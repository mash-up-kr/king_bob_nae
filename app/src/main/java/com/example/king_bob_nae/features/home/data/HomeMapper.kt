package com.example.king_bob_nae.features.home.data

import com.example.king_bob_nae.features.home.data.freindlist.Friends
import com.example.king_bob_nae.features.home.data.userstate.HomeStatus
import com.example.king_bob_nae.features.home.domain.freindlist.UserListItem
import com.example.king_bob_nae.features.home.domain.userstate.HomeUserState
import com.example.king_bob_nae.utils.TimeConverter

fun HomeStatus.toHomeState() = HomeUserState(
    date = TimeConverter().getCurrentDate(),
    todayKkirokCount = "${this.logStats.today}끼",
    userNickName = this.nickName,
    phrase = this.phrase,
    max = this.logStats.max,
    progressBar = this.logStats.progress,
    level = "Lv.${this.logStats.level}",
    levelTotalKkirokCount = "${this.logStats.progress}/${this.logStats.max}",
    largeImageUrl = this.fullImageUrl,
    smallImageUrl = this.miniImageUrl,
    totalKkilogCount = this.logStats.total
)

fun Friends.toUserListItem() = this.friends.map {
    UserListItem(
        id = it.id,
        url = it.imageUrl,
        nickName = it.name
    )
}
