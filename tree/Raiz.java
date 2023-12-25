import java.util.*;
/*
 * clase donde se creara el arbol
 */
public class Raiz{
	// grado de el arbolsiempre va a ser 2
    public static int grado = 2;
    // primer nodo de el arbol
    public Nodo primerNodo;
    public static boolean esRaiz = true;
    public static int nivel = 1;
    public static int imprimir = 1;
    public static String arbol = "";
    int contador;
    /*
     * constructor de la clase raiz donde se inicializa el primer nodo y el contador para insertar los nodos en el arbol
     */
   public Raiz(){
        primerNodo = new Nodo();
        this.contador=0;
    }
   /*
    * metodo de insertar al el arbol donde entra como paramentro un string que representa el mensaje, y con este mensaje crea una instancia de la clase mensaje donde donde se guarda el mensaje y un valor numerico para guardarlo en el arbol b
    */
    public void insertar (String valor) {
    	// instancia creada que sera guardada en el arbol b
    	Mensaje temp = new Mensaje(this.contador,valor);
    	//se aumenta el contador global de los valores numericos que entra a el arbol
    	this.contador++;
    	// en caso de que el primer nodo no tenga hijos
        if (primerNodo.tengoHijos==false) {
            int j = 0;
            // se recorre el arreglo que contiene los valores del nodo hasta encontrar un campo para insertar
            for (int i = 0; i<primerNodo.valores.length; i++) {
                if (primerNodo.valores[i] == null) {
                    primerNodo.valores[i] = temp;
                    Lista.ingresados.add(temp);
                    j = i;
                    ordenar(primerNodo.valores);
                    break;
                }
            }
            // si j llega a ser el doble del el nodo hay que dividirlo
            if (j == 2*grado) {
                split(primerNodo);
            }
            // en caso de que el nodo tenga hijos
        } else {
            setTengoHijos(primerNodo);
            ingresarEnHijos(primerNodo, temp);
            
        }
    }
    /*
     * metodo que ordena  un arreglo mediante 
     */
      public void ordenar(Mensaje arr[]){
    	  // se inicializa una variable longitud que va a se r lacantidad de el ementos que tenga el nodo
      int longitud = 0;
       for(int i = 0; i < arr.length; i++){
           if(arr[i] != null){
               longitud++;
            }else{
            break;
            }
        }
       	// mientras ord sea distinto de la longitud
             for(int ord = 0; ord < longitud; ord++){
            	
            for(int ord1 = 0; ord1 < longitud - 1 ; ord1++){
            	// si l valor de la ord1 es mayor que ord1 + 1 los intercambia de posicion
             if(arr[ord1].valor > arr[ord1 + 1].valor){
                        Mensaje tmp = arr[ord1];
                        arr[ord1] = arr[ord1+1];
                        arr[ord1+1] = tmp;
                      
              }
           }
       }
    }
      /*
       * metodo que daclar si los nodos tienen hijos
       */
    public void setTengoHijos (Nodo nodo) {
    	// si el nodo que entra es igual a primer nodo se revisa para ver si tiene hijos
        if (nodo == primerNodo) {
            if (primerNodo.nodo[0]!= null) {
                primerNodo.tengoHijos = true;
            } 
        }
        // en caso de que el arreglo que contienen los nodos sea distintos a null quiere decir que tienen hijos
        for (int i = 0; i<nodo.nodo.length; i++) {
            if (nodo.nodo[i] != null) {
                nodo.tengoHijos = true;
                setTengoHijos(nodo.nodo[i]);
            }
        }
    }
    
