/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factura;

import CargarEntrenamiento.lstEntrenamiento;
import arqueoCAJA.Cerrar_caja;
import arqueoCAJA.abrir_caja;
import arqueoCAJA.conexionCAja;
import arqueoCAJA.vale_caja;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import factura.lstClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import org.jfree.text.TextBox;
import org.jfree.ui.Spinner;

/**
 *
 * @author CarlosEfrain
 */
public class Factura extends javax.swing.JInternalFrame {
public static boolean tablaInstan, ivaboolean;
boolean calcula=false;
public  static String listaPorductos="";
 DecimalFormat fd = new DecimalFormat("0.00");
DefaultTableModel modeloNat;
JTextField txt=new JTextField();
JTextField txtdesc=new JTextField();
JTextField txtcodPro= new JTextField();
public static JTextField txtautocomplete=new JTextField(); 
int fila,colum,Nfilas;
String cantidadchar,descuentoChar;
double precioPro,CantidaPro,subPro,descuenPro;
public static double subtotal=0,descuento=0,iva=0,total=0;
Calendar calendario = Calendar.getInstance();
conexionFactura fact=new conexionFactura();
DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
Date fecha1, fecha2;
public  static  TextAutoCompleter textAutoCompleter ;
public  static  TextAutoCompleter textAutoCompleterProducto ;
conexionCAja micaja=new conexionCAja();
Calendar calendar = Calendar.getInstance();
DateFormat fechadf =  new SimpleDateFormat("yyyy/MM/dd");
SimpleDateFormat horadf = new SimpleDateFormat("H:m:ss");
String user="1313375980";
NumberFormat formatter = new DecimalFormat("#0.00");    
    /**
     * Creates new form Factura
     */
    public Factura() {
        initComponents();
        ////////////////
         if(micaja.consultarCaja_esta_Abierta(user)){
             btnAceptar.setEnabled(false);
             btnagreProducto.setEnabled(false);
             btnListarCliente.setEnabled(false);
         }
         else {
            
             btnAceptar.setEnabled(true);
             btnagreProducto.setEnabled(true);
             btnListarCliente.setEnabled(true);}
        //////////////////////
        tablaInstan=false;
        ivaboolean=true;
        fechaEmision.setCalendar(calendario);
        fechaVence.setCalendar(calendario);
        textAutoCompleter = null;
         textAutoCompleterProducto = null;
         fact.consultarNumeroFac();
         txtnumfac.setText(String.valueOf(("00"+(fact.getidventa()+1))));
    // textAutoCompleter = new TextAutoCompleter( Factura.txtcodigocliente);
     //TablaClientes.setDefaultRenderer(Object.class, new FormatoTablaClientes());   
 
  //////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////
         
      textAutoCompleter = new TextAutoCompleter(txtcodigocliente, new 

    AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
           // System.out.println("El usuario seleccionó: " + selectedItem);
           String cadena2="",cadena=String.valueOf(selectedItem);
           char l;
           boolean enc=true;
            fact.autcompleteCliente(cadena);
            for (int i = 0; i < cadena.length(); i++) {
                l=cadena.charAt(i);
                if(l!='-' && enc)cadena2=cadena2+l;
                else enc=false;
                }
             fact.autllenarCliente(cadena2);
        } }
      );
///////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
textAutoCompleterProducto = new TextAutoCompleter(txtcodPro, new 

    AutoCompleterCallback() {
        @Override
        public void callback(Object selectedItem) {
           // System.out.println("El usuario seleccionó: " + selectedItem);
           String cadena2="",cadena=String.valueOf(selectedItem);
           char l;
           boolean enc=true;
            fact.autcompleteProducto(cadena);
            for (int i = 0; i < cadena.length(); i++) {
                l=cadena.charAt(i);
                if(l!='-' && enc)cadena2=cadena2+l;
                else enc=false;
                }
             fact.autllenarProducto(cadena2);
        } }
      );

///////////////////
    txtcodPro.addKeyListener(new KeyAdapter()
          {
           @Override
           public void keyTyped(KeyEvent e )
                  {char caracter=e.getKeyChar();    
  char c=e.getKeyChar();
        char l;
        int ascci=(int)c;
        boolean enc=true;
        String cadena,cadena2="";
        if((c<'0'||c>'9')&&(c<'a' || c>'z') && (c<'A'||c>'Z') && ascci!=8) {}
        else {// si esta todo bien 
            cadena=txtcodPro.getText()+c;
            fact.autcompleteProducto(cadena);
            for (int i = 0; i < cadena.length(); i++) {
                l=cadena.charAt(i);
                if(l!='-' && enc)cadena2=cadena2+l;
                else enc=false;
                }
             fact.autllenarProducto(cadena2);}
            } });
