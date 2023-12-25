import java.util.*;
import javax.swing.*; 
public class Interfaz2 extends javax.swing.JFrame {
    Lista datos = new Lista();
    Raiz raiz1;
    boolean ya = true;
    boolean existe = true;
    Raiz raizAux = new Raiz();
    public Interfaz2() {
        initComponents();
    }
/*
 * metodo donde se inicializan todos los componentes
 */
    private void initComponents() {
    	this.raiz1 = new Raiz();
        frameArbol = new javax.swing.JFrame();
        listaDeMensajes = new javax.swing.JFrame();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        frameArbol.setMinimumSize(new java.awt.Dimension(800, 600));
        listaDeMensajes.setMinimumSize(new java.awt.Dimension(800, 600));
        jLabel4.setText("Asi se ve el arbol b de momento ");
        jLabel5.setText("La lista de mesajes es");
        jTextArea2.setEditable(false);
        jTextArea1.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        jScrollPane2.setViewportView(jTextArea2);


        javax.swing.GroupLayout ListaDeMensajesLayout = new javax.swing.GroupLayout(listaDeMensajes.getContentPane());
        listaDeMensajes.getContentPane().setLayout(ListaDeMensajesLayout);
        ListaDeMensajesLayout.setHorizontalGroup(
        		ListaDeMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ListaDeMensajesLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel5)
                .addContainerGap(115, Short.MAX_VALUE))
            .addGroup(ListaDeMensajesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        ListaDeMensajesLayout.setVerticalGroup(
        		ListaDeMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ListaDeMensajesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Consolas", 0, 14)); 
        setResizable(false);
        

        javax.swing.GroupLayout frameArbolLayout = new javax.swing.GroupLayout(frameArbol.getContentPane());
        frameArbol.getContentPane().setLayout(frameArbolLayout);
        frameArbolLayout.setHorizontalGroup(
            frameArbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameArbolLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel4)
                .addContainerGap(115, Short.MAX_VALUE))
            .addGroup(frameArbolLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        frameArbolLayout.setVerticalGroup(
            frameArbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameArbolLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));

        jTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jTextField1.setToolTipText("Numero entero");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Ver arbol");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField3.setForeground(new java.awt.Color(153, 153, 153));
        jTextField3.setToolTipText("Numero entero");
        jTextField3.setPreferredSize(new java.awt.Dimension(73, 20));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton3.setText("Buscar por contenido");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField2.setForeground(new java.awt.Color(153, 153, 153));
        jTextField2.setToolTipText("Numero entero");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton2.setText("Ver conteido");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(jTextField2))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        pack();
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
   
    }
    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {
    
    }
    /*
     * metodo en el que un usuario digita una palabra y le devuelve una todos los mensajes con esta palabra
     */
private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
    	String temp = "";
    	 if(!jTextField3.getText().equals("")){
    		 if(ya){
    			 System.out.println((jTextField3.getText()));
    			 ArrayList<Mensaje> lista = raiz1.buscarPorContenido(jTextField3.getText());
    			 int x = 0;
    			 while(x != lista.size()){
    				 temp += lista.get(x).texto;
    				 temp += "\n";
    				 x++;
    			 }
    			 System.out.println(lista.size()); 
    			 jTextArea2.setText(temp);
    			 listaDeMensajes.setVisible(true);
    			 temp ="";
    		 }
    	 }
    	
    }
/*
 * metodo que toma un valor que digita el usuario ylos busca dentro del arbol, luego muestra un mensaje con el
 */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        if(!jTextField2.getText().equals("")){
        boolean numero = raizAux.esNumero(jTextField2.getText());
        if(numero){
        if(ya){
        String esta = raiz1.buscar(Integer.parseInt(jTextField2.getText()));
        if(esta != null){
            JOptionPane.showMessageDialog(this, esta ,
               "El Dato del Nodo!!!", JOptionPane.WARNING_MESSAGE);
        }
        
      }
      
        }
      }
      jTextField2.setText("");  
    }

   /*
    * metodo que muestra el arbol completo
    */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    	  raiz1.arbol = "";
          if(ya){
          String raiz = "raiz [ ";
          for(int i = 0; i < raiz1.primerNodo.valores.length && raiz1.primerNodo.valores[i] != null; i++){
              raiz += raiz1.primerNodo.valores[i].valor + ", ";
          }
          raiz += " ]\n";
          raiz += raiz1.llamarRecorrer();
          jTextArea1.setText(raiz);
         
          }
          frameArbol.setVisible(true);
      }

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
  
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz2().setVisible(true);
            }
        });
    }
    //declaracion de todas las varibles
    private javax.swing.JFrame frameArbol;
    private javax.swing.JFrame listaDeMensajes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
}