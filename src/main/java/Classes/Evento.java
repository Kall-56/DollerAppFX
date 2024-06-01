package Classes;

import java.text.SimpleDateFormat;

public class Evento {
    private Institucion clienteInstitucion;
    private Farmacia clienteFarmacia;
    private Medico clienteMedico;

    private SimpleDateFormat fecha;
    private int semana; // Semana del Mes
    private String descripcion;

    public Evento(Institucion clienteInstitucion, SimpleDateFormat fecha, int semana, String descripcion) {
        this.clienteInstitucion = clienteInstitucion;
        this.fecha = fecha;
        this.semana = semana;
        this.descripcion = descripcion;
    }

    public Evento(Farmacia clienteFarmacia, SimpleDateFormat fecha, int semana, String descripcion) {
        this.clienteFarmacia = clienteFarmacia;
        this.fecha = fecha;
        this.semana = semana;
        this.descripcion = descripcion;
    }

    public Evento(Medico clienteMedico, SimpleDateFormat fecha, int semana, String descripcion) {
        this.clienteMedico = clienteMedico;
        this.fecha = fecha;
        this.semana = semana;
        this.descripcion = descripcion;
    }

    // Getters y Setters -----------------------------------------------------------------------------------------------
    public Institucion getClienteInstitucion() {
        return clienteInstitucion;
    }

    public void setClienteInstitucion(Institucion clienteInstitucion) {
        this.clienteInstitucion = clienteInstitucion;
    }

    public Farmacia getClienteFarmacia() {
        return clienteFarmacia;
    }

    public void setClienteFarmacia(Farmacia clienteFarmacia) {
        this.clienteFarmacia = clienteFarmacia;
    }

    public Medico getClienteMedico() {
        return clienteMedico;
    }

    public void setClienteMedico(Medico clienteMedico) {
        this.clienteMedico = clienteMedico;
    }

    public SimpleDateFormat getFecha() {
        return fecha;
    }

    public void setFecha(SimpleDateFormat fecha) {
        this.fecha = fecha;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    // -----------------------------------------------------------------------------------------------------------------
}
