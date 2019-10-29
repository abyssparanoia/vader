package repositories

import com.google.inject.{ImplementedBy, Singleton}
import slick.jdbc.MySQLProfile.api._
import entities.{Post, PostQuery, User, UserQuery}

import scala.concurrent.Await._
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

@ImplementedBy(classOf[PostRepositoryImpl])
trait PostRepository {
  def get(postID: Int): Option[models.Post]
  def list(): Seq[models.Post]
  def create(userID: String,
             title: String,
             description: String,
             text: String,
             createdAt: Long,
             updatedAt: Long): Int
  def update(id: Int,
             title: String,
             description: String,
             text: String,
             updatedAt: Long): Int
}

@Singleton
class PostRepositoryImpl extends PostRepository {
  private val database = Database.forConfig("db.default")

  def get(postID: Int): Option[models.Post] = {
    val future =
      database.run(
        PostQuery
          .filter(_.id === postID)
          .join(UserQuery)
          .on(_.userID === _.id)
          .result
          .headOption)
    result(future, Duration.Inf).map {
      case (post: Post, user: User) => models.Post.buildFromEntity(post, user)

    }
  }

  def list(): Seq[models.Post] = {
    val future: Future[Seq[(Post, User)]] = database.run(
      PostQuery.sortBy(_.id).join(UserQuery).on(_.userID === _.id).result)
    result(future, Duration.Inf).map {
      case (post: Post, user: User) => models.Post.buildFromEntity(post, user)
    }
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
    result(future, Duration.Inf)
  }

  def update(id: Int,
             title: String,
             description: String,
             text: String,
             updatedAt: Long): Int = {
    val future = database.run(
      PostQuery
        .filter(_.id === id)
        .map(c => (c.title, c.description, c.text, c.updatedAt))
        .update((title, description, text, updatedAt))
    )

    result(future, Duration.Inf)
  }

}