/////////////////////////////////////////////////////////////////////
     txtobserva.addKeyListener(new KeyAdapter()
          {
           @Override
           public void keyTyped(KeyEvent e )
                  {char caracter=e.getKeyChar();    
                 if(txtobserva.getText().length()>60)
                     e.consume();
            } });
      
      
      
      
        txt.addKeyListener(new KeyAdapter()
          {
           @Override
           public void keyTyped(KeyEvent e )
                  {char caracter=e.getKeyChar();
                    
                // JOptionPane.showMessageDialog(null, "cantidad: "+txt.getText()+caracter+"Fila: "+tblProductosfac.getSelectedRow()+"Precio: "+modeloNat.getValueAt(fila, 6).toString());
         if(tblProductosfac.getValueAt(tblProductosfac.getSelectedRow(), 3)!=null &&(caracter<'a' || caracter>'z') && (caracter<'A'||caracter>'Z') ){
                  cantidadchar=txt.getText()+caracter;
                  fila=tblProductosfac.getSelectedRow();
                     precioPro=Double.parseDouble( modeloNat.getValueAt(fila, 6).toString());
                    try {CantidaPro=Double.parseDouble( cantidadchar);}
                    catch (Exception a) {
                CantidaPro=0; 
                    e.consume();}
                    ///stock ??? :P 
               fact.consultarProductoStock(modeloNat.getValueAt(fila,1).toString());
                  if(CantidaPro>fact.getstock()){
                        JOptionPane.showMessageDialog(null,"la cantidad es mayor al stock"+"\n stock:"+fact.getstock());
                        e.consume();
                        cantidadchar=txt.getText();
                        CantidaPro=Double.parseDouble( cantidadchar);}
              // stock ?? :P 
                     subPro=precioPro*CantidaPro;
                     modeloNat.setValueAt(subPro, fila, 8);
                      calculador();
                  }
         else e.consume();
               
                  } });
        
         txtdesc.addKeyListener(new KeyAdapter()
          {
           @Override
           public void keyTyped(KeyEvent e )
                  {char caracter=e.getKeyChar();
                    
                // JOptionPane.showMessageDialog(null, "cantidad: "+txt.getText()+caracter+"Fila: "+tblProductosfac.getSelectedRow()+"Precio: "+modeloNat.getValueAt(fila, 6).toString());
                  if(tblProductosfac.getValueAt(tblProductosfac.getSelectedRow(), 3)!=null){
                      descuentoChar=txtdesc.getText()+caracter;
                    try {
                        ///cantidad max 100 :P 
                  if(Double.parseDouble(descuentoChar)>100){ e.consume();
                         descuentoChar=txtdesc.getText();}
                       // stock ?? :P 
                     modeloNat.setValueAt(Double.parseDouble(descuentoChar), fila, 7);}
                    catch (Exception a) {
                modeloNat.setValueAt(0, fila, 7); 
                    e.consume();}
                      calculador();
                  }
                  else e.consume();
                  } });
        
        
        
        
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtcodigocliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtDireccionCliente = new javax.swing.JTextField();
        btnListarCliente = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        fechaEmision = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        txtvendedor = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txttermino = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtnumfac = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        chekiva = new javax.swing.JCheckBox();
        fechaVence = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductosfac = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtobserva = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtdescuento = new javax.swing.JTextField();
        txtsubtotal = new javax.swing.JTextField();
        txtiva = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtotal = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        btnagreProducto = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        btneliminarLinea = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Direccion ");

        jLabel2.setText("Empresa");

        jLabel3.setText("Municipalidad-ciudad ");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Codigo Cliente ");

        jLabel6.setText("Nombre");

        jLabel7.setText("Direccion ");

        txtcodigocliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtcodigoclienteMouseClicked(evt);
            }
        });
        txtcodigocliente.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtcodigoclienteInputMethodTextChanged(evt);
            }
        });
        txtcodigocliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodigoclienteActionPerformed(evt);
            }
        });
        txtcodigocliente.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtcodigoclientePropertyChange(evt);
            }
        });
        txtcodigocliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoclienteKeyTyped(evt);
            }
        });

        txtNombreCliente.setEditable(false);

        txtDireccionCliente.setEditable(false);

        btnListarCliente.setText("...");
        btnListarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcodigocliente, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnListarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtcodigocliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnListarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel4.setText("FACTURA");

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setText("Emision ");

        fechaEmision.setForeground(new java.awt.Color(255, 255, 255));
        fechaEmision.setToolTipText("");
        fechaEmision.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaEmisionMouseClicked(evt);
            }
        });

        jLabel13.setText("Vendedor");

        txtvendedor.setEditable(false);
        txtvendedor.setText("Vendedor");

        jLabel14.setText("Termino");

        txttermino.setEditable(false);
        txttermino.setText("Contado");

        jButton4.setText("...");

        jButton5.setText("...");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(fechaEmision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(txttermino))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txttermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Número:");

        txtnumfac.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtnumfac.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtnumfac.setText("001");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtnumfac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(txtnumfac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chekiva.setSelected(true);
        chekiva.setText("Incluir Iva");
        chekiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chekivaActionPerformed(evt);
            }
        });

        fechaVence.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Vence"));
        fechaVence.setForeground(new java.awt.Color(255, 255, 255));
        fechaVence.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(chekiva)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(fechaVence, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chekiva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fechaVence, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tblProductosfac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null, null, null, null, null, null},
                {"2", null, null, null, null, null, null, null, null},
                {"3", null, null, null, null, null, null, null, null},
                {"4", null, null, null, null, null, null, null, null},
                {"5", null, null, null, null, null, null, null, null},
                {"6", null, null, null, null, null, null, null, null},
                {"7", null, null, null, null, null, null, null, null},
                {"8", null, null, null, null, null, null, null, null},
                {"9", null, null, null, null, null, null, null, null},
                {"10", null, null, null, null, null, null, null, null},
                {"11", null, null, null, null, null, null, null, null},
                {"12", null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Línea", "Código", "Descripcion", "Bodega", "Cantidad", "Unidad", "Prec Unit", "Descuent", "Sub total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, true, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductosfac.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblProductosfacFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblProductosfacFocusLost(evt);
            }
        });
        tblProductosfac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosfacMouseClicked(evt);
            }
        });
        tblProductosfac.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tblProductosfacInputMethodTextChanged(evt);
            }
        });
        tblProductosfac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblProductosfacKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductosfac);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtobserva.setColumns(20);
        txtobserva.setRows(5);
        jScrollPane2.setViewportView(txtobserva);

        jLabel16.setText("Observaciones ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Subtotal ");
        jPanel9.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 17, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Descuento ");
        jPanel9.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 49, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Iva");
        jPanel9.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 77, -1));

        txtdescuento.setEditable(false);
        txtdescuento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtdescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtdescuento.setText("0.0");
        jPanel9.add(txtdescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 143, 29));

        txtsubtotal.setEditable(false);
        txtsubtotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtsubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtsubtotal.setText("0.0");
        txtsubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsubtotalActionPerformed(evt);
            }
        });
        jPanel9.add(txtsubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 143, 29));

        txtiva.setEditable(false);
        txtiva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtiva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtiva.setText("0.0");
        jPanel9.add(txtiva, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 143, 29));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel20.setText("Total");

        txtotal.setEditable(false);
        txtotal.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        txtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtotal.setText("0.0");

        btnAceptar.setText("Aceptar ");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jButton7.setText("Cancelar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton10.setText("+client");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(217, 217, 217)
                        .addComponent(jLabel4)
                        .addGap(60, 60, 60)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(291, 291, 291)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtotal))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel2)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        btnagreProducto.setText("+ Prod");
        btnagreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagreProductoActionPerformed(evt);
            }
        });

        jButton9.setText("- descu");

        jButton11.setText("+ Caja");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        btneliminarLinea.setText("- linea");
        btneliminarLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarLineaActionPerformed(evt);
            }
        });

        jButton13.setText("- Caja");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnagreProducto)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneliminarLinea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(btnagreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneliminarLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarClienteActionPerformed
        lstClientes dialog = new lstClientes(new javax.swing.JFrame(), true);
   dialog.setLocationRelativeTo(null);
   dialog.setVisible(true);    
   
    }//GEN-LAST:event_btnListarClienteActionPerformed

    private void btnagreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagreProductoActionPerformed
       lstProductos dialog2 = new lstProductos(new javax.swing.JFrame(), true);
   dialog2.setLocationRelativeTo(null);
   dialog2.setVisible(true);  // TODO add your handling code here:
    }//GEN-LAST:event_btnagreProductoActionPerformed

    private void tblProductosfacKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosfacKeyTyped

        char caracter2=evt.getKeyChar();
                   
                // JOptionPane.showMessageDialog(null, "cantidad: "+txt.getText()+caracter+"Fila: "+tblProductosfac.getSelectedRow()+"Precio: "+modeloNat.getValueAt(fila, 6).toString());
        if(tblProductosfac.getValueAt(tblProductosfac.getSelectedRow(), 3)!=null ){
           if (tblProductosfac.getSelectedColumn()==4){
                  cantidadchar=txt.getText()+caracter2;
                  fila=tblProductosfac.getSelectedRow();
                  precioPro=Double.parseDouble( modeloNat.getValueAt(fila, 6).toString());
              try {CantidaPro=Double.parseDouble( cantidadchar); }
             catch (Exception p) {
                CantidaPro=0;
                evt.consume();}
               ///stock ??? :P 
               fact.consultarProductoStock(modeloNat.getValueAt(fila,1).toString());
              if(CantidaPro>fact.getstock()){
                        JOptionPane.showMessageDialog(null,"la cantidad es mayor al stock"+"\n stock:"+fact.getstock());
                        evt.consume();
                        cantidadchar=txt.getText();
                        CantidaPro=Double.parseDouble( cantidadchar);}
              // stock ?? :P 
               subPro=precioPro*CantidaPro;
               modeloNat.setValueAt(subPro, fila, 8);
           calculador();}
           if (tblProductosfac.getSelectedColumn()==7){
             descuentoChar=txtdesc.getText()+caracter2;
                    try { ///cantidad max 100 :P 
                  if(Double.parseDouble(descuentoChar)>100){ evt.consume();
                         descuentoChar=txtdesc.getText();}
                       // stock ?? :P 
                     modeloNat.setValueAt(Double.parseDouble(descuentoChar), fila, 7);}
                    catch (Exception a) {
                modeloNat.setValueAt(0, fila, 7);
                evt.consume();
                    }
                      calculador();}
   
                  }   
        else evt.consume();
    }//GEN-LAST:event_tblProductosfacKeyTyped

    private void tblProductosfacFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblProductosfacFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductosfacFocusGained

    private void tblProductosfacFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblProductosfacFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductosfacFocusLost

    private void tblProductosfacInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblProductosfacInputMethodTextChanged
   // TODO add your handling code here:
    }//GEN-LAST:event_tblProductosfacInputMethodTextChanged

    private void tblProductosfacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosfacMouseClicked
  fila=tblProductosfac.getSelectedRow();
        if(!tablaInstan)
        { modeloNat=(DefaultTableModel) tblProductosfac.getModel();
        tblProductosfac.setModel(modeloNat); 
        TableColumn tc= tblProductosfac.getColumnModel().getColumn(4);
        TableColumn dc= tblProductosfac.getColumnModel().getColumn(7);
         TableColumn cod= tblProductosfac.getColumnModel().getColumn(1);
        TableCellEditor te  =new DefaultCellEditor(txt);
        TableCellEditor cdes  =new DefaultCellEditor(txtdesc);
        TableCellEditor codt  =new DefaultCellEditor(txtcodPro);
        tc.setCellEditor(te);
        dc.setCellEditor(cdes);
        cod.setCellEditor(codt);
        tablaInstan=true;
       // JOptionPane.showMessageDialog(null, "nuevo modelo");
        }
     
     if(tblProductosfac.getValueAt(tblProductosfac.getSelectedRow(), 3)!=null){
      calcula=true;
     } 
     else calcula=false;      // TODO add your handling code here:
    }//GEN-LAST:event_tblProductosfacMouseClicked

    private void txtsubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubtotalActionPerformed

    private void chekivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chekivaActionPerformed
