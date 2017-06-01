/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usmapgrouper;

/**
 *
 * @author rollersimmer
 */
public class StateDrafter {
    
    StateMap stateMap;
    StateList freeStateList;
    StateList usedStateList;
    Country country;
    
    public StateDrafter(){
//        init();
    }

    void init() {
        stateMap=AmericanStateMapFactory.create();
        usedStateList=new StateList();
        freeStateList=new StateList(stateMap);
        freeStateList.sortByPopulation();
        country=new Country();
        
        initRegionsInCountry();
    }
    
    private void initRegionsInCountry() {
        for(int i=0;i<Parameters.AMT_REGIONS;i++){
            Region newRegion=new Region();
            State stateToAdd=freeStateList.getFirstDistantState(usedStateList);
//            State stateToAdd=freeStateList.getFirstNonTouchingState(usedStateList);
            markStateAsUsed(stateToAdd);
            newRegion.add(stateToAdd);
            newRegion.reCenter(stateToAdd.pos);
            country.add(newRegion);
        }
    }
    
    public void draftAll(){
        while(areThereStatesLeftToDraft()){
            draftNext();
        }
    }

    private void draftNext() {
        country.sortRegionsBySmallestTotalPopulation();
        int i=0;
        boolean done=false;
        while(!done){
            Region rgn=country.get(i);
            done=rgn.draftLargestContiguousState(freeStateList,usedStateList);
            if(!done){
                ++i;
                done=i>=country.size();
            }
        }
    }

    private boolean areThereStatesLeftToDraft() {
        return freeStateList.size()>0;
    }

    private void markStateAsUsed(State stateToAdd) {
        freeStateList.remove(stateToAdd);
        usedStateList.add(stateToAdd);
    }

    void balanceCountry() {
        country.balanceByPopulation();
    }

}
