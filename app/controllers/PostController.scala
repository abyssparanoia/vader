package controllers

import javax.inject._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

class PostController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  def list() = Action {
    implicit request: MessagesRequest[AnyContent] =>
      val message: String = "diplay post list"
      Ok(views.html.list(message))
  }

}
