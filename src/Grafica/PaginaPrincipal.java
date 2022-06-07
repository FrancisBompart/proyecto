package Grafica;
import java.util.Scanner;

import Logica.ManejadorPersonas;
import Logica.ManejadorProductos;
import Logica.Producto;
import Logica.Usuario;

public class PaginaPrincipal {
    static Scanner scanner = new Scanner(System.in);
    static int op;

    public static void menu (Usuario USUARIO_INICIAL, Producto PRODUCTO_INICIAL, ManejadorPersonas manejadorPersonas, ManejadorProductos manejadorProductos, int id) {

        System.out.println("1. Consultar Catalogo");
        System.out.println("2. Consultar Perfil");
        System.out.println("3. Consultar Inventario");
        System.out.println("4. Consultar Notificaciones");
        op= Integer.parseInt(scanner.nextLine());

        switch (op) {
            case 1:
                Catalogo.menu(USUARIO_INICIAL,PRODUCTO_INICIAL,manejadorPersonas,manejadorProductos);
                break;
            case 2:
                Perfil.menu(USUARIO_INICIAL,PRODUCTO_INICIAL,manejadorPersonas,manejadorProductos,id);
                break;
            case 3:
                Inventario.menu(USUARIO_INICIAL,PRODUCTO_INICIAL,manejadorPersonas,manejadorProductos);
                break;
            case 4:
                Notificaciones.menu(USUARIO_INICIAL,PRODUCTO_INICIAL,manejadorPersonas,manejadorProductos);
                break;       
        }
    }   
}
