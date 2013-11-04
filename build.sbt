name := "twitter-accessment"

version := "0.1.0 "

scalaVersion := "2.10.3"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "3.0.3"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.0" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.2.1" % "test",
  "com.typesafe.akka" %% "akka-agent" % "2.2.1"
)
