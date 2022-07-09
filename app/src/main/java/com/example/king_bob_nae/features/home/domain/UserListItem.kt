package com.example.king_bob_nae.features.home.domain

sealed class UserListItem {
    object Plus : UserListItem()

    data class User(
        val id: String,
        val url: Int,
        val name: String
    ) : UserListItem()
}
