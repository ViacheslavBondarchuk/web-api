package io.github.viacheslavbondarchuk.database.connection

import slick.jdbc.JdbcBackend
import slick.jdbc.JdbcBackend.Database

object DatabaseConnection {
  lazy val database: Database = JdbcBackend.Database.forConfig("postgres")
}
