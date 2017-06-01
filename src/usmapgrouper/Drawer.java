/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usmapgrouper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import math.IntVector2;
import math.Point2;
import math.Point2List;
import myutil.MyRandom;

/**
 *
 * @author rollersimmer
 */
public class Drawer extends JComponent {
    public static final int LINE_CLOSENESS_THRESHOLD=3;
    private final int SQUARE_SHIFT=4;
    private final int SQUARE_SIZE=1<<SQUARE_SHIFT;
    private final int DIAGRAM_PIXEL_SIZE=1;
    
    private Country country;
    private GeographyList geoList;
    StateGeographyMap stateGeoMap;

    private Graphics g;
    private IntVector2 geoMaxs;
    private IntVector2 geoMins;
    private IntVector2 extents;
    private IntVector2 scaledExtents;
    
    Color gridColor;
    
    private BufferedImage img;
    
    
    public Drawer(Country country){
        super();
        init(country);
    }
    
    public Drawer(){
        this(new Country());
    }

    public void init(Country country) {
        this.country=country; 
        resizeCanvas();
        if(extents.x==0||extents.y==0)
            img=null;
        else
            img=new BufferedImage(scaledExtents.x,scaledExtents.y,BufferedImage.TYPE_3BYTE_BGR);
        gridColor=Color.GRAY.brighter();
        initStateGeoList();        
    }

    private void initStateGeoList() {
        geoList=NorthAmericanGeographicListFactory.create();
        geoList.scaleAll(DIAGRAM_PIXEL_SIZE);
        this.stateGeoMap=null;
        if(this.country==null)
            return;
        this.stateGeoMap=new StateGeographyMap(this.country.makeStateList(),this.geoList);
        this.stateGeoMap.recolorBasedOnCountryRegions(country);
    }
    
    private void drawCenteredText(String text,IntVector2 pos,Color color){
        FontMetrics fm=g.getFontMetrics();
        int halfWidth=fm.stringWidth(text)/2;
        int halfHeight=fm.getHeight()/2;
        g.setColor(color);
        g.drawString(text,pos.x-halfWidth,pos.y-halfHeight);
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img,0,0,null);
    }    
    
    // draw painting
    public void updatePaint(){
        this.g = img.createGraphics();
        drawStateMap();        
        this.g.dispose();        
        repaint();
    }    
    
    void drawStateMap(){
        drawBackground();
        drawGeographicShapes();
        drawStateNames();        
    }

    private void drawBackground() {
        Dimension dim=this.getPreferredSize();
        if(dim==null) 
            return;
        g.setColor(Color.WHITE);
        g.fillRect(0,0,dim.width,dim.height);
    }
    
    private void resizeCanvas() {
        calcExtents();        
        scaledExtents=IntVector2.scale(extents,DIAGRAM_PIXEL_SIZE);
        this.setPreferredSize(scaledExtents.x,scaledExtents.y);
        System.out.printf("Drawing canvas was resized to %s\n",extents.toString());
    }

    private void calcExtents() {
        extents=new IntVector2(0,0);
        if(country==null)
            return;
        geoMaxs=geoList.getMaxs();
        geoMins=geoList.getMins();
        int x=geoMaxs.x-geoMins.x;
        int y=geoMaxs.y-geoMins.y;
        int xPrime=(x>>SQUARE_SHIFT)<<SQUARE_SHIFT;
        int yPrime=(y>>SQUARE_SHIFT)<<SQUARE_SHIFT;
        if(xPrime<x) 
            xPrime+=SQUARE_SIZE;
        if(yPrime<y) 
            yPrime+=SQUARE_SIZE;
        if(xPrime>extents.x) 
            extents.x=xPrime;
        if(yPrime>extents.y) 
            extents.y=yPrime;            
    }
    
    public void drawOutlinedPolygon(Point2List points,Color fillColor,Color strokeColor,boolean shouldUseGeoMinOfs){
        int amtPoints=points.size();
        if(amtPoints==0)
            return;
        int[] xPoints=new int[amtPoints];
        int[] yPoints=new int[amtPoints];
        for(int i=0;i<amtPoints;i++){
            IntVector2 pt=points.get(i);
            xPoints[i]=pt.x;
            yPoints[i]=pt.y;
            if(shouldUseGeoMinOfs){
                xPoints[i]-=this.geoMins.x;
                yPoints[i]-=this.geoMins.y;
            }
        }
        Polygon p=new Polygon(xPoints,yPoints,amtPoints);        
        g.setColor(fillColor);
        g.fillPolygon(p);
        g.setColor(strokeColor);
        g.drawPolygon(p);
    }

    public void drawGeographicShapes() {
        boolean shouldUseGeoMinOfs=true;
        for(GeographicRegion gr:geoList){            
            drawOutlinedPolygon(gr.boundary,gr.fillColor,gr.strokeColor,shouldUseGeoMinOfs);
        }
    }
       
    private void drawStateNames() {
        
    }

    public void saveImage() {
        DateFormat df = new SimpleDateFormat("yyyy_MM_dd_yyyy_HH_mm_ss");
        Date today = Calendar.getInstance().getTime();        
        String dateStr = df.format(today);
        String filename=String.format("output/map_%s.png",dateStr);
        try {            
            ImageIO.write(img,"PNG",new File(filename));
            System.out.printf("Saved image to %s.\n",filename);
        } catch (java.io.IOException ex) {
            System.out.printf("IO Exception occured when trying to save image to %s.\n",filename);
        }
    }    

    private void setPreferredSize(int x, int y) {
        Dimension dim=new Dimension();
        dim.setSize(x,y);
        super.setPreferredSize(dim);
    }

}
