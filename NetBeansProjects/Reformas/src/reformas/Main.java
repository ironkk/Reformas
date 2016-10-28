/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reformas;

import entradaDatos.Entrada;
import static entradaDatos.Entrada.pedirCadena;
import static entradaDatos.Entrada.pedirEntero;
import java.util.Iterator;
import utilidades.Fichero;

/**
 *
 * @author dam
 */
public class Main {

    private static ListaClientes misClientes;
    private static ListaPresupuestos misPresupuestos;

    // Fichero para grabar los datos
    private static Fichero ficheroClientes;

    //Fichero presupuestos
//    private static Fichero ficheroPresupuestos;
    public static void main(String[] args) {

        int opcion;
        // Aquí siempre inicializamos el nombre del fichero
        ficheroClientes = new Fichero("reformas.xml");
        // Leemos del fichero por si ya hay clientes guardados
        misClientes = (ListaClientes) ficheroClientes.leer();
        // Cuando lee del fichero, si no hay fichero o no hay datos misClientes será null 
        if (misClientes == null) {
            misClientes = new ListaClientes();
        }
        do {

            mostrarMenu();
            opcion = Entrada.pedirEntero("Escoge una opción");

            switch (opcion) {
                case 1:
                    altaCliente();

                    break;
                case 2:
                    String telefono;
                    do {
                        telefono = pedirCadena("Telefono:");
                        // La longitud del tfno tiene que ser 9
                        if (telefono.length() != 9) {
                            System.out.println("Error.!");
                        }
                    } while (telefono.length() != 9);
                    nuevoPresupuesto(telefono);

                    break;
                case 3:
                    presupuestosPendientes();

                    break;
                case 4:
                    ListadoPresupuestos();

                    break;
                case 5:
                    ListadoPresupuestosRechazados();

                    break;

                case 6:
                    ListadoClientes();

                    break;

                case 7:
                    CambiarEstado();

                    break;
                case 8:

                    System.out.println("Adiós!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción incorrecta.");

            }

        } while (opcion != 0);

    }

    private static void mostrarMenu() {
        System.out.println("*** REFORMAS ***");
        System.out.println("1. Alta Cliente");
        System.out.println("2. Nuevos Presupuestos");
        System.out.println("3. Presupuestos Pendientes");
        System.out.println("4. Listado de presupuestos de un cliente determinado.");
        System.out.println("5. Listado de presupuestos rechazados.");
        System.out.println("6. Listado de clientes, donde aparezca también el total de presupuestos\n"
                + "que tiene cada uno.");
        System.out.println("7. Cambiar estado de un presupuesto.");
        System.out.println("8. Salir");

    }

