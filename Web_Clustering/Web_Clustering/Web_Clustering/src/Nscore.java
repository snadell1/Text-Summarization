

import java.util.HashMap;
import java.util.NavigableMap;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sravan
 */
public class Nscore {
    
    HashMap<String, Float> map = new HashMap<String, Float>();
    
    public HashMap<String, Float> getMap()
    {
        return map;
    }
    public float calculateGscore(int num)
    {
        int n = num;
        float score = 0;
        NavigableMap<Float, String> sortedmap = new TreeMap<Float, String>();
        for(String key: map.keySet())
        {
            sortedmap.put(map.get(key), key);
        }
        sortedmap = sortedmap.descendingMap();
        for(Float value: sortedmap.keySet())
        {
            if(n>0)
            {
                score = score + map.get(sortedmap.get(value));
            }
            n = n-1;
        }
        System.out.println(score);
        return score;
        
    }
    
    
}
