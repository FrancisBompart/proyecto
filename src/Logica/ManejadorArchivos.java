package Logica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public abstract class ManejadorArchivos<E> {

    public E existeArchivo(String ruta, String clave, E OBJETO_INICIAL){
        File file = new File(ruta);
        
        if (file.exists())
            OBJETO_INICIAL = leerInformacion(ruta,clave,OBJETO_INICIAL);
        else{
            OBJETO_INICIAL = crearArchivo(ruta,clave, new JSONArray());
        }

        return OBJETO_INICIAL;
    }

    public Calificacion vaciarInformacionCalificaciones(JSONArray calificacionesArray, int index){
        
        if (index<calificacionesArray.size()){
            Object calificacionOBJ = calificacionesArray.get(index);
            JSONObject calificacionJSON = (JSONObject) calificacionOBJ;
            Calificacion calificacion= new Calificacion(0, null, 0);

            calificacion.estrellas= (Long)calificacionJSON.get("estrellas");
            calificacion.calificador= (Long)calificacionJSON.get("calificador");
            calificacion.siguiente= vaciarInformacionCalificaciones(calificacionesArray, index+1);

            return calificacion;
        }
        return null;
    }

    public E crearArchivo(String ruta, String clave, JSONArray objetosArray) {
        JSONObject objetoJSON = new JSONObject();  

        objetoJSON.put(clave, objetosArray);    
        
        try {
			FileWriter file = new FileWriter(ruta);
			file.write(objetoJSON.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			//manejar error
		}
        return null;
    }

    public E leerInformacion(String ruta, String clave, E OBJETO_INICIAL){
        JSONParser parser = new JSONParser(); //convierte la informacion del json a obj
        try{
            Object obj = parser.parse(new FileReader(ruta)); 
            JSONObject jsonObject = (JSONObject)obj; 
            JSONArray objetosArray= (JSONArray) jsonObject.get(clave); 

            if (objetosArray!=null)
                OBJETO_INICIAL= vaciarInformacion(objetosArray,0);
            else OBJETO_INICIAL= crearArchivo(ruta,clave,new JSONArray());

        }
        catch(FileNotFoundException ex){
            System.out.println("Error al leer archivo");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        
        return OBJETO_INICIAL;
    }

    public void modificarArchivo(String ruta, E objeto){
        JSONArray objetosJSONArray = new JSONArray();
        
        objetosJSONArray= llenarArchivos(objetosJSONArray,objeto);
        
        this.crearArchivo(ruta,"PERSONAS",objetosJSONArray);
    }
    
    public abstract JSONArray llenarArchivos(JSONArray objetosJSONArray, E objeto);

    public abstract E vaciarInformacion(JSONArray objetosArray, int i);

  
}
