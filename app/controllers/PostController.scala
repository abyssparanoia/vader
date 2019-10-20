package controllers

import javax.inject._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.Json
import services._

class PostController @Inject()(mcc: MessagesControllerComponents,postService: services.Post) extends MessagesAbstractController(mcc) {

  implicit val postWrites = Json.writes[entities.Posts]

  def list() = Action {
    implicit request: MessagesRequest[AnyContent] =>
      val postList = postService.list()
      val json = Json.toJson(postList)
      Ok(json)
  }

}
