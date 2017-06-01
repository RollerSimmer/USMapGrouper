/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usmapgrouper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author rollersimmer
 */
class CountryBalancer extends ArrayList<Country> {
    
    public static final int MAX_PARENTS=512;
    public static final int MAX_BALANCE_ROUNDS=5;
    public static final int GENERATIONAL_INCREASE_RATIO=32;
    
    public int smallestDeviation;
    
    public int amtRounds;
    
    public CountryBalancer(Country baseCountry){
        init(baseCountry);
    }

    CountryBalancer() {
    }
    
    public void init(Country baseCountry){
        amtRounds=0;
        smallestDeviation=Integer.MAX_VALUE;
        this.clear();
        int amtInitialCountries=10;
        for(int i=0;i<amtInitialCountries;i++){
            Country c = new Country(baseCountry);
            c.exchangeRandomly(Country.SHOULD_SORT_AFTER_EACH_REGION_EXCHANGE);
            add(c);
        }
        sortCountriesByLeastDeviation();        
    }    
    
    public Country balance(Country baseCountry){
        init(baseCountry);
        while(canBalanceMore()){
            balanceForOneRound();
        }
        Country result=size()>0? get(0): null;        
        return result;
    } 

    private void balanceForOneRound() {
        int amtChildren=MAX_PARENTS*GENERATIONAL_INCREASE_RATIO;
        generateChildren(amtChildren);
        sortCountriesByLeastDeviation();
        cullToMaxParents();
        if(areAllDeviationsTheSame()){
            amtRounds++;
        }else{
            amtRounds=0;
        }
    }

    private boolean canBalanceMore() {
        boolean result=false;
        if(amtRounds>=MAX_BALANCE_ROUNDS){
            result=false;                                    
        } else if(size()>0){
            int devA=get(0).calcDeviation();
            result=devA>Parameters.BALANCE_DEVIATION_THRESHOLD;
                   
            smallestDeviation=Math.min(smallestDeviation,devA);            
        }
        return result;            
    }

    private void generateChildren(int amtChildren) {
        int amtParents=size();
        for(int ichild=0;ichild<amtChildren;ichild++){
            Country c=pickCountryFromParents(amtParents);
            add(c.generateMutatedChild());
        }                   
    }   

    private void sortCountriesByLeastDeviation() {
        Collections.sort(this, new Comparator<Country>() {
            @Override
            public int compare(Country a, Country b){
                return a.calcDeviation()-b.calcDeviation();
            }
        });
    }

    private int pickIndexBesidesPrevious(ArrayList<Integer> previouslyPickedIndices) {
        int result=pickIndex();
        while(previouslyPickedIndices.contains(result)){
            result=pickIndex();
        }        
        return result;
    }

    private Country pickCountry() {
        int i=pickIndex();
        Country result=get(i);
        return result;
    }

    private int pickIndex() {
        int result=myutil.MyRandom.next(0,size()-1);
        return result;
    }    

    private void cullToMaxParents() {
        if(size()>MAX_PARENTS){
            this.removeRange(MAX_PARENTS,size());
        }
    }
    
    public String toString() {
        String result="CountryBalancer:\n";
        result+=String.format("amtRounds=%d\n",amtRounds);
        for(Country c: this){
            result+=c.toString();
        }        
        return result;        
    }

    private Country pickCountryFromParents(int amtParents) {
        int i=myutil.MyRandom.next(0,amtParents-1);
        Country result=get(i);
        return result;
    }

    private boolean areAllDeviationsTheSame() {
        if(size()==0)
            return false;
        int devFirst=get(0).calcDeviation();
        int devLast=get(size()-1).calcDeviation();
        return devFirst==devLast;
    }

}
