package services

import entities.{Post, User}
import javax.inject._
import repositories.PostRepository
@Singleton
class PostService @Inject()(postRepository: PostRepository) {

  def get(postID: Int): Option[models.Post] = {
    postRepository.get(postID) match {
      case Some(value: (Post, User)) =>
        Option(models.Post.buildFromEntity(value._1, value._2))
      case None => None
    }
  }

  def list(): Seq[models.Post] = {
    postRepository.list().map {
      case (post: entities.Post, user: entities.User) =>
        models.Post.buildFromEntity(post, user)
    }
  }

  def create(userID: String,
             title: String,
             description: String,
             text: String): models.Post = {
    val now: Long = System.currentTimeMillis()
    val postID =
      postRepository.create(userID, title, description, text, now, now)
    get(postID) match {
      case Some(value) => value
      case None        => throw new Exception("error")
    }
  }

  def update(id: Int, title: String, description: String, text: String) = {
    val now: Long = System.currentTimeMillis()

    postRepository.get(id) match {
      case Some(_) =>
        Option(
          postRepository
            .update(id, title, description, text, now))
      case None => None
    }
  }
}
