package repositories

import com.google.inject.Singleton
import slick.jdbc.MySQLProfile.api._
import entities.{Post, PostQuery, User, UserQuery}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

@Singleton
class PostRepository {
  private val database = Database.forConfig("db.default")

  def get(postID: Int): Option[(Post, User)] = {
    val future =
      database.run(
        PostQuery
          .filter(_.id === postID)
          .join(UserQuery)
          .on(_.userID === _.id)
          .result
          .headOption)
    Await.result(future, Duration.Inf)
  }

  def list(): Seq[(Post, User)] = {
    val future: Future[Seq[(Post, User)]] = database.run(
      PostQuery.sortBy(_.id).join(UserQuery).on(_.userID === _.id).result)
    Await.result(future, Duration.Inf)
  }

  def create(userID: String,
             title: String,
             description: String,
             text: String,
             createdAt: Long,
             updatedAt: Long): Int = {
    val future = {
      database.run(
        PostQuery.map(
          post =>
            (post.userID,
             post.title,
             post.description,
             post.text,
             post.createdAt,
             post.updatedAt)) += (userID, title, description, text, createdAt, updatedAt))
    }
    Await.result(future, Duration.Inf)
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
