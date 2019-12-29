package domain.model

import play.api.libs.json.{Json, JsonConfiguration, JsonNaming}

case class Post(id: Long,
                user: User,
                title: String,
                description: String,
                text: String,
                createdAt: Long,
                updatedAt: Long)

object Post {
  implicit val config = JsonConfiguration(JsonNaming.SnakeCase)
  implicit val jsonWrites = Json.writes[Post]
  implicit val jsonReads = Json.reads[Post]
}
