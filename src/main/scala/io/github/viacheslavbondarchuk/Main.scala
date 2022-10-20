package io.github.viacheslavbondarchuk

import akka.http.scaladsl.Http
import io.github.viacheslavbondarchuk.enpoint.Endpoint
import io.github.viacheslavbondarchuk.service.{AsyncTaskService, SessionService}
import io.github.viacheslavbondarchuk.util.Actors.mainActor

import java.util.concurrent.TimeUnit.SECONDS
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Main {

  def main(args: Array[String]): Unit = {
    Http().newServerAt("localhost", 8080)
      .bind(Endpoint.combined)
      .andThen {
        case Success(_) => AsyncTaskService.register(SessionService.removeExpiredSessionTask, 10L, 5L, SECONDS)
        case Failure(exception) => println(s"Exception: $exception")
      }

  }
}
