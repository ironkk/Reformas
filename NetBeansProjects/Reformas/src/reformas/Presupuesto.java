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
public class Presupuesto implements Serializable {

// no de presupuesto, el concepto, y el precio total del presupuesto neto.
    private int nPresupuesto;
    private String concepto;
    private int precioTotal;
    private String estado;

    public Presupuesto() {
    }

    public Presupuesto(int nPresupuesto, String concepto, int precioTotal, String estado) {
        this.nPresupuesto = nPresupuesto;
        this.concepto = concepto;
        this.precioTotal = precioTotal;
        this.estado = estado;
    }

    public int getNPresupuesto() {
        return nPresupuesto;
    }

    public void setNPresupuesto(int nPresupuesto) {
        this.nPresupuesto = nPresupuesto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int CalcularIVA() {
        int Resultado = (precioTotal * 21) / 100;

        return Resultado;
    }
    
    // DESCUENTO
    public int ClienteVIPdescuento(Cliente seleccionado){
        if(seleccionado.isVip()){
           int ResultadoVIP = (precioTotal * 5) / 100;
            return ResultadoVIP;
        }else{
          return precioTotal;  
        }
    }

}
