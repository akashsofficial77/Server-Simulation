/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;
import java.util.*;
/**
 *
 * @author tinyteddybear
 */
public class ServerFactory 
{
    private Queue q;
    
    public Server generateServer()
    {
        
        return new Server(q);
        
    }
    
}

