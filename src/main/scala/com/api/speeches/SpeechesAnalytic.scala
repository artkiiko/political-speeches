package com.api.speeches

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class SpeechesAnalytic {

  def analytics(param: String): SpeechesData = {
    param match {
      case "url1" => url1Data(SpeechesStorage.speechesData())
      case _ => SpeechesData(None, None, None)
    }
  }

  def url1Data(frame: DataFrame): SpeechesData = {
    val yearFilter = 2013
    val mostSpeeches2013 = frame
      .filter(year(col("date")).geq(lit(yearFilter)))
      .groupBy("speaker").count()
      .orderBy(desc("count"))
      .limit(1)
      .select("speaker")
      .collect()
      .headOption
      .map(_.getString(0))
      .orNull

    val topicFilter = "Internal Security"
    val mostSpeechesByTopic = frame
      .filter(col("topic") === topicFilter)
      .groupBy("speaker", "topic").count()
      .orderBy(desc("count"))
      .limit(1)
      .select("speaker")
      .collect()
      .headOption
      .map(_.getString(0))
      .orNull

    val fewestWordOverall = frame
      .groupBy("speaker").agg(sum("words").as("wordsCount"))
      .orderBy(asc("wordsCount"))
      .limit(1)
      .select("speaker")
      .collect()
      .headOption
      .map(_.getString(0))
      .orNull

    SpeechesData(Option(mostSpeeches2013), Option(mostSpeechesByTopic), Option(fewestWordOverall))
  }
}



case class SpeechesData(mostSpeeches: Option[String],
                        mostSecurity: Option[String],
                        leastWordy: Option[String])
