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
public class Dispatcher {
    
    Request req;
    ServerList queue;
    ServerList serverList = new ServerList();
    ArrayList serverArray;
    ServerFactory serverFactory;
    Queue q;
    
    Dispatcher(Queue q) {
        this.q=q;
        initialize();
    }

    private void initialize() {
        serverFactory = new ServerFactory();
        Server server = serverFactory.generateServer();
        server.setServerName("1ST-SERVER");
        serverArray = serverList.getServerList();
        serverArray.add(server);
        System.out.println("serverArray*******************************");
        System.out.println(serverArray);
        Runnable r = new RunnableImp(q);
       // Runnable rDel= new RunnableDel();
        Thread t1 = new Thread(r);
       // Thread t2 =new Thread(rDel);
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
                        System.out.println("While i is less than server array size");
                        Server s=(Server) serverArray.get(i);
                        System.out.println(Thread.currentThread().getName());
                        System.out.println(Thread.currentThread().getName());
                        for (int j =0;j<serverArray.size();j++)
                        {
                            Server se=(Server) serverArray.get(j);
                            System.out.println("Server"+j+"has"+se.getQ().size());
                        }
                      /*  if(s.getQ().isEmpty() && i!=0)
                        {   
                            System.out.println("Removing empty server "  + s.getServerName());
                            serverArray.remove(s);
                            i--;
                            continue;
                        } */
                         if(s.getQ().size()<=99)
                        {
                           
                            System.out.println("Sending request to server " + s.getServerName() + " with size " +  s.getQ().size() );
                            Request newRequest =(Request)q.poll();
                            if (newRequest == null){
                               /* for (Object o : serverArray )
                                {   
                                    System.out.println("Completly deleting server");
                                    Server server=(Server) o;
                                    if(server.getQ().isEmpty())
                                    {   
                                        System.out.println("Removing empty server "  + server.getServerName());
                                        serverArray.remove(server);
                                    } 
                                }*/
                                break;
                            }
                            System.out.println("Removing the element from main queue and size is " + q.size());
                            q.notifyAll();
                            System.out.println("Dispatcher thread has notified all");
                            //int sleepTime = newRequest.getProcessingTime();
                            //System.out.println(sleepTime);
                            System.out.println(Thread.currentThread().getName()+":"+s.getQ().add(newRequest));
                            
                            s.initialize();
                            
                            
                        }
                        else
                        {
                            i++;
                            if (i == serverArray.size() )
                            {
                                
                                Server server = serverFactory.generateServer();
                                server.setServerName("Server"+i);
                                
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
                                    if(server.getQ().isEmpty())
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
                        q.wait();
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
