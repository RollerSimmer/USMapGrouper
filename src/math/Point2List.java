/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.util.ArrayList;

/**
 *
 * @author rollersimmer
 */
public class Point2List extends ArrayList<Point2> {
    public Point2List() { super(); }
    
    public Point2List(int... coords){
        this();
        int amtCoords=coords.length;
        int x=0; 
        int y=0;
        int i=0;
        Point2 pt;
        for(int c:coords){
            if((i&1)==0) 
                x=c;
            else {
                y=c;
                pt=new Point2(x,y);
                this.add(pt);
            }
            i++;
        }
    }
    
    public void scaleAll(int scalingFactor){
        for(Point2 pt:this){
            pt.scale(scalingFactor);
        }
    }      
    
}
