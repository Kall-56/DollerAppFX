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
}
