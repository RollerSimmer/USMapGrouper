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
public class Parameters {
    public static int REGION_POPULATION_BALANCE_THRESHOLD=3000000;
    public static int EPICENTER_SEPARATION_MILES = 1000;
    public static int EARTH_CIRCUMFERENCE_MILES_PER_DEGREE = 69;
    public static int EPICENTER_SEPARATION_DEGREES = 
            (EPICENTER_SEPARATION_MILES + (EARTH_CIRCUMFERENCE_MILES_PER_DEGREE>>1))
            / EARTH_CIRCUMFERENCE_MILES_PER_DEGREE;
    public static int MAX_SPAN_FROM_CENTER = EPICENTER_SEPARATION_DEGREES*2;
    public static int AMT_REGIONS=4;
    public static int BALANCE_DEVIATION_THRESHOLD=7;    
    public static int MUTATION_CHANCE_PCT=90;
    public static int MAX_MUTATIONS_PER_COUNTRY=3;
}
