package Grafica;
import java.util.ArrayList;
import java.util.Scanner;

import Logica.Calificacion;
import Logica.ManejadorProductos;
import Logica.Producto;
import Logica.Usuario;

public class Inventario {
    static Scanner scanner = new Scanner(System.in);
    static int op;

    public static void menu(Producto PRODUCTO_INICIAL, ManejadorProductos manejadorProductos, String rutaProductos, String clave, Usuario usuarioactual) {
        System.out.println("1. Consultar inventario");
        System.out.println("2. Publicar producto");
        op = Integer.parseInt(scanner.nextLine());

        switch (op){
            case 1: Inventario.consultarInventario(PRODUCTO_INICIAL, usuarioactual.id, manejadorProductos, rutaProductos);
                    break;
            case 2: Inventario.publicarProducto(PRODUCTO_INICIAL,usuarioactual.id, manejadorProductos, rutaProductos, clave);
        }

    }

    private static void publicarProducto(Producto PRODUCTO_INICIAL, long id_usuarioactual,
            ManejadorProductos manejadorProductos, String ruta, String clave){
        
        Producto productonuevo = new Producto(manejadorProductos.ultimoID_P+1, 0, 0, (long)0, (long)0, "", "",
                                                "", "", new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);add(0);}}, new ArrayList<Calificacion>(), id_usuarioactual, 
                                                null, null);

        System.out.println("Nombre");
        productonuevo.nombre = scanner.nextLine();
        System.out.println("Cantidad");
        productonuevo.cantidad = Integer.parseInt(scanner.nextLine());
        System.out.println("Categoria");
        productonuevo.categoria = scanner.nextLine();
        System.out.println("Ruta foto");
        productonuevo.rutafoto = scanner.nextLine();
        System.out.println("Precio");
        productonuevo.precio = Integer.parseInt(scanner.nextLine());
        System.out.println("Descripcion");
        productonuevo.descripcion = scanner.nextLine();
        
        //actualizo la lista
        if (PRODUCTO_INICIAL == null)
                PRODUCTO_INICIAL = productonuevo;       
        else 
            PRODUCTO_INICIAL = PRODUCTO_INICIAL.aggProductoCatalogo(productonuevo, id_usuarioactual);

        //actualizo los archivos
        manejadorProductos.modificarArchivo(ruta, clave, PRODUCTO_INICIAL);
    }

    private static void consultarInventario(Producto PRODUCTO_INICIAL, Long id_usuarioactual,
            ManejadorProductos manejadorProductos, String rutaProductos) {
        
        PRODUCTO_INICIAL.buscarxduenio(id_usuarioactual).imprimirInventario();

        System.out.println("------------------------------");
        System.out.println("1. Consultar prod por id");

        if (op==1)
            Inventario.consultarProd(PRODUCTO_INICIAL);

        
        ///aqui se extiende a consultar prod del inventario paral luego pasar a modificar y eliminar
    }

    private static void consultarProd(Producto PRODUCTO_INICIAL) {
        System.out.println("------------------------");
        System.out.println("Indique id de producto a consultar");
        op = Integer.parseInt(scanner.nextLine());
        
        PRODUCTO_INICIAL.buscarxid(op).imprimirDatos();

        System.out.println("-------------------------------");
        System.out.println("1. Modificar prod");
        System.out.println("2. Eliminar prod");
        op = Integer.parseInt(scanner.nextLine());

        switch(op){
            case 1 :Inventario.modificarProd();
                    break;

            case 2: Inventario.eliminarProd();
                    break;
        }
    }

    private static void modificarProd() {
        
    }

    private static void eliminarProd() {
        

    }

    



}
