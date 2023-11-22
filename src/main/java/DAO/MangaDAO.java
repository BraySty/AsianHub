package DAO;

import Clases.Manga;
import Interfaces.CRUD;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ryuka
 */
public class MangaDAO implements CRUD {

    private GeneralStatements gs = new GeneralStatements();
    private String url;
    private String usuario;
    private String password;
    private final String tabla = "Manga";
    private ArrayList<String> columna;

    public MangaDAO() {
        columna = getColumnas();
    }

    public MangaDAO(String url, String ususario, String password) {
        this.url = url;
        this.usuario = ususario;
        this.password = password;
        columna = getColumnas();
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public boolean insert(Object objeto) {
        Manga manga = (Manga) objeto;
        boolean resultadoOperacion = false;
        int lineasAfectadas;
        try ( Connection conexion = getConnection()) {
            if (conexion != null) {
                if (select(manga) == null) {
                    PreparedStatement ps = conexion.prepareStatement(gs.generalINSERT(tabla, columna));
                    ps.setInt(1, manga.getID());
                    ps.setString(2, manga.getNombre());
                    ps.setString(3, manga.getGenero());
                    ps.setDate(4, manga.getLanzamiento());
                    ps.setString(5, manga.getIconPath());
                    lineasAfectadas = ps.executeUpdate();
                    if (lineasAfectadas > 0) {
                        resultadoOperacion = true;
                    }
                }
                conexion.close();
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return resultadoOperacion;
    }

    @Override
    public ArrayList<?> selectAll() {
        ArrayList<Manga> mangas = new ArrayList<>();
        try ( Connection conexion = getConnection()) {
            if (conexion != null) {
                Statement statement = conexion.createStatement();
                ResultSet rs = statement.executeQuery(gs.generalSELECT(tabla, columna));
                while (rs.next()) {
                    int id = rs.getInt(columna.get(0));
                    String nombre = rs.getString(columna.get(1));
                    String Genero = rs.getString(columna.get(2));
                    Date Lanzamiento = rs.getDate(columna.get(3));
                    String iconPath = rs.getString(columna.get(4));
                    mangas.add(new Manga(id, nombre, Genero, Lanzamiento, iconPath));
                }
                conexion.close();
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return mangas;
    }

    @Override
    public Object select(Object objeto) {
        Manga manga = (Manga) objeto;
        Manga nuevoManga = null;
        try ( Connection conexion = getConnection()) {
            if (conexion != null) {
                PreparedStatement ps = conexion.prepareStatement(gs.selectiveSELECT(tabla, columna, 0));
                ps.setInt(1, manga.getID());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(columna.get(0));
                    String nombre = rs.getString(columna.get(1));
                    String Genero = rs.getString(columna.get(2));
                    Date Lanzamiento = rs.getDate(columna.get(3));
                    String iconPath = rs.getString(columna.get(4));
                    nuevoManga = new Manga(id, nombre, Genero, Lanzamiento, iconPath);
                }
                conexion.close();
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return nuevoManga;
    }

    @Override
    public boolean update(Object objeto) {
        Manga manga = (Manga) objeto;
        boolean resultadoOperacion = false;
        int lineasAfectadas;
        try ( Connection conexion = getConnection()) {
            if (conexion != null) {
                if (select(manga) != null) {
                    int id = manga.getID();
                    String nombre = manga.getNombre();
                    String Genero = manga.getGenero();
                    Long Lanzamiento = manga.getLanzamiento().getTime();
                    String iconPath = manga.getIconPath();
                    // Debido a la forma en que PreparedStatement crea los comandos, los update no son admitidos en sqlite
                    // para que los campos sean admitidos nesesita esta entre "" pero PreparedStatement no los encierra.
                    // Tambiem, para que el programa no errores con la fecha, se deben ingresar y recoger como Long.
                    String exec = "UPDATE `" + tabla + "` SET Nombre=\"" + nombre + "\",Genero=\"" + Genero + "\",Lanzamiento=\"" + Lanzamiento + "\",iconPath=\"" + iconPath + "\" WHERE ID=\"" + id + "\";";
                    PreparedStatement ps = conexion.prepareStatement(exec);
                    lineasAfectadas = ps.executeUpdate();
                    if (lineasAfectadas > 0) {
                        resultadoOperacion = true;
                    }
                }
                conexion.close();
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return resultadoOperacion;
    }

    @Override
    public boolean delete(Object objeto) {
        Manga manga = (Manga) objeto;
        boolean resultadoOperacion = false;
        int lineasAfectadas;
        try ( Connection conexion = getConnection()) {
            if (conexion != null) {
                if (select(manga) != null) {
                    PreparedStatement ps = conexion.prepareStatement(gs.generalDELETE(tabla, columna));
                    ps.setInt(1, manga.getID());
                    lineasAfectadas = ps.executeUpdate();
                    if (lineasAfectadas > 0) {
                        resultadoOperacion = true;
                        System.out.println("Manga eliminado.");
                    }
                }
                conexion.close();
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return resultadoOperacion;
    }

    /**
     * Consigue todos los campos de una tabla y los devuelve en un
     * ArrayList<String>
     *
     * @param tabla String con el nombre de la tabla.
     * @return ArrayList<String> con los campos de la tabla. null si no se
     * encuentra.
     */
    private ArrayList<String> getColumnas() {
        ArrayList<String> campos = new ArrayList<>();
        try ( Connection conexion = getConnection()) {
            if (conexion != null) {
                DatabaseMetaData databaseMetaData = conexion.getMetaData();
                ResultSet rs2 = databaseMetaData.getColumns(null, null, tabla, null);
                while (rs2.next()) {
                    campos.add(rs2.getString("COLUMN_NAME"));
                }
                conexion.close();
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return campos;
    }

    /**
     * Realiza la coneccion con la base de datos.
     *
     * @return Regresa la conexion con la BD. null si no se encuentra.
     */
    private Connection getConnection() {
        Connection Connection = null;
        try {
            Connection = DriverManager.getConnection(this.url, this.usuario, this.password);
            return Connection;
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return Connection;
    }

    /**
     * Imprime un mensaje de error.
     *
     * @param e El mensaje de error.
     */
    private void printSQLException(SQLException ex) {
        for (Throwable error : ex) {
            if (error instanceof SQLException sQLException) {
                System.err.println("SQLState: " + sQLException.getSQLState());
                System.err.println("Codigo de error: " + sQLException.getErrorCode());
                System.err.println("Mensaje: " + error.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Causa: " + t);
                    t = t.getCause();
                }
                error.printStackTrace(System.err);
            }
        }
    }

}
