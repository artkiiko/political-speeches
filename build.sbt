name := "political-speeches"

version := "0.1"

scalaVersion := "2.12.12"

val AkkaVersion = "2.6.17"
val AkkaHttpVersion = "10.2.7"
val sparkVersion = "3.1.2"
val scalatestVersion = "3.2.10"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,

  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,

  "org.scalatest" %% "scalatest" % scalatestVersion % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % Test,
  "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % "test"
)