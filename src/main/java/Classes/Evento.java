package Classes;

public class Evento {
    private String titulo;
    private String cliente;
    private String fecha;
    private int semana; // Semana del Mes
    private String descripcion;

    public Evento(String titulo, String cliente, String fecha, int semana, String descripcion) {
        this.titulo = titulo;
        this.cliente = cliente;
        this.fecha = fecha;
        this.semana = semana;
        this.descripcion = descripcion;
    }

    // Getters y Setters -----------------------------------------------------------------------------------------------
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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
