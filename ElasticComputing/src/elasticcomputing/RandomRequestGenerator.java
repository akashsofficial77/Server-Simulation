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
    int requestRate=0;
    int requestCount = 0;
    Dispatcher d;
    Queue q;
    int processingTime;
    private Integer reqPerServer;
   // long reqtime = 0;
    
    
    public RandomRequestGenerator(int reqRate , int processingTime, int reqPerServer){  
       this.requestRate = reqRate;
       this.processingTime = processingTime;
       this.reqPerServer = reqPerServer;
       initialize();
}

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public void setReqPerServer(Integer reqPerServer) {
      this.reqPerServer = reqPerServer;
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
            
            while(true){
            try{
          
               System.out.println(Thread.currentThread().getName());
               try{
                  // TimeUnit.SECONDS.sleep((long) 1/requestRate);
               } catch(Exception e){
                   
               }
               System.out.println("Request generating thread is waiting for " + 1/requestRate + "seconds ");
               generateRandomRequest(q);
               
               System.out.println("Request "+requestCount);
               
               requestCount++;
            }
            catch(Exception e){
            
            }
            }
                
        }
    }
    }
    
    public int getRequestRate(){
        return requestRate;
    }
    
    public void  setRequestRate(int requestRate){
        this.requestRate = requestRate;
    }


  
    public  void generateRandomRequest(Queue q){
        try {
            int randInt2 = 0;
            int randInt = 0;
            q.wait(1);
            // while(q.size()==50){
            // System.out.println("Queue is full");
            // }
            /* try{
            // randInt = rand.nextInt(5);
            TimeUnit.SECONDS.sleep((long) 0.5);
            } catch(Exception e){
            }*/
            Request req1 = requestFactory.generateRequest();
            req1.setName("Request");
            // req1.setProcessingTime(processingTime);
            q.add(req1);
            System.out.println(q.size()+" is the size of main queue ");
            // queue.setQueue(q);
            q.notifyAll();
            System.out.println("Request generating thread has notified all");
            //Dispatcher  d = new Dispatcher((Request)queue.poll());
            System.out.println("Request Generator Thread "+"   "+req1.getName()+ " T " + req1.getProcessingTime());
        } catch (InterruptedException ex) {
            Logger.getLogger(RandomRequestGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public void initialize() {
       requestFactory  =  new RequestFactory();
       rand = new Random();
       q = new LinkedList<Request>();
       Runnable r1 = new RunnableImp(q);
       mainThread = new Thread(r1); 
       mainThread.setPriority(10);
       mainThread.start();
       Dispatcher d = new Dispatcher(q,reqPerServer, processingTime);
    }
    
}

