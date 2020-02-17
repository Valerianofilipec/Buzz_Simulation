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
public class Convencional extends Autocarro {

    public Convencional(int x, int y, JLabel label) {
        super(x, y,label);
        lotacaoMAX = 51;
        velocidade = 50;
        tipo = "Convencional";
    }
    
}
