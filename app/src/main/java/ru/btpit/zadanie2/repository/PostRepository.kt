package ru.btpit.zadanie2.repository

import androidx.lifecycle.LiveData

import ru.btpit.zadanie2.dto.Post


interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun like(id:Long)
    fun share(id:Long)

    fun save(post: Post)


    fun removeById(id: Long)
}