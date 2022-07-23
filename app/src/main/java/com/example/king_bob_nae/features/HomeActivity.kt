package com.example.king_bob_nae.features

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseActivity
import com.example.king_bob_nae.databinding.ActivityHomeBinding
import com.example.king_bob_nae.features.home.presentation.HomeFragment
import com.example.king_bob_nae.features.imagepicker.presentation.ImagePickerFragment
import com.example.king_bob_nae.features.recipe.RecipeFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val homeFragment by lazy { HomeFragment() }
    private val imagePickerFragment by lazy { ImagePickerFragment() }
    private val recipeFragment by lazy { RecipeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.bottomNavHome.run {
            itemIconTintList = null
            changeFragment(homeFragment)
            setOnItemSelectedListener { menu ->
                when (menu.itemId) {
                    R.id.nav_home -> {
                        changeFragment(homeFragment)
                    }
                    R.id.nav_image_picker -> {
                        changeFragment(imagePickerFragment)
                    }
                    R.id.nav_recipe -> {
                        changeFragment(recipeFragment)
                    }
                }
                true
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}
