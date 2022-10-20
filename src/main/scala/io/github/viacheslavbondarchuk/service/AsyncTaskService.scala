package io.github.viacheslavbondarchuk.service

import java.util.concurrent.{Executors, ScheduledExecutorService}
import scala.concurrent.duration.TimeUnit

trait AsyncTaskService {

  val executor: ScheduledExecutorService

  def register(task: Runnable): Unit

  def register(task: Runnable, delay: Long, period: Long, timeUnit: TimeUnit): Unit
}

object AsyncTaskService extends AsyncTaskService {
  override val executor: ScheduledExecutorService = Executors.newScheduledThreadPool(40)

  override def register(task: Runnable): Unit = executor.execute(task)

  override def register(task: Runnable, delay: Long, period: Long, timeUnit: TimeUnit): Unit = executor.scheduleWithFixedDelay(task, delay, period, timeUnit)

}
