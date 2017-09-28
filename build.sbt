enablePlugins(ScalaJSPlugin)

name := "Keys"
scalaVersion := "2.12.3" // or any other Scala version >= 2.10.2

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.1",
  "be.doeraene" %%% "scalajs-jquery" % "0.9.1"
)

skip in packageJSDependencies := false
jsDependencies +=
  "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"
jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()