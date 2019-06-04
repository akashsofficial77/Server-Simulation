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
    ServerList queue;
    ServerList serverList = new ServerList();
    ArrayList serverArray;
    ServerFactory serverFactory;
    Queue q;
    
    Dispatcher(Queue q) {
        this.q=q;
        initialize(this.q);
    }

    private void initialize( Queue q) {
        serverFactory = new ServerFactory();
        Server server = serverFactory.generateServer();
        serverArray = serverList.getServerList();
        serverArray.add(server);
         System.out.println("serverArray*******************************");
        System.out.println(serverArray);
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
                if(!q.isEmpty())  
                {
                    int i =0;
                    while(i<=serverArray.size())
                    {
                        Server s=(Server) serverArray.get(i);
                        System.out.println(Thread.currentThread().getName());
                        System.out.println(Thread.currentThread().getName());
                        if(s.getQ().isEmpty() && i!=0)
                        {
                            serverArray.remove(s);
                            i++;
                            continue;
                        }
                        else if(s.getQ().size()<=9)
                        {
                            System.out.println(s.getQ().size());
                            System.out.println(s.getServerName());
                            System.out.println(Thread.currentThread().getName()+":"+s.getQ().add(q.poll()));
                            s.initialize();
                            q.notifyAll();
                            
                        }
                        else
                        {
                            
                            if (i == serverArray.size() )
                            {
                                
                                Server server = serverFactory.generateServer();
                                server.setServerName("Server"+i);
                                serverArray = serverList.getServerList();
                                serverArray.add(server);
                                
                            }
                            i++;
                        }
                        
                    }

                } 
                else 
                {
                    try 
                    {
                        q.wait();
                    } catch (InterruptedException ex) 
                    {
                        Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            }
                
               
          
                
        }
    }
}
