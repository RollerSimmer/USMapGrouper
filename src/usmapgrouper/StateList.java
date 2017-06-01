/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usmapgrouper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;

/**
 *
 * @author rollersimmer
 */
public class StateList extends ArrayList<State> {
    
    public StateList(StateMap smap){
        for(Entry<String,State> entry: smap.entrySet()) {
            this.add(entry.getValue());
        }       
    }

    StateList() {}
    
    void sortByPopulation(){
        Collections.sort(this, new Comparator<State>() {
            @Override
            public int compare(State a, State b){
                return  b.population-a.population;
            }
        });
    }

    void popFront() {
        if(size()>0)
            remove(0);
    }
    
    State getFront() {
        if(size()>0)
            return get(0);
        else
            return null;
    }

    State getFirstNonTouchingState(StateList usedStateList) {
        for(State s:this){
            if(usedStateList.size()==0)                
                return s;
            boolean isTouching=false;
            for(State u:usedStateList){
                isTouching=isTouching || s.isConnectedTo(u);
            }
            if(!isTouching)
                return s;
        }
        return null;
    }

     State getFirstDistantState(StateList usedStateList) {
        for(State s:this){
            if(usedStateList.size()==0)                
                return s;
            boolean isDistant=true;
            for(State u:usedStateList){
                isDistant=isDistant&&s.isDistantFrom(u,Parameters.EPICENTER_SEPARATION_DEGREES);
            }
            if(isDistant)
                return s;
        }
        return null;
    }

    
    @Override
    public String toString(){
        String result="";
        int i=0;       
        result+="{";
        for(State s:this){
            result+=s.name;
            if(i<size()-1){
                result+=",";
            }
            ++i;            
        }
        result+="}";
        return result;
        
    }

    State findBestExchangeCandidateWithDest(Region toRgn) {
        State result=null;
        int toPop=toRgn.getTotalPopulation();
        int maxDelta=Integer.MIN_VALUE;       
        for(State s:this){
            Region fromRgn=s.parentRegion;
            int fromPop=fromRgn.getTotalPopulation();
            int rgnPopDelta=toPop-fromPop;
            if(rgnPopDelta>maxDelta){
                maxDelta=rgnPopDelta;
                result=s;
            }
        }
        return result;
    }
    
//    State findBestExchangeCandidateWithDest(Region toRgn, int avgRgnPop) {
//        int toPop=toRgn.getTotalPopulation();
//        State result=null;
//        int minDeltaPop=Integer.MAX_VALUE;
//        for(State s:this){
//            int statePop=s.population;
//            Region fromRgn=s.parentRegion;
//            int fromPop=fromRgn.getTotalPopulation();
//            if(toPop>fromPop){                
//                int futureFromPop=fromPop-statePop;
//                int futureToPop=toPop+statePop;            
//                int futureToDeltaPop=Math.abs(futureToPop-avgRgnPop);
//                int futureFromDeltaPop=Math.abs(futureFromPop-avgRgnPop);            
//                int futureDeltaPop=futureToDeltaPop+futureFromDeltaPop;
//                if(futureDeltaPop<minDeltaPop){
//                    minDeltaPop=futureDeltaPop;
//                    result=s;
//                }
//            }                
//        }        
//        return result;
//    }

    State findBestExchangeCandidateWithSource(Region fromRgn) {
        State result=null;        
        int fromPop=fromRgn.getTotalPopulation();
        int maxDelta=Integer.MIN_VALUE;       
        for(State s:this){
            Region toRgn=s.parentRegion;
            int toPop=toRgn.getTotalPopulation();
            int rgnPopDelta=toPop-fromPop;
            if(rgnPopDelta>maxDelta){
                maxDelta=rgnPopDelta;
                result=s;
            }
        }
        return result;
    }
    
//    State findBestExchangeCandidateWithSource(Region fromRgn, int avgRgnPop) {
//        int fromPop=fromRgn.getTotalPopulation();
//        State result=null;
//        int minDeltaPop=Integer.MAX_VALUE;
//        for(State s:this){
//            int statePop=s.population;
//            Region toRgn=s.parentRegion;
//            int toPop=toRgn.getTotalPopulation();
//            if(toPop>fromPop){                
//                int futureFromPop=fromPop-statePop;
//                int futureToPop=toPop+statePop;            
//                int futureToDeltaPop=Math.abs(futureToPop-avgRgnPop);
//                int futureFromDeltaPop=Math.abs(futureFromPop-avgRgnPop);            
//                int futureDeltaPop=futureToDeltaPop+futureFromDeltaPop;
//                if(futureDeltaPop<minDeltaPop){
//                    minDeltaPop=futureDeltaPop;
//                    result=s;
//                }
//            }                
//        }        
//        return result;
//    }
}
