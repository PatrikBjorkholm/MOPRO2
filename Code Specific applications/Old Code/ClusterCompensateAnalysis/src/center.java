import java.io.IOException;


public class center {

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		FileDataRetriever FDA = new FileDataRetriever();
		String ListOfAlreadyAnalyzed [] = FDA.Analysis("test.tx");
		
		for(int a = 0; a < 10; a++)
		{
			System.out.println("wc -l /home/patrikb/glob/MOPRO/Results/All/" + ListOfAlreadyAnalyzed[a] +" >> OutfilesAll.txt");
		}
		
	}

}
