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
                System.out.println(Thread.currentThread().getName()+":"+q.poll());
            }

        }
        
        public void initialize()
        {
            
        }
    }
    
}
