package com.monkeynuthead.keys

import org.scalajs.dom
import org.scalajs.dom.Element

import scalatags.JsDom.all._

object KeysApp {

  val ClickButtonId = "ClickButton"
  val Click = "Click Me!"
  val ClickedClass = "Clicked"
  val Clicked = "Clicked Keys!"
  val HelloClass = "Hello"
  val Hello = "Hello, Keys!"

  def appendPar(parent: Element, className: String, text: String): Unit = {
    val par = p(text).render
    par.className = className
    parent.appendChild(par)
  }

  def addClickedMessage(e: dom.MouseEvent): Unit = {
    appendPar(dom.document.body, ClickedClass, Clicked)
  }

  def setupUI(): Unit = {
    val clickButton = button(Click).render
    clickButton.id = ClickButtonId
    clickButton.onclick = addClickedMessage
    dom.document.body.appendChild(clickButton)

    appendPar(dom.document.body, HelloClass, Hello)
  }

  def main(args: Array[String]): Unit = {
    dom.window.onload = (e: dom.Event) => setupUI()
  }

}
