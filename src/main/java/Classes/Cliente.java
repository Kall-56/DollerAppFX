package Classes;

import java.util.ArrayList;

public abstract class Cliente {
    protected String nombreCliente;
    protected String direccion;
    protected int numTLF;
    protected char tipoIdentidad; // Si es Natural o Juridico
    protected int docIdentidad;
    protected String descripcion;
    protected String acompany; // Acompañada
    protected ArrayList<Evento> eventoList;

    public Cliente(String nombreCliente, String direccion, int numTLF, char tipoIdentidad, int docIdentidad, String descripcion, String acompany) {
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.numTLF = numTLF;
        this.tipoIdentidad = tipoIdentidad;
        this.docIdentidad = docIdentidad;
        this.descripcion = descripcion;
        this.acompany = acompany;

        eventoList = new ArrayList<>();
    }

    // Getters y Setters -----------------------------------------------------------------------------------------------
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumTLF() {
        return numTLF;
    }

    public void setNumTLF(int numTLF) {
        this.numTLF = numTLF;
    }

    public char getTipoIdentidad() {
        return tipoIdentidad;
    }

    public void setTipoIdentidad(char tipoIdentidad) {
        this.tipoIdentidad = tipoIdentidad;
    }

    public int getDocIdentidad() {
        return docIdentidad;
    }

    public void setDocIdentidad(int docIdentidad) {
        this.docIdentidad = docIdentidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAcompany() {
        return acompany;
    }

    public void setAcompany(String acompany) {
        this.acompany = acompany;
    }

    public ArrayList<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(ArrayList<Evento> eventoList) {
        this.eventoList = eventoList;
    }
    // -----------------------------------------------------------------------------------------------------------------

     public void registrarEvento(Evento event) {
        eventoList.add(event);
    }
}
