/*
 * clase donde se crean los nodos del arbol
 */
public class Nodo
{
    public Mensaje []valores;
    public Nodo []nodo;
    public static int numValores;
    public boolean tengoHijos = false;
    public int ocupados = 0;
    public Nodo padre;
    //constructor de la clase nodo donde se inicializa dos arreglos donde uno se guardan nodos y otro donde se guardan valores
    // la capacidad de estos valores es para los nodos dos veces el grado +3 y para los valores dos veces el grado + 1
    public Nodo(){
       nodo = new Nodo [Raiz.grado * 2 + 3];
       valores = new Mensaje [Raiz.grado * 2 + 1];
    }
    
}