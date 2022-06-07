package Logica;
import java.util.List;

public class Producto {
    long id,dueño, cantidad;
    float precio,promedio;
    String nombre, descripcion, rutafoto, categoria;
    int totalcalificaciones;
    Producto siguientecatalogo;
    Producto siguienteinventario;
    Calificacion calificaciones;
    List<Integer> tablacalificaciones;

    Producto(int id, int cantidad, int totalcalificaciones, float promedio, float precio,
            String nombre, String descripcion, String rutafoto, String categoria, List<Integer> tablacalificaciones,
            int dueño, Producto siguientecatalogo, Producto siguienteinventario, Calificacion calificaciones){
                
        this.id= id;
        this.cantidad= cantidad;
        this.totalcalificaciones= totalcalificaciones;
        this.precio= precio;
        this.promedio= promedio;
        this.nombre= nombre;
        this.descripcion= descripcion;
        this.rutafoto= rutafoto;
        this.dueño=dueño;
        this.siguientecatalogo= siguientecatalogo;
        this.siguienteinventario= siguienteinventario;
        this.calificaciones=calificaciones;
        this.tablacalificaciones= tablacalificaciones;
        this.categoria= categoria;
    }
    
    
    public Producto buscarxduenio(int id){

        Producto producto = this;
        if (producto.dueño!=id){
            if (producto.siguientecatalogo != null)
                producto = producto.siguientecatalogo.buscarxduenio(id);
        }
        return producto;
    }


    public void setPromedio() {
    }


    public void setTablaCalificaciones() {
    }


    public void setTotalCalificaciones() {
    }


    public void imprimirPreviuw() {
        System.out.println("----------------------");
        System.out.println(this.rutafoto);
        System.out.println(this.id);
        System.out.println(this.nombre);
        System.out.println(this.precio);
        System.out.println("----------------------");
        if (this.siguienteinventario != null)
            this.siguienteinventario.imprimirPreviuw();
        if(this.siguientecatalogo != null)
            this.siguientecatalogo.imprimirPreviuw();

    }
}
