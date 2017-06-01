/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usmapgrouper;

import java.util.ArrayList;
import java.util.Stack;
import math.IntVector2;
import myutil.MyColor;
import myutil.MyColorFactory;

/**
 *
 * @author rollersimmer
 */
public class Region extends ArrayList<State> {
    

    private int totalPopulation;
    private IntVector2 centerPos;
    public MyColor color;
    
    Region() {
        super();
        totalPopulation=0;
        centerPos=new IntVector2(0,0);
        generateColor();
    }
    
    Region(Region rgnToCopy) {
        totalPopulation=0;
        centerPos=new IntVector2(0,0);
        generateColor();
        for(State s:rgnToCopy){
            this.add(s);
        }
    }

    private void generateColor() {
        int colorLightLevel=400;
        color=MyColorFactory.createRandomly(colorLightLevel);
    }

    static boolean exchangeState(Region toRgn, Region fromRgn, State s) {
        boolean shouldRetainContiguity=false;
        return exchangeState(toRgn,fromRgn,s,shouldRetainContiguity);
    }

    static boolean exchangeState(Region toRgn, Region fromRgn, State s, boolean shouldRetainContiguity) {
        boolean isToBeRemovedStateNeededForContinguity=shouldRetainContiguity?fromRgn.doesNeedStateForContiguity(s):false;
        
        boolean result=fromRgn.contains(s)&&fromRgn.size()>=1
                      &&(!isToBeRemovedStateNeededForContinguity);        
        if(result){
            fromRgn.remove(s);
            toRgn.add(s);            
        }
        return result;
    }
    
    private static StateList findStatesInLargeRegionTouchingSmallRegion(Region small, Region large) {
        StateList result=new StateList();
        for(State s: large){
            if(small.isStateConnectedToThisRegion(s)){
                result.add(s);
            }
        }
        return result;
    }
    
    public int getTotalPopulation(){
        return totalPopulation;
    }
    
    public int calcTotalPopulation(){
        int result=0;
        for(State s:this){
            result+=s.population;
        }
        totalPopulation=result;
        return result;
    }

    private void updateTotalPopulation() {
        totalPopulation=calcTotalPopulation();
    }
    
    public IntVector2 getCenterPos(){
        return this.centerPos;
    }
    
    public void reCenter(IntVector2 newCenter){
        this.centerPos=newCenter;    
    }
    
    public void autoReCenter(){
        reCenter(calcAvgStatePosition());
    }    
    
    public IntVector2 calcAvgStatePosition(){
        IntVector2 result=new IntVector2(0,0);
        if(this.size()>0){            
            for(State s: this){
                result.inc(s.pos);
            }
            result.divEqu(this.size());
        }
        centerPos=result;
        return result;
    }

    boolean draftLargestContiguousState(StateList sortedFreeStateList, StateList usedStateList) {
        for(State s: sortedFreeStateList){
            if(isStateViableDraftCandidate(s)){
                this.add(s);
                sortedFreeStateList.remove(s);
                usedStateList.add(s);
                return true;
            }
        }
        return false;
    }

    private boolean isStateViableDraftCandidate(State s) {
        return isStateConnectedToThisRegion(s)
//        &&isStateCloseEnoughToCenter(s)
        ;
    }

    public boolean isStateConnectedToThisRegion(State other) {
        boolean result=false;
        for(State s: this){
            if(s.isConnectedTo(other))
                result=true;
        }
        return result;
    }
    
    private boolean isStateCloseEnoughToCenter(State s) {
        return IntVector2.dist(s.pos,centerPos)<=Parameters.MAX_SPAN_FROM_CENTER;
    }
    
    StateList findAllNeighboringStates(){
        StateList result=new StateList();
        for(State s: this){
            for(State n:s.neighbors.values()){
                if(n!=null&&!this.contains(n)&&!result.contains(n)){
                    result.add(n);
                }   
            }
        }            
        return result;
    }
    
    @Override 
    public boolean remove(Object s){
        boolean result=super.remove(s);
        ((State)s).parentRegion=null;
        updateTotalPopulation();
        return result;
    }
    
    @Override
    public boolean add(State s){
        boolean result = super.add(s);
        s.parentRegion=this;
        totalPopulation+=s.population;
        return result;
    }

    @Override
    public String toString(){
        String result="";
        int i=0;
        result+="Region\n";
        for(State s:this){
            result+="\t";
            result+=s.toString();
            if(i<size()-1)
                result+=",";
            result+="\n";
            i++;
        }
        result+=String.format("\ttotalPop=%d\n"+
                              "\tavgStatePos=%s\n",
                    calcTotalPopulation(),
                    calcAvgStatePosition().toString());
        return result;
    }
    
    public String shortString(){
        String result="{";
        int i=0;
        for(State s:this){
            result+=s.name;
            if(i<size()-1)
                result+=",";
            i++;
        }
        result+="}\n";
        return result;
    }

    private boolean doesNeedStateForContiguity(State s) {
//        if(s.name=="Washington"){
//            System.out.println(""); //dummy break
//        }            
        int targetSize=size()-1;
        if(targetSize<=0)
            return false;
        StateList statesVisited=new StateList();
        Stack<State> stateStack=new Stack<>();
        State root=null;
        root=get(0);
        if(root==s)
            root=get(1);
        stateStack.push(root);
        while(!stateStack.isEmpty()){
            State curState=stateStack.pop();
            if(curState!=s && !statesVisited.contains(curState)){
                statesVisited.add(curState);
                for(State n: curState.neighbors.values()){
                    if(this.contains(n)&&!statesVisited.contains(n)&&n!=s){
                        stateStack.push(n);
                    }
                }
            }
        }
        int amtStatesVisited=statesVisited.size();
        boolean result=amtStatesVisited!=targetSize;
        return result;
    }

}
