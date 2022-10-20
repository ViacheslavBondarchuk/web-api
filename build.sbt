ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.9"

val akkaHttpVersion = "10.2.10"
val akkaActorVersion = "2.6.20"
val slickVersion = "3.4.1"
val log4jVersion = "2.19.0"
val typesafeConfigVersion = "1.4.2"
val psqlVersion = "42.5.0"

lazy val dependencies = Seq(
  "org.postgresql" % "postgresql" % psqlVersion,
  "com.typesafe.slick" %% "slick" % slickVersion,
  "com.typesafe" % "config" % typesafeConfigVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaActorVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaActorVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
  "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaActorVersion,
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % log4jVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
)

lazy val root = (project in file("."))
  .settings(
    name := "web-api",
    libraryDependencies ++= dependencies
  )
