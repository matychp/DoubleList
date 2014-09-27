package doublelist;

public class Node < E extends Comparable >
{
   private E info;
   private Node <E> next, previous;
   
   public Node ( )
   {
   }
   
   public Node (E e, Node <E> n, Node <E> p)
   {
     info = e;
     next = n;
     previous = p;
   }
   
   public Node <E> getNext()
   {
     return next;
   }
   
   public void setNext(Node <E> n)
   {
     next = n;
   }
   
   public E getInfo()
   {
     return info;
   }
   
   public void setInfo(E p)
   {
     info = p;
   }
   
   public Node <E> getPrevious()
   {
       return previous;
   }

   public void setPrevious(Node <E> p)
   {
       previous = p;
   }
   
   @Override
   public String toString()
   {
     return info.toString();   
   }
}

