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
//Tuviejaentanga
        DoubleList<Integer> dl = new DoubleList<>();
        
        //Insertando elementos al comienzo y al final. Funciona
        dl.addFirst(3);
        dl.addFirst(2);
        dl.addFirst(1);
        dl.addFirst(0);
        dl.addLast(4);
        dl.addLast(5);
        dl.addLast(6);
        dl.addLast(7);
        
        //Remueve y Muestra el primero o el ultimo de la lista. Funciona
        System.out.println(dl.removeFirst());
        System.out.println(dl.removeLast());
        
        //Devolviendo pero sin eliminar elementos en dicha posicion en la lista. Funciona
        System.out.println(dl.get(2));
        System.out.println(dl.get(5));
        
        //Primero se muestra por pantalla el valor sobreplantado, y luego con el get() 
        //se muestra el nuevo valor que se insertó anteriormente como segundo parametro en el set. Funciona
        System.out.println(dl.set(2, 10));
        System.out.println(dl.get(2));
        
        System.out.println(dl.set(5, 20));
        System.out.println(dl.get(5));
    }
    
}
