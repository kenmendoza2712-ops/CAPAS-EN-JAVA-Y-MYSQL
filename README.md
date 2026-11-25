# 3CAPAS-U

Aplicación de escritorio en **Java (Swing)** para administrar **alumnos** y **profesores**, desarrollada con una **arquitectura en tres capas** (Presentación, Lógica y Datos) y conectada a una base de datos **MySQL** mediante procedimientos almacenados.

## Características

- CRUD completo de **Alumnos**: agregar, actualizar, eliminar, listar y buscar por cédula.
- CRUD completo de **Profesores**: agregar, actualizar, eliminar, listar y buscar por cédula.
- Clasificación automática del nivel de experiencia del profesor (*Entry, Junior, Mid, Senior Level*).
- Interfaz gráfica con ventanas (`JDialog`) y tablas que se actualizan después de cada operación.
- Acceso a datos a través de **procedimientos almacenados** (`CallableStatement`).
- Interfaz genérica `IPersona<T>` que define el contrato de la capa de datos.

## Arquitectura

```
┌──────────────────────────┐
│   CapaPresentacion       │  JDialogAlumno, JDialogProfesor (Swing)
└────────────┬─────────────┘
             │
┌────────────▼─────────────┐
│   CapaLogica             │  AlumnoLog, ProfesorLog
└────────────┬─────────────┘
             │
┌────────────▼─────────────┐
│   CapaDatos              │  AlumnoDA, ProfesorDA, ConnectionDB, IPersona<T>
└────────────┬─────────────┘
             │ JDBC
┌────────────▼─────────────┐
│   MySQL  (_personas)     │  Procedimientos almacenados
└──────────────────────────┘

        Modelo (Persona → Alumno, Profesor): usado por todas las capas
```

### Modelo de dominio

| Clase      | Hereda de | Atributos propios                      |
|------------|-----------|----------------------------------------|
| `Persona`  | —         | cédula, nombre, apellido1, apellido2, fecha de nacimiento (`dd/mm/aaaa`), correo, tipo de persona |
| `Alumno`   | `Persona` | carnet de estudiante                   |
| `Profesor` | `Persona` | carnet de profesor, años de experiencia |

**Niveles de experiencia del profesor** (`Profesor._nExperiencia()`):

| Años de experiencia | Nivel        |
|---------------------|--------------|
| menos de 1          | Entry Level  |
| 1 a 2               | Junior Level |
| más de 2 y hasta 4  | Mid Level    |
| más de 4            | Senior Level |

## Estructura del proyecto

```
3CAPAS-U/
├── src/
│   ├── CapaDatos/
│   │   ├── ConnectionDB.java
│   │   ├── IPersona.java
│   │   ├── AlumnoDA.java
│   │   └── ProfesorDA.java
│   ├── CapaLogica/
│   │   ├── AlumnoLog.java
│   │   └── ProfesorLog.java
│   ├── CapaPresentacion/
│   │   ├── JDialogAlumno.java / .form
│   │   └── JDialogProfesor.java / .form
│   ├── Modelo/
│   │   ├── Persona.java
│   │   ├── Alumno.java
│   │   └── Profesor.java
│   └── pkg3capas/u/U.java
├── test/
├── nbproject/
├── build.xml
└── manifest.mf
```

## Tecnologías utilizadas

- **Java** con interfaz gráfica **Swing** (`JDialog`)
- **Apache NetBeans** y **Ant** como sistema de construcción
- **MySQL** como base de datos
- **JDBC** (MySQL Connector/J) para la conexión

## Base de datos

El proyecto usa una base de datos llamada `_personas` en `localhost:3306`. La aplicación llama a los siguientes procedimientos almacenados, que deben existir en la base de datos:

| Entidad  | Procedimientos |
|----------|----------------|
| Alumno   | `PA_AGREGAR_ALUMN`, `PA_ACTUALIZAR_ALUMN`, `PA_ELIMINAR_ALUMN`, `PA_LISTAR_ALUMN`, `PA_LISTAR_TODOS_ALUMN` |
| Profesor | `PA_AGREGAR_PROFESOR`, `PA_ACTUALIZAR_PROFESOR`, `PA_ELIMINAR_PROFESOR`, `PA_LISTAR_TODOS_PROFESORES` |

Orden de los parámetros de los procedimientos:

- **Alumno (agregar/actualizar):** cédula, nombre, apellido1, apellido2, fecha de nacimiento, correo, carnet, tipo de persona.
- **Profesor (agregar/actualizar):** cédula, nombre, apellido1, apellido2, fecha de nacimiento, correo, carnet, experiencia, tipo de persona.
- **Eliminar / listar uno:** cédula.

La conexión se centraliza en la clase `ConnectionDB`, que usa el driver `com.mysql.cj.jdbc.Driver` y entrega la conexión a las clases de la capa de datos.

## Funcionamiento

Cada ventana (`JDialogAlumno` y `JDialogProfesor`) crea su clase de la capa lógica (`AlumnoLog` o `ProfesorLog`), la cual a su vez usa la clase de datos correspondiente (`AlumnoDA` o `ProfesorDA`). Las ventanas nunca hablan directamente con la base de datos: toda operación pasa por la capa lógica y se ejecuta con un procedimiento almacenado. Las listas se devuelven como `DefaultTableModel` y se muestran directamente en la tabla de la ventana.

La clase `pkg3capas.u.U` tiene un `main` vacío por ahora; cada ventana tiene su propio `main` para ejecutarse de forma independiente.

## Uso

1. Abre la ventana de **Alumno** o **Profesor**.
2. Llena los campos (cédula, nombre, apellidos, correo, fecha de nacimiento en formato `dd/mm/aaaa` y carnet; en profesor también los años de experiencia).
3. Usa los botones:
   - **Guardar**: registra una nueva persona.
   - **Actualizar**: modifica los datos de la persona seleccionada.
   - **Eliminar**: elimina a la persona, con confirmación.
   - **Limpiar**: vacía el formulario.
4. Al escribir una cédula, la tabla filtra el registro correspondiente. Al hacer clic en una fila, sus datos se cargan en el formulario.

## Autor

**Kendall Quesada**

Proyecto académico de arquitectura en tres capas con Java y MySQL.
