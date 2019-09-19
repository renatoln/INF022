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
public class Dados {
    private String periodo;
    private int[] valor;
    private int valorTotal = 0;
    private int[] faixaValor;
    private String[] faixasDeValor = new String[4];
    private int[] elementos = new int[4];
    private int[] somaValorFaixa = new int[4];
    
    public Dados(String periodo, int valor){
        this.periodo = periodo;
        this.valor = new int[1];
        this.valor[0] = valor;
        this.faixaValor = new int[1];
        this.faixaValor[0] = faixa(valor);
        registrarFaixas();
    }
    public void gerarInfomacoes(){
            for(int j = 0; j < faixasDeValor.length; j++){
                    somaValorFaixa[j] = getSomaFaixa(faixasDeValor[j]);
                    elementos[j] = getTotalFaixa(faixasDeValor[j]);
                }
                valorTotal = getSomaValores();
    }
    public String getAtributo(){
        return "\"Total de elementos\", \"Valor Total\", \"Total " + 
                faixasDeValor[0] + "\", \"Total " + faixasDeValor[1] + "\", \"Total " +faixasDeValor[2] + "\", \"Total " +faixasDeValor[3] + "\", \"Valor " +
                faixasDeValor[0] + "\", \"Valor " + faixasDeValor[1] + "\", \"Valor " +faixasDeValor[2] + "\", \"Valor " +faixasDeValor[3] + "\", ";
    }
    public String getValor(){
        return valor.length + ", " + valorTotal + ".00, " + 
                elementos[0] + ", " +elementos[1] + ", " +elementos[2] + ", " +elementos[3] + ", " + 
             + somaValorFaixa[0] + ".00, " +  somaValorFaixa[1] +".00, " +   somaValorFaixa[2] + ".00, " +  somaValorFaixa[3] 
             + ".00, " ;
    }
    public int getValor(int indice){
        return this.valor[indice];
    }
    public String getFaixa(int indice){
        return this.faixasDeValor[indice];
    }
    public int getElemento(int indice){
        return this.elementos[indice];
    }
    public int getSomaValorFaixa(int indice){
        return this.somaValorFaixa[indice];
    }
    public int getQTDValores(){
        return this.valor.length;
    }
    public String getPeriodo(){
        return this.periodo;
    }
    
    public int getTotalValores(){
        return this.valor.length;
    }
    public int getSomaValores(){
        int retorno = 0;
        for(int valor : this.valor){
            retorno+= valor;
        }
        return retorno;
    }
    public String getint(){
        String retorno = "";
        for(int i: elementos){
            retorno+= i +", ";
        }
        return retorno;
    }
    public int getTotalFaixa(String faixa){
        int retorno = 0;
        String t = "";
        for(int i = 0; i < this.faixaValor.length; i++){
            t = faixasDeValor[this.faixaValor[i]];
            if(t.equalsIgnoreCase(faixa)){
                retorno++;
            }
        }
        return retorno;
    }
    public int getSomaFaixa(String faixa){
        int retorno = 0;
        String t = "";
        for(int i = 0; i < this.faixaValor.length; i++){
            t = faixasDeValor[this.faixaValor[i]];
            if(t.equalsIgnoreCase(faixa)){
                retorno+= valor[i];
            }
        }
        return retorno;
    }
    public void addAtributos(int valor){
        int[] valorTemp = new int[this.valor.length+1];        
        int[] faixaValorTemp = new int[this.faixaValor.length+1];
        int i = 0;
        for(i = 0; i < this.valor.length; i++){
            faixaValorTemp[i] = this.faixaValor[i];
            valorTemp[i] = this.valor[i];
        }
        valorTemp[i] = valor;
        this.valor = valorTemp;
        faixaValorTemp[i] = faixa(valor);
        this.faixaValor = faixaValorTemp;
    }    
    private int faixa(int valor){
        int faixa = -1;
        if(valor>=0&&valor<=249){
            faixa = 0;
        }else if(valor>=250&&valor<=499){
            faixa = 1;
        }else if(valor>=500&&valor<=749){
            faixa = 2;
        }else if(valor>=750){
            faixa = 3;
        }
        return faixa;
    }
    private void registrarFaixas(){
        faixasDeValor[0] = "Faixa 1";
        faixasDeValor[1] = "Faixa 2";
        faixasDeValor[2] = "Faixa 3";
        faixasDeValor[3] = "Faixa 4";
        elementos[0] = 0;
        elementos[1] = 0;
        elementos[2] = 0;
        elementos[3] = 0;
        somaValorFaixa[0] = 0;
        somaValorFaixa[1] = 0;
        somaValorFaixa[2] = 0;
        somaValorFaixa[3] = 0;
    }
    public int registros(){
        return faixaValor.length;
    }
}
