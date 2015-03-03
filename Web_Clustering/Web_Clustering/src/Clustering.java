
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.regex.Pattern;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;


/**
 *
 * @author Sravan
 */
public class Clustering{  
    // Getting files into an array from specified Directory
        
        
        //initiaziling the Map for storing words along with its occurences and path of files in which it occured
        static Map<String,Object> MyMap = new HashMap<String, Object>();
        // Sentence map is the hashmap where the thread and object for storing the threads as sentences are defined
        static Map<String, Object> sentence_map = new HashMap<String, Object>();
        static HashMap<String, Object> annotation_map = new HashMap<String , Object>();
        static HashMap<String, Object> thread_term = new HashMap<String, Object>();
        static HashMap<String, Integer> annotation_sentences = new HashMap<String, Integer>();
        static HashMap<String, Object> sentenceToWordMap = new HashMap<String, Object>();
        static Removalstops r = new Removalstops();
        static Trimming t = new Trimming();
        static Stemmer ss = new Stemmer();
        //sentences is the arraylist where every sentence from the corpus is stored
        static ArrayList<String> sentences = new ArrayList<String>();
        static int[][] Co_Occurence;
        static int[] sum;
        //keys is the arraylist which stores each and every word from the corpus after pre-processing
        static ArrayList<String> keys = new ArrayList<String>();
        static Co_Occurence c = new Co_Occurence((HashMap) MyMap);
        
