package Logica;
import java.util.List;

public class Usuario {
    public long id;
    public long telefono, semestre, promedio; 
    public int totalcalificaciones ;
    public boolean nuevanotificacion;
    public String nombre, correo, escuela, rutafoto,clave;
    public Usuario siguiente;
    public Notificacion notificaciones;
    public List<Integer> tablacalificaciones;
    public List<Producto> favoritos; 
    public List<Calificacion> calificaciones;
    
    Usuario(int id, int telefono, int totalcalificaciones, int promedio, int semestre, boolean nuevanotificacion,
            String nombre, String correo, String escuela, String rutafoto, String clave, 
            List<Integer> tablacalificaciones, List<Producto> favoritos, List<Calificacion> calificaciones,
            Usuario siguiente, Notificacion notificaciones){
        this.id= id;
        this.telefono= telefono;
        this.semestre= semestre;
        this.totalcalificaciones= totalcalificaciones;
        this.promedio= promedio;
        this.nuevanotificacion=nuevanotificacion;
        this.nombre= nombre;
        this.correo= correo;
        this.escuela= escuela;
        this.clave= clave;
        this.favoritos= favoritos;
        this.rutafoto= rutafoto;
        this.siguiente= siguiente;
        this.calificaciones=calificaciones;
        this.notificaciones=notificaciones;
        this.tablacalificaciones= tablacalificaciones;
    }

    public void imprimir(int i){
        System.out.println("USUARIO "+i);
        System.out.println(this.id);
        System.out.println(this.telefono);
        System.out.println(this.semestre);
        System.out.println(this.nuevanotificacion);
        System.out.println(this.nombre);
        System.out.println(this.correo);
        System.out.println(this.escuela);
        System.out.println(this.rutafoto);
        System.out.println(this.calificaciones);
        System.out.println("----------------------");
        if (this.siguiente != null)
            this.siguiente.imprimir(i+1);
    }

    public void setEstadisticasCalificaciones() {
        if (!this.calificaciones.isEmpty()){
            this.setPromedio();
            this.setTotalCalificaciones();
            this.crearTablaCalificaciones(this.tablacalificaciones, this.calificaciones);
        }
        if (this.siguiente!= null)
            this.siguiente.setEstadisticasCalificaciones();
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

    public Usuario buscarxCorreo(String correo, String clave) {
        if (this.correo.equals(correo)){
            if (this.clave.equals(clave))
                return this;
            else 
                return null;
        }
        else if (this.siguiente != null)
            return this.siguiente.buscarxCorreo(correo, clave);
        
        return null;
    }


    public void modificarUsuario(Long id, String rutafoto, Long telefono) {
        if (this.id == id){
            this.rutafoto = rutafoto;
            this.telefono = telefono;
        }
        else if (this.siguiente != null)
            this.siguiente.modificarUsuario(id, rutafoto, telefono);
    }

    public Usuario buscarxID(Long id) {
        if (this.id == id)
            return this;
        else if (this.siguiente != null)
            return this.siguiente.buscarxID(id);
        return null;
    }

    public void imprimirDatos() {
        System.out.println("------------------------");
        System.out.println(this.nombre);
        System.out.println(this.rutafoto);
        System.out.println(this.escuela);
        System.out.println(this.semestre);
        System.out.println(this.correo);
        System.out.println(this.telefono);
        System.out.println(this.totalcalificaciones);
        System.out.println(this.tablacalificaciones);
        System.out.println(this.promedio);
        System.out.println("------------------------");
    }

    public int buscarCalificador(Long id) {
        int i = 0;
        while (i < this.calificaciones.size()){
            if (this.calificaciones.get(i).calificador == id)
                return i;
            i++;
        }
        return -1;
    }  
}
