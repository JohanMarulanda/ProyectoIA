/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intentoia;

/**
 *
 * @author Johan
 */
public class EstructuraJuego {
    int[][] tablero;
    int posicionTijera;
    int posicionPiedra;
    int posicionPapel;
    int tijeraEspera;
    int piedraEspera;
    int papelEspera;
    
    public EstructuraJuego(int[][] tablero, int posicionTijera, int posicionPiedra, int posicionPapel, int tijeraEspera, int piedraEspera, int papelEspera){
        this.tablero = tablero;
        this.posicionTijera = posicionTijera;
        this.posicionPiedra = posicionPiedra;
        this.posicionPapel = posicionPapel;
        this.tijeraEspera = tijeraEspera;
        this.piedraEspera = piedraEspera;
        this.papelEspera = papelEspera;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    public void setPosicionTijera(int posicionTijera) {
        this.posicionTijera = posicionTijera;
    }

    public void setPosicionPiedra(int posicionPiedra) {
        this.posicionPiedra = posicionPiedra;
    }

    public void setPosicionPapel(int posicionPapel) {
        this.posicionPapel = posicionPapel;
    }

    public void setTijeraEspera(int tijeraEspera) {
        this.tijeraEspera = tijeraEspera;
    }

    public void setPiedraEspera(int piedraEspera) {
        this.piedraEspera = piedraEspera;
    }

    public void setPapelEspera(int papelEspera) {
        this.papelEspera = papelEspera;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public int getPosicionTijera() {
        return posicionTijera;
    }

    public int getPosicionPiedra() {
        return posicionPiedra;
    }

    public int getPosicionPapel() {
        return posicionPapel;
    }

    public int getTijeraEspera() {
        return tijeraEspera;
    }

    public int getPiedraEspera() {
        return piedraEspera;
    }

    public int getPapelEspera() {
        return papelEspera;
    }
}
