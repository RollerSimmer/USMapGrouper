/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usmapgrouper;

import java.util.ArrayList;
import math.IntVector2;
import math.Point2;

/**
 *
 * @author rollersimmer
 */
public class GeographyList extends ArrayList<GeographicRegion> {
    
    public GeographyList() { super(); }
    
    public void scaleAll(int scalingFactor){
        for(GeographicRegion r:this){
            r.scaleAll(scalingFactor);
        }
    }      
    
    /**
     * 
     * @param rgnName the name of a region to find
     * @return the region in the list that is named by rgnName
     */
    public GeographicRegion find(String rgnName){
        GeographicRegion result=null;
        for(GeographicRegion rgn:this){
            if(rgn.name==rgnName){
                result=rgn;
                break;
            }
        }        
        return result;
    }    

    IntVector2 getMins() {
        IntVector2 result=new IntVector2(Integer.MAX_VALUE,Integer.MAX_VALUE);
        for(GeographicRegion gr:this){
            for(Point2 pt:gr.boundary){
                if(pt.x<result.x)
                    result.x=pt.x;
                if(pt.y<result.y)
                    result.y=pt.y;
            }
        }
        return result;
    }

    IntVector2 getMaxs() {
        IntVector2 result=new IntVector2(Integer.MIN_VALUE,Integer.MIN_VALUE);
        for(GeographicRegion gr:this){
            for(Point2 pt:gr.boundary){
                if(pt.x>result.x)
                    result.x=pt.x;
                if(pt.y>result.y)
                    result.y=pt.y;
            }
        }
        return result;
    }
}

