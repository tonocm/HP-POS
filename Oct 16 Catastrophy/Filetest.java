import java.io.*;
import java.util.*;
class Wages
  /**
   * Antonio C‡rdenas
   * A01191911
   * March 26 2012
   * This set of programs will help us understand how writing and reading a file work. they do several functionalities
   **/
{
  public static void main (String [] args) throws IOException
  {
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter stdOut = new PrintWriter(System.out, true);
    File myFile = new File("/Users/To–o Cardenas/Dropbox/wages.txt");
    StringTokenizer tokenLine;
    PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter("temp.txt")));
    BufferedReader readFile = new BufferedReader(new FileReader(myFile));
    String line = readFile.readLine();
    int tempSalary, tempYears;
    
    while(line != null)
    {
      writeFile.println(line);
      
      line=readFile.readLine();
      
      tokenLine = new StringTokenizer(line);
      
      tempSalary=Integer.parseInt(tokenLine.nextToken());
      tempYears=Integer.parseInt(tokenLine.nextToken());
      
      tempYears=tempYears+1;
      
      if(tempYears < 10)
        tempSalary=tempSalary+15;
      else
        tempSalary=tempSalary+30;
      writeFile.print(tempSalary + " ");
      writeFile.print(tempYears);
      writeFile.println();
      
      line=readFile.readLine();
    }
    writeFile.close();
    readFile.close();
    File tempFile = new File("/Users/To–o Cardenas/Dropbox/temp.txt");
    
    myFile.delete();
    tempFile.renameTo(new File("wages.txt"));
  }
}