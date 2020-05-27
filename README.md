# scalatestplus-easymock
 ScalaTest + EasyMock provides integration support between ScalaTest and EasyMock.

 **Usage**

To use it for ScalaTest 3.1.2 and EasyMock 3.2.x: 

SBT: 

```
libraryDependencies += "org.scalatestplus" %% "easymock-3-2" % "3.1.2.0" % "test"
```

Maven: 

```
<dependency>
  <groupId>org.scalatestplus</groupId>
  <artifactId>easymock-3-2</artifactId>
  <version>3.1.2.0</version>
  <scope>test</scope>
</dependency>
```

**Publishing**

Please use the following commands to publish to Sonatype: 

```
$ sbt +publishSigned
```
