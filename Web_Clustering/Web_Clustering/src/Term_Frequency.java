
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
public class Term_Frequency {
    
    HashMap<String, Integer> term = new HashMap<String, Integer>();
    public Term_Frequency(String word)
    {
        term.put(word, 1);
    }
    public void addTerm(String word)
    {
        term.put(word, 1);
    }
    public void updateMap(String word)
    {
        term.put(word, (term.get(word)+1));
    }
    public HashMap<String, Integer> getMap()
    {
        return term;
    }
    public boolean search(String s)
    {
        if(term.containsKey(s))
        {
            return true;
            
        }
        else
        {
            return false;
        }
    }
    
}
