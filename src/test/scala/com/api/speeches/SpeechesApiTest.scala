package com.api.speeches

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.mockito.Mockito.when
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar.mock

class SpeechesApiTest extends AnyWordSpec
  with Matchers
  with ScalatestRouteTest {

  "Speeches API" should {
    "return expected data" in {
      val expected = "{\"leastWordy\":\"Caesare Collins\",\"mostSecurity\":\"Alexander Abel\",\"mostSpeeches\":null}"
      val route = new SpeechesApi(new SpeechesAnalytic()).route
      Get("/evaluation?url1=url1") ~> route ~> check {
        responseAs[String] shouldEqual(expected)
      }
    }

    "return error message in case of exception" in {
      val analyticMock = mock[SpeechesAnalytic]
      when(analyticMock.analytics("exception")).thenThrow(new IllegalArgumentException("Exception message."))
      val api = new SpeechesApi(analyticMock)
      Get("/evaluation?url1=exception") ~> api.route ~> check {
        val expected = "Show this message your support: Exception message."
        val str = responseAs[String]
        str shouldEqual(expected)
      }
    }
  }
}
