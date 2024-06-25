# scalatestplus-easymock
 ScalaTest + EasyMock provides integration support between ScalaTest and EasyMock.

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
