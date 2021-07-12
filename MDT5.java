
import java.util.Scanner;
import java.io.*;
import java.lang.reflect.Parameter;
import java.util.*;

public class MD_T5 {

    //Main 
    public static void main(String[] args) throws IOException {
        menuNorm();
    }

    //Clase para construir la Matriz de adyacencia del Grafo
    public static class MatrizADY {

        static private int n;
        static private double[][] matriz;
        /*
        Se declara la matriz en "Double" para ingresar los "pesos" de las 
        aristas y así identificar cuando hay conexión y además cuánto vale
        ésa conexión .s
        */
        public MatrizADY(int n) {
            this.n = n;
            matriz = new double[this.n][this.n];
            //se inicializa matriz en 0
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matriz[i][j] = 0;
                }
            }
        }
        //Método para agregar un "Nodo" al grafo. 
        //Se pasa como parámetro el peso w.
           
        public void agregar(int i, int j, double w) {
            matriz[i][j] = w;
            // Se agrega el "peso" de la arista a la matriz. 
        }

        //
        public void imprimir() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //   System.out.print(matriz[i][j] + "|");
                }
                //    System.out.println();
            }
        }
        
            // Método para devolver la distancia minima 
            private double DistanciaMin(double[] dist, int[] verticeYaProcesado, int V) {
                
                
                
                double min = Double.MAX_VALUE;
                double min_index = 0;
                
                for (int v = 0; v < V; v++) {
                
                if (verticeYaProcesado[v] == 0 && dist[v] <= min) {
                    min = dist[v];
                    min_index = v;
                }
            }
            return min_index;
        }

        public double dijkstra(int src, int V) {
            double valorCaminoCorto = 0;
            int max = 0;
            int cont = 0;
            max = (V * (V - 1)) / 2;
          

            // dist[i] guarda la distancia mas corta desde el nodo que se escogió hasta el vertice i
            double[] dist = new double[V];
            
            /*
            NodoVisitado 
            Si vale 1 : es porque ya pasó por el nodo
            Si vale 0 : es porque aún no es visitado.
            */
            int[] NodoVisitado = new int[V];

            //Inicializaciones :
            for (int i = 0; i < V; i++) {
                dist[i] = Integer.MAX_VALUE;
                NodoVisitado[i] = 0;
            }
            // La distancia del vertice origen hacia el mismo es siempre 0
            dist[src] = 0;

            for (int count = 0; count < V - 1; count++) {

                
                //Toma el vertice con la distancia minima del cojunto de vertices aun no procesados
                //En la primera iteracion siempre se devuelve el nodo seleccionado(src).
                double u = DistanciaMin(dist, NodoVisitado, V);

                int U = (int) Math.round(u);
                

                NodoVisitado[U] = 1;// Se marca como ya procesado

              
                for (int v = 0; v < V; v++) {
                    if (NodoVisitado[v] == 0) {
                        if (matriz[U][v] > 0) {
                            if (dist[U] != Integer.MAX_VALUE) {
                                if (dist[U] + matriz[U][v] < dist[v]) {
                                    dist[v] = dist[U] + matriz[U][v];
                                }
                            }
                        }
                    }
                }
            }

            
            for (int i = 0; i < V; i++) {
                if (i == V - 1) {
                    if (dist[i] > 100000) {
                        System.out.println("No están conectados");
                    } else {
                        
                        valorCaminoCorto = dist[i];
                    }
                }
            }
          return valorCaminoCorto;
        }
    }

      //Si escoje la opción verboso :
    public static void MenuVerboso(double Pesos[], int it, int nodoS, double nodos[][], double aristas[], double Retorno, long tiempo, double costos, MatrizADY matrizA) {
       
        System.out.println("\nGRAFO");
        for (int i = 0; i < Pesos.length; i++) {
            System.out.print(Pesos[i] + " ");
        }
       
        System.out.println("\nITERACIONES");
        System.out.println("IT : " + it + " Nodo :" + nodoS);
        System.out.print("SP: ");
        for (int x = 0; x < nodos.length; x++) {
            for (int y = 0; y < 2; y++) {
                System.out.print(nodos[x][y]);
                if (y != nodos[x].length) {
                    System.out.print(" ");
                }
            }
        }

        System.out.print("\nFrom: ");
        for (int i = 0; i < nodos.length; i++) {
            for (int j = 0; j < nodos.length; j++) {
                if (matrizA.matriz[i][j] != 0) {
                    System.out.print(i + " " + j + " ");
                } else {

                }

            }
        }
        System.out.println(" ");
        System.out.print("ARBOL DE CAMINOS: ");
        System.out.println(Retorno);
        System.out.print("TIEMPO DEl ALGORITMO: " + tiempo / 100 + " ms");
        System.out.println(" ");
        System.out.print("COSTO DEL ARBOL GENERADO: " + costos);
        System.out.println(" ");
    }

    public static void manual(double Densidad, boolean mb) throws IOException {

        if (mb == true) {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String temp[] = br.readLine().split(" ");
            int nodos = Integer.parseInt(temp[0]);
            int enlaces = 0;
            int aux4 = 0;
            double cont1 = 0;
            double cont2 = 0;
            double cont3 = 0;
            double cont4 = 0;
            double retorno = 0;
            double costos = 0;
            int auxPesos = 0;
            MatrizADY m = new MatrizADY(nodos);
            double[][] Nodos = new double[nodos][2];

            for (int x = 0; x < Nodos.length; x++) {
                String temp2[] = br.readLine().split(" ");
                for (int y = 0; y < 2; y++) {
                    if (y == 0) {
                        double aux1 = Double.parseDouble(temp2[0]);
                        Nodos[x][y] = aux1;
                        Nodos[x][y] = Math.round(Nodos[x][y] * 1000);
                        Nodos[x][y] = Nodos[x][y] / 1000;
                    }
                    if (y == 1) {
                        double aux2 = Double.parseDouble(temp2[1]);
                        Nodos[x][y] = aux2;
                        Nodos[x][y] = Math.round(Nodos[x][y] * 1000);
                        Nodos[x][y] = Nodos[x][y] / 1000;
                    }
                }
            }

            String temp3[] = br.readLine().split(" ");
            enlaces = Integer.parseInt(temp3[0]);
            aux4 = (nodos * (nodos - 1)) / 2;
            double[] Pesos = new double[aux4];
            if (aux4 >= enlaces) {
                for (int k = 0; k < enlaces; k++) {

                    String temp4[] = br.readLine().split(" ");
                    int i = Integer.parseInt(temp4[0]);
                    int j = Integer.parseInt(temp4[1]);

                    cont1 = ((Nodos[i][0] - Nodos[j][0]));

                    //       System.out.println(i + " " + j);
                    cont1 = (double) Math.pow(cont1, 2);
                    cont2 = ((Nodos[i][1] - Nodos[j][1]));
                    cont2 = (double) Math.pow(cont2, 2);
                    cont3 = cont1 + cont2;
                    cont4 = Math.sqrt(cont3);
                    cont4 = Math.round(cont4 * 1000);
                    cont4 = cont4 / 1000;
                    if (Densidad >= cont4) {

                        m.agregar(i, j, cont4);
                        Pesos[auxPesos] = cont4;
                        costos = costos + cont4;
                        auxPesos++;

                    } else {
                        m.agregar(i, j, 0);
                    }
                }
            } else {
                System.out.println("Son demasiados enlaces para los nodos");
            }
            long startTime = System.currentTimeMillis();
            retorno = m.dijkstra(0, nodos);
            long endTime = System.currentTimeMillis();
            endTime = (endTime - startTime);

            MenuVerboso(Pesos, 1, 0, Nodos, Pesos, retorno, endTime, costos, m);

        } else {

        }

    }


