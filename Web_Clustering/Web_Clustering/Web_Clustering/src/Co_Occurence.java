
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sravan
 */
public class Co_Occurence {
    //initialzing Hash Map
    Map<String,Object> MyMap;
    //Constructor for the class
    public Co_Occurence(HashMap MyMap)
    {
        this.MyMap = MyMap;
    }
    
    public int calculate(String key1, String key2)
    {
        Wordclass a1 = (Wordclass)MyMap.get(key1);
        Wordclass a2 = (Wordclass)MyMap.get(key2);
        ArrayList<String> aa1 = a1.getThreadnumbers();
        ArrayList<String> aa2 = a2.getThreadnumbers();
        /*finding the intersection of the two array list which contains 
        Path/Names of the file which contains the words under Consideration
        */
        ArrayList<String> intersection = new ArrayList<String>(aa1);
        //Interesection is calculated
        intersection.retainAll(aa2);
        // intersection.size will be the value for edge on Relational Graph
        return intersection.size();
    }
    
    public float calculate_precision(ArrayList<String> aa1, ArrayList<String> aa2)
    {
        float precision = 0;
        for(int it =0;it<aa1.size();it++)
        {
            for(int ij = 0;ij<aa2.size();ij++)
            {
                if(aa1.get(it).equalsIgnoreCase(aa2.get(ij)))
                {
                    precision = precision + 1;
                }
            }
        }
        
        return (precision * 100/aa1.size());
    }
    
    public float calculate_roc(float precision, int size, int sents)
    {
        float roc;
        float sensitivity = precision/100;
        float temp = size - precision;
        float specificity_num  = sents - precision - (2* temp);
        float specificity = specificity_num/(temp + specificity_num);
        roc = sensitivity/specificity;
        return roc;
    }
}
