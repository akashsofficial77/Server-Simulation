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
    double rate ;
    ArrayList<Request> requestList;

    public ArrayList<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(ArrayList<Request> requestList) {
        this.requestList = requestList;
    }
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
    

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }
    
    long lStartTime = System.nanoTime();
    long lEndTime;
    int processe=0;
    static int i = 0;

    public long getlStartTime() {
        return lStartTime;
    }

    public void setlStartTime(long lStartTime) {
        this.lStartTime = lStartTime;
    }

    public int getProcesse() {
        return processe;
    }

    public void setProcesse(int processe) {
        this.processe = processe;
    }

    public long getlEndTime() {
        return lEndTime;
    }

    public void setlEndTime(long lEndTime) {
        this.lEndTime = lEndTime;
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
        
        setServerName("Server" + i);
        i++;
       
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
   /* public Server(ArrayList<Request> requestList)
            
    {
        this.requestList=requestList;
        System.out.println("Server queue is created");
        this.q = new LinkedList<Request>();
        
        setServerName("Server" + i);
        i++;
    }*/
    /*public Server(Queue q)
    {
        this.q=q;
    }*/
    
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
                   TimeUnit.MILLISECONDS.sleep((int)processingTime);
               } catch(Exception e){
                   
               }

                try {
                    completed();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        
        public void completed() throws InterruptedException
        {
              synchronized (q)
            {
                while(!q.isEmpty())
                {
                    try{
                   TimeUnit.MILLISECONDS.sleep((int)processingTime-500);
               } catch(Exception e){
                   
               }
             
            
                System.out.println("server is popping request");
                Request newRequest =(Request)q.poll();
                //requestList.add(newRequest);
                newRequest.setrEndTime(getlEndTime());
                processe++;
                
                setlEndTime(System.nanoTime());
                rate = ((getlEndTime()-getlStartTime())/1000000)/processe;
                                     
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
