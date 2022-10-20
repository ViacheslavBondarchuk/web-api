package io.github.viacheslavbondarchuk.domain

trait IndexedEntity {
  def id(): Long
}

case class User(id: Long = 0L, username: String, email: String, password: String, firstName: String, lastName: String, age: Int) extends IndexedEntity
