
package Formularios;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FormJSON extends javax.swing.JFrame 
{

    DefaultListModel modeloLista = new DefaultListModel();
    
    public FormJSON()
    {
        initComponents();
        try {
            //leerJSON();
            C();
        } catch (IOException ex) {
            Logger.getLogger(FormJSON.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(FormJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void C() throws IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        DefaultListModel Lista = new DefaultListModel();
        
    
        Object obj = parser.parse(new FileReader("prueba.json"));

                JSONObject jsonObject = (JSONObject) obj;

                JSONArray tag = (JSONArray) jsonObject.get("delegate");
                Iterator iterator = tag.iterator();

                while (iterator.hasNext()) 
                {
                    Lista.addElement(iterator.next());
                }
                LISTA.setModel(Lista);
    }
    
    
   /* public void leerJSON()
    {
        JSONParser parser = new JSONParser();
        
        
        try 
        {
            
            Object obj = parser.parse(new FileReader("Prueba.json"));                      
            JSONObject jsonObject = (JSONObject) obj;

            System.out.println(obj);
            
            JSONObject innerObject = (JSONObject) jsonObject.get("delegate");
            System.out.println(innerObject.toJSONString());
            
            String cedula = (String) jsonObject.get("Cedula");
            System.out.println(cedula);
            
            String nombre = (String) jsonObject.get("Nombre");
            System.out.println(nombre);
			
            String telefono_1 = (String) jsonObject.get("Telefono_1");
            System.out.println(telefono_1);
            
            String telefono_2 = (String) jsonObject.get("Telefono_2");
            System.out.println(telefono_2);
            
            String email = (String) jsonObject.get("Email");
            System.out.println(email);
            
            String direccion = (String) jsonObject.get("Direccion");
            System.out.println(direccion);
            
            String fecha = (String) jsonObject.get("Fecha_Nacimiento");
            System.out.println(fecha);
            
            
            }
        
        catch (FileNotFoundException e)
        {
            System.out.println("Error Fatal 1");
        } 
        catch (IOException e) 
        {
            System.out.println("Error Fatal 2");
        } 
        catch (ParseException e) 
        {
            System.out.println("Error Fatal 3");
        }
    }
    */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        LISTA = new javax.swing.JList<>();
        btnSalir = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setUndecorated(true);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(LISTA);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 640, 210));

        btnSalir.setText("Regresar");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        lblFondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/thumb-1920-16981.png"))); // NOI18N
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(FormJSON.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormJSON.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormJSON.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormJSON.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormJSON().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> LISTA;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFondo;
    // End of variables declaration//GEN-END:variables
}
