/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elasticcomputing;

/**
 *
 * @author Aditya
 */
public class Request {
    
    String name;
    int processingTime;
    long rStartTime = System.nanoTime();
    long rEndTime;
    static int i=0;

    public long getrStartTime() {
        return rStartTime;
    }

    public void setrStartTime(long rStartTime) {
        this.rStartTime = rStartTime;
    }

    public long getrEndTime() {
        return rEndTime;
    }

    public void setrEndTime(long rEndTime) {
        this.rEndTime = rEndTime;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name + i;
        i++;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }
    
    
}
