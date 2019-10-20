package services

import javax.inject._
import repositories.PostRepository


@Singleton
class PostService @Inject() (postRepository: PostRepository) {

  def list(): List[entities.Post] = postRepository.list()
}
