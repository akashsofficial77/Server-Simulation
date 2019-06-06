/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author tinyteddybear
 */
public class Server {
    String serverName;
    private Queue q;
    int remove=0;
    int processingTime;

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }
    

    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }
    
    public Server(){
        System.out.println("Server queue is created");
        this.q = new LinkedList<Request>();
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
   
    public Queue getQ() {
        return q;
    }

  /*  public void setQ(Queue q) {
        this.q = q;
    }
    */
    
    public Server(Queue q)
    {
        this.q=q;
    }
    
    class RunnableImp implements Runnable{
        Queue q;

        public RunnableImp(Queue q)
        {
            this.q=q;
        }

        public void run()
        {
            while(remove ==0)
            {
               try{
                   TimeUnit.SECONDS.sleep((int)processingTime);
               } catch(Exception e){
                   
               }
                completed();
            }

        }
        
        public void completed()
        {
            synchronized (q)
            {
                while(!q.isEmpty())
                {
                System.out.println("server is poping request");
                   
                System.out.println(Thread.currentThread().getName()+":"+q.poll());
                q.notifyAll();
                }
                
            }
        }
        
        
    }
    public void initialize()
        {
            Runnable r = new RunnableImp(q);
            Thread t1 = new Thread (r);
            t1.start();
            
        }
    
}
