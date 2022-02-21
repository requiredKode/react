
package Formularios;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;





public class FormXML extends javax.swing.JFrame 
{
    
    File fXmlFile = new File("Empleados_ACME.xml");
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    
    DefaultListModel modeloLista = new DefaultListModel();

    public FormXML() 
    {
            initComponents();            
            
            try {
            leerXML();
            } catch (ParserConfigurationException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al mostrar informacion del xml" + ex);
            }
            
            
    }
       
    
    public void leerXML() throws ParserConfigurationException
    {
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = null;
    
    modeloLista.addElement("INFORMACION");
     modeloLista.addElement("****************************************");        
        try {
            doc = dBuilder.parse(fXmlFile);
        } catch (SAXException ex) {
            Logger.getLogger(FormXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    doc.getDocumentElement().normalize();

    NodeList nList = doc.getElementsByTagName("PWC");

    

        int temp = 0;
        Node nNode = nList.item(temp);
       
            Element eElement = (Element) nNode;
            
            NodeList contents = doc.getElementsByTagName("DATOS");
            int A = contents.getLength();

            for (int i = 0; i < A; i++) {
            modeloLista.addElement(eElement.getElementsByTagName("Cedula").item(i).getTextContent());
            modeloLista.addElement(eElement.getElementsByTagName("Nombre").item(i).getTextContent());
            modeloLista.addElement(eElement.getElementsByTagName("Telefono_1").item(i).getTextContent());
            modeloLista.addElement(eElement.getElementsByTagName("Telefono_2").item(i).getTextContent());
            modeloLista.addElement(eElement.getElementsByTagName("Email").item(i).getTextContent());
            modeloLista.addElement(eElement.getElementsByTagName("Direccion").item(i).getTextContent());
            modeloLista.addElement(eElement.getElementsByTagName("Fecha_Nacimiento").item(i).getTextContent());

            modeloLista.addElement("****************************************");
            }

    listaXML.setModel(modeloLista);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        listaXML = new javax.swing.JList<>();
        btnSalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(50, 75));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setViewportView(listaXML);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 23, 244, 349));

        btnSalir.setText("REGRESAR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 385, -1, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/thumb-1920-16981.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 350, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
         this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    public static void main(String args[]) 
    {
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
            java.util.logging.Logger.getLogger(FormXML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormXML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormXML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormXML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new FormXML().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaXML;
    // End of variables declaration//GEN-END:variables
}
