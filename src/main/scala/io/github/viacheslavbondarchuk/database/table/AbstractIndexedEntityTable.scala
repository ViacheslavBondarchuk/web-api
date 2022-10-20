package io.github.viacheslavbondarchuk.database.table

import io.github.viacheslavbondarchuk.domain.IndexedEntity
import slick.jdbc.PostgresProfile.api._

abstract class AbstractIndexedEntityTable[T <: IndexedEntity](tag: Tag, name: String) extends Table[T](tag, name) {
  def id = column[Long]("id", O.Unique, O.PrimaryKey, O.AutoInc)
}
