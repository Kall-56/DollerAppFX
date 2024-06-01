package Classes;

import java.util.ArrayList;

public class ATM extends Usuario {
    private ArrayList<Institucion> institucionList;
    private ArrayList<Farmacia> farmaciaList;
    private ArrayList<Medico> medicoList;
    private ArrayList<Evento> eventoList;
    private Administrador gerente; // Creo que al final no hara falta este atributo

    public ATM(String nomUsuario, String clave, String territorio, String email, Administrador gerente) {
        super(nomUsuario, clave, territorio, email);
        this.gerente = gerente;

        institucionList = new ArrayList<>();
        farmaciaList    = new ArrayList<>();
        medicoList      = new ArrayList<>();
        eventoList      = new ArrayList<>();
    }

    // Getters y Setters -----------------------------------------------------------------------------------------------
    public ArrayList<Institucion> getInstitucionList() {
        return institucionList;
    }

    public void setInstitucionList(ArrayList<Institucion> institucionList) {
        this.institucionList = institucionList;
    }

    public ArrayList<Farmacia> getFarmaciaList() {
        return farmaciaList;
    }

    public void setFarmaciaList(ArrayList<Farmacia> farmaciaList) {
        this.farmaciaList = farmaciaList;
    }

    public ArrayList<Medico> getMedicoList() {
        return medicoList;
    }

    public void setMedicoList(ArrayList<Medico> medicoList) {
        this.medicoList = medicoList;
    }

    public ArrayList<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(ArrayList<Evento> eventoList) {
        this.eventoList = eventoList;
    }
    // -----------------------------------------------------------------------------------------------------------------

    public void registrarCliente(Institucion inst) {
        institucionList.add(inst);
    }

    public void registrarCliente(Farmacia farm) {
        farmaciaList.add(farm);
    }

    public void registrarCliente(Medico med) {
        medicoList.add(med);
    }

    public void registrarEvento(Evento event) {
        eventoList.add(event);
    }

}
