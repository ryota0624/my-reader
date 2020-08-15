package com.ryota0624.myreader

import com.ryota0624.myreader.LineReadable.ReadableArray
import com.ryota0624.myreader.QuestionParameterReader

import scala.util.Success
import org.scalatest.TryValues._

class ReadableArraySpec extends BaseSpec {
  "readText" should {
    "return array head" in {
      val arr = new ReadableArray(Array("1", "2", "3"))
      arr.readLine() mustBe Success("1")
      arr.readLine() mustBe Success("2")
      arr.readLine() mustBe Success("3")
    }

    "fail if array is empty " in {
      val arr = new ReadableArray(Array.empty)
      arr.readLine().isFailure mustBe true
    }
  }
}

class ReaderUtilsSpec extends BaseSpec {
  "readTextsByHeadNumber" should {
    "fail if first text is not number " in {
      val reader = new QuestionParameterReader(Array("a"))
      reader.readTextsByHeadNumber().isFailure mustBe true
    }

    "return tail element" in {
      val reader = new QuestionParameterReader(Array("2", "text", "some"))
      reader.readTextsByHeadNumber().success.value must be(
        Array("text", "some")
      )
    }
  }
}
