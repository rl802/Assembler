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
import java.io.PrintWriter;

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
        String hex_code;
        int len;
        int index_bit=0;
        // Open the file
FileInputStream fstream = new FileInputStream("textfile.txt");
BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
PrintWriter writer = new PrintWriter("obj_code.txt", "UTF-8");

String strLine;

//Read File Line By Line
while ((strLine = br.readLine()) != null)   {
  // Split command into separate tokens
  len=strLine.length();
  str= strLine.split(",|\\s");
  
  /* Assigning addresses to each instruction */
  
  if(str[1].equals("START")){
      if(str[1]!=null && !str[1].isEmpty())
            locctr=Integer.parseInt(str[1]);
  }
        else{
            if(str[1].equals("RESB"))
               locctr=locctr+Integer.parseInt(str[2]);
            else
                if(str[1].equals("RESW"))
                    locctr=locctr+(3*Integer.parseInt(str[2]));
            else
                if(str[1].equals("BYTE"))
                   locctr++;
            else
                locctr=locctr+3;
        }
  String hex_addr=Integer.toHexString(locctr);
  
  if(str[0].equals("START"))
      break;
  else
      hex_code=t.check_optab(str[0]);
  
  if(str[2].equals("X"))
      index_bit=1;
  
    
    writer.println("Address \t Instruction \t Opcode");
    writer.println(hex_addr+" \t"+strLine+" \t"+hex_code);
    writer.close();

  
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
