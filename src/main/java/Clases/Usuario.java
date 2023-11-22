/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Usuario {
    
    private int ID;
    private String user;
    private String password;
    private int admind;

    /**
     * Constructor parametrizado
     * @param ID
     * @param user
     * @param password
     * @param admind 0 es usuario normal, 1 es administrador.
     */
    public Usuario(int ID,String user, String password, int admind) {
        this.ID = ID;
        this.user = user;
        this.password = password;
        this.admind = admind;
    }
    
}
