package io.github.viacheslavbondarchuk.util

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import io.github.viacheslavbondarchuk.domain.Request.LoginRequest
import io.github.viacheslavbondarchuk.domain.Response.LoginResponse
import io.github.viacheslavbondarchuk.domain.{User, UserSession}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

object JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val userJson: RootJsonFormat[User] = jsonFormat7(User.apply)
  implicit val loginRequestJson: RootJsonFormat[LoginRequest] = jsonFormat2(LoginRequest.apply)
  implicit val loginResponseJson: RootJsonFormat[LoginResponse] = jsonFormat1(LoginResponse.apply)
  implicit val userSessionJson: RootJsonFormat[UserSession] = jsonFormat2(UserSession.apply)

}
