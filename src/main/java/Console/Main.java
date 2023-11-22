package Console;

import Clases.Manga;
import Clases.Volumen;
import DAO.MangaDAO;
import DAO.VolumenDAO;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Date;
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

        try {
            URL resource = Main.class.getResource("/SQL/AsianHub.db");
            String url = "jdbc:sqlite:" + Paths.get(resource.toURI()).toFile();
            String usuario = "";
            String password = "";
            
            mangaDAO(url, usuario, password);
            volumenDAO(url, usuario, password);
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void volumenDAO(String url, String usuario, String password) {
        int ID = 0;
        String nombre = "BLAME!";
        String iconPath = "\\Archivos\\BLAME!\\BLAME! Volume 1.jpg";
        int mangaID = 0;
        int captiputlos = 6;

        VolumenDAO vdao = new VolumenDAO(url, usuario, password);

        //vdao.insert(new Volumen(ID, nombre, iconPath, mangaID, captiputlos));

        for (Object object : vdao.selectAll()) {
            System.out.println(object.toString());
        }
    }

    private static void mangaDAO(String url, String usuario, String password) {
        try {
            String baseURL = System.getProperty("user.dir");
            
            int ID = 1;
            String nombre = "All You Need Is Kill";
            String genero = "Acción";
            String fecha = "18-12-2004";
            Date lanzamiento = utilDateToSQLDate(fecha);
            String iconPath = "\\Archivos\\All You Need Is Kill\\icon.jpg";

            MangaDAO mdao = new MangaDAO(url, usuario, password);

            mdao.update(new Manga(ID, nombre, genero, lanzamiento, iconPath));
            File file = new File(baseURL + "\\Archivos\\" + nombre);
            System.out.println(file.getAbsolutePath());
            file.mkdirs();
            
            for (Object object : mdao.selectAll()) {
                System.out.println(object.toString());
            }
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static java.sql.Date utilDateToSQLDate(String fecha) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
        java.util.Date date = sdf1.parse(fecha);
        return new java.sql.Date(date.getTime());
    }

}
