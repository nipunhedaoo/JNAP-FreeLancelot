name := """FreeLancelot"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.8"

libraryDependencies += guice

libraryDependencies += ehcache

libraryDependencies ++= Seq(
  javaWs,
  "org.json" % "org.json" % "chargebee-1.0"
)


libraryDependencies += "org.mockito" % "mockito-core" % "4.0.0" % Test

libraryDependencies += "org.powermock" % "powermock-module-junit4" % "2.0.9" % Test

libraryDependencies += "org.powermock" % "powermock-api-mockito2" % "2.0.9" % Test

routesGenerator := InjectedRoutesGenerator

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.6.18" % Test

libraryDependencies+="com.typesafe.akka" %% "akka-testkit" % "2.6.18" % Test
libraryDependencies+="com.typesafe.akka" %% "akka-slf4j" % "2.6.18" % Test
libraryDependencies+="com.typesafe.akka" %% "akka-protobuf-v3" % "2.6.18" % Test
libraryDependencies+="com.typesafe.akka" %% "akka-serialization-jackson" % "2.6.18" % Test
libraryDependencies+="com.typesafe.akka" %% "akka-stream" % "2.6.18" % Test
libraryDependencies+="com.typesafe.akka" %% "akka-actor-typed" % "2.6.18" % Test