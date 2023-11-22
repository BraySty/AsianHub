package Clases;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Pagina {
    
    private int ID;
    private int pagina;
    private String iconPath;
    private int volumenID;

    public Pagina(int ID, int pagina, String iconPath, int volumenID) {
        this.ID = ID;
        this.pagina = pagina;
        this.iconPath = iconPath;
        this.volumenID = volumenID;
    }
    
}
