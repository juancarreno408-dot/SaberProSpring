# PruebasSaberPro

Proyecto Java/Spring Boot para gestion y seguimiento de resultados Saber Pro.

## Requisitos
- Java 17
- PostgreSQL
- Spring Tools Suite o IDE compatible con Maven

## Base de datos
Crear una base de datos llamada `SaberPro` en PostgreSQL. Credenciales por defecto: usuario `postgres`, clave `postgres`, puerto `5432`.

## Ejecutar
1. Importar como `Existing Maven Project` en Spring Tools Suite.
2. Esperar a que Maven descargue dependencias.
3. Ejecutar `PruebasSaberProApplication`.
4. Abrir `http://localhost:8080`.

## Usuarios de prueba
- Administrador: `admin` / `admin123`
- Coordinacion: `coordinador` / `coordinador123`
- Docente: `docente` / `docente123`
- Estudiante: `estudiante` / `estudiante123`

Los datos del Excel quedan precargados en `data.sql`.
