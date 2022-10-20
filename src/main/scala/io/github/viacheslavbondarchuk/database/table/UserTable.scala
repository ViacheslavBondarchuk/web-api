package io.github.viacheslavbondarchuk.database.table

import io.github.viacheslavbondarchuk.domain.User
import slick.jdbc.PostgresProfile.api._

class UserTable(tag: Tag) extends AbstractIndexedEntityTable[User](tag, "Users") {

  def username = column[String]("username", O.Unique)

  def email = column[String]("email", O.Unique)

  def password = column[String]("password")

  def firstName = column[String]("firstName")

  def lastName = column[String]("lastName")

  def age = column[Int]("age")


  override def * = (id, username, email, password, firstName, lastName, age) <> (User.tupled, User.unapply)
}
