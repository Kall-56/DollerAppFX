package Classes;

import java.util.ArrayList;

public class Administrador extends Usuario {
    private ArrayList<ATM> atmList;

    public Administrador(String nomUsuario, String clave, String territorio, String email) {
        super(nomUsuario, clave, territorio, email);
        atmList = new ArrayList<>();
    }

    // Getters y Setters -----------------------------------------------------------------------------------------------
    public ArrayList<ATM> getAtmList() {
        return atmList;
    }

    public void setAtmList(ArrayList<ATM> atmList) {
        this.atmList = atmList;
    }
    // -----------------------------------------------------------------------------------------------------------------

    public ATM getATM(int index) {
        return atmList.get(index);
    }
}
