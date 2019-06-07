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
import elasticcomputing.*;
/**
 *
 * @author tinyteddybear
 */
public class Dispatcher {
    
    Request req;
    //ServerList queue;
    
    ArrayList serverArray;
    ServerFactory serverFactory;
    Queue q;
    private final int reqPerServer;
    private final int processingTime;
    private final ServerList serverList;
    private final RequestList requestList;
    Dispatcher(Queue q, int reqPerServer, int processingTime, ServerList serverList, RequestList requestList) {
        this.q=q;
        this.reqPerServer = reqPerServer;
        this.processingTime = processingTime;
        this.serverList = serverList;
        this.requestList=requestList;
        initialize();
    }

    private void initialize() {
        serverFactory = new ServerFactory();
        Server server = serverFactory.generateServer();
        server.setServerName("1ST - SERVER");
        server.setProcessingTime(processingTime);
        server.setRequestList(requestList.getRequestList());
        serverArray = serverList.getServerList();
        serverArray.add(server);
        server.initialize();
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
                    while(i<serverArray.size())
                    {   
                       // System.out.println("While i is less than server array size");
                        Server s=(Server) serverArray.get(i);
                        System.out.println(Thread.currentThread().getName());
                        System.out.println(Thread.currentThread().getName());
                        for (int j =0;j<serverArray.size();j++)
                        {
                            Server se=(Server) serverArray.get(j);
                            System.out.println("Sever name is " + se.getServerName()+" has "+se.getQ().size());
                        }
                         if(s.getQ().size() < reqPerServer)
                        {
                            System.out.println("Sending request to server " + s.getServerName() + " with size " +  s.getQ().size() );
                            Request newRequest =(Request)q.poll();
                             if (newRequest == null){
                                break;
                             }
                            System.out.println("Removing the element from main queue and size is " + q.size());
                            q.notifyAll();
                            System.out.println("Dispatcher thread has notified all");
                            System.out.println(Thread.currentThread().getName()+":"+s.getQ().add(newRequest));                           
                            s.initialize();  
                        }
                        else
                        {
                            i++;
                            if (i == serverArray.size() )
                            {
                                Server server = serverFactory.generateServer();
                                server.setProcessingTime(processingTime);
                                server.setRequestList(requestList.getRequestList());
                                System.out.println("Server is full so creating new server with " + server.getServerName() + " as server name");
                                serverArray = serverList.getServerList();
                                serverArray.add(server);   
                            }
                        }
                        
                    }
                     for (int x=0;x<serverArray.size();x++ )
                                {   
                                    
                                    System.out.println("Completly deleting server");
                                    Server server=(Server) serverArray.get(x);
                                    if(server.getQ().isEmpty() && server.getProcesse()!=0)
                                    {   
                                        System.out.println("Removing empty server *****************************************" +x+"    " + server.getServerName());
                                        server.setRemove(1);
                                        serverArray.remove(server);
                                    } 
                                }
                } 
                else 
                {
                    try 
                    {
                        q.wait(10);
                        System.out.println("Dispatcher thread is waiting");
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
