package models

import play.api.libs.json.{Json, JsonConfiguration, JsonNaming}

case class Post(id: Int,
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

  def buildFromEntity(post: entities.Post, user: entities.User) =
    Post(
      post.id,
      User(
        user.id,
        user.displayName,
        user.avatarURL,
        user.createdAt,
        user.updatedAt
      ),
      post.title,
      post.description,
      post.text,
      post.createdAt,
      post.updatedAt
    )
}
