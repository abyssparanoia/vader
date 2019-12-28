package infrastructure.repository

import domain.model.User
import domain.repository
import slick.jdbc.MySQLProfile.api._
import infrastructure.entity.{UserEntity, Tables}

import scala.concurrent.duration.Duration
import scala.concurrent.Await._

@Singleton()
class PostRepositoryImpl extends repository.User {
  private val database = Database.forConfig("db.default")

  override def get(id: String): Option[User] = {
    val future =
      database.run(Tables.Users.filter(_.id === id).result.headOption)

    result(future, Duration.Inf).map {
      case (user) => UserEntity.outputModel(user)
    }
  }

}
