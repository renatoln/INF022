/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lercsv;

/**
 *
 * @author digo_
 */
public class Meso {
    private String nome;
    private int idMeso;
    private Micro[] micros;
    private int populacao;
    Informacoes[] informacoes;
    public Meso(String nome, int id){
        this.micros = new Micro[0];
        this.idMeso = id;
        this.nome = nome;
        this.informacoes = new Informacoes[0];
    }
    
    public void addMicro(int idMeso, Micro micro){
        if(this.idMeso == idMeso){
            addMicro(micro);
        }
    }
    private void addMicro(Micro micro){
        Micro[] microTemp = new Micro[this.micros.length+1];
        int i = 0;
        for(i = 0; i < this.micros.length; i++){
            microTemp[i] = this.micros[i];
        }
        microTemp[i] = micro;
        this.micros = microTemp;
    }
    
    public String getNome(){
        return this.nome;
    }
    public Micro getMicro(int indice){
        return this.micros[indice];
    }
    public int QTDMicro(){
        return this.micros.length;
    }
    
    public void gerarRegistrosJSON(EscreverEmArquivo arquivo, int indice){
        if(!(informacoes.length==0)){
            arquivo.arquivoJSON("\t\t{\n"); 
            arquivo.arquivoJSON("\t\t\t\"ID\": " + idMeso + ",\n"); 
            arquivo.arquivoJSON("\t\t\t\"NOME_MESORREGIAO\": \"" + nome + "\",\n");
            arquivo.arquivoJSON(informacoes[indice].getAtributosToJSON());
            arquivo.arquivoJSON(informacoes[indice].getValoresToJSON());
            if(indice==informacoes.length){
                arquivo.arquivoJSON("\t\t}\n");
            }else{
                arquivo.arquivoJSON("\t\t},\n");
            }
        }
    }
    
    private int getIndice(String periodo){
        int retorno = 0;
        for(Informacoes informacoesTemp: this.informacoes){
            if(informacoesTemp.getPeriodo().equalsIgnoreCase(periodo)){
                return retorno;
            }
            retorno ++;
        }
        return -1;
    }
    
    public void gerarInformacoes(){
        String[] atributos = new String[11];
        double[] valores = new double[11];
        int registro = 0;
        for(int i = 0; i < 11 ; i++){
            valores[i] = 0;
            atributos[i] = new String();
            atributos[i] = "";
        }
        for(Micro microTemp:this.micros){
            microTemp.gerarInformacoes();
            if(microTemp.getRegistro() >= registro){
                registro = microTemp.getRegistro();
            }

        }
        this.informacoes = new Informacoes[registro];
        for(int j = 0; j < this.informacoes.length; j++){
            for(Micro microTemp:this.micros){
                if(!(microTemp.informacoes.length==0)){
                    for(int i = 0; i < 11; i++){
                        atributos= microTemp.informacoes[j].getAtributos();
                        valores[i]+= microTemp.informacoes[j].getValore(i);
                    }
                    valores[2] = valores[0]/valores[1];
                    this.informacoes[j] = new Informacoes("", atributos, valores);
                }
            }
        }
    }
}
