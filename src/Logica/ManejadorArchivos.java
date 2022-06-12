package Logica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public abstract class ManejadorArchivos<E> {

    public E existeArchivo(String ruta, String clave, E OBJETO_INICIAL){
        File file = new File(ruta);
 
        if ((file.exists())&&(file.length()!=0))
            OBJETO_INICIAL = leerInformacion(ruta,clave,OBJETO_INICIAL);
        else{
            OBJETO_INICIAL = crearArchivo(ruta,clave, new JSONArray());
        }

        return OBJETO_INICIAL;
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
            JSONObject jsonObject = (JSONObject) obj; 
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
            e.printStackTrace();/////
        }
        
        return OBJETO_INICIAL;
    }

    public List<Calificacion> vaciarInformacionCalificaciones(JSONArray calificacionesArray, int i, List<Calificacion> calificaciones){
        
        if (i < calificacionesArray.size()){
            Object calificacionOBJ = calificacionesArray.get(i);
            JSONObject calificacionJSON = (JSONObject) calificacionOBJ;
            Calificacion calificacion= new Calificacion(0, 0);

            calificacion.estrellas= (Long)calificacionJSON.get("estrellas");
            calificacion.calificador= (Long)calificacionJSON.get("calificador");
            calificaciones.add(calificacion);
            calificaciones = vaciarInformacionCalificaciones(calificacionesArray, i+1, calificaciones);

            return calificaciones;
        }
        return calificaciones;
    }

    public void modificarArchivo(String ruta, String clave, E objeto){
        JSONArray objetosJSONArray = new JSONArray();
        
        objetosJSONArray= llenarArchivos(objetosJSONArray,objeto);
        
        this.crearArchivo(ruta,clave,objetosJSONArray);
    }

    public JSONArray llenarArchivosCalificaciones(JSONArray calificacionesArray, List<Calificacion> calificaciones){
        JSONObject calificacionJSON = new JSONObject();

        for (Calificacion calificacion : calificaciones) {
            calificacionJSON.put("estrellas", calificacion.estrellas);
            calificacionJSON.put("calificador", calificacion.calificador);
            calificacionesArray.add(calificacionJSON);
        }
        
        return calificacionesArray;       
    }
    
    public abstract JSONArray llenarArchivos(JSONArray objetosJSONArray, E objeto);

    public abstract E vaciarInformacion(JSONArray objetosArray, int i);

  
}
