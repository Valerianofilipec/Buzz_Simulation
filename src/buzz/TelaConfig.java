/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.LinkedList;


public class TelaConfig extends JFrame {
       //Atributos
       private int convencional_check = 0;
       private int minibus_check = 0;
       private int expresso_check = 0;
       private int longdrive_check = 0;
    
       
       private final int lotacaoConvencional = 51;
       private final int lotacaoExpresso     = 69;
       private final int lotacaoLongDrive    = 59;
       private final int lotacaoMiniBus      = 24;
       
       
    //Componentes da GUI
    
    JTextArea autocarroTextArea       = new JTextArea(); //area aonde ficam os dados dos autocarros escolhidos
    JLabel tipoAutocarroLabel         = new JLabel("Tipo de Autocarro: ");    
    JTextField tipoAutocarroTextField = new JTextField(13); // escolher o tipo de autocarro
    JLabel cidadeInicialLabel         = new JLabel("Cidade Inicial: ");  
    JTextField cidadeInicialTextField = new JTextField(10); //  escolher a cidade inicial
    JLabel sentidoLabel               = new JLabel("Sentido: ");
    private static String [] sentido  = {"Norte", "Sul"};
    JComboBox sentidoComboBox         = new JComboBox<>(sentido);
     
    
    JButton botaoAdicionar  = new JButton("Adicionar");
    JButton botaoApagar     = new JButton("Apagar");
    JButton botaoSair       = new JButton("Sair");
    JButton botaoContinuar  = new JButton("Continuar Simulação");
    
    //------------------------//Dados de instancia da classe
    
