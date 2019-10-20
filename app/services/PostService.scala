package services

import javax.inject._
import repositories.PostRepository


@Singleton
class PostService @Inject() (postRepository: PostRepository) {

  def get(postID: Int):Option[entities.Post] = postRepository.get(postID)

  def list(): List[entities.Post] = postRepository.list()
}
