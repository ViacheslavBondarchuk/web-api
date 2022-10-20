package io.github.viacheslavbondarchuk.database.table

import slick.lifted.TableQuery

object SlickTable {
  val users: TableQuery[UserTable] = TableQuery[UserTable]

  val all = Seq(users)


}
