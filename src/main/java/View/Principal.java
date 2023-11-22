package View;

import Clases.Imagen;
import Clases.Manga;
import Console.Main;
import DAO.MangaDAO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ale12
 */
public class Principal extends javax.swing.JFrame {

    int imageHeight = 150;
    ArrayList<Manga> Manga;
    private String[] titulo = new String[]{"Imagen", "Nombre", "Genero", "Fecha de lanzamiento"};

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setVisible(true);
        inicializarTabla();
    }

    private void loadArrayFromDatabase() {
        try {
            URL resource = Main.class.getResource("/SQL/AsianHub.db");
            String url = "jdbc:sqlite:" + Paths.get(resource.toURI()).toFile();
            String usuario = "";
            String password = "";
            MangaDAO mdao = new MangaDAO(url, usuario, password);
            Manga = (ArrayList<Manga>) mdao.selectAll();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Inicializa las tablas al empezar la aplicacion.
     */
    private void inicializarTabla() {
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
        for (Manga manga : Manga) {
            // Recogida de datos a variables.
            String nombre = manga.getNombre();
            String genero = manga.getGenero();
            Date lanzamiento = manga.getLanzamiento();
            String imgPath = System.getProperty("user.dir") + manga.getIconPath();
            String noIMG = System.getProperty("user.dir") + "\\src\\main\\resources\\SQL\\noIMG.jpg";
            ImageIcon imageIcon = null;
            try {
                Imagen imagen = null;
                if (imgPath != null) {
                    File file = new File(imgPath);
                    System.out.println(file.getPath().substring(System.getProperty("user.dir").length()));
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
                genero,
                lanzamiento
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        changepassword = new javax.swing.JMenuItem();
        settinguser = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0))
        );

        jMenu1.setText("Ajustes");

        changepassword.setText("Cambiar contraseña");
        changepassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changepasswordMouseClicked(evt);
            }
        });
        changepassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changepasswordActionPerformed(evt);
            }
        });
        jMenu1.add(changepassword);

        settinguser.setText("Ajustes de cuenta");
        settinguser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settinguserMouseClicked(evt);
            }
        });
        settinguser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settinguserActionPerformed(evt);
            }
        });
        jMenu1.add(settinguser);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Admistracion");

        jMenuItem2.setText("Añadir mangas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Eliminar mangas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void settinguserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settinguserMouseClicked

    }//GEN-LAST:event_settinguserMouseClicked

    private void changepasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changepasswordMouseClicked

    }//GEN-LAST:event_changepasswordMouseClicked

    private void changepasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changepasswordActionPerformed
        passwordchange passwordventana = new passwordchange(this, false);
        passwordventana.setVisible(true);
        System.out.println("00000000002");
    }//GEN-LAST:event_changepasswordActionPerformed

    private void settinguserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settinguserActionPerformed
        usersetting Usersetting = new usersetting(this, true);
    }//GEN-LAST:event_settinguserActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        addmanga Addmanga = new addmanga(this, true);

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        deletemanga Addmanga = new deletemanga(this, true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem changepassword;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem settinguser;
    // End of variables declaration//GEN-END:variables
}
