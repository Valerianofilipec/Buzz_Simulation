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

// Autocarro Expresso apenas para em Lisboa,Porto e Braga
public class Expresso extends Autocarro{
    
    @SuppressWarnings("empty-statement")
    public Expresso(int x, int y,JLabel label) {
        super(x, y,label);
        lotacaoMAX = 69;
        velocidade = 50;
        tipo = "Expresso";
        this.paragensX = new int[]{300,900,1200};
    }
    
}
