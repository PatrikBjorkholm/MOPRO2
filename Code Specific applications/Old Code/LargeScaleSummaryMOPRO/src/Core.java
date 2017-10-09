import java.io.IOException;


public class Core {

	public static void main(String[] args)  throws IOException 
	{
		// TODO Auto-generated method stub

		
		
//		String ListOfMotifFiles = "5thLevelpartsaaaaaa";
		
		FileDataRetriever FDR = new FileDataRetriever();
		String ListOfMotifFiles[] = FDR.Analysis("Lists/ListOf5thMotifFiles.txt");
		
		int MotifFilesPassed = 0;
		int MotifFilesFailed = 0;
		int LostFiles = 0;
		
		String ListOfAllExceptions [] = {};
//			{"5thLevelpartsafaaav","5thLevelpartsafaaau","5thLevelpartsafaaat","5thLevelpartsafaaas","5thLevelpartsafaaar","5thLevelpartsafaaaq","5thLevelpartsafaaap","5thLevelpartsafaaao","5thLevelpartsafaaan","5thLevelpartsafaaam","5thLevelpartsafaaal","5thLevelpartsafaaak","5thLevelpartsafaaaj","5thLevelpartsafaaai","5thLevelpartsafaaah","5thLevelpartsafaaag","5thLevelpartsafaaaf","5thLevelpartsafaaae","5thLevelpartsafaaad","5thLevelpartsafaaac","5thLevelpartsafaaab","5thLevelpartsafaaaa","5thLevelpartsadagah","5thLevelpartsadagag","5thLevelpartsadagaf","5thLevelpartsadagae","5thLevelpartsadagad","5thLevelpartsadagac","5thLevelpartsadagab","5thLevelpartsadagaa","5thLevelpartsadafhr","5thLevelpartsadafhq","5thLevelpartsadafhp","5thLevelpartsadafhg", 
//				"5thLevelpartsadaeeh","5thLevelpartsadaeeg","5thLevelpartsadaeef","5thLevelpartsadaeee","5thLevelpartsadaeed",
//				"5thLevelpartsadaeec","5thLevelpartsadaeeb","5thLevelpartsadaeea","5thLevelpartsadaedz","5thLevelpartsadaedy","5thLevelpartsadaedx",
//				"5thLevelpartsadaedw","5thLevelpartsadaedv","5thLevelpartsadaedu","5thLevelpartsadaedt","5thLevelpartsadaagl",
//				"5thLevelpartsadaagk","5thLevelpartsadaagj","5thLevelpartsadaagi", "5thLevelpartsadaagh","5thLevelpartsadaagg",
//				"5thLevelpartsadaagf", "5thLevelpartsadaage","5thLevelpartsadaafx","5thLevelpartsadaafy","5thLevelpartsadaagd","5thLevelpartsadaagc", 
//				"5thLevelpartsadaagb","5thLevelpartsadaafz", "5thLevelpartsadaaga"};//{"5thLevelpartsaaaaeg"};
		
		int totalPlusMinus [] = new int [2];
		int TotalNumberAnalyzed = 0;
		
		String LostMotifs = "";
		String OverMotifs = "";
		String UnderMotifs = "";
		
		
		
		for(int a = 0; a < ListOfMotifFiles.length; a++)
		{
			
			boolean MissingFilesControl = false;
			for(int b = 0; b < ListOfAllExceptions.length; b++)
			{
				if(ListOfMotifFiles[a].equals(ListOfAllExceptions[b]))
				{
					LostFiles = LostFiles + 1;
					MissingFilesControl = true;
					break;
				}
			}
			if(MissingFilesControl == false)
			{
			String MotifData[] = FDR.Analysis("Data/MotifFiles/5thLevelMotif/thirdLayer/"+ListOfMotifFiles[a]);
			String ResultData[] = FDR.Analysis("Results/Golgi/" + ListOfMotifFiles[a]+ "/Result.txt"); //FDR.Analysis("Results/All/" + ListOfMotifFiles[a]+ "/Result.txt");
			boolean qualityControl = false;
			if((MotifData.length) == (ResultData.length))
			{
				qualityControl = true;
				MotifFilesPassed = MotifFilesPassed + 1;
			}
			else
			{
				MotifFilesFailed = MotifFilesFailed + 1;
			}
//			System.out.println(MotifData.length);
			
			if(qualityControl == false)
			{
//				System.out.println(a + " " + MotifData.length + "\t" +wwResultData.length);
			}
			AnalyzeDataFile ADF = new AnalyzeDataFile();
			
			
			int tempPlusMinus[] = ADF.Start(a, MotifData, ResultData, totalPlusMinus, TotalNumberAnalyzed, ListOfMotifFiles[a]);
			totalPlusMinus[0] = totalPlusMinus[0] +  tempPlusMinus[0];
			totalPlusMinus[1] = totalPlusMinus[1] +  tempPlusMinus[1];
			TotalNumberAnalyzed = TotalNumberAnalyzed + ADF.SendRun();
			LostMotifs = LostMotifs + ADF.SendUnrunMotifs();
			OverMotifs = OverMotifs + ADF.SendOver();
			UnderMotifs = UnderMotifs + ADF.SendUnder();
			
		}
		}
		System.out.println("Summary");
		System.out.println("Number Succeded MotifFiles = " +MotifFilesPassed); 
		System.out.println("Number Failed MotifFiles = " +MotifFilesFailed);
		System.out.println("Number Lost MotifFiles = " +LostFiles);
		
		PrintFile PF = new PrintFile();
		PF.printer("GolgiLostBla.txt", LostMotifs);
		PF.printer("GolgiOverReco.txt", OverMotifs);
		PF.printer("GolgiUnderReco.txt", UnderMotifs);
	}

}
