error id: scala/collection/StringOps#toDouble().
file://<WORKSPACE>/src/main/scala/org/ntic/flights/data/Row.scala
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol scala/collection/StringOps#toDouble().
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
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
          flDate = tokens(0),
          originAirportId = tokens(1).toInt,
          origin = tokens(2),
          originCityName = tokens(3),
          originStateAbr = tokens(4),
          destAirportId = tokens(5).toInt,
          dest = tokens(6),
          destCityName = tokens(7),
          destStateAbr = tokens(8),
          depTime = tokens(9),
          depDelay = tokens(10).toDouble,
          arrTime = tokens(11),
          arrDelay = tokens(12).toDouble
        )
      }.recoverWith {
        case ex: NumberFormatException => Failure(new IllegalArgumentException(s"Error parsing number in row: ${ex.getMessage}"))
        case ex: Exception => Failure(new RuntimeException(s"Unexpected error parsing row: ${ex.getMessage}"))
      }
    }
  }
}

```

#### Short summary: 

empty definition using pc, found symbol in pc: 