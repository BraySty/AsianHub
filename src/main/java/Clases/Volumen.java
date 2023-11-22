package Clases;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Volumen {
    
    private int ID;
    private String nombre;
    private String iconPath;
    private int manggaID;
    private int capitulos;

    public Volumen(int ID, String nombre, String iconPath, int manggaID, int capitulos) {
        this.ID = ID;
        this.nombre = nombre;
        this.iconPath = iconPath;
        this.manggaID = manggaID;
        this.capitulos = capitulos;
    }
    
}
