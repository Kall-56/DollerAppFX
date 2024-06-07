package Classes;

public class Farmacia extends Cliente {
    private String personaContacto;
    private String email;
    private int frecuencia;
    private String cadena;
    private String drogueria;

    public Farmacia(String nombreCliente,
                    String direccion,
                    int numTLF,
                    char tipoIdentidad,
                    int docIdentidad,
                    String descripcion,
                    String personaContacto,
                    String email,
                    int frecuencia,
                    String cadena,
                    String drogueria) {

        super(nombreCliente, direccion, numTLF, tipoIdentidad, docIdentidad, descripcion);
        this.personaContacto = personaContacto;
        this.email = email;
        this.frecuencia = frecuencia;
        this.cadena = cadena;
        this.drogueria = drogueria;
    }

    // Getters y Setters -----------------------------------------------------------------------------------------------
    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
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

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getDrogueria() {
        return drogueria;
    }

    public void setDrogueria(String drogueria) {
        this.drogueria = drogueria;
    }
    // -----------------------------------------------------------------------------------------------------------------
}
