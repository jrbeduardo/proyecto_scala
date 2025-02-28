package org.ntic.flights.data

import org.ntic.flights.data.{Flight, Row}
import org.ntic.flights.FlightsLoaderConfig

import java.io.{FileOutputStream, ObjectOutputStream}
import scala.util.Try
import scala.io.Source


object FileUtils {

  /**
   * This function is used to check if the line is valid or not
   * @param s: String
   * @return Boolean: true if the line is invalid, false otherwise
   */
  def isInvalidLine(s: String): Boolean = s.trim.isEmpty || s.split(FlightsLoaderConfig.delimiter).length != FlightsLoaderConfig.headers.length

  /**
   * This function is used to read the file located in the path `filePath` and return a list of lines of the file
   *
   * @param filePath: String
   * @return List[String]
   */
  def getLinesFromFile(filePath: String): List[String] = {
    val source = Source.fromFile(filePath)
    try {
      source.getLines().toList  
    } finally {
      source.close()
    }
  }

  /**
   * This function is used to load the rows from the file lines
   *
   * @param fileLines: Seq[String]
   * @return Seq[Try[Row]]
   */
  def loadFromFileLines(fileLines: Seq[String]): Seq[Try[Row]] = {
    // TODO: Implementa esta función de tal manera que devuelva una lista de Try[Row]
    //  a partir de las líneas del fichero `fileLines`.
    //  Pista: usa map de las colecciones para generar una lista de Try[Row] a partir de las líneas del fichero `fileLines`.
    //  Pista: estás trabajando con CSV, cada línea del fichero está separado por un `delimitador`
    //  Pista: usa la función fromStringList de la clase Row para crear un objeto Row a partir de una lista de tokens.
   fileLines.tail.map { line =>
      val tokens = line.split(FlightsLoaderConfig.delimiter).toSeq
      Row.fromStringList(tokens)
    }
 }

  def writeFile(flights: Seq[Flight], outputFilePath: String): Unit = {
    val out = new ObjectOutputStream(new FileOutputStream(outputFilePath))
    out.writeObject(flights)
    out.close()
  }

  
}