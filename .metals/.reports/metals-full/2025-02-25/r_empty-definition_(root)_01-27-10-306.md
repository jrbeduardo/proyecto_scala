error id: file://<WORKSPACE>/src/main/scala/org/ntic/flights/data/FlightDate.scala:14
file://<WORKSPACE>/src/main/scala/org/ntic/flights/data/FlightDate.scala
empty definition using pc, found symbol in pc: 
semanticdb not found
|empty definition using fallback
non-local guesses:
	 -

Document text:

```scala
package org.ntic.flights.data

import com.sun.media.sound.InvalidFormatException

/**
 * This class is used to represent a date of a flight
 * @param day: Int
 * @param month: Int
 * @param year: Int
 */
case class FlightDate(day: Int,
                      month: Int,
                      year: Int) {
  // TODO: Sobreescribir el método toString para que devuelva la fecha en formato dd/mm/yy. Al sobreescribirlo haz que
  //  sea peresozo y sólo se calcule cuando se llame a toString, pero además que se calcule una única vez de forma que
  //  si se llama varias veces a toString no se vuelva a calcular.
  //  Pista: usa interpolator `f` (ver https://docs.scala-lang.org/overviews/core/string-interpolation.html)
  //  Pista: conjuga lazy y la inmutabilidad
  override lazy val toString: String = f"$day%02d/$month%02d/${year % 100}%02d"
}

object FlightDate {
  /**
   * This function is used to convert a string to a FlightDate
   * @param date: String
   * @return FlightDate
   */
  def fromString(date: String): FlightDate = {
    date.split(" ").head.split("/").map(x => x.toInt).toList match {
      // TODO: Define el patrón de descomposición y reemplazas: reemplazaEstePatron
      case List(d, m, y) if d > 0 && d <= 31 && m > 0 && m <= 12 && y >= 1987 =>
      FlightDate(d, m, y)
      // TODO: Comprueba que el día, mes y año son correctos y si lo son devuelve
                                  //    un objeto de FlightDate con esos valores.
                                  //    Si no son correctos asegúrate que el programa lance lanza una excepción
                                  //    de tipo `AssertionError` con el mensaje adecuado.
                                  //  Pista: Ten en cuenta que según la documentación del dataset, el año mínimo es 1987
      case _ => throw new InvalidFormatException(s"$date tiene un formato inválido")
    }
  }
}

```

#### Short summary: 

empty definition using pc, found symbol in pc: 