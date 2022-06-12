package Logica;
import java.util.List;

public class Producto {
    public long id,dueño;
    public long cantidad;
    public double precio,promedio;
    public String nombre;
    public String descripcion;
    public String rutafoto;
    public String categoria;
    public int totalcalificaciones;
    public Producto siguientecatalogo;
    public Producto siguienteinventario;
    public List<Integer> tablacalificaciones;
    public List<Calificacion> calificaciones;

    public Producto(Long id, int cantidad, int totalcalificaciones, Long promedio, Long precio,
            String nombre, String descripcion, String rutafoto, String categoria, List<Integer> tablacalificaciones, List<Calificacion> calificaciones,
            Long dueño, Producto siguientecatalogo, Producto siguienteinventario){
                
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
    
    public Producto buscarxduenio(Long id){
        if (this.dueño!=id){
            if (this.siguientecatalogo != null)
                return this.siguientecatalogo.buscarxduenio(id);
            return null;
        }
        else
            return this;
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


    public Producto buscarxid(long id_prodconsulta) {
        if (this.id== id_prodconsulta)
            return this;
        else if (this.siguienteinventario != null){
            Producto producto = this.siguienteinventario.buscarxid(id_prodconsulta);
            if (producto != null)
                return producto;
            else if (this.siguientecatalogo != null)
                return this.siguientecatalogo.buscarxduenio(id);
        }
        return null;
    }


    public void imprimirDatos() {
        System.out.println("---------------");
        System.out.println(this.nombre);
        System.out.println(this.id);
        System.out.println(this.cantidad);
        System.out.println(this.categoria);
        System.out.println(this.rutafoto);
        System.out.println(this.precio);
        System.out.println(this.descripcion);
        System.out.println(this.totalcalificaciones);
        System.out.println(this.promedio);
        System.out.println(this.tablacalificaciones);
    }


    public Producto aggProductoCatalogo(Producto productonuevo, Long id_usuario) {
        if (this.dueño == id_usuario){
            if (this.siguienteinventario != null)
                this.siguienteinventario = this.siguienteinventario.aggProductoInventario(productonuevo);
            else
                this.siguienteinventario = productonuevo;
        }
        else if (this.siguientecatalogo != null)
            this.siguientecatalogo = this.siguientecatalogo.aggProductoCatalogo(productonuevo, id_usuario);
        else
            this.siguientecatalogo = productonuevo;
        return this;      
    }


    public Producto aggProductoInventario(Producto productonuevo) {
        if (this.siguienteinventario != null)
            this.siguienteinventario = this.siguienteinventario.aggProductoInventario(productonuevo);
        else
            this.siguienteinventario = productonuevo;
        return this;
    }


	public void imprimirInventario() {
        this.imprimirDatos();
        if(this.siguienteinventario != null)
            this.siguienteinventario.imprimirInventario();
	}

    public void setEstadisticasCalificaciones() {
        if (!this.calificaciones.isEmpty()){
            this.setPromedio();
            this.setTotalCalificaciones();
            this.crearTablaCalificaciones(this.tablacalificaciones, this.calificaciones);
        }
        if (this.siguienteinventario!= null)
            this.siguienteinventario.setEstadisticasCalificaciones();
        if (this.siguientecatalogo != null)
            this.siguientecatalogo.setEstadisticasCalificaciones();
    }

    public void setPromedio(){
        this.promedio = Calificacion.getPromedio(this.calificaciones);
    }

    public void setTotalCalificaciones(){
        this.totalcalificaciones= Calificacion.getTotal(this.calificaciones);
    }

    public List<Integer> crearTablaCalificaciones(List<Integer> tablacalificaciones, List<Calificacion> calificaciones){
        return Calificacion.crearTablaCalificaciones(this.tablacalificaciones, this.calificaciones);
    }

    public List<Integer> actualizarTablaCalificaciones(int estrella_vieja, int estrella_nueva){
        return Calificacion.actualizarTablaCalificaciones(this.tablacalificaciones, estrella_vieja, estrella_nueva);
    }

    public int buscarCalificador(Long id_usuarioactual) {
        int i = 0;
        while (i < this.calificaciones.size()){
            if (this.calificaciones.get(i).calificador == id_usuarioactual)
                return i;
            i++;
        }
        return -1;
    }    
}
