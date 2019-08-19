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

    private final FileWriter arqTXT ;
    private final PrintWriter gravarArqTXT ;
    private FileWriter arqCSV ;
    private PrintWriter gravarArqCSV ;
    private final FileWriter arqJSON ;
    private final PrintWriter gravarArqJSON ;
    private String[] teg, tegJSON = new String[7];
    private String endereco;
    private int contador = 0, h = 0;
    private String[] municipio1 = new String[417], municipio2 = new String[417], siafi = new String[417], id = new String[417];
    
    public EscreverEmArquivo() throws IOException{
        this.arqTXT = new FileWriter("d:\\dados\\dados.txt");
        this.gravarArqTXT = new PrintWriter(arqTXT);
        this.arqCSV = new FileWriter("d:\\dados\\dados.csv");
        this.gravarArqCSV = new PrintWriter(arqCSV);
        this.arqJSON = new FileWriter("d:\\dados\\dados.json");
        this.gravarArqJSON = new PrintWriter(arqJSON);
        this.carga();
    }
    public EscreverEmArquivo(String endereco, String filtro) throws IOException{
        this.arqTXT = new FileWriter(endereco + "dados" + filtro + ".txt");
        this.gravarArqTXT = new PrintWriter(arqTXT);
        this.arqCSV = new FileWriter(endereco + "dados" + filtro + ".csv");
        this.gravarArqCSV = new PrintWriter(arqCSV);
        this.arqJSON = new FileWriter(endereco + "dados" + filtro + ".json");
        this.gravarArqJSON = new PrintWriter(arqJSON);
        this.endereco = endereco;
        this.carga();
    }    
    
    public void tegs(String[] dado) throws IOException {
        this.teg = dado;
        this.arquivoCSV(dado);
    }
    public String novoCSV(String filtro) throws IOException{
        contador++;
        arqCSV.close();       
        this.arqCSV = new FileWriter(endereco + "dados" + filtro + contador + ".csv");
        this.gravarArqCSV = new PrintWriter(arqCSV);
        arquivoCSV(teg);
        return "dados" + filtro + contador + ".csv\n";
    }
    public void arquivoTXT(String[] dado) throws IOException {
        int i;
        for(i = dado.length; i >= 2; i--){
            gravarArqTXT.printf(teg[dado.length-i] + " " + dado[dado.length-i] + "\n");
        }
        gravarArqTXT.printf(teg[dado.length-i] +  " " + dado[dado.length-i] + "\n\n");
    }
    public void arquivoCSV(String[] dado) throws IOException {
        for(int i = dado.length; i >= 2; i--){
            gravarArqCSV.printf("\"" + dado[dado.length-i] + "\";");
        }
        gravarArqCSV.printf("\"" + dado[dado.length-1] + "\"\n");
    }
    public void arquivoJSON(String[] dado) throws IOException {
        int valor = procurar(dado[4]);
        if(valor == -1){
            return;
        }
        this.gravarArqJSON.printf("{\n\"" + this.tegJSON[0] + "\": " + id[valor] + ",\n");
        this.gravarArqJSON.printf("\"" + this.tegJSON[1] + "\": \"" + municipio1[valor] + "\",\n");
        this.gravarArqJSON.printf("\"" + this.tegJSON[2] + "\": " + 0 + ",\n");
        this.gravarArqJSON.printf("\"" + this.tegJSON[3] + "\": " + dado[7] + ",\n");
        this.gravarArqJSON.printf("\"" + this.tegJSON[4] + "\": \"" + dado[6]+ "\",\"" + dado[5] + "\",\n");
        this.gravarArqJSON.printf("\"" + this.tegJSON[5] + "\": " + 0 + ",\n");
        this.gravarArqJSON.printf("\"" + this.tegJSON[6] + "\": " + 0 + "\n},\n");
    }
    public void fecharArquivo() throws IOException{
      arqTXT.close();       
      arqCSV.close();       
      arqJSON.close();       
    }
    private int procurar(String municipio){
        for(int i = 0; i < this.municipio1.length; i++){
            if(municipio1[i].contains(municipio)){
                return i;
            }
            if(municipio2[i].contains(municipio)){
                return i;
            }
        }
        return -1;
    }
    private void carga(){
        for(int i = 0; i < 7; i++){
            
        }
        for(int i = 0; i < 7; i++){
            
        }
        this.tegJSON[0] = "ID";
	this.tegJSON[1] = "NOME_MUNICIPIO";
	this.tegJSON[2] = "POPULACAO";
	this.tegJSON[3] = "VALOR";
	this.tegJSON[4] = "CATEGORIA";
	this.tegJSON[5] = "ID_MESO";
	this.tegJSON[6] = "ID_MICRO";
        
        this.municipio1[h] = " ABAIRA";	this.id[h] = " 2900108";	this.siafi[h] = " 3301";	this.municipio2[h] = " ABAIRA"; h++;
        this.municipio1[h] = " ABARÉ";	this.id[h] = " 2900207";	this.siafi[h] = " 3303";	this.municipio2[h] = " ABARE"; h++;
        this.municipio1[h] = " ACAJUTIBA";	this.id[h] = " 2900306";	this.siafi[h] = " 3305";	this.municipio2[h] = " ACAJUTIBA"; h++;
        this.municipio1[h] = " ADUSTINA";	this.id[h] = " 2900355";	this.siafi[h] = " 3253";	this.municipio2[h] = " ADUSTINA"; h++;
        this.municipio1[h] = " ÁGUA FRIA";	this.id[h] = " 2900405";	this.siafi[h] = " 3307";	this.municipio2[h] = " AGUA FRIA"; h++;
        this.municipio1[h] = " AIQUARA";	this.id[h] = " 2900603";	this.siafi[h] = " 3311";	this.municipio2[h] = " AIQUARA"; h++;
        this.municipio1[h] = " ALAGOINHAS";	this.id[h] = " 2900702";	this.siafi[h] = " 3313";	this.municipio2[h] = " ALAGOINHAS"; h++;
        this.municipio1[h] = " ALCOBACA";	this.id[h] = " 2900801";	this.siafi[h] = " 3315";	this.municipio2[h] = " ALCOBACA"; h++;
        this.municipio1[h] = " ALMADINA";	this.id[h] = " 2900900";	this.siafi[h] = " 3317";	this.municipio2[h] = " ALMADINA"; h++;
        this.municipio1[h] = " AMARGOSA";	this.id[h] = " 2901007";	this.siafi[h] = " 3319";	this.municipio2[h] = " AMARGOSA"; h++;
        this.municipio1[h] = " AMELIA RODRIGUES";	this.id[h] = " 2901106";	this.siafi[h] = " 3321";	this.municipio2[h] = " AMELIA RODRIGUES"; h++;
        this.municipio1[h] = " AMÉRICA DOURADA";	this.id[h] = " 2901155";	this.siafi[h] = " 3071";	this.municipio2[h] = " AMERICA DOURADA"; h++;
        this.municipio1[h] = " ANAGÉ";	this.id[h] = " 2901205";	this.siafi[h] = " 3323";	this.municipio2[h] = " ANAGE"; h++;
        this.municipio1[h] = " ANDARAI";	this.id[h] = " 2901304";	this.siafi[h] = " 3325";	this.municipio2[h] = " ANDARAI"; h++;
        this.municipio1[h] = " ANDORINHA";	this.id[h] = " 2901353";	this.siafi[h] = " 3255";	this.municipio2[h] = " ANDORINHA"; h++;
        this.municipio1[h] = " ANGICAL";	this.id[h] = " 2901403";	this.siafi[h] = " 3327";	this.municipio2[h] = " ANGICAL"; h++;
        this.municipio1[h] = " ANGUERA";	this.id[h] = " 2901502";	this.siafi[h] = " 3329";	this.municipio2[h] = " ANGUERA"; h++;
        this.municipio1[h] = " ANTAS";	this.id[h] = " 2901601";	this.siafi[h] = " 3331";	this.municipio2[h] = " ANTAS"; h++;
        this.municipio1[h] = " ANTÔNIO CARDOSO";	this.id[h] = " 2901700";	this.siafi[h] = " 3333";	this.municipio2[h] = " ANTONIO CARDOSO"; h++;
        this.municipio1[h] = " ANTÔNIO GONÇALVES";	this.id[h] = " 2901809";	this.siafi[h] = " 3335";	this.municipio2[h] = " ANTONIO GONCALVES"; h++;
        this.municipio1[h] = " APORÁ";	this.id[h] = " 2901908";	this.siafi[h] = " 3337";	this.municipio2[h] = " APORA"; h++;
        this.municipio1[h] = " APUAREMA";	this.id[h] = " 2901957";	this.siafi[h] = " 3257";	this.municipio2[h] = " APUAREMA"; h++;
        this.municipio1[h] = " ARACATU";	this.id[h] = " 2902005";	this.siafi[h] = " 3259";	this.municipio2[h] = " ARACAS"; h++;
        this.municipio1[h] = " ARAÇAS";	this.id[h] = " 2902054";	this.siafi[h] = " 3339";	this.municipio2[h] = " ARACATU"; h++;
        this.municipio1[h] = " ARACI";	this.id[h] = " 2902104";	this.siafi[h] = " 3341";	this.municipio2[h] = " ARACI"; h++;
        this.municipio1[h] = " ARAMARI";	this.id[h] = " 2902203";	this.siafi[h] = " 3343";	this.municipio2[h] = " ARAMARI"; h++;
        this.municipio1[h] = " ARATACA";	this.id[h] = " 2902252";	this.siafi[h] = " 3073";	this.municipio2[h] = " ARATACA"; h++;
        this.municipio1[h] = " ARATUIPE";	this.id[h] = " 2902302";	this.siafi[h] = " 3345";	this.municipio2[h] = " ARATUIPE"; h++;
        this.municipio1[h] = " AURELINO LEAL";	this.id[h] = " 2902401";	this.siafi[h] = " 3347";	this.municipio2[h] = " AURELINO LEAL"; h++;
        this.municipio1[h] = " BAIANÓPOLIS";	this.id[h] = " 2902500";	this.siafi[h] = " 3349";	this.municipio2[h] = " BAIANOPOLIS"; h++;
        this.municipio1[h] = " BAIXA GRANDE";	this.id[h] = " 2902609";	this.siafi[h] = " 3351";	this.municipio2[h] = " BAIXA GRANDE"; h++;
        this.municipio1[h] = " BANZAÊ";	this.id[h] = " 2902658";	this.siafi[h] = " 3261";	this.municipio2[h] = " BANZAE"; h++;
        this.municipio1[h] = " BARRA";	this.id[h] = " 2902708";	this.siafi[h] = " 3353";	this.municipio2[h] = " BARRA"; h++;
        this.municipio1[h] = " BARRA DA ESTIVA";	this.id[h] = " 2902807";	this.siafi[h] = " 3355";	this.municipio2[h] = " BARRA DA ESTIVA"; h++;
        this.municipio1[h] = " BARRA DO CHOCA";	this.id[h] = " 2902906";	this.siafi[h] = " 3357";	this.municipio2[h] = " BARRA DO CHOCA"; h++;
        this.municipio1[h] = " BARRA DO MENDES";	this.id[h] = " 2903003";	this.siafi[h] = " 3359";	this.municipio2[h] = " BARRA DO MENDES"; h++;
        this.municipio1[h] = " BARRA DO ROCHA";	this.id[h] = " 2903102";	this.siafi[h] = " 3361";	this.municipio2[h] = " BARRA DO ROCHA"; h++;
        this.municipio1[h] = " BARREIRAS";	this.id[h] = " 2903201";	this.siafi[h] = " 3363";	this.municipio2[h] = " BARREIRAS"; h++;
        this.municipio1[h] = " BARRO ALTO";	this.id[h] = " 2903235";	this.siafi[h] = " 3075";	this.municipio2[h] = " BARRO ALTO"; h++;
        this.municipio1[h] = " BARROCAS";	this.id[h] = " 2903276";	this.siafi[h] = " 3365";	this.municipio2[h] = " BARRO PRETO"; h++;
        this.municipio1[h] = " BARRO PRETO";	this.id[h] = " 2903300";	this.siafi[h] = " 1110";	this.municipio2[h] = " BARROCAS"; h++;
        this.municipio1[h] = " BELMONTE";	this.id[h] = " 2903409";	this.siafi[h] = " 3367";	this.municipio2[h] = " BELMONTE"; h++;
        this.municipio1[h] = " BELO CAMPO";	this.id[h] = " 2903508";	this.siafi[h] = " 3369";	this.municipio2[h] = " BELO CAMPO"; h++;
        this.municipio1[h] = " BIRITINGA";	this.id[h] = " 2903607";	this.siafi[h] = " 3371";	this.municipio2[h] = " BIRITINGA"; h++;
        this.municipio1[h] = " BOA NOVA";	this.id[h] = " 2903706";	this.siafi[h] = " 3373";	this.municipio2[h] = " BOA NOVA"; h++;
        this.municipio1[h] = " BOA VISTA DO TUPIM";	this.id[h] = " 2903805";	this.siafi[h] = " 3375";	this.municipio2[h] = " BOA VISTA DO TUPIM"; h++;
        this.municipio1[h] = " BOM JESUS DA LAPA";	this.id[h] = " 2903904";	this.siafi[h] = " 3377";	this.municipio2[h] = " BOM JESUS DA LAPA"; h++;
        this.municipio1[h] = " BOM JESUS DA SERRA";	this.id[h] = " 2903953";	this.siafi[h] = " 3263";	this.municipio2[h] = " BOM JESUS DA SERRA"; h++;
        this.municipio1[h] = " BONINAL";	this.id[h] = " 2904001";	this.siafi[h] = " 3379";	this.municipio2[h] = " BONINAL"; h++;
        this.municipio1[h] = " BONITO";	this.id[h] = " 2904050";	this.siafi[h] = " 3265";	this.municipio2[h] = " BONITO"; h++;
        this.municipio1[h] = " BOQUIRA";	this.id[h] = " 2904100";	this.siafi[h] = " 3381";	this.municipio2[h] = " BOQUIRA"; h++;
        this.municipio1[h] = " BOTUPORÃ";	this.id[h] = " 2904209";	this.siafi[h] = " 3383";	this.municipio2[h] = " BOTUPORA"; h++;
        this.municipio1[h] = " BREJÕES";	this.id[h] = " 2904308";	this.siafi[h] = " 3385";	this.municipio2[h] = " BREJOES"; h++;
        this.municipio1[h] = " BREJOLÂNDIA";	this.id[h] = " 2904407";	this.siafi[h] = " 3387";	this.municipio2[h] = " BREJOLANDIA"; h++;
        this.municipio1[h] = " BROTAS DE MACAÚBAS";	this.id[h] = " 2904506";	this.siafi[h] = " 3389";	this.municipio2[h] = " BROTAS DE MACAUBAS"; h++;
        this.municipio1[h] = " BRUMADO";	this.id[h] = " 2904605";	this.siafi[h] = " 3391";	this.municipio2[h] = " BRUMADO"; h++;
        this.municipio1[h] = " BUERAREMA";	this.id[h] = " 2904704";	this.siafi[h] = " 3393";	this.municipio2[h] = " BUERAREMA"; h++;
        this.municipio1[h] = " BURITIRAMA";	this.id[h] = " 2904753";	this.siafi[h] = " 3079";	this.municipio2[h] = " BURITIRAMA"; h++;
        this.municipio1[h] = " CAATIBA";	this.id[h] = " 2904803";	this.siafi[h] = " 3395";	this.municipio2[h] = " CAATIBA"; h++;
        this.municipio1[h] = " CABACEIRAS DO PARAGUAÇU";	this.id[h] = " 2904852";	this.siafi[h] = " 3267";	this.municipio2[h] = " CABACEIRAS DO PARAGUACU"; h++;
        this.municipio1[h] = " CACHOEIRA";	this.id[h] = " 2904902";	this.siafi[h] = " 3397";	this.municipio2[h] = " CACHOEIRA"; h++;
        this.municipio1[h] = " CACULÉ";	this.id[h] = " 2905008";	this.siafi[h] = " 3399";	this.municipio2[h] = " CACULE"; h++;
        this.municipio1[h] = " CAEM";	this.id[h] = " 2905107";	this.siafi[h] = " 3401";	this.municipio2[h] = " CAEM"; h++;
        this.municipio1[h] = " CAETANOS";	this.id[h] = " 2905156";	this.siafi[h] = " 3269";	this.municipio2[h] = " CAETANOS"; h++;
        this.municipio1[h] = " CAETITÉ";	this.id[h] = " 2905206";	this.siafi[h] = " 3403";	this.municipio2[h] = " CAETITE"; h++;
        this.municipio1[h] = " CAFARNAUM";	this.id[h] = " 2905305";	this.siafi[h] = " 3405";	this.municipio2[h] = " CAFARNAUM"; h++;
        this.municipio1[h] = " CAIRU";	this.id[h] = " 2905404";	this.siafi[h] = " 3407";	this.municipio2[h] = " CAIRU"; h++;
        this.municipio1[h] = " CALDEIRÃO GRANDE";	this.id[h] = " 2905503";	this.siafi[h] = " 3409";	this.municipio2[h] = " CALDEIRAO GRANDE"; h++;
        this.municipio1[h] = " CAMACAN";	this.id[h] = " 2905602";	this.siafi[h] = " 3411";	this.municipio2[h] = " CAMACAN"; h++;
        this.municipio1[h] = " CAMAÇARI";	this.id[h] = " 2905701";	this.siafi[h] = " 3413";	this.municipio2[h] = " CAMACARI"; h++;
        this.municipio1[h] = " CAMAMU";	this.id[h] = " 2905800";	this.siafi[h] = " 3415";	this.municipio2[h] = " CAMAMU"; h++;
        this.municipio1[h] = " CAMPO ALEGRE DE LOURDES";	this.id[h] = " 2905909";	this.siafi[h] = " 3417";	this.municipio2[h] = " CAMPO ALEGRE DE LOURDES"; h++;
        this.municipio1[h] = " CAMPO FORMOSO";	this.id[h] = " 2906006";	this.siafi[h] = " 3419";	this.municipio2[h] = " CAMPO FORMOSO"; h++;
        this.municipio1[h] = " CANÁPOLIS";	this.id[h] = " 2906105";	this.siafi[h] = " 3421";	this.municipio2[h] = " CANAPOLIS"; h++;
        this.municipio1[h] = " CANARANA";	this.id[h] = " 2906204";	this.siafi[h] = " 3423";	this.municipio2[h] = " CANARANA"; h++;
        this.municipio1[h] = " CANAVIEIRAS";	this.id[h] = " 2906303";	this.siafi[h] = " 3425";	this.municipio2[h] = " CANAVIEIRAS"; h++;
        this.municipio1[h] = " CANDEAL";	this.id[h] = " 2906402";	this.siafi[h] = " 3427";	this.municipio2[h] = " CANDEAL"; h++;
        this.municipio1[h] = " CANDEIAS";	this.id[h] = " 2906501";	this.siafi[h] = " 3429";	this.municipio2[h] = " CANDEIAS"; h++;
        this.municipio1[h] = " CANDIBA";	this.id[h] = " 2906600";	this.siafi[h] = " 3431";	this.municipio2[h] = " CANDIBA"; h++;
        this.municipio1[h] = " CÂNDIDO SALES";	this.id[h] = " 2906709";	this.siafi[h] = " 3433";	this.municipio2[h] = " CANDIDO SALES"; h++;
        this.municipio1[h] = " CANSANÇÃO";	this.id[h] = " 2906808";	this.siafi[h] = " 3435";	this.municipio2[h] = " CANSANCAO"; h++;
        this.municipio1[h] = " CANUDOS";	this.id[h] = " 2906824";	this.siafi[h] = " 3085";	this.municipio2[h] = " CANUDOS"; h++;
        this.municipio1[h] = " CAPELA DO ALTO ALEGRE";	this.id[h] = " 2906857";	this.siafi[h] = " 3081";	this.municipio2[h] = " CAPELA DO ALTO ALEGRE"; h++;
        this.municipio1[h] = " CAPIM GROSSO";	this.id[h] = " 2906873";	this.siafi[h] = " 3083";	this.municipio2[h] = " CAPIM GROSSO"; h++;
        this.municipio1[h] = " CARAIBAS";	this.id[h] = " 2906899";	this.siafi[h] = " 3271";	this.municipio2[h] = " CARAIBAS"; h++;
        this.municipio1[h] = " CARAVELAS";	this.id[h] = " 2906907";	this.siafi[h] = " 3437";	this.municipio2[h] = " CARAVELAS"; h++;
        this.municipio1[h] = " CARDEAL DA SILVA";	this.id[h] = " 2907004";	this.siafi[h] = " 3439";	this.municipio2[h] = " CARDEAL DA SILVA"; h++;
        this.municipio1[h] = " CARINHANHA";	this.id[h] = " 2907103";	this.siafi[h] = " 3441";	this.municipio2[h] = " CARINHANHA"; h++;
        this.municipio1[h] = " CASA NOVA";	this.id[h] = " 2907202";	this.siafi[h] = " 3443";	this.municipio2[h] = " CASA NOVA"; h++;
        this.municipio1[h] = " CASTRO ALVES";	this.id[h] = " 2907301";	this.siafi[h] = " 3445";	this.municipio2[h] = " CASTRO ALVES"; h++;
        this.municipio1[h] = " CATOLÂNDIA";	this.id[h] = " 2907400";	this.siafi[h] = " 3447";	this.municipio2[h] = " CATOLANDIA"; h++;
        this.municipio1[h] = " CATU";	this.id[h] = " 2907509";	this.siafi[h] = " 3449";	this.municipio2[h] = " CATU"; h++;
        this.municipio1[h] = " CATURAMA";	this.id[h] = " 2907558";	this.siafi[h] = " 3273";	this.municipio2[h] = " CATURAMA"; h++;
        this.municipio1[h] = " CENTRAL";	this.id[h] = " 2907608";	this.siafi[h] = " 3451";	this.municipio2[h] = " CENTRAL"; h++;
        this.municipio1[h] = " CHORROCHÓ";	this.id[h] = " 2907707";	this.siafi[h] = " 3453";	this.municipio2[h] = " CHORROCHO"; h++;
        this.municipio1[h] = " CICERO DANTAS";	this.id[h] = " 2907806";	this.siafi[h] = " 3455";	this.municipio2[h] = " CICERO DANTAS"; h++;
        this.municipio1[h] = " CIPÓ";	this.id[h] = " 2907905";	this.siafi[h] = " 3457";	this.municipio2[h] = " CIPO"; h++;
        this.municipio1[h] = " COARACI";	this.id[h] = " 2908002";	this.siafi[h] = " 3459";	this.municipio2[h] = " COARACI"; h++;
        this.municipio1[h] = " COCOS";	this.id[h] = " 2908101";	this.siafi[h] = " 3461";	this.municipio2[h] = " COCOS"; h++;
        this.municipio1[h] = " CONCEIÇÃO DA FEIRA";	this.id[h] = " 2908200";	this.siafi[h] = " 3463";	this.municipio2[h] = " CONCEICAO DA FEIRA"; h++;
        this.municipio1[h] = " CONCEIÇÃO DO ALMEIDA";	this.id[h] = " 2908309";	this.siafi[h] = " 3465";	this.municipio2[h] = " CONCEICAO DO ALMEIDA"; h++;
        this.municipio1[h] = " CONCEIÇÃO DO COITÉ";	this.id[h] = " 2908408";	this.siafi[h] = " 3467";	this.municipio2[h] = " CONCEICAO DO COITE"; h++;
        this.municipio1[h] = " CONCEIÇÃO DO JACUIPE";	this.id[h] = " 2908507";	this.siafi[h] = " 3469";	this.municipio2[h] = " CONCEICAO DO JACUIPE"; h++;
        this.municipio1[h] = " CONDE";	this.id[h] = " 2908606";	this.siafi[h] = " 3471";	this.municipio2[h] = " CONDE"; h++;
        this.municipio1[h] = " CONDEÚBA";	this.id[h] = " 2908705";	this.siafi[h] = " 3473";	this.municipio2[h] = " CONDEUBA"; h++;
        this.municipio1[h] = " CONTENDAS DO SINCORÁ";	this.id[h] = " 2908804";	this.siafi[h] = " 3475";	this.municipio2[h] = " CONTENDAS DO SINCORA"; h++;
        this.municipio1[h] = " CORAÇÃO DE MARIA";	this.id[h] = " 2908903";	this.siafi[h] = " 3477";	this.municipio2[h] = " CORACAO DE MARIA"; h++;
        this.municipio1[h] = " CORDEIROS";	this.id[h] = " 2909000";	this.siafi[h] = " 3479";	this.municipio2[h] = " CORDEIROS"; h++;
        this.municipio1[h] = " CORIBE";	this.id[h] = " 2909109";	this.siafi[h] = " 3481";	this.municipio2[h] = " CORIBE"; h++;
        this.municipio1[h] = " CORONEL JOÃO SÁ";	this.id[h] = " 2909208";	this.siafi[h] = " 3483";	this.municipio2[h] = " CORONEL JOAO SA"; h++;
        this.municipio1[h] = " CORRENTINA";	this.id[h] = " 2909307";	this.siafi[h] = " 3485";	this.municipio2[h] = " CORRENTINA"; h++;
        this.municipio1[h] = " COTEGIPE";	this.id[h] = " 2909406";	this.siafi[h] = " 3487";	this.municipio2[h] = " COTEGIPE"; h++;
        this.municipio1[h] = " CRAVOLÂNDIA";	this.id[h] = " 2909505";	this.siafi[h] = " 3489";	this.municipio2[h] = " CRAVOLANDIA"; h++;
        this.municipio1[h] = " CRISÓPOLIS";	this.id[h] = " 2909604";	this.siafi[h] = " 3491";	this.municipio2[h] = " CRISOPOLIS"; h++;
        this.municipio1[h] = " CRISTÓPOLIS";	this.id[h] = " 2909703";	this.siafi[h] = " 3493";	this.municipio2[h] = " CRISTOPOLIS"; h++;
        this.municipio1[h] = " CRUZ DAS ALMAS";	this.id[h] = " 2909802";	this.siafi[h] = " 3495";	this.municipio2[h] = " CRUZ DAS ALMAS"; h++;
        this.municipio1[h] = " CURAÇÁ";	this.id[h] = " 2909901";	this.siafi[h] = " 3497";	this.municipio2[h] = " CURACA"; h++;
        this.municipio1[h] = " DÁRIO MEIRA";	this.id[h] = " 2910008";	this.siafi[h] = " 3499";	this.municipio2[h] = " DARIO MEIRA"; h++;
        this.municipio1[h] = " DIAS D ÁVILA";	this.id[h] = " 2910057";	this.siafi[h] = " 3087";	this.municipio2[h] = " DIAS D'AVILA"; h++;
        this.municipio1[h] = " DOM BASILIO";	this.id[h] = " 2910107";	this.siafi[h] = " 3501";	this.municipio2[h] = " DOM BASILIO"; h++;
        this.municipio1[h] = " DOM MACEDO COSTA";	this.id[h] = " 2910206";	this.siafi[h] = " 3503";	this.municipio2[h] = " DOM MACEDO COSTA"; h++;
        this.municipio1[h] = " ELISIO MEDRADO";	this.id[h] = " 2910305";	this.siafi[h] = " 3505";	this.municipio2[h] = " ELISIO MEDRADO"; h++;
        this.municipio1[h] = " ENCRUZILHADA";	this.id[h] = " 2910404";	this.siafi[h] = " 3507";	this.municipio2[h] = " ENCRUZILHADA"; h++;
        this.municipio1[h] = " ENTRE RIOS";	this.id[h] = " 2910503";	this.siafi[h] = " 3509";	this.municipio2[h] = " ENTRE RIOS"; h++;
        this.municipio1[h] = " ÉRICO CARDOSO";	this.id[h] = " 2900504";	this.siafi[h] = " 3309";	this.municipio2[h] = " ERICO CARDOSO"; h++;
        this.municipio1[h] = " ESPLANADA";	this.id[h] = " 2910602";	this.siafi[h] = " 3511";	this.municipio2[h] = " ESPLANADA"; h++;
        this.municipio1[h] = " EUCLIDES DA CUNHA";	this.id[h] = " 2910701";	this.siafi[h] = " 3513";	this.municipio2[h] = " EUCLIDES DA CUNHA"; h++;
        this.municipio1[h] = " EUNÁPOLIS";	this.id[h] = " 2910727";	this.siafi[h] = " 3117";	this.municipio2[h] = " EUNAPOLIS"; h++;
        this.municipio1[h] = " FÁTIMA";	this.id[h] = " 2910750";	this.siafi[h] = " 3089";	this.municipio2[h] = " FATIMA"; h++;
        this.municipio1[h] = " FEIRA DA MATA";	this.id[h] = " 2910776";	this.siafi[h] = " 3275";	this.municipio2[h] = " FEIRA DA MATA"; h++;
        this.municipio1[h] = " FEIRA DE SANTANA";	this.id[h] = " 2910800";	this.siafi[h] = " 3515";	this.municipio2[h] = " FEIRA DE SANTANA"; h++;
        this.municipio1[h] = " FILADÉLFIA";	this.id[h] = " 2910859";	this.siafi[h] = " 3091";	this.municipio2[h] = " FILADELFIA"; h++;
        this.municipio1[h] = " FIRMINO ALVES";	this.id[h] = " 2910909";	this.siafi[h] = " 3517";	this.municipio2[h] = " FIRMINO ALVES"; h++;
        this.municipio1[h] = " FLORESTA AZUL";	this.id[h] = " 2911006";	this.siafi[h] = " 3519";	this.municipio2[h] = " FLORESTA AZUL"; h++;
        this.municipio1[h] = " FORMOSA DO RIO PRETO";	this.id[h] = " 2911105";	this.siafi[h] = " 3521";	this.municipio2[h] = " FORMOSA DO RIO PRETO"; h++;
        this.municipio1[h] = " GANDU";	this.id[h] = " 2911204";	this.siafi[h] = " 3523";	this.municipio2[h] = " GANDU"; h++;
        this.municipio1[h] = " GAVIÃO";	this.id[h] = " 2911253";	this.siafi[h] = " 3093";	this.municipio2[h] = " GAVIAO"; h++;
        this.municipio1[h] = " GENTIO DO OURO";	this.id[h] = " 2911303";	this.siafi[h] = " 3525";	this.municipio2[h] = " GENTIO DO OURO"; h++;
        this.municipio1[h] = " GLÓRIA";	this.id[h] = " 2911402";	this.siafi[h] = " 3527";	this.municipio2[h] = " GLORIA"; h++;
        this.municipio1[h] = " GONGOGI";	this.id[h] = " 2911501";	this.siafi[h] = " 3529";	this.municipio2[h] = " GONGOGI"; h++;
        this.municipio1[h] = " GOVERNADOR MANGABEIRA";	this.id[h] = " 2911600";	this.siafi[h] = " 3531";	this.municipio2[h] = " GOVERNADOR MANGABEIRA"; h++;
        this.municipio1[h] = " GUAJERU";	this.id[h] = " 2911659";	this.siafi[h] = " 3095";	this.municipio2[h] = " GUAJERU"; h++;
        this.municipio1[h] = " GUANAMBI";	this.id[h] = " 2911709";	this.siafi[h] = " 3533";	this.municipio2[h] = " GUANAMBI"; h++;
        this.municipio1[h] = " GUARATINGA";	this.id[h] = " 2911808";	this.siafi[h] = " 3535";	this.municipio2[h] = " GUARATINGA"; h++;
        this.municipio1[h] = " HELIÓPOLIS";	this.id[h] = " 2911857";	this.siafi[h] = " 3097";	this.municipio2[h] = " HELIOPOLIS"; h++;
        this.municipio1[h] = " IAÇU";	this.id[h] = " 2911907";	this.siafi[h] = " 3537";	this.municipio2[h] = " IACU"; h++;
        this.municipio1[h] = " IBIASSUCÊ";	this.id[h] = " 2912004";	this.siafi[h] = " 3539";	this.municipio2[h] = " IBIASSUCE"; h++;
        this.municipio1[h] = " IBICARAI";	this.id[h] = " 2912103";	this.siafi[h] = " 3541";	this.municipio2[h] = " IBICARAI"; h++;
        this.municipio1[h] = " IBICOARA";	this.id[h] = " 2912202";	this.siafi[h] = " 3543";	this.municipio2[h] = " IBICOARA"; h++;
        this.municipio1[h] = " IBICUI";	this.id[h] = " 2912301";	this.siafi[h] = " 3545";	this.municipio2[h] = " IBICUI"; h++;
        this.municipio1[h] = " IBIPEBA";	this.id[h] = " 2912400";	this.siafi[h] = " 3547";	this.municipio2[h] = " IBIPEBA"; h++;
        this.municipio1[h] = " IBIPITANGA";	this.id[h] = " 2912509";	this.siafi[h] = " 3551";	this.municipio2[h] = " IBIPITANGA"; h++;
        this.municipio1[h] = " IBIQUERA";	this.id[h] = " 2912608";	this.siafi[h] = " 3553";	this.municipio2[h] = " IBIQUERA"; h++;
        this.municipio1[h] = " IBIRAPITANGA";	this.id[h] = " 2912707";	this.siafi[h] = " 3555";	this.municipio2[h] = " IBIRAPITANGA"; h++;
        this.municipio1[h] = " IBIRAPUÃ";	this.id[h] = " 2912806";	this.siafi[h] = " 3557";	this.municipio2[h] = " IBIRAPUA"; h++;
        this.municipio1[h] = " IBIRATAIA";	this.id[h] = " 2912905";	this.siafi[h] = " 3559";	this.municipio2[h] = " IBIRATAIA"; h++;
        this.municipio1[h] = " IBITIARA";	this.id[h] = " 2913002";	this.siafi[h] = " 3561";	this.municipio2[h] = " IBITIARA"; h++;
        this.municipio1[h] = " IBITITÁ";	this.id[h] = " 2913101";	this.siafi[h] = " 3563";	this.municipio2[h] = " IBITITA"; h++;
        this.municipio1[h] = " IBOTIRAMA";	this.id[h] = " 2913200";	this.siafi[h] = " 3565";	this.municipio2[h] = " IBOTIRAMA"; h++;
        this.municipio1[h] = " ICHU";	this.id[h] = " 2913309";	this.siafi[h] = " 3567";	this.municipio2[h] = " ICHU"; h++;
        this.municipio1[h] = " IGAPORÃ";	this.id[h] = " 2913408";	this.siafi[h] = " 3569";	this.municipio2[h] = " IGAPORA"; h++;
        this.municipio1[h] = " IGRAPIÚNA";	this.id[h] = " 2913457";	this.siafi[h] = " 3277";	this.municipio2[h] = " IGRAPIUNA"; h++;
        this.municipio1[h] = " IGUAI";	this.id[h] = " 2913507";	this.siafi[h] = " 3571";	this.municipio2[h] = " IGUAI"; h++;
        this.municipio1[h] = " ILHÉUS";	this.id[h] = " 2913606";	this.siafi[h] = " 3573";	this.municipio2[h] = " ILHEUS"; h++;
        this.municipio1[h] = " INHAMBUPE";	this.id[h] = " 2913705";	this.siafi[h] = " 3575";	this.municipio2[h] = " INHAMBUPE"; h++;
        this.municipio1[h] = " IPECAETÁ";	this.id[h] = " 2913804";	this.siafi[h] = " 3577";	this.municipio2[h] = " IPECAETA"; h++;
        this.municipio1[h] = " IPIAÚ";	this.id[h] = " 2913903";	this.siafi[h] = " 3579";	this.municipio2[h] = " IPIAU"; h++;
        this.municipio1[h] = " IPIRÁ";	this.id[h] = " 2914000";	this.siafi[h] = " 3581";	this.municipio2[h] = " IPIRA"; h++;
        this.municipio1[h] = " IPUPIARA";	this.id[h] = " 2914109";	this.siafi[h] = " 3583";	this.municipio2[h] = " IPUPIARA"; h++;
        this.municipio1[h] = " IRAJUBA";	this.id[h] = " 2914208";	this.siafi[h] = " 3585";	this.municipio2[h] = " IRAJUBA"; h++;
        this.municipio1[h] = " IRAMAIA";	this.id[h] = " 2914307";	this.siafi[h] = " 3587";	this.municipio2[h] = " IRAMAIA"; h++;
        this.municipio1[h] = " IRAQUARA";	this.id[h] = " 2914406";	this.siafi[h] = " 3589";	this.municipio2[h] = " IRAQUARA"; h++;
        this.municipio1[h] = " IRARÁ";	this.id[h] = " 2914505";	this.siafi[h] = " 3591";	this.municipio2[h] = " IRARA"; h++;
        this.municipio1[h] = " IRECÊ";	this.id[h] = " 2914604";	this.siafi[h] = " 3593";	this.municipio2[h] = " IRECE"; h++;
        this.municipio1[h] = " ITABELA";	this.id[h] = " 2914653";	this.siafi[h] = " 3279";	this.municipio2[h] = " ITABELA"; h++;
        this.municipio1[h] = " ITABERABA";	this.id[h] = " 2914703";	this.siafi[h] = " 3595";	this.municipio2[h] = " ITABERABA"; h++;
        this.municipio1[h] = " ITABUNA";	this.id[h] = " 2914802";	this.siafi[h] = " 3597";	this.municipio2[h] = " ITABUNA"; h++;
        this.municipio1[h] = " ITACARÉ";	this.id[h] = " 2914901";	this.siafi[h] = " 3599";	this.municipio2[h] = " ITACARE"; h++;
        this.municipio1[h] = " ITAETÊ";	this.id[h] = " 2915007";	this.siafi[h] = " 3601";	this.municipio2[h] = " ITAETE"; h++;
        this.municipio1[h] = " ITAGI";	this.id[h] = " 2915106";	this.siafi[h] = " 3603";	this.municipio2[h] = " ITAGI"; h++;
        this.municipio1[h] = " ITAGIBÁ";	this.id[h] = " 2915205";	this.siafi[h] = " 3605";	this.municipio2[h] = " ITAGIBA"; h++;
        this.municipio1[h] = " ITAGIMIRIM";	this.id[h] = " 2915304";	this.siafi[h] = " 3607";	this.municipio2[h] = " ITAGIMIRIM"; h++;
        this.municipio1[h] = " ITAGUAÇU DA BAHIA";	this.id[h] = " 2915353";	this.siafi[h] = " 3281";	this.municipio2[h] = " ITAGUACU DA BAHIA"; h++;
        this.municipio1[h] = " ITAJU DO COLÔNIA";	this.id[h] = " 2915403";	this.siafi[h] = " 3609";	this.municipio2[h] = " ITAJU DO COLONIA"; h++;
        this.municipio1[h] = " ITAJUIPE";	this.id[h] = " 2915502";	this.siafi[h] = " 3611";	this.municipio2[h] = " ITAJUIPE"; h++;
        this.municipio1[h] = " ITAMARAJU";	this.id[h] = " 2915601";	this.siafi[h] = " 3613";	this.municipio2[h] = " ITAMARAJU"; h++;
        this.municipio1[h] = " ITAMARI";	this.id[h] = " 2915700";	this.siafi[h] = " 3615";	this.municipio2[h] = " ITAMARI"; h++;
        this.municipio1[h] = " ITAMBÉ";	this.id[h] = " 2915809";	this.siafi[h] = " 3617";	this.municipio2[h] = " ITAMBE"; h++;
        this.municipio1[h] = " ITANAGRA";	this.id[h] = " 2915908";	this.siafi[h] = " 3619";	this.municipio2[h] = " ITANAGRA"; h++;
        this.municipio1[h] = " ITANHÉM";	this.id[h] = " 2916005";	this.siafi[h] = " 3621";	this.municipio2[h] = " ITANHEM"; h++;
        this.municipio1[h] = " ITAPARICA";	this.id[h] = " 2916104";	this.siafi[h] = " 3623";	this.municipio2[h] = " ITAPARICA"; h++;
        this.municipio1[h] = " ITAPÉ";	this.id[h] = " 2916203";	this.siafi[h] = " 3625";	this.municipio2[h] = " ITAPE"; h++;
        this.municipio1[h] = " ITAPEBI";	this.id[h] = " 2916302";	this.siafi[h] = " 3627";	this.municipio2[h] = " ITAPEBI"; h++;
        this.municipio1[h] = " ITAPETINGA";	this.id[h] = " 2916401";	this.siafi[h] = " 3629";	this.municipio2[h] = " ITAPETINGA"; h++;
        this.municipio1[h] = " ITAPICURU";	this.id[h] = " 2916500";	this.siafi[h] = " 3631";	this.municipio2[h] = " ITAPICURU"; h++;
        this.municipio1[h] = " ITAPITANGA";	this.id[h] = " 2916609";	this.siafi[h] = " 3633";	this.municipio2[h] = " ITAPITANGA"; h++;
        this.municipio1[h] = " ITAQUARA";	this.id[h] = " 2916708";	this.siafi[h] = " 3635";	this.municipio2[h] = " ITAQUARA"; h++;
        this.municipio1[h] = " ITARANTIM";	this.id[h] = " 2916807";	this.siafi[h] = " 3637";	this.municipio2[h] = " ITARANTIM"; h++;
        this.municipio1[h] = " ITATIM";	this.id[h] = " 2916856";	this.siafi[h] = " 3283";	this.municipio2[h] = " ITATIM"; h++;
        this.municipio1[h] = " ITIRUCU";	this.id[h] = " 2916906";	this.siafi[h] = " 3639";	this.municipio2[h] = " ITIRUCU"; h++;
        this.municipio1[h] = " ITIÚBA";	this.id[h] = " 2917003";	this.siafi[h] = " 3641";	this.municipio2[h] = " ITIUBA"; h++;
        this.municipio1[h] = " ITORORÓ";	this.id[h] = " 2917102";	this.siafi[h] = " 3643";	this.municipio2[h] = " ITORORO"; h++;
        this.municipio1[h] = " ITUAÇU";	this.id[h] = " 2917201";	this.siafi[h] = " 3645";	this.municipio2[h] = " ITUACU"; h++;
        this.municipio1[h] = " ITUBERÁ";	this.id[h] = " 2917300";	this.siafi[h] = " 3647";	this.municipio2[h] = " ITUBERA"; h++;
        this.municipio1[h] = " IUIÚ";	this.id[h] = " 2917334";	this.siafi[h] = " 3285";	this.municipio2[h] = " IUIU"; h++;
        this.municipio1[h] = " JABORANDI";	this.id[h] = " 2917359";	this.siafi[h] = " 9859";	this.municipio2[h] = " JABORANDI"; h++;
        this.municipio1[h] = " JACARACI";	this.id[h] = " 2917409";	this.siafi[h] = " 3649";	this.municipio2[h] = " JACARACI"; h++;
        this.municipio1[h] = " JACOBINA";	this.id[h] = " 2917508";	this.siafi[h] = " 3651";	this.municipio2[h] = " JACOBINA"; h++;
        this.municipio1[h] = " JAGUAQUARA";	this.id[h] = " 2917607";	this.siafi[h] = " 3653";	this.municipio2[h] = " JAGUAQUARA"; h++;
        this.municipio1[h] = " JAGUARARI";	this.id[h] = " 2917706";	this.siafi[h] = " 3655";	this.municipio2[h] = " JAGUARARI"; h++;
        this.municipio1[h] = " JAGUARIPE";	this.id[h] = " 2917805";	this.siafi[h] = " 3657";	this.municipio2[h] = " JAGUARIPE"; h++;
        this.municipio1[h] = " JANDAIRA";	this.id[h] = " 2917904";	this.siafi[h] = " 3659";	this.municipio2[h] = " JANDAIRA"; h++;
        this.municipio1[h] = " JEQUIÉ";	this.id[h] = " 2918001";	this.siafi[h] = " 3661";	this.municipio2[h] = " JEQUIE"; h++;
        this.municipio1[h] = " JEREMOABO";	this.id[h] = " 2918100";	this.siafi[h] = " 3663";	this.municipio2[h] = " JEREMOABO"; h++;
        this.municipio1[h] = " JIQUIRIÇÁ";	this.id[h] = " 2918209";	this.siafi[h] = " 3665";	this.municipio2[h] = " JIQUIRICA"; h++;
        this.municipio1[h] = " JITAÚNA";	this.id[h] = " 2918308";	this.siafi[h] = " 3667";	this.municipio2[h] = " JITAUNA"; h++;
        this.municipio1[h] = " JOÃO DOURADO";	this.id[h] = " 2918357";	this.siafi[h] = " 3099";	this.municipio2[h] = " JOAO DOURADO"; h++;
        this.municipio1[h] = " JUAZEIRO";	this.id[h] = " 2918407";	this.siafi[h] = " 3669";	this.municipio2[h] = " JUAZEIRO"; h++;
        this.municipio1[h] = " JUCURUÇU";	this.id[h] = " 2918456";	this.siafi[h] = " 3287";	this.municipio2[h] = " JUCURUCU"; h++;
        this.municipio1[h] = " JUSSARA";	this.id[h] = " 2918506";	this.siafi[h] = " 3671";	this.municipio2[h] = " JUSSARA"; h++;
        this.municipio1[h] = " JUSSARI";	this.id[h] = " 2918555";	this.siafi[h] = " 3069";	this.municipio2[h] = " JUSSARI"; h++;
        this.municipio1[h] = " JUSSIAPE";	this.id[h] = " 2918605";	this.siafi[h] = " 3673";	this.municipio2[h] = " JUSSIAPE"; h++;
        this.municipio1[h] = " LAFAIETE COUTINHO";	this.id[h] = " 2918704";	this.siafi[h] = " 3675";	this.municipio2[h] = " LAFAIETE COUTINHO"; h++;
        this.municipio1[h] = " LAGOA REAL";	this.id[h] = " 2918753";	this.siafi[h] = " 3289";	this.municipio2[h] = " LAGOA REAL"; h++;
        this.municipio1[h] = " LAJE";	this.id[h] = " 2918803";	this.siafi[h] = " 3677";	this.municipio2[h] = " LAJE"; h++;
        this.municipio1[h] = " LAJEDÃO";	this.id[h] = " 2918902";	this.siafi[h] = " 3679";	this.municipio2[h] = " LAJEDAO"; h++;
        this.municipio1[h] = " LAJEDINHO";	this.id[h] = " 2919009";	this.siafi[h] = " 3681";	this.municipio2[h] = " LAJEDINHO"; h++;
        this.municipio1[h] = " LAJEDO DO TABOCAL";	this.id[h] = " 2919058";	this.siafi[h] = " 3291";	this.municipio2[h] = " LAJEDO DO TABOCAL"; h++;
        this.municipio1[h] = " LAMARÃO";	this.id[h] = " 2919108";	this.siafi[h] = " 3683";	this.municipio2[h] = " LAMARAO"; h++;
        this.municipio1[h] = " LAPÃO";	this.id[h] = " 2919157";	this.siafi[h] = " 3973";	this.municipio2[h] = " LAPAO"; h++;
        this.municipio1[h] = " LAURO DE FREITAS";	this.id[h] = " 2919207";	this.siafi[h] = " 3685";	this.municipio2[h] = " LAURO DE FREITAS"; h++;
        this.municipio1[h] = " LENÇOIS";	this.id[h] = " 2919306";	this.siafi[h] = " 3687";	this.municipio2[h] = " LENCOIS"; h++;
        this.municipio1[h] = " LICINIO DE ALMEIDA";	this.id[h] = " 2919405";	this.siafi[h] = " 3689";	this.municipio2[h] = " LICINIO DE ALMEIDA"; h++;
        this.municipio1[h] = " LIVRAMENTO DE NOSSA SENHORA";	this.id[h] = " 2919504";	this.siafi[h] = " 3691";	this.municipio2[h] = " LIVRAMENTO DE NOSSA SENHORA"; h++;
        this.municipio1[h] = " LUIS EDUARDO MAGALHAES";	this.id[h] = " 2919553";	this.siafi[h] = " 1112";	this.municipio2[h] = " LUIS EDUARDO MAGALHAES"; h++;
        this.municipio1[h] = " MACAJUBA";	this.id[h] = " 2919603";	this.siafi[h] = " 3693";	this.municipio2[h] = " MACAJUBA"; h++;
        this.municipio1[h] = " MACARANI";	this.id[h] = " 2919702";	this.siafi[h] = " 3695";	this.municipio2[h] = " MACARANI"; h++;
        this.municipio1[h] = " MACAÚBAS";	this.id[h] = " 2919801";	this.siafi[h] = " 3697";	this.municipio2[h] = " MACAUBAS"; h++;
        this.municipio1[h] = " MACURURÉ";	this.id[h] = " 2919900";	this.siafi[h] = " 3699";	this.municipio2[h] = " MACURURE"; h++;
        this.municipio1[h] = " MADRE DE DEUS";	this.id[h] = " 2919926";	this.siafi[h] = " 3293";	this.municipio2[h] = " MADRE DE DEUS"; h++;
        this.municipio1[h] = " MAETINGA";	this.id[h] = " 2919959";	this.siafi[h] = " 3975";	this.municipio2[h] = " MAETINGA"; h++;
        this.municipio1[h] = " MAIQUINIQUE";	this.id[h] = " 2920007";	this.siafi[h] = " 3701";	this.municipio2[h] = " MAIQUINIQUE"; h++;
        this.municipio1[h] = " MAIRI";	this.id[h] = " 2920106";	this.siafi[h] = " 3703";	this.municipio2[h] = " MAIRI"; h++;
        this.municipio1[h] = " MALHADA";	this.id[h] = " 2920205";	this.siafi[h] = " 3705";	this.municipio2[h] = " MALHADA"; h++;
        this.municipio1[h] = " MALHADA DE PEDRAS";	this.id[h] = " 2920304";	this.siafi[h] = " 3707";	this.municipio2[h] = " MALHADA DE PEDRAS"; h++;
        this.municipio1[h] = " MANOEL VITORINO";	this.id[h] = " 2920403";	this.siafi[h] = " 3709";	this.municipio2[h] = " MANOEL VITORINO"; h++;
        this.municipio1[h] = " MANSIDÃO";	this.id[h] = " 2920452";	this.siafi[h] = " 3977";	this.municipio2[h] = " MANSIDAO"; h++;
        this.municipio1[h] = " MARACÁS";	this.id[h] = " 2920502";	this.siafi[h] = " 3711";	this.municipio2[h] = " MARACAS"; h++;
        this.municipio1[h] = " MARAGOGIPE";	this.id[h] = " 2920601";	this.siafi[h] = " 3713";	this.municipio2[h] = " MARAGOGIPE"; h++;
        this.municipio1[h] = " MARAÚ";	this.id[h] = " 2920700";	this.siafi[h] = " 3715";	this.municipio2[h] = " MARAU"; h++;
        this.municipio1[h] = " MARCIONILIO SOUZA";	this.id[h] = " 2920809";	this.siafi[h] = " 3717";	this.municipio2[h] = " MARCIONILIO SOUZA"; h++;
        this.municipio1[h] = " MASCOTE";	this.id[h] = " 2920908";	this.siafi[h] = " 3719";	this.municipio2[h] = " MASCOTE"; h++;
        this.municipio1[h] = " MATA DE SÃO JOÃO";	this.id[h] = " 2921005";	this.siafi[h] = " 3721";	this.municipio2[h] = " MATA DE SAO JOAO"; h++;
        this.municipio1[h] = " MATINA";	this.id[h] = " 2921054";	this.siafi[h] = " 3295";	this.municipio2[h] = " MATINA"; h++;
        this.municipio1[h] = " MEDEIROS NETO";	this.id[h] = " 2921104";	this.siafi[h] = " 3723";	this.municipio2[h] = " MEDEIROS NETO"; h++;
        this.municipio1[h] = " MIGUEL CALMON";	this.id[h] = " 2921203";	this.siafi[h] = " 3725";	this.municipio2[h] = " MIGUEL CALMON"; h++;
        this.municipio1[h] = " MILAGRES";	this.id[h] = " 2921302";	this.siafi[h] = " 3727";	this.municipio2[h] = " MILAGRES"; h++;
        this.municipio1[h] = " MIRANGABA";	this.id[h] = " 2921401";	this.siafi[h] = " 3729";	this.municipio2[h] = " MIRANGABA"; h++;
        this.municipio1[h] = " MIRANTE";	this.id[h] = " 2921450";	this.siafi[h] = " 3297";	this.municipio2[h] = " MIRANTE"; h++;
        this.municipio1[h] = " MONTE SANTO";	this.id[h] = " 2921500";	this.siafi[h] = " 3731";	this.municipio2[h] = " MONTE SANTO"; h++;
        this.municipio1[h] = " MORPARÁ";	this.id[h] = " 2921609";	this.siafi[h] = " 3733";	this.municipio2[h] = " MORPARA"; h++;
        this.municipio1[h] = " MORRO DO CHAPÉU";	this.id[h] = " 2921708";	this.siafi[h] = " 3735";	this.municipio2[h] = " MORRO DO CHAPEU"; h++;
        this.municipio1[h] = " MORTUGABA";	this.id[h] = " 2921807";	this.siafi[h] = " 3737";	this.municipio2[h] = " MORTUGABA"; h++;
        this.municipio1[h] = " MUCUGÊ";	this.id[h] = " 2921906";	this.siafi[h] = " 3739";	this.municipio2[h] = " MUCUGE"; h++;
        this.municipio1[h] = " MUCURI";	this.id[h] = " 2922003";	this.siafi[h] = " 3741";	this.municipio2[h] = " MUCURI"; h++;
        this.municipio1[h] = " MULUNGU DO MORRO";	this.id[h] = " 2922052";	this.siafi[h] = " 3299";	this.municipio2[h] = " MULUNGU DO MORRO"; h++;
        this.municipio1[h] = " MUNDO NOVO";	this.id[h] = " 2922102";	this.siafi[h] = " 3743";	this.municipio2[h] = " MUNDO NOVO"; h++;
        this.municipio1[h] = " MUNIZ FERREIRA";	this.id[h] = " 2922201";	this.siafi[h] = " 3745";	this.municipio2[h] = " MUNIZ FERREIRA"; h++;
        this.municipio1[h] = " MUQUÉM DE SÃO FRANCISCO";	this.id[h] = " 2922250";	this.siafi[h] = " 3005";	this.municipio2[h] = " MUQUEM DE SAO FRANCISCO"; h++;
        this.municipio1[h] = " MURITIBA";	this.id[h] = " 2922300";	this.siafi[h] = " 3747";	this.municipio2[h] = " MURITIBA"; h++;
        this.municipio1[h] = " MUTUIPE";	this.id[h] = " 2922409";	this.siafi[h] = " 3749";	this.municipio2[h] = " MUTUIPE"; h++;
        this.municipio1[h] = " NAZARÉ";	this.id[h] = " 2922508";	this.siafi[h] = " 3751";	this.municipio2[h] = " NAZARE"; h++;
        this.municipio1[h] = " NILO PEÇANHA";	this.id[h] = " 2922607";	this.siafi[h] = " 3753";	this.municipio2[h] = " NILO PECANHA"; h++;
        this.municipio1[h] = " NORDESTINA";	this.id[h] = " 2922656";	this.siafi[h] = " 3979";	this.municipio2[h] = " NORDESTINA"; h++;
        this.municipio1[h] = " NOVA CANAÃ";	this.id[h] = " 2922706";	this.siafi[h] = " 3755";	this.municipio2[h] = " NOVA CANAA"; h++;
        this.municipio1[h] = " NOVA FÁTIMA";	this.id[h] = " 2922730";	this.siafi[h] = " 3007";	this.municipio2[h] = " NOVA FATIMA"; h++;
        this.municipio1[h] = " NOVA IBIÁ";	this.id[h] = " 2922755";	this.siafi[h] = " 3009";	this.municipio2[h] = " NOVA IBIA"; h++;
        this.municipio1[h] = " NOVA ITARANA";	this.id[h] = " 2922805";	this.siafi[h] = " 3757";	this.municipio2[h] = " NOVA ITARANA"; h++;
        this.municipio1[h] = " NOVA REDENÇÃO";	this.id[h] = " 2922854";	this.siafi[h] = " 3011";	this.municipio2[h] = " NOVA REDENCAO"; h++;
        this.municipio1[h] = " NOVA SOURE";	this.id[h] = " 2922904";	this.siafi[h] = " 3759";	this.municipio2[h] = " NOVA SOURE"; h++;
        this.municipio1[h] = " NOVA VIÇOSA";	this.id[h] = " 2923001";	this.siafi[h] = " 3761";	this.municipio2[h] = " NOVA VICOSA"; h++;
        this.municipio1[h] = " NOVO HORIZONTE";	this.id[h] = " 2923035";	this.siafi[h] = " 3013";	this.municipio2[h] = " NOVO HORIZONTE"; h++;
        this.municipio1[h] = " NOVO TRIUNFO";	this.id[h] = " 2923050";	this.siafi[h] = " 3015";	this.municipio2[h] = " NOVO TRIUNFO"; h++;
        this.municipio1[h] = " OLINDINA";	this.id[h] = " 2923100";	this.siafi[h] = " 3763";	this.municipio2[h] = " OLINDINA"; h++;
        this.municipio1[h] = " OLIVEIRA DOS BREJINHOS";	this.id[h] = " 2923209";	this.siafi[h] = " 3765";	this.municipio2[h] = " OLIVEIRA DOS BREJINHOS"; h++;
        this.municipio1[h] = " OURIÇANGAS";	this.id[h] = " 2923308";	this.siafi[h] = " 3767";	this.municipio2[h] = " OURICANGAS"; h++;
        this.municipio1[h] = " OUROLÂNDIA";	this.id[h] = " 2923357";	this.siafi[h] = " 3017";	this.municipio2[h] = " OUROLANDIA"; h++;
        this.municipio1[h] = " PALMAS DE MONTE ALTO";	this.id[h] = " 2923407";	this.siafi[h] = " 3769";	this.municipio2[h] = " PALMAS DE MONTE ALTO"; h++;
        this.municipio1[h] = " PALMEIRAS";	this.id[h] = " 2923506";	this.siafi[h] = " 3771";	this.municipio2[h] = " PALMEIRAS"; h++;
        this.municipio1[h] = " PARAMIRIM";	this.id[h] = " 2923605";	this.siafi[h] = " 3773";	this.municipio2[h] = " PARAMIRIM"; h++;
        this.municipio1[h] = " PARATINGA";	this.id[h] = " 2923704";	this.siafi[h] = " 3775";	this.municipio2[h] = " PARATINGA"; h++;
        this.municipio1[h] = " PARIPIRANGA";	this.id[h] = " 2923803";	this.siafi[h] = " 3777";	this.municipio2[h] = " PARIPIRANGA"; h++;
        this.municipio1[h] = " PAU BRASIL";	this.id[h] = " 2923902";	this.siafi[h] = " 3779";	this.municipio2[h] = " PAU BRASIL"; h++;
        this.municipio1[h] = " PAULO AFONSO";	this.id[h] = " 2924009";	this.siafi[h] = " 3781";	this.municipio2[h] = " PAULO AFONSO"; h++;
        this.municipio1[h] = " PÉ DE SERRA";	this.id[h] = " 2924058";	this.siafi[h] = " 3981";	this.municipio2[h] = " PE DE SERRA"; h++;
        this.municipio1[h] = " PEDRÃO";	this.id[h] = " 2924108";	this.siafi[h] = " 3783";	this.municipio2[h] = " PEDRAO"; h++;
        this.municipio1[h] = " PEDRO ALEXANDRE";	this.id[h] = " 2924207";	this.siafi[h] = " 3785";	this.municipio2[h] = " PEDRO ALEXANDRE"; h++;
        this.municipio1[h] = " PIATÃ";	this.id[h] = " 2924306";	this.siafi[h] = " 3787";	this.municipio2[h] = " PIATA"; h++;
        this.municipio1[h] = " PILÃO ARCADO";	this.id[h] = " 2924405";	this.siafi[h] = " 3789";	this.municipio2[h] = " PILAO ARCADO"; h++;
        this.municipio1[h] = " PINDAI";	this.id[h] = " 2924504";	this.siafi[h] = " 3791";	this.municipio2[h] = " PINDAI"; h++;
        this.municipio1[h] = " PINDOBAÇU";	this.id[h] = " 2924603";	this.siafi[h] = " 3793";	this.municipio2[h] = " PINDOBACU"; h++;
        this.municipio1[h] = " PINTADAS";	this.id[h] = " 2924652";	this.siafi[h] = " 3983";	this.municipio2[h] = " PINTADAS"; h++;
        this.municipio1[h] = " PIRAI DO NORTE";	this.id[h] = " 2924678";	this.siafi[h] = " 3019";	this.municipio2[h] = " PIRAI DO NORTE"; h++;
        this.municipio1[h] = " PIRIPÁ";	this.id[h] = " 2924702";	this.siafi[h] = " 3795";	this.municipio2[h] = " PIRIPA"; h++;
        this.municipio1[h] = " PIRITIBA";	this.id[h] = " 2924801";	this.siafi[h] = " 3797";	this.municipio2[h] = " PIRITIBA"; h++;
        this.municipio1[h] = " PLANALTINO";	this.id[h] = " 2924900";	this.siafi[h] = " 3799";	this.municipio2[h] = " PLANALTINO"; h++;
        this.municipio1[h] = " PLANALTO";	this.id[h] = " 2925006";	this.siafi[h] = " 3801";	this.municipio2[h] = " PLANALTO"; h++;
        this.municipio1[h] = " POÇÕES";	this.id[h] = " 2925105";	this.siafi[h] = " 3803";	this.municipio2[h] = " POCOES"; h++;
        this.municipio1[h] = " POJUCA";	this.id[h] = " 2925204";	this.siafi[h] = " 3805";	this.municipio2[h] = " POJUCA"; h++;
        this.municipio1[h] = " PONTO NOVO";	this.id[h] = " 2925253";	this.siafi[h] = " 3021";	this.municipio2[h] = " PONTO NOVO"; h++;
        this.municipio1[h] = " PORTO SEGURO";	this.id[h] = " 2925303";	this.siafi[h] = " 3807";	this.municipio2[h] = " PORTO SEGURO"; h++;
        this.municipio1[h] = " POTIRAGUÁ";	this.id[h] = " 2925402";	this.siafi[h] = " 3809";	this.municipio2[h] = " POTIRAGUA"; h++;
        this.municipio1[h] = " PRADO";	this.id[h] = " 2925501";	this.siafi[h] = " 3811";	this.municipio2[h] = " PRADO"; h++;
        this.municipio1[h] = " PRESIDENTE DUTRA";	this.id[h] = " 2925600";	this.siafi[h] = " 3813";	this.municipio2[h] = " PRESIDENTE DUTRA"; h++;
        this.municipio1[h] = " PRESIDENTE JÂNIO QUADROS";	this.id[h] = " 2925709";	this.siafi[h] = " 3815";	this.municipio2[h] = " PRESIDENTE JANIO QUADROS"; h++;
        this.municipio1[h] = " PRESIDENTE TANCREDO NEVES";	this.id[h] = " 2925758";	this.siafi[h] = " 3023";	this.municipio2[h] = " PRESIDENTE TANCREDO NEVES"; h++;
        this.municipio1[h] = " QUEIMADAS";	this.id[h] = " 2925808";	this.siafi[h] = " 3817";	this.municipio2[h] = " QUEIMADAS"; h++;
        this.municipio1[h] = " QUIJINGUE";	this.id[h] = " 2925907";	this.siafi[h] = " 3819";	this.municipio2[h] = " QUIJINGUE"; h++;
        this.municipio1[h] = " QUIXABEIRA";	this.id[h] = " 2925931";	this.siafi[h] = " 3025";	this.municipio2[h] = " QUIXABEIRA"; h++;
        this.municipio1[h] = " RAFAEL JAMBEIRO";	this.id[h] = " 2925956";	this.siafi[h] = " 3985";	this.municipio2[h] = " RAFAEL JAMBEIRO"; h++;
        this.municipio1[h] = " REMANSO";	this.id[h] = " 2926004";	this.siafi[h] = " 3821";	this.municipio2[h] = " REMANSO"; h++;
        this.municipio1[h] = " RETIROLÂNDIA";	this.id[h] = " 2926103";	this.siafi[h] = " 3823";	this.municipio2[h] = " RETIROLANDIA"; h++;
        this.municipio1[h] = " RIACHÃO DAS NEVES";	this.id[h] = " 2926202";	this.siafi[h] = " 3825";	this.municipio2[h] = " RIACHAO DAS NEVES"; h++;
        this.municipio1[h] = " RIACHÃO DO JACUIPE";	this.id[h] = " 2926301";	this.siafi[h] = " 3827";	this.municipio2[h] = " RIACHAO DO JACUIPE"; h++;
        this.municipio1[h] = " RIACHO DE SANTANA";	this.id[h] = " 2926400";	this.siafi[h] = " 3829";	this.municipio2[h] = " RIACHO DE SANTANA"; h++;
        this.municipio1[h] = " RIBEIRA DO AMPARO";	this.id[h] = " 2926509";	this.siafi[h] = " 3831";	this.municipio2[h] = " RIBEIRA DO AMPARO"; h++;
        this.municipio1[h] = " RIBEIRA DO POMBAL";	this.id[h] = " 2926608";	this.siafi[h] = " 3833";	this.municipio2[h] = " RIBEIRA DO POMBAL"; h++;
        this.municipio1[h] = " RIBEIRÃO DO LARGO";	this.id[h] = " 2926657";	this.siafi[h] = " 3027";	this.municipio2[h] = " RIBEIRAO DO LARGO"; h++;
        this.municipio1[h] = " RIO DE CONTAS";	this.id[h] = " 2926707";	this.siafi[h] = " 3835";	this.municipio2[h] = " RIO DE CONTAS"; h++;
        this.municipio1[h] = " RIO DO ANTÔNIO";	this.id[h] = " 2926806";	this.siafi[h] = " 3837";	this.municipio2[h] = " RIO DO ANTONIO"; h++;
        this.municipio1[h] = " RIO DO PIRES";	this.id[h] = " 2926905";	this.siafi[h] = " 3839";	this.municipio2[h] = " RIO DO PIRES"; h++;
        this.municipio1[h] = " RIO REAL";	this.id[h] = " 2927002";	this.siafi[h] = " 3841";	this.municipio2[h] = " RIO REAL"; h++;
        this.municipio1[h] = " RODELAS";	this.id[h] = " 2927101";	this.siafi[h] = " 3843";	this.municipio2[h] = " RODELAS"; h++;
        this.municipio1[h] = " RUY BARBOSA";	this.id[h] = " 2927200";	this.siafi[h] = " 3845";	this.municipio2[h] = " RUY BARBOSA"; h++;
        this.municipio1[h] = " SALINAS DA MARGARIDA";	this.id[h] = " 2927309";	this.siafi[h] = " 3847";	this.municipio2[h] = " SALINAS DA MARGARIDA"; h++;
        this.municipio1[h] = " SALVADOR";	this.id[h] = " 2927408";	this.siafi[h] = " 3849";	this.municipio2[h] = " SALVADOR"; h++;
        this.municipio1[h] = " SANTA BÁRBARA";	this.id[h] = " 2927507";	this.siafi[h] = " 3851";	this.municipio2[h] = " SANTA BARBARA"; h++;
        this.municipio1[h] = " SANTA BRIGIDA";	this.id[h] = " 2927606";	this.siafi[h] = " 3853";	this.municipio2[h] = " SANTA BRIGIDA"; h++;
        this.municipio1[h] = " SANTA CRUZ CABRÁLIA";	this.id[h] = " 2927705";	this.siafi[h] = " 3855";	this.municipio2[h] = " SANTA CRUZ CABRALIA"; h++;
        this.municipio1[h] = " SANTA CRUZ DA VITÓRIA";	this.id[h] = " 2927804";	this.siafi[h] = " 3857";	this.municipio2[h] = " SANTA CRUZ DA VITORIA"; h++;
        this.municipio1[h] = " SANTA INÊS";	this.id[h] = " 2927903";	this.siafi[h] = " 3859";	this.municipio2[h] = " SANTA INES"; h++;
        this.municipio1[h] = " SANTALUZ";	this.id[h] = " 2928000";	this.siafi[h] = " 3987";	this.municipio2[h] = " SANTA LUZIA"; h++;
        this.municipio1[h] = " SANTA LUZIA";	this.id[h] = " 2928059";	this.siafi[h] = " 3863";	this.municipio2[h] = " SANTA MARIA DA VITORIA"; h++;
        this.municipio1[h] = " SANTA MARIA DA VITÓRIA";	this.id[h] = " 2928109";	this.siafi[h] = " 3549";	this.municipio2[h] = " SANTA RITA DE CASSIA"; h++;
        this.municipio1[h] = " SANTANA";	this.id[h] = " 2928208";	this.siafi[h] = " 3869";	this.municipio2[h] = " SANTA TERESINHA"; h++;
        this.municipio1[h] = " SANTANÓPOLIS";	this.id[h] = " 2928307";	this.siafi[h] = " 3861";	this.municipio2[h] = " SANTALUZ"; h++;
        this.municipio1[h] = " SANTA RITA DE CÁSSIA";	this.id[h] = " 2928406";	this.siafi[h] = " 3865";	this.municipio2[h] = " SANTANA"; h++;
        this.municipio1[h] = " SANTA TERESINHA";	this.id[h] = " 2928505";	this.siafi[h] = " 3867";	this.municipio2[h] = " SANTANOPOLIS"; h++;
        this.municipio1[h] = " SANTO AMARO";	this.id[h] = " 2928604";	this.siafi[h] = " 3871";	this.municipio2[h] = " SANTO AMARO"; h++;
        this.municipio1[h] = " SANTO ANTÔNIO DE JESUS";	this.id[h] = " 2928703";	this.siafi[h] = " 3873";	this.municipio2[h] = " SANTO ANTONIO DE JESUS"; h++;
        this.municipio1[h] = " SANTO ESTEVÃO";	this.id[h] = " 2928802";	this.siafi[h] = " 3875";	this.municipio2[h] = " SANTO ESTEVAO"; h++;
        this.municipio1[h] = " SÃO DESIDÉRIO";	this.id[h] = " 2928901";	this.siafi[h] = " 3877";	this.municipio2[h] = " SAO DESIDERIO"; h++;
        this.municipio1[h] = " SÃO DOMINGOS";	this.id[h] = " 2928950";	this.siafi[h] = " 3029";	this.municipio2[h] = " SAO DOMINGOS"; h++;
        this.municipio1[h] = " SÃO FELIPE";	this.id[h] = " 2929107";	this.siafi[h] = " 3881";	this.municipio2[h] = " SAO FELIPE"; h++;
        this.municipio1[h] = " SÃO FÉLIX";	this.id[h] = " 2929008";	this.siafi[h] = " 3879";	this.municipio2[h] = " SAO FELIX"; h++;
        this.municipio1[h] = " SÃO FÉLIX DO CORIBE";	this.id[h] = " 2929057";	this.siafi[h] = " 3031";	this.municipio2[h] = " SAO FELIX DO CORIBE"; h++;
        this.municipio1[h] = " SÃO FRANCISCO DO CONDE";	this.id[h] = " 2929206";	this.siafi[h] = " 3883";	this.municipio2[h] = " SAO FRANCISCO DO CONDE"; h++;
        this.municipio1[h] = " SÃO GABRIEL";	this.id[h] = " 2929255";	this.siafi[h] = " 3989";	this.municipio2[h] = " SAO GABRIEL"; h++;
        this.municipio1[h] = " SÃO GONÇALO DOS CAMPOS";	this.id[h] = " 2929305";	this.siafi[h] = " 3885";	this.municipio2[h] = " SAO GONCALO DOS CAMPOS"; h++;
        this.municipio1[h] = " SÃO JOSÉ DA VITÓRIA";	this.id[h] = " 2929354";	this.siafi[h] = " 3035";	this.municipio2[h] = " SAO JOSE DA VITORIA"; h++;
        this.municipio1[h] = " SÃO JOSÉ DO JACUIPE";	this.id[h] = " 2929370";	this.siafi[h] = " 3033";	this.municipio2[h] = " SAO JOSE DO JACUIPE"; h++;
        this.municipio1[h] = " SÃO MIGUEL DAS MATAS";	this.id[h] = " 2929404";	this.siafi[h] = " 3887";	this.municipio2[h] = " SAO MIGUEL DAS MATAS"; h++;
        this.municipio1[h] = " SÃO SEBASTIÃO DO PASSE";	this.id[h] = " 2929503";	this.siafi[h] = " 3889";	this.municipio2[h] = " SAO SEBASTIAO DO PASSE"; h++;
        this.municipio1[h] = " SAPEAÇU";	this.id[h] = " 2929602";	this.siafi[h] = " 3891";	this.municipio2[h] = " SAPEACU"; h++;
        this.municipio1[h] = " SÁTIRO DIAS";	this.id[h] = " 2929701";	this.siafi[h] = " 3893";	this.municipio2[h] = " SATIRO DIAS"; h++;
        this.municipio1[h] = " SAUBARA";	this.id[h] = " 2929750";	this.siafi[h] = " 3037";	this.municipio2[h] = " SAUBARA"; h++;
        this.municipio1[h] = " SAÚDE";	this.id[h] = " 2929800";	this.siafi[h] = " 3895";	this.municipio2[h] = " SAUDE"; h++;
        this.municipio1[h] = " SEABRA";	this.id[h] = " 2929909";	this.siafi[h] = " 3897";	this.municipio2[h] = " SEABRA"; h++;
        this.municipio1[h] = " SEBASTIÃO LARANJEIRAS";	this.id[h] = " 2930006";	this.siafi[h] = " 3899";	this.municipio2[h] = " SEBASTIAO LARANJEIRAS"; h++;
        this.municipio1[h] = " SENHOR DO BONFIM";	this.id[h] = " 2930105";	this.siafi[h] = " 3901";	this.municipio2[h] = " SENHOR DO BONFIM"; h++;
        this.municipio1[h] = " SENTO SÉ";	this.id[h] = " 2930204";	this.siafi[h] = " 3903";	this.municipio2[h] = " SENTO SE"; h++;
        this.municipio1[h] = " SERRA DO RAMALHO";	this.id[h] = " 2930154";	this.siafi[h] = " 3039";	this.municipio2[h] = " SERRA DO RAMALHO"; h++;
        this.municipio1[h] = " SERRA DOURADA";	this.id[h] = " 2930303";	this.siafi[h] = " 3905";	this.municipio2[h] = " SERRA DOURADA"; h++;
        this.municipio1[h] = " SERRA PRETA";	this.id[h] = " 2930402";	this.siafi[h] = " 3907";	this.municipio2[h] = " SERRA PRETA"; h++;
        this.municipio1[h] = " SERRINHA";	this.id[h] = " 2930501";	this.siafi[h] = " 3909";	this.municipio2[h] = " SERRINHA"; h++;
        this.municipio1[h] = " SERROLANDIA";	this.id[h] = " 2930600";	this.siafi[h] = " 3911";	this.municipio2[h] = " SERROLANDIA"; h++;
        this.municipio1[h] = " SIMÕES FILHO";	this.id[h] = " 2930709";	this.siafi[h] = " 3913";	this.municipio2[h] = " SIMOES FILHO"; h++;
        this.municipio1[h] = " SITIO DO MATO";	this.id[h] = " 2930758";	this.siafi[h] = " 3041";	this.municipio2[h] = " SITIO DO MATO"; h++;
        this.municipio1[h] = " SITIO DO QUINTO";	this.id[h] = " 2930766";	this.siafi[h] = " 3043";	this.municipio2[h] = " SITIO DO QUINTO"; h++;
        this.municipio1[h] = " SOBRADINHO";	this.id[h] = " 2930774";	this.siafi[h] = " 3045";	this.municipio2[h] = " SOBRADINHO"; h++;
        this.municipio1[h] = " SOUTO SOARES";	this.id[h] = " 2930808";	this.siafi[h] = " 3915";	this.municipio2[h] = " SOUTO SOARES"; h++;
        this.municipio1[h] = " TABOCAS DO BREJO VELHO";	this.id[h] = " 2930907";	this.siafi[h] = " 3917";	this.municipio2[h] = " TABOCAS DO BREJO VELHO"; h++;
        this.municipio1[h] = " TANHACU";	this.id[h] = " 2931004";	this.siafi[h] = " 3919";	this.municipio2[h] = " TANHACU"; h++;
        this.municipio1[h] = " TANQUE NOVO";	this.id[h] = " 2931053";	this.siafi[h] = " 3991";	this.municipio2[h] = " TANQUE NOVO"; h++;
        this.municipio1[h] = " TANQUINHO";	this.id[h] = " 2931103";	this.siafi[h] = " 3921";	this.municipio2[h] = " TANQUINHO"; h++;
        this.municipio1[h] = " TAPEROÁ";	this.id[h] = " 2931202";	this.siafi[h] = " 3923";	this.municipio2[h] = " TAPEROA"; h++;
        this.municipio1[h] = " TAPIRAMUTÁ";	this.id[h] = " 2931301";	this.siafi[h] = " 3925";	this.municipio2[h] = " TAPIRAMUTA"; h++;
        this.municipio1[h] = " TEIXEIRA DE FREITAS";	this.id[h] = " 2931350";	this.siafi[h] = " 3993";	this.municipio2[h] = " TEIXEIRA DE FREITAS"; h++;
        this.municipio1[h] = " TEODORO SAMPAIO";	this.id[h] = " 2931400";	this.siafi[h] = " 3927";	this.municipio2[h] = " TEODORO SAMPAIO"; h++;
        this.municipio1[h] = " TEOFILÂNDIA";	this.id[h] = " 2931509";	this.siafi[h] = " 3929";	this.municipio2[h] = " TEOFILANDIA"; h++;
        this.municipio1[h] = " TEOLÂNDIA";	this.id[h] = " 2931608";	this.siafi[h] = " 3931";	this.municipio2[h] = " TEOLANDIA"; h++;
        this.municipio1[h] = " TERRA NOVA";	this.id[h] = " 2931707";	this.siafi[h] = " 3933";	this.municipio2[h] = " TERRA NOVA"; h++;
        this.municipio1[h] = " TREMEDAL";	this.id[h] = " 2931806";	this.siafi[h] = " 3935";	this.municipio2[h] = " TREMEDAL"; h++;
        this.municipio1[h] = " TUCANO";	this.id[h] = " 2931905";	this.siafi[h] = " 3937";	this.municipio2[h] = " TUCANO"; h++;
        this.municipio1[h] = " UAUÁ";	this.id[h] = " 2932002";	this.siafi[h] = " 3939";	this.municipio2[h] = " UAUA"; h++;
        this.municipio1[h] = " UBAIRA";	this.id[h] = " 2932101";	this.siafi[h] = " 3941";	this.municipio2[h] = " UBAIRA"; h++;
        this.municipio1[h] = " UBAITABA";	this.id[h] = " 2932200";	this.siafi[h] = " 3943";	this.municipio2[h] = " UBAITABA"; h++;
        this.municipio1[h] = " UBATÃ";	this.id[h] = " 2932309";	this.siafi[h] = " 3945";	this.municipio2[h] = " UBATA"; h++;
        this.municipio1[h] = " UIBAI";	this.id[h] = " 2932408";	this.siafi[h] = " 3947";	this.municipio2[h] = " UIBAI"; h++;
        this.municipio1[h] = " UMBURANAS";	this.id[h] = " 2932457";	this.siafi[h] = " 3047";	this.municipio2[h] = " UMBURANAS"; h++;
        this.municipio1[h] = " UNA";	this.id[h] = " 2932507";	this.siafi[h] = " 3949";	this.municipio2[h] = " UNA"; h++;
        this.municipio1[h] = " URANDI";	this.id[h] = " 2932606";	this.siafi[h] = " 3951";	this.municipio2[h] = " URANDI"; h++;
        this.municipio1[h] = " URUÇUCA";	this.id[h] = " 2932705";	this.siafi[h] = " 3953";	this.municipio2[h] = " URUCUCA"; h++;
        this.municipio1[h] = " UTINGA";	this.id[h] = " 2932804";	this.siafi[h] = " 3955";	this.municipio2[h] = " UTINGA"; h++;
        this.municipio1[h] = " VALENÇA";	this.id[h] = " 2932903";	this.siafi[h] = " 3957";	this.municipio2[h] = " VALENCA"; h++;
        this.municipio1[h] = " VALENTE";	this.id[h] = " 2933000";	this.siafi[h] = " 3959";	this.municipio2[h] = " VALENTE"; h++;
        this.municipio1[h] = " VÁRZEA DA ROCA";	this.id[h] = " 2933059";	this.siafi[h] = " 3997";	this.municipio2[h] = " VARZEA DA ROCA"; h++;
        this.municipio1[h] = " VÁRZEA DO POCO";	this.id[h] = " 2933109";	this.siafi[h] = " 3961";	this.municipio2[h] = " VARZEA DO POCO"; h++;
        this.municipio1[h] = " VÁRZEA NOVA";	this.id[h] = " 2933158";	this.siafi[h] = " 3995";	this.municipio2[h] = " VARZEA NOVA"; h++;
        this.municipio1[h] = " VARZEDO";	this.id[h] = " 2933174";	this.siafi[h] = " 3049";	this.municipio2[h] = " VARZEDO"; h++;
        this.municipio1[h] = " VERA CRUZ";	this.id[h] = " 2933208";	this.siafi[h] = " 3963";	this.municipio2[h] = " VERA CRUZ"; h++;
        this.municipio1[h] = " VEREDA";	this.id[h] = " 2933257";	this.siafi[h] = " 3051";	this.municipio2[h] = " VEREDA"; h++;
        this.municipio1[h] = " VITÓRIA DA CONQUISTA";	this.id[h] = " 2933307";	this.siafi[h] = " 3965";	this.municipio2[h] = " VITORIA DA CONQUISTA"; h++;
        this.municipio1[h] = " WAGNER";	this.id[h] = " 2933406";	this.siafi[h] = " 3967";	this.municipio2[h] = " WAGNER"; h++;
        this.municipio1[h] = " WANDERLEY";	this.id[h] = " 2933455";	this.siafi[h] = " 3999";	this.municipio2[h] = " WANDERLEY"; h++;
        this.municipio1[h] = " WENCESLAU GUIMARÃES";	this.id[h] = " 2933505";	this.siafi[h] = " 3969";	this.municipio2[h] = " WENCESLAU GUIMARAES"; h++;
        this.municipio1[h] = " XIQUE-XIQUE";	this.id[h] = " 2933604";	this.siafi[h] = " 3971";	this.municipio2[h] = " XIQUE-XIQUE"; h++;
    }
}