if(chekiva.isSelected()) ivaboolean=true;
else ivaboolean=false;
calculador();
    }//GEN-LAST:event_chekivaActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
if (!facturalleno())JOptionPane.showMessageDialog(null, "Faltan parametros a ingresar :P ");
else{ 
if(total<=0)JOptionPane.showMessageDialog(null, "no hay cantidad que facturar :( ");
   else {   
if(mirartabla())JOptionPane.showMessageDialog(null, "problemas con la tabla :P ");
    //fechaemision fechavence subtotal descuento iva totalcobrado totalcobrar idcliente
   
   else {{
       String cadena2="",cadena=txtcodigocliente.getText();
           char l;
           boolean enc=true;
            fact.autcompleteCliente(cadena);
            for (int i = 0; i < cadena.length(); i++) {
                l=cadena.charAt(i);
                if(l!='-' && enc)cadena2=cadena2+l;
                else enc=false;
                }
             fact.autllenarCliente(cadena2);
             
             
       calculador();
        fecha1 = fechaEmision.getDate();
        fecha2 = fechaVence.getDate();
        fact.ingresarFactura(fecha.format(fecha1), fecha.format(fecha2), subtotal, descuento, iva, total, cadena2,txtobserva.getText());
        modificarProductos();
        if (!txttermino.getText().equals("Contado")){
                   //llamartermino(txttermino);
                   //Ingresarcuentas por pagar 
                   //calcular pagos e ingresar 
                   calcula_cuentas_por_cobrar();
               }
        else  {Date date=calendar.getTime();
               micaja.consultarCajaID(user);
               micaja.ingresarCajaOperacion(micaja.getidcaja(), fechadf.format(date ), horadf.format(date), "facturacion: "+txtnumfac.getText(), total);
               micaja.modificarCajaTotal(user, total);}
               nuevo();
       }
       // ideventa idproducto cantidad precio totalProducto descuento 
}}
       
}
// TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btneliminarLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarLineaActionPerformed
       if(tblProductosfac.getSelectedRow()>=0){
           modeloNat.setValueAt(null, tblProductosfac.getSelectedRow(), 1);
           modeloNat.setValueAt(null, tblProductosfac.getSelectedRow(), 2);
           modeloNat.setValueAt(null, tblProductosfac.getSelectedRow(), 3);
           modeloNat.setValueAt(null, tblProductosfac.getSelectedRow(), 4);
           modeloNat.setValueAt(null, tblProductosfac.getSelectedRow(), 5);
           modeloNat.setValueAt(null, tblProductosfac.getSelectedRow(), 6);
           modeloNat.setValueAt(null, tblProductosfac.getSelectedRow(), 7);
           modeloNat.setValueAt(null, tblProductosfac.getSelectedRow(), 8);
           calculador();
       }
        
    }//GEN-LAST:event_btneliminarLineaActionPerformed

    private void txtcodigoclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodigoclienteActionPerformed
     // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoclienteActionPerformed

    private void txtcodigoclienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoclienteKeyTyped
        char c=evt.getKeyChar();
        char l;
        int ascci=(int)c;
        boolean enc=true;
        String cadena,cadena2="";
        if((c<'0'||c>'9')&&(c<'a' || c>'z') && (c<'A'||c>'Z') && ascci!=8) {//evt.consume();
       //JOptionPane.showMessageDialog(null, ":PaSA :) "+ascci);
        }// si no es alfanomeroco se borra
      //  else if (ascci==8 ||ascci==10){//cadena=txtcodigocliente.getText();
           
            // fact.autcompleteCliente(cadena);
            /*
             for (int i = 0; i < cadena.length(); i++) {
                l=cadena.charAt(i);
                if(l!='-' && enc)cadena2=cadena2+l;
                else enc=false;
                }   
            fact.autllenarCliente(cadena2);
                   */
        // JOptionPane.showMessageDialog(null, ":PaSA :) "+ascci);
      //  } // si es enter o borar no concatena 
        
        else {// si esta todo bien 
            cadena=txtcodigocliente.getText()+c;
       
            fact.autcompleteCliente(cadena);
            for (int i = 0; i < cadena.length(); i++) {
                l=cadena.charAt(i);
                if(l!='-' && enc)cadena2=cadena2+l;
                else enc=false;
                }
             fact.autllenarCliente(cadena2);
              
        //    JOptionPane.showMessageDialog(null, cadena2);
            
            
    }
        
                // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoclienteKeyTyped

    private void txtcodigoclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcodigoclienteMouseClicked
           // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoclienteMouseClicked

    private void txtcodigoclientePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtcodigoclientePropertyChange
      // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoclientePropertyChange

    private void txtcodigoclienteInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtcodigoclienteInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoclienteInputMethodTextChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
