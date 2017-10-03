package com.monkeynuthead.keys

import org.scalajs.dom.raw.HTMLStyleElement

import scalacss.DevDefaults._
import scalatags.JsDom.TypedTag

object KeyStyles extends StyleSheet.Inline {

  import dsl._

  val common = mixin(
    backgroundColor.coral
  )

  val keyboard = style(
    common
  )

  val whiteNote = style(
    common,
    backgroundColor.white,
    color.black
  )

  val blackNote = style(
    common,
    backgroundColor.black,
    color.white
  )

  def renderToHtmlElement: HTMLStyleElement = {
    KeyStyles.render[HTMLStyleElement]
  }

}
