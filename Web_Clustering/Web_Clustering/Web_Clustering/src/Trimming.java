/**
 *
 * @author Sravan
 */

class Trimming
{
  public String ReplaceStem(String word)
  {
    if (word.toLowerCase().endsWith("."))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().endsWith(":"))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().endsWith(","))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().endsWith(":"))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().endsWith(";"))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().endsWith("?"))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().endsWith("'"))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().endsWith("\""))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().endsWith(")"))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().endsWith("("))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().endsWith("]"))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().endsWith("}"))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().endsWith("["))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().endsWith("{"))
      return word.replace(word.trim(), word.substring(0, word.length() - 1));
    if (word.toLowerCase().startsWith("["))
      return word.replace(word.trim(), word.substring(1));
    if (word.toLowerCase().startsWith("("))
      return word.replace(word.trim(), word.substring(1));
    if (word.toLowerCase().startsWith("{"))
      return word.replace(word.trim(), word.substring(1));
    if(word.toLowerCase().startsWith("*"))
    
        
        return word.replace(word.trim(),word.substring(2));
    
        
    if (word.toLowerCase().startsWith("'"))
      return word.replace(word.trim(), word.substring(1));
    if (word.toLowerCase().startsWith("\""))
      return word.replace(word.trim(), word.substring(1));
    if (word.toLowerCase().startsWith("."))
      return word.replace(word.trim(), word.substring(1));
    if (word.toLowerCase().startsWith(":"))
      return word.replace(word.trim(), word.substring(1));
    if (word.toLowerCase().startsWith(","))
      return word.replace(word.trim(), word.substring(1));
    if (word.toLowerCase().startsWith(";")) {
      return word.replace(word.trim(), word.substring(1));
    }
    return word.trim();
  }
}
