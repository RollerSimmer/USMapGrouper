/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usmapgrouper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import static java.lang.Math.abs;
import static java.lang.Math.abs;
import math.Direction;
import static java.lang.Math.abs;
import static java.lang.Math.abs;
import math.IntVector2;

/**
 *
 * @author rollersimmer
 */
public class Country extends ArrayList<Region> {
    
    private int avgRgnPop;
    public static final boolean SHOULD_SORT_AFTER_EACH_REGION_EXCHANGE=true;
    public static final boolean SHOULD_RETAIN_CONTIGUITY=true;
    
    public Country(){
        avgRgnPop=0;
    }

    Country(Country baseCountry) {
        this();
        for(Region rgn: baseCountry){
            Region rgnCopy=new Region(rgn);
            this.add(rgnCopy);
        }
    }
        
    public int calcAvgRegionPopulation(){
        int result=0;
        for(Region r: this){
            result+=r.calcTotalPopulation();
        }
        result/=this.size();
        return result;
    }
    
    public Region findSmallestRegion(){
        int smallPop=Integer.MAX_VALUE;
        Region result=null;
        if (size()==0) 
            return null;
        else{
            for(Region r: this){
                int curPop=r.getTotalPopulation();
                if(curPop<smallPop){
                    smallPop=curPop;
                    result=r;
                }
            }
        }
        return result;
    }
    
    public boolean areRegionsBalanced(){
        return areRegionsBalanced(Parameters.REGION_POPULATION_BALANCE_THRESHOLD);
    }
    
    public boolean areRegionsBalanced(int threshold){
        int avgPop=calcAvgRegionPopulation();
        for(Region r:this){
            int errorAmt=abs(r.getTotalPopulation() - avgPop);
            if(errorAmt>threshold)
                return false;
        }            
        return true;
    }

    public void sortRegionsBySmallestTotalPopulation() {
        Collections.sort(this, new Comparator<Region>() {
            @Override
            public int compare(Region a, Region b){
                return  a.getTotalPopulation()-b.calcTotalPopulation();
            }
        });        
    }

    public String toString(){
        String result="Country:\n";
        result+=String.format("avgPop=%d\n",this.calcAvgRegionPopulation());
        result+=String.format("popDev=%d\n",this.calcDeviation());
        for(Region r:this){
            result+=r.toString();
        }
        return result;
    }

    public void balanceByPopulation() {
        int maxExchanges=100;
        int amtExchanges=0;
        this.sortRegionsBySmallestTotalPopulation();
        avgRgnPop=calcAvgRegionPopulation();
        boolean allExchangesDone=false;
        while(!allExchangesDone){
            boolean noExchangeTookPlaceThisRound=true;
            this.sortRegionsBySmallestTotalPopulation();            
            for(int i=0;i<this.size()-1;i++){
                Region rgn=this.get(i);
                Region toRgn=null;
                Region fromRgn=null;
                State stateToExchange=null;
                int rgnPopDelta=rgn.getTotalPopulation()-avgRgnPop;
                StateList neighborStates=rgn.findAllNeighboringStates();
                if( rgnPopDelta < -Parameters.REGION_POPULATION_BALANCE_THRESHOLD){
                    toRgn=rgn;
                    if(neighborStates!=null){
                        stateToExchange=neighborStates.findBestExchangeCandidateWithDest(toRgn);
                        if(stateToExchange!=null)
                            fromRgn=stateToExchange.parentRegion;
                    }
                } else if( rgnPopDelta > +Parameters.REGION_POPULATION_BALANCE_THRESHOLD){
                    fromRgn=rgn;
                    if(neighborStates!=null){
                        stateToExchange=neighborStates.findBestExchangeCandidateWithSource(fromRgn);
                        if(stateToExchange!=null)
                            toRgn=stateToExchange.parentRegion;
                    }
                }
                if(toRgn!=null && fromRgn!=null && stateToExchange!=null
                   && fromRgn.getTotalPopulation()>toRgn.getTotalPopulation()
                   && Region.exchangeState(toRgn,fromRgn,stateToExchange)){
                    noExchangeTookPlaceThisRound=false;
                    ++amtExchanges;                            
                    allExchangesDone=allExchangesDone||amtExchanges>=maxExchanges;
                }                            
            }
                        
            allExchangesDone=allExchangesDone
                            ||areRegionsBalanced()
                            ||noExchangeTookPlaceThisRound;
        }
        return;
    }

    public int calcDeviation() {
        int devSum=0;
        avgRgnPop=calcAvgRegionPopulation();
        for(Region rgn:this){
            int devMillions=abs(rgn.getTotalPopulation()-avgRgnPop)>>20;
            devMillions*=devMillions;
//            if(devMillions<0)
//                System.out.print(""); // force breakpoint here
            devSum+=devMillions;
        }
        int result=devSum;
        return result;
    }

    public boolean exchangeRandomly(boolean shouldSortAfterwards) {
        int maxAttempts=100;
        int amtAttempts=0;
        while(amtAttempts<maxAttempts){
            amtAttempts++;
            if(attemptSingleRandomExchange()){
                if(shouldSortAfterwards)
                    sortRegionsBySmallestTotalPopulation();
                return true;
            }
        }
        return false;
    }
    
    public boolean attemptSingleRandomExchange() {
        if(size() < 2) {
            return false;
        }
        Region fromRgn=this.get(myutil.MyRandom.next(0,size()-1));
        if(fromRgn.size()==0){
            return false;
        }
        State stateToExchange=fromRgn.get(myutil.MyRandom.next(0,fromRgn.size()-1));
        State neighborState=stateToExchange.neighbors.get(Direction.pickRandomly());
        if(neighborState==null){
            return false;
        }                
        Region toRgn=neighborState.parentRegion;
        if(toRgn==null){
            return false;
        }
        return Region.exchangeState(toRgn,fromRgn,stateToExchange,SHOULD_RETAIN_CONTIGUITY);
    }
    
    public Country generateMutatedChild(){
        Country result=new Country(this);
        int mutationChancePct=Parameters.MUTATION_CHANCE_PCT;
        boolean shouldDoMutation=myutil.MyRandom.pctChance(mutationChancePct);
        if(shouldDoMutation){
            int amtMutations=myutil.MyRandom.next(1,Parameters.MAX_MUTATIONS_PER_COUNTRY);
            for(int i=0;i<amtMutations;i++){
                result.exchangeRandomly(SHOULD_SORT_AFTER_EACH_REGION_EXCHANGE);
            }
        }
        return result;
    }

    IntVector2 getMaxs() {
        IntVector2 result=new IntVector2(Integer.MIN_VALUE,Integer.MIN_VALUE);
        for(Region rgn:this){
            for(State s:rgn){
                if(s.pos.x>result.x)
                    result.x=s.pos.x;
                if(s.pos.y>result.y)
                    result.y=s.pos.y;                    
            }        
        }
        return result;
    }

    IntVector2 getMins() {
        IntVector2 result=new IntVector2(Integer.MAX_VALUE,Integer.MAX_VALUE);
        for(Region rgn:this){
            for(State s:rgn){
                if(s.pos.x<result.x)
                    result.x=s.pos.x;
                if(s.pos.y<result.y)
                    result.y=s.pos.y;                    
            }        
        }
        return result;
    }

    StateList makeStateList() {
        StateList result=new StateList();
        for(Region rgn:this){
            for(State s:rgn){
                if(!result.contains(s)){
                    result.add(s);
                }
            }            
        }            
        return result;
    }
}


