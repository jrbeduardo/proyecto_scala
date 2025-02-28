package org.ntic.flights

import org.ntic.flights.data.{Flight, FlightsFileReport, FileUtils, Row, ReadObjects}
import scala.io.Source
import java.io.File
import scala.util.Try

object FlightsLoaderApp extends App {
  // Asegurar que el directorio de salida existe
  new File(FlightsLoaderConfig.outputDir).mkdirs()
  // Leer las líneas del archivo CSV
  val fileLines: Seq[String] = FileUtils.getLinesFromFile(FlightsLoaderConfig.filePath)

  // Convertir las líneas en filas (Row)
  val rows: Seq[Try[Row]] = FileUtils.loadFromFileLines(fileLines)
  // Generar el reporte de los vuelos
  val flightReport: FlightsFileReport = FlightsFileReport.fromRows(rows)
  // Convertir las filas en objetos Flight
  val flights: Seq[Flight] = rows.collect { case scala.util.Success(row) => Flight.fromRow(row) }
// Iterar sobre los aeropuertos filtrados y generar los archivos de vuelos retrasados y no retrasados por origen
for (origin <- FlightsLoaderConfig.filteredOrigin) {
  val filteredFlights = flights.filter(_.origin.code == origin)

  val delayedFlights = filteredFlights.filter(_.isDelayed).sorted
  val notDelayedFlights = filteredFlights.filter(!_.isDelayed).sorted

  val delayedFlightsObj = s"${FlightsLoaderConfig.outputDir}/${origin}_delayed.obj"
  val flightsObj = s"${FlightsLoaderConfig.outputDir}/${origin}.obj"

  // Mostrar en consola los vuelos filtrados antes de guardarlos
  println(s"\nAeropuerto: $origin")
  println(s"Vuelos retrasados (${delayedFlights.length}):")
  delayedFlights.foreach(flight => println(s"  - $flight"))

  println(s"Vuelos no retrasados (${notDelayedFlights.length}):")
  notDelayedFlights.foreach(flight => println(s"  - $flight"))

  // Guardar en archivos
  FileUtils.writeFile(delayedFlights, delayedFlightsObj)
  FileUtils.writeFile(notDelayedFlights, flightsObj)
}

  println(flightReport)

  // ✅ Llamar a ReadObjects para leer y visualizar los archivos
  println("\n📢 Leyendo archivos generados en output/")
  ReadObjects.readAllObjects()
}