/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reformas;

import java.io.Serializable;

/**
 *
 * @author dam
 */
public class Cliente implements Serializable {
 
//nombre, apellidos y teléfono.
 private String nombre;
 private String apellidos;
 private String telefono;
 private boolean vip;
 private ListaPresupuestos misPresupuestos;

    public Cliente() {
    }
 

    public Cliente(String nombre, String apellidos, String telefono, boolean vip) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.vip = vip;
        misPresupuestos = new ListaPresupuestos();
    }
    
    public void setMisPresupuestos(ListaPresupuestos misPresupuestos) {
        this.misPresupuestos= misPresupuestos;
    }
    
    public ListaPresupuestos getMisPresupuestos() {
        return misPresupuestos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
 
 
    
    
}
