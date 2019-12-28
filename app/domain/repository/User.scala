package domain.repository

import domain.model

trait User {
  def get(id: Int): Option[model.User]
}
