/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lercsv;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author digo_
 */
public class EscreverEmArquivo {

    private FileWriter  arqJSON = null;
    private PrintWriter gravarArqJSON = null;
    
    public EscreverEmArquivo(String endereco) throws IOException{
        this.arqJSON = new FileWriter(endereco + ".json");
        this.gravarArqJSON = new PrintWriter(arqJSON);
    }
    
    public void arquivoJSON(String dado){
        this.gravarArqJSON.printf(dado);
    }
    public void fecharArquivo() throws IOException{
      this.gravarArqJSON.printf("\n\t]\n}");
      arqJSON.close();
    }
   
}
