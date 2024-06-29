package Classes;


public abstract class Cliente {
    protected String nombreCliente;
    protected String ATM;
    protected String direccion;
    protected String numTLF;
    protected char tipoIdentidad; // Si es Natural o Juridico
    protected int docIdentidad;
    protected String descripcion;

    public Cliente(String nombreCliente,
                   String nombreATM,
                   String direccion,
                   String numTLF,
                   char tipoIdentidad,
                   int docIdentidad,
                   String descripcion) {

        this.nombreCliente = nombreCliente;
        this.ATM = nombreATM;
        this.direccion = direccion;
        this.numTLF = numTLF;
        this.tipoIdentidad = tipoIdentidad;
        this.docIdentidad = docIdentidad;
        this.descripcion = descripcion;

    }



    // Getters y Setters -----------------------------------------------------------------------------------------------
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getATM() {
        return ATM;
    }

    public void setATM(String ATM) {
        this.ATM = ATM;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumTLF() {
        return numTLF;
    }

    public void setNumTLF(String numTLF) {
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

    // -----------------------------------------------------------------------------------------------------------------


}
