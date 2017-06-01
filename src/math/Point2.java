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
public class Point2 extends IntVector2 {
    
    public static final Point2 ZERO=new Point2(0,0);    
    
    public Point2(int x,int y){ super(x,y); }   
    
    public Point2(){ super(); }

    public Point2(Point2 copy) { super(copy.x,copy.y);  }
}
