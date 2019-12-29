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
}
