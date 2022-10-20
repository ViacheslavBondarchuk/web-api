package io.github.viacheslavbondarchuk.service

import io.github.viacheslavbondarchuk.domain.UserSession
import io.github.viacheslavbondarchuk.util.DateTimeHelper

import scala.collection.mutable

trait SessionService {

  def find(sessionToken: String): Option[UserSession]

  def get(sessionToken: String): UserSession

  def add(session: UserSession): UserSession

  def remove(sessionToken: String): Option[UserSession]

}

object SessionService extends SessionService {
  private val sessionTimestamps: mutable.Map[String, Long] = mutable.Map()
  private val sessions: mutable.Map[String, UserSession] = mutable.Map()

  val removeExpiredSessionTask: Runnable = () => {
    val now: Long = DateTimeHelper.currentTimeInMillisSupplier()
    sessionTimestamps.filter(now - _._2 >= 30_000L).foreach {
      case (sessionToken, _) =>
        sessions.remove(sessionToken)
        sessionTimestamps.remove(sessionToken)
    }
  }

  override def add(session: UserSession): UserSession = {
    sessions.put(session.sessionToken, session)
    sessionTimestamps.put(session.sessionToken, DateTimeHelper.currentTimeInMillisSupplier())
    session
  }

  override def remove(sessionToken: String): Option[UserSession] = sessions.remove(sessionToken)

  override def find(sessionToken: String): Option[UserSession] = {
    sessionTimestamps.update(sessionToken, DateTimeHelper.currentTimeInMillisSupplier())
    sessions.get(sessionToken)
  }

  override def get(sessionToken: String): UserSession = find(sessionToken)
    .getOrElse(throw new RuntimeException("Can't find session"))

}

