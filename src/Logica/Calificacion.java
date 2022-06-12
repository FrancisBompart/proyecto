package Logica;
import java.util.ArrayList;
import java.util.List;

public class Calificacion {
    public long estrellas;
    long calificador;

    public Calificacion(long estrellas, long id){
        this.estrellas=estrellas;
        this.calificador= id;
    }

    public static long getPromedio(List<Calificacion> calificaciones){
        int suma = 0;
        for (Calificacion calificacion : calificaciones)
            suma+= calificacion.estrellas;
        return (suma/calificaciones.size());
    }

    public static int getTotal(List<Calificacion> calificaciones){
        return calificaciones.size();
    }

    public static List<Integer> crearTablaCalificaciones(List<Integer> tablacalificaciones, List<Calificacion> calificaciones){
        for(Calificacion calificacion: calificaciones)
            tablacalificaciones.set((int)calificacion.estrellas-1,tablacalificaciones.get((int)calificacion.estrellas-1)+1);
    
        return tablacalificaciones;
    }

    public static List<Integer> actualizarTablaCalificaciones(List<Integer> tablacalificaciones, int estrella_vieja, int estrella_nueva){
        if (estrella_vieja!=0){
            int nuevovalor1= (tablacalificaciones.get((int)tablacalificaciones.get(estrella_vieja-1)))-1;
            tablacalificaciones.set(estrella_vieja-1,nuevovalor1);
        }

        int nuevovalor2= (tablacalificaciones.get((int)tablacalificaciones.get(estrella_nueva-1)))+1;
        tablacalificaciones.set(estrella_nueva-1,nuevovalor2);
        return tablacalificaciones;      
    }

        
}
