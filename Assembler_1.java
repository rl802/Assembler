/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assembler_1;

/**
 *
 * @authors Sanjana, Rithika
 */
import java.util.StringTokenizer;  
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Assembler_1 {

    /**
     * @param args the command line arguments
     */
    tables t;
    void extract_assembly_code()
    {
       BufferedReader br = null;
		FileReader fr = null;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("");
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	

    }
    
    void evaluate_line(String in_line)
    {
        StringTokenizer st = new StringTokenizer(in_line," ");
        int count = st.countTokens();
        while(st.hasMoreTokens())
        {
            
        }
    }
    
    void pass_1() throws FileNotFoundException, IOException{
        int locctr=0;
        String[] str;
        // Open the file
FileInputStream fstream = new FileInputStream("textfile.txt");
BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

String strLine;

//Read File Line By Line
while ((strLine = br.readLine()) != null)   {
  // Split command into separate tokens
  str= strLine.split("\\W+");
  
  if(str[0].equals("START"))
            locctr=Integer.parseInt(str[1]);
        else{
            
        }
}

//Close the input stream
br.close();
        
    }
    
    void pass_2(){
        
    }
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
    }
    
}
