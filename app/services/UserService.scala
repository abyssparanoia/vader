package services

import javax.inject.{Inject, Singleton}
import domain.model
import domain.repository.UserRepository

@Singleton
class UserService @Inject()(userRepository: UserRepository) {

  def get(id: String): Option[model.User] = {
    userRepository.get(id) match {
      case Some(value) => Option(value)
      case None        => None
    }
  }
}
