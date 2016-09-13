/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Perfil_Deportivo;

import conexionBase.conexionBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static javax.print.attribute.Size2DSyntax.MM;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CarlosEfrain
 */
public class Conexion_PerfilDepor extends conexionBase {
SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy/MM/dd");
public static  String categoriaGene="";
    
public Conexion_PerfilDepor()
    { conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/force", "root","123456"); }

//*************************************************************************************************+
public void ingresarFuerza(int idfederado,double rm, String categoria, String fecha, String observacion)
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        try { sql2 ="insert into fuerza ( idfederado, rm, categoria, fecha, observacion)" + "values(?,?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setInt(1,idfederado);
          sentencia_sql2.setDouble(2, rm);
          sentencia_sql2.setString(3, categoria);
          sentencia_sql2.setString(4, fecha);
           sentencia_sql2.setString(5, observacion);
          n1=sentencia_sql2.executeUpdate();
if( n1>0) {JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error datos no ingresados" + e.getMessage());}}
  //******************************************************************************************************
public void consultarFuerzaTotal(JTable tabla, String idfederado)
    {
       DefaultTableModel encabezado=new DefaultTableModel ();
        encabezado=(DefaultTableModel) tabla.getModel();
        tabla.setModel(encabezado);
       int nuFlias=encabezado.getRowCount()-1;
       //////////////limpiar tabla
        for(int i=nuFlias;i>=0;i--){ 
            encabezado.removeRow(i);
        }
       ////////////////
        String[] entrenadorDtos2 = new String[4];
        String sql = "SELECT * FROM fuerza where idfederado='"+idfederado+"' and categoria='"+categoriaGene+"' order by fecha";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
//id, idfederado, rm, categoria, fecha, observacion
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 entrenadorDtos2[0]= rs.getString("id");
                 entrenadorDtos2[1]= rs.getString("fecha");
                 entrenadorDtos2[2]= rs.getString("rm");
                 entrenadorDtos2[3]= rs.getString("categoria");
                 encabezado.addRow(entrenadorDtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
  //******************************************************************************************************
public void consultarFuerzaFecha( String fecha, String iddeportista, String categ)
    { Calendar cal = null; 
      cal=Calendar.getInstance();
      String categoria;
   
  /*SELECT entrenamiento.dia, entrenamiento.semana, entrenamiento.identrenador, entrenador.nombres, entrenador.apellidos
 FROM entrenamiento, entrenador
where entrenamiento.idEntreno='15' and entrenador.identrenador=entrenamiento.identrenador*/
        String sql = "SELECT * FROM fuerza where fecha='"+fecha+"' and idfederado='"+iddeportista+"' and categoria='"+categ+"'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
//id, idfederado, rm, categoria, fecha, observacion
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 Progresiones_cargas.txtpeso.setText(rs.getString("rm"));
                 Progresiones_cargas.txtobservacion.setText(rs.getString("observacion"));
                 cal.setTime(formatoDelTexto.parse(fecha));
                 Progresiones_cargas.choserFecha.setCalendar(cal);
                 categoria=rs.getString("categoria");
                 categoriaGene=categoria;
                 if (categoria.equals("Press banca"))Progresiones_cargas.comboxCategoria.setSelectedIndex(0);
                 else if (categoria.equals("Peso muerto"))Progresiones_cargas.comboxCategoria.setSelectedIndex(1);
                 else if (categoria.equals("sentadilla"))Progresiones_cargas.comboxCategoria.setSelectedIndex(2);
                 
                 
                 }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}


//******************************************************************************
public void modificarFuerza(double  rm,String observacion, String  idfede, String categoria, String fecha)
    {int n=0;
     String sql;
     PreparedStatement sentencia_sql;
try { sql = "update fuerza set   rm ="+rm+",observacion='"+observacion+"' where idfederado='666' and categoria='"+categoria+"' and fecha='"+fecha+"'";
            sentencia_sql = (PreparedStatement) miConexion.prepareStatement(sql);
            n= sentencia_sql.executeUpdate();
if(n>0){JOptionPane.showMessageDialog(null, "modificacion exitosa");}}
        catch (SQLException e) {JOptionPane.showMessageDialog(null, "Error de modificacion de fuerza fallo " + e.getMessage());}} 
//******************************************************************************
  //******************************************************************************************************
public boolean consultarRegistro(String fecha, String idfederado, String categoria)
    {
       
        String sql = "SELECT * from fuerza where fecha='"+fecha+"' and idfederado='"+idfederado+"' and categoria='"+categoria+"'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
        String cat="";
//id, idfederado, rm, categoria, fecha, observacion
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 cat=rs.getString("categoria");
             }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}
    return !cat.equals("");
    }

  //******************************************************************************************************

}
