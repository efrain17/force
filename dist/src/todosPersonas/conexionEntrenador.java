/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package todosPersonas;


import conexionBase.conexionBase;
import CargarEntrenamiento.cargarEntreno;
import conexionBase.conexionBase;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.print.DocFlavor.STRING;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class conexionEntrenador extends conexionBase {
            DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
            String convertido ;
        FileInputStream archivoFoto;
        File nombreFoto;
    
    
    String nombrecliente;
    String apellido;
    String cedula;
    int numcomprs;
    int Stock; 
    String telefono;
    String direccion;
    String sexo;
    String[] facturar = new String[3];
    int id;
    String[] clienteDtos = new String[8];
    String facturarPrecio;
    String fe;
    int numfactura;
    float i;
    float subt;
    float to;
    String clientefactura;

public conexionEntrenador()
    { conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/force", "root","123456"); }

//*************************************************************************************************+
public conexionEntrenador(  String nombrecliente1,  String apellido1, String cedula1, String telefono1, String direccion1, String sex,int nc)
    {  nombrecliente  =nombrecliente1;
       apellido =apellido1;
       cedula=cedula1;
       telefono=telefono1;
       direccion=direccion1;
       sexo=sex;
       numcomprs=nc;
       conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/sss", "root","123456");}
//*************************************************************************************************
public void ingresarP(int semana,  int dia,  int peso, int codentreno)
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        try { sql2 ="insert into planentreno ( semana, dia, peso,idEntreno)" + "values(?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setInt(1,semana);
          sentencia_sql2.setInt(2, dia);
          sentencia_sql2.setInt(3, peso);
          sentencia_sql2.setInt(4, codentreno);
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error datos no ingresados" + e.getMessage());}}
  //******************************************************************************************************
public void ingresarFederado (int cedula, String nom,  String ape, String tel, String direc, String email,String sex, String fecI, String fecNac,String foto, String provincia) throws FileNotFoundException, IOException
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        try {
        nombreFoto = new File(foto);
        archivoFoto = new FileInputStream(nombreFoto); 
        } catch (Exception e) {
        }
        
        try { sql2 ="insert into entrenador ( identrenador, nombres, apellidos,telefono,email,direccion,sexo,fecInic,fecNac,foto,provincia)" + "values(?,?,?,?,?,?,?,?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setInt(1,cedula);
          sentencia_sql2.setString(2, nom);
          sentencia_sql2.setString(3, ape);
          sentencia_sql2.setString(4, tel);
          sentencia_sql2.setString(5, email);
          sentencia_sql2.setString(6, direc);
          sentencia_sql2.setString(7, sex);
          sentencia_sql2.setString(8, fecI);
          sentencia_sql2.setString(9, fecNac);
            try {
                 sentencia_sql2.setBinaryStream(10, archivoFoto, archivoFoto.available());
            } catch (Exception e) {
                sentencia_sql2.setBinaryStream(10, null);
            }
         
          sentencia_sql2.setString(11, provincia);
     
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error datos no ingresados" + e.getMessage());}}
  //******************************************************************************************************

public void modificarFederados(String ced, String nom,  String ape, String tel, String direc, String email,String sex, String fecI, String fecNac,String foto, String provincia) throws FileNotFoundException, IOException
    {int n=0;
     String sql;
     PreparedStatement sentencia_sql;
     nombreFoto = new File(foto);
        archivoFoto = new FileInputStream(nombreFoto);
     try { sql ="update entrenador set nombres=?, apellidos=?,telefono=?,email=?,direccion=?,sexo=?,fecInic=?,fecNac=?,foto=?,provincia=?   where identrenador like '%"+ced+"%'";
         
          sentencia_sql =   miConexion.prepareStatement(sql);
          sentencia_sql.setString(1, nom);
          sentencia_sql.setString(2, ape);
          sentencia_sql.setString(3, tel);
          sentencia_sql.setString(4, email);
          sentencia_sql.setString(5, direc);
          sentencia_sql.setString(6, sex);
          sentencia_sql.setString(7, fecI);
          sentencia_sql.setString(8, fecNac);
          sentencia_sql.setBinaryStream(9, archivoFoto, archivoFoto.available());
          sentencia_sql.setString(10, provincia);

            n= sentencia_sql.executeUpdate();
            if(n>0)
            {JOptionPane.showMessageDialog(null, "modificacion exitosa");} }
catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de modificacion " + e.getMessage());}}
//*******************************************************************************************************
public void modificarFederadosSinfoto(String ced, String nom,  String ape, String tel, String direc, String email,String sex, String fecI, String fecNac, String provincia) 
    {int n=0;
     String sql;
     PreparedStatement sentencia_sql;
     
     try { sql ="update entrenador set nombres=?, apellidos=?,telefono=?,email=?,direccion=?,sexo=?,fecInic=?,fecNac=?,provincia=?   where identrenador like '%"+ced+"%'";
         
          sentencia_sql =   miConexion.prepareStatement(sql);
          sentencia_sql.setString(1, nom);
          sentencia_sql.setString(2, ape);
          sentencia_sql.setString(3, tel);
          sentencia_sql.setString(4, email);
          sentencia_sql.setString(5, direc);
          sentencia_sql.setString(6, sex);
          sentencia_sql.setString(7, fecI);
          sentencia_sql.setString(8, fecNac);
          sentencia_sql.setString(9, provincia);
          n= sentencia_sql.executeUpdate();
            if(n>0)
            {JOptionPane.showMessageDialog(null, "modificacion exitosa");} }
catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de modificacion " + e.getMessage());}}
//*******************************************************************************************************
public void eliminarCliente(String cedula)
    {int n=0;
        String sql;
        PreparedStatement sentencia_sql;
        try {
            sql ="delete from cliente " + "where cedula = ?";

            sentencia_sql = miConexion.prepareStatement(sql);
            sentencia_sql.setString(1, cedula);
            n= sentencia_sql.executeUpdate();
            if(n>0)
            {JOptionPane.showMessageDialog(null, "Cliente eliminado Correctamente");
            }}
   catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage());}}
//******************************************************************************
public void consulPlan(  String  identreno,JTable tabla)
    {String sql = "select * from planentreno where idEntreno= "+identreno+" order by idPlan ";
    String [] columna ={"Fecha","Peso"};
    DefaultTableModel encabezado=new DefaultTableModel (null,columna);
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 clienteDtos[0]= rs.getString("nombrescliente");
                 clienteDtos[1]= rs.getString("apellido");
                 clienteDtos[2]= rs.getString("cedula");
                 clienteDtos[3]= rs.getString("telefono");
                 clienteDtos[4]= rs.getString("direccion");
                 clienteDtos[5]= rs.getString("sexo");
                 clienteDtos[6]= rs.getString("ncompras");}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
//*****************************************************************************
public void consulIdEntreno(  String  nombre)
    {String sql = "select idEntreno from entrenamiento where nombre='"+nombre+"'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 id= rs.getInt("idEntreno");}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos loco " + e.getMessage());}}
//*****************************************************************************
public void consultarFederadoTotal(JTable tabla)
    {String [] columna ={"cedula","nombres ","Apellidos"};
       DefaultTableModel encabezado=new DefaultTableModel (null,columna);
      
       tabla.setModel(encabezado);
        String[] entrenadorDtos2 = new String[3];
        String sql = "select * from entrenador ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 entrenadorDtos2[0]= rs.getString("idfederado");
                 entrenadorDtos2[1]= rs.getString("nombres");
                 entrenadorDtos2[2]= rs.getString("apellidos");
                 encabezado.addRow(entrenadorDtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
//*****************************************************************************

public void consultarFederado(String codigo)
    { InputStream datos = null;
        String sql = "select * from entrenador where identrenador="+codigo+" ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
               //  cargarEntreno.imagen= rs.getString("");
                 
                datos = rs.getBinaryStream("foto");
                 } ByteArrayOutputStream ouput = new ByteArrayOutputStream();
                int tem = datos.read();
                while (tem >= 0) {

                    ouput.write((char) tem);
                    tem = datos.read();
                }
                Image image = Toolkit.getDefaultToolkit().createImage(ouput.toByteArray());
                image = image.getScaledInstance(140, 160, 1);
               cargarEntreno.imagen.setIcon(new ImageIcon(image));
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
/*  ByteArrayOutputStream ouput = new ByteArrayOutputStream();
                int tem = datos.read();
                while (tem >= 0) {

                    ouput.write((char) tem);
                    tem = datos.read();
                }
                Image image = Toolkit.getDefaultToolkit().createImage(ouput.toByteArray());
                image = image.getScaledInstance(140, 160, 1);
                MenuCOUNT.labelfoto.setIcon(new ImageIcon(image));*/

public int getId(){
return id;}


public String getNombrecl(){
return clienteDtos[0];}
public String getapellido(){
return clienteDtos[1];}
public String getCedula(){
return clienteDtos[2];}
public String getTelefono(){
return clienteDtos[3];}
public String getDireccion(){
return clienteDtos[4];}
public String getsexo(){
return clienteDtos[5];}
public String getncompras(){
return clienteDtos[6];}

public void consultarEntrenadorCargar(String codigo)
    { InputStream datos = null;
        String sql = "select * from entrenador where identrenador="+codigo+" ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
               //  cargarEntreno.imagen= rs.getString("");
                Entrenador.TXTnombre.setText(rs.getString("nombres"));
                Entrenador.TXTapellido.setText(rs.getString("apellidos"));
                Entrenador.TXTdireccion.setText(rs.getString("direccion"));
                Entrenador.TXTtelefono.setText(rs.getString("telefono"));
                Entrenador.TXTemail.setText(rs.getString("email"));
                Entrenador.fechaInic.setDate(fecha.parse(rs.getString("fecInic")));
                Entrenador.fecnacimiento.setDate(fecha.parse(rs.getString("fecNac")));
                Entrenador.comboProvincia.setSelectedItem(rs.getString("provincia"));
                Entrenador.LSTsexo.setSelectedItem(rs.getString("sexo"));
                datos = rs.getBinaryStream("foto");
                ////////////////////////////activar 
                Entrenador.TXTnombre.setEnabled(true);
                Entrenador.TXTapellido.setEnabled(true);
                Entrenador.TXTdireccion.setEnabled(true);
                Entrenador.TXTtelefono.setEnabled(true);
                Entrenador.TXTemail.setEnabled(true);
                Entrenador.fechaInic.setEnabled(true);
                Entrenador.fecnacimiento.setEnabled(true);
                Entrenador.comboProvincia.setEnabled(true);
                Entrenador.LSTsexo.setEnabled(true);
                Entrenador.foto_label2.setEnabled(true);
                /////////////////
                 } 
               if (datos!=null){
                ByteArrayOutputStream ouput = new ByteArrayOutputStream();
                int tem = datos.read();
                while (tem >= 0) {

                    ouput.write((char) tem);
                    tem = datos.read();
                }
                Image image = Toolkit.getDefaultToolkit().createImage(ouput.toByteArray());
                image = image.getScaledInstance(140, 160, 1);
                 Entrenador.foto_label2.setIcon(new ImageIcon(image));
                 Entrenador.foto_label2.setEnabled(true);
               }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
//////////////////////////////////////
//*****************************************************************************
public void consultarEntrenadorTotal(JTable tabla)
    {String [] columna ={"cedula","nombres ","Apellidos"};
       DefaultTableModel encabezado=new DefaultTableModel (null,columna);
      
       tabla.setModel(encabezado);
        String[] entrenadorDtos2 = new String[3];
        String sql = "select * from entrenador ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 entrenadorDtos2[0]= rs.getString("identrenador");
                 entrenadorDtos2[1]= rs.getString("nombres");
                 entrenadorDtos2[2]= rs.getString("apellidos");
                 encabezado.addRow(entrenadorDtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
//*****************************************************************************
}