 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Sravan
 */
public class Sentences {
     ArrayList<String> sentences = new ArrayList<String>();
     NavigableMap<Integer, String> Sentence_Rank = new TreeMap<Integer, String>();
     NavigableMap<Integer, String> annotationSentenceNScoreOrdered = new TreeMap<Integer, String>();
     HashMap<String, Integer> rank = new HashMap<String, Integer>();
     HashMap<String, String> nList = new HashMap<String, String>();
     HashMap<String, Object> sentenceToWordMap = new HashMap<String, Object>();
     HashMap<String, Integer> annotationSentenceNScore = new HashMap<String, Integer>();
     public void addSentences(String sentence)
     {
         sentences.add(sentence);
     }
     public double getNScoreOfSentence(String sentence)
     {
         
         return (annotationSentenceNScore.get(sentence)/3);
     }
     /*public int getOrderedAnnotationSentenceSize()
     {
         return annotationSentenceNScore.size();
     }*/
     public HashMap<String, Integer> getAnnotationNScoreMap()
     {
         
         return annotationSentenceNScore;
     }
      /* public void orderAnnotationSentenceNScores()
     {
         for(String key: annotationSentenceNScore.keySet())
         {
             annotationSentenceNScoreOrdered.put(annotationSentenceNScore.get(key), key);
         }
         annotationSentenceNScoreOrdered = annotationSentenceNScoreOrdered.descendingMap();
         System.out.println(annotationSentenceNScoreOrdered.size());
     }*/
     public int getAnnotationSentenceSize()
     {
         return annotationSentenceNScore.size();
     }
     public void addAnnotationSentence(String sentence)
     {
         
         if(annotationSentenceNScore.containsKey(sentence))
         {
             annotationSentenceNScore.put(sentence, (annotationSentenceNScore.get(sentence) + 1));
         }
         else
         {
             annotationSentenceNScore.put(sentence, 1);
         }
     }
     
     public ArrayList<String> getSentences()
     {
         return sentences;
     }
     public int getSentencesSize()
     {
         return sentences.size();
     }
     public void addSentenceRank(String line, int rank_value)
     {
         
         
         if((line.startsWith("&gt; ")))
            {
               String s3 = line.replace("&gt; ", "");
               s3 = s3.replace("&gt;", "");
               if(!rank.containsKey(s3))
               {
                   //Sentence_Rank.put(rank_value, s3);
                   //rank.put(s3, rank_value);
                   
                   
                   
                   
                   
                   
               }
               /*else
               {
                   if(rank.get(s3)<rank_value)
                   {
                       Sentence_Rank.remove(rank.get(s3));
                       Sentence_Rank.put(rank_value, s3);
                   rank.put(s3, rank_value);
                   }
               }*/
            }
            else
         {
             Sentence_Rank.put(rank_value, line);
             rank.put(line, rank_value);
         }
     }
     private static HashMap sortByValues(HashMap map) { 
       List list = new LinkedList(map.entrySet());
       // Defined Custom Comparator here
       Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o1)).getValue())
                  .compareTo(((Map.Entry) (o2)).getValue());
            }
       });

       // Here I am copying the sorted list in HashMap
       // using LinkedHashMap to preserve the insertion order
       HashMap sortedHashMap = new LinkedHashMap();
       for (Iterator it = list.iterator(); it.hasNext();) {
              Map.Entry entry = (Map.Entry) it.next();
              sortedHashMap.put(entry.getKey(), entry.getValue());
       } 
       
       /*for(Object key: map.keySet())
       {
           System.out.println(map.get(key));
       }
       System.out.println("--------------------------------------------------");*/
       return sortedHashMap;
  }
     
     
     public ArrayList<String> getRankedSentences(String key, int n)
     {
         /*HashMap<String, Integer> SentenceRank = sortByValues(rank);
         ArrayList<String> ranked_Senteces = new ArrayList<String>();
         int n1= SentenceRank.size();
         int count = 0;
         for(String key: SentenceRank.keySet())
         {
             if(n1>0 && count<n)
             {
                 ranked_Senteces.add(key);
                 n1=n1-1;
                 count++;
             }
         }
         return ranked_Senteces;
                 */
       
         ArrayList<String> ranked_Sentences = new ArrayList<String>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/thesisproject";
        String user = "thesis";
        String password = "sravan786";
         
         
                   try {
            con = DriverManager.getConnection(url, user, password);
            String query = "SELECT sentencestring FROM sentencerank where thread = ? ORDER BY rank DESC LIMIT ? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, key);
            ps.setInt(2, n);
            rs = ps.executeQuery();
            while(rs.next()) {
               
                ranked_Sentences.add(rs.getString("sentencestring"));
            }
            

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
         
        
         
         return ranked_Sentences;
         
     }
     public void addSendNodes(HashMap<String, String> temp)
     {
         for(String key: temp.keySet())
         {
             nList.put(key, temp.get(key));
         }
     }
     public HashMap<String, String> getSendNodes()
     {
         return nList;
     }
}
