error id: scala/
file://<WORKSPACE>/src/main/scala/org/ntic/flights/FlightsLoaderApp.scala
empty definition using pc, found symbol in pc: scala/
semanticdb not found
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
package org.ntic.flights
import scala.util.{Try, Success, Failure}

import org.ntic.flights.data.{Flight, FlightsFileReport, FileUtils, Row}
import scala.io.Source
import java.io.File

object FlightsLoaderApp extends App {
  println("Cargando datos de vuelos...")
  // Asegurar que el directorio de salida existe
  new File(FlightsLoaderConfig.outputDir).mkdirs()
  // Leer las líneas del archivo CSV
  val fileLines: Seq[String] = Source.fromFile(FlightsLoaderConfig.filePath).getLines().toSeq

  // Convertir las líneas en filas (Row)
  val rows: Seq[Try[Row]] = fileLines.tail.map(line => Row.fromStringList(line.split(FlightsLoaderConfig.delimiter).toSeq))
   // Generar el reporte de los vuelos
  val flightReport: FlightsFileReport = FlightsFileReport.fromRows(rows)
  // Convertir las filas en objetos Flight
  val flights: Seq[Flight] = rows.map(Flight.fromRow)

  // Iterar sobre los aeropuertos filtrados y generar los archivos de vuelos retrasados y no retrasados por origen
  for (origin <- FlightsLoaderConfig.filteredOrigin) {
    val filteredFlights = flights.filter(_.origin.code == origin)
    
    val delayedFlights = filteredFlights.filter(_.isDelayed).sorted
    val notDelayedFlights = filteredFlights.filter(!_.isDelayed).sorted
    
    val delayedFlightsObj = s"${FlightsLoaderConfig.outputDir}/${origin}_delayed.obj"
    val flightsObj = s"${FlightsLoaderConfig.outputDir}/${origin}.obj"
    
    FileUtils.writeFile(delayedFlights, delayedFlightsObj)
    FileUtils.writeFile(notDelayedFlights, flightsObj)
  }
  println(flightReport)
}
```

#### Short summary: 

empty definition using pc, found symbol in pc: scala/