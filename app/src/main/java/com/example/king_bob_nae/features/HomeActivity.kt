package com.example.king_bob_nae.features

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseActivity
import com.example.king_bob_nae.databinding.ActivityHomeBinding
import com.example.king_bob_nae.features.create.AddKkiLogBottomSheetFragment
import com.example.king_bob_nae.features.create.kkilog.presenter.KkiLogViewModel
import com.example.king_bob_nae.features.home.presentation.HomeFragmentDirections
import com.example.king_bob_nae.features.imagepicker.presentation.ImageListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    companion object {
        private const val REQUEST_PERMISSION = 1000
        private const val READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
        private const val WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE
        private const val CAMERA = Manifest.permission.CAMERA
    }

    private val imageListViewModel: ImageListViewModel by viewModels()
    private val kkiLogViewModel: KkiLogViewModel by viewModels()
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    private val permissions = arrayOf(
        CAMERA,
        READ_EXTERNAL_STORAGE,
        WRITE_EXTERNAL_STORAGE
    )

    private fun navigate(resId: Int) {
        navController.navigate(resId)
    }

    private fun navigateWithId(userId: Int) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToFriendsHomeFragment(userId)
        val option = NavOptions.Builder().setPopUpTo(R.id.homeFragment, true).build()
        navController.navigate(action, option)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initNavigateFriendsHomeFragment()
    }

    private fun initNavigateFriendsHomeFragment() {
        val id = intent.getStringExtra("id")
        if (!id.isNullOrEmpty()) {
            navigateWithId(id.toInt())
        }
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPermission() {
        if (!(checkPermission(READ_EXTERNAL_STORAGE) && checkPermission(CAMERA))) {
            ActivityCompat.requestPermissions(this@HomeActivity, permissions, REQUEST_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // do nothing
        } else {
            AlertDialog.Builder(this)
                .setTitle(R.string.alert)
                .setMessage(R.string.alert_message)
                .setNegativeButton(R.string.alert_no) { _, _ -> }
                .setPositiveButton(R.string.alert_yes) { _, _ ->
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:$packageName")
                    ).apply {
                        addCategory(Intent.CATEGORY_DEFAULT)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }.also { intent ->
                        startActivity(intent)
                    }
                }.show()
        }
    }

    private fun initView() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        with(binding.bottomNavHome) {
            setupWithNavController(navController)
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeFragment -> {
                        navigate(R.id.homeFragment)
                        clearList()
                    }
                    R.id.imagePickerFragment -> {
                        checkPermission()
                        AddKkiLogBottomSheetFragment().show(supportFragmentManager, "")
                    }
                    R.id.myKkiLogFragment -> {
                        navigate(R.id.myKkiLogFragment)
                        clearList()
                    }
                }
                true
            }
            itemIconTintList = null
        }
    }

    private fun clearList() {
        imageListViewModel.resetAllData()
        kkiLogViewModel.clearList()
    }
}
