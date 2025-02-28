package org.ntic.flights.data

import org.ntic.flights.FlightsLoaderConfig

/**
 * This class is used to represent a flight with its information like the date, origin, destination, scheduled departure time,
 * scheduled arrival time, departure delay and arrival delay.
 *
 * @param flDate: String
 * @param origin: Airport
 * @param dest: Airport
 * @param scheduledDepTime: Time
 * @param scheduledArrTime: Time
 * @param depDelay: Double
 * @param arrDelay: Double
 */
@SerialVersionUID(1L)
case class Flight(
  flDate: String,
  origin: Airport,
  dest: Airport,
  scheduledDepTime: Time,
  scheduledArrTime: Time,
  depDelay: Double,
  arrDelay: Double
) extends Ordered[Flight] with Serializable {
  lazy val flightDate: FlightDate = FlightDate.fromString(flDate)
  lazy val actualDepTime: Time = Time.fromMinutes(scheduledDepTime.asMinutes + depDelay.toInt)
  lazy val actualArrTime: Time = Time.fromMinutes(scheduledArrTime.asMinutes + arrDelay.toInt)
  lazy val isDelayed: Boolean = depDelay != 0 || arrDelay != 0

  override def compare(that: Flight): Int = this.actualArrTime.compare(that.actualArrTime)
} 

object Flight {
  def fromString(flightInfo: String): Flight = {
    val columns = flightInfo.split(FlightsLoaderConfig.delimiter)

    def getColValue(colName: String): String = {
      val index = FlightsLoaderConfig.columnIndexMap.getOrElse(colName,
        throw new IllegalArgumentException(s"Column $colName not found in CSV header"))
      columns(index).trim
    }

    val oriAirport = Airport(
      airportId = getColValue("ORIGIN_AIRPORT_ID").toLong,
      code = getColValue("ORIGIN"),
      cityName = getColValue("ORIGIN_CITY_NAME"),
      stateAbr = getColValue("ORIGIN_STATE_ABR")
    )

    val destAirport = Airport(
      airportId = getColValue("DEST_AIRPORT_ID").toLong,
      code = getColValue("DEST"),
      cityName = getColValue("DEST_CITY_NAME"),
      stateAbr = getColValue("DEST_STATE_ABR")
    )

    Flight(
      flDate = getColValue("FL_DATE"),
      origin = oriAirport,
      dest = destAirport,
      scheduledDepTime = Time.fromString(getColValue("DEP_TIME")),
      scheduledArrTime = Time.fromString(getColValue("ARR_TIME")),
      depDelay = getColValue("DEP_DELAY").toDouble,
      arrDelay = getColValue("ARR_DELAY").toDouble
    )
  }

  def fromRow(row: Row): Flight = {
    val oriAirport = Airport(
      airportId = row.originAirportId,
      code = row.origin,
      cityName = row.originCityName,
      stateAbr = row.originStateAbr
    )

    val destAirport = Airport(
      airportId = row.destAirportId,
      code = row.dest,
      cityName = row.destCityName,
      stateAbr = row.destStateAbr
    )

    Flight(
      flDate = row.flDate,
      origin = oriAirport,
      dest = destAirport,
      scheduledDepTime = Time.fromString(row.depTime),
      scheduledArrTime = Time.fromString(row.arrTime),
      depDelay = row.depDelay,
      arrDelay = row.arrDelay
    )
  }
}
