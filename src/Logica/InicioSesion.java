package Logica;
import java.util.ArrayList;
import java.util.Scanner;
import Grafica.PaginaPrincipal;

public class InicioSesion {
    static Scanner scanner = new Scanner(System.in);

    public static void cargarInformacion(){
        Usuario USUARIO_INICIAL= new Usuario(0, 0, 0, 0, 0, false, "", "",
                                            "", "", new ArrayList<Integer>(), null, null, null, null);
            
        Producto PRODUCTO_INICIAL= new Producto(0, 0, 0, 0, 0, "", "",
                                                "", "", new ArrayList<Integer>(), 0, 
                                                null, null, null);

        ManejadorPersonas manejadorPersonas = new ManejadorPersonas();
        ManejadorProductos manejadorProductos = new ManejadorProductos();

        //Vacio informacion de archivos a listas
        manejadorPersonas.existeArchivo("personas.json", "PERSONAS", USUARIO_INICIAL);
        manejadorProductos.existeArchivo("productos.json", "PRODUCTOS", PRODUCTO_INICIAL);
        //falta categorias
        
        //calcular estadisticas de calificaciones
        USUARIO_INICIAL.setPromedio();
        USUARIO_INICIAL.setTablaCalificaciones();
        USUARIO_INICIAL.setTotalCalificaciones();

        PRODUCTO_INICIAL.setPromedio();
        PRODUCTO_INICIAL.setTablaCalificaciones();
        PRODUCTO_INICIAL.setTotalCalificaciones();

        //enlazar lista de productos a personas
        USUARIO_INICIAL.inventario= USUARIO_INICIAL.conectarProducto(PRODUCTO_INICIAL);

        int id = InicioSesion.login(USUARIO_INICIAL);

        if (id>0)
            PaginaPrincipal.menu(USUARIO_INICIAL, PRODUCTO_INICIAL, manejadorPersonas, manejadorProductos, id);
    }

    private static int login(Usuario USUARIO_INICIAL) {
        System.out.println("correo ucab");
        String correo = scanner.nextLine();

        System.out.println("contrasenia");
        String contrasenia = scanner.nextLine();

        return USUARIO_INICIAL.buscarxcorreo(correo,contrasenia);

    }


    
}
