/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intentoia;

import javax.swing.JFrame; //importa biblioteca JFrame
import javax.swing.*; //importa biblioteca JButton
import java.awt.GridLayout; //importa biblioteca GridLayout
import javax.swing.JOptionPane;

/**
 *
 * @author Johan
 */
public class IntentoIA {

    public static int costos[] = {0, 0, 0};
    public static int copiaCosto[] = {0, 0, 0};

    public static int posicionTijera = 0;
    public static int tijeraEspera = 0;
    public static boolean tijeraAtaca = false;
    public static int copiaposicionTijera = 0;
    public static int copiatijeraEspera = 0;
    public static boolean copiatijeraAtaca = false;

    public static int posicionPiedra = 0;
    public static int piedraEspera = 0;
    public static boolean piedraAtaca = false;
    public static int copiaposicionPiedra = 0;
    public static int copiapiedraEspera = 0;
    public static boolean copiapiedraAtaca = false;

    public static int posicionPapel = 0;
    public static int papelEspera = 0;
    public static boolean papelAtaca = false;
    public static int copiaposicionPapel = 0;
    public static int copiapapelEspera = 0;
    public static boolean copiapapelAtaca = false;

    public static int costosMemoriza[] = {0, 0, 0};
    public static int costosEnRama[] = {0, 0, 0};

    public static int[][] memoriza;
    public static int[][] carrera;
    public static boolean acabo = false;

