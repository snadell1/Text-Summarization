
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 *
 * @author Sravan
 */
public class Wordclass {
    //initialize the Occurences
     double NumberOfTimes=0;
     //initialize the boolean for checking convergence
     boolean convergence = false;
     // Initialize the list for storing the file names in which the word appeared
     ArrayList<String> thread_numbers = new ArrayList<String>();
     HashMap<String, Double> term_f = new HashMap<String, Double>();
     //constructor initializing the occurence and adding the name of the file
     Wordclass(String thread_num)
     {
         NumberOfTimes = NumberOfTimes + 1;
         thread_numbers.add(thread_num);
         term_f.put(thread_num, 1.0);
     }
     //update function which updates the occurence variable each time the word occured 
     public void updateNumber()
     {
         NumberOfTimes = NumberOfTimes + 1;
         
     }
     //update function which add/updates the filename 
    public void UpdateArray(String num)
    {
        if(thread_numbers.contains(num))
        {
           term_f.put(num, (term_f.get(num)+1));
        }
        else
        {
            thread_numbers.add(num);
            term_f.put(num,1.0);
        }
    }
    //retrives the occurences.
    public double getNumber()
    {
        return NumberOfTimes;
    }
    
    //retrieves the list of filenames in which the word occured
    public ArrayList<String> getThreadnumbers()
    {
        return thread_numbers;
    }
    //converts the occurences of a word to its initial Term Rank
    public void changeNumber(double num )
    {
        NumberOfTimes = NumberOfTimes/num;
        NumberOfTimes = Double.parseDouble(new DecimalFormat("##.####").format(NumberOfTimes));
    }
    //changes the boolean of the convergence to true when the difference in term rank between successive iterations is converged.
    public void changeConvergence()
    {
        convergence = true;
    }
    //retrieves the convergence 
    public boolean getConvergence()
    {
        return convergence;
    }
}
