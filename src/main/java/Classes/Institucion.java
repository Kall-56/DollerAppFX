package Classes;

public class Institucion extends Cliente {
    private String personaContacto;
    private String edfFacultad;

    public Institucion(String nombreCliente,
                       String direccion,
                       int numTLF,
                       char tipoIdentidad,
                       int docIdentidad,
                       String descripcion,
                       String acompany,
                       String personaContacto,
                       String edfFacultad) {

        super(nombreCliente, direccion, numTLF, tipoIdentidad, docIdentidad, descripcion, acompany);
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
