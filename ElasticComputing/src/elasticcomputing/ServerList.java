/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;

import java.util.ArrayList;

/**
 *
 * @author Aditya
 */
public class ServerList {
    ArrayList<Server> serverList;
    
    public ServerList()
    {
        serverList = new ArrayList<Server>();
    }
    
    

    public ArrayList getServerList() {
        return serverList;
    }

    public void setServerList(ArrayList ServerList) {
        this.serverList = ServerList;
    }
   
    
}
