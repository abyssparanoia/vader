package services

import entities.{Post, User}
import javax.inject._
import repositories.PostRepository
@Singleton
class PostService @Inject()(postRepository: PostRepository) {

  private def buildPost(post: entities.Post, user: entities.User): models.Post =
    models.Post(
      post.id,
      models.User(
        user.id,
        user.displayName,
        user.avatarURL,
        user.createdAt,
        user.updatedAt
      ),
      post.title,
      post.description,
      post.text,
      post.createdAt,
      post.updatedAt
    )

  def get(postID: Int): Option[models.Post] = {
    postRepository.get(postID) match {
      case Some(value: (Post, User)) => Option(buildPost(value._1, value._2))
      case None                      => None
    }
  }

  def list(): Seq[models.Post] = {
    postRepository.list().map {
      case (post: entities.Post, user: entities.User) => buildPost(post, user)
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
    }
  }

  def update(id: Int, title: String, description: String, text: String) = {
    val now: Long = System.currentTimeMillis()

    postRepository.get(id) match {
      case Some(value) =>
        Option(
          postRepository
            .update(id, title, description, text, now))
      case None => None
    }
  }
}
