/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arqueoCAJA;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import conexionBase.conexionBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CarlosEfrain
 */
public class conexionCAja extends conexionBase{
    public  String idcaja;
    public conexionCAja(){
     conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/contable", "root","123456"); }
    

//*************************************************************************************************
//idcaja, idvendedor, fechaInicio, fechaCierre, horaInicio, horaCierre, efectivoInicio, totalRegistro, conteoTurno, estado
public void ingresarCaja(String idvendedor,String fechaInicio,String horaInicio, double efectivoInicio)
    {   int n1=0;
        String sql2;
        Date j;
        PreparedStatement sentencia_sql2;
        try { sql2 ="insert into caja ( idvendedor, fechaInicio, horaInicio, efectivoInicio, totalRegistro)" + "values(?,?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setString(1,idvendedor);
          sentencia_sql2.setString(2,  fechaInicio);
          sentencia_sql2.setString(3, horaInicio);
          sentencia_sql2.setDouble(4, efectivoInicio);
          sentencia_sql2.setDouble(5, efectivoInicio);
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {JOptionPane.showMessageDialog(null, "Caja abierta correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error datos no ingresados" + e.getMessage());}}
///////////////////////////////////////////////////////////////////////////////////////////////////
///idcaja, idoperacion, fecha, descripicon, monto
public void ingresarCajaOperacion(String idcaja, String fecha, String hora, String descripicon, double monto)
    {   int n1=0;
        String sql2;
        Date j;
        PreparedStatement sentencia_sql2;
        try { sql2 ="insert into operaciones_caja ( idcaja, fecha,hora , descripicon, monto)" + "values(?,?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setString(1,idcaja);
          sentencia_sql2.setString(2,  fecha);
           sentencia_sql2.setString(3,  hora);
          sentencia_sql2.setString(4, descripicon);
          sentencia_sql2.setDouble(5, monto);
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {//JOptionPane.showMessageDialog(null, "Caja abierta correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error datos no ingresados en cajaOperacion" + e.getMessage());}}
/////////////////////////////////////////////////////////////////////////////////////////////////
public void modificarCajaTotal(String idvendedor,double pago)
    {int n=0;
     String sql;
     PreparedStatement sentencia_sql;
     try { sql ="update caja\n" +
"set totalRegistro=totalRegistro+"+pago+"\n" +
"where idvendedor='"+idvendedor+"' and estado='abierta'";
            sentencia_sql =   miConexion.prepareStatement(sql);
            n= sentencia_sql.executeUpdate();
            if(n>0)
            {
               // JOptionPane.showMessageDialog(null, "modificacion exitosa");
            } }
catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de modificacion " + e.getMessage());}}
///////////////////////////////////////////
public void modificarCajaTotalCompra(String idvendedor,double pago)
    {int n=0;
     String sql;
     PreparedStatement sentencia_sql;
     try { sql ="update caja\n" +
"set totalRegistro=totalRegistro-"+pago+"\n" +
"where idvendedor='"+idvendedor+"' and estado='abierta'";
            sentencia_sql =   miConexion.prepareStatement(sql);
            n= sentencia_sql.executeUpdate();
            if(n>0)
            {
               // JOptionPane.showMessageDialog(null, "modificacion exitosa");
            } }
catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de modificacion " + e.getMessage());}}
///////////////////////////////////////////
public void consultarCajaID(String codigo)
    {  
        String sql = "SELECT idcaja FROM caja where idvendedor='"+codigo+"' and estado='abierta'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 idcaja= rs.getString("idcaja");}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos del idcaja   " + e.getMessage());}}
//////////////////////////////////////
//idcaja, idvendedor, fechaInicio, fechaCierre, horaInicio, horaCierre, efectivoInicio, totalRegistro, conteoTurno, estado
public void cerrarCaja(String fechaCierre, String horaCierre,double conteoTurno, String idvendedor)
    {int n=0;
     String sql;
     PreparedStatement sentencia_sql;
     try { sql ="update caja\n" +
"set fechaCierre='"+fechaCierre+"', horaCierre='"+horaCierre+"',conteoTurno="+conteoTurno+",estado='cerrada' where idvendedor='"+idvendedor+"' and estado='abierta'";
            sentencia_sql =   miConexion.prepareStatement(sql);
            n= sentencia_sql.executeUpdate();
            if(n>0)
            {
               JOptionPane.showMessageDialog(null, "caj cerrada correctamente");
            } }
catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de modificacion " + e.getMessage());}}
//////////////////////////la caja esta abierta 
public boolean consultarCaja_esta_Abierta(String codigo)
    {  String idc="";
        String sql = "SELECT idcaja FROM caja where idvendedor='"+codigo+"' and estado='abierta'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 idc= rs.getString("idcaja");}
             rs.close();
 }
 catch (Exception e) {
             //  return true;
             JOptionPane.showMessageDialog(null, "Error en obtener los datos del idcaja   " + e.getMessage());
 }
        return idc.equals("");
}
///////////////////////////////////////////////////////////////////////////////
//idcaja, idvendedor, fechaInicio, fechaCierre, horaInicio, horaCierre, efectivoInicio, totalRegistro, conteoTurno, estado
public void consultarCierreCaja(String codigo)
    {  
        String sql = "SELECT caja.*,usuarios.nombres\n" +
"FROM caja,usuarios\n" +
"where caja.idvendedor=usuarios.cedula and caja.idcaja='"+codigo+"'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 operaciones_caja.txtdesde.setText(rs.getString("fechaInicio"));
                 operaciones_caja.txthasta.setText(rs.getString("fechaCierre"));
                 operaciones_caja.txtvendedor.setText(rs.getString("usuarios.nombres"));
                 operaciones_caja.txtidcaja.setText(rs.getString("idcaja"));
                 operaciones_caja.txthoraInicio.setText(rs.getString("horaInicio"));
                 operaciones_caja.txthoracierre.setText(rs.getString("horaCierre"));
                 operaciones_caja.txtvalorinicio.setText(rs.getString("efectivoInicio"));
                 operaciones_caja.txttotalregistro.setText(rs.getString("totalRegistro"));
                 operaciones_caja.txtconteo.setText(rs.getString("conteoTurno")); 
                 operaciones_caja.txtdiferencia.setText(String.valueOf(Double.parseDouble(rs.getString("totalRegistro"))-Double.parseDouble(rs.getString("conteoTurno"))));
                 
             }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos del idcaja   " + e.getMessage());}}
//////////////////////////////////////
public void consultarOperacionesCaja(JTable tabla, String idcuenta)
    {String [] columna ={"Fecha","Descripción","Monto"};
       DefaultTableModel encabezado=new DefaultTableModel (null,columna);
      
       tabla.setModel(encabezado);
        String[] entrenadorDtos2 = new String[4];
        String sql = "SELECT * FROM operaciones_caja where idcaja='"+idcuenta+"' ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
     //   idcaja, idoperacion, fecha, descripicon, monto, hora
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 entrenadorDtos2[0]= rs.getString("fecha");
                 entrenadorDtos2[1]= rs.getString("descripicon");
                 entrenadorDtos2[2]= rs.getString("monto");
                 encabezado.addRow(entrenadorDtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
///////////////////////////////////////////////////////////
//idcaja, idvendedor, fechaInicio, fechaCierre, horaInicio, horaCierre, efectivoInicio, totalRegistro, conteoTurno, estado
public void consultarCierresTotal(JTable tabla, String fecha)
    {//String [] columna ={"# caja", "id Vendedor", "fecha Inicio", "Fecha Cierre", "Total Registro", "Conteo Turno", "Estado"};
       DefaultTableModel encabezado=(DefaultTableModel)tabla.getModel();
      
       tabla.setModel(encabezado);
       int filas=tabla.getRowCount();
            for (int i = 0;filas>i; i++) {
                encabezado.removeRow(0);
            }
            
        String[] entrenadorDtos2 = new String[10];
        String sql = "SELECT * FROM caja where fechaInicio='"+fecha+"' ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
     //   idcaja, idoperacion, fecha, descripicon, monto, hora
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
//idcaja, idvendedor, fechaInicio, fechaCierre, horaInicio, horaCierre, efectivoInicio, totalRegistro, conteoTurno, estado
                 entrenadorDtos2[0]= rs.getString("idcaja");
                 entrenadorDtos2[1]= rs.getString("idvendedor");
                 entrenadorDtos2[2]= rs.getString("fechaInicio");
                 entrenadorDtos2[3]= rs.getString("fechaCierre");
                 entrenadorDtos2[4]= rs.getString("totalRegistro");
                 entrenadorDtos2[5]= rs.getString("conteoTurno");
                 entrenadorDtos2[6]= rs.getString("estado");
                 encabezado.addRow(entrenadorDtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos de caja  " + e.getMessage());}}
public String getidcaja(){
return idcaja;}
}
