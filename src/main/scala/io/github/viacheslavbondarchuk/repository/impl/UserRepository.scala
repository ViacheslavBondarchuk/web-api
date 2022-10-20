package io.github.viacheslavbondarchuk.repository.impl

import io.github.viacheslavbondarchuk.database.connection.DatabaseConnection
import io.github.viacheslavbondarchuk.database.table.{SlickTable, UserTable}
import io.github.viacheslavbondarchuk.domain.User
import io.github.viacheslavbondarchuk.repository.AbstractRepository
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

object UserRepository extends AbstractRepository[User, UserTable](SlickTable.users, DatabaseConnection.database) {
  def findByUsername(username: String): Future[Option[User]] = database.run(table.filter(_.username === username).result.headOption)

  def findByEmail(email: String): Future[Option[User]] = database.run(table.filter(_.email === email).result.headOption)
}
