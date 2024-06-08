package Classes;

import java.util.ArrayList;

public class Medico extends Cliente {
    private String especialidad;
    private String email;
    private int frecuencia;
    private String diasVisita;
    private String horario;
    private String formato;

    public Medico(String nombreCliente,
                  String direccion,
                  int numTLF,
                  char tipoIdentidad,
                  int docIdentidad,
                  String descripcion,
                  String especialidad,
                  String email,
                  int frecuencia,
                  String diasVisita,
                  String horario,
                  String formato) {

        super(nombreCliente, direccion, numTLF, tipoIdentidad, docIdentidad, descripcion);
        this.especialidad = especialidad;
        this.email = email;
        this.frecuencia = frecuencia;
        this.diasVisita = diasVisita;
        this.horario = horario;
        this.formato = formato;
    }

    public Medico(String nombreCliente,
                  String direccion,
                  int numTLF,
                  char tipoIdentidad,
                  int docIdentidad,
                  String descripcion,
                  String especialidad,
                  String email,
                  int frecuencia,
                  String diasVisita,
                  String horario,
                  String formato,
                  ArrayList<Evento> eventos) {

        super(nombreCliente, direccion, numTLF, tipoIdentidad, docIdentidad, descripcion, eventos);
        this.especialidad = especialidad;
        this.email = email;
        this.frecuencia = frecuencia;
        this.diasVisita = diasVisita;
        this.horario = horario;
        this.formato = formato;
    }

    // Getters y Setters -----------------------------------------------------------------------------------------------
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getDiasVisita() {
        return diasVisita;
    }

    public void setDiasVisita(String diasVisita) {
        this.diasVisita = diasVisita;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
    // -----------------------------------------------------------------------------------------------------------------
}
