package Console;

import Clases.Manga;
import Clases.Pagina;
import Clases.Usuario;
import Clases.Volumen;
import DAO.MangaDAO;
import DAO.PaginaDAO;
import DAO.UsuarioDAO;
import DAO.VolumenDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kongo
 */
public class Main {

    public static void main(String[] args) {

        String base = System.getProperty("user.dir") + "\\SQL\\AsianHub.db";
        String url = "jdbc:sqlite:" + base;
        String usuario = "";
        String password = "";
        System.out.println(url);
        usuarioDAO(url, usuario, password);
        mangaDAO(url, usuario, password);
        volumenDAO(url, usuario, password);
        paginaDAO(url, usuario, password);

    }
    
    private static void usuarioDAO(String url, String usuario, String password) {
        int ID = 0;
        String userName = "usuario";
        String usePassword = "1234";
        int admin = 1;

        UsuarioDAO udao = new UsuarioDAO(url, usuario, password);

        udao.insert(new Usuario(ID, userName, usePassword, admin));
        for (Object object : udao.selectAll()) {
            System.out.println(object.toString());
        }
    }

    private static void paginaDAO(String url, String usuario, String password) {

        PaginaDAO pdao = new PaginaDAO(url, usuario, password);

        pdao.insert(new Pagina(0,0,"\\Archivos\\BLAME!\\Volumen 01\\blame_000a.jpg",0));
        pdao.insert(new Pagina(1,1,"\\Archivos\\BLAME!\\Volumen 01\\blame_000b.jpg",0));
        pdao.insert(new Pagina(2,2,"\\Archivos\\BLAME!\\Volumen 01\\blame_000c.jpg",0));
        pdao.insert(new Pagina(3,3,"\\Archivos\\BLAME!\\Volumen 01\\blame_001.jpg",0));
        pdao.insert(new Pagina(4,4,"\\Archivos\\BLAME!\\Volumen 01\\blame_002.jpg",0));
        pdao.insert(new Pagina(5,5,"\\Archivos\\BLAME!\\Volumen 01\\blame_003.jpg",0));
        pdao.insert(new Pagina(6,6,"\\Archivos\\BLAME!\\Volumen 01\\blame_004.jpg",0));
        pdao.insert(new Pagina(7,7,"\\Archivos\\BLAME!\\Volumen 01\\blame_005.jpg",0));
        pdao.insert(new Pagina(8,8,"\\Archivos\\BLAME!\\Volumen 01\\blame_006.jpg",0));
        pdao.insert(new Pagina(9,9,"\\Archivos\\BLAME!\\Volumen 01\\blame_007.jpg",0));
        pdao.insert(new Pagina(10,10,"\\Archivos\\BLAME!\\Volumen 01\\blame_008.jpg",0));
        pdao.insert(new Pagina(11,11,"\\Archivos\\BLAME!\\Volumen 01\\blame_009.jpg",0));
        pdao.insert(new Pagina(12,12,"\\Archivos\\BLAME!\\Volumen 01\\blame_0010.jpg",0));
        for (Object object : pdao.selectAll()) {
            System.out.println(object.toString());
        }
    }

    private static void volumenDAO(String url, String usuario, String password) {
        int ID = 2;
        String nombre = "BLAME!";
        String iconPath = "\\Archivos\\BLAME!\\BLAME! Volume 3.jpg";
        int mangaID = 0;
        int captiputlos = 6;

        VolumenDAO vdao = new VolumenDAO(url, usuario, password);

        vdao.insert(new Volumen(0, "BLAME!", "\\Archivos\\BLAME!\\BLAME! Volume 1.jpg", 0, 7));
        vdao.insert(new Volumen(1, "BLAME!", "\\Archivos\\BLAME!\\BLAME! Volume 2.jpg", 0, 5));
        vdao.insert(new Volumen(2, "BLAME!", "\\Archivos\\BLAME!\\BLAME! Volume 3.jpg", 0, 6));
        vdao.insert(new Volumen(3, "BLAME!", "\\Archivos\\BLAME!\\BLAME! Volume 4.jpg", 0, 5));
        vdao.insert(new Volumen(4, "BLAME!", "\\Archivos\\BLAME!\\BLAME! Volume 5.jpg", 0, 5));
        vdao.insert(new Volumen(5, "BLAME!", "\\Archivos\\BLAME!\\BLAME! Volume 6.jpg", 0, 6));
        
        vdao.insert(new Volumen(6, "All You Need Is Kill", "\\Archivos\\All You Need Is Kill\\All You Need Is Kill Volume 1.jpg", 1, 10));
        vdao.insert(new Volumen(7, "All You Need Is Kill", "\\Archivos\\All You Need Is Kill\\All You Need Is Kill Volume 2.jpg", 1, 7));
        
        vdao.insert(new Volumen(8, "Devilman", "\\Archivos\\Devilman\\Devilman Volume 1.jpg", 2, 6));
        vdao.insert(new Volumen(9, "Devilman", "\\Archivos\\Devilman\\Devilman Volume 2.jpg", 2, 5));
        vdao.insert(new Volumen(10, "Devilman", "\\Archivos\\Devilman\\Devilman Volume 3.jpg", 2, 4));
        vdao.insert(new Volumen(11, "Devilman", "\\Archivos\\Devilman\\Devilman Volume 4.jpg", 2, 4));
        vdao.insert(new Volumen(12, "Devilman", "\\Archivos\\Devilman\\Devilman Volume 5.jpg", 2, 5));
        for (Object object : vdao.selectAll()) {
            System.out.println(object.toString());
        }
    }

    private static void mangaDAO(String url, String usuario, String password) {
        try {
            String baseURL = System.getProperty("user.dir");
            
            MangaDAO mdao = new MangaDAO(url, usuario, password);

            mdao.insert(new Manga(0, "BLAME!", "Acción", utilDateToSQLDate("01-6-1996"), "\\Archivos\\BLAME!\\folder.jpg"));
            mdao.insert(new Manga(1, "All You Need Is Kill", "Acción", utilDateToSQLDate("18-12-2004"), "\\Archivos\\All You Need Is Kill\\All You Need Is Kill.jpeg"));
            mdao.insert(new Manga(2, "Devilman", "Acción", utilDateToSQLDate("11-5-1972"), "\\Archivos\\Devilman\\cover.png"));
            mdao.insert(new Manga(3, "Sidonia no Kishi", "Acción", utilDateToSQLDate("25-4-2009"), "\\Archivos\\Sidonia no Kishi\\cover.jpg"));
            mdao.insert(new Manga(4, "Taihou to Stamp", "Comedia", utilDateToSQLDate("22-1-2011"), "\\Archivos\\Taihou to Stamp\\cover.jpg"));
            
            for (Object object : mdao.selectAll()) {
                System.out.println(object.toString());
            }
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static java.sql.Date utilDateToSQLDate(String fecha) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(fecha);
        System.out.println(date.toString());
        return new java.sql.Date(date.getTime());
    }

}