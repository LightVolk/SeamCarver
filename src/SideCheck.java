/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Konstantin
 */
public class SideCheck {

    public static boolean IsPointAtLeftSide(int xkoord,int ykoord,int Xmax,int Ymax)
    {
        return (xkoord==0);
    }
    
    public static boolean  IsPointAtRightSide(int xkoord,int ykoord,int Xmax,int Ymax)
    {
     return (xkoord==Xmax);
    }
    
    public static boolean IsPointAtBottomSide(int xkoord,int ykoord,int Xmax,int Ymax)
    {return ykoord==Ymax;}
    
    public static boolean IsPointAtTopSide(int xkoord,int ykoord,int Xmax,int Ymax)
    {return ykoord==0;}
    
    public static boolean  IsPointAtUpperLeftCorner(int xkoord,int ykoord,int Xmax,int Ymax)
    {
        return (IsPointAtLeftSide(xkoord, ykoord, Xmax, Ymax)&&IsPointAtTopSide(xkoord, ykoord, Xmax, Ymax));
    }
    
    public static boolean  IsPointAtBottomLeftCorner(int xkoord,int ykoord,int Xmax,int Ymax)
    {
        return (IsPointAtLeftSide(xkoord, ykoord, Xmax, Ymax)&&IsPointAtBottomSide(xkoord, ykoord, Xmax, Ymax));
    }
    
    public static boolean IsPointAtUpperRightCorner(int xkoord,int ykoord,int Xmax,int Ymax)
    {
        return (IsPointAtTopSide(xkoord, ykoord, Xmax, Ymax)&&IsPointAtRightSide(xkoord, ykoord, Xmax, Ymax));
    }
    
    public static boolean IsPointAtBottomRightCorner(int xkoord,int ykoord,int Xmax,int Ymax)
    {
        return (IsPointAtBottomSide(xkoord, ykoord, Xmax, Ymax)&&(IsPointAtRightSide(xkoord, ykoord, Xmax, Ymax)));
    }   
}
