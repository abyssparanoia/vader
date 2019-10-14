package controllers

import javax.inject._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._
import services._

class PostController @Inject()(mcc: MessagesControllerComponents) extends MessagesAbstractController(mcc) {

  def list() = Action {
    implicit request: MessagesRequest[AnyContent] =>
      val postList: Seq[Post] = Seq(Post("post_1"),Post("post_2"))
      Ok(views.html.list(postList))
  }

}
