/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzz;

import javax.swing.JLabel;

/**
 *
 * @author Valeriano Filipe Calológio
 */
public class LongDrive extends Autocarro{
    
    public LongDrive(int x, int y,JLabel label) {
        super(x, y,label);
        lotacaoMAX = 59;
        velocidade = 60;
        tipo = "Long-Drive";
    }
    
}
