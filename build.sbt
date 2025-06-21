name := "algebird-examples"

version := "1.0"

scalaVersion := "2.13.16"

resolvers ++= Seq(
     "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
     "releases"  at "https://oss.sonatype.org/content/repositories/releases"
   )

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlint",
  "-Ywarn-value-discard",
  "-Ywarn-dead-code"
)

libraryDependencies ++= Seq(
  "com.twitter" %% "algebird-core" % "0.13.10"
)

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))
