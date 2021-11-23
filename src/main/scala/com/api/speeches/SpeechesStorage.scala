package com.api.speeches

import org.apache.spark.sql.types.{DateType, IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}

object SpeechesStorage {
  val Schema = StructType(Array(
    StructField("speaker", StringType, true),
    StructField("topic", StringType, true),
    StructField("date", DateType, true),
    StructField("words", IntegerType, true))
  )

  val Spark: SparkSession = SparkSession
    .builder
    .master("local")
    .appName("political-speeches")
    .getOrCreate

  def speechesData(): DataFrame = {
    val path = getClass.getResource("/url1-data.csv").getPath
    Spark.read
      .format("csv")
      .schema(Schema)
      .load(path)
    }
}
