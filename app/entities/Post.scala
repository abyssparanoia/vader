package entities

import slick.driver.MySQLDriver.api._

case class Post(id: Int, title:String, description: String, text: String, createdAt: Int, updatedAt: Int)

class Posts(tag:Tag) extends Table[Post](tag,"posts") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def title = column[String]("title")

  def description = column[String]("description")

  def text = column[String]("text")

  def createdAt = column[Int]("created_at")

  def updatedAt = column[Int]("updated_at")

  def * = (id,title,description,text,createdAt,updatedAt) <> (Post.tupled,Post.unapply)
}

object Posts extends TableQuery(new Posts(_))