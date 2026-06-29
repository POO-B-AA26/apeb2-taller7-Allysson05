/**
 * Problema 3: Problema 3 - Sistema de envío de mensajes a móviles
 *Implemente un sistema de envío de mensajes a móviles. Existen dos tipos de mensajes 
 * que se pueden enviar entre móviles, mensajes de texto (SMS) y mensajes que contienen 
 * imágenes (MMS). Por un lado, los mensajes de texto contienen un mensaje en caracteres 
 * que se desea enviar de un móvil a otro. Por otro lado, los mensajes que contienen 
 * imágenes almacenan información sobre la imagen a enviar, la cual se representará por 
 * el nombre del fichero que la contiene. Independientemente del tipo de mensaje, cada 
 * mensaje tendrá asociado un remitente de dicho mensaje y un destinatario. Ambos estarán 
 * definidos obligatoriamente por un número de móvil, y opcionalmente se podrá guardar 
 * información sobre su nombre. Además, los métodos enviarMensaje y visualizarMensaje 
 * deben estar definidos.
 * @author Allysson Sanchez
 * @version 1.0
 */

class Movil{
    private String numero;
    private String nombre;

    public Movil(String numero) {
        this.numero = numero;
        this.nombre= "Sin nombre";
    }

    public Movil(String numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Movil{" + "numero=" + numero + ", nombre=" + nombre + '}';
    }
}

class Mensaje{
    protected Movil remitente;
    protected Movil destinatario;

    public Mensaje(Movil remitente, Movil destinatario) {
        this.remitente = remitente;
        this.destinatario = destinatario;
    }
    
    public String enviarMensaje(){
        return "Enviando mensaje de " + remitente.getNumero() + " a " + destinatario.getNumero();
    }
    
    public String visualizarMensaje(){
        return "Remitente: " + remitente.toString() + "\nDestinatario: " + destinatario.toString();
    }

    @Override
    public String toString() {
        return "Mensaje{" + "remitente=" + remitente + ", destinatario=" + destinatario + '}';
    }
}

class Sms extends Mensaje{
    private String textoMensaje;

    public Sms(String textoMensaje, Movil remitente, Movil destinatario) {
        super(remitente, destinatario);
        this.textoMensaje = textoMensaje;
    }
    
    @Override
    public String enviarMensaje(){
        return "Enviando sms de " + remitente.getNumero()+ " a " + destinatario.getNumero()
                + "\nTexto" + textoMensaje;
    }
    @Override
    public String visualizarMensaje(){
        return "Sms" + "\nTexto: " + textoMensaje + "\nRemitente: " + remitente +
                "\nDestinatario: " +destinatario;
    }
   
    @Override
    public String toString() {
        return "Sms{" + "textoMensaje=" + textoMensaje + '}';
    }    
}

class Mms extends Mensaje {
    private String nombreFichero;

    public Mms(String nombreFichero, Movil remitente, Movil destinatario) {
        super(remitente, destinatario);
        this.nombreFichero = nombreFichero;
    }
    
    @Override 
    public String enviarMensaje(){
        return "Enviando imagen: " + nombreFichero + "\nDe: " + remitente.getNumero()+
                "\nPara: " + destinatario.getNumero();
    }
    
    @Override
    public String visualizarMensaje (){
        return  "MMS" 
                + "\nArchivo: " + nombreFichero + "\nRemitente: " + remitente + 
                "\nDestinatario: " + destinatario;
    }
    @Override
    public String toString() {
        return "Mms{" + "nombreFichero=" + nombreFichero + '}';
    }
    
    
}

public class Problema_3_EjecutorMensaje {
    public static void main(String[] args) {
       Movil mov1 = new Movil("0987652435");
       Movil mov2 = new Movil("0985940485","Lucia");
       
       System.out.println("========SMS=========");
       Sms sms1 = new Sms("Hola, ¿como estas?", mov1, mov2);
       System.out.println(sms1.enviarMensaje());
       System.out.println(sms1.visualizarMensaje());
       System.out.println(sms1);
       
        System.out.println("=========MMS========");
       Mms msm1 = new Mms("foto_Familiar.png", mov1, mov2);
       System.out.println(msm1.enviarMensaje());
       System.out.println(msm1.visualizarMensaje()); 
       System.out.println(msm1);
        
    }
}

/**
 * RUN: 
 * run:
========SMS=========
Enviando sms de 0987652435 a 0985940485
TextoHola, �como estas?
Sms
Texto: Hola, �como estas?
Remitente: Movil{numero=0987652435, nombre=Sin nombre}
Destinatario: Movil{numero=0985940485, nombre=Lucia}
Sms{textoMensaje=Hola, �como estas?}
=========MMS========
Enviando imagen: foto_Familiar.png
De: 0987652435
Para: 0985940485
MMS
Archivo: foto_Familiar.png
Remitente: Movil{numero=0987652435, nombre=Sin nombre}
Destinatario: Movil{numero=0985940485, nombre=Lucia}
Mms{nombreFichero=foto_Familiar.png}
BUILD SUCCESSFUL (total time: 0 seconds)
 */