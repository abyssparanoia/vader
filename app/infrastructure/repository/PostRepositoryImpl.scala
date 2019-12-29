package infrastructure.repository

import com.google.inject.{ImplementedBy, Singleton}
import domain.model.Post
import domain.repository.PostRepository
import infrastructure.entity.{PostEntity, Tables}
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Await._
import scala.concurrent.duration.Duration
@Singleton
class PostRepositoryImpl extends PostRepository {
  private val database = Database.forConfig("db.default")

  override def get(postID: Long): Option[Post] = {
    val future =
      database.run(
        Tables.Posts
          .filter(_.id === postID)
          .join(Tables.Users)
          .on(_.userId === _.id)
          .result
          .headOption
      )
    result(future, Duration.Inf).map {
      case (post, user) => PostEntity.outputModel(post, user)
    }
  }

  override def list(): Seq[Post] = {
    val future = database.run(
      Tables.Posts
        .join(Tables.Users)
        .on(_.userId === _.id)
        .result
    )
    result(future, Duration.Inf).map {
      case (post, user) => PostEntity.outputModel(post, user)
    }
  }

  override def create(userID: String,
                      title: String,
                      description: String,
                      text: String,
                      createdAt: Long,
                      updatedAt: Long): Long = {
    val future = database.run(
      Tables.Posts.map(
        post =>
          (post.userId,
           post.title,
           post.description,
           post.text,
           post.createdAt,
           post.updatedAt)) += (userID, title, description, text, createdAt, updatedAt))
    result(future, Duration.Inf)
  }

  override def update(id: Long,
                      title: String,
                      description: String,
                      text: String,
                      updatedAt: Long): Long = {

    val future = database.run(
      Tables.Posts
        .filter(_.id === id)
        .map(c => (c.title, c.description, c.text, c.updatedAt))
        .update((title, description, text, updatedAt))
    )
    result(future, Duration.Inf)
  }
}
