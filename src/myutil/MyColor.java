/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myutil;

import java.awt.Color;

/**
 *
 * @author rollersimmer
 */
public class MyColor extends Color {
    
    public MyColor(Color c){
        super(c.getRed(),c.getGreen(),c.getBlue());
    }
    
    public MyColor(int r, int g, int b) {
        super(r, g, b);
    }
    
    public static MyColor avg(Color ca,Color cb){
        int r=(ca.getRed()+cb.getRed())/2;
        int g=(ca.getGreen()+cb.getGreen())/2;
        int b=(ca.getBlue()+cb.getBlue())/2;
        return new MyColor(r,g,b);
    }    
    
    public MyColor somewhatBrighter(){
        MyColor b=new MyColor(brighter());
        MyColor result=MyColor.avg(b,this);
        return result;
    }    

    public Color scaleAlpha(int num, int denom) {
        int a=getAlpha();
        a*=num;
        a+=Math.max(denom-1,0);
        a/=denom;
        a=Math.min(a,255);
        return new Color(getRed(),getGreen(),getBlue(),a);        
    }
}
