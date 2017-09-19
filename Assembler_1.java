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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;


public class Assembler_1 {

    /**
     * @param args the command line arguments
     */
    tables t=new tables();
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
String strLine;

//Read File Line By Line
while ((strLine = br.readLine()) != null)   {
  // Split command into separate tokens
  String instr=strLine;
  String[] str= strLine.split(",|\\s|\\r");
  len=str.length;
  /* Assigning addresses to each instruction */
  String hex_addr="";
  if(str[0]==null)
      continue;
  if(str[1].equals("START")){
      if(str[2]!=null && !str[2].isEmpty())
            locctr=Integer.parseInt(str[2])-3;
      continue;
  }
        else{
            if(str[1].equals("RESB"))
               locctr=locctr+Integer.parseInt(str[2]);
            else
                if(str[1].equals("RESW"))
                    locctr=locctr+(3*Integer.parseInt(str[2]));
            
            else
                if(str[1].equals("BYTE"))
                   locctr=locctr+1;
                    
                else{
                    locctr=locctr+3;
                    
                    
                }
            
        }
  hex_addr=Integer.toHexString(locctr);
  //System.out.println(hex_addr);
   if(str[1].equals("START")||str[0].equals("END"))
          continue;
  else
      if(len==3)
          t.create_symtab(str[0],hex_addr);
   
  if(str[1].equals("START")||str[0].equals("END")||str[1].equals("RESB")||str[1].equals("RESW")||str[1].equals("BYTE")||str[1].equals("WORD"))
      continue;
else
      if(len==3){
          
      hex_code=t.check_optab(str[1]);
      }
  else
          if(len==2)
              hex_code=t.check_optab(str[0]);
  
 
  
  if(len==3 && str[2].equals("X"))
      index_bit=1;
  
    bw.write(hex_addr+"\t"+instr+"\t"+hex_code+"\t"+index_bit);
    bw.newLine();
    }

//Close the input stream
br.close();
bw.close();     
 
    }
    
    void pass_2(){
        t.print_symtab();
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
                            StringTokenizer st = new StringTokenizer(sCurrentLine,"\t|\\s|\r");
                            int count = st.countTokens();
                              String tok_nm = st.nextToken();
                              
                              String tok_lb = st.nextToken();
                              String tok_hc=  st.nextToken();
                              String tok_ind= st.nextToken();
                              String obj_2="";
                              tok_hc=tok_hc+"0000";
                              int hex= Integer.parseInt(tok_hc,16);
                              String[] str=tok_lb.split("\\s");
                              int len=str.length;
                              if(len==2)
                              {obj_2=t.check_symtab(str[1]);
                              System.out.println(obj_2);}
                              else
                                obj_2=t.check_symtab(str[2]);  
                              int obj=Integer.parseInt(obj_2,16);
                              int address=hex+obj;
                              if(tok_ind.equals("0")){
                                  
                                  bw.write(tok_nm+"\t"+tok_lb+"\t"+Integer.toHexString(address)+"\t");
                              }
                              else{
                                  int fin_address=address+Integer.parseInt("8000",16);
                                  bw.write(tok_nm+"\t"+tok_lb+"\t"+Integer.toHexString(fin_address)+"\t");
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

