package com.xyz

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

import io.gatling.jsonpath.{JPError, JsonPath}

object JsonPathHelper {

  def compile(jsonStr: String): Object = {
    (new ObjectMapper() with ScalaObjectMapper)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      .configure(JsonParser.Feature.ALLOW_COMMENTS, true)
      .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
      .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
      .configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true)
      .readValue(jsonStr, classOf[Object])
  }

  def query(jsonNode: Object, xpath: String): Either[JPError, Iterator[Any]] = {
    /**
      * BSON不支持$和.
      */
    if (xpath.startsWith("$")) {
      JsonPath.query(xpath.replaceAll("#", "."), jsonNode)
    } else {
      JsonPath.query("$." + xpath.replaceAll("#", "."), jsonNode)
    }
  }

  def isExist(jsonNode: Object, xpath: String): Boolean = {
    val result = query(jsonNode, xpath)
    result.right.get.nonEmpty
  }

  private def getList(jsonNode: Object, xpath: String): Option[List[_]] = {
    val result = query(jsonNode, xpath)
    if (result.isRight && result.right.get.nonEmpty) {
      Some(result.right.get.toList)
    } else {
      None
    }
  }

  implicit def getObjectNodeOrArrayNode(jsonNode: Object, xpath: String): Option[Object] = {
    val array = getList(jsonNode, xpath)
    array match {
      case v: Some[List[_]] =>
        if (v.get.isEmpty) {
          None
        } else {
          Some(compile(JsonHelper.toJsonString(v.get.head)))
        }
      case _ =>
        None
    }
  }

  implicit def getString(jsonNode: Object, xpath: String): Option[String] = {
    val array = getList(jsonNode, xpath)
    array match {
      case v: Some[List[_]] =>
        Some(v.get.mkString("_&#_"))
      case _ =>
        None
    }
  }

  implicit def getInt(jsonNode: Object, xpath: String): Option[Int] = {
    construct(jsonNode, xpath, v => v.toInt)
  }

  implicit def getDouble(jsonNode: Object, xpath: String): Option[Double] = {
    construct(jsonNode, xpath, v => v.toDouble)
  }

  implicit def getFloat(jsonNode: Object, xpath: String): Option[Float] = {
    construct(jsonNode, xpath, v => v.toFloat)
  }

  implicit def getLong(jsonNode: Object, xpath: String): Option[Long] = {
    construct(jsonNode, xpath, v => v.toLong)
  }


  private def construct[T](jsonNode: Object, xpath: String, option: String => T): Option[T] = {
    getString(jsonNode, xpath) match {
      case v: Some[String] =>
        Some(option(v.get))
      case _ =>
        None
    }
  }
}
