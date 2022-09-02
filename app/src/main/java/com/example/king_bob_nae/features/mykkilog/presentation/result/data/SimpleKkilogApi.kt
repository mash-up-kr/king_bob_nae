package com.example.king_bob_nae.features.mykkilog.presentation.result.data

import retrofit2.http.*

interface SimpleKkilogApi {

    @GET("logs/{id}") // 간단 끼록 하나 조회
    suspend fun getKkilog(
        @Path("id")
        id: Int,
    )

    @PATCH("logs/{id}") // 수정
    suspend fun patchKkilog(
        @Path("id")
        id: Int,
    )

    @DELETE("logs/{id}") // 삭제
    suspend fun deleteKkilog(
        @Path("id")
        id: Int,
    )

    @POST("logs/{id}") // 간단 끼록 좋아요
    suspend fun postKkilogLike(
        @Path("id")
        id: Int,
    )

    @POST("logs/{id}") // 간단 끼록 좋아요 취소
    suspend fun postKkilogUnLike(
        @Path("id")
        id: Int,
    )

    @POST("logs/{id}") // 간단 끼록 스크랩
    suspend fun postKkilogScrap(
        @Path("id")
        id: Int,
    )

    @POST("logs/{id}") // 간단 끼록 스크랩 취소
    suspend fun postKkilogUnScrap(
        @Path("id")
        id: Int,
    )

}
