/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Konstantin
 */
public class TestClient {
    public static void main(String[] args)
    {
        
        Picture inputImg = new Picture("HJocean.png");
        System.out.printf("image is %d pixels wide by %d pixels high.\n", inputImg.width(), inputImg.height());
        
        SeamCarver sc = new SeamCarver(inputImg);
        
        System.out.printf("Printing energy calculated for each pixel.\n");        

        for (int j = 0; j < sc.height(); j++)
        {
            for (int i = 0; i < sc.width(); i++)
                System.out.printf("%9.0f ", sc.energy(i, j));

            System.out.println();
            
     
        }
        
     System.out.println("\n\n");
     System.out.println("------------------------");
            
     int[] findVertArray,findHorArray;
     findVertArray = sc.findVerticalSeam();
     findHorArray=sc.findHorizontalSeam();
     for(int i=0;i<findVertArray.length;i++)
     {
         System.out.println(""+findVertArray[i]);
     }
        System.out.println("----------------------------");
        System.out.println("\n\n");
        
        System.out.println("Horizontal Seam");
    for(int i=0;i<findHorArray.length;i++)
    {
        System.out.println(""+findHorArray[i]);
    }
        
    }
}
