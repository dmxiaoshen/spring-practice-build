package com.xyz

import java.util.TimeZone

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.node.{ArrayNode, MissingNode, ObjectNode}
import com.fasterxml.jackson.databind.{DeserializationFeature, JsonNode, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

/**
  * Scala版本的Json辅助类<br/>
  * 使用<i>jackson-module-scala</i>封装
  */
object JsonHelper extends Serializable {

  private val mapper = new ObjectMapper() with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true)
  mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
  mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
  mapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true)
  def setTimeZone(tz: TimeZone): Unit = {
    mapper.setTimeZone(tz)
  }

  /**
    * object 转 json字符串
    *
    * @param obj object
    * @return json字符串
    */
  def toJsonString(obj: Any): String = {
    mapper.writeValueAsString(obj)
  }

  /**
    * object 转 json
    *
    * @param obj object
    * @return json
    */
  def toJson(obj: Any): JsonNode = {
    obj match {
      case o: String => mapper.readTree(o)
      case _ => mapper.valueToTree(obj)
    }
  }

  /**
    * json或string 转 object
    *
    * @param obj json或string
    * @return object
    */
  def toObject[E](obj: Any, clazz: Class[E]): E = {
    try {
      obj match {
        case o: String =>
          clazz match {
            case c if c == classOf[String] =>
              o.asInstanceOf[E]
            case c if c == classOf[Int] =>
              o.toInt.asInstanceOf[E]
            case c if c == classOf[Long] =>
              o.toLong.asInstanceOf[E]
            case c if c == classOf[Double] =>
              o.toDouble.asInstanceOf[E]
            case c if c == classOf[Float] =>
              o.toFloat.asInstanceOf[E]
            case c if c == classOf[Boolean] =>
              o.toBoolean.asInstanceOf[E]
            case c if c == classOf[Byte] =>
              o.toByte.asInstanceOf[E]
            case c if c == classOf[Short] =>
              o.toShort.asInstanceOf[E]
            case c if c == classOf[Void] =>
              null.asInstanceOf[E]
            case _ =>
              mapper.readValue[E](o, clazz)
          }
        case o: JsonNode => mapper.readValue(o.toString, clazz)
        case _ => mapper.readValue(mapper.writeValueAsString(obj), clazz)
      }
    } catch {
      case e: Throwable =>
        //        logger.error(s"Parsing to [${clazz.getName}] error , source is :${obj.toString}")
        throw e
    }
  }

  /**
    * json或string 转 generic object
    */
  def toObject[E](obj: Any)(implicit m: Manifest[E]): E = {
    try {
      obj match {
        case o: String => mapper.readValue[E](o)
        case o: JsonNode => mapper.readValue[E](o.toString)
        case _ => mapper.readValue[E](mapper.writeValueAsString(obj))
      }
    } catch {
      case e: Throwable =>
        //        logger.error(s"Parsing to [${m.toString()}] error , source is :${obj.toString}")
        throw e
    }
  }

  def path(jsonNode: JsonNode, pathStr: String): JsonNode = {
    val splitPaths = pathStr.split("\\.")
    val currJsonNode = jsonNode.path(splitPaths(0))
    if (currJsonNode.isInstanceOf[MissingNode]) {
      null
    } else if (splitPaths.length == 1) {
      currJsonNode
    } else {
      path(currJsonNode, pathStr.substring(pathStr.indexOf(".") + 1))
    }
  }

  def createObjectNode(): ObjectNode = {
    mapper.createObjectNode()
  }

  def createArrayNode(): ArrayNode = {
    mapper.createArrayNode()
  }

  def getMapper: ObjectMapper = {
    mapper
  }

  /**
    * 深度Copy
    *
    * 除数组类型外，source 会覆盖 target
    *
    * @param target 目标Json
    * @param source 需要用于覆盖的Json
    */
  def deepCopy(target: JsonNode, source: JsonNode): Unit = {
    val it = source.fields()
    while (it.hasNext) {
      val item = it.next()
      if (!target.has(item.getKey) || !item.getValue.fieldNames().hasNext) {
        target.asInstanceOf[ObjectNode].set(item.getKey, item.getValue)
      } else {
        deepCopy(target.get(item.getKey), item.getValue)
      }
    }
  }

}
