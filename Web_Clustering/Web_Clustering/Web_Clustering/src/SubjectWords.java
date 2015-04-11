
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sravan
 */
public class SubjectWords {
    
    ArrayList<String> list = new ArrayList<String>();
    Stemmer ss = new Stemmer();
    Trimming t = new Trimming();
    public SubjectWords(String line)
    {
        StringTokenizer st = new StringTokenizer(line," ,;:-#([){]}!_/    ><*&");
        loop: while(st.hasMoreElements())
        {
            String s = (String) st.nextElement();
                                
                                String u = t.ReplaceStem(s);
                                if(u.equals(""))
                                {
                                    continue loop;
                                }
                                char[] wr = u.toCharArray();
                                for (int c = 0; c < wr.length; c++) 
                                {
                                    ss.add(wr[c]);
                                }
                                ss.stem();
                                {
                                    String u1;
                                    u1 = ss.toString();
                                    if(u1.compareTo("gt")==0)
                                    {
                                        continue loop;
                                    }
                                    list.add(u1);
                                }
                       
        }
    }
    
    public ArrayList<String> getSubjectWords()
    {
        return list;
    }
}
