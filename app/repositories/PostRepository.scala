package repositories

import com.google.inject.Singleton
import slick.jdbc.MySQLProfile.api._
import entities.{Post, PostQuery}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@Singleton
class PostRepository {
  private val database = Database.forConfig("db.default")

  def get(postID: Int): Option[Post] = {
    val future =
      database.run(PostQuery.filter(_.id === postID).result.headOption)
    Await.result(future, Duration.Inf)
  }

  def list(): List[Post] = {
    val future = database.run(PostQuery.sortBy(_.id).result)
    Await.result(future, Duration.Inf).toList
  }

  def create(title: String,
             description: String,
             text: String,
             createdAt: Long,
             updatedAt: Long): Post = {
    val future = {
      database.run(
        PostQuery.map(post =>
          (post.title,
           post.description,
           post.text,
           post.createdAt,
           post.updatedAt)) += (title, description, text, createdAt, updatedAt))
    }
    val id = Await.result(future, Duration.Inf)
    Post(id, title, description, title, createdAt, updatedAt)
  }

  def update(id: Int,
             title: String,
             description: String,
             text: String,
             updatedAt: Long) = {
    val future = database.run(
      PostQuery
        .filter(_.id === id)
        .map(c => (c.title, c.description, c.text, c.updatedAt))
        .update((title, description, text, updatedAt))
    )

    Await.result(future, Duration.Inf)
  }

}
