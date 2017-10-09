import java.io.IOException;


public class center {

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws IOException {
		// TODO Auto-generated method stub
		String MotifValue = args[0];
		FileDataRetriever FDR = new FileDataRetriever(); 
		
		int RandomizationDepht = 1000;
		
		String Motif[] = FDR.Analysis(MotifValue);
//		String Motif[] = {"GXXXG","CXXXXXXXP"};
		RetrieveSequenceData RSD = new RetrieveSequenceData(); 
		RSD.GetSequences(args[1]);
		String [] TMs = RSD.returnTM();
		int numberTM = RSD.SendTm();
		ShuffleArray SH = new ShuffleArray();
		String RandomTMArray [][] = new String[numberTM][10000];
		
		int PrintType = 0; // 0 = short, 1 = Long
		
		
		for(int a = 0; a < numberTM; a++)
		{
			for(int b = 0; b < RandomizationDepht; b++)
			{
				RandomTMArray [a][b] = SH.main(TMs[a]);
			}
		}
		MotifExtractor ME = new MotifExtractor();
		NonRandomMotifs NRM = new NonRandomMotifs();
		
		System.out.println("No" + "\t" + "Motif" + "\t" + "MT" + "\t" + "MR" + "\t" + "MPL" + "\t" + "MPN" + "\t" + "MPH");// + "\t" + "CT" + "\t" + "CR" + "\t" +  "CPL" + "\t" + "CPN" + "\t" + "CPH"); //MammalianPvalueArray;
		for(int a = 0; a < Motif.length; a++)
		{
			int TotalNumberMotifs = 0;
			String Palette = ME.MotifParsefier(Motif[a]);
			int [] PalettePositionArray = ME.ReturnPPA();
			int MammalianTrueNumberMotifs = NRM.NonRandomizedMotifCounter(TMs, Palette, PalettePositionArray, Motif[a].length(),numberTM);
//			
			String TempRandomArray[] = new String [numberTM];
			
			int Array[] = new int[RandomizationDepht]; 
			
			int MoreRandom = 0;
			int EqualRandom = 0;
			int LessRandom = 0;
			
			for(int b = 0; b < RandomizationDepht; b++)
			{
				int tempRandomNumberMotifs = 0;
//				Transfers a row of total random array onto a single-dimensional array 
				
				for(int c = 0; c < numberTM; c++)
				{
					TempRandomArray[c] = RandomTMArray[c][b];
				}
				
				Array[b] =  NRM.NonRandomizedMotifCounter(TempRandomArray, Palette, PalettePositionArray, Motif[a].length(),numberTM);
				tempRandomNumberMotifs =  Array[b]  +  tempRandomNumberMotifs;
				
				
				
				if(tempRandomNumberMotifs == MammalianTrueNumberMotifs)
				{
					EqualRandom = EqualRandom + 1;
				}
				else if(tempRandomNumberMotifs < MammalianTrueNumberMotifs)
				{
					LessRandom = LessRandom + 1;
				}
				else
				{
					MoreRandom = MoreRandom + 1;
				}
			}
			
			
			
//			Claculating z-values
			
			double averageRandom = 0;
			
			for(int c = 0; c < Array.length; c++)
			{
				double r1 = Array[c];
				averageRandom = averageRandom + r1;
			}
			
			
			
			double tmpe = Array.length;
			averageRandom = (averageRandom/tmpe); 
			double Variance = 0.0;
			for(int c = 0; c < Array.length; c++)
			{
				double r1 = Array[c];
				Variance = Variance + Math.abs((averageRandom-r1));
			}

			double t1 = EqualRandom;
			double t2 = MoreRandom;
//			double 
			
			Variance = (Variance/tmpe);
			double stDev = Math.sqrt(Variance);
			double tmp = MammalianTrueNumberMotifs;
			double z = ((tmp-averageRandom)/stDev);
			double P_value = (t1+t2)/tmpe;
			
			if(PrintType == 0)
			{
			
				System.out.println(Motif[a] + "\t" + P_value + "\t" + z + "\t" + LessRandom + "\t" + EqualRandom + "\t" + MoreRandom + "\t" + MammalianTrueNumberMotifs);
			}
		}
	
	}

}
