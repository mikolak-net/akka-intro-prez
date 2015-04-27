name := "demo"

version := "1.0"

scalaVersion := "2.11.4"

val akkaVersion = "2.3.9"

libraryDependencies ++= Seq("com.typesafe.akka"  %% "akka-actor" % akkaVersion,
  "com.typesafe.akka"  %% "akka-testkit" % akkaVersion)