package com.ucab.dollerappfx;

import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MenuClientesController {
//------------------------------Panel General---------------------------------//
    @FXML
    private Label lbClientesTitle;
    @FXML
    private ImageView iconCliente;
    @FXML
    private ImageView btAtras;
    
//------------------------Panel de Vista Cliente------------------------------//
    @FXML
    private Pane vistaCliente;
    @FXML
    private TextField tfBuscarCliente;
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private Pane btVerCrear;
    @FXML
    private Label lbVerCrear;
    
//------------------------Panel de Info Cliente-------------------------------//
    @FXML
    private Pane infoCliente;
    @FXML
    private Label lbClienteNombre;
    @FXML
    private Label lbCedula;
    @FXML
    private Label lbNumero;
    @FXML
    private Label direccion;
    @FXML
    private Label lbDirect;
//-----------Medico---------------//
    @FXML
    private Label especialidad;
    @FXML
    private Label lbEspecsMd;
    @FXML
    private Label diasVisita;
    @FXML
    private Label lbDiasVisitMd;
    @FXML
    private Label horario;
    @FXML
    private Label lbHorarioMd;
//-------------------------------//
    @FXML
    private Label correo;
    @FXML
    private Label lbEmailMdFm;
    @FXML
    private Label frecuencia;
    @FXML
    private Label lbFrecueMdFm;
//-----------Farmacia------------//
    @FXML
    private Label cadena;
    @FXML
    private Label lbCadenaFm;
    @FXML
    private Label drogueria;
    @FXML
    private Label lbDrogeriaFm;
//-------------------------------//
    @FXML
    private Label persContacto;
    @FXML
    private Label lbPersonContacFmInst;
//----------Institucion----------//
    @FXML
    private Label edfFacultad;
    @FXML
    private Label lbFacultadInst;
    
    
//---------------------Panel Ver Eventos--------------------------//
    @FXML
    private Pane verEventos;
    @FXML
    private TextField tfBuscarEvento;
    @FXML
    private TableView<Evento> tbEventos;
    @FXML
    private TextArea taObservaciones;
    @FXML
    private Pane btCrear;
    @FXML
    private Label lbCrear;
    @FXML
    private Pane btModificar;
    @FXML
    private Label lbModificar;
    
    
//---------------------Panel Crear Eventos-------------------------//
    @FXML
    private Pane crearEvento;
    @FXML
    private TextField tfTituloEvento;
    @FXML
    private Label lbTituloEventoError;
    @FXML
    private Label lbDescripcionError;
    @FXML
    private DatePicker fecha;
    @FXML
    private ChoiceBox<String> cbSemana;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private Pane btCrearNuevo;
    @FXML
    private Label lbCrearNuevo;
    @FXML
    private Pane btVolver;
    @FXML
    private Label lbVolver;

//------------------------Panel de Crear Cliente------------------------------//
    @FXML
    private Pane crearCliente;
    @FXML
    private Pane btCrearCliente;
    @FXML
    private Label lbCrearCliente;
    @FXML
    private Pane btVolverClientes;
    @FXML
    private Label lbVolverClientes;
    @FXML
    private TextField fldNombre;
    @FXML
    private Label lbNombreError;
    @FXML
    private ChoiceBox<String> chnumber;
    @FXML
    private TextField fldNumber;
    @FXML
    private Label lbNumeroError;
    @FXML
    private ChoiceBox<Character> chTypeCedula;
    @FXML
    private TextField fldCedula;
    @FXML
    private Label lbCedulaError;
    @FXML
    private TextField fldDirect;
    @FXML
    private Label lbDireccionError;
//-----------Medico---------------//
    @FXML
    private TextField fldEspecMd;
    @FXML
    private Label lbEspecError;
    @FXML
    private TextField fldDiasVisitMd;
    @FXML
    private Label lbDiasVisitError;
    @FXML
    private TextField fldHorarioMd;
    @FXML
    private Label lbHorarioError;
    @FXML
    private TextField fldFormatoMd;
    @FXML
    private Label lbFormatoError;
//-------------------------------//
    @FXML
    private TextField fldEmailMdFm;
    @FXML
    private Label lbEmailError;
    @FXML
    private TextField fldFrecueMdFm;
    @FXML
    private Label lbFrecueError;
//-----------Farmacia------------//
    @FXML
    private TextField fldCadenaFm;
    @FXML
    private Label lbCadenaError;
    @FXML
    private TextField fldDrogeriaFm;
    @FXML
    private Label lbDrogeriaError;
//-------------------------------//
    @FXML
    private TextField fldPersonContacFmInst;
    @FXML
    private Label lbPersonContacError;
//----------Institucion----------//
    @FXML
    private TextField fldFacultadInst;
    @FXML
    private Label lbFacultadError;

//------------------------Propiedades de Cliente------------------------------//
    private final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Cliente clienteActual;
    private Evento eventoActual;

//--------------------------Metodos del fxml----------------------------------//
    @FXML
    void btAtrasClicked(MouseEvent event) {
        infoCliente.setVisible(false);
        vistaCliente.setVisible(true);
    }
    
    public String getClientesLabel() {
        return lbClientesTitle.getText();
    }
    
    public void visibilityChange(boolean a, boolean b, boolean c) {
        infoCliente.setVisible(a);
        vistaCliente.setVisible(b);
        crearCliente.setVisible(c);
    }
    
//---------------------------Panel de Vista Cliente----------------------------------------//
    @FXML
    void manejarClicks(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 2) {
                clienteActual = tablaClientes.getSelectionModel().getSelectedItem();
                verInfoCliente(clienteActual);
            }
        }
    }
    
    public void verInfoCliente(Cliente cliente) {
        if (cliente != null) {
            tfBuscarCliente.clear();
            visibilityChange(true, false, false);
            verEventos.setVisible(true);
            crearEvento.setVisible(false);
            establecerCliente(cliente);
            filtroEventos(FXCollections.observableArrayList(cliente.getEventoList()));
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Seleccione un cliente");
            alerta.setHeaderText("No ha seleccionado ningún cliente en la tabla");
            alerta.setContentText("Haga doble click sobre un cliente para ver su información");
            alerta.showAndWait();
        }
    }
    
    public <T> void establecerClase(Class<T> clase, ObservableList<T> lista) {
        tfBuscarCliente.setText("");
        TablasController.establecerTipoTabla((TableView<T>) tablaClientes, clase, lista);
        if (clase == Medico.class) {
            lbClientesTitle.setText("Médicos");
            iconCliente.setImage(new Image("Assets/iconMdSelectB.png"));

            fldEspecMd.setVisible(true);
            fldEmailMdFm.setVisible(true);
            fldFrecueMdFm.setVisible(true);
            fldDiasVisitMd.setVisible(true);
            fldHorarioMd.setVisible(true);
            fldFormatoMd.setVisible(true);

            fldPersonContacFmInst.setVisible(false);
            fldCadenaFm.setVisible(false);
            fldDrogeriaFm.setVisible(false);
            fldFacultadInst.setVisible(false);
        }
        else if (clase == Farmacia.class) {
            lbClientesTitle.setText("Farmacias");
            iconCliente.setImage(new Image("Assets/iconFarmaB.png"));

            fldPersonContacFmInst.setVisible(true);
            fldEmailMdFm.setVisible(true);
            fldFrecueMdFm.setVisible(true);
            fldCadenaFm.setVisible(true);
            fldDrogeriaFm.setVisible(true);

            fldFormatoMd.setVisible(false);
            fldEspecMd.setVisible(false);
            fldDiasVisitMd.setVisible(false);
            fldHorarioMd.setVisible(false);
            fldFacultadInst.setVisible(false);
        }
        else if (clase == Institucion.class) {
            lbClientesTitle.setText("Instituciones");
            iconCliente.setImage(new Image("Assets/iconInstSelectB.png"));

            fldPersonContacFmInst.setVisible(true);
            fldFacultadInst.setVisible(true);

            fldFormatoMd.setVisible(false);
            fldEspecMd.setVisible(false);
            fldEmailMdFm.setVisible(false);
            fldFrecueMdFm.setVisible(false);
            fldDiasVisitMd.setVisible(false);
            fldHorarioMd.setVisible(false);
            fldCadenaFm.setVisible(false);
            fldDrogeriaFm.setVisible(false);
        }
    }
    
    public void filtroClientes(ObservableList<Cliente> lista) {
        FilteredList<Cliente> listaFiltro = new FilteredList<>(lista, p -> true);

        tfBuscarCliente.textProperty().addListener((observable, viejo, nuevo) -> {
            listaFiltro.setPredicate(cliente -> {
                if (viejo == null || nuevo.isEmpty()) {
                    return true;
                }

                String filtroMinuscula = nuevo.toLowerCase();

                if (cliente.getNombreCliente().toLowerCase().contains(filtroMinuscula)) {
                    return true;
                } else if (String.valueOf(cliente.getNumTLF()).contains(filtroMinuscula)) {
                    return true;
                } else if (cliente.getDireccion().toLowerCase().contains(filtroMinuscula)) {
                    return true;
                } else if (String.valueOf(cliente.getDocIdentidad()).contains(filtroMinuscula)) {
                    return true;
                }

                if (cliente instanceof Medico) {
                    return ((Medico) cliente).getEspecialidad().toLowerCase().contains(filtroMinuscula);
                }
                else if (cliente instanceof Farmacia) {
                    if (((Farmacia) cliente).getPersonaContacto().toLowerCase().contains(filtroMinuscula)) {
                        return true;
                    } else if (((Farmacia) cliente).getCadena().toLowerCase().contains(filtroMinuscula)) {
                        return true;
                    } else if (((Farmacia) cliente).getDrogueria().toLowerCase().contains(filtroMinuscula)) {
                        return true;
                    }
                }
                else if (cliente instanceof Institucion) {
                    if (((Institucion) cliente).getPersonaContacto().toLowerCase().contains(filtroMinuscula)) {
                        return true;
                    } else if (((Institucion) cliente).getEdfFacultad().toLowerCase().contains(filtroMinuscula)) {
                        return true;
                    }
                }
                return false;
            });
        });
        tablaClientes.setItems(listaFiltro);
    }
    
    @FXML
    void btVerCrearClicked(MouseEvent event) {
        vistaCliente.setVisible(false);
        crearCliente.setVisible(true);
    }
    
    @FXML
    void btVerCrearEntered(MouseEvent event) {
        btVerCrear.setStyle("-fx-background-color: #FF7B52;"+"-fx-border-color: white;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbVerCrear.setTextFill(Color.WHITE);
    }
    
    @FXML
    void btVerCrearExited(MouseEvent event) {
        btVerCrear.setStyle("-fx-background-color: white;"+"-fx-border-color: #FF7B52;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbVerCrear.setTextFill(Color.web("#FF7B52"));
    }
    
//---------------------------Panel de Info Cliente----------------------------------------//
    // Método para cambiar los labels correspondientes al cliente
    public void establecerCliente(Cliente cliente) {
        lbClienteNombre.setText(cliente.getNombreCliente());
        lbCedula.setText(cliente.getTipoIdentidad()+" "+cliente.getDocIdentidad());
        lbNumero.setText(cliente.getNumTLF());
        lbDirect.setText(cliente.getDireccion());
        taObservaciones.setText(cliente.getDescripcion());

        if (cliente instanceof Medico) {
            lbEmailMdFm.setText(((Medico) cliente).getEmail());
            lbEmailMdFm.setVisible(true);
            correo.setVisible(true);

            lbDiasVisitMd.setText(((Medico) cliente).getDiasVisita());
            lbDiasVisitMd.setVisible(true);
            diasVisita.setVisible(true);

            lbEspecsMd.setText(((Medico) cliente).getEspecialidad());
            lbEspecsMd.setVisible(true);
            especialidad.setVisible(true);

            lbFrecueMdFm.setText(""+((Medico) cliente).getFrecuencia());
            lbFrecueMdFm.setVisible(true);
            frecuencia.setVisible(true);

            lbHorarioMd.setText(((Medico) cliente).getHorario());
            lbHorarioMd.setVisible(true);
            horario.setVisible(true);


            lbCadenaFm.setVisible(false);
            cadena.setVisible(false);
            lbDrogeriaFm.setVisible(false);
            drogueria.setVisible(false);
            lbPersonContacFmInst.setVisible(false);
            persContacto.setVisible(false);
            lbFacultadInst.setVisible(false);
            edfFacultad.setVisible(false);
        } else if (cliente instanceof Institucion) {
            lbPersonContacFmInst.setText(((Institucion) cliente).getPersonaContacto());
            lbPersonContacFmInst.setVisible(true);
            persContacto.setVisible(true);

            lbFacultadInst.setText(((Institucion) cliente).getEdfFacultad());
            lbFacultadInst.setVisible(true);
            edfFacultad.setVisible(true);

            lbEmailMdFm.setVisible(false);
            correo.setVisible(false);
            lbDiasVisitMd.setVisible(false);
            diasVisita.setVisible(false);
            lbEspecsMd.setVisible(false);
            especialidad.setVisible(false);
            lbFrecueMdFm.setVisible(false);
            frecuencia.setVisible(false);
            lbHorarioMd.setVisible(false);
            horario.setVisible(false);
            lbCadenaFm.setVisible(false);
            cadena.setVisible(false);
            lbDrogeriaFm.setVisible(false);
            drogueria.setVisible(false);
        } else if (cliente instanceof Farmacia) {
            lbEmailMdFm.setText(((Farmacia) cliente).getEmail());
            lbEmailMdFm.setVisible(true);
            correo.setVisible(true);

            lbFrecueMdFm.setText(""+((Farmacia) cliente).getFrecuencia());
            lbFrecueMdFm.setVisible(true);
            frecuencia.setVisible(true);

            lbPersonContacFmInst.setText(((Farmacia) cliente).getPersonaContacto());
            lbPersonContacFmInst.setVisible(true);
            persContacto.setVisible(true);

            lbCadenaFm.setText(((Farmacia) cliente).getCadena());
            lbCadenaFm.setVisible(true);
            cadena.setVisible(true);

            lbDrogeriaFm.setText(((Farmacia) cliente).getDrogueria());
            lbDrogeriaFm.setVisible(true);
            drogueria.setVisible(true);

            lbEspecsMd.setVisible(false);
            especialidad.setVisible(false);
            lbDiasVisitMd.setVisible(false);
            diasVisita.setVisible(false);
            lbHorarioMd.setVisible(false);
            horario.setVisible(false);
            lbFacultadInst.setVisible(false);
            edfFacultad.setVisible(false);
        }
        tbEventos.setItems(FXCollections.observableArrayList(cliente.getEventoList()));
        tbEventos.refresh();
    }
    
    public void filtroEventos(ObservableList<Evento> lista) {
        FilteredList<Evento> listaFiltro = new FilteredList<>(lista, p -> true);

        tfBuscarEvento.textProperty().addListener((observable, viejo, nuevo) -> {
            listaFiltro.setPredicate(evento -> {
                if (viejo == null || nuevo.isEmpty()) {
                    return true;
                }

                String filtroMinuscula = nuevo.toLowerCase();

                if (evento.getTitulo().toLowerCase().contains(filtroMinuscula)) {
                    return true;
                } else if (evento.getFecha().contains(filtroMinuscula)) {
                    return true;
                } else if (evento.getDescripcion().contains(filtroMinuscula)) {
                    return true;
                }
                return false;
            });
        });
        tbEventos.setItems(listaFiltro);
    }
    
    public int obtenerSemana(ChoiceBox<String> semana) {
        String s = semana.getValue().replace("Semana", "").trim();
        return Integer.parseInt(s);
    }
    
    public void reiniciarCamposEvento() {
        tfTituloEvento.clear();
        tfTituloEvento.setStyle("");
        lbTituloEventoError.setVisible(false);
        taDescripcion.clear();
        taDescripcion.setStyle("");
        lbDescripcionError.setVisible(false);
        fecha.getEditor().clear();
        cbSemana.setValue("Semana 1");
    }
    
    @FXML
    void btCrearPressed(MouseEvent event) {
        verEventos.setVisible(false);
        crearEvento.setVisible(true);

        lbCrearNuevo.setText("Crear");
    }
    
    @FXML
    void btCrearEntered(MouseEvent event) {
        btCrear.setStyle("-fx-background-color: #FF7B52;"+"-fx-border-color: white;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbCrear.setTextFill(Color.WHITE);
    }
    
    @FXML
    void btCrearExited(MouseEvent event) {
        btCrear.setStyle("-fx-background-color: white;"+"-fx-border-color: #FF7B52;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbCrear.setTextFill(Color.web("#FF7B52"));
    }
    
    @FXML
    void btCrearNuevoPressed(MouseEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        boolean titulo = ValidacionesController.validarCampos(tfTituloEvento, "^.{1,30}$", lbTituloEventoError);
        boolean descripcion = ValidacionesController.validarCampos(taDescripcion, "^[\\s\\S]{0,100}$",lbDescripcionError);
        LocalDate date = fecha.getValue();
        
        if (titulo && descripcion && (date != null)) {
            Evento evento = new Evento(tfTituloEvento.getText(),
                    clienteActual.getNombreCliente(),
                    formato.format(date),
                    obtenerSemana(cbSemana),
                    taDescripcion.getText());

            if (lbCrearNuevo.getText().equals("Crear")) {
                clienteActual.registrarEvento(evento);
                App.usuario.registrarEvento(evento);

                alerta.setContentText("EVENTO AGREGADO CON ÉXITO");
                alerta.setTitle("Evento creado");

            } else if (lbCrearNuevo.getText().equals("Modificar")) {
                clienteActual.eliminarEvento(eventoActual);
                clienteActual.registrarEvento(evento);
                App.usuario.eliminarEvento(eventoActual);
                App.usuario.registrarEvento(evento);

                alerta.setContentText("EVENTO MODIFICADO CON ÉXITO");
                alerta.setTitle("Evento modificado");
            }

            reiniciarCamposEvento();
            verEventos.setVisible(true);
            crearEvento.setVisible(false);
            tbEventos.setItems(FXCollections.observableArrayList(clienteActual.getEventoList()));
            tbEventos.refresh();
        } else {

            alerta.setContentText("ALGÚN ATRIBUTO ERRÓNEO");
            alerta.setTitle("Atributo erróneo");
        }
        alerta.setHeaderText("");
        alerta.showAndWait();
    }
    
    @FXML
    void btCrearNuevoEntered(MouseEvent event) {
        btCrearNuevo.setStyle("-fx-background-color: #FF7B52;"+"-fx-border-color: white;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbCrearNuevo.setTextFill(Color.WHITE);
    }
    
    @FXML
    void btCrearNuevoExited(MouseEvent event) {
        btCrearNuevo.setStyle("-fx-background-color: white;"+"-fx-border-color: #FF7B52;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbCrearNuevo.setTextFill(Color.web("#FF7B52"));
    }
    
    @FXML
    void btModificarPressed(MouseEvent event) {
        eventoActual = tbEventos.getSelectionModel().getSelectedItem();
        if (eventoActual != null) {
            lbCrearNuevo.setText("Modificar");
            tfTituloEvento.setText(eventoActual.getTitulo());
            LocalDate date = LocalDate.parse(eventoActual.getFecha(), formato);
            fecha.setValue(date);
            taDescripcion.setText(eventoActual.getDescripcion());
            cbSemana.setValue("Semana " + eventoActual.getSemana());

            verEventos.setVisible(false);
            crearEvento.setVisible(true);
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Seleccione un evento");
            alerta.setHeaderText("");
            alerta.setContentText("No ha seleccionado ningún evento en la tabla");
            alerta.showAndWait();
        }
    }
    
    @FXML
    void btModificarEntered(MouseEvent event) {
        btModificar.setStyle("-fx-background-color: #ffa24d;"+"-fx-border-color: white;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbModificar.setTextFill(Color.WHITE);
    }
    
    @FXML
    void btModificarExited(MouseEvent event) {
        btModificar.setStyle("-fx-background-color: white;"+"-fx-border-color: orange;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbModificar.setTextFill(Color.web("#ffa24d"));
    }
    
    @FXML
    void volverPressed(MouseEvent event) {
        verEventos.setVisible(true);
        crearEvento.setVisible(false);
        reiniciarCamposEvento();
    }

    @FXML
    void btVolverEntered(MouseEvent event) {
        btVolver.setStyle("-fx-background-color: #ffa24d;"+"-fx-border-color: white;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbVolver.setTextFill(Color.WHITE);
    }
    
    @FXML
    void btVolverExited(MouseEvent event) {
        btVolver.setStyle("-fx-background-color: white;"+"-fx-border-color: orange;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbVolver.setTextFill(Color.web("#ffa24d"));
    }
//---------------------------Panel de Crear Cliente----------------------------------------//
    public void reinicarCamposCliente() {
        fldNombre.clear();
        fldNombre.setStyle("");
        lbNombreError.setVisible(false);
        fldNumber.clear();
        fldNumber.setStyle("");
        lbNumeroError.setVisible(false);
        fldCedula.clear();
        fldCedula.setStyle("");
        lbCedulaError.setVisible(false);
        fldDirect.clear();
        fldDirect.setStyle("");
        lbDireccionError.setVisible(false);
        fldEspecMd.clear();
        fldEspecMd.setStyle("");
        lbEspecError.setVisible(false);
        fldEmailMdFm.clear();
        fldEmailMdFm.setStyle("");
        lbEmailError.setVisible(false);
        fldFrecueMdFm.clear();
        fldFrecueMdFm.setStyle("");
        lbFrecueError.setVisible(false);
        fldDiasVisitMd.clear();
        fldDiasVisitMd.setStyle("");
        lbDiasVisitError.setVisible(false);
        fldHorarioMd.clear();
        fldHorarioMd.setStyle("");
        lbHorarioError.setVisible(false);
        fldFormatoMd.clear();
        fldFormatoMd.setStyle("");
        lbFormatoError.setVisible(false);
        fldPersonContacFmInst.clear();
        fldPersonContacFmInst.setStyle("");
        lbPersonContacError.setVisible(false);
        fldCadenaFm.clear();
        fldCadenaFm.setStyle("");
        lbCadenaError.setVisible(false);
        fldDrogeriaFm.clear();
        fldDrogeriaFm.setStyle("");
        lbDrogeriaError.setVisible(false);
        fldFacultadInst.clear();
        fldFacultadInst.setStyle("");
        lbFacultadError.setVisible(false);
        chnumber.setValue("0212");
        chTypeCedula.setValue('V');
    }
    
    @FXML
    void btCrearClienteClicked(MouseEvent event) {
        String clase = lbClientesTitle.getText();

        boolean nombreValido = ValidacionesController.validarCampos(fldNombre, "^[a-zA-Z ]{1,50}$", lbNombreError);
        boolean numValido = ValidacionesController.validarCampos(fldNumber, "^[0-9]{7}$", lbNumeroError);
        boolean ciValido = ValidacionesController.validarCampos(fldCedula, "^[0-9]{6,9}$", lbCedulaError);
        boolean direcValido = ValidacionesController.validarCampos(fldDirect, "^[a-zA-Z0-9#. ]{1,70}$", lbDireccionError);

        boolean[] atributosValidos = {nombreValido, numValido, ciValido, direcValido};

        if (clase.equals("Médicos")) {
            boolean espValido = ValidacionesController.validarCampos(fldEspecMd, "^[a-zA-Z ]{1,20}$", lbEspecError);
            boolean emailValido = ValidacionesController.validarCampos(fldEmailMdFm, "^[A-Za-z0-9+_.-]+@(.+)$", lbEmailError);
            boolean frecValido = ValidacionesController.validarCampos(fldFrecueMdFm, "^[0-9]{1}$", lbFrecueError);
            boolean diasValido = ValidacionesController.validarCampos(fldDiasVisitMd, "^[a-zA-Z- ]{1,50}$", lbDiasVisitError);
            boolean horarioValido = ValidacionesController.validarCampos(fldHorarioMd, "^[0-9- ]{1,10}$", lbHorarioError);
            boolean formatoValido = ValidacionesController.validarCampos(fldFormatoMd, "^[a-zA-Z ]{1,20}$", lbFormatoError);
            atributosValidos = new boolean[] {nombreValido, numValido, ciValido, direcValido, espValido, emailValido, frecValido, diasValido, horarioValido, formatoValido};

        } else if (clase.equals("Farmacias")) {
            boolean perValido = ValidacionesController.validarCampos(fldPersonContacFmInst, "^[a-zA-Z ]{1,20}$", lbPersonContacError);
            boolean emailValido = ValidacionesController.validarCampos(fldEmailMdFm, "^[A-Za-z0-9+_.-]+@(.+)$", lbEmailError);
            boolean frecValido = ValidacionesController.validarCampos(fldFrecueMdFm, "^[0-9]{1}$", lbFrecueError);
            boolean cadValido = ValidacionesController.validarCampos(fldCadenaFm, "^[a-zA-Z ]{1,20}$", lbCadenaError);
            boolean drogValido = ValidacionesController.validarCampos(fldDrogeriaFm, "^[a-zA-Z ]{1,20}$", lbDrogeriaError);

            atributosValidos = new boolean[] {nombreValido, numValido, ciValido, direcValido, perValido, emailValido, frecValido, cadValido, drogValido};
        } else if (clase.equals("Instituciones")) {
            boolean perValido = ValidacionesController.validarCampos(fldPersonContacFmInst, "^[a-zA-Z ]{1,20}$", lbPersonContacError);
            boolean facValido = ValidacionesController.validarCampos(fldFacultadInst, "^[a-zA-Z ]{1,30}$", lbFacultadError);

            atributosValidos = new boolean[] {nombreValido, numValido, ciValido, direcValido, perValido, facValido};
        }

        boolean valido = true;
        for (boolean atributo: atributosValidos) {
            if (!atributo) {
                valido = false;
            }
        }
        if (valido) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Crear Cliente");
            alerta.setHeaderText("ESTÁ SEGURO DE CREAR EL CLIENTE?");
            alerta.setContentText("UNA VEZ CREADO NO SE PUEDE MODIFICAR");
            ButtonType confirmButton = ButtonType.OK;
            ButtonType cancelButton = ButtonType.CANCEL;

            alerta.getButtonTypes().setAll(confirmButton, cancelButton);

            Optional<ButtonType> result = alerta.showAndWait();

            if (result.isPresent() && result.get() == confirmButton) {
                switch (clase) {
                    case "Médicos":
                        Medico med = new Medico(fldNombre.getText(),
                                fldDirect.getText(),
                                chnumber.getValue() + fldNumber.getText(),
                                chTypeCedula.getValue(),
                                Integer.parseInt(fldCedula.getText()),
                                "",
                                fldEspecMd.getText(),
                                fldEmailMdFm.getText(),
                                Integer.parseInt(fldFrecueMdFm.getText()),
                                fldDiasVisitMd.getText(),
                                fldHorarioMd.getText(),
                                fldFormatoMd.getText());
                        App.usuario.registrarCliente(med);
                        establecerClase(Medico.class, FXCollections.observableArrayList(App.usuario.getMedicoList()));
                        filtroClientes(FXCollections.observableArrayList(App.usuario.getMedicoList()));
                        break;
                    case "Farmacias":
                        Farmacia far = new Farmacia(fldNombre.getText(),
                                fldDirect.getText(),
                                chnumber.getValue() + fldNumber.getText(),
                                chTypeCedula.getValue(),
                                Integer.parseInt(fldCedula.getText()),
                                "",
                                fldPersonContacFmInst.getText(),
                                fldEmailMdFm.getText(),
                                Integer.parseInt(fldFrecueMdFm.getText()),
                                fldCadenaFm.getText(),
                                fldDrogeriaFm.getText());
                        App.usuario.registrarCliente(far);
                        establecerClase(Farmacia.class, FXCollections.observableArrayList(App.usuario.getFarmaciaList()));
                        filtroClientes(FXCollections.observableArrayList(App.usuario.getFarmaciaList()));
                        break;
                    case "Instituciones":
                        Institucion inst = new Institucion(fldNombre.getText(),
                                fldDirect.getText(),
                                chnumber.getValue() + fldNumber.getText(),
                                chTypeCedula.getValue(),
                                Integer.parseInt(fldCedula.getText()),
                                "",
                                fldPersonContacFmInst.getText(),
                                fldFacultadInst.getText());
                        App.usuario.registrarCliente(inst);
                        establecerClase(Institucion.class, FXCollections.observableArrayList(App.usuario.getInstitucionList()));
                        filtroClientes(FXCollections.observableArrayList(App.usuario.getInstitucionList()));
                        break;
                }
                Alert alerta2 = new Alert(Alert.AlertType.INFORMATION);
                alerta2.setHeaderText("");
                alerta2.setContentText("CLIENTE AGREGADO CON ÉXITO");
                alerta2.setTitle("Cliente creado");

                reinicarCamposCliente();
                vistaCliente.setVisible(true);
                crearCliente.setVisible(false);
                tablaClientes.refresh();
                alerta2.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText("");
            alerta.setContentText("Revise que no haya ningún campo con formato erróneo");
            alerta.setTitle("Error");
            alerta.showAndWait();
        }
    }
    
    @FXML
    void btCrearClienteEntered(MouseEvent event) {
        btCrearCliente.setStyle("-fx-background-color: #FF7B52;"+"-fx-border-color: white;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbCrearCliente.setTextFill(Color.WHITE);
    }
    
    @FXML
    void btCrearClienteExited(MouseEvent event) {
        btCrearCliente.setStyle("-fx-background-color: white;"+"-fx-border-color: #FF7B52;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbCrearCliente.setTextFill(Color.web("#FF7B52"));
    }
    
    @FXML
    void btVolverClientesPressed(MouseEvent event) {
        vistaCliente.setVisible(true);
        crearCliente.setVisible(false);

        reinicarCamposCliente();
    }

    @FXML
    void btVolverClientesEntered(MouseEvent event) {
        btVolverClientes.setStyle("-fx-background-color: #ffa24d;"+"-fx-border-color: white;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbVolverClientes.setTextFill(Color.WHITE);
    }
    
    @FXML
    void btVolverClientesExited(MouseEvent event) {
        btVolverClientes.setStyle("-fx-background-color: white;"+"-fx-border-color: orange;"+"-fx-background-radius: 7;"+"-fx-border-radius: 3;");
        lbVolverClientes.setTextFill(Color.web("#ffa24d"));
    }
//----------------------------------------------------------------------------//
    public void initialize() {
        // La Tabla de Eventos
        tbEventos.getColumns().clear();

        TableColumn<Evento, String> nombreCol = new TableColumn<>("Título Actividad");
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        TableColumn<Evento, String> fechaCol = new TableColumn<>("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<Evento, String> semanaCol = new TableColumn<>("Semana");
        semanaCol.setCellValueFactory(new PropertyValueFactory<>("semana"));

        TableColumn<Evento, String> descripCol = new TableColumn<>("Descripción");
        descripCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tbEventos.getColumns().add(nombreCol);
        tbEventos.getColumns().add(fechaCol);
        tbEventos.getColumns().add(semanaCol);
        tbEventos.getColumns().add(descripCol);

        ObservableList<String> opciones = FXCollections.observableArrayList("Semana 1", "Semana 2", "Semana 3", "Semana 4", "Semana 5");
        cbSemana.setItems(opciones);
        cbSemana.setValue("Semana 1");

        opciones = FXCollections.observableArrayList("0212", "0412", "0414", "0416", "0424", "0426");
        chnumber.setItems(opciones);
        chnumber.setValue("0212");

        ObservableList<Character> opciones2 = FXCollections.observableArrayList('V', 'J');
        chTypeCedula.setItems(opciones2);
        chTypeCedula.setValue('V');
        
        //----------------------------------------------------------------------------//
        taObservaciones.textProperty().addListener(((observable, oldValue, newValue) -> {
            clienteActual.setDescripcion(newValue);
        }));

        btAtras.setVisible(false);
        infoCliente.visibleProperty().addListener((observable, oldValue, newValue) -> {
            btAtras.setVisible(newValue);
            if (newValue) {
                tfBuscarEvento.clear();
                tbEventos.getSelectionModel().clearSelection();
            } else {
                tablaClientes.refresh();
                tablaClientes.getSelectionModel().clearSelection();
            }
        });

        crearEvento.visibleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && !lbCrearNuevo.getText().equals("Modificar")) {
                reiniciarCamposEvento();
            }
        });
        
        //----------------------------------------------------------------------------//
        crearCliente.visibleProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                reinicarCamposCliente();
            }
        }));

        fldNombre.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldNombre, "^[a-zA-Z ]{0,50}$", lbNombreError);
            }
        });
        fldNumber.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldNumber, "^[0-9]{7}$", lbNumeroError);
            }
        });
        fldCedula.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldCedula, "^[0-9]{6,9}$", lbCedulaError);
            }
        });
        fldDirect.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldDirect, "^[a-zA-Z0-9#. ]{0,70}$", lbDireccionError);
            }
        });
        fldEspecMd.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldEspecMd, "^[a-zA-Z ]{0,20}$", lbEspecError);
            }
        });
        fldEmailMdFm.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldEmailMdFm, "^[A-Za-z0-9+_.-]+@(.+)$", lbEmailError);
            }
        });
        fldFrecueMdFm.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldFrecueMdFm, "^[0-9]{1}$", lbFrecueError);
            }
        });
        fldDiasVisitMd.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldDiasVisitMd, "^[a-zA-Z- ]{0,50}$", lbDiasVisitError);
            }
        });
        fldHorarioMd.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldHorarioMd, "^[0-9- ]{0,10}$", lbHorarioError);
            }
        });
        fldFormatoMd.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldFormatoMd, "^[a-zA-Z ]{0,20}$", lbFormatoError);
            }
        });
        fldCadenaFm.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldCadenaFm, "^[a-zA-Z ]{0,20}$", lbCadenaError);
            }
        });
        fldDrogeriaFm.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldDrogeriaFm, "^[a-zA-Z ]{0,20}$", lbDrogeriaError);
            }
        });
        fldPersonContacFmInst.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldPersonContacFmInst, "^[a-zA-Z ]{0,20}$", lbPersonContacError);
            }
        });
        fldFacultadInst.focusedProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue) {
                ValidacionesController.validarCampos(fldFacultadInst, "^[a-zA-Z ]{0,30}$", lbFacultadError);
            }
        });
    }
}
