/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package todosPersonas;


import conexionBase.conexionBase;
import CargarEntrenamiento.cargarEntreno;
import conexionBase.conexionBase;
import force.miprincipal;
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
import javax.print.DocFlavor.STRING;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class conexionUsuarios extends conexionBase {
            DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
            String convertido ;
        FileInputStream archivoFoto=null;
        File nombreFoto=null;
    
    
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

public conexionUsuarios()
    { conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/force", "root","123456"); }

//*************************************************************************************************+
public conexionUsuarios(  String nombrecliente1,  String apellido1, String cedula1, String telefono1, String direccion1, String sex,int nc)
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
public void ingresarUsuario (int cedula, String nom,  String ape, String tel, String direc, String email,String sex, String fecI, String fecNac,String foto, String provincia,String tipo,String contrasenha) throws FileNotFoundException, IOException
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        try {
             nombreFoto = new File(foto);
        archivoFoto = new FileInputStream(nombreFoto);
        } catch (Exception e) {
        }
       
        try { sql2 ="insert into usuario ( idusuario, nombres, apellidos,telefono,email,direccion,sexo,fecInic,fecNac,foto,provincia,tipo,contrasenha)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
          sentencia_sql2.setString(12, tipo);
          sentencia_sql2.setString(13, contrasenha);
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error datos no ingresados" + e.getMessage());}}
  //******************************************************************************************************

public void modificarCliente(String ced)
    {int n=0;
     String sql;
     PreparedStatement sentencia_sql;
     try { sql ="update cliente set apellido=?, cedula=?, direccion=?, nombrescliente=?, sexo=?, telefono=?, ncompras=?  where cedula like '%"+ced+"%'";
            sentencia_sql =   miConexion.prepareStatement(sql);
            sentencia_sql.setString(1,apellido );
            sentencia_sql.setString(2,cedula);
            sentencia_sql.setString(3,direccion);
            sentencia_sql.setString(4, nombrecliente);
            sentencia_sql.setString(5, sexo);
            sentencia_sql.setString(6,telefono);
            sentencia_sql.setInt(7,numcomprs);

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
public void consultarUsuarioTotal(JTable tabla)
    {String [] columna ={"cedula","nombres ","Apellidos"};
       DefaultTableModel encabezado=new DefaultTableModel (null,columna);
      
       tabla.setModel(encabezado);
        String[] entrenadorDtos2 = new String[3];
        String sql = "select * from usuario ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 entrenadorDtos2[0]= rs.getString("idusuario");
                 entrenadorDtos2[1]= rs.getString("nombres");
                 entrenadorDtos2[2]= rs.getString("apellidos");
                 encabezado.addRow(entrenadorDtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
//*****************************************************************************

public void consultarUsuarioFoto(String codigo)
    { InputStream datos = null;
        String sql = "select * from usuario where idusuario="+codigo+" ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
               //  cargarEntreno.imagen= rs.getString("");
                miprincipal.nombrePrincipal.setText(rs.getString("nombres"));
                datos = rs.getBinaryStream("foto");
                miprincipal.tipousuario=rs.getString("tipo");
                 } ByteArrayOutputStream ouput = new ByteArrayOutputStream();
                int tem = datos.read();
                while (tem >= 0) {

                    ouput.write((char) tem);
                    tem = datos.read();
                }
                Image image = Toolkit.getDefaultToolkit().createImage(ouput.toByteArray());
                image = image.getScaledInstance(140, 160, 1);
              miprincipal.foto_label2.setIcon(new ImageIcon(image));
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}


public boolean consultarUsuariologin(String codigo,String contra)
    { String datos = null;
        String sql = "select * from usuario where idusuario='"+codigo+"' and contrasenha='"+contra+"'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
        
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
               //  cargarEntreno.imagen= rs.getString("");
                
                datos = rs.getString("nombres");
                 } 
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Datos ingresados incorrectos.");}
    if (datos == null)return false;
    else return true;
    
    }
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

//************************************************************************
public void consultarUsuarioCargar(String codigo)
    { InputStream datos = null;
        String sql = "select * from usuario where idusuario="+codigo+" ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
               //  cargarEntreno.imagen= rs.getString("");
                Usuarios.TXTnombre.setText(rs.getString("nombres"));
                Usuarios.TXTapellido.setText(rs.getString("apellidos"));
                Usuarios.TXTdireccion.setText(rs.getString("direccion"));
                Usuarios.TXTtelefono.setText(rs.getString("telefono"));
                Usuarios.TXTemail.setText(rs.getString("email"));
                Usuarios.fechaInic.setDate(fecha.parse(rs.getString("fecInic")));
                Usuarios.fecnacimiento.setDate(fecha.parse(rs.getString("fecNac")));
                Usuarios.comboProvincia.setSelectedItem(rs.getString("provincia"));
                Usuarios.combotipousuario.setSelectedItem(rs.getString("tipo"));
                Usuarios.LSTsexo.setSelectedItem(rs.getString("sexo"));
                datos = rs.getBinaryStream("foto");
                ////////////////////////////activar 
                Usuarios.TXTnombre.setEnabled(true);
                Usuarios.TXTapellido.setEnabled(true);
                Usuarios.TXTdireccion.setEnabled(true);
                Usuarios.TXTtelefono.setEnabled(true);
                Usuarios.TXTemail.setEnabled(true);
                Usuarios.fechaInic.setEnabled(true);
                Usuarios.fecnacimiento.setEnabled(true);
                Usuarios.comboProvincia.setEnabled(true);
                Usuarios.LSTsexo.setEnabled(true);
                Usuarios.combotipousuario.setEnabled(true);
                Usuarios.txtcontraseña1.setEnabled(true);
                Usuarios.txtcontraseña2.setEnabled(true);
                Usuarios.foto_label2.setEnabled(true);
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
                 Usuarios.foto_label2.setIcon(new ImageIcon(image));
                 Usuarios.foto_label2.setEnabled(true);
               }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
////////////////////////////////////////////////////////////////////////
public void modificarUsuarioSinfoto(String ced, String nom,  String ape, String tel, String direc, String email,String sex, String fecI, String fecNac, String provincia,String contrasenha, String tipo) 
    {int n=0;
     String sql;
     PreparedStatement sentencia_sql;
     
     try { sql ="update usuario set nombres=?, apellidos=?,telefono=?,email=?,direccion=?,sexo=?,fecInic=?,fecNac=?,provincia=?,contrasenha=?, tipo=?  where idusuario like '%"+ced+"%'";
         
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
          sentencia_sql.setString(10, contrasenha);
          sentencia_sql.setString(11, tipo);
          n= sentencia_sql.executeUpdate();
            if(n>0)
            {JOptionPane.showMessageDialog(null, "modificacion exitosa");} }
catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de modificacion " + e.getMessage());}}
//*******************************************************************************************************
public void modificarUsuario(String ced, String nom,  String ape, String tel, String direc, String email,String sex, String fecI, String fecNac,String foto, String provincia,String contrasenha,String tipo) throws FileNotFoundException, IOException
    {int n=0;
     String sql;
     PreparedStatement sentencia_sql;
     nombreFoto = new File(foto);
        archivoFoto = new FileInputStream(nombreFoto);
     try { sql ="update usuario set nombres=?, apellidos=?,telefono=?,email=?,direccion=?,sexo=?,fecInic=?,fecNac=?,foto=?,provincia=?,contrasenha=?, tipo=?    where idusuario like '%"+ced+"%'";
         
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
          sentencia_sql.setString(11, contrasenha);
          sentencia_sql.setString(12, tipo);

            n= sentencia_sql.executeUpdate();
            if(n>0)
            {JOptionPane.showMessageDialog(null, "modificacion exitosa");} }
catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de modificacion " + e.getMessage());}}
//*******************************************************************************************************


}