
import java.util.Arrays;

/**
 * Problema 02: Un videoclub dispone de una serie de películas que pueden estar 
 * en DVD (con capacidad en Gb.) o en VHS (una sola cinta por película y con cierto 
 * tipo de cinta magnetica). De las películas interesa guardar el título, el autor, 
 * el año de edición y el idioma (o los idiomas, en caso de DVD). El precio de alquiler 
 * de las películas varía en función del tipo de película. Las DVD siempre son 10% mas caras que las de VHS.
 * @author Allysson Sanchez
 * @version 1.0
 */

class Pelicula{
  private String titulo;
  private String autor;
  private int anio;

    public Pelicula(String titulo, String autor, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnio() {
        return anio;
    }
    
    

    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + titulo + ", autor=" + autor + ", anio=" + anio + '}';
    }
}

class Soporte{
    private Pelicula pelicula;
    private int cantidad;
    private double precio;
    protected double costoAlquiler;

    public Soporte(Pelicula pelicula, int cantidad, double precio) {
        this.pelicula = pelicula;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }
    
    public double calcularCostoAlquiler(){
        this.costoAlquiler = (this.cantidad * this.precio);
        return this.costoAlquiler;
    }

    @Override
    public String toString() {
        return "Soporte{" + "pelicula=" + pelicula + ", cantidad=" + cantidad + ", "
                + "precio=" + precio + ", costoAlquiler=" + costoAlquiler + '}';
    }   
}

class Dvd extends Soporte{
    private double porcentajeRecargo;
    private String idiomas[];

    public Dvd(double porcentajeRecargo, String[] idiomas, Pelicula pelicula, int cantidad, double precio) {
        super(pelicula, cantidad, precio);
        this.porcentajeRecargo = porcentajeRecargo;
        this.idiomas = idiomas;
    }

    public double getPorcentajeRecargo() {
        return porcentajeRecargo;
    }

    public String[] getIdiomas() {
        return idiomas;
    }
    
    public double calcularCostoAlquiler(){
        this.costoAlquiler = super.calcularCostoAlquiler()+ (this.costoAlquiler * (this.porcentajeRecargo/100));
        return this.costoAlquiler;
    }

    @Override
    public String toString() {
        return "Dvd{" + "porcentajeRecargo=" + porcentajeRecargo + ", idiomas=" + Arrays.toString(idiomas)+ '}' + super.toString();
    }
 
}
class Vhs extends Soporte{
    private String idioma;
    private String tipoCinta; 

    public Vhs(String idioma, String tipoCinta, Pelicula pelicula, int cantidad, double precio) {
        super(pelicula, cantidad, precio);
        this.idioma = idioma;
        this.tipoCinta = tipoCinta;
    }
    
    @Override
    public double calcularCostoAlquiler(){
        this.costoAlquiler=super.calcularCostoAlquiler();
        return this.costoAlquiler;
    }

    @Override
    public String toString() {
        return "Vhs{" + "idioma=" + idioma + '}'+super.toString();
    }
    
    
}
public class Problema_2_EjecutoVideoClub {
    public static void main(String[] args) {
        String idiomas [] = {"Español", "Ingles"};
        Pelicula peli1 = new Pelicula("El mundial", "Mathew", 2026);
        Dvd dvd1 = new Dvd(10, idiomas, peli1, 2, 10);
        dvd1.calcularCostoAlquiler();
        System.out.println(dvd1);
        
        Vhs vhs1 = new Vhs(idiomas[0], "T-120",peli1, 2, 10);
        vhs1.calcularCostoAlquiler();
        System.out.println(vhs1);
    }
}
 
/**
 * RUN:
 * run:
 * Dvd{porcentajeRecargo=10.0, idiomas=[Espa�ol, Ingles]}Soporte{pelicula=Pelicula{titulo=El mundial, autor=Mathew, anio=2026}, cantidad=2, precio=10.0, costoAlquiler=22.0}
 * Vhs{idioma=Espa�ol}Soporte{pelicula=Pelicula{titulo=El mundial, autor=Mathew, anio=2026}, cantidad=2, precio=10.0, costoAlquiler=20.0}
 */