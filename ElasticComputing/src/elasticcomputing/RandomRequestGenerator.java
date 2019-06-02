/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Aditya
 */
public class RandomRequestGenerator {
    
  
    public static void main(String args[]){
        generateRandomRequest();
    }
  
    public static  void generateRandomRequest(){
    RequestFactory requestFactory  =  new RequestFactory();
    Random rand = new Random();
    
    int randInt2 = 0; 
    int randInt = 0;
    
    
    for (int i = 0 ; i < 100 ; i ++){
    try{
    randInt2 = rand.nextInt(10);
    randInt = rand.nextInt(10);
    TimeUnit.SECONDS.sleep(randInt2);
    } catch(Exception e){
        
    }
    Request req1 = requestFactory.generateRequest();
    req1.setName(Integer.toString(i));
    req1.setProcessingTime(randInt);
    System.out.println("Request name is "+req1.getName()+ " Request processing time is " + req1.getProcessingTime());
    }
    
  
    
    
}
}

