package Clases;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Kongo
 */
public class Imagen {

    private File archivo;
    private int alto;
    private int ancho;

    public Imagen(File archivo) throws IOException {
        getSize();
        this.archivo = archivo;
    }

    public File getArchivo() {
        return archivo;
    }

    public int getAlto() {
        return alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    private void getSize() throws IOException {
        BufferedImage imagen = ImageIO.read(archivo);
        alto = imagen.getHeight();
        ancho = imagen.getWidth();
        imagen.flush();
    }
    
    /**
     * Metodo para reescalar una imagen por BufferedImage.
     * @param originalImage La imagen original.
     * @param targetWidth La altura objetivo.
     * @param targetHeight El ancho objetivo.
     * @return Regresa la imagen con las dimenciones deseadas.
     * @throws IOException 
     */
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    /**
     * Metodo que devuelve una imagen reescalada como BufferedImage.
     * @param valorAltura double con el porcentaje de rescalado de la altura.
     * @param valorAncho double con el porcentaje de rescalado de la ancho.
     * @return Regresa la imagen con las dimenciones deseadas.
     * @throws IOException
     */
    public BufferedImage resizeImage(double valorAltura, double valorAncho) throws IOException {
        double porcentajeAltura = valorAltura / 100;
        double porcentajeAncho = valorAncho / 100;
        BufferedImage imagen = null;
        if (archivo.isFile() && (archivo.getName().endsWith("png")) || archivo.getName().endsWith("jpg") || archivo.getName().endsWith("jpeg") || archivo.getName().endsWith(".gif")) {
            imagen = ImageIO.read(archivo);
            if (imagen != null) {
                double reescaladoAltura = (alto * porcentajeAltura);
                double reescaladoAncho = (ancho * porcentajeAncho);
                imagen.flush();
                imagen = resizeImage(ImageIO.read(archivo), (int) reescaladoAncho, (int) reescaladoAltura);
            }
        }
        return imagen;
    }
    
    /**
     * Metodo que reggresa una imagen reescalada como BufferedImage.
     * @param valor double con el porcentaje de rescalado.
     * @return Regresa la imagen con las dimenciones deseadas.
     * @throws IOException 
     */
    public BufferedImage resizeImageByPercentage(double valor) throws IOException {
        double porcentaje = valor / 100;
        BufferedImage imagen = null;
        if (archivo.isFile() && (archivo.getName().endsWith("png")) || archivo.getName().endsWith("jpg") || archivo.getName().endsWith("jpeg") || archivo.getName().endsWith(".gif")) {
            imagen = ImageIO.read(archivo);
            if (imagen != null) {
                double reescaladoAltura = (alto * porcentaje);
                double reescaladoAncho = (ancho * porcentaje);
                imagen.flush();
                imagen = resizeImage(ImageIO.read(archivo), (int) reescaladoAncho, (int) reescaladoAltura);
            }
        }
        return imagen;
    }
}
