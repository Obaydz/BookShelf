package com.example.bookshelf.network

import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val KEY_1 = "AIzaSyC1LOz6rct1XrBF"
private const val KEY_2 = "BJV0FqZFJxL2Ybx3bHI"
private const val API_KEY = KEY_1 + KEY_2

interface BooksApiService {

    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String = API_KEY
    ): BookResponse

    @GET("volumes/{id}")
    suspend fun getBookInfo(
        @Path("id") id: String,
        @Query("key") apiKey: String = API_KEY
    ): BookItem
}