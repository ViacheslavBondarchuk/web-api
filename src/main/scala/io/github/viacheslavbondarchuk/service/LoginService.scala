package io.github.viacheslavbondarchuk.service

import io.github.viacheslavbondarchuk.domain.Request.LoginRequest
import io.github.viacheslavbondarchuk.domain.UserSession

import java.util.UUID
import java.util.concurrent.Executors
import scala.concurrent.{ExecutionContext, Future}

trait LoginService {
  protected implicit val ec: ExecutionContext

  def login(loginRequest: LoginRequest): Future[UserSession]
}

object LoginService extends LoginService {

  override protected implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(100))

  override def login(loginRequest: LoginRequest): Future[UserSession] = AuthenticationService.authenticate(loginRequest.username, loginRequest.password)
    .map(UserSession(UUID.randomUUID().toString, _))
    .map(SessionService.add)

}
