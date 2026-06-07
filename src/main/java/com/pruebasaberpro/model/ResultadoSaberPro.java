package com.pruebasaberpro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resultados_saber_pro")
public class ResultadoSaberPro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToOne(optional = false, fetch = FetchType.LAZY) @JoinColumn(name = "estudiante_id", nullable = false) private Estudiante estudiante;
    private Integer puntaje; private String estado; private String nivelGlobal;
    private Integer comunicacionEscrita; private String comunicacionEscritaNivel;
    private Integer razonamientoCuantitativo; private String razonamientoCuantitativoNivel;
    private Integer lecturaCritica; private String lecturaCriticaNivel;
    private Integer competenciasCiudadanas; private String competenciasCiudadanasNivel;
    private Integer ingles; private String inglesNivel;
    private Integer formulacionProyectos; private String formulacionProyectosNivel;
    private Integer pensamientoCientifico; private String pensamientoCientificoNivel;
    private Integer disenoSoftware; private String disenoSoftwareNivel;
    private String nivelIngles;
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Estudiante getEstudiante() { return estudiante; } public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public Integer getPuntaje() { return puntaje; } public void setPuntaje(Integer puntaje) { this.puntaje = puntaje; }
    public String getEstado() { return estado; } public void setEstado(String estado) { this.estado = estado; }
    public String getNivelGlobal() { return nivelGlobal; } public void setNivelGlobal(String nivelGlobal) { this.nivelGlobal = nivelGlobal; }
    public Integer getComunicacionEscrita() { return comunicacionEscrita; } public void setComunicacionEscrita(Integer v) { this.comunicacionEscrita = v; }
    public String getComunicacionEscritaNivel() { return comunicacionEscritaNivel; } public void setComunicacionEscritaNivel(String v) { this.comunicacionEscritaNivel = v; }
    public Integer getRazonamientoCuantitativo() { return razonamientoCuantitativo; } public void setRazonamientoCuantitativo(Integer v) { this.razonamientoCuantitativo = v; }
    public String getRazonamientoCuantitativoNivel() { return razonamientoCuantitativoNivel; } public void setRazonamientoCuantitativoNivel(String v) { this.razonamientoCuantitativoNivel = v; }
    public Integer getLecturaCritica() { return lecturaCritica; } public void setLecturaCritica(Integer v) { this.lecturaCritica = v; }
    public String getLecturaCriticaNivel() { return lecturaCriticaNivel; } public void setLecturaCriticaNivel(String v) { this.lecturaCriticaNivel = v; }
    public Integer getCompetenciasCiudadanas() { return competenciasCiudadanas; } public void setCompetenciasCiudadanas(Integer v) { this.competenciasCiudadanas = v; }
    public String getCompetenciasCiudadanasNivel() { return competenciasCiudadanasNivel; } public void setCompetenciasCiudadanasNivel(String v) { this.competenciasCiudadanasNivel = v; }
    public Integer getIngles() { return ingles; } public void setIngles(Integer ingles) { this.ingles = ingles; }
    public String getInglesNivel() { return inglesNivel; } public void setInglesNivel(String inglesNivel) { this.inglesNivel = inglesNivel; }
    public Integer getFormulacionProyectos() { return formulacionProyectos; } public void setFormulacionProyectos(Integer v) { this.formulacionProyectos = v; }
    public String getFormulacionProyectosNivel() { return formulacionProyectosNivel; } public void setFormulacionProyectosNivel(String v) { this.formulacionProyectosNivel = v; }
    public Integer getPensamientoCientifico() { return pensamientoCientifico; } public void setPensamientoCientifico(Integer v) { this.pensamientoCientifico = v; }
    public String getPensamientoCientificoNivel() { return pensamientoCientificoNivel; } public void setPensamientoCientificoNivel(String v) { this.pensamientoCientificoNivel = v; }
    public Integer getDisenoSoftware() { return disenoSoftware; } public void setDisenoSoftware(Integer v) { this.disenoSoftware = v; }
    public String getDisenoSoftwareNivel() { return disenoSoftwareNivel; } public void setDisenoSoftwareNivel(String v) { this.disenoSoftwareNivel = v; }
    public String getNivelIngles() { return nivelIngles; } public void setNivelIngles(String nivelIngles) { this.nivelIngles = nivelIngles; }
}
