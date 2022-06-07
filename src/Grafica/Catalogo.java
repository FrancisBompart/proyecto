package Grafica;

import Logica.ManejadorPersonas;
import Logica.ManejadorProductos;
import Logica.Producto;
import Logica.Usuario;

public class Catalogo {

    public static void menu(Usuario USUARIO_INICIAL, Producto PRODUCTO_INICIAL, ManejadorPersonas manejadorPersonas,
                            ManejadorProductos manejadorProductos) {

        Catalogo.imprimirCatalogo(PRODUCTO_INICIAL);
    }

    private static void imprimirCatalogo(Producto producto) {
        if (producto!=null)
            producto.imprimirPreviuw();
        else 
            System.out.println("No existen productos en el catalogo");
    }

}
