/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author tinyteddybear
 */
public class Dispatcher {

    Request req;
    MainQueue queue;
 
    

    Dispatcher(Queue q) {
        initialize(q);
    }

    private void initialize( Queue q) {
        System.out.println("dispatcher running");
        Runnable r = new RunnableImp(q);
        Thread t1 = new Thread(r);
        t1.start();
    }
   
    
    class RunnableImp implements Runnable{
        Queue q;
        
        public RunnableImp(Queue q)
        {
            this.q=q;
        }
        
        public void run()
        {  
            System.out.println("Dispatcher in run");
            System.out.println(q.size());
            while(true){
                synchronized(q){
                if(!q.isEmpty())  {
                System.out.println(Thread.currentThread().getName()+":"+q.poll());
                q.notifyAll();
            } else {
                    try {
                        q.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            }
                
               
          
                
        }
    }
    public static void main (String args[])
    {
        
    }
}
