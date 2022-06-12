package Logica;
import java.util.ArrayList;
import java.util.Scanner;
import Grafica.PaginaPrincipal;

public class InicioSesion {
    static Scanner scanner = new Scanner(System.in);

    public static void cargarInformacion(){
        
        Usuario USUARIO_INICIAL= new Usuario(0, 0, 0, 0, 0, false, "", "",
                                            "", "", "", new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);add(0);}}, new ArrayList<Producto>(),new ArrayList<Calificacion>(),
                                            null, null);
            
        Producto PRODUCTO_INICIAL= new Producto((long) 0, 0, 0, (long)0, (long)0, "", "",
                                                "", "", new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);add(0);}}, new ArrayList<Calificacion>(), (long) 0, 
                                                null, null);

        ManejadorPersonas manejadorPersonas = new ManejadorPersonas();
        ManejadorProductos manejadorProductos = new ManejadorProductos();

        //Vacio informacion de archivos a listas
        USUARIO_INICIAL=manejadorPersonas.existeArchivo("personas.json", "PERSONAS", USUARIO_INICIAL);
        PRODUCTO_INICIAL=manejadorProductos.existeArchivo("productos.json", "PRODUCTOS", PRODUCTO_INICIAL);

        if (PRODUCTO_INICIAL == null)
            manejadorProductos.ultimoID_P= 0;  

        //falta categorias
        
        //todas estas son interfaces
        if (USUARIO_INICIAL != null){
            USUARIO_INICIAL.setEstadisticasCalificaciones();
        }

        if (PRODUCTO_INICIAL != null){
            PRODUCTO_INICIAL.setEstadisticasCalificaciones();
        }

        Usuario usuarioactual = null;

        if (USUARIO_INICIAL != null)
            usuarioactual = InicioSesion.login(USUARIO_INICIAL);

        if (usuarioactual != null)
            PaginaPrincipal.menu(USUARIO_INICIAL, PRODUCTO_INICIAL, manejadorPersonas, manejadorProductos, usuarioactual, 
                                "personas.json", "productos.json");
    }

    private static Usuario login(Usuario USUARIO_INICIAL){
        System.out.println("correo ucab");
        String correo = scanner.nextLine();

        System.out.println("contrasenia");
        String contrasenia = scanner.nextLine();

        return USUARIO_INICIAL.buscarxCorreo(correo,contrasenia);

        // agregar cambiar contrasenia
    }    
}
