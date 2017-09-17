/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assembler_1;

/**
 *
 * @authors Rithika, Sanjana
 */
public class converter {
    
    String dectohex(int decNumber)
    {
    int remainder,quotient;
    int i=1,j,temp;
    String hexadecimalNumber="";

    quotient = decNumber;

    while(quotient!=0){
         temp = quotient % 16;

      //To convert integer into character
      if( temp < 10)
           temp =temp + 48;
      else
         temp = temp + 55;

      hexadecimalNumber= hexadecimalNumber+Integer.toString(temp);
      quotient = quotient / 16;
  }

    //System.out.println("Equivalent hexadecimal value of decimal number "+ decNumber);
    for(j = i -1 ;j> 0;j--)
      System.out.println(hexadecimalNumber);

    return hexadecimalNumber;
    }
    
    int hextodec(String hexdecnum)
    {
    
        int decnum;
        String digits = "0123456789ABCDEF";
             hexdecnum = hexdecnum.toUpperCase();
             int val = 0;
             for (int i = 0; i < hexdecnum.length(); i++)
             {
                 char c = hexdecnum.charAt(i);
                 int d = digits.indexOf(c);
                 val = 16*val + d;
             }
		decnum=val;
        return decnum;
    
    }
    
    String addhex(String num1,String num2)
    {
        int n1=hextodec(num1);
        int n2=hextodec(num2);
        int sum =n1+n2;
        String net_hex=dectohex(sum);
        return net_hex;
    }
}

