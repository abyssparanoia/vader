package controllers

import entities.Post
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
      val post: Option[Post] = postService.get(postID)
      post match {
        case Some(value) => Ok(Json.toJson(value))
        case None => NotFound("not found post")
    }
  }

  def list() = Action {
    implicit request: MessagesRequest[AnyContent] =>
      val postList = postService.list()
      val json = Json.toJson(postList)
      Ok(json)
  }

}
