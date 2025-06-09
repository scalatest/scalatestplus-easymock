# scalatestplus-easymock
 ScalaTest + EasyMock provides integration support between ScalaTest and EasyMock.

 💖 Support ScalaTest
--------------------

[![Sponsor ScalaTest](https://img.shields.io/badge/sponsor-scalatest-ff69b4?logo=github-sponsors)](https://github.com/sponsors/scalatest)

ScalaTest has been a cornerstone of testing in the Scala ecosystem for over 17 years. It’s trusted by countless developers and teams to write expressive, flexible, and robust tests. We’ve always believed in keeping ScalaTest free and open source, but maintaining a tool used so widely takes time, care, and ongoing effort.

If ScalaTest has saved you time, helped you ship better software, or become a key part of your development workflow, please consider supporting our work. Your sponsorship helps us dedicate time to fixing bugs, improving documentation, adding new features, and keeping ScalaTest reliable for the entire community.

👉 [Become a sponsor for ScalaTest](https://github.com/sponsors/scalatest) to help keep Scala’s most widely used testing library thriving!

 **Usage**

To use it for ScalaTest 3.2.19 and EasyMock 5.3.x: 

SBT: 

```
libraryDependencies += "org.scalatestplus" %% "easymock-5-3" % "3.2.19.0" % "test"
```

Maven: 

```
<dependency>
  <groupId>org.scalatestplus</groupId>
  <artifactId>easymock-5-3_3</artifactId>
  <version>3.2.19.0</version>
  <scope>test</scope>
</dependency>
```

**Publishing**

Please use the following commands to publish to Sonatype: 

```
$ sbt +publishSigned
```
