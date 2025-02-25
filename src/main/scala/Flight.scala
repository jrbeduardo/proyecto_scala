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
case class Flight(flDate: String,
                  origin: Airport,
                  dest: Airport,
                  scheduledDepTime: Time,
                  scheduledArrTime: Time,
                  depDelay: Double,
                  arrDelay: Double) {
  ??? flightDate: FlightDate = ???  // TODO: define el campo flightDate (perezoso e inmutable) de tipo FlightDate a partir del campo flDate
                                    //    que es de tipo String.
                                    //    Pista: usa el método fromString de FlightDate

  ??? actualDepTime: Time = ??? //  TODO: define el campo actualDepTime (perezoso e inmutable) de tipo Time a partir de los campos
  //    scheduledDepTime y depDelay
  //    Ten en cuenta que este campo debe representar la hora de salida real del vuelo, esto quiere decir que debe
  //    tener en cuenta el retraso, el campo depDelay representa el retraso en minutos, puede ser negativo y es Double.
  //    Pista: usa el método fromMinutes de Time

  ??? actualArrTime: Time = ??? //  TODO: define el campo actualArrTime (perezoso e inmutable) de tipo Time a partir de los campos
  //    scheduledArrTime y arrDelay.
  //    Ten en cuenta que este campo debe representar la hora de llegada real del vuelo, esto quiere decir que debe
  //    tener en cuenta el retraso, el campo arrDelay representa el retraso en minutos, puede ser negativo y es Double.
  //    Pista: usa el método fromMinutes de Time

  ??? isDelayed: Boolean = ??? // TODO: define el atributo inmutable `isDelayed` de tipo Boolean que indica si el vuelo está retrasado o no.
  //  Pista: un vuelo está retrasado si el campo depDelay o el campo arrDelay son distintos de 0

  // TODO: la clase Flight debe poderse ordenar por el campo actualArrTime, para ello la clase debe implementar el trait Ordered
  //  Pista: para implementar el trait Ordered debes implementar el método compare
  //  Pista: el método compare debe devolver un Int
  //  Pista: el método compare debe comparar el atributo actualArrTime del objeto que invoque a la función con el
  //    atributo actualArrTime de la clase que se le pasa como parámetro
  //  Pista: actualArrTime es de tipo Time

object Flight {
  /**
   * This function is used to create a Flight object from a string with the information of the flight separated by a
   * delimiter defined in the configuration. The function returns a Flight object with the information of the flight.
   * The input string should have the following format:
   * "FL_DATE,ORIGIN_AIRPORT_ID,ORIGIN,ORIGIN_CITY_NAME,ORIGIN_STATE_ABR,DEST_AIRPORT_ID,DEST,DEST_CITY_NAME,DEST_STATE_ABR,DEP_TIME,ARR_TIME,DEP_DELAY,ARR_DELAY"
   *
   * @param flightInfo: String
   * @return Flight
   */
  def fromString(flightInfo: String): Flight = {
    val columns = ???  //  TODO: genera un array de Strings a partir de la variable flightInfoRow
                       //    Pista: usa el método split de la clase String
                       //    Pista: el delimitador está en la configuración

    /**
     * This function is used to get the value of a column from the array of String generated from the row of the csv
     * and stored in the variable `columns`.
     * @param colName: String name of the column
     * @return String value of the column
     */
    def getColValue(colName: String): String = {
      // TODO: Implementar esta función
      //  Pista: usa el mapa columnIndexMap de la clase FlightsLoaderConfig,
      //    tiene como clave el nombre de la columna y como valor el índice de la columna
      //  Pista: puedes usar el método apply de la clase Array para obtener el valor de la columna
      //    del array de Strings `columns` usando el índice
      ???
    }

    val oriAirport = Airport(
      airportId = getColValue("ORIGIN_AIRPORT_ID").toLong,
      code = getColValue("ORIGIN"),
      cityName = getColValue("ORIGIN_CITY_NAME"),
      stateAbr = getColValue("ORIGIN_STATE_ABR"))

    val destAirport = Airport(
      airportId = getColValue("DEST_AIRPORT_ID").toLong,
      code = getColValue("DEST"),
      cityName = getColValue("DEST_CITY_NAME"),
      stateAbr = getColValue("DEST_STATE_ABR"))

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

  /**
   * This function is used to create a Flight object from a Row object. The function returns a Flight object with the
   * information of the flight.
   *
   * @param row: Row
   * @return Flight
   */
  def fromRow(row: Row): Flight = {
    // TODO: Implementar esta función
    //   Construye un objeto de la clase Flight usando la información que ya está cargada en el objeto de la clase Row
    //   Se deberá crear instancias de Airport para los aeropuertos de origen y destino usando la información el Row
    //   y objetos de la case Time para los horarios de salida y llegada programados, también usando la información de Row.
    ???
  }
}
