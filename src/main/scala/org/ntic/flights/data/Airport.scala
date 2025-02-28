package org.ntic.flights.data

/**
 * This class is used to represent an airport with its information like the airport id, code, city name and state abbreviation.
 * @param airportId: Long
 * @param code: String
 * @param cityName: String
 * @param stateAbr: String
 */
@SerialVersionUID(1L)
case class Airport(
                    airportId: Long,
                    code: String,
                    cityName: String,
                    stateAbr: String
) extends Serializable