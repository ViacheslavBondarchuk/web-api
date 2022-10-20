package io.github.viacheslavbondarchuk.domain

case class UserSession(sessionToken: String, user: Option[User])
