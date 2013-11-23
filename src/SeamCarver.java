
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Konstantin
 */
public class SeamCarver {
    
    public static Picture _picture;
    
    public SeamCarver(Picture picture)
    {
        _picture=picture;
    }
    
    // current picture
    public Picture picture()
    {
        return _picture;
    
    }
    // width  of current picture
    public int width()
    {
        return _picture.width();
    }
    // height of current picture
    public int height()
    {
        return _picture.height();
    }
    // energy of pixel at column x and row y in current picture
    public double energy(int x,int y)
    {
        return GetEnergyYield(x, y, "yieldX")+GetEnergyYield(x, y, "yieldY");
    }
    private static double GetEnergyYield(int x,int y,String mode)
    {
        Color color1 = null,color2 = null;
        if(mode.equals("yieldX"))
        {
        color1=_picture.get(x+1,y);
        color2=_picture.get(x-1, y);
        }
        else if(mode.equals("yieldY"))
        {
        color1=_picture.get(x, y+1);
        color2=_picture.get(x, y-1);
        
        }
      
        int R1=color1.getRed();
        int B1=color1.getBlue();
        int Gx1=color1.getGreen();
        
        int R2=color2.getRed();
        int B2=color2.getBlue();
        int G2=color2.getGreen();
        
        int diffR=R1-R2;
        int diffB=B1-B2;
        int diffG=Gx1-G2;
        
        return (double)(diffR*diffR+diffB*diffB+diffG*diffG);
    }
    
    
    // sequence of indices for horizontal seam in current picture
    public int[] findHorizontalSeam()
    {
        return null;
    
    }
    // sequence of indices for vertical   seam in current picture
    public int[] findVerticalSeam()
    {
        return null;
    
    }
    
    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] a)
    {
    
    }
    
    // remove vertical   seam from current picture
    public void removeVerticalSeam(int[] a)
    {
    
    }
}
