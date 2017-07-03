lazy val typeconfig = (project in file("."))
  .settings(
    scalaVersion := "2.11.8",
    version := "1.0.0",
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % "2.5.15",
      "com.typesafe" % "config" % "1.3.1",
      "org.specs2" %% "specs2-core" % "3.8.5" % "test"
    ),
    organization := "com.github.kondaurovdev",
    publishTo := {
      if (isSnapshot.value) {
        Some("Sonatype Nexus Repository Manager" at "https://oss.sonatype.org/content/repositories/snapshots/")
      } else {
        publishTo.value
      }
    },
    bintrayRepository := "maven",
    publishArtifact in (Compile, packageDoc) := !isSnapshot.value,
    publishArtifact in (Test, packageDoc) := false,
    bintrayReleaseOnPublish := !isSnapshot.value,
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    credentials ++= Seq(
      Credentials(Path.userHome / ".ivy2" / ".sonatype")
    ),
    publishMavenStyle := true,
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    pomExtra :=
      <url>https://github.com/kondaurov-scala/typeconfig</url>
        <scm>
          <url>https://github.com/kondaurov-scala/typeconfig.git</url>
          <connection>https://github.com/kondaurov-scala/typeconfig.git</connection>
          <tag>1.0.0</tag>
        </scm>
        <developers>
          <developer>
            <id>kondaurovdev</id>
            <name>Alexander Kondaurov</name>
            <email>kondaurov.dev@gmail.com</email>
          </developer>
        </developers>,
    pomIncludeRepository := { _ => false }
  )