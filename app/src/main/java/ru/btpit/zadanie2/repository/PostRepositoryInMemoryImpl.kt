package ru.btpit.zadanie2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import ru.btpit.zadanie2.dto.Post


class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L
    private var post = listOf(
        Post(id = nextId++,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        published = "21 мая в 18:36",
        likecount = 999,
        share = 5,
        isLiked = false
        ),
        Post(
            id = nextId++,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Знаний хватит на всех: на следующей неделе разбираемся Информатикой" ,
            published = "30 сентября в 22:12",
            isLiked = false,
            likecount = 999,
            share = 5
        ),
        Post(
            id = nextId++,
            author = "Инфляция",
            content = "Инфля́ция — устойчивое повышение общего уровня цен на товары и услуги; процесс обесценивания денег, падение их покупательной способности вследствие чрезмерного выпуска" ,
            published = "10 сентября в 12:12",
            isLiked = false,
            likecount = 999,
            share = 5
        ),
    )
    private val data = MutableLiveData(post)

    override fun getAll(): LiveData<List<Post>> = data
    override fun like(id:Long) {

        post = post.map {
            if (it.id == id && it.isLiked) {
                it.copy(likecount = it.likecount - 1, isLiked = false)
            } else if (it.id == id && !it.isLiked) {
                it.copy(likecount = it.likecount + 1, isLiked = true)
            } else {
                it
            }
        }

        data.value = post



    }


    override fun save(posts: Post) {
        if (posts.id == 0L) {
            // TODO: remove hardcoded author & published
            post = listOf(
                posts.copy(
                    id = nextId++,
                    author = "Me",
                    isLiked = false,
                    published = "now"
                )
            ) + post
            data.value = post
            return
        }
        post = post.map {
            if (it.id != posts.id) it else it.copy(content = posts.content)
        }
        data.value = post
    }

    override fun share(id: Long) {
        post = post.map {
            if (it.id != id) {
                it
            } else {
                it.copy(share = it.share + 1)
            }
        }
        data.value = post
    }
    override fun removeById(id: Long)
    {
        post = post.filter { it.id != id }
        data.value =post
    }
}