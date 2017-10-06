package com.monkeynuthead.keys

import org.scalajs.dom
import utest._

object KeysTest extends TestSuite {

  //Initialize App
  KeysApp.setupUI()

  def tests = TestSuite {
    'HasC4 {
      assert(dom.document.querySelectorAll("#C4").length == 1)
    }

    'HasFSharp4 {
      assert(dom.document.querySelectorAll("#FSharp4").length == 1)
    }
  }

}
