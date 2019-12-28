package domain.repository

import domain.model

trait User {
  def get(id: String): Option[model.User]
}
