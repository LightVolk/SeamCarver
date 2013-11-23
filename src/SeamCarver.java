
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
     String _yieldX="yieldX",
             _yieldY="yieldY",
             _yieldTopCorner="yieldTopCorner",
             _yieldBottomCorner="yieldBottomCorner";
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
       
        if(SideCheck.IsPointAtUpperLeftCorner(x, y, this.width()-1, this.height()-1))
        {
            return GetEnergyYield(x, y, _yieldTopCorner);
        }
        else if(SideCheck.IsPointAtUpperRightCorner(x, y, this.width()-1, this.height()-1))
        {
        return GetEnergyYield(x, y, _yieldTopCorner);
        }
        else if(SideCheck.IsPointAtBottomLeftCorner(x, y, this.width()-1, this.height()-1))
        {
            return GetEnergyYield(x, y, _yieldBottomCorner);
        }
        else if(SideCheck.IsPointAtBottomRightCorner(x, y, this.width()-1, this.height()-1))
        {
            return GetEnergyYield(x, y, _yieldBottomCorner);
        }
        else if(SideCheck.IsPointAtLeftSide(x, y, this.width()-1, this.height()-1))
        {
            return GetEnergyYield(x, y, _yieldY);
        }
        else if(SideCheck.IsPointAtRightSide(x, y, this.width()-1, this.height()-1))
        {
            return GetEnergyYield(x, y, _yieldY);
        }
        else if(SideCheck.IsPointAtTopSide(x, y, this.width()-1, this.height()-1))
        {
            return GetEnergyYield(x, y, _yieldX);
        }
        else if(SideCheck.IsPointAtBottomSide(x, y, this.width()-1, this.height()-1))
        {
            return GetEnergyYield(x, y, _yieldX);
        }
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
        else if(mode.equals("yieldTopCorner"))
        {
            color1=_picture.get(x, y+1);
            int R1=color1.getRed();
            int B1=color1.getBlue();
            int G1=color1.getGreen();
            return R1*R1+B1*B1+G1*G1;
        }
        else if(mode.equals("yieldBottomCorner"))
        {
            color1=_picture.get(x, y-1);
            int R1=color1.getRed();
            int B1=color1.getBlue();
            int G1=color1.getGreen();
            return R1*R1+B1*B1+G1*G1;
        }
      
        int R1=color1.getRed();
        int B1=color1.getBlue();
        int G1=color1.getGreen();
        
        int R2=color2.getRed();
        int B2=color2.getBlue();
        int G2=color2.getGreen();
        
        int diffR=R1-R2;
        int diffB=B1-B2;
        int diffG=G1-G2;
        
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
