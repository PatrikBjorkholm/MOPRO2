import java.io.IOException;


public class RetrieveSequenceData {

	private int numberTMS = 0;
	private String [] TMs;
	
	public void GetSequences(String Path) throws IOException 
	{
		FileDataRetriever FDR = new FileDataRetriever();
		String RawData[] = FDR.Analysis(Path);
		int SsideCharge [] = new int[RawData.length];
		String SecondNearSide [] = new String[RawData.length];
		String SequenceString [] = new String[RawData.length];
		String StartSequenceModification [] = new String[RawData.length];
		String AfterChargeModifications []  = new String[RawData.length];
		int numberSequences [] = new int[RawData.length];
		String FinalStrings[][] = new String[RawData.length][4];
		String InSequence [] = new String[RawData.length];
		String OutSequence [] = new String[RawData.length];
		String TmSequence [] = new String[RawData.length];
		
		String Identifier [] = new String[RawData.length];
		double DG_value[] = new double[RawData.length];
		
		
		int numberIn = 0;
		int numberOut = 0;
		int numberTm = 0;
		
		for(int a = 0; a < RawData.length; a++)
		{
			FinalStrings[a][0] = "";
			FinalStrings[a][1] = "";
			FinalStrings[a][2] = "";
			StartSequenceModification [a] = "";
			AfterChargeModifications [a] = ""; 
			
//			System.out.println(RawData[a]);
			
//			if(RawData[a].charAt(0) == ' ')
//			{
//				RawData[a] = RawData[a].substring(1);
//			}
			
//	internal reference 
//															 b
//															 +
//												   09876543210
//			MAGWSCLVTGAGGFVGQRIIKM LVQEKELQEVRALDK e 5 P24815 2.935
			
			
			for(int b = 1; b < RawData[a].length(); b++)
			{
				if(RawData[a].charAt(RawData[a].length()-b) == ' ')
				{
					DG_value[a] = Double.parseDouble(RawData[a].substring(RawData[a].length()-b+1));
					Identifier[a] = RawData[a].substring(RawData[a].length()-b-6,RawData[a].length()-b);
					SsideCharge[a] = Integer.parseInt(RawData[a].substring(RawData[a].length()-b-8,RawData[a].length()-b-7));
					SecondNearSide[a] = RawData[a].substring(RawData[a].length()-b-10, RawData[a].length()-b-9);
					SequenceString [a] = RawData[a].substring(0, RawData[a].length()-b-11);
//					System.out.println(SequenceString [a]);
					break;
				}
				
				
			}
			
//			Old format code
//			for(int b = 0; b < RawData[a].length(); b++)
//			{
//				if(RawData[a].charAt(RawData[a].length()-1-b) == ' ')
//				{
//					System.out.println(RawData[a].substring(RawData[a].length()-b));
//					
//					SsideCharge[a] = Integer.parseInt(RawData[a].substring(RawData[a].length()-b, RawData[a].length()));
//					SecondNearSide[a] = RawData[a].substring(RawData[a].length()-b-2, RawData[a].length()-b-1);
//					SequenceString [a] = RawData[a].substring(0, RawData[a].length()-b-3);
//					
//					break;
//				}
//			}
//			
//			System.out.println(SsideCharge[a] + " " + SecondNearSide[a] + " " + SequenceString [a]);
//			System.out.println(RawData[a]);
		}
		
		String S = "s";
		for(int a = 0; a < RawData.length; a++)
		{
//			System.out.println(StartSequenceModification[a]);
			if(S.equals(SecondNearSide[a]))
			{
				StartSequenceModification[a] = SequenceString [a];
			}
			else
			{
//				System.out.println(SecondNearSide[a] + " "+ SequenceString[a]);
				
				for(int b = 0; b < SequenceString[a].length(); b++)
				{
					StartSequenceModification[a] = StartSequenceModification[a] + SequenceString [a].charAt(SequenceString[a].length()-b-1);
				}
			}
			
//			System.out.println(StartSequenceModification[a]);
		}
		
		for(int a = 0; a < RawData.length; a++)
		{
			if(SsideCharge[a] > 0)
			{
				AfterChargeModifications [a] = StartSequenceModification[a];
			}
			else
			{
				for(int b = 0; b < StartSequenceModification[a].length(); b++)
				{
					AfterChargeModifications[a] = AfterChargeModifications[a] + StartSequenceModification [a].charAt(StartSequenceModification[a].length()-b-1);
				}
			}
//			System.out.println(AfterChargeModifications[a]);
		}
		
		for(int a = 0; a < RawData.length; a++)
		{
			String TmpString = "";
			int tmp = 0;
			for(int b = 0; b < AfterChargeModifications[a].length(); b++)
			{
				if(AfterChargeModifications[a].charAt(b) != ' ')
				{
					TmpString = TmpString + AfterChargeModifications[a].charAt(b);
				}
				else if(AfterChargeModifications[a].charAt(b) == ' ')
				{
					FinalStrings[a][tmp] = TmpString;
					TmpString = "";
					tmp = tmp + 1;
				}
			}
			numberSequences[a] = tmp;
		}
		
		for(int a = 0; a < RawData.length; a++)
		{
			if(numberSequences[a] == 3)
			{
				InSequence [numberIn] = FinalStrings[a][0];
				OutSequence [numberOut] =FinalStrings[a][1];
				TmSequence [numberTm ] = FinalStrings[a][2];
				numberIn = numberIn + 1;
				numberOut = numberOut + 1;
				numberTm = numberTm + 1;
			}
			else if(numberSequences[a] == 2)
			{
				if(FinalStrings[a][0].length() >= FinalStrings[a][1].length())
				{
					TmSequence [numberTm ] = FinalStrings[a][0];
					numberTm = numberTm + 1;
					OutSequence [numberOut] =FinalStrings[a][1];
					numberOut = numberOut + 1;
				}
				else
				{
					InSequence [numberIn] = FinalStrings[a][0];
					numberIn = numberIn + 1;
					TmSequence [numberTm ] = FinalStrings[a][1];
					numberTm = numberTm + 1;
				}
			}
			else if(numberSequences[a] == 1)
			{
				TmSequence [numberTm ] = FinalStrings[a][0];
				numberTm = numberTm + 1;
			}
		}	
		
		
		TMs = TmSequence;
		numberTMS = numberTm;
	}
	
	public int SendTm()
	{
		return numberTMS;
	}
	
	public String [] returnTM ()
	{
		return TMs;
	}
	
}