     //LinkedList para a classe Autocarro
     public static LinkedList<Autocarro> autocarroLinkedList = new LinkedList<Autocarro>();
     public static int total_passageiros = 6;
        
    
    public TelaConfig(){
           JPanel flow1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
           JPanel flow2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
           JPanel gridPanel = new JPanel (new GridLayout(3,7));
           
        autocarroTextArea.setEditable(false); //não se pode editar a area onde ficam os dados dos autocarros
        
        //Adicionar as Label's e Text Field's ao painel
        flow1Panel.add (tipoAutocarroLabel);   
        flow1Panel.add (tipoAutocarroTextField);  
        flow1Panel.add (cidadeInicialLabel);  
        flow1Panel.add (cidadeInicialTextField); 
        flow1Panel.add (sentidoLabel);   
        flow1Panel.add (sentidoComboBox); 
        
        //Adicionar os botões ao painel
        flow2Panel.add(botaoAdicionar);
        flow2Panel.add(botaoApagar);
        flow2Panel.add(botaoSair);
        flow2Panel.add(botaoContinuar);
        
        gridPanel.add (flow1Panel);
        gridPanel.add (flow2Panel);
        add(autocarroTextArea,BorderLayout.CENTER);
        add(gridPanel, BorderLayout.SOUTH);
        
        botaoAdicionar.addActionListener   (event -> adicionarAutocarro());
        botaoApagar.addActionListener      (event -> apagarAutocarro());
        botaoContinuar.addActionListener   (event -> continuar());
        botaoSair.addActionListener        (event -> sairDaAplicacao());
    }
    
    
    //Metodo para verificar se o autocarro está na lista
    private boolean verificaListaAuto(String tipo)
    {
       boolean naLista = false;
       
       for(Autocarro bus : autocarroLinkedList){
            //se a comparação for = 0 é pq o autocarro encontra-se na lista
          if (bus.getTipo().compareToIgnoreCase(tipo)==0){
             naLista = true; 
          }        
       }
        return naLista;
    }
    public int convertNS(String str){
        return (str.equalsIgnoreCase("Norte"))?80:20;
    }
    
    
    //metodo para adicionar autocarro para a area de dados
   public void adicionarAutocarro(){
      //se o tipo de autocarro escolhido for do tipo "Convencional", é adicionado à autocarroLinkedList um novo autocarro convencional
       if("Convencional".equalsIgnoreCase(tipoAutocarroTextField.getText()))
       {
           convencional_check = 1;
          
           String cidadeConvencional =  cidadeInicialTextField.getText();
           
         switch(cidadeConvencional)
         {
           case "Cascais":  
               //A cidade de cascais é a cidade mais a sul logo se a cidade inicial for cascais não se pode escolher o sentido sul
               if(cidadeInicialTextField.getText().equalsIgnoreCase("Cascais") && 
                              sentidoComboBox.getSelectedItem().equals("Sul"))
               {
                   JOptionPane.showMessageDialog(null," Tem que ir para norte");             
               }
               
               else{
                   //int x, int y, JLabel label
                   //String tipoAutocarro, String cidadeInicial, Object sentido 
                    total_passageiros += lotacaoConvencional;
                   autocarroLinkedList.add(new Convencional(0,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                   }
              break;
              
           case "Coimbra":
                total_passageiros += lotacaoConvencional;
                autocarroLinkedList.add(new Convencional(600,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                break;
                
           case "Lisboa":
                       total_passageiros += lotacaoConvencional;
                     autocarroLinkedList.add(new Convencional(300,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                     break;
                     
           case "Porto":
                      total_passageiros += lotacaoConvencional;
                     autocarroLinkedList.add(new Convencional(900,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                     break;
               
           case "Braga":         
               //A cidade de braga é a cidade mais a norte logo se a cidade inicial for braga não se pode escolher o sentido norte
                if(cidadeInicialTextField.getText().equalsIgnoreCase("Braga") && 
                   sentidoComboBox.getSelectedItem().equals("Norte"))
                {
                     JOptionPane.showMessageDialog(null," Tem que ir para sul");
                }  
               else{
                         total_passageiros += lotacaoConvencional;
                        autocarroLinkedList.add(new Convencional(1200,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                    }   
                break;
           default:
                 if(!cidadeInicialTextField.getText().equalsIgnoreCase("Cascais") || !cidadeInicialTextField.getText().equalsIgnoreCase("Lisboa")  
                 || !cidadeInicialTextField.getText().equalsIgnoreCase("Coimbra") || !cidadeInicialTextField.getText().equalsIgnoreCase("Porto")
                 ||!cidadeInicialTextField.getText().equalsIgnoreCase("Braga"))
                {
                  JOptionPane.showMessageDialog(null," Escolha uma cidade inicial ");             
                }   
         }
   }
       else if("Expresso".equalsIgnoreCase(tipoAutocarroTextField.getText()))
       {
          expresso_check = 1;
         
          //O autocarro expresso não passa por todas as cidades
          
          String cidadeExpresso = cidadeInicialTextField.getText();
          switch(cidadeExpresso)
          {
             case "Cascais":
                  JOptionPane.showMessageDialog(null," O autocarro expresso não passa pela cidade de cascais");
                  break;
             case "Lisboa":
                 //Nos autocarros expressos, lisboa é a cidade mais a sul 
                 if(cidadeInicialTextField.getText().equalsIgnoreCase("lisboa") && 
                       sentidoComboBox.getSelectedItem().equals("Sul"))
                 {
              JOptionPane.showMessageDialog(null," Tem que ir para norte");             
                 }    
                 else {
                      total_passageiros += lotacaoExpresso;
                     autocarroLinkedList.add(new Expresso(300,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
          }                    
                  break;
                   
             case "Coimbra":
                  JOptionPane.showMessageDialog(null," O autocarro expresso não passa pela cidade de coimbra");
                  break;
             case "Porto":
                  total_passageiros += lotacaoExpresso;
             autocarroLinkedList.add
                        (new Expresso(900,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel())); 
                    break;
             case "Braga":
                    if(cidadeInicialTextField.getText().equalsIgnoreCase("braga") && 
                               sentidoComboBox.getSelectedItem().equals("Norte"))
                    {
                     JOptionPane.showMessageDialog(null," Tem que ir para sul");
                    } 
                    else{
                          total_passageiros += lotacaoExpresso;
                         autocarroLinkedList.add
                        (new Expresso(1200,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel())); }
                    break;
             default:
                 if(!cidadeInicialTextField.getText().equalsIgnoreCase("cascais") || !cidadeInicialTextField.getText().equalsIgnoreCase("lisboa")  
                 || !cidadeInicialTextField.getText().equalsIgnoreCase("coimbra") || !cidadeInicialTextField.getText().equalsIgnoreCase("porto")
                 ||!cidadeInicialTextField.getText().equalsIgnoreCase("braga"))
                {
                   JOptionPane.showMessageDialog(null," Escolha uma cidade");
                } else
                      throw new IllegalArgumentException("Excepção");
              
           }
       }
       
       else if("LongDrive".equalsIgnoreCase(tipoAutocarroTextField.getText()))
       {
            longdrive_check=1;   
            
            String cidadeLongDrive = cidadeInicialTextField.getText();
               
         switch(cidadeLongDrive)
         {
            case "Cascais":   
               if(cidadeInicialTextField.getText().equalsIgnoreCase("Cascais") && 
                  sentidoComboBox.getSelectedItem().equals("Sul"))
               {
                  JOptionPane.showMessageDialog(null," Tem que ir para norte");             
               }
                  else
                  {
                      total_passageiros += lotacaoLongDrive ;
                     autocarroLinkedList.add(new LongDrive(0,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                  }
            break;
            
            case "Coimbra":
                     total_passageiros += lotacaoLongDrive ;
                     autocarroLinkedList.add(new LongDrive(600,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                     
             break;             
            case "Lisboa":
                     total_passageiros += lotacaoLongDrive ;
                     autocarroLinkedList.add(new LongDrive(300,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                  break;
                  
            case "Porto":
                     total_passageiros += lotacaoLongDrive ;
                     autocarroLinkedList.add(new LongDrive(900,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                  break;
              
            case "Braga":
                 if(cidadeInicialTextField.getText().equalsIgnoreCase("Braga") &&
                               sentidoComboBox.getSelectedItem().equals("Norte"))
                 {
              JOptionPane.showMessageDialog(null," Tem que ir para sul");
                 }
               else
                  {
                      total_passageiros += lotacaoLongDrive ;
                     autocarroLinkedList.add(new LongDrive(1200,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                  }
            break;
            default:
                 if(!cidadeInicialTextField.getText().equalsIgnoreCase("Cascais") || !cidadeInicialTextField.getText().equalsIgnoreCase("Lisboa")  
                 || !cidadeInicialTextField.getText().equalsIgnoreCase("Coimbra") || !cidadeInicialTextField.getText().equalsIgnoreCase("Porto")
                 ||!cidadeInicialTextField.getText().equalsIgnoreCase("Braga"))
                {
                   JOptionPane.showMessageDialog(null," Escolha uma cidade");
                } else
                      throw new IllegalArgumentException("Excepção");
         }       
     }
       
       else if("MiniBus".equalsIgnoreCase(tipoAutocarroTextField.getText()))
        {
            minibus_check = 1;

            String cidadeMiniBus = cidadeInicialTextField.getText();
            
          switch(cidadeMiniBus){
              case "Cascais":
              //A cidade de cascais é a cidade mais a sul logo se a cidade inicial for cascais não se pode escolher o sentido sul
              if(cidadeInicialTextField.getText().equalsIgnoreCase("Cascais") && 
                              sentidoComboBox.getSelectedItem().equals("Sul"))
              {
                JOptionPane.showMessageDialog(null," Tem que ir para norte");             
              }
              else {
                   total_passageiros += lotacaoMiniBus ;
                   autocarroLinkedList.add(new MiniBus(0,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                   }
               break;
               
              case "Coimbra":
                   total_passageiros += lotacaoMiniBus ;
                   autocarroLinkedList.add(new MiniBus(600,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                break;
                
              case "Lisboa":
                   total_passageiros += lotacaoMiniBus ;
                   autocarroLinkedList.add(new MiniBus(300,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                 break;
                 
              case "Porto":
                   total_passageiros += lotacaoMiniBus ;
                   autocarroLinkedList.add(new MiniBus(900,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel()));
                 break;
              case "Braga":       
                   //A cidade de braga é a cidade mais a norte logo se a cidade inicial for braga não se pode escolher o sentido norte
                   if(cidadeInicialTextField.getText().equalsIgnoreCase("Braga") && 
                   sentidoComboBox.getSelectedItem().equals("Norte"))
                   {
                    JOptionPane.showMessageDialog(null," Tem que ir para sul");
                   }
                  else{
                       total_passageiros += lotacaoMiniBus ;
                  autocarroLinkedList.add(new MiniBus(1200,convertNS(sentidoComboBox.getSelectedItem().toString()),new javax.swing.JLabel())); 
                      }
                break;
              default:
                 if(!cidadeInicialTextField.getText().equalsIgnoreCase("Cascais") || !cidadeInicialTextField.getText().equalsIgnoreCase("Lisboa")  
                 || !cidadeInicialTextField.getText().equalsIgnoreCase("Coimbra") || !cidadeInicialTextField.getText().equalsIgnoreCase("Porto")
                 ||!cidadeInicialTextField.getText().equalsIgnoreCase("Braga"))
                {
                   JOptionPane.showMessageDialog(null," Escolha uma cidade");
                } else
                      throw new IllegalArgumentException("Excepção");  
      
        }
     }
            autocarroTextArea.setText("");
          for(Autocarro bus : autocarroLinkedList) 
          {
             autocarroTextArea.append(bus + "\n");   
          }
          
           MostrarTodos();
          /* tipoAutocarroTextField.setText("");
           cidadeInicialTextField.setText("");*/     
    }
          
      //Metodo para mostrar todos os autocarros na area de dados dos autocarros
    private void MostrarTodos(){
       autocarroTextArea.setText("");
       for(Autocarro bus : autocarroLinkedList) 
       {
          autocarroTextArea.append(" Tipo de autocarro:   " + bus.getTipo() + "\tCidade Inicial:   " + bus.getcidadeInicial()+ "\tSentido:   " + bus.getSentido() + "\n" );        
       }
        
    }
    
    //metodo para apagar autocarros da lista
      private void apagarAutocarro()
      {      //se a lista estiver vazia, mostra uma mensagem a dizer que não se pode apagar
             if (autocarroLinkedList.isEmpty()) 
           {
            JOptionPane.showMessageDialog(null, "Não pode apagar, Lista vazia!!! ");
           }    
            // se o autocarro não se encontrar na lista mostra uma mensagem
           else if(verificaListaAuto(tipoAutocarroTextField.getText()) == false)
           {
           JOptionPane.showMessageDialog(null, "Erro: Este tipo de autocarro não se encontra na lista ");
           }   
       else
       {  //Percorre a lista de autocarros e se o autocarro estiver na lista vai remove-lo
          for(int s=0; s< autocarroLinkedList.size(); s++)
          {
             String autoTipo = autocarroLinkedList.get(s).getTipo();
             if (autoTipo.compareToIgnoreCase(tipoAutocarroTextField.getText()) == 0 )
             {   
               //vai decrementar o atributo total_passageiros consoante o tipo de autocarro que for eliminado
               switch(autoTipo)
               {
                   case "Convencional":
                       total_passageiros -= lotacaoConvencional;
                       break;
                   case "Expresso":
                       total_passageiros -= lotacaoExpresso;
                       break;
                   case "LongDrive":
                        total_passageiros -= lotacaoLongDrive;
                        break;
                   case "MiniBus":
                         total_passageiros -= lotacaoMiniBus;
                         break;
               }
               autocarroLinkedList.remove(s);
             }
            
          } 
        }
           MostrarTodos();
           //nameTextField.setText ("");
           //idTextField.setText(""); 
       }
    
      //metodo para sair
      private void sairDaAplicacao()
      {
         System.exit (0); // o zero significa que está tudo bem
      }
      
      
      //Metodo para continuar a simulacao
      private void continuar()
      {  
          //Tem que haver um autocarro de cada tipo
         //String autocarro = ( tipoAutocarroTextField.getText());

         if (convencional_check == 0  || minibus_check == 0 || expresso_check == 0 || longdrive_check == 0)
            {
            JOptionPane.showMessageDialog(null," Tem que escolher pelo menos um autocarro de cada tipo");
           
           }

                 //A frota de autocarros é de 4 a 10    
            else if(autocarroLinkedList.size()>=11)
            {
                JOptionPane.showMessageDialog(null, "Atenção, não pode haver mais de 10 autocarros!!! ");
            }
            else if(autocarroLinkedList.size()<=3)
            {
               JOptionPane.showMessageDialog(null, "Atenção, não pode haver menos de 4 autocarros!!! ");
            }
       else{
               
            JOptionPane.showMessageDialog(null, "O Total de passageiros esperados são: " + total_passageiros);
             fechar(); 
             new SimulationFrame().setVisible(true);
            }    
       } 

     //metodo para fechar a janela inicial quando é aberta a janela de configuração
    
     public void  fechar()
     {
         WindowEvent  winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
         Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
     }
      
  public static void main(String []args){
        TelaConfig config = new TelaConfig();
        config.setVisible(true);
        config.setSize(700, 700);
        config.setLocation(350,50);
        config.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        config.setTitle("Configuração");       
      }
    }

 