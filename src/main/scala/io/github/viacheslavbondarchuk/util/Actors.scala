package io.github.viacheslavbondarchuk.util

import akka.actor.ActorSystem

object Actors {
  implicit val mainActor: ActorSystem = ActorSystem()
}
