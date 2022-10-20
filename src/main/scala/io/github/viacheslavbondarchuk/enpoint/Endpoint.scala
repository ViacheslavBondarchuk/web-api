package io.github.viacheslavbondarchuk.enpoint

import akka.http.scaladsl.model.{HttpResponse, StatusCode}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.github.viacheslavbondarchuk.domain.Request.LoginRequest
import io.github.viacheslavbondarchuk.domain.User
import io.github.viacheslavbondarchuk.repository.impl.UserRepository
import io.github.viacheslavbondarchuk.service.LoginService
import io.github.viacheslavbondarchuk.util.JsonSupport._

import scala.concurrent.ExecutionContext.Implicits.global

object Endpoint {

  private val login: Route = pathPrefix("login") {
    post {
      entity(as[LoginRequest]) { loginRequest =>
        onSuccess(LoginService.login(loginRequest)) { session =>
          complete(session)
        }
      }
    }
  }

  private val users: Route = pathPrefix("users") {

    get {
      concat(
        pathEndOrSingleSlash {
          onSuccess(UserRepository.findAll()) { users =>
            complete(users)
          }
        },
        path(IntNumber) { id =>
          onSuccess(UserRepository.findById(id)) { user =>
            complete(user)
          }
        }
      )
    } ~
      post {
        entity(as[User]) { user =>
          complete {
            UserRepository.save(user)
              .map(_ => HttpResponse(StatusCode.int2StatusCode(201)))
          }
        }
      }

  }

  val combined: Route = users ~ login

}
