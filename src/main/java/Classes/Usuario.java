package Classes;

public abstract class Usuario {
    protected String nomUsuario;
    protected String clave;
    protected String territorio;
    protected String email;

    public Usuario() {
        nomUsuario = "usuarioPrueba";
        clave = "1234";
        territorio = "Caracas";
        email = "usuarioprueba@gmail.com";
    }

    public Usuario(String nomUsuario, String clave, String territorio, String email) {
        this.nomUsuario = nomUsuario;
        this.clave = clave;
        this.territorio = territorio;
        this.email = email;
    }

    // Getters y Setters -----------------------------------------------------------------------------------------------
    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTerritorio() {
        return territorio;
    }

    public void setTerritorio(String territorio) {
        this.territorio = territorio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // -----------------------------------------------------------------------------------------------------------------

    public boolean validarClave(String clave) {
        return clave.equals(this.clave);
    }

    public boolean validarUsuario(String usuario) {
        return usuario.equals(this.nomUsuario);
    }

    public void recuperarClave(String email) {
        // no estoy seguro como seria esto aun
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "nomUsuario='" + nomUsuario + '\'' +
                ", territorio='" + territorio + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
