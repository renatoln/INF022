/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lercsv;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Scanner;

/**
 *
 * @author digo_
 */
public class LerCSV {
    private String leitura, destino;
    BufferedReader br = null;
    String linha = "";
    private String[] dados = null;
    String[] dadosJSON = null;
    
    public LerCSV(){
        
    }
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */

    
    public void picota(String csvDivisor){
        for(int i = 0; i < 7; i++){
            this.dados = this.linha.split(csvDivisor);
        }
    }
    public void retiraElemento(String elementoProcurado, String elementoSubstituto){
        String dado;
        for(int i = 0; i < this.dados.length ; i++){
            dado = this.dados[i];
            this.dados[i] = dado.replace(elementoProcurado, elementoSubstituto);
        }
    }
    public void setDestino(String endereço){
        this.destino = endereço;
    }
    public String[] getDados(){
        return this.dados;
    }
    public String getDado(int indice){
        return this.dados[indice];
    }
    public String getDestino(){
        return this.destino;
    }
    public void setLeitura(String endereço){
        this.leitura = endereço;
    }
    public String getLeitura(){
        return this.leitura;
    }
    public void iniciar() throws FileNotFoundException, IOException{
        this.br = new BufferedReader(new FileReader(this.leitura));
        this.linha = this.br.readLine();
    }
    public String getLinha(){
        return this.linha;
    }
    public long getTamanhoArquivo(){
        try {
        File arquivoLeitura = new File(leitura);

        // pega o tamanho
        long tamanhoArquivo = arquivoLeitura .length();
        FileInputStream fs = new FileInputStream(arquivoLeitura);
        DataInputStream in = new DataInputStream(fs);

        LineNumberReader lineRead = new LineNumberReader(new InputStreamReader(in));
        lineRead.skip(tamanhoArquivo);
            // conta o numero de linhas do arquivo, começa com zero, por isso adiciona 1
            int numLinhas = lineRead.getLineNumber() + 1;
        return numLinhas;

        } catch (IOException e) {
        //TODO: Tratar exceção
        }
        return 0;
    }
}