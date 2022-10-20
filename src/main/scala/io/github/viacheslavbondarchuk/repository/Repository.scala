package io.github.viacheslavbondarchuk.repository

import io.github.viacheslavbondarchuk.domain.IndexedEntity

import scala.concurrent.Future

trait Repository[T <: IndexedEntity] {
  def save(entity: T): Future[Int]

  def remove(id: Long): Future[Int]

  def findById(id: Long): Future[Option[T]]

  def findAll(): Future[Seq[T]]

}
