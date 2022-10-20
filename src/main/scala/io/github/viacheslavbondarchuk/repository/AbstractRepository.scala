package io.github.viacheslavbondarchuk.repository

import io.github.viacheslavbondarchuk.database.table.AbstractIndexedEntityTable
import io.github.viacheslavbondarchuk.domain.IndexedEntity
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

abstract class AbstractRepository[T <: IndexedEntity, A <: AbstractIndexedEntityTable[T]](protected val table: TableQuery[A], protected val database: Database)
  extends Repository[T] {

  override def save(entity: T): Future[Int] = database.run(table += entity)

  override def remove(id: Long): Future[Int] = database.run(table.filter(_.id === id).delete)

  override def findById(id: Long): Future[Option[T]] = database.run(table.filter(_.id === id).result.headOption)

  override def findAll(): Future[Seq[T]] = database.run(table.result)
}
