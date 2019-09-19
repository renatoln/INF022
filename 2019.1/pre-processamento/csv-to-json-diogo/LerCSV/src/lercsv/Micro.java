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
public class Micro {
    private String nome;
    private int idMicro, idMeso;
    private Municipio[] municipios;
    public Informacoes[] informacoes;
    
     
    public Micro(String nome, int idMicro, int idMeso){
        this.nome = nome;
        this.idMicro = idMicro;
        this.idMeso = idMeso;
        this.municipios = new Municipio[0];
        this.informacoes = new Informacoes[0];
    }
    public String getNome(){
        return this.nome;
    }

    public void addMunicipio(int idMicro, Municipio municipio){
        if(this.idMicro == idMicro){
            addMunicipio(municipio);
        }
    }
    private void addMunicipio(Municipio municipio){
        Municipio[] municipioTemp = new Municipio[municipios.length+1];
        int i = 0;
        for(i = 0; i < this.municipios.length; i++){
            municipioTemp[i] = this.municipios[i];
        }
        municipioTemp[i] = municipio;
        this.municipios = municipioTemp;
    }
    public int getMeso(){
        return this.idMeso;
    }
    public Municipio getMunicipio(int indice){
        return this.municipios[indice];
    }
    public int QTDMunicipios(){
        return this.municipios.length;
    }
    public void gerarRegistrosJSON(EscreverEmArquivo arquivo, int indice){
        if(!(informacoes.length==0)){
            arquivo.arquivoJSON("\t\t{\n");
            arquivo.arquivoJSON("\t\t\t\"ID\": " + this.idMicro + ",\n"); 
            arquivo.arquivoJSON("\t\t\t\"NOME_MESORREGIAO\": \"" + this.nome + "\",\n");
            arquivo.arquivoJSON(informacoes[indice].getAtributosToJSON());
            arquivo.arquivoJSON(informacoes[indice].getValoresToJSON());
            arquivo.arquivoJSON("\t\t\t\"ID_MESO\": " + this.idMeso + "\n");
            arquivo.arquivoJSON("\t\t},\n");
        }
    }
    public int getQTDDados(){
        return municipios[0].getQTDDados();
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
        int registo = 0;
        String[] atributos = new String[11];
        double[] valores = new double[11];
        for(int i = 0; i < 11 ; i++){
            valores[i] = 0;
        }
        for(Municipio municipioTemp:this.municipios){
            municipioTemp.gerarInformacoes();
            if(municipioTemp.getRegistro() >= registo){
                registo = municipioTemp.getRegistro();
            }
        }
        this.informacoes = new Informacoes[registo];
        for(int j = 0; j < this.informacoes.length; j++){
          for(Municipio municipioTemp:this.municipios){
                if(!(municipioTemp.informacoes.length==0)){
                    for(int i = 0; i < 11; i++){
                        atributos = municipioTemp.informacoes[j].getAtributos();
                        valores[i]+= municipioTemp.informacoes[j].getValore(i);
                    }
                    valores[2] = valores[0]/valores[1];
                    this.informacoes[j] = new Informacoes(municipioTemp.informacoes[j].getPeriodo(), atributos, valores);
                }
            }
        }
    }
    public int getRegistro(){
        return informacoes.length;
    }
}
