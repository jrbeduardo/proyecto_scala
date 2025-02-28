error id: fromStringList
file://<WORKSPACE>/src/main/scala/org/ntic/flights/FlightsLoaderApp.scala
empty definition using pc, found symbol in pc: 
semanticdb not found
found definition using fallback; symbol fromStringList
Document text:

```scala
package org.ntic.flights

import org.ntic.flights.FlightsLoaderConfig
import org.ntic.flights.data.{Flight, FlightsFileReport, Row}

import scala.util.Try

object FlightsLoaderApp extends App {

  val fileLines: Seq[String] = scala.io.Source.fromFile(FlightsLoaderConfig.filePath).getLines().toSeq
  // TODO: obtén la lista de líneas del fichero `filePath`
  val rows: Seq[Try[Row]] = fileLines.map(Row.fromStringList())
  // TODO: obtén la lista de Try[Row] a partir de las líneas del fichero `fileLines`. Pista: usa funciones de FileUtils para ello.
  val flightReport: FlightsFileReport = FlightsFileReport.fromRows(rows)
  // TODO: crea un objeto FlightsFileReport a partir de las filas `rows`. Pista: usa el objeto companion de FlightsFileReport.
  val flights: Seq[Flight] = flightReport.flights // TODO: obtén la lista de vuelos a partir de las filas `rows`. Pista: ya tienes un objeto FlightsFileReport previo con esa información.

  for (origin <- FlightsLoaderConfig.filteredOrigin) {
    val filteredFlights = flights.filter(_.origin == origin)
    val delayedFlights = filteredFlights.filter(_.isDelayed).sorted
    val notDelayedFlights = filteredFlights.filter(!_.isDelayed).sorted

    val delayedFlightsObj = s"${FlightsLoaderConfig.outputDir}/$origin_delayed.obj"
    val flightsObj = s"${FlightsLoaderConfig.outputDir}/$origin.obj"

    FileUtils.writeObjectToFile(delayedFlights, delayedFlightsObj)
    FileUtils.writeObjectToFile(notDelayedFlights, flightsObj)
  }
  // TODO: Itera sobre los aeropuertos filtrados y genera los ficheros de vuelos retrasados y no retrasados por origen.
  //  Pista: puedes usar un bucle-for o foreach para iterar sobre los orígenes de los vuelos.
  //  Donde para cada origen se debe realizar lo siguiente:
  //  - Filtrar los vuelos por origen (filteredFligths)
  //  - Filtrar los vuelos retrasados (delayedFlights)
  //  - Filtrar los vuelos no retrasados (notDelayedFlights)
  //  - Escribir los vuelos retrasados en un fichero .obj (delayedFlightsObj)
  //  - Escribir los vuelos no retrasados en un fichero .obj (flightsObj)
  //  Pista: filteredFligths es una variable que debes definir que contiene los vuelos filtrados por origen
  //  Pista: delayedFlights es una variable que debes definir que contiene los vuelos retrasados y ordenados
  //    Pista: el atributo isDelayed de Flight te puede ayudar para realizar el filtrado
  //    Pista: usa la función sorted de las colecciones de Scala para ordenar
  //    Pista: para que la función sorted funcione, debes implementar el trait Ordered en la clase Flight
  //  Pista: notDelayedFlights es una variable que debes definir que contiene los vuelos no retrasados y ordenados
  //    Pista: el atributo isDelayed de Flight te puede ayudar para realizar el filtrado
  //    Pista: usa la función sorted de las colecciones de Scala para ordenar
  //    Pista: para que la función sorted funcione, debes implementar el trait Ordered en la clase Flight
  //  Pista: delayedFlightsObj y flightsObj son variables que debes definir que contienen los paths de los ficheros .obj
  //    Pista: el path debe concatenar el directorio de salida (outputDir) con el origen del vuelo, la cadena "_delayed"
  //    (sólo aplica para delayedFlightsObj) y la extensión .obj
  //  Pista: Usa FileUtils para escribir los ficheros .obj con los vuelos retrasados y no retrasados en el directorio de salida (outputDir) correspondiente a cada origen de vuelo.
  println(flightReport)
}

```

#### Short summary: 

empty definition using pc, found symbol in pc: 