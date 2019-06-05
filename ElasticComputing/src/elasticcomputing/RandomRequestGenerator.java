/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aditya
 */
public class RandomRequestGenerator {
        
    
    RequestFactory requestFactory;
    Random rand;
   // MainQueue queue;
    Thread mainThread;
    
  
    Dispatcher d;
    Queue q;
    
    
    public RandomRequestGenerator(){  
       initialize();
}
    
    class RunnableImp implements Runnable{
        Queue q;
        
        public RunnableImp(Queue q)
        {
            this.q=q;
        }
        
        public void run()
        {    synchronized(q){
            //while(!q.isEmpty())
            int rc=0;
            while (true){
            try{
               System.out.println(Thread.currentThread().getName());
               
               generateRandomRequest(q);
               System.out.println("Request "+rc);
               rc++;
            }
            catch(Exception e){
            
            }
                
      
             //System.out.println(Thread.currentThread().getName()+":"+q.poll());
            }
                
        }
    }
    }

  
   /* public static void main(String args[]){
           
          
           
        //r.initialize();
       // r.generateRandomRequest();
       
    }*/
  
    public  void generateRandomRequest(Queue q){
    int randInt2 = 0; 
    int randInt = 0;
    
    while(q.size()==50){
      System.out.println("Queue is full");
                try {
                    q.wait();
                    System.out.println("Request generating thread is waiting");
                } catch (InterruptedException ex) {
                    Logger.getLogger(RandomRequestGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    try{
    randInt = rand.nextInt(5);
    TimeUnit.SECONDS.sleep((long) 0.5);
    } catch(Exception e){    
    }
    Request req1 = requestFactory.generateRequest();
    req1.setName("Request");
    req1.setProcessingTime(randInt);
    q.add(req1);
    System.out.println(q.size()+" is the size of main queue ");
   // queue.setQueue(q);
    q.notifyAll();
    System.out.println("Request generating thread has notified all");
    //Dispatcher  d = new Dispatcher((Request)queue.poll());
    System.out.println("Request Generator Thread "+"   "+req1.getName()+ " T " + req1.getProcessingTime());
    
    }

    private void initialize() {
       requestFactory  =  new RequestFactory();
      
       rand = new Random();
       q = new LinkedList<Request>();
       Runnable r1 = new RunnableImp(q);
       mainThread = new Thread(r1); 
       mainThread.setPriority(10);
       mainThread.start();
       Dispatcher d = new Dispatcher(q);
    }
    
}

