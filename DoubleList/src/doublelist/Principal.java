/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doublelist;

/**
 *
 * @author matychp
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DoubleList<Integer> dl = new DoubleList<>();
        
        dl.addFirst(1);
        dl.addFirst(2);
        dl.addLast(3);
        dl.addLast(4);
    }
    
}
