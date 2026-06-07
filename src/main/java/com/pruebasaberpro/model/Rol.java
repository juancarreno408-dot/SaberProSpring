package com.pruebasaberpro.model;

public enum Rol {
    ADMINISTRADOR("Administrador"), COORDINACION("Coordinacion"), DOCENTE("Docente"), ESTUDIANTE("Estudiante");
    private final String etiqueta;
    Rol(String etiqueta) { this.etiqueta = etiqueta; }
    public String getEtiqueta() { return etiqueta; }
}
