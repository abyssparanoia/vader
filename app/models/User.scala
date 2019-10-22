package models

import play.api.libs.json.Json

case class User(id: String,
                displayName: String,
                avatarURL: String,
                createdAt: Long,
                updatedAt: Long)

object User {
  implicit val jsonWrites = Json.writes[User]
  implicit val jsonReads = Json.reads[User]
}