    private static void altaCliente() {
        String telefono;
        do {
            telefono = pedirCadena("Telefono:");
            // La longitud del tfno tiene que ser 9
            if (telefono.length() != 9) {
                System.out.println("Error.!");
            }
        } while (telefono.length() != 9);
        if (misClientes.existeCliente(telefono)) {
            System.out.println("Cliente Existente");

        } else {

            String nombre;
            do {

                nombre = pedirCadena("Nombre");
                if (nombre.equals("")) {
                    System.out.println("No se puede dejar el nombre en blanco");
                }
            } while (nombre.equals(""));
            String apellidos;
            do {

                apellidos = pedirCadena("Apellidos");
                if (apellidos.equals("")) {
                    System.out.println("No se pueden dejar los apellidos en blanco");
                }
            } while (apellidos.equals(""));

            String respuesta;
            boolean vip = false;
            do {
                respuesta = pedirCadena("Eres vip? SI/NO");

                if (respuesta.equalsIgnoreCase("si")) {
                    vip = true;

                } else if (respuesta.equalsIgnoreCase("no")) {
                    vip = false;

                }

            } while (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no"));

            Cliente c = new Cliente(nombre, apellidos, telefono, vip);
            misClientes.alta(c);
            ficheroClientes.grabar(misClientes);
        }
    }

    private static void altaPresupuesto(Cliente seleccionado) {
        int nPresupuesto;
        String concepto;
        int precioTotal;
        String estado;

        do {

            nPresupuesto = pedirEntero("Numero Presupuesto: ");
            if (nPresupuesto < 0 || nPresupuesto > 10) {
                System.out.println("Debe estar entre 0 y 10.");
            }
        } while (nPresupuesto < 0 || nPresupuesto > 10);

        do {

            concepto = pedirCadena("Concepto");
            if (concepto.equals("")) {
                System.out.println("No se puede dejar el concepto en blanco");
            }
        } while (concepto.equals(""));

        precioTotal = pedirEntero("Precio total: ");

        do {
            estado = pedirCadena("Estado ?");
            if (!estado.equalsIgnoreCase("Pendiente") && !estado.equalsIgnoreCase("Aceptado") && !estado.equalsIgnoreCase("Rechazado")) {
                System.out.println("Estado Incorrecto");
            } else {
                estado = "Pendiente";
            }
        } while (!estado.equalsIgnoreCase("Pendiente") && !estado.equalsIgnoreCase("Aceptado") && !estado.equalsIgnoreCase("Rechazado"));

        Presupuesto p = new Presupuesto(nPresupuesto, concepto, precioTotal, estado);
        seleccionado.getMisPresupuestos().alta(p);
        ficheroClientes.grabar(misClientes);

    }

    private static void presupuestosPendientes() {
        // Primero recorremos el Array de Clientes
        for (Cliente c : misClientes.getLista()) {
            // Para cada cliente recorremos su lista de presupuestos
            for (Presupuesto p : c.getMisPresupuestos().getListaPresupuesto()) {
                if (p.getEstado().equalsIgnoreCase("Pendiente")) {
                    System.out.println(p);
                }
            }
        }

    }

    private static void ListadoPresupuestos() {

        for (Cliente c : misClientes.getLista()) {
            // Para cada cliente recorremos su lista de presupuestos

            for (Presupuesto p : c.getMisPresupuestos().getListaPresupuesto()) {
                System.out.println(p + " Precio con IVA " + p.CalcularIVA());
                System.out.println(p + "Precio con Descuento para VIP" + p.ClienteVIPdescuento(c));
            }
        }
    }

    private static void ListadoPresupuestosRechazados() {
        for (Cliente c : misClientes.getLista()) {
            // Para cada cliente recorremos su lista de presupuestos
            for (Presupuesto p : c.getMisPresupuestos().getListaPresupuesto()) {
                if (p.getEstado().equalsIgnoreCase("Rechazado")) {
                    System.out.println(p);
                }
            }
        }

    }

    private static void ListadoClientes() {
        Iterator<String> it = misClientes.iterator();

        while (it.hasNext()) {

            System.out.println(it.next());

        }
    }

    private static void CambiarEstado() {
        Presupuesto p = null;
        do {
            int nPresupuesto = Entrada.pedirEntero("Introducir Nº Presupuesto: ");
            p = misClientes.obtenerPresupuestoPorN(nPresupuesto);

            if (p == null) {
                System.out.println("No existe ningun Nº Presupuesto");
            }

        } while (p == null);
        p.setEstado(Entrada.pedirCadena("Introducir nuevo estado -> (Aceptado|Pendiente|Rechazado)"));
        ficheroClientes.grabar(misClientes);

    }

    private static void nuevoPresupuesto(String telefono) {

        if (misClientes.existeCliente(telefono) == true) {
            Cliente seleccionado = misClientes.obtenerClientePorTelefono(telefono);
            altaPresupuesto(seleccionado);

        } else {
            altaCliente();
            Cliente seleccionado = misClientes.obtenerClientePorTelefono(telefono);
            altaPresupuesto(seleccionado);

        }
    }
}
