/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usmapgrouper;

import java.util.HashMap;

/**
 *
 * @author rollersimmer
 */
public class StateMap extends HashMap<String,State>{

    public StateMap(){
        super();
    }
    
    public void addUnique(State newState) {
        put(newState.name, newState);
    }
    
    public State find(String stateName){
        if(containsKey(stateName))
            return get(stateName);
        else
            return null;
    }
}
