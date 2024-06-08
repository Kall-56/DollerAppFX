package Classes;

import java.util.ArrayList;

public class Institucion extends Cliente {
    private String personaContacto;
    private String edfFacultad;

    public Institucion(String nombreCliente,
                       String direccion,
                       String numTLF,
                       char tipoIdentidad,
                       int docIdentidad,
                       String descripcion,
                       String personaContacto,
                       String edfFacultad) {

        super(nombreCliente, direccion, numTLF, tipoIdentidad, docIdentidad, descripcion);
        this.personaContacto = personaContacto;
        this.edfFacultad = edfFacultad;
    }

    public Institucion(String nombreCliente,
                       String direccion,
                       String numTLF,
                       char tipoIdentidad,
                       int docIdentidad,
                       String descripcion,
                       String personaContacto,
                       String edfFacultad,
                       ArrayList<Evento> eventos) {

        super(nombreCliente, direccion, numTLF, tipoIdentidad, docIdentidad, descripcion, eventos);
        this.personaContacto = personaContacto;
        this.edfFacultad = edfFacultad;
    }

    // Getters y Setters -----------------------------------------------------------------------------------------------
    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getEdfFacultad() {
        return edfFacultad;
    }

    public void setEdfFacultad(String edfFacultad) {
        this.edfFacultad = edfFacultad;
    }
    // -----------------------------------------------------------------------------------------------------------------
}