package controller

import domain.model.Post
import javax.inject._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.{JsResult, JsValue, Json}
import service.PostService

class PostController @Inject()(mcc: MessagesControllerComponents,
                               postService: PostService)
    extends MessagesAbstractController(mcc) {

  def get(postID: Long) = Action {
    implicit request: MessagesRequest[AnyContent] =>
      val post: Option[Post] = postService.get(postID)
      post match {
        case Some(value) => Ok(Json.toJson(value))
        case None        => NotFound("not found post")
      }
  }

  def list() = Action { implicit request: MessagesRequest[AnyContent] =>
    val postList = postService.list()
    val json = Json.toJson(postList)
    Ok(json)
  }

  case class CreatePostRequest(title: String, description: String, text: String)

  implicit val createPostRequestReads = Json.reads[CreatePostRequest]

  def create(): Action[JsValue] = Action(parse.json) { request =>
    val result = request.body.validate[CreatePostRequest]
    val dst = result.get
    val post =
      postService.create("DUMMY_USER_ID", dst.title, dst.description, dst.text)
    val json = Json.toJson(post)
    Ok(json)
  }

  case class UpdatePoseRequest(title: String, description: String, text: String)

  implicit val updatePoseRequestReads = Json.reads[UpdatePoseRequest]

  def update(postID: Long): Action[JsValue] = Action(parse.json) { request =>
    val result: JsResult[UpdatePoseRequest] =
      request.body.validate[UpdatePoseRequest]
    val dst: UpdatePoseRequest = result.get
    postService.update(postID, dst.title, dst.description, dst.text) match {
      case Some(_) => Ok("success")
      case None    => NotFound("not found post")
    }
  }

}
