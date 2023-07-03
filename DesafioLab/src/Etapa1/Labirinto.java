package Etapa1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Labirinto{

    private char[][] labirinto;
    private int nroLinhas;
    private int nroColunas;

    public Labirinto(String filename) throws IOException {
        calcLabirinto(filename);
        iniciaLabirinto(filename);
    }

    public char[][] getLabirinto() {
        return labirinto;
    }

    public void setLabirinto(char[][] labirinto) {
        this.labirinto = labirinto;
    }

    public int getNroLinhas() {
        return nroLinhas;
    }

    public void setNroLinhas(int nroLinhas) {
        this.nroLinhas = nroLinhas;
    }

    public int getNroColunas() {
        return nroColunas;
    }

    public void setNroColunas(int nroColunas) {
        this.nroColunas = nroColunas;
    }

    public void calcLabirinto(String filename) throws IOException {
        try {

            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            nroColunas = line.length();

            do {
                line = br.readLine();
                nroLinhas++;
            } while (line != null);

            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniciaLabirinto(String filename) throws IOException {
        try {

            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            char[][] tempLab = new char[nroLinhas][nroColunas];

            String line = br.readLine();
            for (int i = 0; i < tempLab.length && (line != null); i++) {
                tempLab[i] = line.toCharArray();
                line = br.readLine();
            }

            setLabirinto(tempLab);

            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validador(int lin, int col) {
        if (lin >= 0 && lin < nroLinhas && col >= 0 && col < nroColunas) {
            return labirinto[lin][col] == ' ' || labirinto[lin][col] == 'D';
        }
        return false;
    }

    public boolean percorreLabirinto() throws IllegalArgumentException {
        if (labirinto == null) throw new IllegalArgumentException();
        else return percorreLabirinto(0, 0);
    }

    private boolean percorreLabirinto(int lin, int col) {
        if (validador(lin, col)) {

            if (labirinto[lin][col] == 'D') {

                return true;
            }

            labirinto[lin][col] = '.';

            boolean achou = percorreLabirinto(lin - 1, col);
            if (!achou) achou = percorreLabirinto(lin + 1, col);
            if (!achou) achou = percorreLabirinto(lin, col - 1);
            if (!achou) achou = percorreLabirinto(lin, col + 1);
        }
        return false;
    }

    public void imprimeLabirinto() {
        for (char[] linhaLab : labirinto) {
            for (int j = 0; (linhaLab != null && j < linhaLab.length); j++) {
                System.out.print(linhaLab[j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        try {
            Labirinto labirinto = new Labirinto("D:\\Users\\Desktop\\filename.txt");
            labirinto.percorreLabirinto();
            labirinto.imprimeLabirinto();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
