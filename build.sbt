name := "easymock-3.2"

organization := "org.scalatestplus"

version := "3.1.0.1"

homepage := Some(url("https://github.com/scalatest/scalatestplus-easymock"))

licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

developers := List(
  Developer(
    "bvenners",
    "Bill Venners",
    "bill@artima.com",
    url("https://github.com/bvenners")
  ),
  Developer(
    "cheeseng",
    "Chua Chee Seng",
    "cheeseng@amaseng.com",
    url("https://github.com/cheeseng")
  )
)

crossScalaVersions := List("2.10.7", "2.11.12", "2.12.10", "2.13.1")

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  "org.easymock" % "easymockclassextension" % "3.2",
  "org.scalatest" %% "scalatest" % "3.1.0"
)

import scala.xml.{Node => XmlNode, NodeSeq => XmlNodeSeq, _}
import scala.xml.transform.{RewriteRule, RuleTransformer}

// skip dependency elements with a scope
pomPostProcess := { (node: XmlNode) =>
  new RuleTransformer(new RewriteRule {
    override def transform(node: XmlNode): XmlNodeSeq = node match {
      case e: Elem if e.label == "dependency"
          && e.child.exists(child => child.label == "scope") =>
        def txt(label: String): String = "\"" + e.child.filter(_.label == label).flatMap(_.text).mkString + "\""
        Comment(s""" scoped dependency ${txt("groupId")} % ${txt("artifactId")} % ${txt("version")} % ${txt("scope")} has been omitted """)
      case _ => node
    }
  }).transform(node).head
}

enablePlugins(SbtOsgi)

osgiSettings

OsgiKeys.exportPackage := Seq(
  "org.scalatestplus.easymock.*"
)

OsgiKeys.importPackage := Seq(
  "org.scalatest.*",
  "org.scalactic.*", 
  "scala.*;version=\"$<range;[==,=+);$<replace;"+scalaBinaryVersion.value+";-;.>>\"",
  "*;resolution:=optional"
)

OsgiKeys.additionalHeaders:= Map(
  "Bundle-Name" -> "ScalaTestPlusEasyMock",
  "Bundle-Description" -> "ScalaTest+EasyMock is an open-source integration library between ScalaTest and EasyMock for Scala projects.",
  "Bundle-DocURL" -> "http://www.scalatest.org/",
  "Bundle-Vendor" -> "Artima, Inc."
)

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  Some("publish-releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

pgpSecretRing := file((Path.userHome / ".gnupg" / "secring.gpg").getAbsolutePath)

pgpPassphrase := None
