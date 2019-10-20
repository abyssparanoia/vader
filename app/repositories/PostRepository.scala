package repositories

import com.google.inject.Singleton
import slick.driver.MySQLDriver.api._
import entities.{Post,Posts}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@Singleton
class PostRepository {
  private val database = Database.forConfig("db.default")

  def get(postID: Int):Post = {
    val future = database.run(Posts.filter(_.id === postID).result.headOption)
    Await.result(future,Duration.Inf) match {
      case Some(value) => value
    }
  }

  def list(): List[Post] = {
    val future = database.run(Posts.sortBy(_.id).result)
    Await.result(future, Duration.Inf).toList
  }

}
