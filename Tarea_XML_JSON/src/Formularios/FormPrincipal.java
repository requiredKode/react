
package Formularios;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Writer;
import javax.swing.DefaultListModel;


public final class FormPrincipal extends javax.swing.JFrame
{
    
    DefaultTableModel tabla = new DefaultTableModel();
    
    Map <String, Informacion> Info = new TreeMap<>();
    
    XMLOutputter xmlOutput = new XMLOutputter();


    
    public FormPrincipal()
    {
        initComponents();
        
        tabla.addColumn("CÉDULA");
        tabla.addColumn("NOMBRE");
        tabla.addColumn("TELÉFONO");
        tabla.addColumn("TELÉFONO2");
        tabla.addColumn("E-MAIL");
        tabla.addColumn("DIRECCIÓN");
        tabla.addColumn("FECHA DE NACIMIENTO");
        tablaDatos.setModel(tabla);
       
    }
    
    
    public void IngresarDatos()
    {
        
        Info.put(txtCedula.getText(), new Informacion(txtCedula.getText(), txtNombre.getText()
                , txtTelefono1.getText(), txtTelefono2.getText(), txtEmail.getText(), txtDireccion.getText(), txtFecha.getText())); 

                txtCedula.setText("");
                txtNombre.setText("");
                txtTelefono1.setText("");
                txtTelefono2.setText(""); 
                txtEmail.setText(""); 
                txtDireccion.setText(""); 
                txtFecha.setText(""); 
                
                MostrarDatos();                
    }
     
    
    public void MostrarDatos()
    {        
        while (tabla.getRowCount() > 0) 
        {
        tabla.removeRow(0);
        }
        
        for (Map.Entry < String, Informacion > producto : Info.entrySet())
        { 
            String cedula = producto.getKey(); 
            Informacion valor = producto.getValue(); 
            
            String Datos[]=new String[100];
      
          
            Datos[0] = cedula;
            Datos[1] = valor.getNombre();
            Datos[2] = valor.getTelefono1();
            Datos[3] = valor.getTelefono2();
            Datos[4] = valor.getEmail();
            Datos[5] = valor.getDireccion();
            Datos[6] = valor.getFechaNac();
            tabla.addRow(Datos);
         
        }
    }
    
    
    public void GuardarXML()
    {
        try
        {
            
            Element info = new Element("PWC"); 
            Document document = new Document(info);
            
            for(int Contador=0; Contador<tablaDatos.getRowCount(); Contador++)
            {
                
            Element dato = new Element("DATOS");
            
            dato.addContent(new Element("Cedula").setText(tablaDatos.getValueAt(Contador,0).toString())); 
            dato.addContent(new Element("Nombre").setText(tablaDatos.getValueAt(Contador,1).toString()));
            dato.addContent(new Element("Telefono_1").setText(tablaDatos.getValueAt(Contador,2).toString()));
            dato.addContent(new Element("Telefono_2").setText(tablaDatos.getValueAt(Contador,3).toString()));
            dato.addContent(new Element("Email").setText(tablaDatos.getValueAt(Contador,4).toString()));
            dato.addContent(new Element("Direccion").setText(tablaDatos.getValueAt(Contador,5).toString()));
            dato.addContent(new Element("Fecha_Nacimiento").setText(tablaDatos.getValueAt(Contador,6).toString()));
   
            document.getRootElement().addContent(dato);  

            xmlOutput.setFormat(Format.getPrettyFormat());  
            //xmlOutput.output(document, new FileWriter("C:/Empleados_ACME.xml"));    
            xmlOutput.output(document, new FileWriter("Empleados_ACME.xml"));            
            }
            
            JOptionPane.showMessageDialog(this, "Xml guardado con exito.");
            
        }
        catch (IOException io) 
        {  
            System.out.println(io.getMessage());  
        }         
    }
    
    
    public void EliminarDatos()
    {        
        Boolean A = false;
        A = Info.containsKey(txtEliminar.getText());
        
        if (A == true)
        {
        Info.remove(txtEliminar.getText());
        JOptionPane.showMessageDialog(rootPane, "Información eliminada correctamente." );
        txtEliminar.setText("");
        }
        else {JOptionPane.showMessageDialog(rootPane, "La información de la cédula: " + txtEliminar.getText() + "  no está almacenada.");}
        
        MostrarDatos();
    }
    
    
    public void ModificarDatos()
    {
        int seleccion=tablaDatos.getSelectedRow();
        
        if(seleccion>=0)
        {
            txtCedula.setText(tablaDatos.getValueAt(seleccion, 0).toString() );
            txtNombre.setText(tablaDatos.getValueAt(seleccion, 1).toString());
            txtTelefono1.setText(tablaDatos.getValueAt(seleccion, 2).toString());
            txtTelefono2.setText(tablaDatos.getValueAt(seleccion, 3).toString());
            txtEmail.setText(tablaDatos.getValueAt(seleccion, 4).toString());
            txtDireccion.setText(tablaDatos.getValueAt(seleccion, 5).toString());
            txtFecha.setText(tablaDatos.getValueAt(seleccion,6).toString());
        }
        else
        {
        JOptionPane.showMessageDialog(this,"No has seleccionado ningun dato de la tabla");
        }
    }
      
    
    public void BuscarDatos()
    {        
        Boolean A = false;
        String X;
        
        while (tabla.getRowCount() > 0) 
        {
        tabla.removeRow(0);
        }
        
        A = Info.containsKey(txtBuscar.getText());
        if (A == true)
        {
            X = Info.get(txtBuscar.getText()).toString();
            JOptionPane.showMessageDialog(rootPane,  X);
            txtBuscar.setText("");
        }
        else 
        {   
            JOptionPane.showMessageDialog(rootPane, "La información no se encuentra." );
        }

        MostrarDatos();
    }
    
    
    
