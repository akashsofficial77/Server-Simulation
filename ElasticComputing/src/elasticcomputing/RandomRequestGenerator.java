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

/**
 *
 * @author Aditya
 */
public class RandomRequestGenerator {
    
        
    class RunnableImp implements Runnable{
        Queue q;
        
        public RunnableImp(Queue q)
        {
            this.q=q;
        }
        
        public void run()
        {
            //while(!q.isEmpty())
             System.out.println(Thread.currentThread().getName());
             generateRandomRequest(q);
             //System.out.println(Thread.currentThread().getName()+":"+q.poll());
            
                
        }
    }
    
    
    RequestFactory requestFactory;
    Random rand;
    Queue<Request> queue;
  
    public static void main(String args[]){
        RandomRequestGenerator r = new RandomRequestGenerator();
        r.initialize();
       // r.generateRandomRequest();
       
    }
  
    public  void generateRandomRequest(Queue queue){
    int randInt2 = 0; 
    int randInt = 0;
    for (int i = 0 ; i < 100 ; i ++){
    try{
   
    randInt = rand.nextInt(5);
    TimeUnit.SECONDS.sleep(randInt2);
    } catch(Exception e){
        
    }
    Request req1 = requestFactory.generateRequest();
    req1.setName(Integer.toString(i));
    req1.setProcessingTime(randInt);
    queue.add(req1);
    Dispatcher  d = new Dispatcher((Request)queue.poll());
    System.out.println("Request name is "+req1.getName()+ " Request processing time is " + req1.getProcessingTime());
    }
    }

    private void initialize() {
       requestFactory  =  new RequestFactory();
       rand = new Random();
       queue = new LinkedList<Request>();
       Runnable r1 = new RunnableImp(queue);
       Thread mainThread = new Thread(r1);
       mainThread.start();
    }
    
}

