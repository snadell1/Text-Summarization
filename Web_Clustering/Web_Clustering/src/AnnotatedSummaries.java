
import java.util.ArrayList;
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
public class AnnotatedSummaries {
    HashMap<String, Object> SummaryMap = new HashMap<String, Object>();
    ArrayList<String> summary1 = new ArrayList<String>();
    ArrayList<String> summary2 = new ArrayList<String>();
    ArrayList<String> summary3 = new ArrayList<String>();
    
    public void addSummary1Sentence(String sentence)
    {
        summary1.add(sentence);
    }
    
    public void addSummary2Sentence(String sentence)
    {
        summary2.add(sentence);
    }
    
    public void addSummary3Sentence(String sentence)
    {
        summary3.add(sentence);
    }
    
    
    
    public ArrayList<String> getSummary1Sentences()
    {
        return summary1;
    }
    public ArrayList<String> getSummary2Sentences()
    {
        return summary2;
        
    }
    
    public ArrayList<String> getSummary3Sentences()
    {
        return summary3;
        
    }
    public int getSummary1SentencesSize()
    {
        return summary1.size();
    }
    
    public int getSummary2SentencesSize()
    {
        return summary2.size();
    }
    
    public int getSummary3SentencesSize()
    {
        return summary3.size();
    }
    
    
}
