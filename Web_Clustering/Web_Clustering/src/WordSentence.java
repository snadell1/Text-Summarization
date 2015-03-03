
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
public class WordSentence {
    
    HashMap<String, Integer> wsMap = new HashMap<String, Integer>();
    
    public WordSentence(String word)
    {
        
        wsMap.put(word, 1);
    }
    public WordSentence()
    {
        
    }
    public HashMap<String, Integer> getMap()
    {
        return wsMap;
    }
    public void addWord(String word)
    {
        if(!wsMap.containsKey(word))
             {
                 wsMap.put(word, 1);
             }
             else
             {
                 wsMap.put(word, (wsMap.get(word)+1));
             }
        
    }
    
}
