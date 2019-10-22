package services

import entities.Post
import javax.inject._
import repositories.PostRepository
@Singleton
class PostService @Inject()(postRepository: PostRepository) {

  def get(postID: Int): Option[entities.Post] = postRepository.get(postID)

  def list(): List[entities.Post] = postRepository.list()

  def create(title: String, description: String, text: String): Post = {
    val now: Long = System.currentTimeMillis()
    postRepository.create(title, description, text, now, now)
  }

  def update(id: Int,
             title: String,
             description: String,
             text: String): Option[Post] = {
    val now: Long = System.currentTimeMillis()

    postRepository.get(id) match {
      case Some(value) =>
        Option(
          postRepository
            .update(id, title, description, text, now)).map(_ =>
          Post(id, title, description, text, value.createdAt, now))
      case None => None
    }
  }
}
