package services

import javax.inject._
import repositories.PostRepository


@Singleton
class PostService @Inject() (postRepository: PostRepository) {

  def get(postID: Int):entities.Post = postRepository.get(postID)

  def list(): List[entities.Post] = postRepository.list()
}
