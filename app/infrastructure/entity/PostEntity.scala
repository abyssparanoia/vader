package infrastructure.entity

import domain.model.{User, Post}

object PostEntity {

  def outputModel(post: Tables.Posts#TableElementType,
                  user: Tables.Users#TableElementType): Post = {
    Post(
      id = post.id,
      user = UserEntity.outputModel(user),
      title = post.title,
      description = post.description,
      text = post.text,
      createdAt = post.createdAt,
      updatedAt = post.updatedAt
    )
  }

}
