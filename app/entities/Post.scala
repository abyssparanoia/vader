package entities

import slick.driver.MySQLDriver.api._

case class Post(id: Int, title:String, description: String, text: String, created_at: Int, updated_at: Int)

class Posts(tag:Tag) extends Table[Post](tag,"posts") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def title = column[String]("title")

  def description = column[String]("description")

  def text = column[String]("text")

  def created_at = column[Int]("created_at")

  def updated_at = column[Int]("updated_at")

  def * = (id,title,description,text,created_at,updated_at) <> (Post.tupled,Post.unapply)
}
