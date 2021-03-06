package com.monkeynuthead.keys

import org.monkeynuthead.scalajs.tone.Tone
import org.scalajs.dom

import scalacss.ScalatagsCss._
import scalatags.JsDom.all._
import scalatags.JsDom.svgTags.{polygon, svg}
import scalatags.JsDom.svgAttrs.points

object KeysApp {

  private case class NoteConfig(name: String, idPrefix: String, style: Modifier, points: (Int) => (String, Int)) {

    def id(octave: Integer): String = name + octave

  }

  private val Notes = List(
    NoteConfig("C", "C", KeyStyles.WhiteNoteAfterWhite, KeyStyles.WhiteNoteAfterWhitePoints),
    NoteConfig("C#", "CSharp", KeyStyles.BlackNote, KeyStyles.BlackNotePoints),
    NoteConfig("D", "D", KeyStyles.WhiteNoteAfterBlack, KeyStyles.WhiteNoteAfterBlackPoints),
    NoteConfig("Eb", "EFlat", KeyStyles.BlackNote, KeyStyles.BlackNotePoints),
    NoteConfig("E", "E", KeyStyles.WhiteNoteAfterBlack, KeyStyles.WhiteNoteAfterBlackPoints),
    NoteConfig("F", "F", KeyStyles.WhiteNoteAfterWhite, KeyStyles.WhiteNoteAfterWhitePoints),
    NoteConfig("F#", "FSharp", KeyStyles.BlackNote, KeyStyles.BlackNotePoints),
    NoteConfig("G", "G", KeyStyles.WhiteNoteAfterBlack, KeyStyles.WhiteNoteAfterBlackPoints),
    NoteConfig("Ab", "AFlat", KeyStyles.BlackNote, KeyStyles.BlackNotePoints),
    NoteConfig("A", "A", KeyStyles.WhiteNoteAfterBlack, KeyStyles.WhiteNoteAfterBlackPoints),
    NoteConfig("Bb", "BFlat", KeyStyles.BlackNote, KeyStyles.BlackNotePoints),
    NoteConfig("B", "B", KeyStyles.WhiteNoteAfterBlack, KeyStyles.WhiteNoteAfterBlackPoints)
  )

  private case class Note(id: String, octave: Integer, config: NoteConfig)

  private def noteMouseDown(playNote: String => Unit, note: Note)(e: dom.MouseEvent): Unit = {
    println(s"MouseDown ${note.id}")
    playNote(note.id)
  }

  private def noteMouseUp(releaseNote: String => Unit, note: Note)(e: dom.MouseEvent): Unit = {
    println(s"MouseUp ${note.id}")
    releaseNote(note.id)
  }

  private def noteMouseOut(releaseNote: String => Unit, note: Note)(e: dom.MouseEvent): Unit = {
    println(s"MouseOut ${note.id}")
    releaseNote(note.id)
  }

  private def createNote(playNote: String => Unit, releaseNote: String => Unit,
                         offset: Int, octave: Int, noteConfig: NoteConfig): (Modifier, Int) = {
    val (calculatedPoints, newOffset) = noteConfig.points(offset)
    val note = Note(noteConfig.id(octave), octave, noteConfig)
    val modifier = polygon(
      id := note.id,
      onmousedown := noteMouseDown(playNote, note) _,
      onmouseup := noteMouseUp(releaseNote, note) _,
      onmouseout := noteMouseOut(releaseNote, note) _,
      note.config.style,
      points := calculatedPoints,
      note.id
    )
    (modifier, newOffset)
  }

  private def createKeys(playNote: String => Unit, releaseNote: String => Unit): Seq[Modifier] = {
    val octaveNotes = for (
      octave <- 4 to 5;
      note <- Notes
    ) yield (octave, note)
    octaveNotes.foldLeft((0, Seq.newBuilder[Modifier])) {
      case ((offset, builder), (octave, note)) =>
        val (modifier, newOffset) = createNote(playNote, releaseNote, offset, octave, note)
        (newOffset, builder += modifier)
    }._2.result()
  }

  private[keys] def setupUI(): Unit = {
    dom.document.body.appendChild(KeyStyles.renderToHtmlElement.render)

    val instrument = new Tone.Synth().toMaster()
    var playingNotes: Set[String] = Set.empty
    val playNote = (note: String) => {
      playingNotes = playingNotes + note
      instrument.triggerAttack(note)
    }
    val releaseNote = (note: String) => if (playingNotes.contains(note)) {
      playingNotes = playingNotes - note
      instrument.triggerRelease()
    }

    val keyboard = svg(
      createKeys(playNote, releaseNote):_*,
    )(KeyStyles.Keyboard)
    dom.document.body.appendChild(keyboard.render)
  }

  def main(args: Array[String]): Unit = {
    dom.window.onload = (e: dom.Event) => setupUI()
  }

}
