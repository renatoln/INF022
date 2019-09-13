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
public class Aplicacao {
    
    private Scanner ler;
    private Pais registrosEmOO;
    private LerCSV lerCSV;
    private String csvDivisor = ";", registro, filtro = "BA", separar = "0" ;
    private EscreverEmArquivo arquivo = null;
    private int nFiltro = 3;
    private long tamanho;
    private float contador = 0, acumulado = 0, limitador = 15000000;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Aplicacao obj = new Aplicacao();
        obj.run();    
   }
    public void run() throws FileNotFoundException, IOException {
        registrosEmOO = new Pais();       
        lerCSV = new LerCSV();
        ler = new Scanner(System.in); 
        
        //Identificação do arquivo de leitura 
        leituraEndereco();
        
        //cabeçalho do csv
        lerCSV.picota(this.csvDivisor);
        lerCSV.retiraElemento("\"","");
        lerCSV.retiraElemento("  "," ");
        registrosEmOO.setCabecalhoArquivoCSV(lerCSV.getDados());
        System.out.println(registrosEmOO.getCabecalhoArquivoCSV());
        
        //processamento do conteúdo do arquivo csv para modelo OO
        CSVparaOO();
        /*leituraEndereco("E:\\dados\\csv\\201302_BolsaFamilia_Pagamentos.csv");
        lerCSV.getDados();
        CSVparaOO();
        leituraEndereco("E:\\dados\\csv\\201303_BolsaFamilia_Pagamentos.csv");
        lerCSV.getDados();
        CSVparaOO();
        leituraEndereco("E:\\dados\\csv\\201304_BolsaFamilia_Pagamentos.csv");
        lerCSV.getDados();
        CSVparaOO();
        leituraEndereco("E:\\dados\\csv\\201305_BolsaFamilia_Pagamentos.csv");
        lerCSV.getDados();
        CSVparaOO();
        leituraEndereco("E:\\dados\\csv\\201306_BolsaFamilia_Pagamentos.csv");
        lerCSV.getDados();
        CSVparaOO();
        leituraEndereco("E:\\dados\\csv\\201307_BolsaFamilia_Pagamentos.csv");
        lerCSV.getDados();
        CSVparaOO();
        leituraEndereco("E:\\dados\\csv\\201308_BolsaFamilia_Pagamentos.csv");
        lerCSV.getDados();
        CSVparaOO();
        leituraEndereco("E:\\dados\\csv\\201309_BolsaFamilia_Pagamentos.csv");
        lerCSV.getDados();
        CSVparaOO();
        leituraEndereco("E:\\dados\\csv\\201310_BolsaFamilia_Pagamentos.csv");
        lerCSV.getDados();
        CSVparaOO();
        leituraEndereco("E:\\dados\\csv\\201311_BolsaFamilia_Pagamentos.csv");
        lerCSV.getDados();
        CSVparaOO();
        leituraEndereco("E:\\dados\\csv\\201312_BolsaFamilia_Pagamentos.csv");
        lerCSV.getDados();
        CSVparaOO();
        */
        
        // processamento para contabilizar registros        
        registrosEmOO.gerarInformacoes();
        registrosEmOO.gerarRegistrosJSON();
           
        
  }
    private void CSVparaOO() throws IOException{
        // é necessário identificar o dado que será armazenado.
        while ((lerCSV.linha = lerCSV.br.readLine()) != null){
            //contador++;
            lerCSV.picota(this.csvDivisor);
            lerCSV.retiraElemento("\"","");
            lerCSV.retiraElemento("  "," ");
            lerCSV.retiraElemento(",00","");
            if(lerCSV.getDado(2).equalsIgnoreCase("BA")){
                //passagem direta do valor 
                registrosEmOO.cadastrarPeriodo(lerCSV.getDado(0));
                registrosEmOO.getEstado(lerCSV.getDado(2)).getMunicipio(lerCSV.getDado(4)).addDados(lerCSV.getDado(7),lerCSV.getDado(0));              
                /*if(contador==tamanho){
                    System.out.println(((int)acumulado++) + "%");
                    contador = 0;
                }*/
            }
        }
    }
    private void divisorCSV(){
        System.out.printf("Qual elemento separar o arquivo CSV?\n Exemplo: , ; -\n");
        //this.csvDivisor = ";";
        this.csvDivisor = ler.next();
        
    }
    private void limitadorCSV(){
        System.out.printf("Qual numero de linha deseja separar o arquivo CSV?\n Exemplo: 500000\n");
        //this.limitador = 1000000000;
        this.limitador = ler.nextInt();
        
    }
    private void criarFiltro(String[] tegs){
        int i;
        for(i = 0; i < tegs.length; i++){
            System.out.printf( (i+1)+ " " + tegs[i] + "\n");
        }
        System.out.printf("Qual elemento deseja filtrar:\nExemplo: de 1 a " + i + " ou digite 0 para não filtar\n");
        this.nFiltro = this.ler.nextInt();
        if(this.nFiltro == 0 || this.nFiltro > i+1){
          this.nFiltro = 1;
          return ;
        }
        this.nFiltro--;
        System.out.printf("Qual termo procura:\nExemplo: Salvador\n");
        this.filtro = this.ler.next(); 
    }
    private void leituraEndereco() throws IOException{
        //System.out.printf("Informe o endereçoo do caminho do arquivo CSV:\nExemplo: C:/arquivo.csv\n");
        lerCSV.setLeitura("E:\\dados\\csv\\201301_BolsaFamilia_Pagamentos.csv");
        //lerCSV.setLeitura(ler.next());
        //System.out.printf("Informe o endereco que os arquivos serão gerados:\nExemplo: C:/\n");
        //lerCSV.setDestino("D:\\dados\\testes\\");
        //lerCSV.setDestino(ler.next());
        lerCSV.iniciar();
        tamanho = lerCSV.getTamanhoArquivo()/100;
    }
    private void leituraEndereco(String endereco) throws IOException{
        lerCSV.setLeitura(endereco);
        lerCSV.iniciar();
        tamanho = lerCSV.getTamanhoArquivo()/100;
    }
    private void separarCSV(){
        System.out.printf("Deseja separar o arquivo CSV quando mais de 1 milhão de registros?:\n0 para SIM\n1 para NÃO\n");
        separar = ler.next();
        //this.separar = "0";
    }
    private void csv1() throws IOException{
    lerCSV = new LerCSV();
        ler = new Scanner(System.in);
        leituraEndereco();
        divisorCSV();
        limitadorCSV();
        lerCSV.iniciar();
        
        lerCSV.picota(this.csvDivisor);
        lerCSV.retiraElemento("\"","");
        lerCSV.retiraElemento("  "," ");
        //lerCSV.retiraElemento("","");
        criarFiltro(lerCSV.getDados());
        //arquivo = new EscreverEmArquivo(lerCSV.getDestino(), filtro);
        //arquivo.tegs(lerCSV.getDados());
        
        this.registro = "Arquivo base " + lerCSV.getLeitura() + "\nPasta destino " 
                        + lerCSV.getDestino() + "\nArquivos:\ndados"+filtro
                        +".txt\ndados"+filtro+".json\ndados"+filtro+".csv\n";
        int reg = 0, n = 0;
        Pais r = new Pais();
        while ((lerCSV.linha = lerCSV.br.readLine()) != null) {
            lerCSV.picota(this.csvDivisor);
            lerCSV.retiraElemento("\"","");
            lerCSV.retiraElemento("  "," ");
            if(lerCSV.getDado(2).equalsIgnoreCase(this.filtro)){
                this.contador++;
                System.out.println(r.getEstado(lerCSV.getDado(2)).getMunicipio(lerCSV.getDado(4)));
                //r.getEstado(lerCSV.dados[2]).getMunicipio(lerCSV.dados[4]).addValor(10);
                if(this.contador > limitador && this.separar.equalsIgnoreCase("0")){
                       // this.registro+= arquivo.novoCSV(this.filtro);
                        this.acumulado+= this.contador;
                        this.contador = 0;
                }
                reg++;
                if(reg == 100000){
                    n++;
                    reg = 0;
                    System.out.print(n+"\n");

                }
                //ESCREVER EM TXT
                //arquivo.arquivoTXT(lerCSV.dados);
                //ESCREVER EM CSV
                //lerCSV.dados = lerCSV.retiraElemento(".", ",",lerCSV.dados);
                //arquivo.arquivoCSV(lerCSV.dados);
                //ESCREVER EM JSON
                //lerCSV.dadosJSON = lerCSV.retiraElemento(",", ".",lerCSV.dados);
                //arquivo.arquivoJSON(lerCSV.dadosJSON);    

            }
            
        }
        if(this.acumulado==0||this.contador!=this.acumulado){
            this.acumulado+= this.contador;
        }
        this.registro = this.registro.replace("\\", "/");
        System.out.println("Registros: " + (int)this.acumulado + "\n" + this.registro);    
        System.out.print("ok 1");}    
    private void csv2() throws IOException{
        String origem = (lerCSV.getDestino() + "dados" + this.filtro + ".csv");
        String destino = lerCSV.getDestino();
        lerCSV = new LerCSV();
        lerCSV.setLeitura(origem);
        lerCSV.setDestino(destino);
        lerCSV.iniciar();
        registro = "";
        
        lerCSV.picota(this.csvDivisor);
        lerCSV.retiraElemento("\"","");
        lerCSV.retiraElemento("  "," ");
        //lerCSV.retiraElemento("","");
        //criarFiltro(lerCSV.dados);
        //arquivo = new EscreverEmArquivo(lerCSV.getDestino(), filtro,"");
        //arquivo.tegs2(lerCSV.getDados());
        
//        this.registro = "Arquivo base " + lerCSV.leitura + "\nPasta destino " 
  //                      + lerCSV.destino + "\nArquivos:\ndados"+filtro
    //                    +".txt\ndados"+filtro+".json\ndados"+filtro+".csv\n";
        
        while ((lerCSV.linha = lerCSV.br.readLine()) != null) {
            lerCSV.picota(this.csvDivisor);
            lerCSV.retiraElemento("\"","");
            lerCSV.retiraElemento(",00","");
            if(lerCSV.getDado(this.nFiltro).contains(this.filtro)){
                
                //System.out.println(lerCSV.dados[4]);
                //arquivo.somaCSV(lerCSV.getDados());
            }
        }
        //arquivo.arquivoCSVJSON();
        /*
        this.registro+= arquivo.fecharArquivo();
        if(this.acumulado==0||this.contador!=this.acumulado){
            this.acumulado+= this.contador;
        }
        this.registro = this.registro.replace("\\", "/");
        System.out.println("Registros: " + (int)this.acumulado + "\n" + this.registro);
        */
    }
}