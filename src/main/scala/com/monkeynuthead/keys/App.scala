package com.monkeynuthead.keys

import org.scalajs.jquery.jQuery

object App {

  def appendPar(selector: String, text: String): Unit = {
    jQuery(selector).append(s"<p>$text</p>")
  }

  def addClickedMessage(): Unit = {
    appendPar("body", "Clicked Keys!")
  }

  def setupUI(): Unit = {
    jQuery("#click-me-button").click(() => addClickedMessage())
    appendPar("body", "Hello, Keys!")
  }

  def main(args: Array[String]): Unit = {
    jQuery(() => setupUI())
  }

}
