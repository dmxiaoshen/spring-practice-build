package com.xyz

import java.text.SimpleDateFormat
import java.util.Calendar

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Main {

  def main(args: Array[String]): Unit = {
    //var dayList:ListBuffer[String] = getBetweenDay("2018-02-23","2018-02-27");
    //dayList.toList.foreach(e=>println(e))
    //getBetweenDay("2018-02-23","2018-02-23").toList.foreach(e=>println(e));
//    var day1:String="2019-05-03"
//    var day2:String="2019-04-03"
//    val startDayTime = new SimpleDateFormat("yyyy-MM-dd").parse(day1).getTime
//
//    val endDatTime = new SimpleDateFormat("yyyy-MM-dd").parse(day2).getTime
//    println(startDayTime==endDatTime)
//
//    var list:ListBuffer[String] = new ListBuffer[String];
//
//    if(startDayTime<endDatTime){
//      list = getBetweenDay(day1,day2);
//    }else if(startDayTime==endDatTime){
//      list.append(day1)
//    }else{
//      println("开始时间不能大于结束时间")
//    }
//    list.toList.foreach(e=>println(e))
//    var k = s"""select ID,
//       |  DOP_VISIT_VISITID,
//       |  DOP_VISIT_USERID,
//       |  DOP_VISIT_LOGGINGTIME,
//       |  DOP_VISIT_URL,
//       |  DOP_VISIT_REFERCHANNELCODE
//       |from
//       |  DOP_VISIT_INFO
//       |where
//       |  ID like '3'
//       |AND
//       |  DOP_VISIT_USERID IS NOT NULL
//       |AND
//       |  DOP_VISIT_REFERCHANNELCODE = '2'
//       |AND
//       |  (DOP_VISIT_URL = '0'
//       |OR
//       |  DOP_VISIT_URL = '8')
//       """;
//    println(k);
//
//    var urls:String = "http://baidu.com,http://google.com";
//    var buffer = new StringBuilder;
//    var urlArrays:Array[String] = urls.split(",");
//
//    urlArrays.foreach(e=>buffer++="DOP_VISIT_URL='"+e+"' OR ");
//
//    println("("+buffer.toString().substring(0,buffer.toString().lastIndexOf("OR"))+")")

    var h = new HelloJava();
    h.say();

    var day:String = "2019-03-23";

    println(caculateDay(day,Calendar.MONTH,-6))
    println(caculateDay(day,Calendar.MONTH,6))

    //var x = mutable.Map[String, Any]


    //x = x.++(JsonHelper.toObject("""{{"type":"公告","urls":"https://www.baidu.com"},{"type":"帮助","urls":"https://www.sina.com"}}""",classOf[mutable.Map[String, Any]]));
    //JsonHelper.toObject("""{{"type":"公告","urls":"https://www.baidu.com"},{"type":"帮助","urls":"https://www.sina.com"}}""").toString

    //var s = JsonPathHelper.getString("""{{"type1":"公告","urls":"https://www.baidu.com"},{"type":"帮助","urls":"https://www.sina.com"}}""","$.type1");
    //println(s)
    var baseField:String = "BBL";
    var channelCode:String = "xsk";
    var queryVisitDaysSql =
      s"""select
       |  ID
       |  DOP_USER_ID,
       |  DOP_USER_CHANNELCODE,
       |  SUM(DOP_USER_DAILY_COUNT_${baseField}${baseField}) AS DOP_USER_VISIT_NUM,
       |from
       |  DOP_USER_DAY_PAGE_DETAIL
       |where
       |  DOP_USER_CHANNELCODE = '${channelCode}'
       |GROUP BY
       |  DOP_USER_ID, DOP_USER_CHANNELCODE
       """.stripMargin

    println(queryVisitDaysSql)
  }

  def caculateDay(day:String,unitType:Integer,amount:Integer):String={
    var dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    var tempStart = Calendar.getInstance();
    tempStart.setTime(dateFormat.parse(day));
    tempStart.add(unitType,amount);
    dateFormat.format(tempStart.getTime)
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
