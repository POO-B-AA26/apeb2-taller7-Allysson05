
/**
 * Problema 04: Problema 4 - Sistema de nómina para trabajadores
Se desea desarrollar un sistema de nómina para los trabajadores de una empresa. 
* Los datos personales de los trabajadores son nombre y apellidos, dirección y DNI. 
* Además, existen diferentes tipos de trabajadores:
* Fijos Mensuales: que cobran una cantidad fija al mes.
* Comisionistas: cobran un porcentaje fijo por las ventas que han realizado
* Por Horas: cobran un precio por cada una de las horas que han realizado durante el mes. 
* El precio es fijo para las primeras 40 horas y es otro para las horas realizadas a partir de la 40 hora mensual.
* Jefe: cobra un sueldo fijo (no hay que calcularlo). Cada empleado tiene obligatoriamente un jefe 
* (exceptuando los jefes que no tienen ninguno). El programa debe permitir dar de alta a trabajadores, 
* así como fijar horas o ventas realizadas e imprimir la nómina correspondiente al final de mes.
 * @author Allysson Sanchez 
 * @version 1.0
 */

class Trabajador{
    protected String nombre;
    protected String apellido;
    protected String direccion;
    protected String dni;
    protected Jefe jefe;

    public Trabajador(String nombre, String apellido, String direccion, String dni, Jefe jefe) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.dni = dni;
        this.jefe = jefe;
    }
    
    public double calcularNomina(){
        return 0;
    }

    @Override
    public String toString() {
        return "Trabajador{" + "nombre=" + nombre + ", apellido=" + apellido + 
                ", direccion=" + direccion + ", dni=" + dni + ", jefe=" + jefe + '}';
    } 
}

class Jefe extends Trabajador{
    private double sueldoFijo;
    private String departamento;

    public Jefe(double sueldoFijo, String departamento, String nombre, String apellido, String direccion, String dni) {
        super(nombre, apellido, direccion, dni, null);
        this.sueldoFijo = sueldoFijo;
        this.departamento = departamento;
    }

    public double getSueldoFijo() {
        return sueldoFijo;
    }

    public String getDepartamento() {
        return departamento;
    }
    
    
    @Override
    public double calcularNomina(){
        return sueldoFijo;
    }

    @Override
public String toString() {
    return "Jefe{" +
           "nombre=" + nombre +
           ", apellido=" + apellido +
           ", departamento=" + departamento +
           ", sueldoFijo=" + sueldoFijo +
           "}";
}
}

class FijoMensual extends Trabajador {
    private double sueldoMensual;

    public FijoMensual(double sueldoMensual, String nombre, String apellido, String direccion, String dni, Jefe jefe) {
        super(nombre, apellido, direccion, dni, jefe);
        this.sueldoMensual = sueldoMensual;
    }
    
    @Override
    public double calcularNomina(){
        return sueldoMensual;
    }

    @Override
    public String toString() {
        return "FijoMensual{" +
               "nombre=" + nombre +
               ", apellido=" + apellido +
               ", sueldoMensual=" + sueldoMensual +
               "}";
}
}

class Comisionista extends Trabajador{
    private double porcentaje;
    private double totalVentas;

    public Comisionista(double porcentaje, String nombre, String apellido, String direccion, String dni, Jefe jefe) {
        super(nombre, apellido, direccion, dni, jefe);
        this.porcentaje = porcentaje;
        this.totalVentas = 0;
    }
    
    public void fijarVentas(double ventas){
        this.totalVentas= ventas;
    }
    
    @Override 
    public double calcularNomina(){
        return totalVentas * (porcentaje/100);
    }
    
    @Override
    public String toString() {
        return "Comisionista{" +
               "nombre=" + nombre +
               ", apellido=" + apellido +
               ", porcentaje=" + porcentaje +
               ", totalVentas=" + totalVentas +
               "}";
}
}

class PorHoras extends Trabajador{
    private double precioHoraNormal;
    private double precioHorasExtras;
    private int horasTrabajadas;

    public PorHoras(double precioHoraNormal, double precioHorasExtras, String nombre, String apellido, String direccion, String dni, Jefe jefe) {
        super(nombre, apellido, direccion, dni, jefe);
        this.precioHoraNormal = precioHoraNormal;
        this.precioHorasExtras = precioHorasExtras;
        this.horasTrabajadas=0;
    }
    
    public void fijarHoras(int horas){
        this.horasTrabajadas = horas;
    }
    
    @Override
    public double calcularNomina(){
        if(horasTrabajadas <= 40){
            return (horasTrabajadas * precioHoraNormal);
        }else{
            return (40 * precioHoraNormal)+((horasTrabajadas - 40)*precioHorasExtras);
        }
    }
    
    @Override
public String toString() {
    return "PorHoras{" +
           "nombre=" + nombre +
           ", apellido=" + apellido +
           ", horasTrabajadas=" + horasTrabajadas +
           ", precioHoraNormal=" + precioHoraNormal +
           ", precioHoraExtra=" + precioHorasExtras +
           "}";
}
}
public class Problema_4_EjecutorNomina {
    public static void main(String[] args) {
        Jefe jefe1 = new Jefe(800, "Sistemas", "Marco", "Perez", "Av.Occidental","1120747364");
        System.out.println("Nombre: " + jefe1.nombre + " " + jefe1.apellido);
        System.out.println("Cargo: Jefe");
        System.out.println("Nomina del Jefe: " + jefe1.calcularNomina());
        
        System.out.println("======FIJO MENSUAL======");
        FijoMensual fijo1 = new FijoMensual(600, "Ana", "Garcia", "Av.Oriental de Paso", "2649647263", jefe1);
        System.out.println("Nombre: " + fijo1.nombre + " " + fijo1.apellido);
        System.out.println("Nomina Fijo Mensual: " + fijo1.calcularNomina());
        
        System.out.println("========COMISIONISTA=========");
        Comisionista comi1 = new Comisionista(15.0, "Luis", "Torres", "Cnetro Ciudad", "1234398464", jefe1);
        comi1.fijarVentas(1200);
        System.out.println("Nombre: " + comi1.nombre + " " + comi1.apellido);
        System.out.println("Nomina Comisionista: " + comi1.calcularNomina());
        
        System.out.println("========POR HORAS======");
        PorHoras ph1 = new PorHoras(10.0, 17.0, "Maria", "Caraguay", "La Alborada", "1870863821", jefe1);
        ph1.fijarHoras(50);
        System.out.println("Nombre:" + ph1.nombre + " " + ph1.apellido);
        System.out.println("Nomina Trabajo Por Horas: " + ph1.calcularNomina());
        
        
    }
}
/**
 * RUN:
 * run:
Nombre: Marco Perez
Cargo: Jefe
Nomina del Jefe: 800.0
======FIJO MENSUAL======
Nombre: Ana Garcia
Nomina Fijo Mensual: 600.0
========COMISIONISTA=========
Nombre: Luis Torres
Nomina Comisionista: 180.0
========POR HORAS======
Nombre:Maria Caraguay
Nomina Trabajo Por Horas: 570.0
 */