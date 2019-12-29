package services

import javax.inject.{Inject, Singleton}
import domain.repository
import domain.model

@Singleton
class UserService @Inject()(userRepository: repository.User) {

  def get(id: String): Option[model.User] = {
    userRepository.get(id) match {
      case Some(value) => Option(value)
      case None        => None
    }
  }
}