lstTerminoPago dialog3 = new lstTerminoPago(new javax.swing.JFrame(), true);
   dialog3.setLocationRelativeTo(null);
   dialog3.setVisible(true); 
    if (!txttermino.getText().equals("Contado")){
        fact.consultarTermino(txttermino.getText());
           Calendar calenfin = Calendar.getInstance();
           calenfin.setTime(fechaEmision.getDate());
     calenfin.add(Calendar.MONTH, fact.getNmeses()*fact.getnperiodo());
     fechaVence.setCalendar(calenfin);
    
    }// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void fechaEmisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaEmisionMouseClicked
      // TODO add your handling code here:
    }//GEN-LAST:event_fechaEmisionMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        nuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if(!micaja.consultarCaja_esta_Abierta(user)){
            vale_caja dialogcv = new vale_caja(new javax.swing.JFrame(), true);
   dialogcv.setLocationRelativeTo(null);
   dialogcv.setVisible(true);  
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
   abrir_caja dialogc = new abrir_caja(new javax.swing.JFrame(), true);
   dialogc.setLocationRelativeTo(null);
   dialogc.setVisible(true);      
    if(micaja.consultarCaja_esta_Abierta(user)){
             btnAceptar.setEnabled(false);
             btnagreProducto.setEnabled(false);
             btnListarCliente.setEnabled(false);
         }
         else {
             btnAceptar.setEnabled(true);
             btnagreProducto.setEnabled(true);
             btnListarCliente.setEnabled(true);}// TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
   Cerrar_caja dialogcc = new Cerrar_caja(new javax.swing.JFrame(), true);
   dialogcc.setLocationRelativeTo(null);
   dialogcc.setVisible(true);   
    if(micaja.consultarCaja_esta_Abierta(user)){
             btnAceptar.setEnabled(false);
             btnagreProducto.setEnabled(false);
             btnListarCliente.setEnabled(false);
             this.dispose();
         }
         else {
             btnAceptar.setEnabled(true);
             btnagreProducto.setEnabled(true);
             btnListarCliente.setEnabled(true);}// TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnListarCliente;
    private javax.swing.JButton btnagreProducto;
    private javax.swing.JButton btneliminarLinea;
    private javax.swing.JCheckBox chekiva;
    private com.toedter.calendar.JDateChooser fechaEmision;
    private com.toedter.calendar.JDateChooser fechaVence;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable tblProductosfac;
    public static javax.swing.JTextField txtDireccionCliente;
    public static javax.swing.JTextField txtNombreCliente;
    public static javax.swing.JTextField txtcodigocliente;
    public static javax.swing.JTextField txtdescuento;
    public static javax.swing.JTextField txtiva;
    private javax.swing.JLabel txtnumfac;
    private javax.swing.JTextArea txtobserva;
    public static javax.swing.JTextField txtotal;
    public static javax.swing.JTextField txtsubtotal;
    public static javax.swing.JTextField txttermino;
    private javax.swing.JTextField txtvendedor;
    // End of variables declaration//GEN-END:variables

