/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzz;

import static buzz.TelaConfig.autocarroLinkedList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


/**
 *
 * @author Valeriano Filipe Calológio
 */
public abstract class Autocarro extends Thread{
    
    //*   Atributos
    // localizaçao{Cascais, Lisboa, Coimbra, Porto, Braga} & Sentido{y=0=Norte v y=1=Sul}&{x=cidade} 
    protected int x,y; //!!!!! se calhar mudar para float
    // velocidade/deslocamento de normal do autocarro
    protected long velocidade;
    // Capacidade maxima de passageiros do autocarro
    protected int lotacaoMAX; 
    // numero de passageiros no autocarro
    protected int lotacao;
    // estado do autocarro bom ou avariado/bloqueado
    protected boolean estadoOK;
    // informaçoes sobre o autocarro em tempo real
    protected String estadoInfo;
    // lista de avaria/bloqueios de autocarro
    protected String[] avarias = {"Pneu Furado","Acidente!","Sistema de Refrigeraçao Avariado"};
    // limita o numero de Cidades em que o autocarro pode parar/estacionar
    protected String tipo;// expresso: Lisboa<->Porto<->Braga
    // Label para umAutocarro
    protected JLabel label;
    //
    protected JFrame frame;
    protected int[] paragensX;//equivale a{Cascais, Lisboa, Coimbra, Porto, Braga}
    // Guarda o numero de passageiros
    public static int passageiros ; //valor para testes apenas!!!!!!!!remover depois!!!!!!!!!
    protected String cidadeInicial;

    
    //*  Construtor
    public Autocarro(int x, int y, JLabel label){
        this.x = x;
        this.y = y;
        this.lotacao = 0;
        this.estadoOK = true;
        this.label = label;
        this.paragensX = new int[]{0,300,600,900,1200};
        
    }
    
    //*   Metodos
    public String getcidadeInicial(){
    if (x==0){
        return cidadeInicial= "Cascais";}
    
    else if (x==300){
        return cidadeInicial= "Lisboa";
       
    }
     else if (x==600){
          return cidadeInicial= "Coimbra";
     }
     else if (x==900){
         return cidadeInicial= "Porto";
     }
    else if (x==1200){
         return cidadeInicial= "Braga";
          }
    return cidadeInicial;
    }
    
            
    public String getEstado() {
        return (this.estadoOK)? "Bom":"Avariado/Bloqueado";
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    // devolve o numero atual de passageiros no autocarro
    public int getLotacao(){
        return lotacao;
    }
    //Retorna o Sentido do Autocarro
    public String getSentido(){
        if(y<80) {
            return "Sul";
        }
        return "Norte";
    }
    public String getTipo(){
        return tipo;
    }
    public String getEstadoInfo() {
        return estadoInfo;
    }
    public void setEstadoInfo(String estadoInfo) {
        this.estadoInfo = estadoInfo;
    }    
    // adiciona um passageiro no autocarro nao lotado
    public boolean setPassageiros(){// retorna true se adicionar o passageiro
        if(lotacao<lotacaoMAX){
            lotacao++;
            return true;
        }
        return false;
    }
    
    @Override
    public void run(){
        //Equanto houver passageiros os autocarros correm
        entrarPassageiros();
        do{
            // Código para movimentar uma label(autocarro)
            //label.setVisible(true);
            initLabel();
            this.setEstadoInfo("...");
            avancar();
            estacionar();
        }while(passageiros!=0 && this.lotacao!=0);
        this.estadoInfo = "Acabou a viagem, até à proxima! ";
    }
    
    //liga a thread autocarro a uma label
    public void initLabel(){        
        if(this.y<80){
            label.setIcon(new ImageIcon(getClass().getResource("Sul.png")));
        }
        else{
            label.setIcon(new ImageIcon(getClass().getResource("Norte.png")));
        }
    }
    
    //movimentar a label do autocarro num sentido
    public void avancar(){
        this.setEstadoInfo("...");
        do{
            if(this.y<80){
                --this.x;
            }else {
                ++this.x;
            }
            label.setLocation(this.x,this.y);
            try{
                this.sleep(this.velocidade);//Velocidade do Autocarro
            } 
            catch (InterruptedException ex){
                Logger.getLogger(Autocarro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(Arrays.binarySearch(this.paragensX, this.x)<0);
        
        
        
    }
    
    // deve para temporariamente a label autocarro, e aleatoriamente descer e entrar passageiros
    public synchronized  void estacionar() {
        try {
            //parar o autocarro durante 10 segundos
            this.estadoInfo = "Saindo Passageiros...";
            this.sleep(1000);
            //Saida de passageiros
            this.lotacao -= Math.random()*this.lotacao;            
            this.sleep(1000);

            entrarPassageiros();
            
            //Condiçao para a mudança de sentido
            if (this.x == 0 || this.x==1200){
                mudarsentido();
            }
            //volta a velocidade normal do autocarro
            this.sleep(this.velocidade);
        } catch (InterruptedException ex) {
            Logger.getLogger(Autocarro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    // muda o sentido da label configurando tambem icone do autocarro(Norte.png || Sul.png)
    private void mudarsentido() {
        if (this.y == 80){
            this.y = 20;
        }else{
            this.y = 80;
        }
    }
    
    //Entrada de Passageiros
    private void entrarPassageiros() {
        this.estadoInfo = "Passageiros entrando...";
        if (passageiros > 0){
            int n ;
            do{
                n= (int) (Math.random()*(this.lotacaoMAX - this.lotacao));                   
            }while(n>passageiros);
            this.lotacao +=n ;
            passageiros -=n;
        }
    }
     public  void dorme(){
        try {
            this.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Autocarro.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
     //bloquear a thread autocarro(modo wait de uma thread)
    public void bloquear(){try {
        // fazer chamada deste metodo na thread "avaria"
        //Ao bloquear, os passageiros descem do autocarro, incrementando assim no numero total de pessoas a espera de autocarro
        this.estadoOK=false;
        this.estadoInfo = avarias[(int) Math.random()*(avarias.length)];
        this.sleep(1000); //this.sleep(1000);
        this.setEstadoInfo("Passageiros Descendo...");
        passageiros += this.lotacao;
        this.lotacao = 0;//esvazia o autocarro
        this.suspend(); //interrupt()
        } catch (InterruptedException ex) {
            Logger.getLogger(Autocarro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void reabastecer() {
        try {
            this.estadoInfo = "Reabastecendo...";
            this.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Autocarro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void manutencao() {
        try {
            this.estadoInfo = "Fazendo Vistoria no Autocarro...";
            this.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Autocarro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void trocarMototorista() {
        try {
            this.estadoInfo = "Trocando de Motorista...";
            this.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Autocarro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reparar() {
        try {
            this.estadoOK=true;
            this.setEstadoInfo("Reparando Avaria...");
            this.resume();
          this.sleep(1000); //this.sleep(1000);
            /*int posi = autocarroLinkedList.indexOf(this);
            autocarroLinkedList.get(posi).dorme();*/
            
       } catch (InterruptedException ex) {
           Logger.getLogger(Autocarro.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
}  
    
       

