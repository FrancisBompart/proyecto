package Logica;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ManejadorProductos extends ManejadorArchivos<Producto>{

    public long ultimoID_P;

    @Override
    public Producto vaciarInformacion(JSONArray objetosArray, int i) {
        if(i<objetosArray.size()){
            Object productoOBJ = objetosArray.get(i);
            JSONObject productoJSON = (JSONObject) productoOBJ;
                
            Producto producto = new Producto((long) 0, 0, 0, (long)0, (long)0, "", "",
                                            "", "", new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);add(0);}}, new ArrayList<Calificacion>(), (long) 0, 
                                            null, null);

            producto.id = (Long) productoJSON.get("id");
            producto.dueño = (Long) productoJSON.get("duenio");
            producto.precio =  (double) productoJSON.get("precio");
            producto.nombre = productoJSON.get("nombre").toString();
            producto.descripcion = productoJSON.get("descripcion").toString();
            producto.rutafoto = productoJSON.get("rutafoto").toString();
            producto.categoria = productoJSON.get("categoria").toString();
            producto.cantidad = (Long) productoJSON.get("cantidad");
            
            JSONArray calificacionesArray = (JSONArray) productoJSON.get("calificaciones");
            if (calificacionesArray != null)
                producto.calificaciones= vaciarInformacionCalificaciones(calificacionesArray,0,new ArrayList<Calificacion>());
            else 
                producto.calificaciones= new ArrayList<Calificacion>();
            
            JSONArray inventarioArray = (JSONArray) productoJSON.get("inventario");
            if (!inventarioArray.isEmpty())
                producto.siguienteinventario= vaciarInformacionInventario(inventarioArray,0);  
            else
                producto.siguienteinventario = null;

            producto.siguientecatalogo= vaciarInformacion(objetosArray, i+1);

            if (i == objetosArray.size()-1)
                this.ultimoID_P= producto.id;  //para guardar el ultimo id de producto

            return producto;
        }
        return null;
    }
    
    //resumir funcion
    public Producto vaciarInformacionInventario(JSONArray inventarioArray, int i) {
        if(i<inventarioArray.size()){
            Object productoOBJ = inventarioArray.get(i);
            JSONObject productoJSON = (JSONObject) productoOBJ;
                
            Producto producto = new Producto((long) 0, 0, 0, (long)0, (long)0, "", "",
                                            "", "", new ArrayList<Integer>(){{add(0);add(0);add(0);add(0);add(0);}}, new ArrayList<Calificacion>(), (long) 0, 
                                            null, null);


            producto.id = (Long) productoJSON.get("id");
            producto.dueño = (Long) productoJSON.get("duenio");
            producto.precio =  (Double) productoJSON.get("precio");
            producto.nombre = productoJSON.get("nombre").toString();
            producto.descripcion = productoJSON.get("descripcion").toString();
            producto.rutafoto = productoJSON.get("rutafoto").toString();
            producto.categoria = productoJSON.get("categoria").toString();
            producto.cantidad = (Long) productoJSON.get("cantidad");

            JSONArray calificacionesArray = (JSONArray) productoJSON.get("calificaciones");

            if (calificacionesArray!= null)
                producto.calificaciones= vaciarInformacionCalificaciones(calificacionesArray,0,new ArrayList<Calificacion>());
            else 
                producto.calificaciones = new ArrayList<Calificacion>();

            producto.siguienteinventario = vaciarInformacionInventario(inventarioArray, i+1);
            return producto;
        }
        return null;
    }
    
    @Override
    public JSONArray llenarArchivos(JSONArray objetosJSONArray, Producto producto) {
        
        JSONObject productoJSON = new JSONObject();
        productoJSON.put("id", producto.id);
        productoJSON.put("duenio", producto.dueño);
        productoJSON.put("precio", producto.precio);
        productoJSON.put("nombre", producto.nombre);
        productoJSON.put("descripcion", producto.descripcion);
        productoJSON.put("categoria", producto.categoria);
        productoJSON.put("cantidad", producto.cantidad);
        productoJSON.put("rutafoto", producto.rutafoto);
        
        if (producto.calificaciones != null)       
            productoJSON.put("calificaciones", this.llenarArchivosCalificaciones(new JSONArray(), producto.calificaciones));
        else
            productoJSON.put("calificaciones", new JSONArray());

        if (producto.siguienteinventario != null)       
            productoJSON.put("inventario", this.llenarArchivosInventario(new JSONArray(), producto.siguienteinventario));
        else
            productoJSON.put("inventario", new JSONArray());
        
        objetosJSONArray.add(productoJSON);

        if (producto.siguientecatalogo!=null)
            llenarArchivos(objetosJSONArray, producto.siguientecatalogo);

        return objetosJSONArray;
    }

    private JSONArray llenarArchivosInventario(JSONArray objetosJSONArray, Producto producto) {
        JSONObject productoJSON = new JSONObject();
        productoJSON.put("id", producto.id);
        productoJSON.put("duenio", producto.dueño);
        productoJSON.put("precio", producto.precio);
        productoJSON.put("nombre", producto.nombre);
        productoJSON.put("descripcion", producto.descripcion);
        productoJSON.put("categoria", producto.categoria);
        productoJSON.put("cantidad", producto.cantidad);
        productoJSON.put("rutafoto", producto.rutafoto);
        
        if (producto.calificaciones != null)       
            productoJSON.put("calificaciones", this.llenarArchivosCalificaciones(new JSONArray(), producto.calificaciones));
        else
            productoJSON.put("calificaciones", new JSONArray());

        if (producto.siguienteinventario != null)       
            productoJSON.put("inventario", this.llenarArchivosInventario(new JSONArray(), producto.siguienteinventario));
        else
            productoJSON.put("inventario", new JSONArray());
        
        objetosJSONArray.add(productoJSON);

        if (producto.siguienteinventario!=null)
            objetosJSONArray= llenarArchivos(objetosJSONArray, producto.siguientecatalogo);

        return objetosJSONArray;
    }

}
