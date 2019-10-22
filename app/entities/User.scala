package entities

import slick.jdbc.MySQLProfile.api._

case class User(id: String,
                displayName: String,
                avatarURL: String,
                createdAt: Long,
                updatedAt: Long)

class UserTable(tag: Tag) extends Table[User](tag, "users") {
  def id = column[String]("id", O.PrimaryKey)

  def displayName = column[String]("display_name")

  def avatarURL = column[String]("avatar_url")

  def createdAt = column[Long]("created_at")

  def updatedAt = column[Long]("updated_at")

  def * =
    (id, displayName, avatarURL, createdAt, updatedAt) <> (User.tupled, User.unapply)

}

object UserQuery extends TableQuery(new UserTable(_))
