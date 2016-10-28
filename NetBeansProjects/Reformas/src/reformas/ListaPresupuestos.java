/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reformas;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaPresupuestos implements Serializable {
   private ArrayList<Presupuesto> listaPresupuesto;

    public void alta(Presupuesto p) {
        listaPresupuesto.add(p);

    }

    public ArrayList<Presupuesto> getListaPresupuesto() {
        return listaPresupuesto;
    }

    public void setListaPresupuesto(ArrayList<Presupuesto> listaPresupuesto) {
        this.listaPresupuesto = listaPresupuesto;
    }

    public ListaPresupuestos() {
        listaPresupuesto = new ArrayList<>(); 
    }
    
  


    
}
