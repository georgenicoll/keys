enablePlugins(ScalaJSPlugin)

name := "Keys"
scalaVersion := "2.12.3" // or any other Scala version >= 2.10.2

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.2",
  "com.lihaoyi" %%% "scalatags" % "0.6.2",
  "com.github.japgolly.scalacss" %%% "core" % "0.5.3",
  "com.github.japgolly.scalacss" %%% "ext-scalatags" % "0.5.3",
  "org.monkeynuthead" %%% "scalajs-tone" % "1.0.0-SNAPSHOT",
  "com.lihaoyi" %%% "utest" % "0.4.4" % "test"
)

skip in packageJSDependencies := false

//jsDependencies +=
//  "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"

jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()

testFrameworks += new TestFramework("utest.runner.Framework")