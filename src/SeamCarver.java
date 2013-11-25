
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
    private static Picture _picture;
    private static double[][] _eMatrix;
    public SeamCarver(Picture picture)
    {
        _picture=picture;
        _eMatrix=this.GetEnergyMatrix();
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
    
    
    
    // sequence of indices for horizontal seam in current picture
    public int[] findHorizontalSeam()
    {
        double[][] transopeMatrix=Transontire(_eMatrix);
        double[] Energy=new double[this.width()];
        int[] findArray=new int[_eMatrix.length];
        
        // find first pixel
        for(int i=0;i<transopeMatrix[0].length-1;i++)
        {
            Energy[i]=transopeMatrix[0][i];
        }
        //sort it and find minimum energy.        
        QuickSort.sort(Energy);
        
        int energyMinIndex=0;
        for(int i=0;i<transopeMatrix[0].length-1;i++)
        {
            if(Energy[0]==transopeMatrix[i][0])
            {
                energyMinIndex=i;
                findArray[0]=i;
                break;
            }
        }
       
        int minWidthIndex=0;
        for(int i=1;i<this.height()-1;i++)
        {
            
            double[] tmp=new double[3];
            int[] tmpIndex=new int[3];
            if(i==1)
                minWidthIndex=energyMinIndex;
            int k=0;
            for(int j=minWidthIndex-1;j<=minWidthIndex+1;j++)
            {
                
                tmp[k]=transopeMatrix[j][i];;
                tmpIndex[k]=j;
                k++;
            }
            int index=this.GetMin(tmp,tmpIndex);
            findArray[i]=index;
        }
        return findArray;
       
    
    }
   
    private int GetMin(double[] arr,int[] tmpIndex)
    {
           double res;
           int index;
           double a=arr[0],b=arr[1],c=arr[2];
           if(a < b && a < c){
	         res = a;
                 index=tmpIndex[0];
	         }else if(c < b && c < a){
                     {
                         res = c;
                         index=tmpIndex[2];
                     }
	         }else if(b < c && b < a){
                     {res = b;index=tmpIndex[1];}
	         }else{
	        	 res = 0;
                         index=tmpIndex[0];
	        	 System.out.println("Use different numbers!");
	         }
	         return index;
    }
    
    
    
    // sequence of indices for vertical   seam in current picture
    public int[] findVerticalSeam()
    {
        double[] Energy=new double[this.width()];
        int[] findArray=new int[this.height()];
        
        // find first pixel
        for(int i=0;i<this.width();i++)
        {
            Energy[i]=this.energy(i, 0);
        }
        //sort it and find minimum energy.        
        QuickSort.sort(Energy);
        
        int energyMinIndex=0;
        for(int i=0;i<this.width()-1;i++)
        {
            if(Energy[0]==this.energy(i, 0))
            {
                energyMinIndex=i;
                findArray[0]=i;
                break;
            }
        }
       
        int minWidthIndex=0;
        for(int i=1;i<this.height()-1;i++)
        {
            
            double[] tmp=new double[3];
            int[] tmpIndex=new int[3];
            if(i==1)
                minWidthIndex=energyMinIndex;
            int k=0;
            for(int j=minWidthIndex-1;j<=minWidthIndex+1;j++)
            {
                
                tmp[k]=this.energy(j, i);
                tmpIndex[k]=j;
                k++;
            }
            int index=this.GetMin(tmp,tmpIndex);
            findArray[i]=index;
        }
        return findArray;
    
    }
    
    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] a)
    {
    Picture newPicture = new Picture(this._picture.width() - 1, this._picture.height());
                for(int i = 0; i < this._picture.width(); i++)
                {
                        for(int j = 0; j < this._picture.height(); j++)
                        {
                                try
                                {
                                        if(i < a[j])
                                                newPicture.set(i, j, this._picture.get(i, j));
                                        else if(i > a[j])
                                                newPicture.set(i - 1, j, this._picture.get(i, j));
                                }
                                catch(Exception ex)
                                {
                                        throw new IllegalArgumentException("Bad argument! i =  " + i + " j = " + j + " a[j] = " + a[j]);
                                }
                        }
                }
                
                this._eMatrix = GetEnergyMatrix();
                this._picture = newPicture;
    }
    
    // remove vertical   seam from current picture
    public void removeVerticalSeam(int[] a)
    {
       Picture vertPicture=new Picture(this._picture.width(),this._picture.height()-1); 
       for(int i=0;i<this._picture.width();i++)
       {
           for(int j=0;j<this._picture.height()-1;j++)
           {
               if(j<=a[i])
                   vertPicture.set(i,j,this._picture.get(j, j));
               else if(j>a[i])
                   vertPicture.set(i,j-1,this._picture.get(i, j));
           }
       }
       this._picture=vertPicture;
       
     
    }
    
    
    private double[][] GetEnergyMatrix()
    {
        
         double[][] eMatrix=new double[_picture.width()][_picture.height()];
         
         for(int i=0;i<_picture.width()-1;i++)
             for(int j=0;j<_picture.height()-1;j++)
             {
                 eMatrix[i][j]=this.energy(i, j);
             }
         
         return eMatrix;
        
    }
    private double[][] Transontire(double[][] eMatrix)
    {
        double[][] result = new double[eMatrix[0].length][eMatrix.length];
                for(int i = 0; i < eMatrix.length; i++)
                {
                        for(int j = 0; j < eMatrix[i].length; j++)
                                result[j][i] = eMatrix[i][j];
                }
        
         return result;
    
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
    
}
