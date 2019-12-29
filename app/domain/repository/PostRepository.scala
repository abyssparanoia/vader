package domain.repository

import domain.model.Post

trait PostRepository {
  def get(postID: Long): Option[Post]
  def list(): Seq[Post]
  def create(userID: String,
             title: String,
             description: String,
             text: String,
             createdAt: Long,
             updatedAt: Long): Int
  def update(id: Long,
             title: String,
             description: String,
             text: String,
             updatedAt: Long): Int
}
