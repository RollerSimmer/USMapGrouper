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
public class PointNav2 extends IntVector2 {       
    
    public static final int DEFAULT_STEP=1;
    
    public PointNav2(PointNav2 copy){
        super(copy);        
    }    
    
    public PointNav2(IntVector2 copy){
        super(copy);        
    }    
    
    public PointNav2 getUp(int step){
        PointNav2 result=new PointNav2(this);
        result.goUp(step);
        return result;        
    }
    
    public PointNav2 getDown(int step){
        PointNav2 result=new PointNav2(this);
        result.goDown(step);
        return result;        
    }
    
    public PointNav2 getLeft(int step){
        PointNav2 result=new PointNav2(this);
        result.goLeft(step);
        return result;        
    }
    
    public PointNav2 getRight(int step){
        PointNav2 result=new PointNav2(this);
        result.goRight(step);
        return result;        
    }
    
    public PointNav2 getUp(){
        return getUp(DEFAULT_STEP);
    }

    public PointNav2 getDown(){
        return getDown(DEFAULT_STEP);
    }

    public PointNav2 getLeft(){
        return getLeft(DEFAULT_STEP);
    }

    public PointNav2 getRight(){
        return getRight(DEFAULT_STEP);
    }
    
    public void goUp(int step){
        y-=step;
    }
    
    public void goDown(int step){
        y+=step;
    }
    
    public void goLeft(int step){
        x-=step;
    }
    
    public void goRight(int step){
        x+=step;
    }

    public void goUp(){
        goUp(DEFAULT_STEP);
    }
    
    
    public void goDown(){
        goDown(DEFAULT_STEP);
    }

    public void goLeft(){
        goLeft(DEFAULT_STEP);
    }

    public void goRight(){
        goRight(DEFAULT_STEP);
    }    
}
