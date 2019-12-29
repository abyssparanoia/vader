package domain.repository

import domain.model.Post
import com.google.inject.ImplementedBy
import infrastructure.repository.PostRepositoryImpl

@ImplementedBy(classOf[PostRepositoryImpl])
trait PostRepository {
  def get(postID: Long): Option[Post]
  def list(): Seq[Post]
  def create(userID: String,
             title: String,
             description: String,
             text: String,
             createdAt: Long,
             updatedAt: Long): Long
  def update(id: Long,
             title: String,
             description: String,
             text: String,
             updatedAt: Long): Long
}
