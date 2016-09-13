/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexionBase;


import conexionBase.conexionBase;
import entrenamiento.Entrenamiento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.print.DocFlavor.STRING;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class conexionEntrena extends conexionBase {
            DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
            String convertido ;
            String nombreEntreno="";
            int semana,dia;
            public static  boolean cargando=false;
     
    
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

public conexionEntrena()
    { conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/force", "root","123456"); }

//*************************************************************************************************+
public conexionEntrena(  String nombrecliente1,  String apellido1, String cedula1, String telefono1, String direccion1, String sex,int nc)
    {  nombrecliente  =nombrecliente1;
       apellido =apellido1;
       cedula=cedula1;
       telefono=telefono1;
       direccion=direccion1;
       sexo=sex;
       numcomprs=nc;
       conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/sss", "root","123456");}
//*************************************************************************************************
public void ingresarP(int semana,  int dia,  double peso, int codentreno)
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        try { sql2 ="insert into planentreno ( semana, dia, peso,idEntreno)" + "values(?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setInt(1,semana);
          sentencia_sql2.setInt(2, dia);
          sentencia_sql2.setDouble(3, peso);
          sentencia_sql2.setInt(4, codentreno);
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {//JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error datos no ingresados en Planentreno" + e.getMessage());}}
  //******************************************************************************************************
public void ingresarEntreno (String nombre,  String tipo, int idEntrenador, int sema, int dia)
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        try { sql2 ="insert into entrenamiento ( nombre, tipo, identrenador, semana,dia)" + "values(?,?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setString(1,nombre);
          sentencia_sql2.setString(2, tipo);
          sentencia_sql2.setInt(3, idEntrenador);
          sentencia_sql2.setInt(4, sema);
          sentencia_sql2.setInt(5, dia);
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error datos no ingresados" + e.getMessage());}}
  //******************************************************************************************************
public void ModificarEntreno (String codigo,  String tipo, int idEntrenador, int sema, int dia)
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        try { sql2 ="update entrenamiento set  tipo=?, identrenador=?, semana=?,dia=? where idEntreno like '%"+codigo+"%'";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setString(1,tipo);
          sentencia_sql2.setInt(2, idEntrenador);
          sentencia_sql2.setInt(3, sema);
          sentencia_sql2.setInt(4, dia);
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error datos no ingresados" + e.getMessage());}}
  //******************************************************************************************************
//*******************************************************************************************************
public void eliminarPlanEntreno(String identreno)
    {int n=0;
        String sql;
        PreparedStatement sentencia_sql;
        try {
            sql ="DELETE FROM planentreno WHERE idEntreno='"+identreno+"';";

            sentencia_sql = miConexion.prepareStatement(sql);
            n= sentencia_sql.executeUpdate();
            if(n>0)
            {
            }}
   catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Error al eliminar Paln Entreno: " + e.getMessage());}}
//******************************************************************************
public void eliminarEntreno(String identreno)
    {int n=0;
        String sql;
        PreparedStatement sentencia_sql;
        try {
            sql ="DELETE FROM entrenamiento WHERE idEntreno='"+identreno+"';";

            sentencia_sql = miConexion.prepareStatement(sql);
            n= sentencia_sql.executeUpdate();
            if(n>0)
            {
            }}
   catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Error al eliminar Entreno: " + e.getMessage());}}
//******************************************************************************
//******************************************************************************
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
public void consulNombreEntreno(  String  nombre)
    {String sql = "select nombre from entrenamiento where nombre='"+nombre+"'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 nombreEntreno= rs.getString("nombre");}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos loco " + e.getMessage());}}
//*****************************************************************************

public void consultarEntrenaTotal(JTable tabla)
    {String [] columna ={"codigo","nombre ","Categoria"};
       DefaultTableModel encabezado=new DefaultTableModel (null,columna);
      
       tabla.setModel(encabezado);
        String[] entrenadorDtos2 = new String[3];
        String sql = "select * from entrenamiento ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 entrenadorDtos2[0]= String.valueOf(rs.getInt("idEntreno"));
                 entrenadorDtos2[1]= rs.getString("nombre");
                 entrenadorDtos2[2]= rs.getString("tipo");
                 encabezado.addRow(entrenadorDtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
//*****************************************************************************
public void consulDiaEntreno(  String id1 )
    {String sql = "select semana,dia from entrenamiento where idEntreno='"+id1+"'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 semana= rs.getInt("semana");
                 dia= rs.getInt("dia");}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos loco " + e.getMessage());}}
//*****************************************************************************
public void consultarplantotal(Date f , String  identreno, JTable tabla, boolean lun, boolean mar, boolean mier, boolean jue, boolean vier, boolean sab )
    {   Calendar calendario = Calendar.getInstance();
        calendario.setTime(f);
        String sql = "select * from planentreno where idEntreno= "+identreno+" order by idPlan ";
        String [] columna ={"Fecha","Peso ","Serie"};
        int cnt=0;
       DefaultTableModel encabezado=new DefaultTableModel (null,columna);
      
        tabla.setModel(encabezado);
        String[] clienteDtos2 = new String[6];
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
        
       //  JOptionPane.showMessageDialog(null,"entroo :)");
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 cnt=cnt+1; 
                 clienteDtos2[0]= " ";
                 clienteDtos2[1]= rs.getString("peso");
                // JOptionPane.showMessageDialog(null, "siii funka :')");
                 encabezado.addRow(clienteDtos2);
             }
            rs.close();}
 catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}
 int diasemana=calendario.get(Calendar.DAY_OF_WEEK);	 
 //JOptionPane.showMessageDialog(null, "dia semana: "+diasemana);
 //calendario.add(Calendar.DAY_OF_MONTH, -2);    
 int cd=diasemana;
 // pasar el dia al lunes para calcular fechas 
 if(diasemana==1) calendario.add(Calendar.DAY_OF_MONTH, 1);
 else if(diasemana==3) calendario.add(Calendar.DAY_OF_MONTH, -1);
 else if(diasemana==4) calendario.add(Calendar.DAY_OF_MONTH, -2);
 else if(diasemana==5) calendario.add(Calendar.DAY_OF_MONTH, -3);
 else if(diasemana==6) calendario.add(Calendar.DAY_OF_MONTH, -4);
 else if(diasemana==7) calendario.add(Calendar.DAY_OF_MONTH, -5);
 
 
 
 
 
 // pasar el dia al lunes para calcular fechas 
 for (int j = 0; j <cnt; j++) {
              if (lun){convertido = fecha.format(calendario.getTime()); 
                          encabezado.setValueAt(convertido, j, 0);
                          j=j+1;}
                  calendario.add(Calendar.DAY_OF_MONTH, 1);
              if (mar) {convertido = fecha.format(calendario.getTime()); 
                          encabezado.setValueAt(convertido, j, 0);
                          j=j+1;}
                  calendario.add(Calendar.DAY_OF_MONTH, 1);
              if (mier) {convertido = fecha.format(calendario.getTime()); 
                          encabezado.setValueAt(convertido, j, 0);
                          j=j+1;}
                  calendario.add(Calendar.DAY_OF_MONTH, 1);
              if (jue) {convertido = fecha.format(calendario.getTime()); 
                          encabezado.setValueAt(convertido, j, 0);
                          j=j+1;}
                  calendario.add(Calendar.DAY_OF_MONTH, 1);
              if (vier) {convertido = fecha.format(calendario.getTime()); 
                          encabezado.setValueAt(convertido, j, 0);
                          j=j+1;}
                  calendario.add(Calendar.DAY_OF_MONTH, 1);
              if (sab) {convertido = fecha.format(calendario.getTime()); 
                          encabezado.setValueAt(convertido, j, 0);
                          j=j+1;}
              j=j-1;
                  calendario.add(Calendar.DAY_OF_MONTH, 1);
                  calendario.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
//*****************************************************************************
 public boolean consultarEntrenamientoCargado( String identreno)
    {
     
        String[] entrenadorDtos2 = new String[4];
        String sql = "SELECT *\n" +
            "from cargas\n" +
            "where idfede='"+identreno+"'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
        boolean find=false;
//id, idfederado, rm, categoria, fecha, observacion
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 find=true;
                 }
             rs.close(); }
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}
   return find;
    }
 ////////////////////////////////////////////////////////////////////////////////////////
//*****************************************************************************
public void consultarplanCargar( String  identreno, JTable tabla)
    {   
       
       cargando=true;
        int cnt=0;
        DefaultTableModel encabezado=new DefaultTableModel ();
        encabezado=(DefaultTableModel) tabla.getModel();
        tabla.setModel(encabezado);
         int nuFlias=encabezado.getRowCount()-1;
       //////////////limpiar tabla
        for(int i=nuFlias;i>=0;i--){ 
            encabezado.removeRow(i); }
       ////////////////
        String[] clienteDtos2 = new String[6];
       String   sql = "SELECT entrenamiento.tipo, entrenamiento.dia, entrenamiento.semana, entrenamiento.identrenador, entrenador.nombres, entrenador.apellidos\n" +
" FROM entrenamiento, entrenador\n" +
"where entrenamiento.idEntreno='"+identreno+"' and entrenador.identrenador=entrenamiento.identrenador ";
  PreparedStatement  sentencia_sql = null;
  ResultSet   rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 cnt=cnt+1; 
                 Entrenamiento.comboSemana.setSelectedItem(rs.getString("semana"));
                 Entrenamiento.comboDias.setSelectedItem(rs.getString("dia"));
                 Entrenamiento.combCategoria.setSelectedItem(rs.getString("tipo"));
                 Entrenamiento.txtcodentrenador.setText(rs.getString("identrenador"));
                 Entrenamiento.txtnombreentrenador.setText(rs.getString("nombres")+" "+rs.getString("apellidos"));
                 
             }
            rs.close();}
 catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}
 
         sql = "select * from planentreno where idEntreno= '"+identreno+"' order by idPlan ";
         sentencia_sql = null;
         rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 cnt=cnt+1; 
                 clienteDtos2[0]= "SEMANA "+rs.getString("semana");
                 clienteDtos2[1]= "DIA "+rs.getString("dia");
                 clienteDtos2[2]= rs.getString("peso");
                // JOptionPane.showMessageDialog(null, "siii funka :')");
                 encabezado.addRow(clienteDtos2);
             }
            rs.close();}
 catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}
 /**/   
  cargando=false;
    }

public int getId(){
return id;}
public String getnombreEntreno()
{return nombreEntreno;}
public int getsemana(){
return semana;}
public int getdia(){
return dia;}

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

}