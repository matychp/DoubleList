package doublelist;

/**
 *  Una lista generica. Empleamos generics para control de homogeneidad. Tambien
 *  se incluye la implementacion del patron Iterator.
 *  @author  Ing. Valerio Frittelli.
 *  @version Agosto de 2013.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
public class DoubleList <E extends Comparable> implements Iterable<E>
{
      private Node <E> frente, fondo;
      private int cantidad;
      
      /** 
       * Constructor por defecto.
       */
      public DoubleList ()
      {
          frente = null;
          fondo = null;
          cantidad = 0;
      }
      
      /**
       *  Inserta un objeto al principio de la lista. La insercion se hara solo si el parametro recibido 
       *  no es null.
       *  @param x el objeto a almacenar en la lista.
       */
      public void addFirst(E x)
      {
            if ( x == null ) { return; }
            
            Node <E> nn = new Node <> (x, frente, null);
            if (frente != null) { frente.setPrevious(nn); }
            frente = nn;
            
            if (fondo == null) { fondo = nn; }
            
            cantidad++;
      }
      
      /**
       *  Inserta un objeto al final de la lista. La insercion se hara solo si el parametro recibido 
       *  no es null.
       *  @param x el objeto a almacenar en la lista.
       */
      public void addLast(E x)
      {
            if ( x  == null ) { return; }
            
            Node < E > nn = new Node <> (x, null, fondo);
            if (fondo != null) { fondo.setNext(nn); }
            fondo = nn;
            
            if(frente == null) { frente = nn; }
            
            cantidad++;
      } 
      
      /**
       *  Remueve todos los elementos de la lista.
       */
      public void clear( )
      {
         frente = null;
         fondo = null;
         cantidad = 0;
      }
      
      /**
       *  Determina si en la lista existe un elemento que coincida con x. 
       *  Usamos compareTo() para realizar las comparaciones (aunque podria 
       *  usarse equals()).
       *  @return true si x esta en la lista - false si x no esta o si x es null.
       *  @param x el objeto a buscar.
       */
      public boolean contains( E x )
      {
          Node <E> p = frente;
          while ( p != null && x.compareTo( p.getInfo() ) != 0 ) { p = p.getNext(); }    
          return ( p != null );
      }
      
      /**
       *  Retorna (pero sin removerlo) el objeto ubicado en la posicion "index" 
       *  de la lista, tomando como convencion que el primer nodo esta en la 
       *  posicion cero.
       *  Si el index a buscar está despues de la mitad de la lista busca desde el fondo para atras.
       *  Si está antes de la mitad de la lista, busca desde el frente hacia adelante.
       *  @param index el numero de orden del objeto a acceder.
       *  @return una referencia objeto ubicado en la posicion "index".
       *  @throws IndexOutOfBoundsException si index esta fuera de rango.
       */
      public E get( int index )
      {
          if( index < 0 || index >= size() ) { throw new IndexOutOfBoundsException( "Indice fuera del rango" ); }
         
          if(index >= (cantidad / 2)){
            Node <E> p = fondo;
            for( int i = cantidad-1; i > index; i-- ){  p = p.getPrevious(); }
            return p.getInfo();
          }
          else{
            Node <E> n = frente;
            for( int i = 0; i < index; i++ ){  n = n.getNext(); }
            return n.getInfo();
          }
          
      }
      
      /**
       *  Retorna (pero sin removerlo) el objeto ubicado al principio de la lista. 
       *  @return una referencia al primer elemento de la lista.
       *  @throws NoSuchElementException si la lista estaba vacia.
       */
      public E getFirst()
      {
         if (frente == null) { throw new NoSuchElementException("Error: la lista esta vacia..."); }
         return frente.getInfo();
      }
      
      /**
       *  Retorna (pero sin removerlo) el objeto ubicado al final de la lista. 
       *  @return una referencia al ultimo elemento de la lista.
       *  @throws NoSuchElementException si la lista estaba vacia.
       */
      public E getLast()
      {
         if (fondo == null) { throw new NoSuchElementException("Error: la lista esta vacia..."); }
         return fondo.getInfo();
      }
      
      /**
       * Retorna el indice de la primera ocurrencia del objeto x en la lista, o -1 si x 
       * no esta en la lista o si x es null o si x no es compatible con el contenido de 
       * la lista.
       * @param x el objeto a buscar en la lista.
       * @return el indice de la primera ocurrencia de x en la lista, o -1 si x no existe.
       */
      public int indexOf( E x )
      {          
            int c = 0;
            for ( Node <E> p = frente; p != null; p = p.getNext() )
            {
                if( x.compareTo( p.getInfo() ) == 0 ) { return c; }
                c++;
            }
            return -1;
      }
            
      /**
       * Retorna true si la lista esta vacia.
       * @return true si la lista est� vacia - false en caso contrario.
       */
      public boolean isEmpty()
      {
         return (frente == null && fondo == null);    
      }
      
      /**
       * Retorna un iterado
       * @return r para la lista.
       */
      @Override
      public Iterator<E> iterator()
      {
         return new SimpleListIterator();
      }
      
      /**
       *  Remueve el primer nodo de la lista que contenga al objeto x si el mismo se 
       *  encontraba en ella. Retorna true si la eliminacion tuvo exito, o false en 
       *  caso contrario (x no estaba en la lista, x era null, o x no era compatible 
       *  con el contenido de la lista).
       *  @param x el objeto a remover de la lista.
       *  @return true si la eliminacion pudo hacerse, false en caso contrario.
       */
      public boolean remove( E x )
      {
          Node <E> p = frente, q = null;
          while( p != null && x.compareTo( p.getInfo() ) != 0  ) 
          {
              q = p;
              p = p.getNext();
          }
          
          if( p == null ) { return false; }
          if( q == null ) { frente = p.getNext(); }
          else { q.setNext( p.getNext() ); }
          
          cantidad--;
          return true;
      }
      
      /**
       *  Remueve el objeto x en la posicion index de la lista. Los objetos 
       *  que se encontraban a su derecha en la lista se desplazan hacia la 
       *  izquierda una posicion. Se toma como convencion que el primer 
       *  nodo esta en la posicion cero. 
       *  @param index el numero de orden del objeto a acceder.
       *  @return el objeto que se encontraba en la posicion index.
       *  @throws IndexOutOfBoundsException si index esta fuera de rango (index <0 || index >= size() ).
       */
      public E remove( int index )
      {
          if( index < 0 || index >= size() ) { throw new IndexOutOfBoundsException( "Indice fuera del rango" ); }

          Node <E> p = frente, q = null;
          for( int i = 0; i < index; i++ ) 
          {
              q = p;
              p = p.getNext();
          }
          
          E x = p.getInfo();
          if( q == null ) { frente = p.getNext(); }
          else { q.setNext( p.getNext() ); }
          
          cantidad--;
          return x; 
      }
      
      /**
       *  Retorna (y remueve) el objeto ubicado al principio de la lista. 
       *  @return el primer elemento de la lista.
       *  @throws NoSuchElementException si la lista estaba vacia.
       */
      public E removeFirst()
      {
         if (frente == null) { throw new NoSuchElementException("Error: la lista esta vacia..."); }
         
         E x = frente.getInfo();
         frente.getNext().setPrevious(null);
         frente = frente.getNext();
         
         cantidad--;
         return x;
      }
      
      /**
       *  Retorna (y remueve) el objeto ubicado al final de la lista. 
       *  @return el ultimo elemento de la lista.
       *  @throws NoSuchElementException si la lista estaba vacia.
       */
      public E removeLast()
      {
         if (fondo == null) { throw new NoSuchElementException("Error: la lista esta vacia..."); }
         
         E x = fondo.getInfo();
         fondo.getPrevious().setNext(null);
         fondo = fondo.getPrevious();
         
         cantidad--;
         return x;
      }
     

      /**
       *  Remueve el primer nodo de la lista que contenga al objeto x si el mismo se 
       *  encontraba en ella. Equivale a invocar a remove(x). Retorna true si la 
       *  eliminacion tuvo exito, o false en caso contrario (x no estaba en la lista, 
       *  x era null, o x no era compatible con el contenido de la lista).
       *  @param x el objeto a remover de la lista.
       *  @return true si la eliminacion pudo hacerse, false en caso contrario.
       */
      public boolean removeFirstOccurrence( E x )
      {
          return remove(x);
      }
      
      /**
       * Busca un objeto x en la lista, y en caso de encontrarlo retorna 
       * una referencia al objeto que ESTA EN LA LISTA. Retorna null si x 
       * no esta en la lista o si x es null o si x no es compatible con el 
       * contenido de la lista.
       * @param x el objeto a buscar en la lista.
       * @return una referencia al objeto encontrado en la lista.
       */
      public E search (E x)
      {
            for ( Node <E> p = frente; p != null; p = p.getNext() )
            {
                if( x.compareTo( p.getInfo() ) == 0 ) { return p.getInfo(); }
            }
            return null;
      }
      
      /**
       *  Reemplaza el objeto ubicado en la posicion "index" de la lista, por 
       *  el objeto x que entra como parametro. Se toma como convencion que el
       *  primer nodo esta en la posicion cero. El metodo retorna el objeto que
       *  se encontraba previamente en la posicion index.
       *  @param index el numero de orden del objeto a acceder.
       *  @param x el objeto a almacenar en la posicion index.
       *  @return el objeto previamente ubicado en la posicion index.
       *  @throws IndexOutOfBoundsException si index esta fuera de rango.
       *  @throws ClassCastException si x no es compatible con el contenido de la lista.
       */
      public E set( int index, E x )
      {
          if( index < 0 || index >= size() ) { throw new NoSuchElementException( "Indice fuera del rango" ); }
         
          Node <E> p = frente;
          for( int i = 0; i < index; i++ ) { p = p.getNext(); }
          
          E ant = p.getInfo();
          p.setInfo( x );
          return ant;
      }
      
      /**
       *  Retorna la cantidad de objetos que contiene la lista.
       *  @return la longitud de la lista.
       */
      public int size()
      {
          return cantidad;
      }
      
      /**
       *  Redefine el metodo toString heredado desde Object.
       *  @return el contenido de la lista convertido a String.
       */
      @Override
      public String toString()
      {
          Node <E> p = frente;
          String res = "[";
          while( p != null )
          {
             res = res + p.toString();
             if ( p.getNext() != null ) { res = res + ", "; }
             p = p.getNext();
          }
          res = res + "]";
          return res;
      }
    
      /**
       * Clase interna para implementar el iterador.
       */
      private class SimpleListIterator implements Iterator <E>
      {

          private Node <E> actual;          // patron Iterator: direccion del nodo que toca procesar.
          private Node <E> previo;          // direccion del nodo anterior al actual.
          private boolean  next_invocado;   // true: next fue invocado (usado por remove()...)
        
          public SimpleListIterator()
          {
             actual = null;
             previo = null;
             next_invocado = false;
          }
        
         /**
          * Indica si queda algun objeto en el recorrido del iterador.          *
          * @return true si queda algun objeto en el recorrido - false si no
          * quedan objetos.
          */
         @Override
         public boolean hasNext()
         {
              if (frente == null) { return false; }
              if (actual != null && actual.getNext() == null) { return false; }
              return true;
         }

         /**
          * Retorna el siguiente objeto en el recorrido del iterador.          *
          * @return el siguiente objeto en el recorrido.
          * @throws NoSuchElementException si la lista esta vacia o en la lista
          * no quedan elementos por recorrer.
          */
         @Override
         public E next()
         {
            if (!hasNext()) { throw new NoSuchElementException("No quedan elementos por recorrer"); }

            if (actual == null) { actual = frente; } 
            else
            {
                previo = actual;
                actual = actual.getNext();
            }
            next_invocado = true;
            
            return actual.getInfo();
         }

         /**
          * Elimina el ultimo elemento que retorno el iterador. Debe invocarse primero a next(). El iterador 
          * queda posicionado en el elemento anterior al eliminado. 
          * @throws IllegalStateException si se invoca a remove() sin haber invocado a next(), o si 
          * remove fue invocado mas de una vez luego de una invocacion a next().
          */
         @Override
         public void remove()
         {
            if(!next_invocado) { throw new IllegalStateException("Debe invocar a next() antes de remove()..."); }
             
            if (previo == null)
            {
                frente = actual.getNext();
            }
            else
            {
                previo.setNext(actual.getNext());
            }

            actual = previo;
            next_invocado = false;
            cantidad--;
         }
      }
}
