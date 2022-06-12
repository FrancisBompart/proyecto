package Grafica;

import java.util.Scanner;

import Logica.Calificacion;
import Logica.ManejadorPersonas;
import Logica.ManejadorProductos;
import Logica.Producto;
import Logica.Usuario;

public class Catalogo {
    static Scanner scanner = new Scanner(System.in);
    static int op;

    public static void menu(Usuario USUARIO_INICIAL, Producto PRODUCTO_INICIAL, ManejadorPersonas manejadorPersonas,
                            ManejadorProductos manejadorProductos, String rutaPersonas, String rutaProductos, Usuario usuarioactual) {

        
        System.out.println("1. Consultar catalogo");
        System.out.println("2. Consultar lista de productos favoritos");

        op= Integer.parseInt(scanner.nextLine());
                        
        switch (op) {
            case 1: Catalogo.imprimirCatalogo(usuarioactual.id, USUARIO_INICIAL, PRODUCTO_INICIAL, manejadorPersonas, manejadorProductos, rutaPersonas, rutaProductos);
                    break;
            case 2: Catalogo.consultarFavoritos(usuarioactual, USUARIO_INICIAL, PRODUCTO_INICIAL, manejadorPersonas, manejadorProductos, rutaPersonas, rutaProductos);
                    break;
        }
    }

    private static void consultarFavoritos(Usuario usuarioactual, Usuario USUARIO_INICIAL, Producto PRODUCTO_INICIAL, ManejadorPersonas manejadorPersonas,
                                            ManejadorProductos manejadorProductos, String rutaPersonas, String rutaProductos) {
        
        for (Producto producto : usuarioactual.favoritos) {
            producto.imprimirDatos();
        }

        System.out.println("----------------------");
        System.out.println("1. Consultar producto");
        op = Integer.parseInt(scanner.nextLine());
        
        if (op==1){
            System.out.println("---------------------");
            System.out.println("Indicar id de producto a consultar");
            long id_prodconsulta = Integer.parseInt(scanner.nextLine());
            Catalogo.consultarProdCatalogo(usuarioactual.id, id_prodconsulta, USUARIO_INICIAL, PRODUCTO_INICIAL, manejadorPersonas, manejadorProductos, rutaPersonas, rutaProductos);
        }
    }

    private static void imprimirCatalogo(Long id_usuarioactual, Usuario USUARIO_INICIAL, Producto PRODUCTO_INICIAL, ManejadorPersonas manejadorPersonas,
                                        ManejadorProductos manejadorProductos, String rutaPersonas, String rutaProductos ) {

        if (PRODUCTO_INICIAL!=null)
            PRODUCTO_INICIAL.imprimirPreviuw();
        else 
            System.out.println("No existen productos en el catalogo");

        System.out.println("----------------------");
        System.out.println("1. Consultar producto");
        op = Integer.parseInt(scanner.nextLine());
            
        if (op==1){
            System.out.println("---------------------");
            System.out.println("Indicar id de producto a consultar");
            long id_prodconsulta = Integer.parseInt(scanner.nextLine());
            Catalogo.consultarProdCatalogo(id_usuarioactual, id_prodconsulta, USUARIO_INICIAL, PRODUCTO_INICIAL, manejadorPersonas, manejadorProductos, rutaPersonas, rutaProductos);
        }
    }

    private static void consultarProdCatalogo(Long id_usuarioactual, long id_prodconsulta, Usuario USUARIO_INICIAL, Producto PRODUCTO_INICIAL, ManejadorPersonas manejadorPersonas,
                                                ManejadorProductos manejadorProductos, String rutaPersonas, String rutaProductos) {

        Producto productoconsulta = PRODUCTO_INICIAL.buscarxid(id_prodconsulta);
        productoconsulta.imprimirDatos();

        //if (productoconsulta.dueño!= id_usuarioactual){
            System.out.println("-------------------------");
            System.out.println("1. Calificar producto");
            System.out.println("2. Consultar perfil duenio");
            op = Integer.parseInt(scanner.nextLine());
            switch (op){
                case 1: 
                        Catalogo.calificarProd(id_usuarioactual, productoconsulta, PRODUCTO_INICIAL, manejadorProductos, rutaProductos);
                        break;
                case 2: 
                        USUARIO_INICIAL.buscarxID(productoconsulta.dueño).imprimirDatos();
                        System.out.println("-------------------------");
                        System.out.println("1. Calificar perfil");
                        op = Integer.parseInt(scanner.nextLine());
                        if (op==1)
                            Catalogo.calificarUser(id_usuarioactual, productoconsulta.dueño, USUARIO_INICIAL, manejadorPersonas, rutaPersonas);
            }
    //}
    }

    //resumir calificar como una interfaz para productos y usuarios
    private static void calificarUser(Long id_usuarioactual, long id_duenio_prodconsulta, Usuario USUARIO_INICIAL, ManejadorPersonas manejadorPersonas, String rutaPersonas) {
        Usuario duenio_prodconsulta = USUARIO_INICIAL.buscarxID(id_duenio_prodconsulta);
        int estrellas = 0;

        int index_calificador = duenio_prodconsulta.buscarCalificador(id_usuarioactual);
        if (index_calificador != -1)
            estrellas = (int) duenio_prodconsulta.calificaciones.get(index_calificador).estrellas;
        
        if (estrellas !=0)
            System.out.println("Ha calificado antes al perfil con "+estrellas+" estrellas");
        
        System.out.println("---------------------");
        System.out.println("Indique calificacion");
        op = Integer.parseInt(scanner.nextLine());

        if ((op>0)&&(op<6)){
            if (estrellas != 0)
                duenio_prodconsulta.calificaciones.set(index_calificador, new Calificacion(op, id_usuarioactual));
            else
            //si la calificacion es nueva
                duenio_prodconsulta.calificaciones.add(new Calificacion(op, id_usuarioactual));
            duenio_prodconsulta.setPromedio();
            duenio_prodconsulta.setTotalCalificaciones();
            duenio_prodconsulta.actualizarTablaCalificaciones(estrellas,op);    
        }   
        
        manejadorPersonas.modificarArchivo(rutaPersonas, "PERSONAS", USUARIO_INICIAL);
    }

    private static void calificarProd(Long id_usuarioactual, Producto prodconsulta, Producto PRODUCTO_INICIAL, ManejadorProductos manejadorProductos, String rutaProductos) {
        int index_calificador = (int) prodconsulta.buscarCalificador(id_usuarioactual);
        int estrellas= 0;

        if (index_calificador != -1)
            estrellas = (int) prodconsulta.calificaciones.get(index_calificador).estrellas;
            
        if (estrellas !=0)
            System.out.println("Ha calificado antes al perfil con "+estrellas+" estrellas");
        
        System.out.println("---------------------");
        System.out.println("Indique calificacion");
        op = Integer.parseInt(scanner.nextLine());

        if ((op>0)&&(op<6)){
            if (estrellas != 0)
                prodconsulta.calificaciones.set(index_calificador, new Calificacion(op, id_usuarioactual));
            else
            //si la calificacion es nueva
                prodconsulta.calificaciones.add(new Calificacion(op, id_usuarioactual));
            prodconsulta.setPromedio();
            prodconsulta.setTotalCalificaciones();
            prodconsulta.actualizarTablaCalificaciones(estrellas,op);    
        }
        manejadorProductos.modificarArchivo(rutaProductos, "PRODUCTOS", PRODUCTO_INICIAL);
    
    }

}
