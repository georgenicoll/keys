package com.monkeynuthead.keys

import org.scalajs.dom

import scalacss.ScalatagsCss._
import scalatags.JsDom.all._
import scalatags.JsDom.svgTags.{svg,polygon}
import scalatags.JsDom.svgAttrs.{points}

object KeysApp {

  private case class Note(name: String, idPrefix: String, style: Modifier, points: (Int) => (String, Int)) {

    def id(octave: Integer): String = idPrefix + octave

  }

  private val Notes = List(
    Note("C", "C", KeyStyles.WhiteNoteAfterWhite, KeyStyles.WhiteNoteAfterWhitePoints),
    Note("C#", "CSharp", KeyStyles.BlackNote, KeyStyles.BlackNotePoints),
    Note("D", "D", KeyStyles.WhiteNoteAfterBlack, KeyStyles.WhiteNoteAfterBlackPoints),
    Note("Eb", "EFlat", KeyStyles.BlackNote, KeyStyles.BlackNotePoints),
    Note("E", "E", KeyStyles.WhiteNoteAfterBlack, KeyStyles.WhiteNoteAfterBlackPoints),
    Note("F", "F", KeyStyles.WhiteNoteAfterWhite, KeyStyles.WhiteNoteAfterWhitePoints),
    Note("F#", "FSharp", KeyStyles.BlackNote, KeyStyles.BlackNotePoints),
    Note("G", "G", KeyStyles.WhiteNoteAfterBlack, KeyStyles.WhiteNoteAfterBlackPoints),
    Note("Ab", "AFlat", KeyStyles.BlackNote, KeyStyles.BlackNotePoints),
    Note("A", "A", KeyStyles.WhiteNoteAfterBlack, KeyStyles.WhiteNoteAfterBlackPoints),
    Note("Bb", "BFlat", KeyStyles.BlackNote, KeyStyles.BlackNotePoints),
    Note("B", "B", KeyStyles.WhiteNoteAfterBlack, KeyStyles.WhiteNoteAfterBlackPoints)
  )

  private def noteMouseDown(octave: Integer, note: Note)(e: dom.MouseEvent): Unit = {
    println(s"MouseDown ${note.name}$octave [#${note.id(octave)}]")
  }

  private def noteMouseUp(octave: Integer, note: Note)(e: dom.MouseEvent): Unit = {
    println(s"MouseUp ${note.name}$octave [#${note.id(octave)}]")
  }

  private def noteMouseOut(octave: Integer, note: Note)(e: dom.MouseEvent): Unit = {
    println(s"MouseOut ${note.name}$octave [#${note.id(octave)}]")
  }

  private def createNote(offset: Int, octave: Int, note: Note): (Modifier, Int) = {
    val (calculatedPoints, newOffset) = note.points(offset)
    val modifier = polygon(
      id := note.id(octave),
      onmousedown := noteMouseDown(octave, note) _,
      onmouseup := noteMouseUp(octave, note) _,
      onmouseout := noteMouseOut(octave, note) _,
      note.style,
      points := calculatedPoints,
      note.name
    )
    (modifier, newOffset)
  }

  private def createKeys(): Seq[Modifier] = {
    val octaveNotes = for (
      octave <- 4 to 5;
      note <- Notes
    ) yield (octave, note)
    octaveNotes.foldLeft((0, Seq.newBuilder[Modifier])) {
      case ((offset, builder), (octave, note)) =>
        val (modifier, newOffset) = createNote(offset, octave, note)
        (newOffset, builder += modifier)
    }._2.result()
  }

  private[keys] def setupUI(): Unit = {
    dom.document.body.appendChild(KeyStyles.renderToHtmlElement.render)

    val keyboard = svg(
      createKeys():_*,
    )(KeyStyles.Keyboard)
    dom.document.body.appendChild(keyboard.render)
  }

  def main(args: Array[String]): Unit = {
    dom.window.onload = (e: dom.Event) => setupUI()
  }

}
