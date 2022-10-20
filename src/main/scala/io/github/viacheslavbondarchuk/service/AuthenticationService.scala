package io.github.viacheslavbondarchuk.service

import io.github.viacheslavbondarchuk.domain.User
import io.github.viacheslavbondarchuk.repository.impl.UserRepository

import scala.concurrent.{ExecutionContext, Future}

trait AuthenticationService {

  def authenticate(sessionToken: String): Unit

  def authenticate(username: String, password: String)(implicit ex: ExecutionContext): Future[Option[User]]

}

object AuthenticationService extends AuthenticationService {

  override def authenticate(username: String, password: String)(implicit ex: ExecutionContext): Future[Option[User]] =
    UserRepository.findByUsername(username)
      .map(_.filter(_.password == password)
        .orElse(throw new RuntimeException("Invalid username or password")))

  override def authenticate(sessionToken: String): Unit = ???
}