    /*
     *   metodo que inserta hijos a los nodos
     */
    public void ingresarEnHijos(Nodo conHijos, Mensaje valor) {
        boolean entro = false;
        // en caso de con hijos no tenga hijos
        if(conHijos != null && !conHijos.tengoHijos){
        	
            ubicarValorEnArreglo(conHijos, valor);
            entro = true;
        }
        // mientras i sea menor que el dos veces el grado mas uno y en el nodo conHijos sea distinto de nulo
        for(int i = 0; conHijos != null && i < 2*grado+1   && !entro; i++){
        	//si la posicion i de el nodo conHijos es distinta a nulo
        	if(conHijos.valores[i]!= null){
        		//si el valor numerico del mensaje que vamos a ingresar es menor a el valor numerico del nodo con hijos en la posicion i se ingres el valor en ese nodo
            if(valor.valor < conHijos.valores[i].valor || conHijos.valores[i] == null){
                entro = true;
                ingresarEnHijos(conHijos.nodo[i], valor);
                i = 2*grado;
            }
        	}
        	// si el valor es igual a nullnse ingresa el valor en esa posicion
        	else if(conHijos.valores[i] == null){
        	    entro = true;
                ingresarEnHijos(conHijos.nodo[i], valor);
                i = 2*grado;	
        	}
        } 
    }
    /*
     * metodo que ubica los valores de ntro de los nodos 
     */
    public void ubicarValorEnArreglo(Nodo nodoA, Mensaje valor){
    	// se inicializa un contador
        int cont = 0;
        // mientras el contador sea menor que dos veces el grado, si lo que contiene la posicion en el arreglo es nulo mete elvalor ahi
        while(cont <= 2*grado){
            if (nodoA.valores[cont]== null) { 
                nodoA.valores[cont]=valor;
                ordenar(nodoA.valores);
                Lista.ingresados.add(valor);
                // si el contador es mayor que dos veces el grado se debe dividir el nodo
                if (cont == 2*grado) {
                    split(nodoA);
                }
                break;
            }
            cont++;
        }
    }
    /*
     *Metodo que ordena los nodos segun su valor numerico dentro de los nodos
     */
     public void ordenarNodos(Nodo aOrdenar){
       int i,j;
       i = 0;
       Nodo tmp;
       //mientras i sea distinta a dos veces el grado mas 3 yel nodo es distinto a null
       while(i < 2 * grado + 3 && aOrdenar.nodo[i] != null){
           j = 0;
           // mientras j sea distinta de dos veces el grado mas dos vecesylos nodos del nodo aordenar sean distintos de nulo
           while(j < 2 * grado +2  && aOrdenar.nodo[j] != null && aOrdenar.nodo[j+1] != null){
        	   // si el primer valor del nodo en la posicion j es mayor a el primer valor del nodo en la posicion j + 1, se intercambian los nodos
               if(aOrdenar.nodo[j].valores[0].valor > aOrdenar.nodo[j+1].valores[0].valor ){
                   tmp = aOrdenar.nodo[j];
                   aOrdenar.nodo[j] = aOrdenar.nodo[j+1];
                   aOrdenar.nodo[j+1] = tmp;
                }
                j++;
            }
            i++;
        }   
    }
     /*
      * metodo que divide los nodos y sube un valor ya que se sobre pasa la cantidad de valores que contiene el nodo
      */
    public void split (Nodo nodo) {
       
        Nodo hijoIzq = new Nodo();
        Nodo hijoDer = new Nodo();
        
      //si tiene hijos antes de hacer el split entonces
        if (nodo.nodo[0]!=null) { 
        	// los separa los hijos del nodo en hijoIzq e hijoDer
            for (int i = 0; i <grado+1; i++) { 
                hijoIzq.nodo[i] = nodo.nodo[i];
                hijoIzq.nodo[i].padre = hijoIzq;
                nodo.nodo[i] = null;
                hijoDer.nodo[i] = nodo.nodo[grado+1+i];
                hijoDer.nodo[i].padre = hijoDer;
                nodo.nodo[grado+1+i] = null;
            }
        }
      //guarda los valores en hijoIzq e hijoDer
        for (int i =0; i<grado; i++){ 
            hijoIzq.valores[i] = nodo.valores[i];
            nodo.valores[i] = null;
            hijoDer.valores[i] = nodo.valores[grado+1+i];
            nodo.valores[grado+1+i] = null;
        }
        nodo.valores[0] = nodo.valores[grado];
      //queda en nodo solo el valor que "subio"
        nodo.valores[grado] = null; 
      //asigna a nodo el nuevo hijo izquierdo
        nodo.nodo[0] = hijoIzq;
     // se hizo en primer ciclo
        nodo.nodo[0].padre = nodo; 
        // asigna a nodo el nuevo hijo derecho 
        nodo.nodo[1] = hijoDer;
     // se hizo en el primer ciclo  
        nodo.nodo[1].padre = nodo;       
        setTengoHijos(primerNodo);
        ordenarNodos(nodo);
        
     // luego del split y asignar los hijos, subir el valor al padre
        if (nodo.padre!=null) { 
            boolean subido = false;
            // se recorre el nodo padre
            for (int i = 0; i<nodo.padre.valores.length && subido==false; i++) {
            	// si el valor i del nodo padre es igual a nulo
                if (nodo.padre.valores[i] == null) {
                	//se ubica el valor a subir ahi
                    nodo.padre.valores[i] = nodo.valores[0];
                    subido = true;
                    nodo.valores[0] = null;
                    ordenar(nodo.padre.valores);
                }
            }
            //busca donde estan lo hijos en el arreglo
            int posHijos = 0;
            for (int i = 0; i<2*grado+3 ; i++) {
                if (nodo.padre.nodo[i]!=null) {
                    posHijos++;
                } else {
                    break;
                }
            }
            // ubica el valor de los hijos en los primeros valores del nodo hijo y reasigna el valor del padre
            nodo.padre.nodo[posHijos] = nodo.nodo[0];
            nodo.padre.nodo[posHijos+1] = nodo.nodo[1];
            nodo.padre.nodo[posHijos].padre = nodo.padre;
            nodo.padre.nodo[posHijos+1].padre = nodo.padre;
            
            // busca la posicon donde el nodo padre en su arreglo de de nodos en la primera posicion de los valores es igual nodo. valores
            int aqui = 0;
            for (int i =0; i<2*grado+3 && nodo.padre.nodo[i]!=null; i++) {
                if (nodo.padre.nodo[i].valores[0] == nodo.valores[0]) {
                    aqui = i;
                    break;
                }
            }
            Nodo papa = nodo.padre;
            nodo = null;
            int j = aqui;
            while (j<2*grado+2 && papa.nodo[j]!=null && papa.nodo[j+1]!=null) {
                papa.nodo[j] = papa.nodo[j+1];
                j++;
            }
            papa.nodo[j] = null;
            ordenar(papa.valores);
            ordenarNodos(papa);
            if (papa.valores[2*grado]!=null) {
                split(papa);
            }
        }
    }
     /*
      * metodo que busca un valor numerico dentro del arbol
      */
    public String buscar(int valor){
        boolean esta = false;
        //mientras el valor de i sea diferente de del largo del arreglo
        for(int i = 0; i < Lista.ingresados.size() && !esta; i++){
        	//si el valor numerico que buscamos coincide con la posicion del arreglo que bucabamos, retorna el texto de este valor
            if(Lista.ingresados.get(i).valor == valor){
                esta = true;
                return Lista.ingresados.get(i).texto;
            }
        }
        return null;
    }
    /*
     * metodo queretorna el arbol en un string
     */
    public String recorrer(Nodo nodo) {
         arbol += "\n";
         // mientras i sea distinto de el dos veces el grado mas 1 
        for (int i =0; i<2*grado+1; i++) {
        	//si el primer nodo del nodo es distinto de nulo y el valor de i es 0 se aumenta en uno el nivel y se suma uno a imprimir
            if (nodo.nodo[i] != null) {
                if (i == 0) {
                    nivel++;
                    imprimir = 1;
                // en caso de que valor de i no sea 0  se aumenta solo el valor de imprimir
                } else {
                    imprimir ++;
                }
                //se repite el proceso con los nodos de los nodos
                recorrer(nodo.nodo[i]);
            }
            // ademas se guardan todos los valores del nodo en el string arbol
            arbol += "[ ";
            for (int j = 0; nodo.nodo[i]!=null && j<nodo.nodo[i].valores.length; j++) {
                if (nodo.nodo[i].valores[j] != null) {
                    arbol += nodo.nodo[i].valores[j].valor + ", ";
                }
            }
            arbol += " ]";
        }
        if (arbol.length() > (2*grado+3)*4) {
            //System.out.println (arbol);
            return arbol;
        }
        return arbol;
    }
    /*
     * metodo que llama a recorrer para crear el arbol
     */
    public String llamarRecorrer() {
        String mostrar = recorrer(primerNodo);
        nivel = 1;
        imprimir = 1;
        return arbol;
    }
    /*
     * metodo que determina si un string es un numero
     */
     public boolean esNumero(String s){
        try{
           Integer.parseInt(s);
           return true;
        }catch(NumberFormatException e){
            return false;
        }
        }
     
