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
public class Informacoes {
    private String periodo;
    private String[] atributos;
    private double[] valores;
    
    public Informacoes(String periodo, String[] atributos, double[] valores){
        this.periodo = periodo;
        addString(atributos);
        addDouble(valores);
    }
    
    private void addString(String[] atributos){
        this.atributos = new String[atributos.length];
        for(int i = 0; i < atributos.length; i++){
            this.atributos[i] = atributos[i];
        }
    } 
    
    private void addDouble(double[] valores){
        this.valores = new double[valores.length];
        for(int i = 0; i < valores.length; i++){
            this.valores[i] = valores[i];
        }        
    } 
    public int canDados(){
        return valores.length;
    }
    public String getPeriodo(){
        return this.periodo;
    }
    public String getAtributosToJSON(){
        String retorno = "\t\t\t\"ATRIBUTOS\": [";
        int i = 0;
        for(i = 0; i<this.atributos.length - 1; i++){
            retorno+= "\"" + this.atributos[i] + "\", ";
        }
            retorno+= "\"" + this.atributos[i] + "\"], \n";
        return retorno;
    }
    public String getValoresToJSON(){
        String retorno = "\t\t\t\"VALORES\": [";
        int i = 0;
        for(i = 0; i<this.valores.length - 1; i++){
            retorno+= this.valores[i] + ", ";
        }
            retorno+= this.valores[i] + "],\n";
        return retorno;
    }
    public String[] getAtributos(){
        return this.atributos;
    }
    public double getValore(int indice){
        return this.valores[indice];
    }
    public String getAtributo(int indice){
        return this.atributos[indice];
    }
    public int qteElementos(){
        return this.atributos.length;
    }
}
