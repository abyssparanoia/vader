package controllers

import javax.inject._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.Json
import services._

class PostController @Inject()(mcc: MessagesControllerComponents,postService: services.PostService) extends MessagesAbstractController(mcc) {

  implicit val postWrites = Json.writes[entities.Post]

  def get(postID: Int) = Action {
    implicit request:MessagesRequest[AnyContent] =>
      val post = postService.get(postID)
      val json = Json.toJson(post)
      Ok(json)
  }

  def list() = Action {
    implicit request: MessagesRequest[AnyContent] =>
      val postList = postService.list()
      val json = Json.toJson(postList)
      Ok(json)
  }

}