     /*
      * metodo que busca por contenido en el cual le entra una palabra y revisa el arbol para ver cuales mensajes contienen esa palabra
      */
     public ArrayList<Mensaje> buscarPorContenido(String palabra){
    	 // se crea una nueva lista
    	 ArrayList<Mensaje> listaTemp = new ArrayList<Mensaje>();
    	 // se recorre el arreglo donde estan los mensajes
    	 for(int i=0; i < Lista.ingresados.size(); i++){
    		 String Mensaje = Lista.ingresados.get(i).texto;
    		 String temp = "";
    		 String letraActual;
    		 String vacio = " ";
    		 // se recocore el mensaje letra por letra y cuando se forma un palabra lo compara con la palabra que el usuario digito
    		// en caso de estar la palabra buscada en el mensaje, este mensaje se guarda en un arrglo que sera retornado
    		 for(int j=0; j < Mensaje.length(); j++){
    			 System.out.println(temp);
    			 letraActual = Character.toString(Mensaje.charAt(j));
    			 if(String.valueOf(vacio).equals(letraActual)){
    				 temp = ""; 
    			 }
    			 
    			 else{
    				 temp += letraActual;
    			 }
    			 if(String.valueOf(palabra).equals(temp)){
    				 listaTemp.add(Lista.ingresados.get(i));	 
    			 }
    	 }
     }
       return listaTemp; 
}
}