    public static void mostrarMatriz(int m, int[][] matriz) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matriz[j][i] + " ");
            }
            System.out.println();
        }
    }
    
    public static void copiaVector(int vector1[], int vector2[]){
        for(int i = 0; i< vector1.length; i++){
            vector1[i] = vector2[i];
        }
    }

    public static void igualaMatriz(int[][] matriz1, int[][] matriz2, int n) {
        for (int i = 0; i < 3; i++) {
            for (int x = 0; x < n; x++) {
                matriz1[x][i] = matriz2[x][i];
            }
        }
    }

    public static int[][] pintaMatriz(int length) { //constructor
        //frame.setLayout(new GridLayout(3,length)); 
        carrera = new int[length][3];
        memoriza = new int[length][3];

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < length; x++) {
                carrera[x][y] = 0;
                memoriza[x][y] = 0;
                //frame.add(carrera[x][y]); //añade el botón a a grilla
            }
        }
        return carrera;
    }

    public static void calculaCosto(int robot, int enemigo1, int enemigo2, int enemigo3, int matrizCostos[], int costosActual[]) {
        if (robot == 1) { //COSTOS PARA CUANDO ES TIJERA
            if (enemigo1 == 4 && enemigo2 == 4 && enemigo3 == 4) {
                matrizCostos[0] += 3;
                costosActual[0] = 3;
            } else if (enemigo1 == 4 && enemigo2 != 4 && enemigo3 != 4) {
                matrizCostos[0] += 5;
                costosActual[0] = 5;
            } else if (enemigo1 != 4 && enemigo2 == 4 && enemigo3 != 4) {
                matrizCostos[0] += 5;
                costosActual[0] = 5;
            } else if (enemigo1 != 4 && enemigo2 != 4 && enemigo3 == 4) {
                matrizCostos[0] += 5;
                costosActual[0] = 5;
            } else if (enemigo1 == 4 && enemigo2 == 4 && enemigo3 != 4) {
                matrizCostos[0] += 4;
                costosActual[0] = 4;
            } else if (enemigo1 == 4 && enemigo2 != 4 && enemigo3 == 4) {
                matrizCostos[0] += 4;
                costosActual[0] = 4;
            } else if (enemigo1 != 4 && enemigo2 == 4 && enemigo3 == 4) {
                matrizCostos[0] += 4;
                costosActual[0] = 4;
            } else if (enemigo1 != 4 && enemigo2 != 4 && enemigo3 != 4) {
                matrizCostos[0] += 6;
                costosActual[0] = 6;
            }
        } else if (robot == 2) { //COSTOS PARA CUANDO ES PIEDRA
            if (enemigo1 == 5 && enemigo2 == 5 && enemigo3 == 5) {
                matrizCostos[1] += 3;
                costosActual[1] = 3;
            } else if (enemigo1 == 5 && enemigo2 != 5 && enemigo3 != 5) {
                matrizCostos[1] += 5;
                costosActual[1] = 5;
            } else if (enemigo1 != 5 && enemigo2 == 5 && enemigo3 != 5) {
                matrizCostos[1] += 5;
                costosActual[1] = 5;
            } else if (enemigo1 != 5 && enemigo2 != 5 && enemigo3 == 5) {
                matrizCostos[1] += 5;
                costosActual[1] = 5;
            } else if (enemigo1 == 5 && enemigo2 == 5 && enemigo3 != 5) {
                matrizCostos[1] += 4;
                costosActual[1] = 4;
            } else if (enemigo1 == 5 && enemigo2 != 5 && enemigo3 == 5) {
                matrizCostos[1] += 4;
                costosActual[1] = 4;
            } else if (enemigo1 != 5 && enemigo2 == 5 && enemigo3 == 5) {
                matrizCostos[1] += 4;
                costosActual[1] = 4;
            } else if (enemigo1 != 5 && enemigo2 != 5 && enemigo3 != 5) {
                matrizCostos[1] += 6;
                costosActual[1] = 6;
            }
        } else if (robot == 3) { //COSTOS PARA CUANDO ES PAPEL
            if (enemigo1 == 6 && enemigo2 == 6 && enemigo3 == 6) {
                matrizCostos[2] += 3;
                costosActual[2] = 3;
            } else if (enemigo1 != 6 && enemigo2 != 6 && enemigo3 != 6) {
                matrizCostos[2] += 6;
                costosActual[2] = 6;
            } else if (enemigo1 == 6 && enemigo2 != 6 && enemigo3 != 6) {
                matrizCostos[2] += 5;
                costosActual[2] = 5;
            } else if (enemigo1 != 6 && enemigo2 == 6 && enemigo3 != 6) {
                matrizCostos[2] += 5;
                costosActual[2] = 5;
            } else if (enemigo1 != 6 && enemigo2 != 6 && enemigo3 == 6) {
                matrizCostos[2] += 5;
                costosActual[2] = 5;
            } else if (enemigo1 == 6 && enemigo2 == 6 && enemigo3 != 6) {
                matrizCostos[2] += 4;
                costosActual[2] = 4;
            } else if (enemigo1 == 6 && enemigo2 != 6 && enemigo3 == 6) {
                matrizCostos[2] += 4;
                costosActual[2] = 4;
            } else if (enemigo1 != 6 && enemigo2 == 6 && enemigo3 == 6) {
                matrizCostos[2] += 4;
                costosActual[2] = 4;
            }

        }
    }

    public static int continuaJugando(int[][] matriz, int posTi, int posPi, int posPa, int tiEsp, int piEsp, int paEsp, boolean tiAtaca, boolean piAtaca, boolean paAtaca, int costos[], int costosR[]) {
        int n = matriz.length;
        int mejor = 0;
        boolean acabo = false;
        
        while (acabo == false) {
            if (matriz[posTi + 1][0] == 0 && tiEsp == 0) {
                matriz[posTi][0] = 0;

                posTi++;
                matriz[posTi][0] = 1;
                //Si puede avanzar, avanza 1

                //Ahora bien, estos serian los casos para cuando en la siguiente iteración, se encuentra con otro robot que está derrotando enemigos
                if (matriz[posTi][1] == 2 && piEsp != 0 && piAtaca == true) { //CUANDO LA TIJERA SE ENCUENTRA CON LA PIEDRA DERROTANDO UN OBSTACULO
                    piEsp = piEsp--; //DESCONTAMOS LA UNIDAD DE TIEMPO QUE SE DEMORO LA TIJERA EN LLEGAR
                    piEsp = piEsp * 2;
                    tiEsp = piEsp;
                } else if (matriz[posTi][2] == 3 && paEsp != 0 && paAtaca == true) { //CUANDO LA TIJERA SE ENCUENTRA CON EL PAPEL DERROTANDO OBSCATULO
                    paEsp = paEsp--;
                    paEsp = Math.round(paEsp / 2);
                    tiEsp = paEsp;
                } else {
                    //NO TIENE ENEMIGOS NI AMIGOS QUE AYUDAR/DERROTAR
                }
            } else if (tiEsp != 0) {
                tiEsp--;

                if (tiEsp == 0) {
                    tiAtaca = false;
                }
            }

            if (matriz[posPi + 1][1] == 0 && piEsp == 0) {
                matriz[posPi][1] = 0;

                posPi++;

                matriz[posPi][1] = 2;
                //Si puede avanzar, avanza 1

                //Ahora bien, estos serian los casos para cuando en la siguiente iteración, se encuentra con otro robot que está derrotando enemigos
                if (matriz[posPi][0] == 1 && tiEsp != 0 && tiAtaca == true) { //CUANDO LA PIEDRA SE ENCUENTRA CON LA TIJERA DERROTANDO UN OBSTACULO
                    tiEsp = tiEsp--; //DESCONTAMOS LA UNIDAD DE TIEMPO QUE SE DEMORO LA PIEDRA EN LLEGAR
                    tiEsp = Math.round(tiEsp / 2);
                    piEsp = tiEsp;
                } else if (matriz[posPi][2] == 3 && paEsp != 0 && paAtaca == true) {
                    paEsp = paEsp--;
                    paEsp = paEsp * 2;
                    piEsp = paEsp;
                } else {

                }
            } else if (piEsp != 0) {
                piEsp--;

                if (piEsp == 0) {
                    piAtaca = false;
                }
            }

            if (matriz[posPa + 1][2] == 0 && paEsp == 0 && paAtaca == true) {
                matriz[posPa][2] = 0;

                posPa++;

                matriz[posPa][2] = 3;
                //Si puede avanzar, avanza 1 

                //Ahora bien, estos serian los casos para cuando en la siguiente iteración, se encuentra con otro robot que está derrotando enemigos
                if (matriz[posPa][0] == 1 && tiEsp != 0 && tiAtaca == true) { //CUANDO EL PAPEL SE ENCUENTRA CON LA TIJERA DERROTANDO UN OBSTACULO
                    tiEsp = tiEsp--; //DESCONTAMOS LA UNIDAD DE TIEMPO QUE SE DEMORO LA PIEDRA EN LLEGAR
                    tiEsp = tiEsp * 2;
                    paEsp = tiEsp;
                } else if (matriz[posPi][1] == 2 && piEsp != 0 && piAtaca == true) { //CUANDO EL PAPEL SE ENCUENTRA CON LA PIEDRA DERROTANDO OBSTACULO
                    piEsp = piEsp--;
                    piEsp = Math.round(piEsp / 2);
                    paEsp = piEsp;
                } else {

                }
            } else if (paEsp != 0) {
                paEsp--;

                if (paEsp == 0) {
                    paAtaca = false;
                }
            }

            if (matriz[posTi + 1][0] != 0 && matriz[posPi + 1][1] != 0 && matriz[posPa + 1][2] != 0) {
                calculaCosto(1, matriz[posTi + 1][0], matriz[posPi + 1][1], matriz[posPa + 1][2], costos, costosR);
                calculaCosto(2, matriz[posTi + 1][0], matriz[posPi + 1][1], matriz[posPa + 1][2], costos, costosR);
                calculaCosto(3, matriz[posTi + 1][0], matriz[posPi + 1][1], matriz[posPa + 1][2], costos, costosR);
                if (posTi == posPi && posPi == posPa) {
                    if (costos[0] < costos[1]
                            && costos[0] < costos[2]) {

                        mejor = costos[0];
                        acabo = true;
                    } else if (costos[1] < costos[0]
                            && costos[1] < costos[2]) {

                        mejor = costos[1];
                        acabo = true;
                    } else if (costos[2] < costos[0]
                            && costos[2] < costos[1]) {

                        mejor = costos[2];
                        acabo = true;
                    } else if (costos[0] == costos[1]
                            && costos[0] < costos[2]) {

                        mejor = costos[0];
                        acabo = true;
                    } else if (costos[0] == costos[1]
                            && costos[0] == costos[2]) {

                        mejor = costos[0];
                        acabo = true;
                    }
                }
            } else if ((posTi + 1 >= n) || posPi + 1 >= n || posPa + 1 >= n) {
                acabo = true;
                //EL JUEGO YA ACABO
            } else if (matriz[posTi + 1][0] == 0 && matriz[posPi + 1][1] != 0 && matriz[posPa + 1][2] != 0) {
                calculaCosto(3, matriz[posPi + 1][0], matriz[posPi + 1][1], matriz[posPa + 1][2], costos, costosR);
                calculaCosto(2, matriz[posPi + 1][0], matriz[posPi + 1][1], matriz[posPa + 1][2], costos, costosR);

                if (posPi == posPa) {
                    if (costos[2] < costos[1]) {
                        mejor = costos[2];
                        acabo = true;
                    } else if (costos[1] < costos[2]) {
                        mejor = costos[1];
                        acabo = true;
                    } else if (costos[2] == costos[1]) {
                        mejor = costos[2];
                        acabo = true;
                    }
                }
            } else if (matriz[posTi + 1][0] != 0 && matriz[posPi + 1][1] != 0 && matriz[posPa + 1][2] == 0) {
                calculaCosto(1, matriz[posTi + 1][0], matriz[posPi + 1][1], matriz[posPi + 1][2], costos, costosR);
                calculaCosto(2, matriz[posTi + 1][0], matriz[posPi + 1][1], matriz[posPi + 1][2], costos, costosR);
                if (posTi == posPi) {
                    if (costos[0] < costos[1]) {
                        mejor = costos[0];
                        acabo = true;
                    } else if (costos[1] < costos[0]) {

                        mejor = costos[1];
                        acabo = true;
                    } else if (costos[0] == costos[1]) {

                        mejor = costos[0];
                        acabo = true;
                    }
                }
            } else if (matriz[posTi + 1][0] != 0 && matriz[posPi + 1][1] == 0 && matriz[posPa + 1][2] != 0) {
                calculaCosto(1, matriz[posTi + 1][0], matriz[posPa + 1][1], matriz[posPa + 1][2], costos, costosR);
                calculaCosto(3, matriz[posTi + 1][0], matriz[posPa + 1][1], matriz[posPa + 1][2], costos, costosR);

                if (posTi == posPa) {
                    if (costos[0] < costos[2]) {
                        //System.out.println("Entre en continua jugando a 1 y 3 tienen enemigo y me quede con lo que hy en 1");
                        mejor = costos[0];
                        acabo = true;
                    } else if (costos[2] < costos[0]) {
                        //System.out.println("Entre en continua jugando a 1 y 3 tienen enemigo y me quede con lo que hy en 3");
                        mejor = costos[2];
                        acabo = true;
                    } else if (costos[2] == costos[0]) {
                        //System.out.println("Entre en continua jugando a 1 y 3 diferentes y me quede con lo que hy en 1");
                        mejor = costos[2];
                        acabo = true;
                    }
                }
            } //LAS SIGUIENTES CONDICIONES SON PARA CUANDO SOLO HAY UN ROBOT Y LLEGA A UNOS OBSTACULOS
            else if (matriz[posTi + 1][0] != 0 && matriz[posTi][1] == 0 && matriz[posTi][2] == 0) {
                calculaCosto(1, matriz[posTi + 1][0], matriz[posTi + 1][1], matriz[posTi + 1][2], costos, costosR);

                mejor = costos[0];
                acabo = true;
            } else if (matriz[posPi + 1][1] != 0 && matriz[posPi][0] == 0 && matriz[posPi][2] == 0) {
                calculaCosto(2, matriz[posPi + 1][0], matriz[posPi + 1][1], matriz[posPi + 1][2], costos, costosR);

                mejor = costos[1];
                acabo = true;
            } else if (matriz[posPa + 1][2] != 0 && matriz[posPa][0] == 0 && matriz[posPa][1] == 0) {
                calculaCosto(3, matriz[posPa + 1][0], matriz[posPa + 1][1], matriz[posPa + 1][2], costos, costosR);

                mejor = costos[2];
                acabo = true;
            }
        }
        return mejor;
    }

    public static boolean compruebaGanador(int m) {
        boolean acabo = false;
        if (posicionTijera >= m) {
            acabo = true;
            JOptionPane.showMessageDialog(null, "LA TIJERA HA GANADO!");
        }
        if (posicionPiedra >= m) {
            acabo = true;
            JOptionPane.showMessageDialog(null, "LA PIEDRA HA GANADO!");
        }
        if (posicionPapel >= m) {
            acabo = true;
            JOptionPane.showMessageDialog(null, "EL PAPEL HA GANADO!");
        }
        return acabo;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el tamaño de la carrera (de 0 a 50)"));
        pintaMatriz(n);
        //Pongo Robots
        carrera[posicionTijera][0] = 1;
        carrera[posicionPiedra][1] = 2;
        carrera[posicionPapel][2] = 3;

        //Pongo Enemigos
        carrera[3][0] = 4;
        carrera[3][1] = 4;
        carrera[3][2] = 4;

        carrera[7][0] = 5;
        carrera[7][1] = 5;
        carrera[7][2] = 5;

        carrera[10][0] = 6;
        carrera[10][1] = 6;
        carrera[10][2] = 6;
        //MOSTRAMOS LA MATRIZ INICIAL
        mostrarMatriz(n, carrera);

        while (acabo == false) {

            if (carrera[posicionTijera + 1][0] == 0 && tijeraEspera == 0) {
                carrera[posicionTijera][0] = 0;

                posicionTijera++;
                carrera[posicionTijera][0] = 1;
                //Si puede avanzar, avanza 1 y muestra la matriz
                System.out.println();
                mostrarMatriz(n, carrera);
                //Ahora bien, estos serian los casos para cuando en la siguiente iteración, se encuentra con otro robot que está derrotando enemigos
                if (carrera[posicionTijera][1] == 2 && piedraEspera != 0 && piedraAtaca == true) { //CUANDO LA TIJERA SE ENCUENTRA CON LA PIEDRA DERROTANDO UN OBSTACULO
                    System.out.println("La tijera esta entrando a perjudicar a la piedra");
                    piedraEspera = piedraEspera--; //DESCONTAMOS LA UNIDAD DE TIEMPO QUE SE DEMORO LA TIJERA EN LLEGAR
                    piedraEspera = piedraEspera * 2;
                    tijeraEspera = piedraEspera;
                } else if (carrera[posicionTijera][2] == 3 && papelEspera != 0 && papelAtaca == true) { //CUANDO LA TIJERA SE ENCUENTRA CON EL PAPEL DERROTANDO OBSCATULO
                    System.out.println("La tijera esta entrando a ayudar al papel");
                    papelEspera = papelEspera--;
                    papelEspera = Math.round(papelEspera / 2);
                    tijeraEspera = papelEspera;
                } else {
                    //NO TIENE ENEMIGOS NI AMIGOS QUE AYUDAR/DERROTAR
                }
            } else if (tijeraEspera != 0) {
                tijeraEspera--;

                if (tijeraEspera == 0) {
                    tijeraAtaca = false;
                }
            }

            if (carrera[posicionPiedra + 1][1] == 0 && piedraEspera == 0) {

                carrera[posicionPiedra][1] = 0;

                posicionPiedra++;

                carrera[posicionPiedra][1] = 2;
                //Si puede avanzar, avanza 1 y muestra la matriz
                System.out.println();
                mostrarMatriz(n, carrera);
                //Ahora bien, estos serian los casos para cuando en la siguiente iteración, se encuentra con otro robot que está derrotando enemigos
                if (carrera[posicionPiedra][0] == 1 && tijeraEspera != 0 && tijeraAtaca == true) { //CUANDO LA PIEDRA SE ENCUENTRA CON LA TIJERA DERROTANDO UN OBSTACULO
                    System.out.println("La piedra entro a ayudar a la tijera");
                    tijeraEspera = tijeraEspera--; //DESCONTAMOS LA UNIDAD DE TIEMPO QUE SE DEMORO LA PIEDRA EN LLEGAR
                    tijeraEspera = Math.round(tijeraEspera / 2);
                    piedraEspera = tijeraEspera;
                } else if (carrera[posicionPiedra][2] == 3 && papelEspera != 0 && papelAtaca == true) {
                    System.out.println("La piedra entro a perjudicar al papel");
                    papelEspera = papelEspera--;
                    papelEspera = papelEspera * 2;
                    piedraEspera = papelEspera;
                } else {

                }
            } else if (piedraEspera != 0) {
                piedraEspera--;

                if (piedraEspera == 0) {
                    piedraAtaca = false;
                }
            }

            if (carrera[posicionPapel + 1][2] == 0 && papelEspera == 0) {
                carrera[posicionPapel][2] = 0;

                posicionPapel++;

                carrera[posicionPapel][2] = 3;
                //Si puede avanzar, avanza 1 y muestra la matriz
                System.out.println();
                mostrarMatriz(n, carrera);
                //Ahora bien, estos serian los casos para cuando en la siguiente iteración, se encuentra con otro robot que está derrotando enemigos
                if (carrera[posicionPapel][0] == 1 && tijeraEspera != 0 && tijeraAtaca == true) { //CUANDO EL PAPEL SE ENCUENTRA CON LA TIJERA DERROTANDO UN OBSTACULO
                    System.out.println("El papel entro a perjudicar a la tijera");
                    tijeraEspera = tijeraEspera--; //DESCONTAMOS LA UNIDAD DE TIEMPO QUE SE DEMORO LA PIEDRA EN LLEGAR
                    tijeraEspera = tijeraEspera * 2;
                    papelEspera = tijeraEspera;
                } else if (carrera[posicionPiedra][1] == 2 && piedraEspera != 0 && piedraAtaca == true) { //CUANDO EL PAPEL SE ENCUENTRA CON LA PIEDRA DERROTANDO OBSTACULO
                    System.out.println("El papel entro a ayudar a la piedra");
                    piedraEspera = piedraEspera--;
                    piedraEspera = Math.round(piedraEspera / 2);
                    papelEspera = piedraEspera;
                    System.out.println("papel y piedra se quedan esperando por un tiempo de " + papelEspera);
                } else {

                }
            } else if (papelEspera != 0) {
                papelEspera--;

                if (papelEspera == 0) {
                    papelAtaca = false;
                }
            }

            if (carrera[posicionTijera + 1][0] != 0 && carrera[posicionPiedra + 1][1] != 0 && carrera[posicionPapel + 1][2] != 0) {
                calculaCosto(1, carrera[posicionTijera + 1][0], carrera[posicionPiedra + 1][1], carrera[posicionPapel + 1][2], costos, copiaCosto);
                System.out.println("El costo de la rama tijera es: " + costos[0]);
                calculaCosto(2, carrera[posicionTijera + 1][0], carrera[posicionPiedra + 1][1], carrera[posicionPapel + 1][2], costos, copiaCosto);
                System.out.println("El costo de la rama piedra es: " + costos[1]);
                calculaCosto(3, carrera[posicionTijera + 1][0], carrera[posicionPiedra + 1][1], carrera[posicionPapel + 1][2], costos, copiaCosto);
                System.out.println("El costo de la rama papel es: " + costos[2]);

                System.out.println("EN EL VECTOR COSTOS QUEDARION ASÍ : " + costos[0] + " " + costos[1] + " " + costos[2]);
                System.out.println("EN EL VECTOR COPIA COSTO QUEDARION ASÍ : " + copiaCosto[0] + " " + copiaCosto[1] + " " + copiaCosto[2]);
                if (posicionTijera == posicionPiedra && posicionPiedra == posicionPapel) {
                    if (costos[0] < costos[1]
                            && costos[0] < costos[2]) {

                        igualaMatriz(memoriza, carrera, n);
                        
                        System.out.println("ESTOY ENTRANDO A LA RAMA DONDE TIJERA ES MENOR A PIEDRA Y PAPEL!!!");
                        
                        //System.out.println("ESTA ES LA MATRIZ QUE ESTOY MEMORIZANDO!");
                        //mostrarMatriz(n, memoriza);
                        //System.out.println(); 

                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
                        copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);
                        //costosMemoriza = costos;
                        //costosEnRama = copiaCosto;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;

                        //CREAMOS A LOS TIOS, PARA SABER SI EL VALOR DE SUS HIJOS ES MENOR AL DE LA RAMIFICACIÓN POR LA QUE NOS FUIMOS O NO
                        //Generamos el primer Tio, que es la rama que se crea, si es que la piedra se hubiera quedado:
                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        
                        //System.out.println("ESTA ES LA MATRIZ QUE LE LLEGA A TABLERO TIO 1: ");
                        //mostrarMatriz(n, tableroTio1);
                        //System.out.println();
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        //TABLA DEL TIO 1
                        piedraTioEsp = copiaCostoTio1[1];
                        System.out.println("En tio espera tengo una espera de : " + copiaCostoTio1[1]);
                        piedraTioAtaca = true;

                        tableroTio1[posicionTioTijera + 1][0] = 0;
                        tableroTio1[posicionTioPiedra + 1][1] = 0;
                        tableroTio1[posicionTioPapel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioTijera][0] = 0;
                        tableroTio1[posicionTioPapel][2] = 0;

                        posicionTioTijera = posicionTioTijera + 2;
                        System.out.println("La posicionTioTijera esta en : " + posicionTioTijera);
                        posicionTioPapel = posicionTioPapel + 2;
                        System.out.println("La posicionTioPapel esta en : " + posicionTioPapel);
                        

                        tableroTio1[posicionTioTijera][0] = 1;
                        tableroTio1[posicionTioPapel][2] = 3;

                        int[][] tablero2Tio = new int[n][3];
                        igualaMatriz(tablero2Tio, memoriza, n);
                        //System.out.println();

                        //mostrarMatriz(n, tablero2Tio);
                        //System.out.println();
                        int tijeraTio2Esp = copiatijeraEspera;
                        int piedraTio2Esp = copiapiedraEspera;
                        int papelTio2Esp = copiapapelEspera;
                        int posicionTio2Tijera = copiaposicionTijera;
                        int posicionTio2Piedra = copiaposicionPiedra;
                        int posicionTio2Papel = copiaposicionPapel;
                        int costosMemoriza2Tio[] = {0,0,0};
                        copiaVector(costosMemoriza2Tio, costosMemoriza);
                        int copiaCosto2Tio[] = {0,0,0};
                        copiaVector(copiaCosto2Tio, costosEnRama);
                        //int costosMemoriza2Tio[] = costosMemoriza;
                        //int copiaCosto2Tio[] = costosEnRama;
                        boolean tijeraTio2Ataca = copiatijeraAtaca;
                        boolean piedraTio2Ataca = copiapiedraAtaca;
                        boolean papelTio2Ataca = copiapapelAtaca;

                        //TABLA DEL TIO 2
                        //Generamos el segundo Tio, que es la rama que se crea, si es que el papel se hubiera quedado:
                        papelTio2Esp = copiaCosto2Tio[2];
                        papelTio2Ataca = true;

                        tablero2Tio[posicionTio2Tijera + 1][0] = 0;
                        tablero2Tio[posicionTio2Piedra + 1][1] = 0;
                        tablero2Tio[posicionTio2Papel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tablero2Tio[posicionTio2Tijera][0] = 0;
                        tablero2Tio[posicionTio2Piedra][1] = 0;

                        posicionTio2Piedra = posicionTio2Piedra + 2;
                        posicionTio2Tijera = posicionTio2Tijera + 2;

                        tablero2Tio[posicionTio2Tijera][0] = 1;
                        tablero2Tio[posicionTio2Piedra][1] = 2;

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        EstructuraJuego tio2 = new EstructuraJuego(tablero2Tio, posicionTio2Tijera, posicionTio2Piedra, posicionTio2Papel, tijeraTio2Esp, piedraTio2Esp, papelTio2Esp);

                        int t1 = continuaJugando(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp, tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);
                        int t2 = continuaJugando(tablero2Tio, posicionTio2Tijera, posicionTio2Piedra, posicionTio2Papel, tijeraTio2Esp, piedraTio2Esp, papelTio2Esp, tijeraTio2Ataca, piedraTio2Ataca, papelTio2Ataca, costosMemoriza2Tio, copiaCosto2Tio);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[0]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);
                        System.out.println("EL TIO 2 TIENE UN MEJOR COSTO DE : " + t2);
                        if (costos[0] < t1
                                && costos[0] < t2) {
                            System.out.println("ENTRE EN LA OPCION 1");
                            //ESTO QUE SIGUE, ES LA ELECCION MEJOR DE LA RAMA ACTUAL, PERO TAMBIÉN DEBEMOS ANALIZAR A LOS TIOS
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionPiedra = posicionPiedra + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPiedra][1] = 2;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (t2 < costos[0]
                                && t2 < t1) {
                            System.out.println("ENTRE EN LA OPCION 2");
                            igualaMatriz(carrera, tablero2Tio, n);
                            posicionTijera = posicionTio2Tijera;
                            posicionPiedra = posicionTio2Piedra;
                            posicionPapel = posicionTio2Papel;
                            tijeraEspera = tijeraTio2Esp;
                            piedraEspera = piedraTio2Esp;
                            papelEspera = papelTio2Esp;
                            copiaVector(costos, costosMemoriza2Tio);
                            copiaVector(copiaCosto, copiaCosto2Tio);
                            //costos = costosMemoriza2Tio;
                            //copiaCosto = copiaCosto2Tio;
                            tijeraAtaca = tijeraTio2Ataca;
                            piedraAtaca = piedraTio2Ataca;
                            papelAtaca = papelTio2Ataca;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[0]
                                && t1 < t2) {
                            System.out.println("ENTRE EN LA OPCION 3");    
                            
                            igualaMatriz(carrera, tableroTio1, n);
                            System.out.println("Estoy copiando esta matriz a la original!: ");
                            mostrarMatriz(n, tableroTio1);
                            System.out.println();
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[0] == t1
                                && costos[0] < t2) {
                            System.out.println("ENTRE EN LA OPCION 4");
                            //ESTO QUE SIGUE, ES LA ELECCION MEJOR DE LA RAMA ACTUAL, PERO TAMBIÉN DEBEMOS ANALIZAR A LOS TIOS
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionPiedra = posicionPiedra + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPiedra][1] = 2;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (costos[0] == t2
                                && costos[0] < t1) {
                            System.out.println("ENTRE EN LA OPCION 5");
                            
                            //ESTO QUE SIGUE, ES LA ELECCION MEJOR DE LA RAMA ACTUAL, PERO TAMBIÉN DEBEMOS ANALIZAR A LOS TIOS
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionPiedra = posicionPiedra + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPiedra][1] = 2;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (t1 == t2
                                && t1 < costos[0]) {
                            System.out.println("ENTRE EN LA OPCION 6");
                            
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        }

                    } else if (costos[1] < costos[0]
                            && costos[1] < costos[2]) {
                        igualaMatriz(memoriza, carrera, n);
                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;
                        copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);

                        //TALA DEL TIO 1: Caso en que se queda tijera
                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        //mostrarMatriz(n, tableroTio1);
                        //System.out.println();
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemorizaTio1[] = costosMemoriza;
                        //int copiaCostoTio1[] = costosEnRama;
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        tijeraTioEsp = copiaCostoTio1[0];
                        tijeraTioAtaca = true;
                        //System.out.println("El costo de la tijera del tio 1 es : " + tijeraTioEsp);

                        //MOVEMOS LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioTijera + 1][0] = 0;
                        tableroTio1[posicionTioPiedra + 1][1] = 0;
                        tableroTio1[posicionTioPapel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioPiedra][1] = 0;
                        tableroTio1[posicionTioPapel][2] = 0;

                        posicionTioPiedra = posicionTioPiedra + 2;
                        posicionTioPapel = posicionTioPapel + 2;

                        tableroTio1[posicionTioPiedra][1] = 2;
                        tableroTio1[posicionTioPapel][2] = 3;

                        //System.out.println("El tablero de tio 1 despues de realizar sus movimientos ");
                        //mostrarMatriz(n, tableroTio1);
                        //TABLA DEL TIO 2
                        //Generamos el segundo Tio, que es la rama que se crea, si es que el papel se hubiera quedado:
                        int[][] tablero2Tio = new int[n][3];
                        igualaMatriz(tablero2Tio, memoriza, n);
                        int tijeraTio2Esp = copiatijeraEspera;
                        int piedraTio2Esp = copiapiedraEspera;
                        int papelTio2Esp = copiapapelEspera;
                        int posicionTio2Tijera = copiaposicionTijera;
                        int posicionTio2Piedra = copiaposicionPiedra;
                        int posicionTio2Papel = copiaposicionPapel;
                        int costosMemoriza2Tio[] = {0,0,0};
                        copiaVector(costosMemoriza2Tio, costosMemoriza);
                        int copiaCosto2Tio[] = {0,0,0};
                        copiaVector(copiaCosto2Tio, costosEnRama);
                        //int costosMemoriza2Tio[] = costosMemoriza;
                        //int copiaCosto2Tio[] = costosEnRama;
                        boolean tijeraTio2Ataca = copiatijeraAtaca;
                        boolean piedraTio2Ataca = copiapiedraAtaca;
                        boolean papelTio2Ataca = copiapapelAtaca;

                        papelTio2Esp = copiaCosto2Tio[2];
                        papelTio2Ataca = true;
                        //System.out.println("El costo de papel en el tio 2: " + papelTio2Esp);

                        tablero2Tio[posicionTio2Tijera + 1][0] = 0;
                        tablero2Tio[posicionTio2Piedra + 1][1] = 0;
                        tablero2Tio[posicionTio2Papel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tablero2Tio[posicionTio2Tijera][0] = 0;
                        tablero2Tio[posicionTio2Piedra][1] = 0;

                        posicionTio2Piedra = posicionTio2Piedra + 2;
                        posicionTio2Tijera = posicionTio2Tijera + 2;

                        tablero2Tio[posicionTio2Tijera][0] = 1;
                        tablero2Tio[posicionTio2Piedra][1] = 2;

                        //System.out.println("El tablero de tio 2 despues de realizar sus movvimientos queda como : ");
                        //mostrarMatriz(n, tablero2Tio);

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        EstructuraJuego tio2 = new EstructuraJuego(tablero2Tio, posicionTio2Tijera, posicionTio2Piedra, posicionTio2Papel, tijeraTio2Esp, piedraTio2Esp, papelTio2Esp);

                        int t1 = continuaJugando(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp, tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);
                        int t2 = continuaJugando(tablero2Tio, posicionTio2Tijera, posicionTio2Piedra, posicionTio2Papel, tijeraTio2Esp, piedraTio2Esp, papelTio2Esp, tijeraTio2Ataca, piedraTio2Ataca, papelTio2Ataca, costosMemoriza2Tio, copiaCosto2Tio);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[1]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);
                        System.out.println("EL TIO 2 TIENE UN MEJOR COSTO DE : " + t2);
                        if (costos[1] < t1
                                && costos[1] < t2) {
                            piedraEspera = copiaCosto[1];
                            piedraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionTijera = posicionTijera + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionTijera][0] = 1;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (t2 < costos[1]
                                && t2 < t1) {

                            igualaMatriz(carrera, tablero2Tio, n);
                            posicionTijera = posicionTio2Tijera;
                            posicionPiedra = posicionTio2Piedra;
                            posicionPapel = posicionTio2Papel;
                            tijeraEspera = tijeraTio2Esp;
                            piedraEspera = piedraTio2Esp;
                            papelEspera = papelTio2Esp;
                            copiaVector(costos, costosMemoriza2Tio);
                            copiaVector(copiaCosto, copiaCosto2Tio);
                            //costos = costosMemoriza2Tio;
                            //copiaCosto = copiaCosto2Tio;
                            tijeraAtaca = tijeraTio2Ataca;
                            piedraAtaca = piedraTio2Ataca;
                            papelAtaca = papelTio2Ataca;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[1]
                                && t1 < t2) {

                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[1] == t1
                                && costos[1] < t2) {
                            piedraEspera = copiaCosto[1];
                            piedraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionTijera = posicionTijera + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionTijera][0] = 1;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (costos[1] == t2
                                && costos[1] < t1) {
                            piedraEspera = copiaCosto[1];
                            piedraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionTijera = posicionTijera + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionTijera][0] = 1;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (t1 == t2
                                && t1 < costos[1]) {

                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        }
                        if (costos[1] < t1
                                && costos[1] < t2) {
                            piedraEspera = copiaCosto[1];
                            piedraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionTijera = posicionTijera + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionTijera][0] = 1;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (t2 < costos[1]
                                && t2 < t1) {

                            igualaMatriz(carrera, tablero2Tio, n);
                            posicionTijera = posicionTio2Tijera;
                            posicionPiedra = posicionTio2Piedra;
                            posicionPapel = posicionTio2Papel;
                            tijeraEspera = tijeraTio2Esp;
                            piedraEspera = piedraTio2Esp;
                            papelEspera = papelTio2Esp;
                            copiaVector(costos, costosMemoriza2Tio);
                            copiaVector(copiaCosto, copiaCosto2Tio);
                            //costos = costosMemoriza2Tio;
                            //copiaCosto = copiaCosto2Tio;
                            tijeraAtaca = tijeraTio2Ataca;
                            piedraAtaca = piedraTio2Ataca;
                            papelAtaca = papelTio2Ataca;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[1]
                                && t1 < t2) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;

                            mostrarMatriz(n, carrera);
                        } else if (costos[1] == t1
                                && costos[1] < t2) {
                            piedraEspera = copiaCosto[1];
                            piedraAtaca = true;
                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionTijera = posicionTijera + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionTijera][0] = 1;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (costos[1] == t2
                                && costos[1] < t1) {
                            piedraEspera = copiaCosto[1];
                            piedraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionTijera = posicionTijera + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionTijera][0] = 1;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (t1 == t2
                                && t1 < costos[1]) {

                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        }

                    } else if (costos[2] < costos[0]
                            && costos[2] < costos[1]) {
                        igualaMatriz(memoriza, carrera, n);
                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;
                        copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);
                        
                        //TALA DEL TIO 1: Caso en que se queda tijera
                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        mostrarMatriz(n, tableroTio1);
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemorizaTio1[] = costosMemoriza;
                        //int copiaCostoTio1[] = costosEnRama;
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        tijeraTioEsp = copiaCostoTio1[0];
                        tijeraTioAtaca = true;

                        //MOVEMOS LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioTijera + 1][0] = 0;
                        tableroTio1[posicionTioPiedra + 1][1] = 0;
                        tableroTio1[posicionTioPapel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioPiedra][1] = 0;
                        tableroTio1[posicionTioPapel][2] = 0;

                        posicionTioPiedra = posicionTioPiedra + 2;
                        posicionTioPapel = posicionTioPapel + 2;

                        tableroTio1[posicionTioPiedra][1] = 2;
                        tableroTio1[posicionTioPapel][2] = 3;

                        //System.out.println("El tablero de tio 1 despues de realizar sus movimientos ");
                        //mostrarMatriz(n, tableroTio1);

                        //TABLA DEL TIO 2
                        //Generamos el segundo Tio, que es la rama que se crea, si es que la piedra se hubiera quedado:
                        int[][] tablero2Tio = new int[n][3];
                        igualaMatriz(tablero2Tio, memoriza, n);
                        mostrarMatriz(n, tablero2Tio);
                        int tijeraTio2Esp = copiatijeraEspera;
                        int piedraTio2Esp = copiapiedraEspera;
                        int papelTio2Esp = copiapapelEspera;
                        int posicionTio2Tijera = copiaposicionTijera;
                        int posicionTio2Piedra = copiaposicionPiedra;
                        int posicionTio2Papel = copiaposicionPapel;
                        int costosMemoriza2Tio[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCosto2Tio[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemoriza2Tio[] = costosMemoriza;
                        //int copiaCosto2Tio[] = costosEnRama;
                        boolean tijeraTio2Ataca = copiatijeraAtaca;
                        boolean piedraTio2Ataca = copiapiedraAtaca;
                        boolean papelTio2Ataca = copiapapelAtaca;

                        piedraTio2Esp = copiaCosto2Tio[1];
                        piedraTio2Ataca = true;
                        //System.out.println("El costo de piedra en el tio 2: " + piedraTio2Esp);

                        tablero2Tio[posicionTio2Tijera + 1][0] = 0;
                        tablero2Tio[posicionTio2Piedra + 1][1] = 0;
                        tablero2Tio[posicionTio2Papel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tablero2Tio[posicionTio2Tijera][0] = 0;
                        tablero2Tio[posicionTio2Papel][2] = 0;

                        posicionTio2Papel = posicionTio2Papel + 2;
                        posicionTio2Tijera = posicionTio2Tijera + 2;

                        tablero2Tio[posicionTio2Tijera][0] = 1;
                        tablero2Tio[posicionTio2Papel][2] = 3;
                        //System.out.println("El tablero de tio 2 despues de realizar sus movvimientos queda como : ");
                        //mostrarMatriz(n, tablero2Tio);

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        EstructuraJuego tio2 = new EstructuraJuego(tablero2Tio, posicionTio2Tijera, posicionTio2Piedra, posicionTio2Papel, tijeraTio2Esp, piedraTio2Esp, papelTio2Esp);

                        int t1 = continuaJugando(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp, tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);
                        int t2 = continuaJugando(tablero2Tio, posicionTio2Tijera, posicionTio2Piedra, posicionTio2Papel, tijeraTio2Esp, piedraTio2Esp, papelTio2Esp, tijeraTio2Ataca, piedraTio2Ataca, papelTio2Ataca, costosMemoriza2Tio, copiaCosto2Tio);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[2]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);
                        System.out.println("EL TIO 2 TIENE UN MEJOR COSTO DE : " + t2);

                        if (costos[2] < t1
                                && costos[2] < t2) {
                            //El tiempo de Espera de papel derrotando a los Enemigos
                            papelEspera = copiaCosto[2];
                            papelAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;
                            carrera[posicionPiedra][1] = 0;

                            posicionTijera = posicionTijera + 2;
                            posicionPiedra = posicionPiedra + 2;

                            carrera[posicionTijera][0] = 1;
                            carrera[posicionPiedra][1] = 2;

                            mostrarMatriz(n, carrera);
                        } else if (t2 < costos[2]
                                && t2 < t1) {

                            igualaMatriz(carrera, tablero2Tio, n);
                            posicionTijera = posicionTio2Tijera;
                            posicionPiedra = posicionTio2Piedra;
                            posicionPapel = posicionTio2Papel;
                            tijeraEspera = tijeraTio2Esp;
                            piedraEspera = piedraTio2Esp;
                            papelEspera = papelTio2Esp;
                            copiaVector(costos, costosMemoriza2Tio);
                            copiaVector(copiaCosto, copiaCosto2Tio);
                            //costos = costosMemoriza2Tio;
                            //copiaCosto = copiaCosto2Tio;
                            tijeraAtaca = tijeraTio2Ataca;
                            piedraAtaca = piedraTio2Ataca;
                            papelAtaca = papelTio2Ataca;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[2]
                                && t1 < t2) {

                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[2] == t1
                                && costos[2] < t2) {
                            //El tiempo de Espera de papel derrotando a los Enemigos
                            papelEspera = copiaCosto[2];
                            papelAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;
                            carrera[posicionPiedra][1] = 0;

                            posicionTijera = posicionTijera + 2;
                            posicionPiedra = posicionPiedra + 2;

                            carrera[posicionTijera][0] = 1;
                            carrera[posicionPiedra][1] = 2;

                            mostrarMatriz(n, carrera);
                        } else if (costos[2] == t2
                                && costos[2] < t1) {

                            papelEspera = copiaCosto[2];
                            papelAtaca = true;
                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;
                            carrera[posicionPiedra][1] = 0;

                            posicionTijera = posicionTijera + 2;
                            posicionPiedra = posicionPiedra + 2;

                            carrera[posicionTijera][0] = 1;
                            carrera[posicionPiedra][1] = 2;

                            mostrarMatriz(n, carrera);
                        } else if (t1 == t2
                                && t1 < costos[2]) {

                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[2] < t1
                                && costos[2] < t2) {
                            //El tiempo de Espera de la tijera derrotando a los Enemigos
                            papelEspera = copiaCosto[2];
                            papelAtaca = true;
                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;
                            carrera[posicionPiedra][1] = 0;

                            posicionTijera = posicionTijera + 2;
                            posicionPiedra = posicionPiedra + 2;

                            carrera[posicionTijera][0] = 1;
                            carrera[posicionPiedra][1] = 2;

                            mostrarMatriz(n, carrera);
                        } else if (t2 < costos[2]
                                && t2 < t1) {

                            igualaMatriz(carrera, tablero2Tio, n);
                            posicionTijera = posicionTio2Tijera;
                            posicionPiedra = posicionTio2Piedra;
                            posicionPapel = posicionTio2Papel;
                            tijeraEspera = tijeraTio2Esp;
                            piedraEspera = piedraTio2Esp;
                            papelEspera = papelTio2Esp;
                            copiaVector(costos, costosMemoriza2Tio);
                            copiaVector(copiaCosto, copiaCosto2Tio);
                            //costos = costosMemoriza2Tio;
                            //copiaCosto = copiaCosto2Tio;
                            tijeraAtaca = tijeraTio2Ataca;
                            piedraAtaca = piedraTio2Ataca;
                            papelAtaca = papelTio2Ataca;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[2]
                                && t1 < t2) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[2] == t1
                                && costos[2] < t2) {
                            //El tiempo de Espera de la tijera derrotando a los Enemigos
                            papelEspera = copiaCosto[2];
                            papelAtaca = true;
                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;
                            carrera[posicionPiedra][1] = 0;

                            posicionTijera = posicionTijera + 2;
                            posicionPiedra = posicionPiedra + 2;

                            carrera[posicionTijera][0] = 1;
                            carrera[posicionPiedra][1] = 2;

                            mostrarMatriz(n, carrera);
                        } else if (costos[2] == t2
                                && costos[2] < t1) {
                            //El tiempo de Espera de la tijera derrotando a los Enemigos
                            papelEspera = copiaCosto[2];
                            papelAtaca = true;
                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;
                            carrera[posicionPiedra][1] = 0;

                            posicionTijera = posicionTijera + 2;
                            posicionPiedra = posicionPiedra + 2;

                            carrera[posicionTijera][0] = 1;
                            carrera[posicionPiedra][1] = 2;

                            mostrarMatriz(n, carrera);
                        } else if (t1 == t2
                                && t1 < costos[2]) {

                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        }

                    } else if (costos[0] == costos[1]
                            && costos[0] < costos[2]) {
                        igualaMatriz(memoriza, carrera, n);
                        //System.out.println();
                        //mostrarMatriz(n, memoriza);
                        //System.out.println();

                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
			copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);
                        //costosMemoriza = costos;
                        //costosEnRama = copiaCosto;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;

                        //El tiempo de Espera de la tijera derrotando a los Enemigos
                        //CREAMOS A LOS TIOS, PARA SABER SI EL VALOR DE SUS HIJOS ES MENOR AL DE LA RAMIFICACIÓN POR LA QUE NOS FUIMOS O NO
                        //Generamos el primer Tio, que es la rama que se crea, si es que la piedra se hubiera quedado:
                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        //System.out.println();
                        //mostrarMatriz(n, tableroTio1);
                        //System.out.println();
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemorizaTio1[] = costosMemoriza;
                        //int copiaCostoTio1[] = costosEnRama;
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        //TABLA DEL TIO 1
                        piedraTioEsp = copiaCostoTio1[1];
                        piedraTioAtaca = true;
                        //System.out.println("El costo de la piedra en el tio 1: " + piedraTioEsp);

                        tableroTio1[posicionTioTijera + 1][0] = 0;
                        tableroTio1[posicionTioPiedra + 1][1] = 0;
                        tableroTio1[posicionTioPapel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioTijera][0] = 0;
                        tableroTio1[posicionTioPapel][2] = 0;

                        posicionTioTijera = posicionTioTijera + 2;
                        posicionTioPapel = posicionTioPapel + 2;

                        tableroTio1[posicionTioTijera][0] = 1;
                        tableroTio1[posicionTioPapel][2] = 3;
                        //System.out.println("El tablero de tio 1 despues de realizar sus movvimientos queda como : ");
                        //mostrarMatriz(n, tableroTio1);

                        int[][] tablero2Tio = new int[n][3];
                        igualaMatriz(tablero2Tio, memoriza, n);
                        //System.out.println();
                        mostrarMatriz(n, tablero2Tio);
                        System.out.println();
                        int tijeraTio2Esp = copiatijeraEspera;
                        int piedraTio2Esp = copiapiedraEspera;
                        int papelTio2Esp = copiapapelEspera;
                        int posicionTio2Tijera = copiaposicionTijera;
                        int posicionTio2Piedra = copiaposicionPiedra;
                        int posicionTio2Papel = copiaposicionPapel;
                        int costosMemoriza2Tio[] = {0,0,0};
                        copiaVector(costosMemoriza2Tio, costosMemoriza);
                        int copiaCosto2Tio[] = {0,0,0};
                        copiaVector(copiaCosto2Tio, costosEnRama);
                        //int costosMemoriza2Tio[] = costosMemoriza;
                        //int copiaCosto2Tio[] = costosEnRama;
                        boolean tijeraTio2Ataca = copiatijeraAtaca;
                        boolean piedraTio2Ataca = copiapiedraAtaca;
                        boolean papelTio2Ataca = copiapapelAtaca;

                        //TABLA DEL TIO 2
                        //Generamos el segundo Tio, que es la rama que se crea, si es que el papel se hubiera quedado:
                        papelTio2Esp = copiaCosto2Tio[2];
                        papelTio2Ataca = true;
                        //System.out.println("El costo de papel en el tio 2: " + papelTio2Esp);

                        tablero2Tio[posicionTio2Tijera + 1][0] = 0;
                        tablero2Tio[posicionTio2Piedra + 1][1] = 0;
                        tablero2Tio[posicionTio2Papel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tablero2Tio[posicionTio2Tijera][0] = 0;
                        tablero2Tio[posicionTio2Piedra][1] = 0;

                        posicionTio2Piedra = posicionTio2Piedra + 2;
                        posicionTio2Tijera = posicionTio2Tijera + 2;

                        tablero2Tio[posicionTio2Tijera][0] = 1;
                        tablero2Tio[posicionTio2Piedra][1] = 2;
                        //System.out.println("El tablero de tio 2 despues de realizar sus movvimientos queda como : ");
                        // mostrarMatriz(n, tablero2Tio);
                        //System.out.println();

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        EstructuraJuego tio2 = new EstructuraJuego(tablero2Tio, posicionTio2Tijera, posicionTio2Piedra, posicionTio2Papel, tijeraTio2Esp, piedraTio2Esp, papelTio2Esp);

                        int t1 = continuaJugando(tio1.getTablero(), tio1.getPosicionTijera(), tio1.getPosicionPiedra(), tio1.getPosicionPapel(), tio1.getTijeraEspera(), tio1.getPiedraEspera(), tio1.getPapelEspera(), tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);
                        int t2 = continuaJugando(tio1.getTablero(), tio1.getPosicionTijera(), tio2.getPosicionPiedra(), tio2.getPosicionPapel(), tio2.getTijeraEspera(), tio2.getPiedraEspera(), tio2.getPapelEspera(), tijeraTio2Ataca, piedraTio2Ataca, papelTio2Ataca, costosMemoriza2Tio, copiaCosto2Tio);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[0]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);
                        System.out.println("EL TIO 2 TIENE UN MEJOR COSTO DE : " + t2);
                        if (costos[0] < t1
                                && costos[0] < t2) {

                            //ESTO QUE SIGUE, ES LA ELECCION MEJOR DE LA RAMA ACTUAL, PERO TAMBIÉN DEBEMOS ANALIZAR A LOS TIOS
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionPiedra = posicionPiedra + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPiedra][1] = 2;
                            carrera[posicionPapel][2] = 3;
                        } else if (t2 < costos[0]
                                && t2 < t1) {

                            igualaMatriz(carrera, tablero2Tio, n);
                            posicionTijera = posicionTio2Tijera;
                            posicionPiedra = posicionTio2Piedra;
                            posicionPapel = posicionTio2Papel;
                            tijeraEspera = tijeraTio2Esp;
                            piedraEspera = piedraTio2Esp;
                            papelEspera = papelTio2Esp;
                            copiaVector(costos, costosMemoriza2Tio);
                            copiaVector(copiaCosto, copiaCosto2Tio);
                            //costos = costosMemoriza2Tio;
                            //copiaCosto = copiaCosto2Tio;
                            tijeraAtaca = tijeraTio2Ataca;
                            piedraAtaca = piedraTio2Ataca;
                            papelAtaca = papelTio2Ataca;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[0]
                                && t1 < t2) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[0] == t1
                                && costos[0] < t2) {
                            //ESTO QUE SIGUE, ES LA ELECCION MEJOR DE LA RAMA ACTUAL, PERO TAMBIÉN DEBEMOS ANALIZAR A LOS TIOS
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionPiedra = posicionPiedra + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPiedra][1] = 2;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (costos[0] == t2
                                && costos[0] < t1) {
                            //ESTO QUE SIGUE, ES LA ELECCION MEJOR DE LA RAMA ACTUAL, PERO TAMBIÉN DEBEMOS ANALIZAR A LOS TIOS
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionPiedra = posicionPiedra + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPiedra][1] = 2;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (t1 == t2
                                && t1 < costos[0]) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        }

                    } else if (costos[0] == costos[1]
                            && costos[0] == costos[2]) {
                        igualaMatriz(memoriza, carrera, n);
                        //System.out.println();
                        //System.out.println("Estoy memorizando este vector: ");
                        //mostrarMatriz(n, memoriza);
                        //System.out.println();

                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
                        copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);
                        //costosMemoriza = costos;
                        //costosEnRama = copiaCosto;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;

                        //CREAMOS A LOS TIOS, PARA SABER SI EL VALOR DE SUS HIJOS ES MENOR AL DE LA RAMIFICACIÓN POR LA QUE NOS FUIMOS O NO
                        //Generamos el primer Tio, que es la rama que se crea, si es que la piedra se hubiera quedado:
                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        //System.out.println();
                        //mostrarMatriz(n, tableroTio1);
                        //System.out.println();
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemorizaTio1[] = costosMemoriza;
                        //int copiaCostoTio1[] = costosEnRama;
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        //TABLA DEL TIO 1
                        piedraTioEsp = copiaCostoTio1[1];
                        piedraTioAtaca = true;
                        //System.out.println("El costo de la piedra en el tio 1: " + piedraTioEsp);

                        tableroTio1[posicionTioTijera + 1][0] = 0;
                        tableroTio1[posicionTioPiedra + 1][1] = 0;
                        tableroTio1[posicionTioPapel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioTijera][0] = 0;
                        tableroTio1[posicionTioPapel][2] = 0;

                        posicionTioTijera = posicionTioTijera + 2;
                        posicionTioPapel = posicionTioPapel + 2;

                        tableroTio1[posicionTioTijera][0] = 1;
                        tableroTio1[posicionTioPapel][2] = 3;
                        //System.out.println("El tablero de tio 1 despues de realizar sus movvimientos queda como : ");
                        //mostrarMatriz(n, tableroTio1);

                        int[][] tablero2Tio = new int[n][3];
                        igualaMatriz(tablero2Tio, memoriza, n);
                        //System.out.println();
                        //mostrarMatriz(n, tablero2Tio);
                        //System.out.println();
                        int tijeraTio2Esp = copiatijeraEspera;
                        int piedraTio2Esp = copiapiedraEspera;
                        int papelTio2Esp = copiapapelEspera;
                        int posicionTio2Tijera = copiaposicionTijera;
                        int posicionTio2Piedra = copiaposicionPiedra;
                        int posicionTio2Papel = copiaposicionPapel;
                        int costosMemoriza2Tio[] = {0,0,0};
                        copiaVector(costosMemoriza2Tio, costosMemoriza);
                        int copiaCosto2Tio[] = {0,0,0};
                        copiaVector(copiaCosto2Tio, costosEnRama);
                        //int costosMemoriza2Tio[] = costosMemoriza;
                        //int copiaCosto2Tio[] = costosEnRama;
                        boolean tijeraTio2Ataca = copiatijeraAtaca;
                        boolean piedraTio2Ataca = copiapiedraAtaca;
                        boolean papelTio2Ataca = copiapapelAtaca;

                        //TABLA DEL TIO 2
                        //Generamos el segundo Tio, que es la rama que se crea, si es que el papel se hubiera quedado:
                        papelTio2Esp = copiaCosto2Tio[2];
                        papelTio2Ataca = true;

                        tablero2Tio[posicionTio2Tijera + 1][0] = 0;
                        tablero2Tio[posicionTio2Piedra + 1][1] = 0;
                        tablero2Tio[posicionTio2Papel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tablero2Tio[posicionTio2Tijera][0] = 0;
                        tablero2Tio[posicionTio2Piedra][1] = 0;

                        posicionTio2Piedra = posicionTio2Piedra + 2;
                        posicionTio2Tijera = posicionTio2Tijera + 2;

                        tablero2Tio[posicionTio2Tijera][0] = 1;
                        tablero2Tio[posicionTio2Piedra][1] = 2;
                        //System.out.println("El tablero de tio 2 despues de realizar sus movvimientos queda como : ");
                        // mostrarMatriz(n, tablero2Tio);
                        //System.out.println();

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        EstructuraJuego tio2 = new EstructuraJuego(tablero2Tio, posicionTio2Tijera, posicionTio2Piedra, posicionTio2Papel, tijeraTio2Esp, piedraTio2Esp, papelTio2Esp);

                        int t1 = continuaJugando(tio1.getTablero(), tio1.getPosicionTijera(), tio1.getPosicionPiedra(), tio1.getPosicionPapel(), tio1.getTijeraEspera(), tio1.getPiedraEspera(), tio1.getPapelEspera(), tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);
                        int t2 = continuaJugando(tio1.getTablero(), tio1.getPosicionTijera(), tio2.getPosicionPiedra(), tio2.getPosicionPapel(), tio2.getTijeraEspera(), tio2.getPiedraEspera(), tio2.getPapelEspera(), tijeraTio2Ataca, piedraTio2Ataca, papelTio2Ataca, costosMemoriza2Tio, copiaCosto2Tio);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[0]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);
                        System.out.println("EL TIO 2 TIENE UN MEJOR COSTO DE : " + t2);
                        if (costos[0] < t1
                                && costos[0] < t2) {
                            //ESTO QUE SIGUE, ES LA ELECCION MEJOR DE LA RAMA ACTUAL, PERO TAMBIÉN DEBEMOS ANALIZAR A LOS TIOS
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionPiedra = posicionPiedra + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPiedra][1] = 2;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (t2 < costos[0]
                                && t2 < t1) {

                            igualaMatriz(carrera, tablero2Tio, n);
                            posicionTijera = posicionTio2Tijera;
                            posicionPiedra = posicionTio2Piedra;
                            posicionPapel = posicionTio2Papel;
                            tijeraEspera = tijeraTio2Esp;
                            piedraEspera = piedraTio2Esp;
                            papelEspera = papelTio2Esp;
                            copiaVector(costos, costosMemoriza2Tio);
                            copiaVector(copiaCosto, copiaCosto2Tio);
                            //costos = costosMemoriza2Tio;
                            //copiaCosto = copiaCosto2Tio;
                            tijeraAtaca = tijeraTio2Ataca;
                            piedraAtaca = piedraTio2Ataca;
                            papelAtaca = papelTio2Ataca;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[0]
                                && t1 < t2) {

                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[0] == t1
                                && costos[0] < t2) {
                            //ESTO QUE SIGUE, ES LA ELECCION MEJOR DE LA RAMA ACTUAL, PERO TAMBIÉN DEBEMOS ANALIZAR A LOS TIOS
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionPiedra = posicionPiedra + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPiedra][1] = 2;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (costos[0] == t2
                                && costos[0] < t1) {
                            //ESTO QUE SIGUE, ES LA ELECCION MEJOR DE LA RAMA ACTUAL, PERO TAMBIÉN DEBEMOS ANALIZAR A LOS TIOS
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;
                            carrera[posicionPapel][2] = 0;

                            posicionPiedra = posicionPiedra + 2;
                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPiedra][1] = 2;
                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (t1 == t2
                                && t1 < costos[0]) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        }
                    }
                }
            } else if ((posicionTijera + 1 >= n) || posicionPiedra + 1 >= n || posicionPapel + 1 >= n) {
                System.out.println("TENEMOS UN GANADOR!!! :V");
                if (posicionTijera + 1 >= n) {
                    System.out.println("El ganador es las TIJERAS");
                    break;
                } else if (posicionPiedra + 1 >= n) {
                    System.out.println("El ganador es la PIEDRA");
                    break;
                } else if (posicionPapel + 1 >= n) {
                    System.out.println("El ganador es el PAPEL");
                    break;
                }
                break;
            } else if (carrera[posicionTijera + 1][0] == 0 && carrera[posicionPiedra + 1][1] != 0 && carrera[posicionPapel + 1][2] != 0) {
                calculaCosto(3, carrera[posicionPiedra + 1][0], carrera[posicionPiedra + 1][1], carrera[posicionPapel + 1][2], costos, copiaCosto);
                calculaCosto(2, carrera[posicionPiedra + 1][0], carrera[posicionPiedra + 1][1], carrera[posicionPapel + 1][2], costos, copiaCosto);

                if (posicionPiedra == posicionPapel) {
                    if (costos[2] < costos[1]) {
                        igualaMatriz(memoriza, carrera, n);
                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;
                        copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);

                        //CREAMOS A LOS TIOS, PARA SABER SI EL VALOR DE SUS HIJOS ES MENOR AL DE LA RAMIFICACIÓN POR LA QUE NOS FUIMOS O NO
                        //Generamos el primer Tio, que es la rama que se crea, si es que la piedra se hubiera quedado:
                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        //System.out.println();
                        mostrarMatriz(n, tableroTio1);
                        System.out.println();
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemorizaTio1[] = costosMemoriza;
                        //int copiaCostoTio1[] = costosEnRama;
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        //TABLA DEL TIO 1
                        piedraTioEsp = copiaCostoTio1[1];
                        piedraTioAtaca = true;
                        //System.out.println("El costo de la piedra en el tio 1: " + piedraTioEsp);

                        tableroTio1[posicionTioPiedra + 1][0] = 0;
                        tableroTio1[posicionTioPiedra + 1][1] = 0;
                        tableroTio1[posicionTioPapel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioPapel][2] = 0;

                        posicionTioPapel = posicionTioPapel + 2;

                        tableroTio1[posicionTioPapel][2] = 3;

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        int t1 = continuaJugando(tio1.getTablero(), tio1.getPosicionTijera(), tio1.getPosicionPiedra(), tio1.getPosicionPapel(), tio1.getTijeraEspera(), tio1.getPiedraEspera(), tio1.getPapelEspera(), tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[2]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);
                        if (costos[2] < t1) {
                            papelEspera = copiaCosto[2];
                            papelAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionPiedra + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;

                            posicionPiedra = posicionPiedra + 2;

                            carrera[posicionPiedra][1] = 2;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[2]) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[2] == t1) {
                            papelEspera = copiaCosto[2];
                            papelAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionPiedra + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;

                            posicionPiedra = posicionPiedra + 2;

                            carrera[posicionPiedra][1] = 2;

                            mostrarMatriz(n, carrera);
                        }
                    } else if (costos[1] < costos[2]) {
                        igualaMatriz(memoriza, carrera, n);
                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;
                        copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);

                        //CREAMOS A LOS TIOS, PARA SABER SI EL VALOR DE SUS HIJOS ES MENOR AL DE LA RAMIFICACIÓN POR LA QUE NOS FUIMOS O NO
                        //Generamos el primer Tio, que es la rama que se crea, si es que la piedra se hubiera quedado:
                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        //System.out.println();
                        //mostrarMatriz(n, tableroTio1);
                        //System.out.println();
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemorizaTio1[] = costosMemoriza;
                        //int copiaCostoTio1[] = costosEnRama;
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        //TABLA DEL TIO 1
                        papelTioEsp = copiaCostoTio1[2];
                        papelTioAtaca = true;

                        tableroTio1[posicionTioPiedra + 1][0] = 0;
                        tableroTio1[posicionTioPiedra + 1][1] = 0;
                        tableroTio1[posicionTioPapel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioPiedra][1] = 0;

                        posicionTioPiedra = posicionTioPiedra + 2;

                        tableroTio1[posicionTioPiedra][1] = 2;

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        int t1 = continuaJugando(tio1.getTablero(), tio1.getPosicionTijera(), tio1.getPosicionPiedra(), tio1.getPosicionPapel(), tio1.getTijeraEspera(), tio1.getPiedraEspera(), tio1.getPapelEspera(), tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[1]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);

                        if (costos[1] < t1) {
                            //El tiempo de Espera de la piedra derrotando a los Enemigos
                            piedraEspera = copiaCosto[1];
                            piedraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionPiedra + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPapel][2] = 0;

                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[1]) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[1] == t1) {
                            piedraEspera = copiaCosto[1];
                            piedraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionPiedra + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPapel][2] = 0;

                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        }
                    } else if (costos[2] == costos[1]) {
                        igualaMatriz(memoriza, carrera, n);
                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;
                        copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);

                        //CREAMOS A LOS TIOS, PARA SABER SI EL VALOR DE SUS HIJOS ES MENOR AL DE LA RAMIFICACIÓN POR LA QUE NOS FUIMOS O NO
                        //Generamos el primer Tio, que es la rama que se crea, si es que la piedra se hubiera quedado:
                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        //System.out.println();
                        mostrarMatriz(n, tableroTio1);
                        System.out.println();
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemorizaTio1[] = costosMemoriza;
                        //int copiaCostoTio1[] = costosEnRama;
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        //TABLA DEL TIO 1
                        papelTioEsp = copiaCostoTio1[2];
                        papelTioAtaca = true;
                        //System.out.println("El costo de la piedra en el tio 1: " + piedraTioEsp);

                        tableroTio1[posicionTioPiedra + 1][0] = 0;
                        tableroTio1[posicionTioPiedra + 1][1] = 0;
                        tableroTio1[posicionTioPapel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioPiedra][1] = 0;

                        posicionTioPiedra = posicionTioPiedra + 2;

                        tableroTio1[posicionTioPiedra][1] = 2;

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        int t1 = continuaJugando(tio1.getTablero(), tio1.getPosicionTijera(), tio1.getPosicionPiedra(), tio1.getPosicionPapel(), tio1.getTijeraEspera(), tio1.getPiedraEspera(), tio1.getPapelEspera(), tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[1]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);

                        if (costos[1] < t1) {
                            //El tiempo de Espera de la piedra derrotando a los Enemigos
                            piedraEspera = copiaCosto[1];
                            piedraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionPiedra + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPapel][2] = 0;

                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPapel][2] = 3;
                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[1]) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[1] == t1) {
                            piedraEspera = copiaCosto[1];
                            piedraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionPiedra + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPapel][2] = 0;

                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        }
                    }
                }
            } else if (carrera[posicionTijera + 1][0] != 0 && carrera[posicionPiedra + 1][1] != 0 && carrera[posicionPapel + 1][2] == 0) {
                if (posicionTijera == posicionPiedra) {
                    calculaCosto(1, carrera[posicionTijera + 1][0], carrera[posicionPiedra + 1][1], carrera[posicionPiedra + 1][2], costos, copiaCosto);
                    calculaCosto(2, carrera[posicionTijera + 1][0], carrera[posicionPiedra + 1][1], carrera[posicionPiedra + 1][2], costos, copiaCosto);

                    if (costos[0] < costos[1]) {
                        igualaMatriz(memoriza, carrera, n);
                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;
                        copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);

                        //CREAMOS A LOS TIOS, PARA SABER SI EL VALOR DE SUS HIJOS ES MENOR AL DE LA RAMIFICACIÓN POR LA QUE NOS FUIMOS O NO
                        //Generamos el primer Tio, que es la rama que se crea, si es que papel se hubiera quedado:
                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        System.out.println();
                        mostrarMatriz(n, tableroTio1);
                        System.out.println();
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemorizaTio1[] = costosMemoriza;
                        //int copiaCostoTio1[] = costosEnRama;
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        //TABLA DEL TIO 1
                        piedraTioEsp = copiaCostoTio1[1];
                        piedraTioAtaca = true;
                        //System.out.println("El costo de la piedra en el tio 1: " + piedraTioEsp);

                        tableroTio1[posicionTioTijera + 1][0] = 0;
                        tableroTio1[posicionTioPiedra + 1][1] = 0;
                        tableroTio1[posicionTioPiedra + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioTijera][0] = 0;

                        posicionTioTijera = posicionTioTijera + 2;

                        tableroTio1[posicionTioTijera][0] = 1;

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        int t1 = continuaJugando(tio1.getTablero(), tio1.getPosicionTijera(), tio1.getPosicionPiedra(), tio1.getPosicionPapel(), tio1.getTijeraEspera(), tio1.getPiedraEspera(), tio1.getPapelEspera(), tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[0]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);

                        if (costos[0] < t1) {
                            //El tiempo de Espera de la tijera derrotando a los Enemigos
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPiedra + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;

                            posicionPiedra = posicionPiedra + 2;

                            carrera[posicionPiedra][1] = 2;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[0]) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[0] == t1) {
                            //El tiempo de Espera de la tijera derrotando a los Enemigos
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPiedra + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;

                            posicionPiedra = posicionPiedra + 2;

                            carrera[posicionPiedra][1] = 2;

                            mostrarMatriz(n, carrera);
                        }
                    } else if (costos[1] < costos[0]) {
                        igualaMatriz(memoriza, carrera, n);
                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;
                        copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);

                        //CREAMOS A LOS TIOS, PARA SABER SI EL VALOR DE SUS HIJOS ES MENOR AL DE LA RAMIFICACIÓN POR LA QUE NOS FUIMOS O NO
                        //Generamos el primer Tio, que es la rama que se crea, si es que la piedra se hubiera quedado:
                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        //System.out.println();
                        mostrarMatriz(n, tableroTio1);
                        System.out.println();
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemorizaTio1[] = costosMemoriza;
                        //int copiaCostoTio1[] = costosEnRama;
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        //TABLA DEL TIO 1
                        tijeraTioEsp = copiaCostoTio1[0];
                        tijeraTioAtaca = true;
                        //System.out.println("El costo de la piedra en el tio 1: " + piedraTioEsp);

                        tableroTio1[posicionTioTijera + 1][0] = 0;
                        tableroTio1[posicionTioPiedra + 1][1] = 0;
                        tableroTio1[posicionTioPiedra + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioPiedra][1] = 0;

                        posicionTioPiedra = posicionTioPiedra + 2;

                        tableroTio1[posicionTioPiedra][1] = 2;

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        int t1 = continuaJugando(tio1.getTablero(), tio1.getPosicionTijera(), tio1.getPosicionPiedra(), tio1.getPosicionPapel(), tio1.getTijeraEspera(), tio1.getPiedraEspera(), tio1.getPapelEspera(), tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[1]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);

                        if (costos[1] < t1) {
                            //El tiempo de Espera de la piedra derrotando a los Enemigos
                            piedraEspera = copiaCosto[1];
                            piedraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPiedra + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;

                            posicionTijera = posicionTijera + 2;

                            carrera[posicionTijera][0] = 1;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[1]) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[1] == t1) {
                            //El tiempo de Espera de la piedra derrotando a los Enemigos
                            piedraEspera = copiaCosto[1];
                            piedraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPiedra + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;

                            posicionTijera = posicionTijera + 2;

                            carrera[posicionTijera][0] = 1;

                            mostrarMatriz(n, carrera);
                        }
                    } else if (costos[0] == costos[1]) {
                        igualaMatriz(memoriza, carrera, n);
                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;
                        copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);

                        //CREAMOS A LOS TIOS, PARA SABER SI EL VALOR DE SUS HIJOS ES MENOR AL DE LA RAMIFICACIÓN POR LA QUE NOS FUIMOS O NO
                        //Generamos el primer Tio, que es la rama que se crea, si es que papel se hubiera quedado:
                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        //System.out.println();
                        //mostrarMatriz(n, tableroTio1);
                        //System.out.println();
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemorizaTio1[] = costosMemoriza;
                        //int copiaCostoTio1[] = costosEnRama;
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        //TABLA DEL TIO 1
                        piedraTioEsp = copiaCostoTio1[1];
                        piedraTioAtaca = true;
                        //System.out.println("El costo de la piedra en el tio 1: " + piedraTioEsp);

                        tableroTio1[posicionTioTijera + 1][0] = 0;
                        tableroTio1[posicionTioPiedra + 1][1] = 0;
                        tableroTio1[posicionTioPiedra + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioTijera][0] = 0;

                        posicionTioTijera = posicionTioTijera + 2;

                        tableroTio1[posicionTioTijera][0] = 1;

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        int t1 = continuaJugando(tio1.getTablero(), tio1.getPosicionTijera(), tio1.getPosicionPiedra(), tio1.getPosicionPapel(), tio1.getTijeraEspera(), tio1.getPiedraEspera(), tio1.getPapelEspera(), tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[0]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);

                        if (costos[0] < t1) {
                            //El tiempo de Espera de la tijera derrotando a los Enemigos
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPiedra + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;

                            posicionPiedra = posicionPiedra + 2;

                            carrera[posicionPiedra][1] = 2;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[0]) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[0] == t1) {
                            //El tiempo de Espera de la tijera derrotando a los Enemigos
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPiedra + 1][1] = 0;
                            carrera[posicionPiedra + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPiedra][1] = 0;

                            posicionPiedra = posicionPiedra + 2;

                            carrera[posicionPiedra][1] = 2;
                            mostrarMatriz(n, carrera);
                        }

                    }
                }
            } else if (carrera[posicionTijera + 1][0] != 0 && carrera[posicionPiedra + 1][1] == 0 && carrera[posicionPapel + 1][2] != 0) {
                calculaCosto(1, carrera[posicionTijera + 1][0], carrera[posicionPapel + 1][1], carrera[posicionPapel + 1][2], costos, copiaCosto);
                System.out.println("EL COSTO DE LA TIJERA EN SUMA DE RAMA ES : " + costos[0] + " Y EL COSTO EN SU RAMA ACTUAL ES : " + copiaCosto[0]);
                calculaCosto(3, carrera[posicionTijera + 1][0], carrera[posicionPapel + 1][1], carrera[posicionPapel + 1][2], costos, copiaCosto);
                System.out.println("EL COSTO DEL PAPEL EN SUMA DE RAMA ES : " + costos[2] + " Y EL COSTO EN SU RAMA ACTUAL ES : " + copiaCosto[2]);
                System.out.println("La posicion de la tijera es : " + posicionTijera);
                System.out.println("La posicion del papel es : " + posicionPapel);
                if (posicionTijera == posicionPapel) {
                    System.out.println("DENTRO DEL IF ENTRARON CON LOS VALORES DE : " + costos[0] + " Y : " + costos[2]);

                    if (costos[0] < costos[2]) {
                        igualaMatriz(memoriza, carrera, n);
                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;
                        copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);
                        
                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        //System.out.println();
                        //mostrarMatriz(n, tableroTio1);
                        //System.out.println();
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemorizaTio1[] = costosMemoriza;
                        //int copiaCostoTio1[] = costosEnRama;
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        //TABLA DEL TIO 1
                        papelTioEsp = copiaCostoTio1[2];
                        papelTioAtaca = true;
                        //System.out.println("El costo de la piedra en el tio 1: " + piedraTioEsp);

                        tableroTio1[posicionTioTijera + 1][0] = 0;
                        tableroTio1[posicionTioTijera + 1][1] = 0;
                        tableroTio1[posicionTioPapel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioTijera][0] = 0;

                        posicionTioTijera = posicionTioTijera + 2;

                        tableroTio1[posicionTioTijera][0] = 1;

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        int t1 = continuaJugando(tio1.getTablero(), tio1.getPosicionTijera(), tio1.getPosicionPiedra(), tio1.getPosicionPapel(), tio1.getTijeraEspera(), tio1.getPiedraEspera(), tio1.getPapelEspera(), tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[0]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);

                        if (costos[0] < t1) {
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPapel + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPapel][2] = 0;

                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPapel][2] = 3;
                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[0]) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[0] == t1) {
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPapel + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPapel][2] = 0;

                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        }
                    } else if (costos[2] < costos[0]) {
                        igualaMatriz(memoriza, carrera, n);
                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;
                        copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);

                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        //System.out.println();
                        mostrarMatriz(n, tableroTio1);
                        System.out.println();
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemorizaTio1[] = costosMemoriza;
                        //int copiaCostoTio1[] = costosEnRama;
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        //TABLA DEL TIO 1
                        tijeraTioEsp = copiaCostoTio1[0];
                        tijeraTioAtaca = true;
                        //System.out.println("El costo de la piedra en el tio 1: " + piedraTioEsp);

                        tableroTio1[posicionTioTijera + 1][0] = 0;
                        tableroTio1[posicionTioTijera + 1][1] = 0;
                        tableroTio1[posicionTioPapel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioPapel][2] = 0;

                        posicionTioPapel = posicionTioPapel + 2;

                        tableroTio1[posicionTioPapel][2] = 3;

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        int t1 = continuaJugando(tio1.getTablero(), tio1.getPosicionTijera(), tio1.getPosicionPiedra(), tio1.getPosicionPapel(), tio1.getTijeraEspera(), tio1.getPiedraEspera(), tio1.getPapelEspera(), tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[2]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);

                        if (costos[2] < t1) {
                            papelEspera = copiaCosto[2];
                            papelAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPapel + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;

                            posicionTijera = posicionTijera + 2;

                            carrera[posicionTijera][0] = 1;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[2]) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[2] == t1) {
                            papelEspera = copiaCosto[2];
                            papelAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPapel + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionTijera][0] = 0;

                            posicionTijera = posicionTijera + 2;

                            carrera[posicionTijera][0] = 1;

                            mostrarMatriz(n, carrera);
                        }
                    } else if (costos[2] == costos[0]) {
                        igualaMatriz(memoriza, carrera, n);
                        copiatijeraEspera = tijeraEspera;
                        copiapiedraEspera = piedraEspera;
                        copiapapelEspera = papelEspera;
                        copiaposicionTijera = posicionTijera;
                        copiaposicionPiedra = posicionPiedra;
                        copiaposicionPapel = posicionPapel;
                        copiatijeraAtaca = tijeraAtaca;
                        copiapiedraAtaca = piedraAtaca;
                        copiapapelAtaca = papelAtaca;
                        copiaVector(costosMemoriza, costos);
                        copiaVector(costosEnRama , copiaCosto);
                        
                        int[][] tableroTio1 = new int[n][3];
                        igualaMatriz(tableroTio1, memoriza, n);
                        //System.out.println();
                        // mostrarMatriz(n, tableroTio1);
                        //System.out.println();
                        int tijeraTioEsp = copiatijeraEspera;
                        int piedraTioEsp = copiapiedraEspera;
                        int papelTioEsp = copiapapelEspera;
                        int posicionTioTijera = copiaposicionTijera;
                        int posicionTioPiedra = copiaposicionPiedra;
                        int posicionTioPapel = copiaposicionPapel;
                        int costosMemorizaTio1[] = {0,0,0};
                        copiaVector(costosMemorizaTio1, costosMemoriza);
                        int copiaCostoTio1[] = {0,0,0};
                        copiaVector(copiaCostoTio1, costosEnRama);
                        //int costosMemorizaTio1[] = costosMemoriza;
                        //int copiaCostoTio1[] = costosEnRama;
                        boolean tijeraTioAtaca = copiatijeraAtaca;
                        boolean piedraTioAtaca = copiapiedraAtaca;
                        boolean papelTioAtaca = copiapapelAtaca;

                        //TABLA DEL TIO 1
                        papelTioEsp = copiaCostoTio1[2];
                        papelTioAtaca = true;
                        //System.out.println("El costo de la piedra en el tio 1: " + piedraTioEsp);

                        tableroTio1[posicionTioTijera + 1][0] = 0;
                        tableroTio1[posicionTioTijera + 1][1] = 0;
                        tableroTio1[posicionTioPapel + 1][2] = 0;

                        //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                        tableroTio1[posicionTioTijera][0] = 0;

                        posicionTioTijera = posicionTioTijera + 2;

                        tableroTio1[posicionTioTijera][0] = 1;

                        EstructuraJuego tio1 = new EstructuraJuego(tableroTio1, posicionTioTijera, posicionTioPiedra, posicionTioPapel, tijeraTioEsp, piedraTioEsp, papelTioEsp);
                        int t1 = continuaJugando(tio1.getTablero(), tio1.getPosicionTijera(), tio1.getPosicionPiedra(), tio1.getPosicionPapel(), tio1.getTijeraEspera(), tio1.getPiedraEspera(), tio1.getPapelEspera(), tijeraTioAtaca, piedraTioAtaca, papelTioAtaca, costosMemorizaTio1, copiaCostoTio1);

                        System.out.println("MI MEJOR ELECCION EN MI RAMIFICACION ACTUAL ES DE : " + costos[0]);
                        System.out.println("EL TIO 1 TIENE UN MEJOR COSTO DE : " + t1);

                        if (costos[0] < t1) {
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPapel + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPapel][2] = 0;

                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        } else if (t1 < costos[0]) {
                            igualaMatriz(carrera, tableroTio1, n);
                            posicionTijera = posicionTioTijera;
                            posicionPiedra = posicionTioPiedra;
                            posicionPapel = posicionTioPapel;
                            tijeraEspera = tijeraTioEsp;
                            piedraEspera = piedraTioEsp;
                            papelEspera = papelTioEsp;
                            copiaVector(costos, costosMemorizaTio1);
                            copiaVector(copiaCosto, copiaCostoTio1);
                            //costos = costosMemorizaTio1;
                            //copiaCosto = copiaCostoTio1;
                            tijeraAtaca = tijeraTioAtaca;
                            piedraAtaca = piedraTioAtaca;
                            papelAtaca = papelTioAtaca;

                            mostrarMatriz(n, carrera);
                        } else if (costos[0] == t1) {
                            tijeraEspera = copiaCosto[0];
                            tijeraAtaca = true;

                            //Ponemos los enemigos en 0
                            carrera[posicionTijera + 1][0] = 0;
                            carrera[posicionPapel + 1][1] = 0;
                            carrera[posicionPapel + 1][2] = 0;

                            //MOVEMOS A LOS ROBOTS QUE SIGUEN AVANZANDO
                            carrera[posicionPapel][2] = 0;

                            posicionPapel = posicionPapel + 2;

                            carrera[posicionPapel][2] = 3;

                            mostrarMatriz(n, carrera);
                        }

                    }
                }
            } //LAS SIGUIENTES CONDICIONES SON PARA CUANDO SOLO HAY UN ROBOT Y LLEGA A UNOS OBSTACULOS
            else if (carrera[posicionTijera + 1][0] != 0 && carrera[posicionTijera][1] == 0 && carrera[posicionTijera][2] == 0) {
                calculaCosto(1, carrera[posicionTijera + 1][0], carrera[posicionTijera + 1][1], carrera[posicionTijera + 1][2], costos, copiaCosto);

                tijeraEspera = copiaCosto[0];
                tijeraAtaca = true;

                //Ponemos los enemigos en 0
                carrera[posicionTijera + 1][0] = 0;
                carrera[posicionTijera + 1][1] = 0;
                carrera[posicionTijera + 1][2] = 0;

                mostrarMatriz(n, carrera);
            } else if (carrera[posicionPiedra + 1][1] != 0 && carrera[posicionPiedra][0] == 0 && carrera[posicionPiedra][2] == 0) {
                calculaCosto(2, carrera[posicionPiedra + 1][0], carrera[posicionPiedra + 1][1], carrera[posicionPiedra + 1][2], costos, copiaCosto);

                piedraEspera = copiaCosto[1];
                piedraAtaca = true;

                //Ponemos los enemigos en 0
                carrera[posicionPiedra + 1][0] = 0;
                carrera[posicionPiedra + 1][1] = 0;
                carrera[posicionPiedra + 1][2] = 0;

                mostrarMatriz(n, carrera);
            } else if (carrera[posicionPapel + 1][2] != 0 && carrera[posicionPapel][0] == 0 && carrera[posicionPapel][1] == 0) {
                calculaCosto(3, carrera[posicionPapel + 1][0], carrera[posicionPapel + 1][1], carrera[posicionPapel + 1][2], costos, copiaCosto);

                papelEspera = copiaCosto[2];
                papelAtaca = true;

                //Ponemos los enemigos en 0
                carrera[posicionPapel + 1][0] = 0;
                carrera[posicionPapel + 1][1] = 0;
                carrera[posicionPapel + 1][2] = 0;

                mostrarMatriz(n, carrera);
            }

            System.out.println();
            mostrarMatriz(n, carrera);

            if (posicionTijera >= n) {
                acabo = true;
                JOptionPane.showMessageDialog(null, "LA TIJERA HA GANADO!");

            }
            if (posicionPiedra >= n) {
                acabo = true;
                JOptionPane.showMessageDialog(null, "LA PIEDRA HA GANADO!");
            }
            if (posicionPapel >= n) {
                acabo = true;
                JOptionPane.showMessageDialog(null, "EL PAPEL HA GANADO!");
            }
        }

    }
