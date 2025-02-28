package org.ntic.flights.data

import scala.util.{Try, Failure, Success}

case class Row(
  flDate: String,
  originAirportId: Int,
  origin: String,
  originCityName: String,
  originStateAbr: String,
  destAirportId: Int,
  dest: String,
  destCityName: String,
  destStateAbr: String,
  depTime: String,
  depDelay: Double,
  arrTime: String,
  arrDelay: Double
)

object Row {
  def fromStringList(tokens: Seq[String]): Try[Row] = {
    if (tokens.length != 13) {
      Failure(new ArrayIndexOutOfBoundsException(s"${tokens.length}"))
    } else {
      Try {
        Row(
          flDate = tokens(0).trim,
          originAirportId = tokens(1).trim.toInt,
          origin = tokens(2).trim,
          originCityName = tokens(3).trim,
          originStateAbr = tokens(4).trim,
          destAirportId = tokens(5).trim.toInt,
          dest = tokens(6).trim,
          destCityName = tokens(7).trim,
          destStateAbr = tokens(8).trim,
          depTime = tokens(9).trim,
          depDelay = tokens(10).trim.toDouble,
          arrTime = tokens(11).trim,
          arrDelay = tokens(12).trim.toDouble
        )
      }.recoverWith {
        case ex: NumberFormatException => Failure(new IllegalArgumentException(s"Error parsing number in row: ${ex.getMessage}"))
        case ex: Exception => Failure(new RuntimeException(s"Unexpected error parsing row: ${ex.getMessage}"))
      }
    }
  }
}
