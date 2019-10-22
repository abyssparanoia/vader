package models

import play.api.libs.json.Json

case class Post(id: Int,
                user: User,
                title: String,
                description: String,
                text: String,
                createdAt: Long,
                updatedAt: Long)

object Post {
  implicit val jsonWrites = Json.writes[Post]
  implicit val jsonReads = Json.reads[Post]
}
