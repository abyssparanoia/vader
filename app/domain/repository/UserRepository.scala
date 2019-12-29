package domain.repository

import com.google.inject.ImplementedBy
import domain.model
import infrastructure.repository.UserRepositoryImpl

@ImplementedBy(classOf[UserRepositoryImpl])
trait UserRepository {
  def get(id: String): Option[model.User]
}
