/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package compra;

import factura.*;
import com.mxrck.autocompleter.TextAutoCompleter;
import conexionBase.conexionBase;
import static factura.Factura.ivaboolean;
import static factura.Factura.tblProductosfac;
import static factura.Factura.textAutoCompleter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CarlosEfrain
 */
public class conexionCompra extends conexionBase{
    public int stock,stock_max,idventa, idcuentacobrar, Nperiodo,Nmese;
    public double interes, adelanto;
//     TextAutoCompleter textAutoAcompleter = new TextAutoCompleter( Factura.txtcodigocliente );
    
 DecimalFormat fd = new DecimalFormat("0.00");
  public String[] ProductoDtos2 = new String[9];
   public String[] Productovacio = {"","","","","","","","",""};
    
public conexionCompra(){
    conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/contable", "root","123456"); }

//*****************************************************************************
public void consultarProveedoresTotal(JTable tabla)
    {String [] columna ={"cedula-ruc","Nombres ","Apellidos","Dirección"};
       DefaultTableModel encabezado=new DefaultTableModel (null,columna);
      
       tabla.setModel(encabezado);
        String[] entrenadorDtos2 = new String[4];
        String sql = "select * from proveedor ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 entrenadorDtos2[0]= rs.getString("ruc");
                 entrenadorDtos2[1]= rs.getString("nombres");
                 entrenadorDtos2[2]= rs.getString("apellidos");
                 entrenadorDtos2[3]= rs.getString("direccion");
                 
                 encabezado.addRow(entrenadorDtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
//*****************************************************************************
public void consultarProductoTotal(JTable tabla)
    {String [] columna ={"Codigo","Descripcion ","Costo","cantidad"};
       DefaultTableModel encabezado=new DefaultTableModel (null,columna);
      //poner la validacion 
       tabla.setModel(encabezado);
        String[] Dtos2 = new String[4];
        String sql =  " SELECT * FROM producto where  '"+Compra.listaPorductosc+"' not LIKE CONCAT('%', codigo_producto, '%')";
      
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 Dtos2[0]= String.valueOf(rs.getInt("codigo_producto"));
                 Dtos2[1]= rs.getString("descripcion");
                 Dtos2[2]= String.valueOf(rs.getDouble("costo"));
                 Dtos2[3]="1";
                 encabezado.addRow(Dtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}


//**********************************************************************************************

public void consultarProductoTabla(JTable tabla, String codigo,double cantidad)
    {DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
      tabla.setModel(modelo);
      double subt=0;
      String valor="";
      boolean seguir=true,lleno;
      int contF=0, nuFlias=modelo.getRowCount();
      //0        1      2         3    4          5      6     7    8     9
      //linea cdgio descripcion bodega cantidad unidad precio unit descto subtotal 
        String sql = "select * from  producto where codigo_producto="+codigo+"";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                  ProductoDtos2[0]= "";
                  ProductoDtos2[1]= String.valueOf(rs.getInt("codigo_producto"));
                  ProductoDtos2[2]= rs.getString("descripcion");
                  ProductoDtos2[3]= rs.getString("bodega");
                  ProductoDtos2[4]= String.valueOf(cantidad);
                  ProductoDtos2[5]= rs.getString("unidad");
                  ProductoDtos2[6]= String.valueOf(rs.getDouble("precio"));
                  ProductoDtos2[7]= "";
                  subt=Double.parseDouble(ProductoDtos2[6])*cantidad;
                  ProductoDtos2[8]= String.valueOf(subt);
                  while (seguir){
                      
                      valor=String.valueOf(tabla.getValueAt(contF, 3));
                      // JOptionPane.showMessageDialog(null, "valor: "+valor);
                      if (tabla.getValueAt(contF, 3)==null){
                        //  JOptionPane.showMessageDialog(null, "entro"+valor);
                             //   modelo.setValueAt(ProductoDtos2[0], contF, 0);
                                modelo.setValueAt(ProductoDtos2[1], contF, 1);
                                modelo.setValueAt(ProductoDtos2[2], contF, 2);
                                modelo.setValueAt(ProductoDtos2[3], contF, 3);
                                modelo.setValueAt(ProductoDtos2[4], contF, 4);
                                modelo.setValueAt(ProductoDtos2[5], contF, 5);
                                modelo.setValueAt(ProductoDtos2[6], contF, 6);
                                modelo.setValueAt(ProductoDtos2[7], contF, 7);
                                modelo.setValueAt(ProductoDtos2[8], contF, 8);
                                Compra.tablaInstanc=false;
                                seguir=false;
                      }
                      if (contF==nuFlias)modelo.addRow(Productovacio);
                      contF++;
                  }
            // modelo.addRow(ProductoDtos2);
              int  Nfilas=modelo.getRowCount();
              double subtotal=0,descuento=0,iva=0,total=0;
             Compra.listaPorductosc="";
   //  JOptionPane.showMessageDialog(null, "hay  :  ;) "+Nfilas);
    for (int i = 0; i < Nfilas; i++) {
       // JOptionPane.showMessageDialog(null, "hay vamos :  ;) ");
        if (modelo.getValueAt(i, 3)!=null){
     subtotal=subtotal+Double.parseDouble(modelo.getValueAt(i, 8).toString());
     Compra.listaPorductosc= Compra.listaPorductosc+","+modelo.getValueAt(i, 1).toString();
     //JOptionPane.showMessageDialog(null, "hay vamos sub sub  :  ;) "+subtotal);
     if(!modelo.getValueAt(i, 7).toString().equals("")){
     descuento=((Double.parseDouble(modelo.getValueAt(i, 7).toString())/100)  *(Double.parseDouble(modelo.getValueAt(i, 8).toString())))+descuento;  } 
   
        }
       else descuento=descuento+0.0;
        if (ivaboolean) iva=(subtotal-descuento)*0.12;
        else iva=0;
    total=(subtotal-descuento)+iva;
    }
    Compra.totalc=total;
    Compra.ivac=iva;
    Compra.descuentoc=descuento;
    Compra.subtotalc=subtotal; 
    Compra.txtsubtotal.setText(String.valueOf(fd.format(subtotal)));
    Compra.txtdescuento.setText(String.valueOf(fd.format(descuento)));
    Compra.txtiva.setText(String.valueOf(fd.format(iva)));
    Compra.txtotal.setText(String.valueOf(fd.format(total)));
             }
             
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());} }
//*****************************************************************************
public void consultarProductoStock(String codigo)
    {  
        String sql = "select * from  producto where codigo_producto="+codigo+"";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 stock= rs.getInt("stock");
                 stock_max= rs.getInt("stock_max");}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
////////////////////////////////////////////////////////////////////////////////////////
//fechaemision fechavence subtotal descuento iva totalcobrado totalcobrar idcliente
//*************************************************************************************************
public void ingresarCompra(String fechae,  String fechav,  double sub, double des,double iva,double total, String idcliente,String observacion )
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        try { sql2 ="insert into compra ( fechaemision, fechavence, subtotal, descuento, iva, totalpagar, idproveedor, observacion)" + "values(?,?,?,?,?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setString(1,fechae);
          sentencia_sql2.setString(2, fechav);
          sentencia_sql2.setDouble(3, sub);
          sentencia_sql2.setDouble(4, des);
          sentencia_sql2.setDouble(5, iva);
          sentencia_sql2.setDouble(6, total);
          sentencia_sql2.setString(7, idcliente);
           sentencia_sql2.setString(8, observacion);
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {JOptionPane.showMessageDialog(null, "Compra ingresada correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error datos no ingresados" + e.getMessage());}}
  //******************************************************************************************************
//******************************************************************************
public void modificarStock(String  idp, double cant)
    {int n=0;
     String sql;
     PreparedStatement sentencia_sql;
try { sql = "update producto set   stock =stock+"+cant+", where codigo_producto="+idp+"";
            sentencia_sql = (PreparedStatement) miConexion.prepareStatement(sql);
            n= sentencia_sql.executeUpdate();
if(n>0){JOptionPane.showMessageDialog(null, "modificacion de stock exitosa");}}
        catch (SQLException e) {JOptionPane.showMessageDialog(null, "Error de modificacion de sctok fallo " + e.getMessage());}} 
//******************************************************************************
  // ideventa idproducto cantidad precio totalProducto descuento 
//*************************************************************************************************
public void ingresardetalleCompra(int idv,  String idpro,  double cant,double pre, double tot,double des)
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        try { sql2 ="insert into detallecompra ( idcompra, idproducto, cantidad, precio, totalProducto, descuento)" + "values(?,?,?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setInt(1,idv);
          sentencia_sql2.setString(2, idpro);
          sentencia_sql2.setDouble(3, cant);
          sentencia_sql2.setDouble(4, pre);
          sentencia_sql2.setDouble(5, tot);
          sentencia_sql2.setDouble(6, des);
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {//JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error detalles de venta ingresados" + e.getMessage());}}
  //******************************************************************************************************
//SELECT MAX(IdPersona) FROM Personas
//*****************************************************************************
public void consultarNumeroCompra()
    {  
        String sql = "SELECT MAX(idcompra) FROM compra";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 idventa= rs.getInt("MAX(idcompra)");}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos de compra  " + e.getMessage());}}
////////////////////////////////////////////////////////////////////////////////////////
//SELECT * FROM clientes where  '2333,3233,22,12' not LIKE CONCAT('%', ruc, '%');
public void autcompleteProveedor(String codigo){
    // Factura.textAutoCompleter = new TextAutoCompleter( Factura.txtcodigocliente);
    Compra.textAutoCompleterc.removeAllItems();
        String sql = "SELECT * FROM proveedor where ruc like '"+codigo+"%'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
        String[] entrenadorDtos2 = new String[4];
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) { /*
                 entrenadorDtos2[0]= rs.getString("ruc");
                 entrenadorDtos2[1]= rs.getString("nombres");
                 entrenadorDtos2[2]= rs.getString("apellidos");
                 entrenadorDtos2[3]= rs.getString("direccion");*/
        Compra.textAutoCompleterc.addItem(rs.getString("ruc")+"---"+rs.getString("nombres")+""+rs.getString("apellidos"));
       //textAutoAcompleter.addItem(rs.getString("ruc")+"-"+rs.getString("nombres"));
             }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos de venta  " + e.getMessage());}
    
}
//////////////
public void autllenarProveedor(String codigo){
      
                
                 Compra.txtNombreCliente.setText("");
                 Compra.txtDireccionCliente.setText("");
     //textAutoAcompleter.removeAllItems();
        String sql = "SELECT * FROM proveedor where ruc ='"+codigo+"'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
        String[] entrenadorDtos2 = new String[4];
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) { 
                 
                 Compra.txtcodigocliente.setText(rs.getString("ruc"));
                 Compra.txtNombreCliente.setText(rs.getString("nombres")+" "+rs.getString("apellidos"));
                 Compra.txtDireccionCliente.setText(rs.getString("direccion"));

            }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos de venta  " + e.getMessage());}
    
}



/////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
public void autcompleteProducto(String codigo){
    // Factura.textAutoCompleter = new TextAutoCompleter( Factura.txtcodigocliente);
     Compra.textAutoCompleterProductoc.removeAllItems();
     
           
        String sql = "SELECT * FROM producto where codigo_producto like '"+codigo+"%'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
        String[] entrenadorDtos2 = new String[4];
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 // JOptionPane.showMessageDialog(null, "weonnnnn :D ");
       Compra.textAutoCompleterProductoc.addItem(rs.getString("codigo_producto")+"---"+rs.getString("descripcion"));
       //textAutoAcompleter.addItem(rs.getString("ruc")+"-"+rs.getString("nombres"));
             }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos de venta  " + e.getMessage());}
    
}

public void autllenarProducto(String codigo){    
}

/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////CUENTAS POR COBRAR   //////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
public void ingresarCuentaPorPagar(int ID_VENTA, String FECHA_REGISTRO, double INTERES,double VALOR_INTERES,double TOTAL_CUENTA,int CANT_CUOTAS,double VALOR_CUOTAS,String FECHA_COBRO,int  NUM_CUOTA_COBRADA, double VALOR_COBRADO,double  VALOR_POR_COBRAR, double ADELANTO)
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        try { sql2 ="insert into cuentas_por_pagar (ID_COMPRA, FECHA_REGISTRO, INTERES, VALOR_INTERES, TOTAL_CUENTA, CANT_CUOTAS, VALOR_CUOTAS, FECHA_COBRO, NUM_CUOTA_PAGADA, VALOR_PAGADO, VALOR_POR_PAGAR, ADELANTO)" + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setInt(1,ID_VENTA);
          sentencia_sql2.setString(2, FECHA_REGISTRO);
          sentencia_sql2.setDouble(3, INTERES);
          sentencia_sql2.setDouble(4, VALOR_INTERES);
          sentencia_sql2.setDouble(5, TOTAL_CUENTA);
          sentencia_sql2.setDouble(6, CANT_CUOTAS);
          sentencia_sql2.setDouble(7, VALOR_CUOTAS);          
          sentencia_sql2.setString(8, FECHA_COBRO);
          sentencia_sql2.setInt(9,NUM_CUOTA_COBRADA);
          sentencia_sql2.setDouble(10, VALOR_COBRADO);          
          sentencia_sql2.setDouble(11, VALOR_POR_COBRAR);          
          sentencia_sql2.setDouble(12, ADELANTO);
          
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {//JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error datos no ingresados" + e.getMessage());}}

//CUOTAS_COBRAR_Detalle, ID_CUENTA_POR_COBRAR, NUM_CUOTA, VALOR_CUOTA, INTERES, VALOR_TOTAL_CUOTA
//CUOTAS_COBRAR_Detalle, ID_CUENTA_POR_COBRAR, NUM_CUOTA, VALOR_CUOTA, INTERES, VALOR_MORA, PAGO

public void ingresarCuentaDetalle(int ID_CUENTA_POR_COBRAR,int NUM_CUOTA,double VALOR_CUOTA, double INTERES, String FECHA_EMISION, String FECHA_VENCIMIENTO)
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        try { sql2 ="insert into cuotas_pagar_detalle (ID_CUENTA_POR_PAGAR, NUM_CUOTA, VALOR_CUOTA, INTERES, FECHA_EMISION, FECHA_VENCIMIENTO)" + "values(?,?,?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setInt(1,ID_CUENTA_POR_COBRAR);
          sentencia_sql2.setInt(2, NUM_CUOTA);
          sentencia_sql2.setDouble(3, VALOR_CUOTA);
          sentencia_sql2.setDouble(4, INTERES);
          sentencia_sql2.setString(5, FECHA_EMISION);
          sentencia_sql2.setString(6, FECHA_VENCIMIENTO);
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {//JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error cuotas no ingresados" + e.getMessage());}}
////////////////////////////////////////////////////////////////////////////////////////////////
public void consultarNumeroCuenta_cobrar()
    {  
        String sql = "SELECT MAX(ID_CUENTA_POR_PAGAR) FROM cuentas_por_pagar ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 idcuentacobrar= rs.getInt("MAX(ID_CUENTA_POR_PAGAR)");}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos de venta  " + e.getMessage());}}

public void consultarTerminoTotal(JTable tabla)
    {String [] columna ={"Codigo","Descripcion "};
       DefaultTableModel encabezado=new DefaultTableModel (null,columna);
      //poner la validacion 
       tabla.setModel(encabezado);
        String[] Dtos2 = new String[4];
        String sql =  " SELECT idtermino, descripcion FROM terminopago ";
      
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 Dtos2[0]= rs.getString("idtermino");
                 Dtos2[1]= rs.getString("descripcion");
                 encabezado.addRow(Dtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
//////////////////////////////////////////////////////////////////////////
public void consultarTermino(String codigo)
    { 
      //poner la validacion 
       
        String sql =  " SELECT  interes, adelanto, periodo,Nmeses FROM terminopago where idtermino='"+codigo+"'";
      
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 interes= rs.getDouble("interes");
                 Nperiodo= rs.getInt("periodo");
                  adelanto=rs.getDouble("adelanto");
                  Nmese=rs.getInt("Nmeses");
             }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}


public int getnperiodo(){
        return Nperiodo;}
public int getNmeses(){
        return Nmese;}
public double getinteres(){
        return interes;}
public double getadelano(){
        return adelanto;}

//////////////
public int getstock(){
        return stock;}
public int getstock_max(){
        return stock_max;}
public int getidcuentaCobrar(){
        return idcuentacobrar;}
public int getidventa(){
        return idventa;}


}
