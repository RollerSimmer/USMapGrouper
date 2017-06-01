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
public class MyColorFactory {

    public static final int RED_LIGHT=292;
    public static final int GREEN_LIGHT=589;
    public static final int BLUE_LIGHT=114;

    private static int calcLightSum(int r, int g, int b) {
        return r*RED_LIGHT+g*GREEN_LIGHT+b*BLUE_LIGHT;
    }

    public static MyColor createRandomly(int light1000){
        final int ACCURACY_THRESHOLD=1000;
        final int MAX_LIGHT_SUM=1000*255;
        int targetLightSum=light1000*255;
        int r=MyRandom.next(0,255);
        int g=MyRandom.next(0,255);
        int b=MyRandom.next(0,255);
        int lightSum=calcLightSum(r,g,b);
        for(int pass=0;pass<1;pass++){
            if(lightSum-targetLightSum<-ACCURACY_THRESHOLD){   
                while(lightSum-targetLightSum<-ACCURACY_THRESHOLD){   
                    r=(7*r+255)/8;
                    g=(7*g+255)/8;
                    b=(7*b+255)/8;
                    lightSum=calcLightSum(r,g,b);
                }        
            } else {
                while(lightSum-targetLightSum>ACCURACY_THRESHOLD){   
                    r=7*r/8;
                    g=7*g/8;
                    b=7*b/8;
                    lightSum=calcLightSum(r,g,b);
                }        
            }
        }
        MyColor result=new MyColor(r,g,b);
        return result;
    }    
}
