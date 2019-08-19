/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lercsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author digo_
 */
public class LerCSV {
    Scanner ler = new Scanner(System.in);
    String leitura, destino;
 
    BufferedReader br = null;
    String linha = "";
    String csvDivisor = ";";
    String[] dados = null;

    char pularLinhas = '\n';
    char aspas = '"';
    String dado, registro, filtro = "";
    int nFiltro = 1;
    float contador = 0, acumulado = 0;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
   public static void main(String[] args) throws IOException {
        LerCSV obj = new LerCSV();
        obj.run();    
   }
    public void run() throws FileNotFoundException, IOException {

        System.out.printf("Informe o endereçoo do caminho do arquivo CSV:\nExemplo: C:/arquivo.csv\n");
        //leitura = "D:\\arquivo.csv";
        this.leitura = ler.next();
        System.out.printf("Informe o endereco que os arquivos serão gerados:\nExemplo: C:/\n");
        //destino = "D:\\dados\\";
        this.destino = ler.next();
    
        String arquivoCSV = leitura;
        
        br = new BufferedReader(new FileReader(arquivoCSV));
        linha = br.readLine();
        this.picota();
        this.retiraAspasEEspacos();
        criarFiltro(dados);
        EscreverEmArquivo arquivo = new EscreverEmArquivo(destino, filtro);
        arquivo.tegs(dados);
        registro = "Arquivo base " + this.leitura + "\nPasta destino " + this.destino + "\nArquivos:\ndados"+filtro+".txt\ndados"+filtro+".json\ndados"+filtro+".csv\n";
        while ((linha = br.readLine()) != null) {
            this.picota();
            this.retiraAspasEEspacos();
            if(dados[nFiltro].contains(filtro)){
                contador++;
                if(contador > 1000000.0 ){
                    registro+= arquivo.novoCSV(filtro);
                    acumulado+= contador;
                    contador = 0;
                }
                //ESCREVER EM TXT
                arquivo.arquivoTXT(dados);
                //ESCREVER EM JSON
                arquivo.arquivoJSON(dados);    
                //ESCREVER EM CSV
                arquivo.arquivoCSV(dados);
            }
        }
        arquivo.fecharArquivo();
        if(acumulado==0||contador!=acumulado){
            acumulado+= contador;
        }
        this.registro = this.registro.replace("\\", "/");
        System.out.println("Registros: " + (int)acumulado + "\n" + registro);
  }
    private void picota(){
        for(int i = 0; i < 7; i++){
            this.dados = this.linha.split(this.csvDivisor);
        }
    }
    private void retiraAspasEEspacos(){
        for(int i = 1; i < 9; i++){
            this.dado = this.dados[this.dados.length-i];
            this.dado = this.dado.replace("\"", "");
            this.dados[this.dados.length-i] = this.dado.replace("  ", " ");
        }
    }
    private void criarFiltro(String[] tegs){
        int i;
        for(i = 0; i < tegs.length; i++){
            System.out.printf( (i+1)+ " " + tegs[i] + "\n");
        }
        System.out.printf("Qual elemento deseja filtrar:\nExemplo: de 1 a " + i + " ou digite 0 para não filtar\n");
        nFiltro = ler.nextInt();
        if(nFiltro == 0 || nFiltro > i+1){
          nFiltro = 1;
          return ;
        }
        nFiltro--;
        System.out.printf("Qual termo procura:\nExemplo: Salvador\n");
        filtro = ler.next();
    
    }
}