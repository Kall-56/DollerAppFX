package Classes;


public class ATM extends Usuario {
    
    private String gerente; // Creo que al final no hara falta este atributo

    public ATM() {
        super();
        this.gerente = "LiaUCAB()";

    }

    public ATM(String nomUsuario, String clave, String territorio, String email, String gerente) {
        super(nomUsuario, clave, territorio, email);
        this.gerente = gerente;
    }

    // Getters y Setters -----------------------------------------------------------------------------------------------
    
    public String getGerente() {
        return gerente;
    }
    
    public void setGerente(String gerente) {
        this.gerente = gerente;
    }
    
    
    
    // -----------------------------------------------------------------------------------------------------------------





}
