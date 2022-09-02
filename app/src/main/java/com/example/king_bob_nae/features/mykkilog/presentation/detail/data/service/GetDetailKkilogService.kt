package com.example.king_bob_nae.features.mykkilog.presentation.detail.data.service

import retrofit2.http.GET
import retrofit2.http.Path

interface GetDetailKkilogService {

    @GET("detail-log/{id}")
    suspend fun getDetailKkilog(
        @Path("id") id: Int
    )
}