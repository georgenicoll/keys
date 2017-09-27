enablePlugins(ScalaJSPlugin)

name := "Keys"
scalaVersion := "2.12.3" // or any other Scala version >= 2.10.2

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.1"
)