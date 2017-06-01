/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usmapgrouper;

import java.awt.Color;
import math.Point2;
import math.Point2List;
import myutil.MyColor;

/**
 *
 * @author rollersimmer
 */
public class GeographicRegion {
    String name;
    Point2List boundary;
    Color fillColor;
    Color strokeColor;
    Point2 center;
    
    public GeographicRegion() {  
        name="";
        boundary=new Point2List();
        fillColor=new Color(128);
        strokeColor=new Color(255);        
        center=new Point2(0,0);
    }
    
    public GeographicRegion(String regionName,Color fillColor,Color strokeColor,Point2List boundary){
        this();
        this.name=regionName;
        this.fillColor=fillColor;
        this.strokeColor=strokeColor;
        this.boundary=boundary;
        center=findCenter();
    }
    
    public GeographicRegion(String regionName,Color fillColor,Color strokeColor,int... coords){
        this(regionName,fillColor,strokeColor,new Point2List(coords));
    }    
    
    public GeographicRegion(String regionName,Color fillColor,int... coords){
        this(regionName,fillColor,fillColor.darker(),coords);
        MyColor newStrokeColor=new MyColor(strokeColor);
        strokeColor=newStrokeColor.scaleAlpha(3,1);
    }    
    
    public void scaleAll(int scalingFactor){
        boundary.scaleAll(scalingFactor);
    }      

    private Point2 findCenter() {
        if(boundary.size()==0) return Point2.ZERO;
        Point2 sum;
        Point2 result;
        sum=new Point2(0,0);
        for(Point2 p:this.boundary){
            sum.inc(p);
        }
        result=new Point2(sum);
        result.divEqu(boundary.size());
        return result;
    }
}
