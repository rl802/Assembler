/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assembler_1;

/**
 *
 * @author Sanjana13
 */
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Assembler_1 {

    /**
     * @param args the command line arguments
     */
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
    
    void evaluate_line()
    {
        
    }
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
    }
    
}