public boolean SoloFloat(char c,String valor)
    { int ascci=(int) c;
        if ((c<'0'||c>'9')&& c=='.'&& ascci!=10 && ascci!=8){
        try {
            Integer.parseInt(valor);
        return true; }
    catch (NumberFormatException nfe){
        if ( c=='.'){return true;}
        else return false;
        
    }
    }
    return false;}
public boolean solodescuento(char valor){
    if ((valor<'0'||valor>'9')|| valor=='.'){
        try { if (Integer.parseInt(valor+txtdesc.getText())>100 && Integer.parseInt(valor+txtdesc.getText())<0)
       return true;
          else return false;
            
        } catch (Exception e) {
            return true;
        }
  }
    
   else return false;
}
public boolean facturalleno(){
if(txtNombreCliente.getText().equals("")||txtvendedor.getText().equals("")||txttermino.getText().equals("")||fechaEmision.getDate()==null||fechaVence.getDate()==null)
    return false;
else 
    if(fechaVence.getDate().before(fechaEmision.getDate())){
        JOptionPane.showMessageDialog(null, "La fecha de vencimiento es menor a la de emision");
          return false;}
      else return true;
}
///////////////////////////////////////////////////////////////////////////////////////////
public void modificarProductos(){
if(!tablaInstan)
        { modeloNat=(DefaultTableModel) tblProductosfac.getModel();
        tblProductosfac.setModel(modeloNat); 
        TableColumn tc= tblProductosfac.getColumnModel().getColumn(4);
        TableColumn dc= tblProductosfac.getColumnModel().getColumn(7);
         TableColumn cod= tblProductosfac.getColumnModel().getColumn(1);
        TableCellEditor te  =new DefaultCellEditor(txt);
        TableCellEditor cdes  =new DefaultCellEditor(txtdesc);
        TableCellEditor codt  =new DefaultCellEditor(txtcodPro);
        tc.setCellEditor(te);
        dc.setCellEditor(cdes);
        cod.setCellEditor(codt);
        tablaInstan=true;
      //  JOptionPane.showMessageDialog(null, "nuevo modelo");
        }
    fact.consultarNumeroFac();
    int vent=fact.getidventa();
    double descu;
    Nfilas=tblProductosfac.getRowCount();
   //  JOptionPane.showMessageDialog(null, "hay  :  ;) "+Nfilas);
    for (int i = 0; i < Nfilas; i++) {
       // JOptionPane.showMessageDialog(null, "hay vamos :  ;) ");
        if (tblProductosfac.getValueAt(i, 3)!=null){
            try {
                descu=Double.parseDouble(modeloNat.getValueAt(i, 7).toString());
            } catch (Exception e) {
                descu=0;
            }
             // ideventa idproducto cantidad precio totalProducto descuento 
        fact.ingresardetalleVenta(vent, modeloNat.getValueAt(i, 1).toString() , Double.parseDouble(modeloNat.getValueAt(i, 4).toString()), Double.parseDouble(modeloNat.getValueAt(i, 6).toString()), Double.parseDouble(modeloNat.getValueAt(i, 8).toString()),descu);
        fact.modificarStock(modeloNat.getValueAt(i, 1).toString(), Double.parseDouble(modeloNat.getValueAt(i, 4).toString()));
        
        }}
       
    }

