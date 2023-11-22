package Clases;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Manga {
    private int ID;
    private String Nombre;
    private String Genero;
    private Date Lanzamiento;
    private String iconPath;

    public Manga(int ID, String Nombre, String Genero, Date Lanzamiento, String iconPath) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Genero = Genero;
        this.Lanzamiento = Lanzamiento;
        this.iconPath = iconPath;
    }

}

