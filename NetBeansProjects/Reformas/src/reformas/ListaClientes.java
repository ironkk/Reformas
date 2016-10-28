/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reformas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author dam
 */
public class ListaClientes implements Serializable {

    private ArrayList<Cliente> lista;

    private ArrayList<Presupuesto> listaPresupuesto;

    public ArrayList<Cliente> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Cliente> lista) {
        this.lista = lista;
    }

    public void alta(Cliente c) {
        lista.add(c);

    }

    public ListaClientes() {
        lista = new ArrayList<>();
    }

    public boolean existeCliente(String telefono) {
        for (Cliente c : lista) {
            if (c.getTelefono().equalsIgnoreCase(telefono)) {
                return true;
            }
        }
        return false;
    }

    // que parametro le paso? 
    public boolean existePresupuesto(Integer nPresupuesto) {
        for (Presupuesto p : listaPresupuesto) {
            if (p.getNPresupuesto() == nPresupuesto) {
                return true;
            }
        }
        return false;
    }

    public Cliente obtenerClientePorTelefono(String telefono) {
        for (Cliente c : lista) {
            if (c.getTelefono().equalsIgnoreCase(telefono)) {
                return c;
            }
        }
        return null;
    }

    public Presupuesto obtenerPresupuestoPorN(Integer nPresupuesto) {
        for (Cliente c : lista) {
            for (Presupuesto p : c.getMisPresupuestos().getListaPresupuesto()) {
                if (p.getNPresupuesto() == nPresupuesto) {
                    return p;
                }
            }
        }
        return null;
    }

    Iterator<String> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
