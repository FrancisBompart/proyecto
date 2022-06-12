package Grafica;

import java.util.Scanner;

import Logica.ManejadorPersonas;
import Logica.Usuario;

public class Perfil {
    
    static Scanner scanner = new Scanner(System.in);
    static int op;

    public static void menu(Usuario USUARIO_INICIAL, ManejadorPersonas manejadorPersonas, Long id, String ruta) {

        System.out.println("1. Consultar perfil");
        System.out.println("2. Editar perfil");

        op= Integer.parseInt(scanner.nextLine());
                        
        switch (op) {
            case 1: Perfil.consultarPerfil(USUARIO_INICIAL,id, manejadorPersonas, ruta);
                    break;
            case 2: Perfil.editarPerfil(USUARIO_INICIAL,id, manejadorPersonas, ruta);
                    break;
        }

    }

    private static void consultarPerfil(Usuario USUARIO_INICIAL, Long id, ManejadorPersonas manejadorPersonas, String ruta) {
        USUARIO_INICIAL.buscarxID(id).imprimirDatos();
        System.out.println("2. Editar perfil");
        int op= Integer.parseInt(scanner.nextLine());

        if (op==2)
            Perfil.editarPerfil(USUARIO_INICIAL, id, manejadorPersonas, ruta);
    }

    private static void editarPerfil(Usuario USUARIO_INICIAL, Long id, ManejadorPersonas manejadorPersonas, String ruta){
        System.out.println("Introduzca nueva ruta de foto");
        String rutafoto = scanner.nextLine();
        System.out.println("Introduzca nuevo telefono");
        Long telefono = Long.parseLong(scanner.nextLine()); 

        USUARIO_INICIAL.modificarUsuario(id,rutafoto,telefono);

        manejadorPersonas.modificarArchivo(ruta,"PERSONAS",USUARIO_INICIAL);;
    }

}
