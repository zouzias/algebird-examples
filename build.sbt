name := "algebird-examples"

version := "1.0"

scalaVersion := "2.11.7"

resolvers ++= Seq(
     "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
     "releases"  at "http://oss.sonatype.org/content/repositories/releases"
   )

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlint",
  "-Ywarn-adapted-args",
  "-Ywarn-value-discard",
  "-Ywarn-inaccessible",
  "-Ywarn-dead-code"
)

libraryDependencies ++= Seq(
  "com.twitter" %% "algebird-core" % "0.10.2"
)

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))
