package Classes;

import java.util.Date;

public class Evento {
    private String cliente;
    private Date fecha;
    private int semana; // Semana del Mes
    private String descripcion;

    public Evento(String cliente, Date fecha, int semana, String descripcion) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.semana = semana;
        this.descripcion = descripcion;
    }

    // Getters y Setters -----------------------------------------------------------------------------------------------
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
