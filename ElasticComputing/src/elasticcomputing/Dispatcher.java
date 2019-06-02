/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;
import java.util.*;
/**
 *
 * @author tinyteddybear
 */
public class Dispatcher {
    
    class RunnableImp implements Runnable{
        Queue q;
        
        public RunnableImp(Queue q)
        {
            this.q=q;
        }
        
        public void run()
        {
            while(!q.isEmpty())
            {
                System.out.println(Thread.currentThread().getName()+":"+q.poll());
            }
                
        }
    }
    public static void main (String args[])
    {
        
    }
}
