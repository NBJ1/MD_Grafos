package MDT5;

import java.util.Scanner;
import java.io.*;
import java.util.*;

public class MDT5 {

    public static class MatrizADY {

        static private int n;
        static private double[][] matriz;

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

        public void agregar(int i, int j, double w) {
            matriz[i][j] = w;
        }

        public void imprimir() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //   System.out.print(matriz[i][j] + "|");
                }
                //    System.out.println();
            }
        }

        private double minDistance(double[] dist, int[] verticeYaProcesado, int V) {
            // Initialize min value
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

// Funcion utilitaria para imprimir el arreglo de distancias calculadas
        public double dijkstra(int src, int V) {
            double retorno = 0;
            int max = 0;
            int cont = 0;
            max = (V * (V - 1)) / 2;
            double[] caminos = new double[max];

            double[] dist = new double[V];
            // dist[i] guarda la distancia mas corta desde src hasta el vertice i

            int[] verticeYaProcesado = new int[V];
            //Este arreglo tiene true si el vertice i ya fue procesado

            // Initialize all distances as INFINITE and stpSet[] as false
            for (int i = 0; i < V; i++) {
                dist[i] = Integer.MAX_VALUE;
                verticeYaProcesado[i] = 0;
            }
            // La distancia del vertice origen hacia el mismo es siempre 0
            dist[src] = 0;

            //Encuentra el camino mas corto para todos los vertices
            for (int count = 0; count < V - 1; count++) {

                //Toma el vertice con la distancia minima del cojunto de vertices aun no procesados
                //En la primera iteracion siempre se devuelve src
                double u = minDistance(dist, verticeYaProcesado, V);

                int U = (int) Math.round(u);
                // Se marca como ya procesado

                verticeYaProcesado[U] = 1;

                // Update dist value of the adjacent vertices of the picked vertex.
                for (int v = 0; v < V; v++) {

                    if (verticeYaProcesado[v] == 0 && matriz[U][v] > 0 && dist[U] != Integer.MAX_VALUE && dist[U] + matriz[U][v] < dist[v]) {

                        dist[v] = dist[U] + matriz[U][v];

                    }
                }

            }

            // se imprime el arreglo con las distancias
            //   System.out.println("Distancia del vertice desde el origen\n");
            for (int i = 0; i < V; i++) {
                if (i == V - 1) {
                    if (dist[i] > 100) {
                        //         System.out.println("Nodo no Conexo");
                    } else {
                        //   System.out.println("Resultado camino corto: " + dist[i] );
                        retorno = dist[i];
                        //    System.out.println(" ");

                    }

                }
            }

            return retorno;
        }

    }

    public static void verboso(double Pesos[], int it, int nodoS, double nodos[][], double aristas[], double Retorno, long tiempo, double costos, MatrizADY matrizA) {
        System.out.println(" ");
        System.out.println("GRAFO");
        for (int i = 0; i < Pesos.length; i++) {
            System.out.print(Pesos[i] + " ");
        }
        System.out.println(" ");
        System.out.println("ITERACIONES");
        System.out.println("it: " + it + " Nodo:" + nodoS);
        System.out.print("sp: ");
        for (int x = 0; x < nodos.length; x++) {
            for (int y = 0; y < 2; y++) {
                System.out.print(nodos[x][y]);
                if (y != nodos[x].length) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println(" ");
        System.out.print("from: ");
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
        System.out.print("TIEMPO DE EJECUCION: " + tiempo / 100 + " ms");
        System.out.println(" ");
        System.out.print("COSTO DEL ARBOL GENERADO: " + costos);
        System.out.println(" ");
    }

    public static void manual(double D, boolean mb) throws IOException {

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
                    if (D >= cont4) {

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
            long startTime = System.nanoTime();
            retorno = m.dijkstra(0, nodos);
            long endTime = System.nanoTime();
            endTime = (endTime - startTime);

            verboso(Pesos, 1, 0, Nodos, Pesos, retorno, endTime, costos, m);

        } else {

        }

    }

    public static void main(String[] args) throws IOException {

        int iterador = 0;
        boolean mb = false;
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String[] contador = a.split(" ");
        String verboso = (contador[0]);
        String manual = (contador[1]);
        int V = Integer.parseInt(contador[2]);
        String F = (contador[3]);
        int nReps = Integer.parseInt(contador[4]);
        int vInicio = Integer.parseInt(contador[5]);

        double D = Double.parseDouble(F);
        double Retorno = 0;
        //Matriz Adyacente.
        MatrizADY matriz = new MatrizADY(V);

        for (int k = 0; k < nReps; k++) {
            double costos = 0;
            iterador++;

            //Generar Coordenadas : 
            Random numAleatorio = new Random();
            // Constructor pasadole una semilla
            numAleatorio.setSeed(1235);

            // Numero decimal entre 0.0 y 1.0
            double auxRandom = numAleatorio.nextDouble();

            double X, Y = 2;
            double[][] Coordenadas = new double[V][2];
            //  System.out.print(V);
            //       System.out.println(" ");
            for (int x = 0; x < Coordenadas.length; x++) {
                for (int y = 0; y < 2; y++) {
                    Coordenadas[x][y] = (double) (Math.random());
                    Coordenadas[x][y] = Math.round(Coordenadas[x][y] * 1000);
                    Coordenadas[x][y] = Coordenadas[x][y] / 1000;
                    //      System.out.print(Coordenadas[x][y]);
                    if (y != Coordenadas[x].length) {
                        //         System.out.print("\t");
                    }

                }
                //  System.out.println("");
            }

            //Generar Aristas : 
            int AristasPosibles = 0;
            int NInicio = 0;
            double d = 0;
            int aux1 = 0;
            double aux2 = 0;
            double aux3 = 0;
            double aux4 = 0;
            double cont1 = 0;
            double cont2 = 0;
            double cont3 = 0;
            double cont4 = 0;

            AristasPosibles = (V * (V - 1)) / 2;

            double[] Pesos = new double[AristasPosibles];

            int auxej = 0;
            int auxPesos = 0;
            for (int i = 0; i < V - 1; i++) {
                for (int j = i + 1; j < V; j++) {

                    cont1 = ((Coordenadas[i][0] - Coordenadas[j][0]));

                    cont1 = (double) Math.pow(cont1, 2);
                    cont2 = ((Coordenadas[i][1] - Coordenadas[j][1]));
                    cont2 = (double) Math.pow(cont2, 2);
                    cont3 = cont1 + cont2;
                    cont4 = Math.sqrt(cont3);
                    cont4 = Math.round(cont4 * 1000);
                    cont4 = cont4 / 1000;

                    if (D >= cont4) {

                        matriz.agregar(i, j, cont4);
                        Pesos[auxPesos] = cont4;
                        costos = costos + cont4;
                        auxPesos++;
                    } else {
                        matriz.agregar(i, j, 0);
                    }

                }
            }
            for (int i = 0; i < Pesos.length; i++) {
                //    System.out.println("Pesos" + Pesos[i]);
            }

            matriz.imprimir();

            long startTime = System.nanoTime();
            matriz.dijkstra(vInicio, V);
            long endTime = System.nanoTime();
            endTime = (endTime - startTime);
            Retorno = matriz.dijkstra(vInicio, V);
            if (verboso.equals("-v")) {
                if (manual.equals("-m")) {
                    manual(D, true);
                } else {
                    verboso(Pesos, iterador, vInicio, Coordenadas, Pesos, Retorno, endTime, costos, matriz);
                }

            } else {
                if (manual.equals("-m")) {
                    manual(D, false);
                }
            }
        }
    }
}
