/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;
import java.util.*;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author tinyteddybear
 */
public class Server {
    String serverName;
    private Queue q;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
   
    public Queue getQ() {
        return q;
    }

    public void setQ(Queue q) {
        this.q = q;
    }
    
    
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
            while(!q.isEmpty())
            {
                try{
                TimeUnit.SECONDS.sleep(5);
                } catch(Exception e){

                }
                completed();
            }

        }
        
        public void completed()
        {
            synchronized (q)
            {
                System.out.println(Thread.currentThread().getName()+":"+q.poll());
                q.notifyAll();
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
