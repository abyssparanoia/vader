package entities

import slick.jdbc.MySQLProfile.api._

case class Post(id: Int,
                userID: String,
                title: String,
                description: String,
                text: String,
                createdAt: Long,
                updatedAt: Long)

class PostTable(tag: Tag) extends Table[Post](tag, "posts") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def userID = column[String]("user_id")

  def title = column[String]("title")

  def description = column[String]("description")

  def text = column[String]("text")

  def createdAt = column[Long]("created_at")

  def updatedAt = column[Long]("updated_at")

  def * =
    (id, userID, title, description, text, createdAt, updatedAt) <> (Post.tupled, Post.unapply)

  def user = foreignKey("users", userID, UserQuery)(_.id)
}

object PostQuery extends TableQuery(new PostTable(_))