    public void GuardarJSON2()
    {               
        Writer writer = null;
        
        DefaultListModel Lista = new DefaultListModel();
        
        try {
            
            writer = new FileWriter("Prueba.json");
            Gson gson = new GsonBuilder().create();

            
        for(int x=0; x<tablaDatos.getRowCount(); x++)
        {
            JSONObject objB = new JSONObject();
            
            JSONObject objA = new JSONObject();
            objA.put("Cedula", tablaDatos.getValueAt(x,0).toString());
            objA.put("Nombre", tablaDatos.getValueAt(x,1).toString());
            objA.put("Telefono_1", tablaDatos.getValueAt(x,2).toString());
            objA.put("Telefono_2", tablaDatos.getValueAt(x,3).toString());
            objA.put("Email", tablaDatos.getValueAt(x,4).toString());
            objA.put("Direccion", tablaDatos.getValueAt(x,5).toString());
            objA.put("Fecha_Nacimiento", tablaDatos.getValueAt(x,6).toString());  
            
            objB.put("Empleado", objA);
            
            Lista.addElement(objB);
            
            
        }
        gson.toJson(Lista, writer);
            //gson.toJson(Lista, writer);
            
            writer.close(); 
            
        } catch (IOException ex) {
            Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(FormPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    
    public void GuardarJSON() throws IOException
    {               
        FileWriter file = new FileWriter("JSON.json");
        
        JSONObject objA = new JSONObject();
        objA.put("PWC", file);
        
        for(int x=0; x<tablaDatos.getRowCount(); x++)
        {
            
            JSONObject objB = new JSONObject();
            JSONObject objC = new JSONObject();
            
            
            objB.put("Cedula", tablaDatos.getValueAt(x,0).toString());
            objB.put("Nombre", tablaDatos.getValueAt(x,1).toString());
            objB.put("Telefono_1", tablaDatos.getValueAt(x,2).toString());
            objB.put("Telefono_2", tablaDatos.getValueAt(x,3).toString());
            objB.put("Email", tablaDatos.getValueAt(x,4).toString());
            objB.put("Direccion", tablaDatos.getValueAt(x,5).toString());
            objB.put("Fecha_Nacimiento", tablaDatos.getValueAt(x,6).toString());   
            
            objC.put("Empleado", objB);
            
            
            file.write(objC.toJSONString());
        }
            
        
            file.flush();
            file.close();
            JOptionPane.showMessageDialog(this, "JSON guardado con exito.");
                 
    }
    
    
    public void MostrarJSON() 
    {
        FormJSON FJ = new FormJSON();
        FJ.setVisible(true);
    }
    
    
    
    
    
    
    class Informacion { 

 	private String cedula; 
 	private String nombre; 
 	private String telefono1; 
        private String telefono2; 
        private String email; 
        private String direccion; 
        private String fechaNac; 
 	
        public Informacion() { 
 	} 

 	public Informacion(String ced, String nomb, String tel, String tel2, String emai, String dir, String fech) 
        { 
		this.cedula = ced; 
 		this.nombre = nomb; 
 		this.telefono1 = tel; 
                this.telefono2 = tel2;
                this.email = emai;
                this.direccion = dir;
                this.fechaNac = fech;
 	} 

        public String getCedula() {
            return cedula;
        }

        public void setCedula(String cedula) {
            this.cedula = cedula;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTelefono1() {
            return telefono1;
        }

        public void setTelefono1(String telefono1) {
            this.telefono1 = telefono1;
        }

        public String getTelefono2() {
            return telefono2;
        }

        public void setTelefono2(String telefono2) {
            this.telefono2 = telefono2;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getFechaNac() {
            return fechaNac;
        }

        public void setFechaNac(String fechaNac) {
            this.fechaNac = fechaNac;
        }
  	 	 
 	@Override 
 	public String toString() { 
 		return this.cedula+"  --  "+this.nombre+"  --  "+this.telefono1+"  --  "+this.telefono2+"  --  "+this.email+"  --  "+this.direccion+"  --  "+this.fechaNac; 
 	}    
}
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtTelefono2 = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        txtTelefono1 = new javax.swing.JTextField();
        btnInsertar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtEliminar = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnMostrarXML = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnGuardar2 = new javax.swing.JButton();
        btnMostrar2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(400, 60));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre Completo.");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 66, -1, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Cédula.");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 66, -1, -1));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Dirección de domicilio.");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 146, -1, -1));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Teléfono 2.");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(489, 106, -1, -1));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("e-mail.");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 146, -1, -1));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Teléfono 1.");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 103, -1, -1));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Fecha de Nacimiento.");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 186, 140, -1));
        getContentPane().add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 250, -1));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 250, -1));
        getContentPane().add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 250, -1));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 63, 250, -1));
        getContentPane().add(txtTelefono2, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 103, 250, -1));
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 143, 250, -1));

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Cédula", "Nombre", "Teléfono 1", "Teléfono 2", "e-mail", "Dirección", "Fecha de Nacimiento"
            }
        ));
        jScrollPane1.setViewportView(tablaDatos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 548, 211));
        getContentPane().add(txtTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 250, -1));

        btnInsertar.setText("Insertar");
        btnInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarActionPerformed(evt);
            }
        });
        btnInsertar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnInsertarKeyReleased(evt);
            }
        });
        getContentPane().add(btnInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 200, 80, -1));

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, 80, -1));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 400, 80, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 320, 80, -1));
        getContentPane().add(txtEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 290, 220, -1));
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 370, 220, -1));

        btnGuardar.setText("Guardar XML");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, -1));

        btnMostrarXML.setText("Mostrar XML");
        btnMostrarXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarXMLActionPerformed(evt);
            }
        });
        getContentPane().add(btnMostrarXML, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, -1, -1));

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 440, -1, -1));

        jLabel9.setFont(new java.awt.Font("Rockwell", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("FIRMA PWC");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, -1));

        btnGuardar2.setText("Guardar JSON");
        btnGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, -1, -1));

        btnMostrar2.setText("Mostrar JSON");
        btnMostrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrar2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnMostrar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 440, -1, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/thumb-1920-16981.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 480));

        btnGuardar1.setText("Guardar XML");
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarActionPerformed
        IngresarDatos();
        btnGuardar.setEnabled(true);
     
    }//GEN-LAST:event_btnInsertarActionPerformed

    private void btnInsertarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnInsertarKeyReleased
        
    }//GEN-LAST:event_btnInsertarKeyReleased

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        ModificarDatos();
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        BuscarDatos();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        EliminarDatos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        GuardarXML();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnMostrarXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarXMLActionPerformed
        FormXML Fxml = new FormXML();
        Fxml.setVisible(true);
    }//GEN-LAST:event_btnMostrarXMLActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
             char c = evt.getKeyChar();
            if(Character.isDigit(c)) {
              getToolkit().beep();
              evt.consume();
            }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar2ActionPerformed
        GuardarJSON2();
    }//GEN-LAST:event_btnGuardar2ActionPerformed

    private void btnMostrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrar2ActionPerformed
        MostrarJSON();
    }//GEN-LAST:event_btnMostrar2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnMostrar2;
    private javax.swing.JButton btnMostrarXML;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDatos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEliminar;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTelefono2;
    // End of variables declaration//GEN-END:variables
}
