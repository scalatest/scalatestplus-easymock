# scalatestplus-easymock
 ScalaTest + EasyMock provides integration support between ScalaTest and EasyMock.

 **Usage**

To use it for ScalaTest 3.2.13 and EasyMock 4.3.x: 

SBT: 

```
libraryDependencies += "org.scalatestplus" %% "easymock-4-3" % "3.2.13.0" % "test"
```

Maven: 

```
<dependency>
  <groupId>org.scalatestplus</groupId>
  <artifactId>easymock-4-3_2.13</artifactId>
  <version>3.2.13.0</version>
  <scope>test</scope>
</dependency>
```

**Publishing**

Please use the following commands to publish to Sonatype: 

```
$ sbt +publishSigned
```
