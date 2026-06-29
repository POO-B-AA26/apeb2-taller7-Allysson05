
/**
 *Problema 06: El banco UN BANCO mantiene las cuentas de varios clientes. Los datos 
 * que describen a cada una de las cuentas consisten en el número de cuenta, el nombre 
 * del cliente y el balance actual. Escriba una clase para implementar dicha cuenta bancaria. 
 * El método constructor debe aceptar como parámetros el número de cuenta y el nombre. Debe 
 * proporcionarse métodos para depositar o retirar una cantidad de dinero y obtener el balance actual.
 * El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de AHORROS. 
 * Una cuenta de cheques puede sobregirarse (el balance puede ser menor que cero), pero 
 * una cuenta de ahorros no. Al final de cada mes, se calcula el interés sobre la cantidad que tenga 
 * la cuenta de ahorros. Este interés se suma al balance. Escriba clases para describir cada uno de 
 * estos tipos de cuentas, haciendo un máximo uso de la herencia. La clase de la cuenta de ahorros 
 * debe proporcionar un método que sea invocado para calcular el interés. Además, el banco está pensando 
 * en implementar una cuenta PLATINO que viene siendo similar a los otros dos tipos anteriores de cuentas 
 * bancarias, ésta tiene el interés del 10%, sin cargos ni castigos por sobregiro.
 * @author Allysson Sanchez
 * @version 1.0
 */

class CuentaBancaria {
    protected String nombreCliente;
    protected String numeroCuenta;
    protected double balance;

    public CuentaBancaria(String nombreCliente, String numeroCuenta) {
        this.nombreCliente = nombreCliente;
        this.numeroCuenta = numeroCuenta;
        this.balance=0.0;
    }
    
    public void depositarDinero(double monto){
        this.balance = (this.balance + monto);
    }
    
    public String retirarDinero(double monto){
        this.balance = (this.balance - monto);
        return "Balance actual : " + balance;
    }

    @Override
    public String toString() {
        return  "\nnombreCliente=" + nombreCliente + 
                "\nnumeroCuenta=" + numeroCuenta + 
                "\nbalance=" + balance ;
    }
}

class CuentaCheque extends CuentaBancaria{

    public CuentaCheque(String nombreCliente, String numeroCuenta) {
        super(nombreCliente, numeroCuenta);
    }
    
    @Override
    public String retirarDinero(double monto){
        double saldoInicial = this.balance;
        this.balance = (this.balance - monto);
        return "Saldo Inicial : " + saldoInicial + 
               " | Retiro realizado " + monto + 
               " | Balance final: " + this.balance;
    }

    @Override
    public String toString() {
        return "======CUENTA CHEQUE=======" + "\n" + super.toString();
    }
}
    
class CuentaAhorro extends CuentaBancaria{
    protected double tasaInteres;

    public CuentaAhorro(double tasaInteres, String nombreCliente, String numeroCuenta) {
        super(nombreCliente, numeroCuenta);
        this.tasaInteres = tasaInteres;
    }
    
    @Override 
    public String retirarDinero(double monto){
        if(monto > this.balance){
            return "Saldo insuficiente: " + balance;
        }
        double saldoIncicial = this.balance;
        this.balance = (this.balance - monto);
        return "Saldo actual: " + saldoIncicial + 
               " | Retiro: " + monto + 
               " | Saldo final: " + this.balance;
    }
    
    public void calcularInteres (){
        this.balance = (this.balance +(this.balance *tasaInteres / 100));
    }

    @Override
    public String toString() {
        return "=====CUENTA AHORRO=======" + "\ntasaInteres: " + tasaInteres +"\n" + super.toString();
    }
}

class CuentaPlatino extends CuentaBancaria {
    protected double tasaInteres = 10.0;

    public CuentaPlatino(String nombreCliente, String numeroCuenta) {
        super(nombreCliente, numeroCuenta);
    }

    @Override
    public String retirarDinero (double monto){
        double saldoInicial = this.balance;
        this.balance = (this.balance - monto);
        return "Saldo Inicial: " + saldoInicial + 
                " | Retiro: " + monto + 
                " | Saldo final: " + this.balance;
    }
    
    public void calcularIntereses (){
        this.balance = (this.balance + (this.balance * tasaInteres/100));
    }

    @Override
    public String toString() {
        return "=====CUENTA PLATINO=====" + "\ntasaInteres=" + tasaInteres + 
                                            "\n" +  super.toString();
    }  
}
public class Problema_6_EjecutorBanco {
    public static void main(String[] args) {
        
        CuentaCheque cc1 = new CuentaCheque("Martha Arango","CHE-10164623472");
        CuentaCheque cc2 = new CuentaCheque("Marco Jimenez","CHE-46757465369");
        CuentaAhorro ca1 = new CuentaAhorro(6.5, "Juan Maza", "AHO-3927532075038e");
        CuentaAhorro ca2 = new CuentaAhorro(5, "Jennifer Camacho", "AHO-537467252");
        CuentaPlatino cp1 = new CuentaPlatino("Lucia Gutierrez", "CP-917723826");
        CuentaPlatino cp2 = new CuentaPlatino("Norma Suarez", "CP-7196236219");
        
        cc1.depositarDinero(1500.0);
        cc1.retirarDinero(600.0);
        System.out.println(cc1);
        
        cc2.depositarDinero(200.0);
        cc2.retirarDinero(300.0);
        System.out.println(cc2);
        
        ca1.depositarDinero(5000.0);
        ca1.retirarDinero(6000.0);
        ca1.calcularInteres();
        System.out.println(ca1);
        
        ca2.depositarDinero(50.0);
        ca2.retirarDinero(48.0);
        ca2.calcularInteres();
        System.out.println(ca2);
        
        cp1.depositarDinero(700.0);
        cp1.retirarDinero(700.0);
        cp1.calcularIntereses();
        System.out.println(cp1);
        
        cp2.depositarDinero(20.0);
        cp2.retirarDinero(10.0);
        cp2.calcularIntereses();
        System.out.println(cp2);
       
    }
}

/**
 * RUN: 
======CUENTA CHEQUE=======

nombreCliente=Martha Arango
numeroCuenta=CHE-10164623472
balance=900.0
======CUENTA CHEQUE=======

nombreCliente=Marco Jimenez
numeroCuenta=CHE-46757465369
balance=-100.0
=====CUENTA AHORRO=======
tasaInteres: 6.5

nombreCliente=Juan Maza
numeroCuenta=AHO-3927532075038e
balance=5325.0
=====CUENTA AHORRO=======
tasaInteres: 5.0

nombreCliente=Jennifer Camacho
numeroCuenta=AHO-537467252
balance=2.1
=====CUENTA PLATINO=====
tasaInteres=10.0

nombreCliente=Lucia Gutierrez
numeroCuenta=CP-917723826
balance=0.0
=====CUENTA PLATINO=====
tasaInteres=10.0

nombreCliente=Norma Suarez
numeroCuenta=CP-7196236219
balance=11.0
BUILD SUCCESSFUL (total time: 0 seconds)
 */