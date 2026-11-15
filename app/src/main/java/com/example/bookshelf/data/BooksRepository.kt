package com.example.bookshelf.data

import com.example.bookshelf.model.BookItem
import com.example.bookshelf.network.BooksApiService

interface BooksRepository {
    suspend fun getBooks(query: String): List<BookItem>
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {

    override suspend fun getBooks(query: String): List<BookItem> {
        val bookList = mutableListOf<BookItem>()

        val searchResult = booksApiService.getBooks(query)

        if (searchResult.items != null) {
            for (item in searchResult.items) {
                try {
                    val bookInfo = booksApiService.getBookInfo(item.id)
                    if (bookInfo.volumeInfo?.imageLinks?.thumbnail != null) {
                        bookList.add(bookInfo)
                    }
                } catch (e: Exception) {
                    // fallback if a single book detail call fails
                    if (item.volumeInfo?.imageLinks?.thumbnail != null) {
                        bookList.add(item)
                    }
                }
            }
        }

        return bookList
    }
}