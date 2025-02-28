error id: arrTime
file://<WORKSPACE>/src/main/scala/org/ntic/flights/data/Row.scala
empty definition using pc, found symbol in pc: 
semanticdb not found
found definition using fallback; symbol arrTime
Document text:

```scala
package org.ntic.flights.data

import scala.util.Try

/**
 * This class is used to represent a row of the flights data. It contains the following fields:
 * @param flDate: String
 * @param originAirportId: Long
 * @param origin: String
 * @param originCityName: String
 * @param originStateAbr: String
 * @param destAirportId: Long
 * @param dest: String
 * @param destCityName: String
 * @param destStateAbr: String
 * @param depTime: String
 * @param depDelay: Double
 * @param arrTime: String
 * @param arrDelay: Double
 */
case class Row (
  flDate: String,
  originAirportId: Long,
  origin: String,
  originCityName: String,
  originStateAbr: String,
  destAirportId: Long,
  dest: String,
  destCityName: String,
  destStateAbr: String,
  depTime: String,
  depDelay: Double,
  arrTime: String,
  arrDelay: Double
) extends Ordered[Flight] {
  
  override def compare(that: Flight): Int = this.arrTime.compare(that.arrTime)
}

object Row {
  /**
   * This method is used to create a Row object from a list of tokens. It returns a Try[Row] object.
   * If the list of tokens is not valid or any of the token is invalid, it returns a Failure object. Otherwise, it returns a Success object.
   *
   * @param tokens: Seq[String]
   * @return Try[Row]
   */
  def fromStringList(tokens: Seq[String]): Try[Row] = Try {
    if (tokens.length < 13) throw new IllegalArgumentException("Row must contain at least 13 fields")

    Row(
      flDate = tokens(0).trim,
      originAirportId = tokens(1).trim.toLong,
      origin = tokens(2).trim,
      originCityName = tokens(3).trim,
      originStateAbr = tokens(4).trim,
      destAirportId = tokens(5).trim.toLong,
      dest = tokens(6).trim,
      destCityName = tokens(7).trim,
      destStateAbr = tokens(8).trim,
      depTime = tokens(9).trim,
      depDelay = tokens(10).trim.toDouble,
      arrTime = tokens(11).trim,
      arrDelay = tokens(12).trim.toDouble
    )
  }.recoverWith {
    case e: Exception => Failure(new IllegalArgumentException(s"Error parsing row: ${e.getMessage}"))
  }
  
} 
```

#### Short summary: 

empty definition using pc, found symbol in pc: 