/////////////////////////////////////////////
public boolean mirartabla(){
if(!tablaInstan)
        { modeloNat=(DefaultTableModel) tblProductosfac.getModel();
        tblProductosfac.setModel(modeloNat); 
        TableColumn tc= tblProductosfac.getColumnModel().getColumn(4);
        TableColumn dc= tblProductosfac.getColumnModel().getColumn(7);
         TableColumn cod= tblProductosfac.getColumnModel().getColumn(1);
        TableCellEditor te  =new DefaultCellEditor(txt);
        TableCellEditor cdes  =new DefaultCellEditor(txtdesc);
        TableCellEditor codt  =new DefaultCellEditor(txtcodPro);
        tc.setCellEditor(te);
        dc.setCellEditor(cdes);
        cod.setCellEditor(codt);
        tablaInstan=true;
      //  JOptionPane.showMessageDialog(null, "nuevo modelo");
        }
       
    Nfilas=tblProductosfac.getRowCount();
   //  JOptionPane.showMessageDialog(null, "hay  :  ;) "+Nfilas);
    for (int i = 0; i < Nfilas; i++) {
       // JOptionPane.showMessageDialog(null, "hay vamos :  ;) ");
        if (tblProductosfac.getValueAt(i, 3)!=null){
            try {
                Double.parseDouble(modeloNat.getValueAt(i, 8).toString());
                if (Double.parseDouble(modeloNat.getValueAt(i, 8).toString())<=0){
                 //   JOptionPane.showMessageDialog(null, "jamasss ");
                    return true;}
                
            } catch (Exception e) {
                
            }}
       
    }
    return false;
}


