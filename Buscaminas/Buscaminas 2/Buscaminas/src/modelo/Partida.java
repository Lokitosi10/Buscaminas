/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.Buscaminas;
import javax.swing.JOptionPane;

/**
 *
 * @author sehjud
 */
public class Partida {
    private Tablero tablero;
    private boolean destapadas[][];
    private int nivelDificultad;
    public int banderas=0;
    public int casillasDestapadas=1;

    public Partida(int c, int f, int nivelDificultad) {
        JOptionPane.showMessageDialog(null,"Bienvenido a buscaminas \n Controles: "
                + "para acceder a banderas de click derecho en flag \n"
                + "para cambair a minas de click izquierdo en flag");
        this.nivelDificultad = nivelDificultad;
        switch(nivelDificultad){
            case 1:
                Buscaminas.cantMinas=10;
                tablero = new Tablero(c, f, 8, 8);
                destapadas = new boolean[8][8];
                tablero.imprimirMatriz();
                
                break;
            case 2:
                Buscaminas.cantMinas=40;
                tablero = new Tablero(c, f, 16, 16);
                destapadas = new boolean[16][16];
                break;
            case 3:
                Buscaminas.cantMinas=99;
                tablero = new Tablero(c, f, 30, 16);
                destapadas = new boolean[16][30];
                break;
        }
        destaparCasilla(c, f);
        //imprimirMatriz();
    }

    /**
     * @return the tablero
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * @param tablero the tablero to set
     */
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    /**
     * @return the nivelDificultad
     */
    public int getNivelDificultad() {
        return nivelDificultad;
    }

    /**
     * @param nivelDificultad the nivelDificultad to set
     */
    public void setNivelDificultad(int nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    /**
     * @return the banderas
     */
    public int getBanderas() {
        return banderas;
    }

    /**
     * @param banderas the banderas to set
     */
    public void setBanderas(int banderas) {
        this.banderas = banderas;
    }

    /**
     * @return the casillasDestapadas
     */
    public int getCasillasDestapadas() {
        return casillasDestapadas;
    }

    /**
     * @param casillasDestapadas the casillasDestapadas to set
     */
    public void setCasillasDestapadas(int casillasDestapadas) {
        this.casillasDestapadas = casillasDestapadas;
    }
    
    public void imprimirMatriz(){
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 30; j++) {
                System.out.print(destapadas[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    public void destaparCasilla(int c, int f) throws ArrayIndexOutOfBoundsException{
        int cRv = c; 
        int fRv = f;
        if(destapadas[f][c])
            return ; 
        if(tablero.getTab()[f][c]>0&&tablero.getTab()[f][c]<9){
            casillasDestapadas++;
            Buscaminas.cantCasillas = (64- casillasDestapadas); 
            destapadas[f][c]=true;
            Buscaminas.destaparCasilla(f, c,tablero.getTab()[f][c]);
        }else{
            if(tablero.verificarSiHayMina(c, f)){
                Buscaminas.Peerdio();
                return ;
            }
            if(tablero.getTab()[f][c]==0){
                destapadas[f][c] = true;
                Buscaminas.destaparCasilla(f, c,0);
                casillasDestapadas++;
                Buscaminas.cantCasillas = (64- casillasDestapadas); 
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if(i!=0||j!=0){
                            try{
                                cRv = c+i;
                                fRv = f+j;
                                destaparCasilla(cRv, fRv);
                            }catch(ArrayIndexOutOfBoundsException e){
                                continue;
                            }
                        }
                    }
                }
            }
        }
    }
    public void colocarBandera(int f, int c){
        Buscaminas.colocarBandera(f, c);
        banderas++;
        Buscaminas.cantBanderas = banderas;
        
        
    }
    
}
