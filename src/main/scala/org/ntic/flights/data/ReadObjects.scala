package org.ntic.flights.data

import java.io.{File, FileInputStream, ObjectInputStream}

object ReadObjects {
  def readAllObjects(): Unit = {
    val directory = new File("output")

    if (!directory.exists() || !directory.isDirectory) {
      println("❌ Error: La carpeta 'output' no existe.")
      return
    }

    val objFiles = directory.listFiles().filter(_.getName.endsWith(".obj"))

    if (objFiles.isEmpty) {
      println("📂 No hay archivos .obj en la carpeta 'output'.")
      return
    }

    objFiles.foreach { file =>
      println(s"\n📄 Leyendo archivo: ${file.getName}")
      readObjFile(file)
    }
  }

  private def readObjFile(file: File): Unit = {
  try {
    val in = new ObjectInputStream(new FileInputStream(file))
    val flights = in.readObject() match {
      case list: Seq[_] =>
        try {
          list.asInstanceOf[Seq[Flight]]
        } catch {
          case e: ClassCastException =>
            println(s"  ❌ Error de conversión en ${file.getName}: ${e.getMessage}")
            Seq()
        }
      case other =>
        println(s"  ❌ Tipo inesperado en ${file.getName}: ${other.getClass}")
        Seq()
    }
    in.close()

    if (flights.nonEmpty) {
      flights.foreach(flight => println(s"  ✈ $flight"))
    } else {
      println(s"El archivo ${file.getName} está vacío o tiene un formato incorrecto.")
    }
  } catch {
    case e: ClassNotFoundException =>
      println(s"  ❌ Error al leer ${file.getName}: La clase `Flight` no coincide con la serialización: ${e.getMessage}")
    case e: ClassCastException =>
      println(s"  ❌ Error al leer ${file.getName}: ${e.getMessage}")
    case e: Exception =>
      println(s"  ❌ Error desconocido en ${file.getName}: ${e.getMessage}")
  }
}

}
