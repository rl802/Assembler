/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package assembler_1;
import java.util.*;
/**
 *
 * @authors Sanjana, Rithika
 */
public class tables {
     Map<String,String> optab;
     Map<String,String> symtab;
    tables(){
        Map<String,String> optab = new HashMap<String,String>();
         optab.put("ADD","18");
        optab.put("AND","40");
        optab.put("COMP","28");
        optab.put("DIV","24");
        optab.put("J","3C");
        optab.put("JEQ","30");
        optab.put("JGT","34");
        optab.put("JLT","38");
        optab.put("JSUB","48");
        optab.put("LDA","00");
        optab.put("LDCH","50");
        optab.put("LDL","08");
        optab.put("LDX","04");
        optab.put("MUL","20");
        optab.put("OR","44");
        optab.put("RD","D8");
        optab.put("RSUB","4C");
        optab.put("STA","0C");
        optab.put("STCH","54");
        optab.put("STL","14");
        optab.put("STSW","E8");
        optab.put("STX","10");
        optab.put("SUB","1C");
        optab.put("TD","E0");
        optab.put("TIX","2C");
        optab.put("WD","DC");
     Map<String,String> symtab= new HashMap<>();   
    }
    
        
    void create_symtab(String symbol, String addr){
        
        int flag=0;
        for (String sym : symtab.keySet()){
            if(sym.equalsIgnoreCase(symbol)){
                flag=1;
                break;
            }
        }
            if(flag==0)
            symtab.put(symbol,addr);    
        
    }
    
    String check_optab(String mnemon){
        String addr=new String();
        if(optab.containsKey(mnemon)){
           addr=optab.get(mnemon);
           
        }
        else 
            System.out.println("Mnemonic doesn't exist. Please check the SIC manul again");
        return addr;
    }
    
    String check_symtab(String sym){
        String addr=new String();
        if(optab.containsKey(sym)){
           addr=optab.get(sym);
        }
        return addr;
    }
}
