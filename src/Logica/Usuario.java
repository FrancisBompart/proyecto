package Logica;
import java.util.List;

public class Usuario {
    long id, telefono,semestre; 
    int totalcalificaciones, promedio;
    boolean nuevanotificacion;
    String nombre, correo, escuela, rutafoto;
    Usuario siguiente;
    Producto inventario;
    Calificacion calificaciones;
    Notificacion notificaciones;
    List<Integer> tablacalificaciones; 
    
    
    Usuario(int id, int telefono, int totalcalificaciones, int promedio, int semestre, boolean nuevanotificacion,
            String nombre, String correo, String escuela, String rutafoto, List<Integer> tablacalificaciones,
            Usuario siguiente, Calificacion calificaciones, Notificacion notificaciones, Producto Inventario){
        this.id= id;
        this.telefono= telefono;
        this.semestre= semestre;
        this.totalcalificaciones= totalcalificaciones;
        this.promedio= promedio;
        this.nuevanotificacion=nuevanotificacion;
        this.nombre= nombre;
        this.correo= correo;
        this.escuela= escuela;
        this.inventario= inventario;
        this.rutafoto= rutafoto;
        this.siguiente=siguiente;
        this.calificaciones=calificaciones;
        this.notificaciones=notificaciones;
        this.tablacalificaciones= tablacalificaciones;
    }


    public void imprimir(Usuario usuario, int i){
        System.out.println("USUARIO "+i);
        System.out.println(usuario.id);
        System.out.println(usuario.telefono);
        System.out.println(usuario.semestre);
        System.out.println(usuario.nuevanotificacion);
        System.out.println(usuario.nombre);
        System.out.println(usuario.correo);
        System.out.println(usuario.escuela);
        System.out.println(usuario.rutafoto);
        System.out.println(usuario.calificaciones);
        System.out.println("----------------------");
        if (usuario.siguiente != null)
            imprimir(usuario.siguiente, i+1);
    }


    public void setPromedio() {
    }


    public void setTablaCalificaciones() {
    }


    public void setTotalCalificaciones() {
    }


    public Producto conectarProducto(Producto producto) {
        if (this.siguiente!=null)
            this.siguiente.inventario= this.siguiente.conectarProducto(producto);

        return producto.buscarxduenio((int)this.id);
    }


    public int buscarxcorreo(String correo2) {
        return 0;
    }


    public int buscarxcorreo(String correo2, String contrasenia) {
        return 0;
    }
}
