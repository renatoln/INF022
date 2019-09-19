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
public class Municipio {
    private String municipio;
    private String municipioAcentuado;
    private String codigo;
    private Dados[] dados;
    public Informacoes[] informacoes;
    private int registros;
    private int populacao;
    private int idMeso;
    private int idMicro;
    private int idMunicipio;
    
    public Municipio(){
        
    }
    public Municipio(String municipioAcentuado, String municipio, String codigo){
        this.municipioAcentuado = municipioAcentuado;
        this.municipio = municipio;
        this.codigo = codigo;
        dados = new Dados[0];
        informacoes = new Informacoes[0];
    }
    public void addValores(int idMunicipio, int populacao, int idMeso, int idMicro){
        this.populacao = populacao;
        this.idMunicipio = idMunicipio;
        this.idMeso = idMeso;
        this.idMicro = idMicro;
    }
    public int getMicro(){
        return this.idMicro;
    }
    public int getMeso(){
        return this.idMeso;
    }
    public void gerarRegistrosJSON(EscreverEmArquivo arquivo, int indice){
       if(!(informacoes.length==0)){
            arquivo.arquivoJSON("\n\t\t{\n");
            arquivo.arquivoJSON("\t\t\t\"ID\": " + idMunicipio + ",\n"); 
            arquivo.arquivoJSON("\t\t\t\"NOME_MUNICIPIO\": \"" + municipioAcentuado + "\",\n");
            arquivo.arquivoJSON(informacoes[indice].getAtributosToJSON());
            arquivo.arquivoJSON(informacoes[indice].getValoresToJSON()); 
            arquivo.arquivoJSON("\t\t\t\"ID_MESO\": " + this.idMeso + ",\n");
            arquivo.arquivoJSON("\t\t\t\"ID_MICRO\": " + this.idMicro + "\n");
            arquivo.arquivoJSON("\t\t},");
       }  
    }
    
    public Dados getDados(int indice){
        return this.dados[indice];
    }
    public int getQTDDados(){
        return this.dados.length;
    }
    public String getNomeMunicipio(){
        return this.municipio;
    }
    public String getNomeMunicipioAcentuado(){
        return this.municipioAcentuado;
    }
    public int getRegistros(){    
        return this.registros;
    }
    public int getRegistro(){    
        return dados.length;
    }
    public int getRegistros(String periodo){    
        for(Dados dadosTemp : dados){
            if(dadosTemp.getPeriodo().equalsIgnoreCase(periodo)){
                return dadosTemp.registros();
            }
        }
        return 0;
    }
    public int getTotal(){
        int valor = 0;
        for(Dados dadosTemp : this.dados){
            valor += dadosTemp.getTotalValores();
        }
        return valor;
    }
    public int getSoma(){
        int valor = 0;
        for(Dados dadosTemp : this.dados){
            valor += dadosTemp.getSomaValores();
        }
        return valor;
    }
    public int getTotalFaixaPeriodo(String periodo, String faixa){
        for(Dados dadosTemp: this.dados){
            if(dadosTemp.getPeriodo().equalsIgnoreCase(periodo)){
                return dadosTemp.getTotalFaixa(faixa);
            }
        }
        return 0;
    }
    public int getTotalFaixa(String faixa){
        int retorno = 0;
        for(Dados dadosTemp: this.dados){
            retorno+= dadosTemp.getTotalFaixa(faixa);
        }
        return retorno;
    }
    public int getTotalPeriodo(String periodo){
        return this.dados.length;
    }
    
    public int getSomaFaixaPeriodo(String periodo, String faixa){
        for(Dados dadosTemp: this.dados){
            if(dadosTemp.getPeriodo().equalsIgnoreCase(periodo)){
                return dadosTemp.getSomaFaixa(faixa);
            }
        }
        return 0;
    }
    public int getSomaFaixa(String faixa){
        int retorno = 0;
        for(Dados dadosTemp: this.dados){
            retorno+= dadosTemp.getSomaFaixa(faixa);
        }
        return retorno;
    }
    public int getSomaPeriodo(String periodo){
        for(Dados dadosTemp: this.dados){
            if(dadosTemp.getPeriodo().equalsIgnoreCase(periodo)){
                return dadosTemp.getSomaValores();
            }
        }
        return 0;
    }
    public void addDados(String valor, String referencia){
        if(this.dados.length == 0){
            this.dados = new Dados[1];
            this.dados[0] = new Dados(referencia,Integer.parseInt(valor));
            this.registros = 1;
            return;
        }
        int i = 0;        
        for(i = 0; i < this.dados.length; i++){
            if(this.dados[i].getPeriodo().equalsIgnoreCase(referencia)){
                this.dados[i].addAtributos(Integer.parseInt(valor));
                this.registros++;
                return;
            }
        }
        
        Dados[] dadosTemp = new Dados[this.dados.length+1];
        for(i = 0; i < this.dados.length; i++){
            dadosTemp[i] = this.dados[i];
        }
        dadosTemp[i] = new Dados(referencia,Integer.parseInt(valor));
        this.dados = dadosTemp;
        this.registros++;
    }
    public boolean isEquals(String nomeMunicipio){
        return nomeMunicipio.equalsIgnoreCase(this.municipioAcentuado) || (nomeMunicipio.equalsIgnoreCase(this.municipio));
    }
   
    public String getAtributo(String periodo){
        for(Dados dadosTemp : this.dados){
            dadosTemp.getPeriodo().equalsIgnoreCase(periodo);
            return dadosTemp.getAtributo();
        }
        return null;
    }
    public String getValor(String periodo){
        for(Dados dadosTemp : this.dados){
            dadosTemp.getPeriodo().equalsIgnoreCase(periodo);
            return dadosTemp.getValor();
        }
        return null;
    }
    public int gerarInformacoes(){
        this.informacoes = new Informacoes[this.dados.length];
        String[] atributos = new String[11];
        double[] valores = new double[11];
        double resto = 0;
        int contador = 0;
        for(Dados dadosTemp : dados){
            dadosTemp.gerarInfomacoes();
        }
        for(Dados dadosTemp : dados){
            atributos[0] = "População";
            valores[0] = this.populacao;
            atributos[1] = "Beneficios";
            valores[1] = dadosTemp.getQTDValores();
            atributos[2] = "Percentual sobre a população";
            resto = dadosTemp.getQTDValores() % this.populacao;
            valores[2] = dadosTemp.getQTDValores() / this.populacao;
            valores[2]+= resto / this.populacao;
            valores[2] = valores[2] * 100;
            for(int i = 0; i < 4; i++){
                valores[i+3] = dadosTemp.getSomaValorFaixa(i);
                valores[i+7] = dadosTemp.getElemento(i);
                atributos[i+3] = dadosTemp.getFaixa(i);
                atributos[i+7] = dadosTemp.getFaixa(i);
            }
            this.informacoes[contador++] = new Informacoes(dadosTemp.getPeriodo(), atributos, valores);
        }
        return contador;
    }
}
