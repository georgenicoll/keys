package com.monkeynuthead.keys

import org.scalajs.jquery.jQuery

object KeysApp {

  val Body = "body"
  val Click = "Click Me!"
  val Clicked = "Clicked Keys!"
  val Hello = "Hello, Keys!"

  def appendPar(selector: String, text: String): Unit = {
    jQuery(selector).append(s"<p>$text</p>")
  }

  def addClickedMessage(): Unit = {
    appendPar(Body, Clicked)
  }

  def setupUI(): Unit = {
    jQuery(s"""<button type="button">$Click</button>""")
      .click(addClickedMessage _)
      .appendTo(jQuery(Body))
    appendPar(Body, Hello)
  }

  def main(args: Array[String]): Unit = {
    jQuery(() => setupUI())
  }

}
