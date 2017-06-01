/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usmapgrouper;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import myutil.MyColor;

/**
 *
 * @author rollersimmer
 */
public class StateGeographyMap extends HashMap<State,GeographicRegion> {

    public StateGeographyMap(StateList sl,GeographyList gl) {
        super();
        init(sl,gl);
    }
    
    public void init(StateList sl,GeographyList gl) {
        for(State s:sl){
            if(!this.containsKey(s)){
                this.put(s,gl.find(s.name));
            }
        }
    }
    
    public void recolorBasedOnCountryRegions(Country c){
        MyColor fillColor=null;
        MyColor strokeColor=null;
        GeographicRegion gr=null;
        for(Region rgn:c){
            fillColor=rgn.color;
            strokeColor=new MyColor(fillColor.darker().darker());
            for(State s:rgn){
                if(this.containsKey(s)){
                    gr=this.get(s);
                    try {
                        if(gr==null){
                            throw new Exception("Fucksticks");
                        }
                    } catch (Exception ex) {
                        continue;
                    }
                    gr.fillColor=fillColor;
                    gr.strokeColor=strokeColor;
                }
            }
        }
    }
    
}
