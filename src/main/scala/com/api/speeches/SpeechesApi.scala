package com.api.speeches

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.InternalServerError
import akka.http.scaladsl.server.Directives
import spray.json.{DefaultJsonProtocol, NullOptions}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol with NullOptions  {
  implicit val speechesDataFormat = jsonFormat3(SpeechesData)
}
import spray.json._

class SpeechesApi(speechesAnalytic: SpeechesAnalytic) extends Directives with JsonSupport {
  val route =
    concat(
      path("evaluation") {
        get {
          parameters('url1.as[String]) { url1 =>
            complete {
              try {
                speechesAnalytic.analytics(url1).toJson
              } catch {
                case ex: Exception =>
                  HttpResponse(InternalServerError, entity = s"Show this message your support: ${ex.getMessage}")
              }
            }
          }
        }
      }
    )
}
