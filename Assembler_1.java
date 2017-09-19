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
        String hex_code=" ";
        int len;
        
        // Open the file
FileInputStream fstream = new FileInputStream("C:\\Users\\rithika\\Documents\\Assembler\\assembler_1\\textfile.txt");
BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
FileWriter fw=new FileWriter("C:\\Users\\rithika\\Documents\\Assembler\\assembler_1\\obj_code1.txt");
BufferedWriter bw=new BufferedWriter(fw);
String strLine;

//Read File Line By Line
while ((strLine = br.readLine()) != null)   {
  // Split command into separate tokens
  int index_bit=0;
  String instr=strLine;
  String[] str= strLine.split(",|\\s|\\r|\\t");
  len=str.length;
  /* Assigning addresses to each instruction */
  String hex_addr=" ";
  if(str[0]==null)
      continue;
  if(str[1].equals("START")){
      if(str[2]!=null && !str[2].isEmpty())
            locctr=Integer.parseInt(str[2],16)-3;
      continue;
  }
        else{
            if(str[1].equals("RESB"))
               locctr=locctr+Integer.parseInt(str[2]);
            else
                if(str[1].equals("RESW"))
                    locctr=locctr+(3*Integer.parseInt(str[2]));
            
            else
                if(str[1].equals("BYTE") && (str[2].charAt(0)=='X'||str[2].charAt(0)=='C'))
                   locctr=locctr+(str[2].length()-3);
                else
                    if(str[1].equals("BYTE") && !(str[2].charAt(0)=='X'||str[2].charAt(0)=='C'))
                        locctr++;
                else{
                    locctr=locctr+3;
                    }
            
        }
  hex_addr=Integer.toHexString(locctr);
  //System.out.println(hex_addr);
   if(str[1].equals("START")||str[0].equals("END"))
          continue;
  else
      if(len==4){
          t.create_symtab(str[0], hex_addr);
          index_bit=1;
      }
   else
      if(len==3 && !(str[2].equals("X")))
          t.create_symtab(str[0],hex_addr);  
   
  if(str[1].equals("START")||str[0].equals("END"))
      continue;
else
      if(str[1].equals("RESB")||str[1].equals("RESW")||str[1].equals("BYTE")||str[1].equals("WORD")){
          bw.write(hex_addr+"\t"+instr);
          bw.newLine();
      continue;
      }
  else
          if(len==4){
            hex_code=t.check_optab(str[1]);  
          }
        else      
      if(len==3 && str[2].equals("X")){
        index_bit=1;  
      hex_code=t.check_optab(str[0]);
      }
  else
          if(len==3 && !(str[2].equals("X"))){
          
      hex_code=t.check_optab(str[1]);
      }
  else
          if(len==2)
              hex_code=t.check_optab(str[0]);  
  
    bw.write(hex_addr+"\t"+instr+"\t"+hex_code+"\t"+index_bit);
    bw.newLine();
    }

//Close the input stream
br.close();
bw.close();     
 
    }
    
    void pass_2(){
        //t.print_symtab();
        BufferedReader br = null;
		FileReader fr = null;
                BufferedWriter bw = null;
		FileWriter fw = null;
                String padding="000000";

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("C:\\Users\\rithika\\Documents\\Assembler\\assembler_1\\obj_code1.txt");
			br = new BufferedReader(fr);
                        fw = new FileWriter("C:\\Users\\rithika\\Documents\\Assembler\\assembler_1\\obj_file.txt");
			bw = new BufferedWriter(fw);
                        
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
                            String[] str= sCurrentLine.split("\\t");
                            int len=str.length;
                              String obj_2="";
                              if(len==4){
                              str[2]=str[2]+"0000";
                              int hex=Integer.parseInt(str[2],16);
                              String[] instr_str=str[1].split(",|\\s");
                              int instr_len=instr_str.length;
                              if(instr_len==2)
                              {obj_2=t.check_symtab(instr_str[1]);
                              }
                              /* Check for indexed mode */
                              else
                                  if(instr_len==4)
                                      obj_2=t.check_symtab(instr_str[2]);
                              else
                                  if(instr_len==3 && instr_str[2].equals("X"))
                                obj_2=t.check_symtab(instr_str[1]);
                              /* Other cases */
                              else
                                if(instr_len==3 && !(instr_str[2].equals("X")))
                                {//System.out.println("You mad bruh");
                                obj_2=t.check_symtab(instr_str[2]);
                                }
                              int obj=Integer.parseInt(obj_2,16);
                              int address=hex+obj;
                              String obj_code=Integer.toHexString(address);
                              int ad_len=obj_code.length();
                              if(ad_len<6){
                                  obj_code=padding.substring(ad_len)+obj_code;
                                  //System.out.println(obj_code+"\n");
                              }
                                  
                              if(str[3].equals("0")){
                                  
                                  bw.write(str[0]+"\t"+str[1]+"\t"+obj_code+"\t");
                                  bw.newLine();
                              }
                              else{
                                  int fin_address=address+Integer.parseInt("8000",16);
                                  bw.write(str[0]+"\t"+str[1]+"\t"+Integer.toHexString(fin_address)+"\t");
                                  bw.newLine();
                              }
                            }
                              else{
                                bw.write(str[0]+"\t"+str[1]); 
                                bw.newLine();
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

