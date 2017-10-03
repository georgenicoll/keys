package com.monkeynuthead.keys

import org.scalajs.dom
import org.scalajs.dom.raw.MouseEvent

import scalacss.ScalatagsCss._
import scalatags.JsDom.all._

object KeysApp {

  private case class Note(name: String, idPrefix: String, style: Modifier) {

    def id(octave: Integer): String = idPrefix + octave

  }

  private val Notes = List(
    Note("C", "C", KeyStyles.whiteNote),
    Note("C#", "CSharp", KeyStyles.blackNote),
    Note("D", "D", KeyStyles.whiteNote),
    Note("Eb", "EFlat", KeyStyles.blackNote),
    Note("E", "E", KeyStyles.whiteNote),
    Note("F", "F", KeyStyles.whiteNote),
    Note("F#", "FSharp", KeyStyles.blackNote),
    Note("G", "G", KeyStyles.whiteNote),
    Note("Ab", "AFlat", KeyStyles.blackNote),
    Note("A", "A", KeyStyles.whiteNote),
    Note("Bb", "BFlat", KeyStyles.blackNote),
    Note("B", "B", KeyStyles.whiteNote),
  )

  private def clickNote(octave: Integer, note: Note)(e: MouseEvent): Unit = {
    dom.window.alert(s"Clicked ${note.name}$octave [#${note.id(octave)}]")
  }

  private def createNote(octave: Integer, note: Note): Modifier = {
    div(
      note.style,
      note.name,
      id := note.id(octave),
      onclick := clickNote(octave, note) _
    )
  }

  private def createKeys(): Seq[Modifier] = {
    for (
      octave <- 4 to 4;
      note <- Notes
    ) yield createNote(octave, note)
  }

  private[keys] def setupUI(): Unit = {
    dom.document.body.appendChild(KeyStyles.renderToHtmlElement.render)

    val keyboard = div(
      KeyStyles.keyboard,
      createKeys()
    ).render
    dom.document.body.appendChild(keyboard)
  }

  def main(args: Array[String]): Unit = {
    dom.window.onload = (e: dom.Event) => setupUI()
  }

}
