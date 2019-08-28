package com.xyz

import java.text.SimpleDateFormat
import java.util.Calendar

import scala.collection.mutable.ListBuffer

/**
  * Created by hzhsg on 2019/3/1.
  */
class HelloScala {
    def say(x:String): Unit={
      println("welcome to scala,"+x);
    }

  def main(args: Array[String]): Unit = {
      getBetweenDay("2018-01-23","2018-02-23").toList.foreach(e=>print(e));
  }

  def getBetweenDay(startDay: String, endDay: String): ListBuffer[String] = {
    val startData = new SimpleDateFormat("yyyy-MM-dd").parse(startDay); //定义起始日期
    val endData = new SimpleDateFormat("yyyy-MM-dd").parse(endDay); //定义结束日期

    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    var buffer = new ListBuffer[String]
    buffer += dateFormat.format(startData.getTime())
    val tempStart = Calendar.getInstance()

    tempStart.setTime(startData)
    tempStart.add(Calendar.DAY_OF_YEAR, 1)

    val tempEnd = Calendar.getInstance()
    tempEnd.setTime(endData)
    while (tempStart.before(tempEnd)) {
      // result.add(dateFormat.format(tempStart.getTime()))
      buffer += dateFormat.format(tempStart.getTime())
      tempStart.add(Calendar.DAY_OF_YEAR, 1)
    }
    buffer += dateFormat.format(endData.getTime())
  }
}
