/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

/**
 *
 * @author rollersimmer
 */
public enum Direction {
    north,
    south,
    east,
    west;
    
    public static final Direction[] VALUES=Direction.values();

    public static Direction pickRandomly() {
        int pickVal=myutil.MyRandom.next(0,VALUES.length-1);
        Direction result=Direction.values()[pickVal];
        return result;
    }
}
