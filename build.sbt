name := """FreeLancelot"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.8"

libraryDependencies += guice

libraryDependencies += ehcache

libraryDependencies ++= Seq(
  javaWs,
  "org.json" % "org.json" % "chargebee-1.0"
)

routesGenerator := InjectedRoutesGenerator

