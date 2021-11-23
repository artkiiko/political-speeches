package com.api.speeches

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http

import scala.concurrent.ExecutionContextExecutor

object SpeechesServer {
  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "local-system")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val api = new SpeechesApi(new SpeechesAnalytic).route

    Http().newServerAt("localhost", 8080).bind(api)
  }
}
