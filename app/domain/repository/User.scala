package domain.repository

import com.google.inject.ImplementedBy
import domain.model

import infrastructure.repository

@ImplementedBy(classOf[repository.UserRepositoryImpl])
trait User {
  def get(id: String): Option[model.User]
}
