/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart_inventory;
import com.mathworks.engine.MatlabEngine;
import com.mathworks.matlab.types.HandleObject;

/**
 *
 * @author MAHE
 */
public class Matlab_Graphs {
     public static void main(String[] args) 
    {
        //int height[]=new int{63, 64, 66, 69, 69, 71, 71, 72, 73, 75};
        //double height[]=new double[]{63, 64, 66, 69, 69, 71, 71, 72, 73, 75};
        //double weight[]=new double[]{127, 121, 142, 157, 162, 156, 169, 165, 181, 208};
        //double dt[][]={{63,127},{64,121},{66,142},{69,157},{69,162},{71,156},{71,169},{72,165},{73,181},{75,208}};
        double testx[]=new double[]{8,2,11,6,5,4,12,9,6,1};
        double testy[]=new double[]{3,10,3,6,8,12,1,4,9,14};
        double test2x[]=new double[testx.length];
        double m=-1.1;
        double c=14;
        //test2x=m*testx;
        for(int i=0;i<testx.length;i++)
        {
            test2x[i]=m*testx[i];
            //System.out.print(test2x[i]+" ");
        }            
        double test2y[]=new double[testy.length];
        for(int i=0;i<testy.length;i++)
        {
            test2y[i]=test2x[i]+c;
            //System.out.print(test2);
        }
        try
        {            
            MatlabEngine eng = MatlabEngine.startMatlab();
            //double[][] data = {{1.0, 2.0, 3.0}, {-2.0, -4.0, -6.0}};
            //int future = eng.sqrt(4.0,async=True);
            //eng.fevalAsync("fitlm",(Object)height,(Object)weight);            
            eng.feval("plot",(Object) testx,(Object) testy, "*",(Object)testx,(Object)test2y);
            //eng.eval("hold on");
            //eng.feval("plot",(Object)testx,(Object)test2y);            
            //eng.feval("plot", (Object) h);
            //String lw = ("LineWidth");
            //eng.feval(0, "set", h, lw.toCharArray(), 2.0);            
            //eng.feval("plot")
            eng.eval("uiwait");
            eng.close();
            System.out.println("pls work bro");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            //System.out.println("Exception Occured.");
        }
    }
}
