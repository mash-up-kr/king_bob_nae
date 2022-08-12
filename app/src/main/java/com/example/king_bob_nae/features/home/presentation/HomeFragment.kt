package com.example.king_bob_nae.features.home.presentation

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.king_bob_nae.R
import com.example.king_bob_nae.base.BaseFragment
import com.example.king_bob_nae.databinding.FragmentHomeBinding
import com.example.king_bob_nae.features.home.domain.UserListItem
import com.example.king_bob_nae.features.home.presentation.adapter.UserListAdapter
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val userListAdapter by lazy { UserListAdapter() }
    private val userList = mutableListOf<UserListItem>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectFlows()
    }

    private fun initView() {
        binding.commonHomeLayout.rvFriends.apply {
            adapter = userListAdapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)

                    val slidePadding =
                        resources.getDimensionPixelSize(R.dimen.userList_item_padding)

                    outRect.right = slidePadding
                }
            })
        }

        userList.add(UserListItem.Plus)
        userList.add(UserListItem.User("1", R.drawable.ic_component_13, "석주"))
        userList.add(UserListItem.User("2", R.drawable.ic_component_13, "현수"))
        userList.add(UserListItem.User("3", R.drawable.ic_component_13, "지은"))
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    userListAdapter.submitList(userList)
                }
            }
        }
    }
}
