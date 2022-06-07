package Logica;

public class Calificacion {
    long estrellas, calificador;
    Calificacion siguiente;

    Calificacion(int i, Calificacion siguiente, int j){
        this.estrellas=i;
        this.siguiente= siguiente;
        this.calificador= j;
    }
    
}
