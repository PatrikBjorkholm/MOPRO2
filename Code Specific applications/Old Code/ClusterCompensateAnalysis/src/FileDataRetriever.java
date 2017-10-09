import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FileDataRetriever {
	
	
	public String [] Analysis (String Path) throws IOException {
		//---------------------------------------------------------------
		//hr hmtas information till grabber
		//hr lses info frn fil
    	String Line;
//        String s[]= new String[23800];

		
    	//ppnar upp filen
    	BufferedReader in = new BufferedReader(new FileReader(Path)); //Supplementary_data_file2.txt Test_set_a core_desc1_a core_desc2_a_2
    	
    	//deklarerar variabler
        
        int m=0;

        //hr lses information frn filen in till string array
        while (true){
            Line = in.readLine();
            if (Line==null){
                break;
            }

//            s[m] = Line;
            if(Line.length() > 0)
            {
            	m=m+1;
            }
        }
		
        String s[] = new String [m];
        m = 0;

        BufferedReader tin = new BufferedReader(new FileReader(Path));
        
        while (true){
            Line = tin.readLine();
            if (Line==null){
                break;
            }

            if(Line.length() > 0)
            {
            	s[m] = Line;
            	m=m+1;
            }
        }
        
        
        return s;
	}
}


