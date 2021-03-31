# scalatestplus-easymock
 ScalaTest + EasyMock provides integration support between ScalaTest and EasyMock.

 **Usage**

To use it for ScalaTest 3.2.7 and EasyMock 3.2.x: 

SBT: 

```
libraryDependencies += "org.scalatestplus" %% "easymock-3-2" % "3.2.7.0" % "test"
```

Maven: 

```
<dependency>
  <groupId>org.scalatestplus</groupId>
  <artifactId>easymock-3-2_2.13</artifactId>
  <version>3.2.7.0</version>
  <scope>test</scope>
</dependency>
```

**Publishing**

Please use the following commands to publish to Sonatype: 

```
$ sbt +publishSigned
```
