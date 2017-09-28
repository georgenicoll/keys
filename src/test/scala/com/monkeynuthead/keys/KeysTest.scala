package com.monkeynuthead.keys

import org.scalajs.jquery.jQuery
import utest._

object KeysTest extends TestSuite {

  //Initialize App
  KeysApp.setupUI()

  def tests = TestSuite {
    'HelloKeys {
      assert(jQuery(s"p:contains('${KeysApp.Hello}')").length == 1)
    }

    'ButtonClick {
      def messageCount =
        jQuery(s"p:contains('${KeysApp.Clicked}')").length

      val button = jQuery(s"button:contains('${KeysApp.Click}')")
      assert(button.length == 1)
      assert(messageCount == 0)

      for (c <- 1 to 5) {
        button.click()
        assert(messageCount == c)
      }
    }
  }

}
