 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
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
                   Sentence_Rank.put(rank_value, s3);
                   rank.put(s3, rank_value);
               }
               else
               {
                   if(rank.get(s3)<rank_value)
                   {
                       Sentence_Rank.remove(rank.get(s3));
                       Sentence_Rank.put(rank_value, s3);
                   rank.put(s3, rank_value);
                   }
               }
            }
            else
         {
             Sentence_Rank.put(rank_value, line);
             rank.put(line, rank_value);
         }
     }
     public ArrayList<String> getRankedSentences(int n)
     {
         Sentence_Rank = Sentence_Rank.descendingMap();
         System.out.println(Sentence_Rank.size());
         ArrayList<String> ranked_Senteces = new ArrayList<String>();
         int n1=0;
         
         for(int key: Sentence_Rank.keySet())
         {
             if(n1<n)
             {
                 ranked_Senteces.add(Sentence_Rank.get(key));
                 n1=n1+1;
             }
         }
         return ranked_Senteces;
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
