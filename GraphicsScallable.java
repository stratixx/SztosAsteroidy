package asteroidymodyfikacja;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anonymous
 */
public class GraphicsScallable extends Graphics {

    private Graphics graphics;
    private double scaleW;
    private double scaleH;
    
    GraphicsScallable( Graphics newGraphics, double newScaleW, double newScaleH  )
    {
        graphics = newGraphics;
        scaleW = newScaleW;
        scaleH = newScaleH;
    }
    
    @Override
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
        graphics.copyArea(x, y, width, height, dx, dy);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        graphics.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        graphics.fillRect(x, y, (int)(1000.0*width*scaleW)/1000, (int)(1000.0*height*scaleH)/1000);
    }

    @Override
    public void clearRect(int x, int y, int width, int height) {
        graphics.clearRect(x, y, width, height);
    }

    @Override
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        graphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        graphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        double widthS = width*scaleW;
        double heightS = height*scaleH;
        double xS = (widthS-width)/2 + x*scaleW;
        double yS = (heightS-height)/2 + y*scaleH;
        graphics.drawOval((int)xS, (int)yS, (int)widthS, (int)heightS);
    }

    @Override
    public void fillOval(int x, int y, int width, int height) {
        double widthS = width*scaleW;
        double heightS = height*scaleH;
        double xS = (widthS-width)/2 + x*scaleW;
        double yS = (heightS-height)/2 + y*scaleH;
        graphics.fillOval((int)xS, (int)yS, (int)widthS, (int)heightS);
    }

    @Override
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        graphics.drawArc(x, y, width, height, startAngle, arcAngle);
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        graphics.fillArc(x, y, width, height, startAngle, arcAngle);
    }

    @Override
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
        graphics.drawPolyline(xPoints, yPoints, nPoints);
    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        double xCenter = 0, yCenter = 0;
        double xCenterS = 0, yCenterS = 0;
        // Środek wielokąta
        for( int x:xPoints )
            xCenter += x;
        for( int y:yPoints )
            yCenter += y;
        xCenter /= xPoints.length;
        yCenter /= yPoints.length;
        xCenterS = xCenter*scaleW;
        yCenterS = yCenter*scaleH;
        
        // przesunięcie do zera, przeskalowanie i ponowne przesuniecie zwrotne
        for( int n=0; n<xPoints.length; n++)
        {
            xPoints[n] = (int)((xPoints[n]-xCenter)*scaleW+xCenterS);
            yPoints[n] = (int)((yPoints[n]-yCenter)*scaleH+yCenterS);
        }        
        
        graphics.drawPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        double xCenter = 0, yCenter = 0;
        double xCenterS = 0, yCenterS = 0;
        // Środek wielokąta
        for( int x:xPoints )
            xCenter += x;
        for( int y:yPoints )
            yCenter += y;
        xCenter /= xPoints.length;
        yCenter /= yPoints.length;
        xCenterS = xCenter*scaleW;
        yCenterS = yCenter*scaleH;
        
        // przesunięcie do zera, przeskalowanie i ponowne przesuniecie zwrotne
        for( int n=0; n<xPoints.length; n++)
        {
            xPoints[n] = (int)((xPoints[n]-xCenter)*scaleW+xCenterS);
            yPoints[n] = (int)((yPoints[n]-yCenter)*scaleH+yCenterS);
        }        
        
        graphics.fillPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public void drawString(String str, int x, int y) {
        x = (int)((1000.0*x*scaleW)/1000);
        y = (int)((1000.0*y*scaleH)/1000);
        graphics.drawString(str, x, y);
    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {
        x = (int)((1000.0*x*scaleW)/1000);
        y = (int)((1000.0*y*scaleH)/1000);
        graphics.drawString(iterator, x, y);
    }

    @Override
    public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
        return graphics.drawImage(img, x, y, observer);
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
        return graphics.drawImage(img, x, y, width, height, observer);
    }

    @Override
    public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
        return graphics.drawImage(img, x, y, bgcolor, observer);
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Graphics create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void translate(int x, int y) {
        graphics.translate(x, y);
    }

    @Override
    public Color getColor() {
        return graphics.getColor();
    }

    @Override
    public void setColor(Color c) {
        graphics.setColor(c);
    }

    @Override
    public void setPaintMode() {
        graphics.setPaintMode();
    }

    @Override
    public void setXORMode(Color c1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Font getFont() {
        return graphics.getFont();
    }

    @Override
    public void setFont(Font font) {
        font = font.deriveFont(  (float)(1000.0*font.getSize2D()*(scaleH*4.0/5.0+scaleW*1.0/5.0))/1000  );
        graphics.setFont(font);
    }

    @Override
    public FontMetrics getFontMetrics(Font f) {
        return graphics.getFontMetrics(f);
    }

    @Override
    public Rectangle getClipBounds() {
        return graphics.getClipBounds();
    }

    @Override
    public void clipRect(int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setClip(int x, int y, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Shape getClip() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setClip(Shape clip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
