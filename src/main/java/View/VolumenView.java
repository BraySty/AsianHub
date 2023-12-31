/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import Clases.Imagen;
import Clases.Manga;
import Clases.Volumen;
import DAO.VolumenDAO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ryuka
 */
public class VolumenView extends javax.swing.JDialog {

    Manga manga;
    int imageHeight = 150;
    ArrayList<Volumen> volumen;
    private String[] titulo = new String[]{"Imagen", "Nombre", "Capitulos"};

    
    /**
     * Creates new form Volumen
     */
    public VolumenView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void setManga(Manga manga) {
        this.manga = manga;
    }
    
    private void loadArrayFromDatabase() {
        String base = System.getProperty("user.dir") + "\\SQL\\AsianHub.db";
        String url = "jdbc:sqlite:" + base;
        String usuario = "";
        String password = "";
        VolumenDAO vdao = new VolumenDAO(url, usuario, password);
        volumen = (ArrayList<Volumen>) vdao.selectAllWhere(manga.getID());
    }

    /**
     * Inicializa las tablas al empezar la aplicacion.
     */
    public void inicializarTabla() {
        // Crea la nueva tabla.
        DefaultTableModel dtm = new DefaultTableModel() {

            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0 -> {
                        return Icon.class;
                    }
                    default -> {
                        if (getRowCount() > 0) {
                            Object value = getValueAt(0, column);
                            if (value != null) {
                                return getValueAt(0, column).getClass();
                            }
                        }
                        return super.getColumnClass(column);
                    }
                }

            }

            // Permite seleccionar que celdas son editables.
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == -1;
            }

        };
        // Añade las columnas a la tabla.
        dtm.setColumnIdentifiers(titulo);
        jTable1.setModel(dtm);
        loadArrayFromDatabase();
        cargarDatos();
        jTable1.setRowHeight(imageHeight + 10);
    }

    /**
     * Carga los datos en la tabla principal.
     */
    private void cargarDatos() {
        for (Volumen volumen : volumen) {
            // Recogida de datos a variables.
            String nombre = volumen.getNombre();
            int capitulos = volumen.getCapitulos();
            String imgPath = System.getProperty("user.dir") + volumen.getIconPath();
            String noIMG = System.getProperty("user.dir") + "\\src\\main\\resources\\SQL\\noIMG.jpg";
            ImageIcon imageIcon = null;
            try {
                Imagen imagen = null;
                if (imgPath != null) {
                    File file = new File(imgPath);
                    if (file.exists() && file.isFile() && (file.getName().endsWith("png")) || file.getName().endsWith("jpg") || file.getName().endsWith("jpeg") || file.getName().endsWith(".gif")) {
                        imagen = new Imagen(file);
                        imageIcon = new ImageIcon(imagen.loadImageByPixelHeightSize(imageHeight));
                    } else {
                        imagen = new Imagen(new File(noIMG));
                        imageIcon = new ImageIcon(imagen.loadImageByPixelHeightSize(imageHeight));
                    }
                } else {
                    imagen = new Imagen(new File(noIMG));
                    imageIcon = new ImageIcon(imagen.loadImageByPixelHeightSize(imageHeight));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // Empaquetado de variables.
            Object[] rowData = new Object[]{
                imageIcon,
                nombre,
                capitulos
            };
            addData(rowData);

        }
    }

    /**
     * Se encarga de escribir datos a la tabla general.
     *
     * @param rowData
     */
    public void addData(Object[] rowData) {
        //Pasa la informacion a la pantalla principal.
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.addRow(rowData);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Titulo", "Tipo", "Capitulos", "Volumenes"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int posX = jTable1.getSelectedRow();
        int posY = jTable1.getSelectedColumn();
        //System.out.println(jTable1.getValueAt(posX, posY));
        Galeria vv = new Galeria();
        vv.setVolumen(volumen.get(posX));
        vv.setVisible(true);
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(VolumenView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VolumenView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VolumenView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VolumenView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VolumenView dialog = new VolumenView(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