    public static void main(String args[]) throws FileNotFoundException, IOException, InterruptedException, ParserConfigurationException, SAXException
    { 
        // Getting files into an array from specified Directory
        
        double sum_of_occurences = 0;
        
        int count = 0;
        try {
		File fXmlFile = new File("C:/Users/Sravan/Documents/NetBeansProjects/JavaApplication15/corpus.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
 
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("thread");
                NodeList sentencesList = doc.getElementsByTagName("Sent");
                NodeList thread_id = null;
                
                for (int temp = 0; temp < nList.getLength(); temp++) 
		{
                    Node nNode = nList.item(temp);
                    String text ="";
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                    {
                        Element eElement = (Element) nNode;
                        NodeList senList = eElement.getElementsByTagName("Sent");
                        
                        thread_id = eElement.getElementsByTagName("listno");
                        NodeList TextList = eElement.getElementsByTagName("Text");
			sentence_map.put(thread_id.item(0).getTextContent(), new Sentences());
			Sentences sn = (Sentences) sentence_map.get(thread_id.item(0).getTextContent());
                        HashMap<String, String> tempt = new HashMap<String, String>();
                        for(int it=0;it<senList.getLength();it++)
                        {
                            
                            tempt.put(senList.item(it).getAttributes().getNamedItem("id").getNodeValue(), senList.item(it).getTextContent());
                            sn.addSentences(senList.item(it).getTextContent());
                            sentences.add(senList.item(it).getTextContent());
                            sentenceToWordMap.put(senList.item(it).getTextContent(), new WordSentence());
                            WordSentence ws = (WordSentence) sentenceToWordMap.get(senList.item(it).getTextContent());
                            String line =senList.item(it).getTextContent();
                            line = line.toLowerCase();
                            StringTokenizer st = new StringTokenizer(line," ,;:-#([){]}!_/    ><*&");
                            loop:    while (st.hasMoreElements())
                            {
                                String s = (String) st.nextElement();
                                if(s == "gt"|| s == "&gt;")
                                {
                                    continue loop;
                                }
                                if(r.removings(s)==1)
                                {
                                    continue loop;
                                }
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
                                    ws.addWord(u1);
                                    sum_of_occurences = sum_of_occurences + 1;
                                    if(!MyMap.containsKey(u1))
                                    {
                                        MyMap.put(u1, new Wordclass(thread_id.item(0).getTextContent()));
                                        keys.add(u1);
                                    }
                                    else
                                    {
                                        Wordclass a = (Wordclass)MyMap.get(u1);
                                        a.updateNumber();
                                        a.UpdateArray(thread_id.item(0).getTextContent());
                                    }
                   
                                }
                            }
                        }
                        sn.addSendNodes(tempt);
                        
                    }
                }
        } 
        catch (Exception e) 
	{
            e.printStackTrace();
        }
      
        double [] Term_rank = new double[MyMap.size()];
        for(int i=0;i<keys.size();i++)
        {
            Wordclass a = (Wordclass)MyMap.get(keys.get(i));
            a.changeNumber(sum_of_occurences);
            Term_rank[i] = a.getNumber();
        }
        
         Co_Occurence = new int[MyMap.size()][MyMap.size()];
        //Assigning values for Relational Graphs
        
        
        for(int i=0;i<(keys.size());i++)
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
        
        sum = new int[keys.size()];
        for(int i=0;i<keys.size();i++)
        {
            for(int j=0;j<keys.size();j++)
            {
                sum[i] = sum[i] + Co_Occurence[i][j];
            }
        }
        //Term Rank formula Iterations and Convergence
        double cv = 0.001;
        int convergence_count;
        double [] tem_term_rank = new double[keys.size()];
        double[] tem_difference_matrix = new double[keys.size()];
        int sec_count=0;
        NavigableMap<Double,String> sortedMap = new TreeMap<Double, String>();
        do
       {
          convergence_count = 0;
          
          sec_count =0;
          loop5:  for (int i=0;i<keys.size();i++)
        {
            
            if(!((Wordclass)MyMap.get(keys.get(i))).getConvergence())
            {
                sec_count++;
               
            
            
            loop6:for(int j=0;j<keys.size();j++)
            {
                if(Co_Occurence[i][j]!=0)
                {
                   
                
                
                
                
                tem_term_rank[i] = tem_term_rank[i] + ((Term_rank[j] * Co_Occurence[i][j])/sum[j]);
                }
                
                
            }
            tem_difference_matrix[i] = tem_term_rank[i] - Term_rank[i];
            
            tem_difference_matrix[i]= Double.parseDouble(new DecimalFormat("##.####").format(tem_difference_matrix[i]));
            Term_rank[i] = tem_term_rank[i];
            if(tem_difference_matrix[i]<= cv)
            {
                convergence_count = convergence_count + 1;
                
                
                ((Wordclass)MyMap.get(keys.get(i))).changeConvergence();
                // putting the converged values into a TREE MAP
                sortedMap.put(Term_rank[i],keys.get(i));
                
            }
            tem_term_rank[i] = 0.0;
            tem_difference_matrix[i] = 0.0;
            
            
        }
          
        }
       }
       
       while(convergence_count!=(sec_count));
        // Sorting the Tree Map so that the words are stored in sorted order
        sortedMap = sortedMap.descendingMap();
        Map<String, Integer> Term_Rank = new HashMap<String, Integer>();
        int rank = sortedMap.size();
        for(Double key:sortedMap.keySet())
        {
            //System.out.println("Rank of "+sortedMap.get(key)+" is "+key+"");
            Term_Rank.put(sortedMap.get(key), (rank+1));
        }
        
        
     
        for(String key: sentence_map.keySet())
        {
            
            Sentences snn = (Sentences) sentence_map.get(key);
            ArrayList<String>  temp_arr = snn.getSentences();
            
            
            for(int j=0;j<temp_arr.size();j++)
            {
                
                String line = temp_arr.get(j);
                WordSentence ws = (WordSentence) sentenceToWordMap.get(line);
                int line_rank = 0;
                
                HashMap<String, Integer> hm = ws.getMap();
                for(String key1: hm.keySet())
                {
                   line_rank = line_rank + Term_Rank.get(key1);
                }
                
                
                snn.addSentenceRank(line,line_rank);
                    
                
            }
        }
        //Sentences test = (Sentences)sentence_map.get("067-11978590");
        //ArrayList<String> test_arr = test.getRankedSentences(20);
        //for(int t=0;t<test_arr.size();t++)
        //{
         //   System.out.println(test_arr.get(t));
        //}
        //System.out.println(test_arr.size());
        
        HashMap<String, Object> annotatedSummaryMap = new HashMap<String, Object>();
        
        int s= 0;
        try 
        {
            
            File anotXmlFile = new File("C:/Users/Sravan/Documents/NetBeansProjects/JavaApplication15/annotation.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document annotationdoc = dBuilder.parse(anotXmlFile);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work

            annotationdoc.getDocumentElement().normalize();
            NodeList annotationList = annotationdoc.getElementsByTagName("thread");
            for (int temp = 0; temp < annotationList.getLength(); temp++) 
            {
                Node annotationNode = annotationList.item(temp);
                if (annotationNode.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element annotationElement = (Element) annotationNode;
                    NodeList annotationthread_id = annotationElement.getElementsByTagName("listno");
                    annotation_map.put(annotationthread_id.item(0).getTextContent(), new Sentences());
                    Sentences ssn = (Sentences) annotation_map.get(annotationthread_id.item(0).getTextContent());
                    Sentences sn = (Sentences) sentence_map.get(annotationthread_id.item(0).getTextContent());
                    HashMap<String, String> tempNode = sn.getSendNodes();
                    NodeList sentencesListInAnnot = annotationElement.getElementsByTagName("sentences");
                    annotatedSummaryMap.put(annotationthread_id.item(0).getTextContent(), new AnnotatedSummaries());
                    AnnotatedSummaries as = (AnnotatedSummaries) annotatedSummaryMap.get(annotationthread_id.item(0).getTextContent());
                    
                    NodeList Summary1 = sentencesListInAnnot.item(0).getChildNodes();
                    for(int temp2=0;temp2<Summary1.getLength();temp2++)
                    {
                        try
                        {
                            String id = Summary1.item(temp2).getAttributes().getNamedItem("id").getNodeValue();
                            if(tempNode.containsKey(id))
                            {
                               
                                ssn.addAnnotationSentence(tempNode.get(id));
                                as.addSummary1Sentence(tempNode.get(id));
                            }
                        }
                        catch(Exception e)
                        {
                            
                        }
                    }
                    
                    NodeList Summary2 = sentencesListInAnnot.item(1).getChildNodes();
                    for(int temp2=0;temp2<Summary2.getLength();temp2++)
                    {
                        try
                        {
                            String id = Summary2.item(temp2).getAttributes().getNamedItem("id").getNodeValue();
                            if(tempNode.containsKey(id))
                            {
                               
                                ssn.addAnnotationSentence(tempNode.get(id));
                                as.addSummary2Sentence(tempNode.get(id));
                            }
                        }
                        catch(Exception e)
                        {
                            
                        }
                    }
                    
                    NodeList Summary3 = sentencesListInAnnot.item(2).getChildNodes();
                    for(int temp2=0;temp2<Summary3.getLength();temp2++)
                    {
                        try
                        {
                            String id = Summary3.item(temp2).getAttributes().getNamedItem("id").getNodeValue();
                            if(tempNode.containsKey(id))
                            {
                               
                                ssn.addAnnotationSentence(tempNode.get(id));
                                as.addSummary3Sentence(tempNode.get(id));
                            }
                        }
                        catch(Exception e)
                        {
                            
                        }
                    }
                    
                    
                    
                }
            }
        }
        catch (Exception e) 
        { 
            e.printStackTrace();
        }
        
        HashMap<String, Double> averageNScoreValueAnnotation = new HashMap<String, Double>();
        for(String key: annotatedSummaryMap.keySet())
        {
            Sentences sn = (Sentences) annotation_map.get(key);
            double sum1 = 0;
            double sum2 =0;
            double sum3 = 0;
            AnnotatedSummaries as = (AnnotatedSummaries) annotatedSummaryMap.get(key);
            ArrayList<String> summary1Sentences = as.getSummary1Sentences();
            ArrayList<String> summary2Sentences = as.getSummary2Sentences();
            ArrayList<String> summary3Sentences = as.getSummary3Sentences();
            HashMap<String, Integer> sentenceMap = sn.getAnnotationNScoreMap();
            
            for(int temp7 = 0;temp7<summary1Sentences.size();temp7++)
            {
                
                sum1 = sum1 + (double) sentenceMap.get(summary1Sentences.get(temp7))/3;
            }
            
            for(int temp7 = 0;temp7<summary2Sentences.size();temp7++)
            {
                sum2 = sum2 + (double) sentenceMap.get(summary2Sentences.get(temp7))/3;
            }
            
            for(int temp7 = 0;temp7<summary3Sentences.size();temp7++)
            {
                sum3 = sum3 + (double) sentenceMap.get(summary3Sentences.get(temp7))/3;
            }
            double value = (double) ((sum1+sum2+sum3)/3);
            averageNScoreValueAnnotation.put(key, value);
            
            
        }
        
        
        /* for(String key: averageNScoreValueAnnotation.keySet())
        {
            System.out.println("Value is "+ averageNScoreValueAnnotation.get(key));
        }*/
        
        HashMap<String, Double> extractedSummaryNScore = new HashMap<String, Double>();
        for(String key: sentence_map.keySet())
        {
            Sentences sn = (Sentences) sentence_map.get(key);
            Sentences sn1 = (Sentences) annotation_map.get(key);
            HashMap<String, Integer> sentenceMap = sn1.getAnnotationNScoreMap();
            AnnotatedSummaries as = (AnnotatedSummaries) annotatedSummaryMap.get(key);
            int summary1Size = as.getSummary1SentencesSize();
            int summary2Size = as.getSummary2SentencesSize();
            int summary3Size = as.getSummary3SentencesSize();
            int size = summary1Size + summary2Size + summary3Size;
            size = size/3;
            double value = 0;
            ArrayList<String> rankedSentences = sn.getRankedSentences(size);
            for(int i=0;i<rankedSentences.size();i++)
            {
                if(sentenceMap.containsKey(rankedSentences.get(i)))
                {
                value = value + (double)sentenceMap.get(rankedSentences.get(i))/3;
                }
            }
            extractedSummaryNScore.put(key, value);
            
        }
        int recallcount = 0;
        for(String key: extractedSummaryNScore.keySet())
        {
            System.out.println("my recall" + extractedSummaryNScore.get(key));
            System.out.println("Annotation recall" + averageNScoreValueAnnotation.get(key));
            if(extractedSummaryNScore.get(key)> averageNScoreValueAnnotation.get(key))
            {
                recallcount = recallcount + 1;
            }
        }
         
        System.out.println(recallcount);
        
        
        }
}