////////////////////////////////////
public void calculador(){
    subtotal=0;
    descuento=0;
    iva=0;
    total=0;
    listaPorductos=""; 
         if(!tablaInstan)
        { modeloNat=(DefaultTableModel) tblProductosfac.getModel();
        tblProductosfac.setModel(modeloNat); 
       TableColumn tc= tblProductosfac.getColumnModel().getColumn(4);
        TableColumn dc= tblProductosfac.getColumnModel().getColumn(7);
         TableColumn cod= tblProductosfac.getColumnModel().getColumn(1);
        TableCellEditor te  =new DefaultCellEditor(txt);
        TableCellEditor cdes  =new DefaultCellEditor(txtdesc);
        TableCellEditor codt  =new DefaultCellEditor(txtcodPro);
        tc.setCellEditor(te);
        dc.setCellEditor(cdes);
        cod.setCellEditor(codt);
        tablaInstan=true;
      //  JOptionPane.showMessageDialog(null, "nuevo modelo");
        }
 
    Nfilas=tblProductosfac.getRowCount();
   //  JOptionPane.showMessageDialog(null, "hay  :  ;) "+Nfilas);
    for (int i = 0; i < Nfilas; i++) {
       // JOptionPane.showMessageDialog(null, "hay vamos :  ;) ");
        if (tblProductosfac.getValueAt(i, 3)!=null){
     subtotal=subtotal+Double.parseDouble(modeloNat.getValueAt(i, 8).toString());
     listaPorductos=listaPorductos+","+modeloNat.getValueAt(i, 1).toString();
     //JOptionPane.showMessageDialog(null, "hay vamos sub sub  :  ;) "+subtotal);
     if(!tblProductosfac.getValueAt(i, 7).toString().equals("")&&(tblProductosfac.getValueAt(i, 7)!= null)){
       
     descuento=((Double.parseDouble(modeloNat.getValueAt(i, 7).toString())/100)  *(Double.parseDouble(modeloNat.getValueAt(i, 8).toString())))+descuento;  } 
     
        }
       else descuento=descuento+0.0;
   if (ivaboolean) iva=(subtotal-descuento)*0.12;
   else iva=0;
    total=(subtotal-descuento)+iva;
    }
    txtsubtotal.setText(String.valueOf(fd.format(subtotal)));
    txtdescuento.setText(String.valueOf(fd.format(descuento)));
    txtiva.setText(String.valueOf(fd.format(iva)));
    txtotal.setText(String.valueOf(fd.format(total)));
    // JOptionPane.showMessageDialog(null, "hay vamos :  ;) ");
}

