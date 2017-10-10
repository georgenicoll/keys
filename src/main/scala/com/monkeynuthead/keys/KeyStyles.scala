package com.monkeynuthead.keys

import org.scalajs.dom.raw.HTMLStyleElement

import scalacss.DevDefaults._
import scala.language.postfixOps

object KeyStyles extends StyleSheet.Inline {

  private val WhiteWidth = 40
  private val WhiteHeight = 200
  private val BlackWidth = 20
  private val BlackHeight = 160
  private val HalfBlackWidth = BlackWidth / 2 + 1 //plus 1 - used by white shapes
  private val WhiteWidthMinusHalfBlackWidth = WhiteWidth - HalfBlackWidth //-1 used by white shapes

  import dsl._

  val SvgViewBox = "0,0 1200,400"

  val Keyboard = style(
    height(200 px),
    width(600 px),
  )

  val NoteCommon = style(
    borderStyle.solid,
    borderColor.darkgray,
    borderWidth(1 px),
    float.left
  )

  def WhiteNotePoints(offsetX: Int): (String, Int) = {
    val points = s"$offsetX,0 ${offsetX + WhiteWidth},0 ${offsetX + WhiteWidth},$WhiteHeight $offsetX,$WhiteHeight"
    (points, offsetX + WhiteWidth)
  }

  def WhiteNoteAfterWhitePoints(offsetX: Int): (String, Int) = {
    val points = s"$offsetX,0 " +
      s"${offsetX + WhiteWidthMinusHalfBlackWidth},0 " +
      s"${offsetX + WhiteWidthMinusHalfBlackWidth},$BlackHeight " +
      s"${offsetX + WhiteWidth},$BlackHeight " +
      s"${offsetX + WhiteWidth},$WhiteHeight " +
      s"$offsetX,$WhiteHeight"
    (points, offsetX + WhiteWidth)
  }

  def WhiteNoteAfterBlackPoints(offsetX: Int): (String, Int) = {
    val points = s"${offsetX + HalfBlackWidth},0 " +
      s"${offsetX + HalfBlackWidth},$BlackHeight " +
      s"$offsetX,$BlackHeight " +
      s"$offsetX,$WhiteHeight " +
      s"${offsetX + WhiteWidth},$WhiteHeight " +
      s"${offsetX + WhiteWidth},0"
    (points, offsetX + WhiteWidth)
  }

  def BlackNotePoints(offsetX: Int): (String, Int) = {
    val points = s"${offsetX - HalfBlackWidth},0 " +
      s"${offsetX - HalfBlackWidth + BlackWidth},0 " +
      s"${offsetX - HalfBlackWidth + BlackWidth},$BlackHeight " +
      s"${offsetX - HalfBlackWidth},$BlackHeight"
    (points, offsetX)
  }

  val WhiteNote = style(
    NoteCommon,
    svgFill := "ivory",
    svgStroke.black,
    svgStrokeWidth := "2"
  )

  val WhiteNoteAfterWhite = style(
    WhiteNote
  )

  val WhiteNoteAfterBlack = style(
    WhiteNote
  )

  val BlackNote = style(
    NoteCommon,
    svgFill := "black",
    svgStroke.black,
    svgStrokeWidth := "2"
  )

  def renderToHtmlElement: HTMLStyleElement = {
    KeyStyles.render[HTMLStyleElement]
  }

}
