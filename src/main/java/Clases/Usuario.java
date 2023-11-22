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

    public Usuario(int ID, String user, String password) {
        this.ID = ID;
        this.user = user;
        this.password = password;
    }
    
}
