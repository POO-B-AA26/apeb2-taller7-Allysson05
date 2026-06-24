
import java.util.ArrayList;

/**
 *Problema 01: Dibuje un diagrama de clases que muestre la estructura de un capítulo 
 * de libro; un capítulo está compuesto por varias secciones, cada una de las cuales 
 * comprende varios párrafos y figuras. Un párrafo incluye varias sentencias, cada una 
 * de las cuales contiene varias palabras.
 * @author Allysson Sanchez
 * @version 1.0
 */

class Palabra {
    private String valor;
    public Palabra(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Palabra{valor=" + valor + "}";
    }
}

class Sentencia {
    private String texto;
    private ArrayList<Palabra> palabras;

    public Sentencia(String texto) {
        this.texto   = texto;
        this.palabras = new ArrayList<>();
        for (String p : texto.split(" ")) {
            palabras.add(new Palabra(p));
        }
    }

    @Override
    public String toString() {
        return "Sentencia{texto=" + texto + ", palabras=" + palabras.size() + "}";
    }
}

class ComponenteContenido {
    private int orden;

    public ComponenteContenido(int orden) {
        this.orden = orden;
    }

    public String getTipo() {
        return "Componente";
    }

    @Override
    public String toString() {
        return "ComponenteContenido{orden=" + orden + "}";
    }
}

class Parrafo extends ComponenteContenido {
    private int numeroPar;
    private ArrayList<Sentencia> sentencias;

    public Parrafo(int orden, int numeroPar) {
        super(orden);
        this.numeroPar  = numeroPar;
        this.sentencias = new ArrayList<>();
    }

    public void agregarSentencia(Sentencia s) {
        sentencias.add(s);
    }

    @Override
    public String getTipo() {
        return "Parrafo";
    }

    @Override
    public String toString() {
        return "Parrafo{numeroPar=" + numeroPar
             + ", sentencias=" + sentencias.size() + "}"
             + super.toString();
    }
}

class Figura extends ComponenteContenido {
    private String urlImagen;
    private String pieDeFoto;

    public Figura(int orden, String urlImagen, String pieDeFoto) {
        super(orden);
        this.urlImagen = urlImagen;
        this.pieDeFoto = pieDeFoto;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public String getPieDeFoto() {
        return pieDeFoto;
    }
    
    @Override
    public String getTipo() {
        return "Figura";
    }

    @Override
    public String toString() {
        return "Figura{urlImagen=" + urlImagen
             + ", pieDeFoto=" + pieDeFoto + "}"
             + super.toString();
    }
}

class Seccion {
    private String titulo;
    private ArrayList<ComponenteContenido> componentes;

    public Seccion(String titulo) {
        this.titulo      = titulo;
        this.componentes = new ArrayList<>();
    }

    public void agregarComponente(ComponenteContenido c) {
        componentes.add(c);
    }

    @Override
    public String toString() {
        return "Seccion{titulo=" + titulo
             + ", componentes=" + componentes.size() + "}";
    }
}

class Capitulo {
    private int numero;
    private ArrayList<Seccion> secciones;

    public Capitulo(int numero) {
        this.numero   = numero;
        this.secciones = new ArrayList<>();
    }

    public void agregarSeccion(Seccion s) {
        secciones.add(s);
    }

    @Override
    public String toString() {
        return "Capitulo{numero=" + numero
             + ", secciones=" + secciones.size() + "}";
    }
}

public class Problema_1_EjecutorCapitulo {
    public static void main(String[] args) {

        Palabra p1 = new Palabra("Mariposas");
        System.out.println(p1);
        Palabra p2 = new Palabra("vuelan");
        System.out.println(p2);

        System.out.println("=========SENTENCIAS========");
        Sentencia s1 = new Sentencia("La mariposa vuela entre las flores");
        System.out.println(s1);
        Sentencia s2 = new Sentencia("Una mariposa puede volar miles de kilometros");
        System.out.println(s2);

        System.out.println("==========PARRAFO CON SENTENCIAS========");
        Parrafo par1 = new Parrafo(1, 1);
        par1.agregarSentencia(s1);
        par1.agregarSentencia(s2);
        System.out.println(par1);

        System.out.println("========FIGURA=========");
        Figura fig1 = new Figura(2, "mariposas_tropicales.png", "Figura 1: Mariposas del Tropico");
        System.out.println(fig1);

        System.out.println("===========SECCION CON PARRAFO Y FIGURA==========");
        Seccion sec1 = new Seccion("Documental de las mariposas");
        sec1.agregarComponente(par1);
        sec1.agregarComponente(fig1);
        System.out.println(sec1);

        System.out.println("=========CAPITULO==========");
        Capitulo cap = new Capitulo(1);
        cap.agregarSeccion(sec1);
        System.out.println(cap);
        
    }
}

/**
 * RUN: 
 * run:
Palabra{valor=Mariposas}
Palabra{valor=vuelan}
=========SENTENCIAS========
Sentencia{texto=La mariposa vuela entre las flores, palabras=6}
Sentencia{texto=Una mariposa puede volar miles de kilometros, palabras=7}
==========PARRAFO CON SENTENCIAS========
Parrafo{numeroPar=1, sentencias=2}ComponenteContenido{orden=1}
========FIGURA=========
Figura{urlImagen=mariposas_tropicales.png, pieDeFoto=Figura 1: Mariposas del Tropico}ComponenteContenido{orden=2}
===========SECCION CON PARRAFO Y FIGURA==========
Seccion{titulo=Documental de las mariposas, componentes=2}
=========CAPITULO==========
Capitulo{numero=1, secciones=1}
BUILD SUCCESSFUL (total time: 0 seconds)
 */