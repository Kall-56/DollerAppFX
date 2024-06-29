package ManejadorBD;

import Classes.ATM;
import Classes.Administrador;
import Classes.Evento;
import Classes.Farmacia;
import Classes.Institucion;
import Classes.Medico;
import java.sql.*;
import com.mysql.jdbc.*;
import java.util.ArrayList;


public class ManejadorBD {
    
    private Connection conexion;
    
    public ManejadorBD() throws ClassNotFoundException{
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion = DriverManager.getConnection("jdbc:mysql://avnadmin:AVNS_Qh5C9Xh7CDKUWx5cM11@mysql-server-app-dollder-2024-app-dollder-2024.g.aivencloud.com:11773/appdollder?ssl-mode=REQUIRED?ssl-mode=REQUIRED", "avnadmin", "AVNS_Qh5C9Xh7CDKUWx5cM11");
        } catch (SQLException sQLException) {
            this.conexion = null;
        }
    }
    
    public void cerrar() throws SQLException{
        conexion.close();
    }
    
    
    public  boolean verificarUsuarioATM(String nombre, String clave ) throws ClassNotFoundException{
       int cantidadDeUsuarios = 0;
       try{
            String instruccion = "select * from atms where NOMBRE = '" + nombre + "' and CLAVE = '" + clave + "'";
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            ResultSet usuariosCoinciden = stm.executeQuery(instruccion);
            while (usuariosCoinciden.next()) {
                cantidadDeUsuarios++;
            }
            
            usuariosCoinciden.close();
            stm.close();
       } catch (SQLException ex){
           System.err.println(ex);
       }
       return (cantidadDeUsuarios == 1);
   }
   
    public  Administrador retornarUsuarioADMIN(String nombre ) throws ClassNotFoundException{
       Administrador user = null;
       try{
        String instruccion = "select * from admin where NOMBRE = '" + nombre + "'";
       Statement stm = conexion.createStatement();
       ResultSet admin = stm.executeQuery(instruccion);
       admin.next();
       user = new Administrador(admin.getString("NOMBRE"), admin.getString("CLAVE"), admin.getString("TERRITORIO"), admin.getString("EMAIL"));
       
       admin.close();
       stm.close();
       } catch (SQLException ex){
           System.err.println(ex);
       }
       return user;
   }
   
    public ATM retornarUsuarioATM(String nombre ) throws ClassNotFoundException{
       ATM user = null;
       try{
       String instruccion = "select * from atms where NOMBRE = '" + nombre + "'";
       Statement stm = conexion.createStatement();
       ResultSet admin = stm.executeQuery(instruccion);
       admin.next();
       user = new ATM(admin.getString("NOMBRE"), admin.getString("CLAVE"), admin.getString("TERRITORIO"), admin.getString("EMAIL"), admin.getString("GERENTE"));
       
       admin.close();
       stm.close();
       } catch (SQLException ex){
           System.err.println(ex);
       }
       return user;
   }   
      
    public boolean VerificarUsuarioADMIN(String nombre, String clave ) throws ClassNotFoundException{
       int cantidadDeUsuarios = 0;
       try{
       String instruccion = "select * from admin where NOMBRE = '" + nombre + "' and CLAVE = '" + clave + "'";
       Statement stm = conexion.createStatement();
       ResultSet usuariosCoinciden = stm.executeQuery(instruccion);
       while (usuariosCoinciden.next()) {
           cantidadDeUsuarios++;
       }
       
       usuariosCoinciden.close();
       stm.close();
       } catch (SQLException ex){
           System.err.println(ex);
       }
       return (cantidadDeUsuarios == 1);
   }
   
    public void AgregarUsuarioABaseDeDatos(String nombre, String clave, String territorio, String email, String gerente ) throws ClassNotFoundException{
      try{
        String instruccion = "insert into atms values ('"+ nombre +"', '" + clave + "','" + territorio +"', '" + email + "','" + gerente +"')" ;
        System.out.println(instruccion);
        Statement stm = conexion.createStatement();
        stm.executeUpdate(instruccion);
        
        stm.close();   
      } catch(SQLException ex){
          System.err.println(ex);
      }
    }

    public ArrayList<Farmacia> DevolverFarmacias(String nombreATM) throws ClassNotFoundException {
       ArrayList<Farmacia> retornoFarmacias = new ArrayList<Farmacia>();
      try{
        String instruccion = "select * from farmacia where ATM = '" + nombreATM + "'" ;
        System.out.println(instruccion);
        Statement stm = conexion.createStatement();
        ResultSet farmacias = stm.executeQuery(instruccion);
        
        while (farmacias.next()){
            retornoFarmacias.add(new Farmacia(farmacias.getString("FARMACIA"),
                                              farmacias.getString("ATM"), 
                                              farmacias.getString("Direccion"), 
                                              farmacias.getString("TELEFONO"),
                                              'J', 
                                              farmacias.getInt("RIF"),
                                              farmacias.getString("DESCRIPCION"),
                                              farmacias.getString("PERSONA_DE_CONTACTO"),
                                              farmacias.getString("CORREO"),
                                              farmacias.getInt("FREC"),
                                              farmacias.getString("CADENA"),
                                              farmacias.getString("DROGUERIA")));
        }
        farmacias.close();
        
        stm.close();   
      } catch(SQLException ex){
          System.err.println(ex);
      }
      return retornoFarmacias;
    }

    public ArrayList<Medico> DevolverMedicos(String nombreATM) throws ClassNotFoundException {
       ArrayList<Medico> retornoMedicos = new ArrayList<Medico>();
      try{
        String instruccion = "select * from medico where ATM = '" + nombreATM + "'" ;
        System.out.println(instruccion);
        Statement stm = conexion.createStatement();
        ResultSet medicos = stm.executeQuery(instruccion);
        
        while (medicos.next()){
            retornoMedicos.add(new Medico(medicos.getString("MEDICO"),
                                              medicos.getString("ATM"), 
                                              medicos.getString("DIRECCION"), 
                                              medicos.getString("TELEFONO"),
                                              'I', 
                                              medicos.getInt("CEDULA"),
                                              medicos.getString("DESCRIPCION"),
                                              medicos.getString("ESPECIALIDAD"),
                                              medicos.getString("CORREO"),
                                              medicos.getInt("FRECUENCIA"),
                                              medicos.getString("DIAS_DE_VISITA"),
                                              medicos.getString("HORARIO_DE_VISITA"),
                                              medicos.getString("FORMATO_DE_VISITA")));
        }
        medicos.close();
        
        stm.close();   
      } catch(SQLException ex){
          System.err.println(ex);
      }
      return retornoMedicos;
    }

    public ArrayList<Institucion> DevolverInstitucion(String nombreATM) throws ClassNotFoundException {
       ArrayList<Institucion> retornoInstitucion = new ArrayList<Institucion>();
        try{
        String instruccion = "select * from institucion where ATM = '" + nombreATM + "'" ;
        System.out.println(instruccion);
        Statement stm = conexion.createStatement();
        ResultSet instituciones = stm.executeQuery(instruccion);
        
        while (instituciones.next()){
            retornoInstitucion.add(new Institucion(instituciones.getString("INSTITUCION"),
                                              instituciones.getString("ATM"), 
                                              instituciones.getString("DIRECCION"), 
                                              instituciones.getString("TELEFONO"),
                                              'J', 
                                              instituciones.getInt("RIF"),
                                              instituciones.getString("DESCRIPCION"),
                                              instituciones.getString("PERSONA_DE_CONTACTO"),
                                              instituciones.getString("EDIFICIO")));
        }
        instituciones.close();
        
        stm.close();   
      } catch(SQLException ex){
          System.err.println(ex);
      }
      return retornoInstitucion;
    }
  
    public ArrayList<Evento> DevolverEvento (String nombreATM) throws ClassNotFoundException {
       ArrayList<Evento> retornosEventos = new ArrayList<Evento>();
        try{
            String instruccion = "select * from eventos where ATM = " + nombreATM + "'" ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            ResultSet eventos = stm.executeQuery(instruccion);

            while (eventos.next()){
                retornosEventos.add(new Evento(eventos.getString("TITULO"),
                                                  eventos.getString("CLIENTE"),
                                                  eventos.getString("FECHA"),
                                                  eventos.getInt("SEMANA"),
                                                  eventos.getString("DESCRIPCION")));
            }
            eventos.close();
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
      return retornosEventos;
    }
  
    public ArrayList<Evento> DevolverEventoADMIN (String nombreADMIN) throws ClassNotFoundException {
       ArrayList<Evento> retornosEventos = new ArrayList<Evento>();
        try{
            String instruccion = "select * from atms where GERENTE = '" + nombreADMIN + "'" ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            ResultSet ATM = stm.executeQuery(instruccion);

            while (ATM.next()){
                retornosEventos.addAll(DevolverEvento(ATM.getString("NOMBRE")));

            }
            ATM.close();
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
      return retornosEventos;
    }
  
    public ArrayList<Farmacia> DevolverFarmaciaADMIN (String nombreADMIN) throws ClassNotFoundException {

       ArrayList<Farmacia> retornosFarmacia = new ArrayList<Farmacia>();
        try{
            String instruccion = "select * from atms where GERENTE = '" + nombreADMIN + "'" ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            ResultSet ATM = stm.executeQuery(instruccion);

            while (ATM.next()){
                retornosFarmacia.addAll(DevolverFarmacias(ATM.getString("NOMBRE")));

            }
            ATM.close();
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
      return retornosFarmacia;
    }
  
    public ArrayList<Medico> DevolverMedicoADMIN (String nombreADMIN) throws ClassNotFoundException {

       ArrayList<Medico> retornosMedico = new ArrayList<Medico>();
        try{
            
            
            String instruccion = "select * from atms where GERENTE = '" + nombreADMIN + "'" ;
            Statement stm = conexion.createStatement();
            ResultSet ATM = stm.executeQuery(instruccion);

            while (ATM.next()){
                retornosMedico.addAll(DevolverMedicos(ATM.getString("NOMBRE")));

            }
            ATM.close();
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
      return retornosMedico;
    }
  
    public ArrayList<Institucion> DevolverInstitucionADMIN (String nombreADMIN) throws ClassNotFoundException {

       ArrayList<Institucion> retornosInstitucion = new ArrayList<Institucion>();
        try{
            
                    String instruccion = "select * from atms where GERENTE = '" + nombreADMIN + "'" ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            ResultSet ATM = stm.executeQuery(instruccion);

            while (ATM.next()){
                retornosInstitucion.addAll(DevolverInstitucion(ATM.getString("NOMBRE")));

            }
            ATM.close();
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
      return retornosInstitucion;
    }
  
    public ArrayList<ATM> DevolverATM (String nombreADMIN) throws ClassNotFoundException {

       ArrayList<ATM> retornosAtms = new ArrayList<ATM>();
        try{
            
                    String instruccion = "select * from atms where gerente = '" + nombreADMIN + "'" ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            ResultSet ATM = stm.executeQuery(instruccion);

            while (ATM.next()){
                retornosAtms.add(new ATM(ATM.getString("NOMBRE"),ATM.getString("CLAVE"),ATM.getString("TERRITORIO"),ATM.getString("EMAIL"),ATM.getString("GERENTE")));

            }
            ATM.close();
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
      return retornosAtms;
    }
    
    public void registrarFarmacia (String FARMACIA, String RIF, String GERENTE, String ATM, String DIRECCION, String CORREO, String TELEFONO, String PERSONA_DE_CONTACTO, String FREC, String CADENA, String DROGUERIA, String DESCRIPCION ) throws ClassNotFoundException {

      try{
        
        String instruccion = "insert into farmacia  values (' " + FARMACIA + "', " +
                                                            RIF + ", '" +
                                                            GERENTE  + "', '" +
                                                            ATM  + "', '" +
                                                            DIRECCION  + "', '" +
                                                            CORREO + "', " +
                                                            TELEFONO + ", '" +
                                                            PERSONA_DE_CONTACTO + "', " +
                                                            FREC  + "  , '" +
                                                            CADENA + "', '" +
                                                            DROGUERIA + "', '" +
                                                            DESCRIPCION+ "')"  ;
        System.out.println(instruccion);
        Statement stm = conexion.createStatement();
        stm.executeUpdate(instruccion);
        
        stm.close();   
      } catch(SQLException ex){
          System.err.println(ex);
      }
    }
    
    public void ElimiarFarmacia (String RIF) throws ClassNotFoundException {

        try{
            
                    String instruccion = "delete from farmacia where RIF = " + RIF + "" ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            stm.executeUpdate(instruccion);
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
    }

    public void registrarInstitucion (String INSTITUCION, String RIF, String GERENTE, String ATM, String DIRECCION, String CORREO, String TELEFONO, String PERSONA_DE_CONTACTO, String EDIFICIO, String FREC, String DESCRIPCION ) throws ClassNotFoundException {

        try{
            
                    String instruccion = "insert into institucion  values (' " + INSTITUCION + "', " +
                                                                RIF + ", '" +
                                                                GERENTE  + "', '" +
                                                                ATM  + "', '" +
                                                                DIRECCION  + "', '" +
                                                                CORREO + "', " +
                                                                TELEFONO + ", '" +
                                                                PERSONA_DE_CONTACTO + "', " +
                                                                FREC  + "  , '" +
                                                                EDIFICIO + "', '" +
                                                                DESCRIPCION+ "')"  ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            stm.executeUpdate(instruccion);
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
    }
    
    public void ElimiarInstitucion (String RIF) throws ClassNotFoundException {

        try{
            
                    String instruccion = "delete from institucion where RIF = " + RIF + "" ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            stm.executeUpdate(instruccion);
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
    }
    
    public void registrarMedico (String MEDICO, String CEDULA, String GERENTE, String ATM, String NOMBRE_INSTITUCION, String DIRECCION, String ESPECIALIDAD, String FREC,  String CORREO, String TELEFONO, String DIAS_DE_VISITA, String HORARIO_DE_VISITA, String FORMATO_DE_VISITA, String DESCRIPCION ) throws ClassNotFoundException {

        try{
            
                    String instruccion = "insert into medico  values (' " + MEDICO + "', " +
                                                                CEDULA + ", '" +
                                                                GERENTE  + "', '" +
                                                                ATM  + "', '" +
                                                                NOMBRE_INSTITUCION  + "', '" +
                                                                DIRECCION  + "', '" +
                                                                ESPECIALIDAD  + "', " +
                                                                FREC  + ", '" +
                                                                CORREO + "', " +
                                                                TELEFONO + ", '" +
                                                                DIAS_DE_VISITA + "', '" +
                                                                HORARIO_DE_VISITA + "', '" +
                                                                FORMATO_DE_VISITA  + "'  , '" +
                                                                DESCRIPCION+ "')"  ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            stm.executeUpdate(instruccion);
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
    }
    
    public void ElimiarMEDICO (String CEDULA) throws ClassNotFoundException {

        try{
            
                    String instruccion = "delete from medico where CEDULA = " + CEDULA + "" ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            stm.executeUpdate(instruccion);
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
    }
    
    public void registrarEvento (String TITULO, String ATM, String CLIENTE, String FECHA, String SEMANA, String DESCRIPCION ) throws ClassNotFoundException {
 
        try{
            
                    String instruccion = "insert into eventos  values (' " + TITULO + "', '" +
                                                                ATM + "', '" +
                                                                CLIENTE  + "', '" +
                                                                FECHA  + "', " +
                                                                SEMANA  + ", '" +
                                                                DESCRIPCION+ "')"  ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            stm.executeUpdate(instruccion);
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
    }
    
    public void eliminarEvento(String Titulo) throws ClassNotFoundException {
 
        try{
            
                    String instruccion = "delete from eventos where TITULO = '" + Titulo + "'" ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            stm.executeUpdate(instruccion);
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
    }

    public ArrayList<Evento> DevolverEventos (String Cliente) throws ClassNotFoundException {
 
        ArrayList<Evento> retornosEventos = new ArrayList<Evento>();
        try{
            
                    String instruccion = "select * from eventos where CLIENTE = '" + Cliente + "'" ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            ResultSet eventos = stm.executeQuery(instruccion);

            while (eventos.next()){
                retornosEventos.add(new Evento(eventos.getString("TITULO"),eventos.getString("CLIENTE"),eventos.getString("FECHA"),Integer.parseInt(eventos.getString("SEMANA")),eventos.getString("DESCRIPCION")));

            }
            eventos.close();
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
      return retornosEventos;
    }
    
    public void agregarRequestFarmacia(String ADMINISTRADOR,
                                                String ATMS,
                                                String TITULO_PETICION,
                                                String DESCRIPCION_MOTIVO, 
                                                String TITULO_PREVIO,
                                                String FARMACIA,  
                                                String RIF, 
                                                String GERENTE, 
                                                String ATM,  
                                                String DIRECCION ,
                                                String CORREO, 
                                                String TELEFONO,
                                                String PERSONA_DE_CONTACTO, 
                                                String FREC,
                                                String CADENA,
                                                String DROGUERIA,
                                                String DESCRIPCION  ) throws ClassNotFoundException{
 
        try{
            
                    String instruccion = "insert into requestfarmacia values  ('" + ADMINISTRADOR + "', '" +
                                                                        ATM + "', '" +
                                                                        TITULO_PETICION  + "', '" +
                                                                        DESCRIPCION_MOTIVO  + "', '" +
                                                                        TITULO_PREVIO  + "', '" +
                                                                        FARMACIA  + "', " +
                                                                        RIF  + ", '" +
                                                                        GERENTE  + ", '" +
                                                                        ATM  + ", '" +
                                                                        DIRECCION  + ", '" +
                                                                        CORREO  + ", " +
                                                                        TELEFONO  + ", '" +
                                                                        PERSONA_DE_CONTACTO  + "', " +
                                                                        FREC  + ", '" +
                                                                        CADENA  + "', '" +
                                                                        DROGUERIA  + "', '" +
                                                                        DESCRIPCION+ "')"  ;
            System.out.println(instruccion);
            Statement stm = conexion.createStatement();
            stm.executeUpdate(instruccion);
            
            stm.close();
      } catch(SQLException ex){
          System.err.println(ex);
      }
    }
}
  
    
    

