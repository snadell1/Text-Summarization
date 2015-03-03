
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sravan
 */
public class Threadd extends Clustering implements Runnable {
    
    
    public void run()
    {
        for(int i=keys.size()/2;i<(keys.size()-1);i++)
        {
            loop1: for(int j=1;j<keys.size();j++)
            {
                if(i<j)
                {
                    
                
                Co_Occurence[i][j] = c.calculate(keys.get(i),keys.get(j));
                Co_Occurence[j][i] = Co_Occurence[i][j];
                }
                
            }
        }
    }
}
