package com.monkeynuthead.keys

import org.scalajs.dom
import org.scalajs.dom.html
import utest._

object KeysTest extends TestSuite {

  //Initialize App
  KeysApp.setupUI()

  def tests = TestSuite {
    'HelloKeys {
      assert(dom.document.querySelectorAll(s"p.${KeysApp.HelloClass}").length == 1)
    }

    'ButtonClick {
      def messageCount =
        dom.document.querySelectorAll(s"p.${KeysApp.ClickedClass}").length

      val button = dom.document.querySelector(s"button#${KeysApp.ClickButtonId}").asInstanceOf[html.Button]
      assert(button != null)
      assert(messageCount == 0)

      for (c <- 1 to 5) {
        button.click()
        assert(messageCount == c)
      }
    }
  }

}
