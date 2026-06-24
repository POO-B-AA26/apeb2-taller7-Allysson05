
import java.util.ArrayList;

/**
 * Problema 05: Dadas las siguientes clases, que expresan una relación de herencia entre las entidades:
 * Se desea gestionar la venta de entradas para un espectáculo en un teatro. El patio de butacas del 
 * teatro se divide en varias zonas, cada una identificada por su nombre.
 * Para realizar la compra de una entrada, un espectador debe indicar la zona que desea y 
 * presentar al vendedor el documento que justifique que tiene algún tipo de descuento (estudiante, 
 * abonado o pensionista). El vendedor sacará la entrada del tipo apropiado y de la zona indicada, en 
 * el momento de la compra se asignará a la entrada un identificador (un número entero) que permitirá la
 * identificación de la entrada en todas las operaciones que posteriormente se desee realizar con ella.
 * Una entrada tiene como datos asociados su identificador, la zona a la que pertenece y el nombre del comprador.
 * Los precios de las entradas dependen de la zona y del tipo de entrada según lo explicado a continuación:
 * Entradas normales: su precio es el precio normal de la zona elegida sin ningún tipo de descuento.
 * Entradas reducidas (para estudiantes o pensionistas): su precio tiene una rebaja del 15% sobre el precio normal de la zona elegida.
 * Entradas abonado: su precio es el precio para abonados de la zona elegida.
 * La interacción entre el vendedor y la aplicación es la descrita en los siguientes casos de usos.
 * @author Allysson Sanchez
 * @version 1.0
 */

class Zona {
    private String nombre;
    private int    numLocalidades;
    private double precioNormal;
    private double precioAbonado;
    private int    entradasVendidas;

    public Zona(String nombre, int numLocalidades, double precioNormal, double precioAbonado) {
        this.nombre           = nombre;
        this.numLocalidades   = numLocalidades;
        this.precioNormal     = precioNormal;
        this.precioAbonado    = precioAbonado;
        this.entradasVendidas = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumLocalidades() {
        return numLocalidades;
    }

    public double getPrecioNormal() {
        return precioNormal;
    }

    public double getPrecioAbonado() {
        return precioAbonado;
    }

    public int getEntradasVendidas() {
        return entradasVendidas;
    }
    
    public void incrementarVendidas(){
        entradasVendidas++;
    }

    public boolean estaCompleta() {
        return entradasVendidas >= numLocalidades;
    }

    @Override
    public String toString() {
        return "Zona{nombre=" + nombre + ", localidades=" + numLocalidades
             + ", vendidas=" + entradasVendidas
             + ", precioNormal=" + precioNormal
             + ", precioAbonado=" + precioAbonado + "}";
    }
}

class Entrada {
    public int    id;
    public Zona   zona;
    public String nombreComprador;
    public double precio;

    public Entrada(int id, Zona zona, String nombreComprador) {
        this.id = id;
        this.zona = zona;
        this.nombreComprador = nombreComprador;
        this.precio = zona.getPrecioNormal();
        zona.incrementarVendidas();
    }

    public double calcularPrecio() {
        return zona.getPrecioNormal();
    }

    @Override
    public String toString() {
            return "ID: " + id +
                   "\nComprador: " + nombreComprador +
                   "\nZona: " + zona.getNombre() +
                   "\nPrecio: $" + precio;
}
}

class EntradaNormal extends Entrada {

    public EntradaNormal(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
        this.precio=calcularPrecio();
    }

    @Override
    public double calcularPrecio() {
        return zona.getPrecioNormal();
    }

    @Override
    public String toString() {
        return "EntradaNormal{}" + super.toString();
    }
}

class EntradaReducida extends Entrada {
    private String tipoDescuento;

    public EntradaReducida(int id, Zona zona, String nombreComprador, String tipoDescuento) {
        super(id, zona, nombreComprador);
        this.tipoDescuento = tipoDescuento;
        this.precio = calcularPrecio();
    }

    @Override
    public double calcularPrecio() {
        return zona.getPrecioNormal() * 0.85;
    }
    
    @Override
    public String toString() {
           return "EntradaReducida\n" +
                  "Tipo descuento: " + tipoDescuento + "\n" +
                  "ID: " + id + "\n" +
                  "Comprador: " + nombreComprador + "\n" +
                  "Zona: " + zona.getNombre() + "\n" +
                  "Precio: $" + precio;
}
}

class EntradaAbonado extends Entrada {

