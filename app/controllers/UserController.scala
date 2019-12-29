package controllers

import javax.inject.Inject
import play.api.mvc.{
  AnyContent,
  MessagesAbstractController,
  MessagesControllerComponents,
  MessagesRequest
}
import domain.model
import play.api.libs.json.{JsResult, JsValue, Json}

class UserController @Inject()(mcc: MessagesControllerComponents,
                               userService: services.UserService)
    extends MessagesAbstractController(mcc) {
  def get(id: String) = Action {
    implicit request: MessagesRequest[AnyContent] =>
      val user: Option[model.User] = userService.get(id)
      user match {
        case Some(value) => Ok(Json.toJson(value))
        case None        => NotFound("not found user")
      }
  }
}
