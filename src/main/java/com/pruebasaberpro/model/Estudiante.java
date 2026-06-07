package com.pruebasaberpro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "estudiantes")
public class Estudiante {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoDocumento;
    private String numeroDocumento;
    @NotBlank private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private String correoElectronico;
    private String numeroTelefonico;
    @Column(unique = true) private String numeroRegistro;
    private boolean aprobadoSaberPro;
    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ResultadoSaberPro resultado;
    public String getNombreCompleto() { String n = String.join(" ", safe(primerNombre), safe(segundoNombre), safe(primerApellido), safe(segundoApellido)).trim(); return n.isBlank() ? primerApellido : n; }
    private String safe(String v) { return v == null ? "" : v; }
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getTipoDocumento() { return tipoDocumento; } public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }
    public String getNumeroDocumento() { return numeroDocumento; } public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
    public String getPrimerApellido() { return primerApellido; } public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }
    public String getSegundoApellido() { return segundoApellido; } public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }
    public String getPrimerNombre() { return primerNombre; } public void setPrimerNombre(String primerNombre) { this.primerNombre = primerNombre; }
    public String getSegundoNombre() { return segundoNombre; } public void setSegundoNombre(String segundoNombre) { this.segundoNombre = segundoNombre; }
    public String getCorreoElectronico() { return correoElectronico; } public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public String getNumeroTelefonico() { return numeroTelefonico; } public void setNumeroTelefonico(String numeroTelefonico) { this.numeroTelefonico = numeroTelefonico; }
    public String getNumeroRegistro() { return numeroRegistro; } public void setNumeroRegistro(String numeroRegistro) { this.numeroRegistro = numeroRegistro; }
    public boolean isAprobadoSaberPro() { return aprobadoSaberPro; } public void setAprobadoSaberPro(boolean aprobadoSaberPro) { this.aprobadoSaberPro = aprobadoSaberPro; }
    public ResultadoSaberPro getResultado() { return resultado; } public void setResultado(ResultadoSaberPro resultado) { this.resultado = resultado; }
}