    public EntradaAbonado(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
        this.precio = calcularPrecio();
    }

    @Override
    public double calcularPrecio() {
        return zona.getPrecioAbonado();
    }

    @Override
    public String toString() {
        return "EntradaAbonado{}" + super.toString();
    }
}

class Teatro {
    private Zona[] zonas;
    private ArrayList<Entrada> entradas;
    private int contadorId;

    public Teatro() {
        this.contadorId = 1;
        this.entradas   = new ArrayList<>();
        this.zonas = new Zona[]{
            new Zona("Principal", 200, 25.0,  17.5),
            new Zona("PalcoB",     40, 70.0,  40.0),
            new Zona("Central",   400, 20.0,  14.0),
            new Zona("Lateral",   100, 15.5,  10.0)
        };
    }

    public String venderEntrada(String nombreZona, String nombreComprador, String tipo) {
        Zona zonaEncontrada = null;
        for (Zona z : zonas) {
            if (z.getNombre().equalsIgnoreCase(nombreZona)) {
                zonaEncontrada = z;
                break;
            }
        }
        if (zonaEncontrada == null)
            return "ERROR: No existe la zona '" + nombreZona + "'.";

        if (zonaEncontrada.estaCompleta())
            return "ERROR: La zona '" + nombreZona + "' esta completa.";

        Entrada nueva;
        switch (tipo.toLowerCase()) {
            case "reducida":
                nueva = new EntradaReducida(contadorId, zonaEncontrada, nombreComprador, "estudiante/pensionista");
                break;
            case "abonado":
                nueva = new EntradaAbonado(contadorId, zonaEncontrada, nombreComprador);
                break;
            default:
                nueva = new EntradaNormal(contadorId, zonaEncontrada, nombreComprador);
        }
        entradas.add(nueva);
        contadorId++;
        
        return "Entrada generada -> ID: " + nueva.id +
               " | Comprador: " + nombreComprador +
               " | Zona: " + nombreZona +
               " | Precio: $" + nueva.precio;
    }

    public String consultarEntrada(int id) {
        for (Entrada e : entradas) {
            if (e.id == id) return e.toString();
        }
        return "ERROR: No existe entrada con ID " + id + ".";
    }

    @Override
    public String toString() {
        return "Teatro{zonas=" + zonas.length + ", entradasVendidas=" + entradas.size() + "}";
    }
}

public class Problema_5_EjecutorTeatro {
    public static void main(String[] args) {

        Teatro teatro = new Teatro();

        System.out.println("===========VENTA DE ENTRADAS==================");
        System.out.println(teatro.venderEntrada("Principal", "Sofia Torres","normal"));
        System.out.println(teatro.venderEntrada("Central","Luisa Coronel","reducida"));
        System.out.println(teatro.venderEntrada("PalcoB","Teresa Mendez","abonado"));
        System.out.println(teatro.venderEntrada("Inexistente","Milton Abad","normal"));

        System.out.println();
        
        System.out.println("=========CONSULTAR ENTRADAS========");
        System.out.println(teatro.consultarEntrada(1));
        System.out.println(teatro.consultarEntrada(2));
        System.out.println(teatro.consultarEntrada(99));
    }
}

/**
 * RUN: 
 * run:
===========VENTA DE ENTRADAS==================
Entrada generada -> ID: 1 | Comprador: Sofia Torres | Zona: Principal | Precio: $25.0
Entrada generada -> ID: 2 | Comprador: Luisa Coronel | Zona: Central | Precio: $17.0
Entrada generada -> ID: 3 | Comprador: Teresa Mendez | Zona: PalcoB | Precio: $40.0
ERROR: No existe la zona 'Inexistente'.

=========CONSULTAR ENTRADAS========
EntradaNormal{}ID: 1
Comprador: Sofia Torres
Zona: Principal
Precio: $25.0
EntradaReducida
Tipo descuento: estudiante/pensionista
ID: 2
Comprador: Luisa Coronel
Zona: Central
Precio: $17.0
ERROR: No existe entrada con ID 99.
BUILD SUCCESSFUL (total time: 0 seconds
 */