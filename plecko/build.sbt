val akkaVersion = "2.5.17"

name := "plecko"
organization:= "org.epnoi"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.4"


libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
libraryDependencies += "junit" % "junit" % "4.10" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % akkaVersion
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.6.4"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.5"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.13"
libraryDependencies += "org.mockito" % "mockito-scala_2.12" % "0.4.5"



