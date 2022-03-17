name := """FreeLancelot"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.8"

libraryDependencies += guice

libraryDependencies += ehcache

libraryDependencies ++= Seq(
  javaWs,
  "org.json" % "org.json" % "chargebee-1.0",
  "org.mockito" % "mockito-core" % "4.0.0" % Test,
  "org.powermock" % "powermock-module-junit4" % "2.0.9" % Test,
  "org.powermock" % "powermock-api-mockito2" % "2.0.9" % Test,
)

routesGenerator := InjectedRoutesGenerator

