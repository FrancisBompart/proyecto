package Logica;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ManejadorPersonas extends ManejadorArchivos<Usuario>{

    @Override
    public Usuario vaciarInformacion(JSONArray objetosArray, int i) {
        if(i<objetosArray.size()){
            Object usuarioOBJ = objetosArray.get(i);
            JSONObject usuarioJSON = (JSONObject) usuarioOBJ;
            
            Usuario usuario= new Usuario(0, 0, 0, 0, 0, false, "", "",
                                        "", "", "", new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);add(0);}}, new ArrayList<Producto>(),new ArrayList<Calificacion>(),
                                        null, null);
                                        
            usuario.id = (Long) usuarioJSON.get("id");
            usuario.telefono = (Long) usuarioJSON.get("telefono");
            usuario.nuevanotificacion = (Boolean) usuarioJSON.get("nuevanotificacion");
            usuario.nombre = usuarioJSON.get("nombre").toString();
            usuario.correo = usuarioJSON.get("correo").toString();
            usuario.escuela = usuarioJSON.get("escuela").toString();
            usuario.semestre = (Long) usuarioJSON.get("semestre");
            usuario.rutafoto = usuarioJSON.get("rutafoto").toString();
            usuario.clave = usuarioJSON.get("clave").toString();
            
            JSONArray calificacionesArray = (JSONArray) usuarioJSON.get("calificaciones");
            if (calificacionesArray!= null)
                usuario.calificaciones= vaciarInformacionCalificaciones(calificacionesArray,0, new ArrayList<Calificacion>());
            else 
                usuario.calificaciones = new ArrayList<Calificacion>();

            JSONArray favoritosArray = (JSONArray) usuarioJSON.get("favoritos");
            if (favoritosArray!= null)
                usuario.favoritos= vaciarInformacionFavoritos(favoritosArray,new ArrayList<Producto>(),0);
            else 
                usuario.favoritos = new ArrayList<Producto>();

            /*JSONArray notificacionesArray = (JSONArray) usuarioJSON.get("notificaciones");
            vaciarInformacionCalificaciones(notificacionesArray,0,usuario.calificaciones); */
      
            usuario.siguiente= vaciarInformacion(objetosArray, i+1);

            return usuario;
        }
        return null;
    }

    
    private List<Producto> vaciarInformacionFavoritos(JSONArray favoritosArray, List<Producto> favoritosUser, int i) {
        if (i < favoritosArray.size()){
            Object productoOBJ = favoritosArray.get(i);
            JSONObject productoJSON = (JSONObject) productoOBJ;
            Producto producto = new Producto((long) 0, 0, 0, (long)0, (long)0, "", "",
                                            "", "", new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);add(0);}}, 
                                            new ArrayList<Calificacion>(), (long) 0, null, null);

            producto.id = (Long) productoJSON.get("id");
            producto.dueño = (Long) productoJSON.get("duenio");
            producto.precio =  (Long) productoJSON.get("precio");
            producto.nombre = productoJSON.get("nombre").toString();
            producto.descripcion = productoJSON.get("descripcion").toString();
            producto.rutafoto = productoJSON.get("rutafoto").toString();
            producto.categoria = productoJSON.get("categoria").toString();
            producto.cantidad = (Long) productoJSON.get("cantidad");

            JSONArray calificacionesArray = (JSONArray) productoJSON.get("calificaciones");
            if (calificacionesArray!= null)
                producto.calificaciones= vaciarInformacionCalificaciones(calificacionesArray,0, new ArrayList<Calificacion>());
            else 
                producto.calificaciones = new ArrayList<Calificacion>();

            favoritosUser.add(producto);

            return vaciarInformacionFavoritos(favoritosArray, favoritosUser, i+1);
        }
        return favoritosUser;
    }


    public JSONArray llenarArchivos(JSONArray personasJSONArray, Usuario usuario) {
        JSONObject personaJSON = new JSONObject();
        personaJSON.put("id", usuario.id);
        personaJSON.put("telefono", usuario.telefono);
        personaJSON.put("nuevanotificacion", usuario.nuevanotificacion);
        personaJSON.put("nombre", usuario.nombre);
        personaJSON.put("correo", usuario.correo);
        personaJSON.put("clave", usuario.clave);
        personaJSON.put("escuela", usuario.escuela);
        personaJSON.put("semestre", usuario.semestre);
        personaJSON.put("rutafoto", usuario.rutafoto);
        personaJSON.put("clave", usuario.clave); 
        personaJSON.put("favoritos", usuario.favoritos);
        if (usuario.calificaciones.isEmpty())
            personaJSON.put("calificaciones", new JSONArray());
        else
            personaJSON.put("calificaciones", llenarArchivosCalificaciones(new JSONArray(), usuario.calificaciones));
                
        personasJSONArray.add(personaJSON);
        if (usuario.siguiente!=null)
            llenarArchivos(personasJSONArray, usuario.siguiente);
        return personasJSONArray;
    }
    
}
