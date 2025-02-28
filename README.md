# 📌 Aplicación de Gestión de Datos de Vuelos en Scala

Este documento describe la estructura de la aplicación desarrollada en **Scala** para la gestión de datos de vuelos a partir de archivos CSV.

## 🏗️ **Descripción de las Carpetas y Archivos Principales**

### **1️⃣ `project/`**
Contiene archivos de configuración de **SBT (Scala Build Tool)**, necesarios para la compilación y gestión del proyecto.

### **2️⃣ `src/`**
Directorio principal que contiene el código fuente de la aplicación.

- **`src/main/scala/org/ntic/flights/data/`** → Contiene clases de datos para representar vuelos y aeropuertos.
- **`src/main/scala/org/ntic/flights/processing/`** → Implementa la lógica de procesamiento y filtrado de datos.
- **`src/main/scala/org/ntic/flights/utils/`** → Funciones de utilidad usadas en la aplicación.

### **3️⃣ `flights.csv`**
Dataset que contiene los datos de vuelos y aeropuertos en formato CSV.

### **4️⃣ `built.sbt`**
Archivo de configuración del proyecto, define las dependencias y configuraciones necesarias para la ejecución.

Ejemplo de dependencias en `built.sbt`:
```sbt
name := "FlightDataProcessor"
version := "1.0"
scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.2",    // Manejo de configuración
  "org.scalatest" %% "scalatest" % "3.2.14" % Test  // Framework de pruebas
)
5️⃣

