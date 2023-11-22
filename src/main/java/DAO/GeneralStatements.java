/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;

/**
 * Se encarga de generar statements generales con datos introducidos.
 * @author Ryuka
 */
public class GeneralStatements {

    /**
     * Genera un INSERT autogenerado a partir de los datos ingresados.
     *
     * @param tabla String con la tabla que en la que se van a insertar los
     * datos.
     * @param campos ArrayList con los campos de la tabla.
     * @return Un String con la consulta generada.
     */
    public String generalINSERT(String tabla, ArrayList<String> campos) {
        StringBuilder sb = new StringBuilder();
        int numCampos = campos.size();
        sb.append("INSERT INTO `").append(tabla).append("` (");
        for (int i = 0; i < numCampos; i++) {
            if (i == numCampos -1) {
                sb.append(campos.get(i));
            } else {
                sb.append(campos.get(i)).append(",");
            }
        }
        sb.append(") VALUES (");
        for (int i = 0; i < numCampos; i++) {
            if (i == numCampos -1) {
                sb.append("?");
            } else {
                sb.append("?").append(",");
            }
        }
        sb.append(");");
        return sb.toString();
    }

    /**
     * Genera un SELECT general autogenerado a partir de los datos ingresados.
     *
     * @param tabla String con la tabla que en la que se van a leer los datos.
     * @param campos ArrayList con los campos de la tabla.
     * @return Un String con la consulta generada.
     */
    public String generalSELECT(String tabla, ArrayList<String> campos) {
        StringBuilder sb = new StringBuilder();
        int numCampos = 0;
        if (!campos.isEmpty()) {
            numCampos = campos.size();
        }
        sb.append("SELECT");
        if (numCampos == 0) {
            sb.append(" * ");
        } else {
            sb.append(" ");
            for (int i = 0; i < numCampos; i++) {
                if (i == numCampos -1) {
                    sb.append(campos.get(i)).append(" ");
                } else {
                    sb.append(campos.get(i)).append(",");
                }
            }
        }
        sb.append("FROM `").append(tabla).append("`;");
        return sb.toString();
    }
    
    /**
     * Genera un SELECT general autogenerado a partir de los datos ingresados.
     *
     * @param tabla String con la tabla que en la que se van a leer los datos.
     * @param campos ArrayList con los campos de la tabla.
     * @param id La posicion en el ArrayList del campo que se compara.
     * @return Un String con la consulta generada.
     */
    public String selectiveSELECT(String tabla, ArrayList<String> campos, int id) {
        StringBuilder sb = new StringBuilder();
        int numCampos = campos.size();
        sb.append("SELECT");
        if (numCampos == 0) {
            sb.append(" * ");
        } else {
            sb.append(" ");
            for (int i = 0; i < numCampos; i++) {
                if (i == numCampos -1) {
                    sb.append(campos.get(i)).append(" ");
                } else {
                    sb.append(campos.get(i)).append(",");
                }
            }
        }
        sb.append("FROM `").append(tabla).append("` ");
        sb.append("WHERE ").append(campos.get(id)).append(" = ?;");
        return sb.toString();
    }

    /**
     * Genera un UPDATE autogenerado a partir de los datos ingresados.
     *
     * @param tabla String con la tabla que en la que se van a actualizar los
     * datos.
     * @param campos ArrayList con los campos de la tabla.
     * @param id La posicion en el ArrayList del campo que se compara.
     * @return Un String con la consulta generada.
     */
    public String generalUPDATE(String tabla, ArrayList<String> campos, int id) {
        StringBuilder sb = new StringBuilder();
        int numCampos = campos.size();
        sb.append("UPDATE `").append(tabla).append("` ");
        sb.append("SET ");
        for (int i = 1; i < numCampos; i++) {
            if (i == numCampos -1) {
                sb.append(campos.get(i)).append("=? ");
            } else {
                sb.append(campos.get(i)).append("=?,");
            }
        }
        sb.append("WHERE ").append(campos.get(id)).append("=?;");
        return sb.toString();
    }

    /**
     * Genera un DELETE autogenerado a partir de los datos ingresados.
     *
     * @param tabla String con la tabla que en la que se van a eliminar los
     * datos.
     * @param campos ArrayList con los campos de la tabla.
     * @return Un String con la consulta generada.
     */
    public String generalDELETE(String tabla, ArrayList<String> campos) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ").append("FROM `").append(tabla).append("` ");
        sb.append("WHERE ").append(campos.get(0)).append(" = ?;");
        return sb.toString();
    }
}
