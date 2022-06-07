package Logica;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ManejadorProductos extends ManejadorArchivos<Producto>{

    @Override
    public Producto vaciarInformacion(JSONArray objetosArray, int i) {
        if(i<objetosArray.size()){
            Object productoOBJ = objetosArray.get(i);
            JSONObject productoJSON = (JSONObject) productoOBJ;
                
            Producto producto = new Producto(0, 0, 0, 0, 0, "", "",
                                                "","", new ArrayList<Integer>(), 0, 
                                                null, null, null);

            producto.id = (Long) productoJSON.get("id");
            producto.dueño = (Long) productoJSON.get("dueño");
            producto.precio =  (Float) productoJSON.get("precio");
            producto.nombre = productoJSON.get("nombre").toString();
            producto.descripcion = productoJSON.get("descripcion").toString();
            producto.rutafoto = productoJSON.get("rutafoto").toString();
            producto.categoria = productoJSON.get("categoria").toString();
            producto.cantidad = (Long) productoJSON.get("cantidad");
            
            JSONArray calificacionesArray = (JSONArray) productoJSON.get("calificaciones");
            producto.calificaciones= vaciarInformacionCalificaciones(calificacionesArray,0);
            
            JSONArray inventarioArray = (JSONArray) productoJSON.get("inventario");
            producto.siguienteinventario= vaciarInformacionInventario(inventarioArray,0);  
            

            producto.siguientecatalogo= vaciarInformacion(objetosArray, i+1);
            return producto;
        }
        return null;
    }
    
    //resumir funcion
    public Producto vaciarInformacionInventario(JSONArray inventarioArray, int i) {
        if(i<inventarioArray.size()){
            Object productoOBJ = inventarioArray.get(i);
            JSONObject productoJSON = (JSONObject) productoOBJ;
                
            Producto producto = new Producto(0, 0, 0, 0, 0, "", "",
                                                "","", new ArrayList<Integer>(), 0, 
                                                null, null, null);

            producto.id = (Long) productoJSON.get("id");
            producto.dueño = (Long) productoJSON.get("dueño");
            producto.precio =  (Float) productoJSON.get("precio");
            producto.nombre = productoJSON.get("nombre").toString();
            producto.descripcion = productoJSON.get("descripcion").toString();
            producto.rutafoto = productoJSON.get("rutafoto").toString();
            producto.categoria = productoJSON.get("categoria").toString();
            producto.cantidad = (Long) productoJSON.get("cantidad");
            
            producto.siguienteinventario = vaciarInformacionInventario(inventarioArray, i+1);
            return producto;
        }
        return null;
    }
    
    @Override
    public JSONArray llenarArchivos(JSONArray objetosJSONArray, Producto producto) {
        
        JSONObject productoJSON = new JSONObject();
        productoJSON.put("id", producto.id);
        productoJSON.put("telefono", producto.dueño);
        productoJSON.put("nuevanotificacion", producto.precio);
        productoJSON.put("nombre", producto.nombre);
        productoJSON.put("correo", producto.descripcion);
        productoJSON.put("escuela", producto.categoria);
        productoJSON.put("semestre", producto.cantidad);
        productoJSON.put("rutafoto", producto.rutafoto);

        objetosJSONArray.add(productoJSON);

        if (producto.siguienteinventario!=null)
            llenarArchivos(objetosJSONArray, producto.siguienteinventario);
        if (producto.siguientecatalogo!=null)
            llenarArchivos(objetosJSONArray, producto.siguientecatalogo);

        return objetosJSONArray;

    }

}