public void calcula_cuentas_por_cobrar(){
  //(ID_VENTA, FECHA_REGISTRO, INTERES, VALOR_INTERES, TOTAL_CUENTA, CANT_CUOTAS, VALOR_CUOTAS, FECHA_COBRO, NUM_CUOTA_COBRADA, VALOR_COBRADO, VALOR_POR_COBRAR, ADELANTO)  
   fact.consultarTermino(txttermino.getText());
    
    double interes=fact.getinteres(),adelanto=fact.getadelano();
    int Nperiodo=fact.getnperiodo(),Nmese=fact.getNmeses();
    ////////////////////////
    int vent=fact.getidventa();
    double valor_futuro=0,valor_presente,valor_cuotas=0,valor_adelanto=0,valor_interes=0; 
    valor_adelanto=total*(adelanto/100);
    JOptionPane.showMessageDialog(null,"Valor de adelanto a cancelar: " +formatter.format(valor_adelanto));
    /////////////////////////////////
    Date date=calendar.getTime();
    micaja.consultarCajaID(user);
    micaja.ingresarCajaOperacion(micaja.getidcaja(), fechadf.format(date ), horadf.format(date), "facturacion: "+txtnumfac.getText(), valor_adelanto);
    micaja.modificarCajaTotal(user, valor_adelanto);
               
    ///////////////////////////////
    valor_presente=total-valor_adelanto;
    valor_futuro=valor_presente*(1+((interes/100)*Nperiodo));
    valor_interes=valor_futuro-valor_presente;
    valor_cuotas=valor_futuro/Nperiodo;
    /////////////////////
     Calendar calenEmision = Calendar.getInstance();
     calenEmision.setTime(fechaEmision.getDate());
     calenEmision.add(Calendar.MONTH, Nmese*Nperiodo);
     //fecha.format(calenEmision.getTime())
     //(ID_VENTA, FECHA_REGISTRO, INTERES, VALOR_INTERES, TOTAL_CUENTA, CANT_CUOTAS, VALOR_CUOTAS, FECHA_COBRO, NUM_CUOTA_COBRADA, VALOR_COBRADO, VALOR_POR_COBRAR, ADELANTO)  
     fact.ingresarCuentaPorCobrar(fact.getidventa(),fecha.format(fechaEmision.getDate()) ,interes, valor_interes, valor_futuro, Nperiodo, valor_cuotas, fecha.format(calenEmision.getTime()), 0, 0, valor_futuro, valor_adelanto);
    /////////////////////////////////////////////
    //////////////cuotitas 
    fact.consultarNumeroCuenta_cobrar();
    int numeroCuenta_cobrar=fact.getidcuentaCobrar();
    calenEmision.setTime(fechaEmision.getDate());
    //CUOTAS_COBRAR_Detalle, ID_CUENTA_POR_COBRAR, NUM_CUOTA, VALOR_CUOTA, INTERES, VALOR_TOTAL_CUOTA
    for (int i = 0; i < Nperiodo; i++) {
        
    calenEmision.add(Calendar.MONTH,Nmese);
    fact.ingresarCuentaDetalle(numeroCuenta_cobrar, i+1, valor_cuotas, interes,fecha.format(fechaEmision.getDate()),fecha.format(calenEmision.getTime()));     
    }
}
public void nuevo(){
//listaPorductos="";
txtcodigocliente.setText("");
txtNombreCliente.setText("");
txtDireccionCliente.setText("");


txtiva.setText("0.0");
txtdescuento.setText("0.0");
txtsubtotal.setText("0.0");
txtotal.setText("0.0");
txtobserva.setText("");
txttermino.setText("Contado");
fechaEmision.setCalendar(calendario);
fechaVence.setCalendar(calendario);
    for (int i = 0; i < modeloNat.getRowCount(); i++) {
           modeloNat.setValueAt(null, i, 1);
           modeloNat.setValueAt(null, i, 2);
           modeloNat.setValueAt(null, i, 3);
           modeloNat.setValueAt(null, i, 4);
           modeloNat.setValueAt(null, i, 5);
           modeloNat.setValueAt(null, i, 6);
           modeloNat.setValueAt(null, i, 7);
           modeloNat.setValueAt(null, i, 8);
        
    }
calculador();
}

}


