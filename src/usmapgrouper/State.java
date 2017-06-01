/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usmapgrouper;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import math.Direction;
import math.IntVector2;

/**
 *
 * @author rollersimmer
 */
public class State {
    public int alphabeticIndex;
    public String name;
    public IntVector2 pos;
    public int population;
    public Map<Direction,State> neighbors;
    Region parentRegion;
    
    public State(int alphabeticIndex,String name,IntVector2 pos,int population){
        this.alphabeticIndex=alphabeticIndex;
        this.name=name;
        this.pos=pos;
        this.population=population;
        this.neighbors=new EnumMap<Direction,State>(Direction.class);
        for (Direction dir: Direction.values()){
            this.neighbors.put(dir,null);
        parentRegion=null;
        }
    }
    
    public void setNeighbors(String n,String s,String e,String w,StateMap lookupMap){
        this.neighbors.put(Direction.north,lookupMap.find(n));
        this.neighbors.put(Direction.south,lookupMap.find(s));
        this.neighbors.put(Direction.east,lookupMap.find(e));
        this.neighbors.put(Direction.west,lookupMap.find(w));
    }
    
    public void setNeighbor(Direction dir,State neighbor){
        this.neighbors.put(dir,neighbor);
    }
    
    public void setNeighborByName(Direction dir,String neighborName,StateMap lookupMap){
        State neighbor=lookupMap.find(neighborName);
        setNeighbor(dir,neighbor);
    }
       
    public void removeNeighbor(State neighbor){
        for(Direction dir: Direction.values()){
            if(neighbors.get(dir)==neighbor)
                neighbors.put(dir,null);                
        }
    }
    
    public void clearNeighbors(){
        neighbors.clear();
    }    

    boolean isConnectedTo(State other) {
        boolean result=false;
        for(Direction dir: Direction.values()){
            result=result||(other==neighbors.get(dir));
        }
        return result;
    }
    
    
    public String getNeighborName(Direction dir){
        State neighbor=neighbors.get(dir);
        if(neighbor==null)
            return "(None)";
        else
            return neighbor.name;
    }
    
    @Override
    public String toString(){
        String result=String.format("%s,%d,%s,%s,%s,%s,%s",
                            name,population,
                            pos.toString(),
                            getNeighborName(Direction.north),
                            getNeighborName(Direction.south),
                            getNeighborName(Direction.east),
                            getNeighborName(Direction.west)
                            );
        return result;
    }

    boolean isDistantFrom(State other, int minDistance) {
        int dist=IntVector2.dist(other.pos,pos);
        if(dist>=minDistance){
             dist=IntVector2.dist(other.pos,pos);
        }
        return dist>=minDistance;
    }

    StateList findAllNeighborsSharingRegionWithMe() {
        StateList result=new StateList();
        for(State n: neighbors.values()){
            if(n!=null&&n.parentRegion==parentRegion){
                result.add(n);
            }
        }
        return result;
    }
}
