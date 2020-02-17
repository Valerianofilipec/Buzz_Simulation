/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzz;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Valeriano Filipe Calológio
 */
public class ThreadAvaria extends Thread {
    
    @Override
    public void run(){
        int n;
      
        try {
            Thread.sleep(20000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadAvaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        do{
            n = (int) (Math.random() * 100);
            if(n < TelaConfig.autocarroLinkedList.size()){
                TelaConfig.autocarroLinkedList.get(n).bloquear();   
            }
        }while(true);
    }
}
