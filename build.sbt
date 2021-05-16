

name := """play-with-slick"""

version := "1.0"

scalaVersion := "2.13.5"

lazy val root = (project in file(".")).enablePlugins(PlayScala)


libraryDependencies ++= Seq(
  guice,
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  "com.h2database" % "h2" % "1.4.187",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc4",
  "mysql" % "mysql-connector-java" % "8.0.23",
  "com.pauldijou" %% "jwt-core" % "5.0.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
  "org.mockito" %% "mockito-scala" % "1.11.2" % Test,
  specs2 % Test,
  "org.scalatestplus" %% "selenium-3-141" % "3.2.7.0" % "test",
  "org.scalatest" %% "scalatest-flatspec" % "3.2.7" % "test",
  "org.scalatest" %% "scalatest-shouldmatchers" % "3.2.7" % "test"

)

routesGenerator := InjectedRoutesGenerator

javaOptions in Test += "-Dconfig.file=conf/test.conf"
