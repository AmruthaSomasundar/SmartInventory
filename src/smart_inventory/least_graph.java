/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart_inventory;

import com.mathworks.engine.MatlabEngine;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MAHE
 */
public class least_graph {
     int getrowcount(ResultSet rs1)
    {
        int size=0;
        try {
            rs1.last();
            size=rs1.getRow();
            rs1.beforeFirst();
            
            
        } catch (Exception e) {
        }
       
        return size; 
    }
    public  double slope;
    public  double yintercept;
    public  double testx[];
    public  double testy[];
    public  void getslope() 
    {
        try {
            DB_Connect conn= new DB_Connect();
            Statement stmt= conn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlq="SELECT DISTINCT(WORKING_HRS),SALARY from employee";
            ResultSet rs=stmt.executeQuery(sqlq);
            int k=getrowcount(rs);
            testx=new double[k];/// get from database
            testy=new double[k];/// get from database
            int s=0;
            while (rs.next())
            {
                testx[s]=rs.getDouble("WORKING_HRS");
                testy[s]=rs.getDouble("SALARY");
                s++;
            }
            for(int index=0; index<k; index++)
            {
                System.out.print(testx[index]+"\t");
                System.out.print(testy[index]+"\n");
                
            }
            conn.con.close();
            double sumx=0;
            double sumy=0;
            for(int i=0;i<testx.length;i++)
            {
                sumx=sumx+testx[i];
                sumy=sumy+testy[i];
            }   double meanx=sumx/testx.length;
            double meany=sumy/testy.length;
            double xdev[]=new double[testx.length],ydev[]=new double[testy.length];
            for(int i=0;i<testx.length;i++)
            {
                xdev[i]=testx[i]-meanx;
                ydev[i]=testy[i]-meany;
            }   double multdev[]=new double[testx.length];
            double xdevsquare[]=new double[testx.length];
            for(int i=0;i<testx.length;i++)
            {
                multdev[i]=xdev[i]*ydev[i];
                xdevsquare[i]=xdev[i]*xdev[i];
            }   double sumxydev=0;
            double sumxdevsquare=0;
            for(int i=0;i<testx.length;i++)
            {
                sumxydev=sumxydev+multdev[i];
                sumxdevsquare=sumxdevsquare+xdevsquare[i];
            }   
            slope=sumxydev/sumxdevsquare;
            System.out.println(slope);
            
            yintercept=meany-slope*meanx;
            System.out.println(yintercept);
        } catch (SQLException ex) {
            Logger.getLogger(Leastsquare.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public  void start1() 
    {
        //int height[]=new int{63, 64, 66, 69, 69, 71, 71, 72, 73, 75};
        //double height[]=new double[]{63, 64, 66, 69, 69, 71, 71, 72, 73, 75};
        //double weight[]=new double[]{127, 121, 142, 157, 162, 156, 169, 165, 181, 208};
        //double dt[][]={{63,127},{64,121},{66,142},{69,157},{69,162},{71,156},{71,169},{72,165},{73,181},{75,208}};
       // double testx[]=new double[]{8,2,11,6,5,4,12,9,6,1};
        //double testy[]=new double[]{3,10,3,6,8,12,1,4,9,14};
        getslope();
        System.out.println(yintercept);
        System.out.println(slope);
        double test2x[]=new double[testx.length];
        double m=slope;
        double c=yintercept;
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
            String xl="Working hours";
            String yl="Salary";
            //double[][] data = {{1.0, 2.0, 3.0}, {-2.0, -4.0, -6.0}};
            //int future = eng.sqrt(4.0,async=True);
            //eng.fevalAsync("fitlm",(Object)height,(Object)weight); 
            
             eng.feval("plot",(Object) testx,(Object) testy, "*",(Object)testx,(Object)test2y);
             eng.feval("xlabel", (Object)xl);
            eng.feval("ylabel", (Object)yl);
//            eng.feval("plot",(Object) testx,(Object) testy, "*");
//            eng.eval("hold on");
//            
//            eng.feval("plot",(Object)testx,(Object)test2y);  
            eng.feval("xlabel", (Object)xl);
            eng.feval("ylabel", (Object)yl);
            //eng.feval("plot", (Object) h);
            //String lw = ("LineWidth");
            //eng.feval(0, "set", h, lw.toCharArray(), 2.0);            
            //eng.feval("plot")
            eng.eval("uiwait");
            eng.close();
            //System.out.println("pls work bro");
        }
        catch(Exception e)
        {
            System.out.println("");
            //e.printStackTrace();
            //System.out.println("Exception Occured.");
        }
        

}
}