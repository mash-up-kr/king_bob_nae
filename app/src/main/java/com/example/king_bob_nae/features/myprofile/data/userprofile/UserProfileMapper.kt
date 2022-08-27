package com.example.king_bob_nae.features.myprofile.data.userprofile

import com.example.king_bob_nae.features.myprofile.data.UserProfileResult
import com.example.king_bob_nae.features.myprofile.domain.userprofile.UserProfileUiState

fun UserProfileResult.toUserProfileUiState() = UserProfileUiState(
    profileImageUrl = this.data.miniImageUrl,
    level = "Lv.${this.data.logStats.level}",
    nickName = this.data.name,
    progressbarPercent = this.data.logStats.progress,
    totalKkilogCount = "끼록 ${this.data.logStats.total} / ${this.data.logStats.max}회 작성",
    totalKKini = "${this.data.logStats.total}",
    following = "${this.data.followingCount}",
    follower = "${this.data.followerCount}",
    scrapList = this.data.scrappedLogs.map {
        UserProfileUiState.ScrapedImage(id = "${it.id}${it.type}",
            title = it.title,
            imageUrl = it.image.original,
            type = if (it.type == "simple") "간단 끼록" else "상세 끼록")
    },
    email = this.data.email
)
