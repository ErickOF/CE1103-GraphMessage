import java.util.*;
/*
 * clase lista donde se guardan los mensajes
 */
public class Lista
{
    public static ArrayList<Mensaje> ingresados;
    // contructor de la clase donde se crea la lista
    public Lista()
    {
       ingresados = new ArrayList<Mensaje>();
    }
    /*
     * metodo que busca valores dentro de la lista
     */
    public boolean buscar(Mensaje valor){
        boolean esta = false;
        for (int i = 0; i < ingresados.size() && !esta; i++){
            if (ingresados.get(i) == valor){
                 esta = true;
                
            }
        }
        return esta;
        
    }
}