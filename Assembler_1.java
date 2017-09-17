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
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Assembler_1 {

    /**
     * @param args the command line arguments
     */
    tables t;
    converter c;
    
    
void pass_1() throws FileNotFoundException, IOException{
        int locctr=0;
        String hex_code="";
        int len;
        int index_bit=0;
        // Open the file
FileInputStream fstream = new FileInputStream("C:\\Users\\rithika\\Documents\\Assembler\\assembler_1\\textfile.txt");
BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
FileWriter fw=new FileWriter("C:\\Users\\rithika\\Documents\\Assembler\\assembler_1\\obj_code1.txt");
BufferedWriter bw=new BufferedWriter(fw);
bw.write("Address Instruction Opcode");
String strLine;

//Read File Line By Line
while ((strLine = br.readLine()) != null)   {
  // Split command into separate tokens
  len=strLine.length();
  String[] str= strLine.split(",|\\s");
  
  /* Assigning addresses to each instruction */
  
  if(str[1].equals("START")){
      if(str[2]!=null && !str[2].isEmpty())
            locctr=Integer.parseInt(str[2]);
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
  
  if(str[1].equals("START")||str[0].equals("END"))
      break;
else
      hex_code=t.check_optab(str[0]);
  
  if(str[2].equals("X"))
      index_bit=1;
  
    
    
    bw.write(hex_addr+" "+strLine+" "+hex_code);
    

  
}

//Close the input stream
br.close();
bw.close();     
    
    }
    
    void pass_2(){
        BufferedReader br = null;
		FileReader fr = null;
                BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("C:\\Users\\rithika\\Documents\\Assembler\\assembler_1\\obj_code1.txt");
			br = new BufferedReader(fr);
                        fw = new FileWriter("C:\\Users\\rithika\\Documents\\Assembler\\assembler_1\\obj_file.txt");
			bw = new BufferedWriter(fw);
                        
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
                            StringTokenizer st = new StringTokenizer(sCurrentLine," ");
                            int count = st.countTokens();
                              String tok_nm = st.nextToken();
                              String tok_lb = st.nextToken();
                              System.out.println(tok_lb);
                              String obj_2="";
                              obj_2=t.check_symtab(tok_lb);
                               if("".equals(obj_2))
                               {
                                   bw.write(sCurrentLine);
                               }
                               if(st.hasMoreTokens())
                               {
                               String tok_x = st.nextToken();
                               if("1".equals(tok_x))
                               {
                                   obj_2=c.addhex("8000",obj_2);
                               }
                                bw.write(tok_nm+obj_2);
                               }
                               
                            //System.out.println(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();
                                if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
    }
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Assembler_1 a=new Assembler_1();
        a.pass_1();
        a.pass_2();
    }

    
    
}

