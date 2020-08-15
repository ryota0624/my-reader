package com.ryota0624.myreader

import java.io.BufferedReader

import scala.util.{Failure, Success, Try}

trait LineReadable {
  def readLine(): Try[String]
}

object LineReadable {
  implicit class bufferedReader(b: BufferedReader) extends LineReadable {
    override def readLine(): Try[String] =
      Try {
        val text = b.readLine()
        if (text == null)
          throw new java.io.EOFException("BufferedReader has not input")
        else
          text
      }
  }

  implicit class ReadableArray(original: Array[String]) extends LineReadable {
    private var arr = original

    override def readLine(): Try[String] =
      Try {
        if (arr.isEmpty) {
          throw new Exception("array is empty")
        }

        val head = arr.head
        arr = arr.tail
        head
      }
  }
}

class QuestionParameterReader(val readable: LineReadable) {
  def readTextsByHeadNumber(): Try[Array[String]] =
    Try {
      readable
        .readLine()
        .map(_.toInt)
        .map(count =>
          Array
            .range(0, count)
            .map(_ =>
              readable.readLine() match {
                case Failure(exception) => throw exception
                case Success(value)     => value
              }
            )
        )
    }.flatten
}