public static void menuNorm() throws IOException{
    
    //Variables de lectura.
    int iterador = 0;
    Scanner sc = new Scanner(System.in);
    String a = sc.nextLine();
    String[] contador = a.split(" ");
    String verboso = (contador[0]);
    String manual = (contador[1]);
    int V = Integer.parseInt(contador[2]);
    String Densidad = (contador[3]);
    int nReps = Integer.parseInt(contador[4]);
    int vInicio = Integer.parseInt(contador[5]);

    double Den = Double.parseDouble(Densidad);
    double Retorno = 0;
    
    //Matriz Adyacente. , se crea la matriz que se usará.
    MatrizADY matriz = new MatrizADY(V);
    
    //Se repita n veces.
    for (int k = 0; k < nReps; k++) {
        double costos = 0;
        iterador++;

            //Generar Coordenadas : 
            Random numAleatorio = new Random();
            // Constructor pasadole una semilla
            numAleatorio.setSeed(1535);

            // Numero decimal entre 0.0 y 1.0
          

             /*
            Coordenadas : |x y|
                          |x y| 
                          |...|
            */
            double[][] Coordenadas = new double[V][2];
          
            for (int x = 0; x < Coordenadas.length; x++) {
                for (int y = 0; y < 2; y++) {  
                    GenerarCoordenadas(Coordenadas,x,y);    
                }             
            }

            //Generar Aristas : 
            int AristasPosibles = (V * (V - 1)) / 2;
            double[] Pesos = new double[AristasPosibles];
           int auxPesos = 0;
           
          
        for (int i = 0; i < V - 1; i++) {
            for (int j = i + 1; j < V; j++) {
                double Resultado = Calc(Coordenadas, i, j);
                if (Den >= Resultado) {
                    matriz.agregar(i, j, Resultado);
                    Pesos[auxPesos] = Resultado;
                    costos = costos + Resultado;
                    auxPesos++;
                } else {
                    matriz.agregar(i, j, 0);
                }
            }
        }
        
 
            long startTime = System.nanoTime();
            
            matriz.dijkstra(vInicio, V);
            long endTime = System.nanoTime();
            endTime = (endTime - startTime);
            Retorno = matriz.dijkstra(vInicio, V);
            if (verboso.equals("-v")) {
                if (manual.equals("-m")) {
                    manual(Den, true);
                } else {
                    MenuVerboso(Pesos, iterador, vInicio, Coordenadas, Pesos, Retorno, endTime, costos, matriz);
                }

            } else {
                if (manual.equals("-m")) {
                    manual(Den, false);
                }
            }
        }
    }


//Metodos de ayuda :



    public static double GenerarCoordenadas(double Coordenadas[][], int x, int y) {

        Coordenadas[x][y] = (double) (Math.random());
        Coordenadas[x][y] = Math.round(Coordenadas[x][y] * 1000);
        Coordenadas[x][y] = Coordenadas[x][y] / 1000;

        return Coordenadas[x][y];
    }
    
    public static double Calc(double Coordenadas[][],int i, int j){
          
        double aux1 , aux2 , aux3 , aux4 = 0;

        
        
           aux1 = ((Coordenadas[i][0] - Coordenadas[j][0]));
                    aux1 = (double) Math.pow(aux1, 2);
                    aux2 = ((Coordenadas[i][1] - Coordenadas[j][1]));
                    aux2 = (double) Math.pow(aux2, 2);
                    aux3 = aux1 + aux2;
                    aux4 = Math.sqrt(aux3);
                    aux4 = Math.round(aux4 * 1000);
                    aux4 = aux4 / 1000;

                    return aux4;
    }
}
