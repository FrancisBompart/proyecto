package Logica;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ManejadorPersonas extends ManejadorArchivos<Usuario>{

    @Override
    public Usuario vaciarInformacion(JSONArray objetosArray, int i) {
        if(i<objetosArray.size()){
            Object usuarioOBJ = objetosArray.get(i);
            JSONObject usuarioJSON = (JSONObject) usuarioOBJ;
            
            Usuario usuario = new Usuario(0, 0, 0, 0, 0, 
                                            false, "", "", "", "",
                                            new ArrayList<Integer>(), null, null, null,null);
                                            
            usuario.id = (Long) usuarioJSON.get("id");
            usuario.telefono = (Long)usuarioJSON.get("telefono");
            usuario.nuevanotificacion = (Boolean)usuarioJSON.get("nuevanotificacion");
            usuario.nombre = usuarioJSON.get("nombre").toString();
            usuario.correo = usuarioJSON.get("correo").toString();
            usuario.escuela = usuarioJSON.get("escuela").toString();
            usuario.semestre = (Long)usuarioJSON.get("semestre");
            usuario.rutafoto = usuarioJSON.get("rutafoto").toString();

            JSONArray calificacionesArray = (JSONArray) usuarioJSON.get("calificaciones");
            usuario.calificaciones= vaciarInformacionCalificaciones(calificacionesArray,0);

            /*JSONArray notificacionesArray = (JSONArray) usuarioJSON.get("notificaciones");
            vaciarInformacionCalificaciones(notificacionesArray,0,usuario.calificaciones); */
      
            usuario.siguiente= vaciarInformacion(objetosArray, i+1);

            return usuario;
        }
        return null;
    }

    
    public JSONArray llenarArchivos(JSONArray personasJSONArray, Usuario usuario) {
        JSONObject personaJSON = new JSONObject();
        personaJSON.put("id", usuario.id);
        personaJSON.put("telefono", usuario.telefono);
        personaJSON.put("nuevanotificacion", usuario.nuevanotificacion);
        personaJSON.put("nombre", usuario.nombre);
        personaJSON.put("correo", usuario.correo);
        personaJSON.put("escuela", usuario.escuela);
        personaJSON.put("semestre", usuario.semestre);
        personaJSON.put("rutafoto", usuario.rutafoto);

        personasJSONArray.add(personaJSON);

        if (usuario.siguiente!=null)
            llenarArchivos(personasJSONArray, usuario.siguiente);
        return personasJSONArray;
    }
    
}
