
public class AnalyzeDataFile {
	
	private int NumberRun = 0;
	private String SendMotifs = "";
	private String Under = "";
	private String Over = "";
	
	public int [] Start (int aa, String MotifData [], String ResultData [], int [] total, int nRun, 
			String FileName)
	{
//		boolean SignificantMotif [] = new boolean [MotifData.length];
		
		String UnAnalyzedMotifs = "";
		String OverRepresentedMotifs = "";
		String UnderRepresentedMotifs = "";
		int numRun = 0;
		int numberSigPlusMinus[] = new int[2];
		for(int a = 0; a < MotifData.length; a++)
		{
			if(a  < ResultData.length)
			{
				
				numRun = numRun + 1;
				ExtractResultDataFromLine ERDFL = new ExtractResultDataFromLine();
				String TempMotif =ERDFL.Parse(ResultData[a]);
				double Z_value = ERDFL.returnZ();
				
				if(MotifData[a].equals(TempMotif))
				{
					if(Z_value > 5.0)
					{
						numberSigPlusMinus[0] = numberSigPlusMinus[0] + 1; 
						OverRepresentedMotifs = OverRepresentedMotifs + MotifData[a]+"\n"; 
						
					}
					else if(Z_value < -5.0)
					{
						numberSigPlusMinus[1] = numberSigPlusMinus[1] + 1;
						UnderRepresentedMotifs = UnderRepresentedMotifs + MotifData[a]+"\n";
					}
				}
			}
			else
			{
				UnAnalyzedMotifs = UnAnalyzedMotifs + MotifData[a] + "\n";
			}
		}
//
		SendMotifs = UnAnalyzedMotifs;
		NumberRun = numRun;
		Under = UnderRepresentedMotifs;
		Over = OverRepresentedMotifs;
		
		
		System.out.println(FileName +"\t"+ aa +"\t:\t" + MotifData.length + "\t" + numRun+ "\t"+ (nRun+numRun) +"\t:\t"+
		numberSigPlusMinus[0]+ "\t" + numberSigPlusMinus[1]
				+"\t" + (numberSigPlusMinus[0] + total[0])+"\t" + (numberSigPlusMinus[1] + total[1]));
		return numberSigPlusMinus;
	}
	
	
	public int SendRun()
	{
		return NumberRun;
	}
	
	public String SendUnrunMotifs()
	{
		return SendMotifs;
	}
	
	public String SendUnder()
	{
		return Under;
	}
	
	public String SendOver()
	{
		return Over;
	}
	
//	System.out.println(aa +"\t" + MotifData.length + "\t" + numberSigPlusMinus[0]+ "\t" + numberSigPlusMinus[1]);
//	 Motif	P-value Z-value 		MT	  MR	MPL	MPN	MPH
//	AAAAA	0.0	16.653599732040952	1000	0	0	64
//	AAAAR	0.813	-0.6735257496201834	187	304	509	1

}
