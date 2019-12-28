package infrastructure.entity

import domain.model

object UserEntity {
  def outputModel(e: Tables.Users#TableElementType): model.User = {
    model.User(
      id = e.id,
      displayName = e.displayName,
      avatarURL = e.avatarUrl,
      createdAt = e.createdAt,
      updatedAt = e.updatedAt,
    )
  }
}
