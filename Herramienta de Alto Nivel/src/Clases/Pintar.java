/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.MONOSPACED;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author fredy_000
 */
public class Pintar {   
    Color color;
     
   
    public Pintar(){
        
        
    }
    
   public static void pintarCirculo(Graphics g,int x,int y, String name){
        //g.drawOval(x, y-10, 20, 20);
       ((Graphics2D)g).setColor(Color.LIGHT_GRAY);
        ((Graphics2D)g).setStroke(new BasicStroke(2));//leda el grosor al circulo        
        ((Graphics2D)g).fillOval(x, y, 10, 10);        
        ((Graphics2D)g).setColor(Color.BLACK);
        ((Graphics2D)g).drawOval(x, y, 10, 10);
        
        ((Graphics2D)g).setColor(Color.black);
        Font fuente=new Font("Monospaced",Font.BOLD, 14);
        g.setFont(fuente);
        ((Graphics2D)g).drawString(name, x+30, y + 8);
         
    }    
  
  public static void pintarLinea(Graphics g, int x1,int y1,int x2,int y2){
        int xAux = 0; int yAux = 0; 
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(2);
        ((Graphics2D)g).setStroke(stroke);         
       ((Graphics2D)g).drawLine(x1+6, y1+10, x2+6, y2);
       if(x1<=x2)
           xAux=((x2-x1)/2)+x1;       
        if(x1>x2)
           xAux=((x1-x2)/2)+x2;
        if(y1<y2)
           yAux=((y2-y1)/2)+y1;
        if(y1>=y2)
            yAux=((y1-y2)/2)+y2;        
        // ((Graphics2D)g).setColor(Color.black);
        Font fuente=new Font("Monospaced",Font.PLAIN, 12);
        g.setFont(fuente);
      ((Graphics2D)g).drawString("", xAux, yAux);
  }   
  public static void pintarCamino(Graphics g, int x1,int y1,int x2,int y2, Color color){
      ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(2);
        ((Graphics2D)g).setStroke(stroke);
        g.setColor(color);
        g.drawLine(x1+10, y1+10, x2+10, y2+10);
        //g.drawString(String.valueOf(tam), x1, y1);
  }
   public static void clickSobreNodo(Graphics g,int x,int y,String n,Color co){
        //g.drawOval(x, y-10, 20, 20);
       ((Graphics2D)g).setColor(co);
        ((Graphics2D)g).setStroke(new BasicStroke(4));//leda el grosor al circulo        
        ((Graphics2D)g).fillOval(x, y, 15, 15);        
        ((Graphics2D)g).setColor(Color.BLACK);
        ((Graphics2D)g).drawOval(x, y, 15, 15);
         
    }
}
