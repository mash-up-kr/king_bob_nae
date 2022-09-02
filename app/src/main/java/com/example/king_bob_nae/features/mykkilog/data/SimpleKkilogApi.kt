package com.example.king_bob_nae.features.mykkilog.data

import com.example.king_bob_nae.features.mykkilog.data.deletekkilog.DeleteKkilogResult
import com.example.king_bob_nae.features.mykkilog.data.getkkilog.GetKkilogResult
import com.example.king_bob_nae.features.mykkilog.data.patchkkilog.PatchKkilogResult
import com.example.king_bob_nae.features.mykkilog.data.postkkiloglike.PostKkilogLikeResult
import com.example.king_bob_nae.features.mykkilog.data.postkkilogscrap.PostKkilogScrapResult
import com.example.king_bob_nae.features.mykkilog.data.postkkilogunlike.PostKkilogUnLikeResult
import com.example.king_bob_nae.features.mykkilog.data.postkkilogunscrap.PostKkilogUnScrapResult
import retrofit2.Response
import retrofit2.http.*

interface SimpleKkilogApi {

    @GET("logs/{id}") // 간단 끼록 하나 조회
    suspend fun getKkilog(
        @Path("id")
        id: Int,
    ): GetKkilogResult

    @PATCH("logs/{id}") // 수정
    suspend fun patchKkilog(
        @Path("id")
        id: Int,
    ): PatchKkilogResult

    @DELETE("logs/{id}") // 삭제
    suspend fun deleteKkilog(
        @Path("id")
        id: Int,
    ): Response<DeleteKkilogResult>

    @POST("logs/{id}/like") // 간단 끼록 좋아요
    suspend fun postKkilogLike(
        @Path("id")
        id: Int,
    ): Response<PostKkilogLikeResult>

    @POST("logs/{id}/unlike") // 간단 끼록 좋아요 취소
    suspend fun postKkilogUnLike(
        @Path("id")
        id: Int,
    ): Response<PostKkilogUnLikeResult>

    @POST("logs/{id}/scrap") // 간단 끼록 스크랩
    suspend fun postKkilogScrap(
        @Path("id")
        id: Int,
    ): Response<PostKkilogScrapResult>

    @POST("logs/{id}/unscrap") // 간단 끼록 스크랩 취소
    suspend fun postKkilogUnScrap(
        @Path("id")
        id: Int,
    ): Response<PostKkilogUnScrapResult>
}
