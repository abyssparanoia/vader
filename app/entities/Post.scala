package entities

import slick.driver.MySQLDriver.api._

case class Post(id: Int,
                title: String,
                description: String,
                text: String,
                createdAt: Long,
                updatedAt: Long)

class PostTable(tag: Tag) extends Table[Post](tag, "posts") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def title = column[String]("title")

  def description = column[String]("description")

  def text = column[String]("text")

  def createdAt = column[Long]("created_at")

  def updatedAt = column[Long]("updated_at")

  def * =
    (id, title, description, text, createdAt, updatedAt) <> (Post.tupled, Post.unapply)
}

object PostQuery extends TableQuery(new PostTable(_))
