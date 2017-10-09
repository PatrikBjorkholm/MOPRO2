
public class NonRandomMotifs {
	
	public int NonRandomizedMotifCounter (String [] TMSequences, String Palette, int [] PalettePositions, int MotifLength, int numberTMs)
	{
		int numMotifs = 0;
		
		
		
		for(int a = 0; a < numberTMs; a++)
		{
				for(int b = 0; b < TMSequences[a].length() - MotifLength; b++)
				{
					int numberCorrect = 0;
					for(int c = 0; c < Palette.length(); c++)
					{
						if(TMSequences[a].charAt(b + PalettePositions[c]) == Palette.charAt(c))
						{
							numberCorrect = numberCorrect + 1;
						}
					}
				
					if(numberCorrect == Palette.length())
					{
						numMotifs = numMotifs + 1;
					}
			}
			
			
//			int numV = 0;
//			int numT = 0;
//			int numW = 0;
//			int numI = 0;
//			
//			for(int b = 0; b < TMSequences[a].length(); b++)
//			{
//				if(TMSequences[a].charAt(b) == 'V')
//				{
//					numV = numV + 1;
//				}
//				if(TMSequences[a].charAt(b) == 'T')
//				{
//					numT = numT + 1;
//				}
//				if(TMSequences[a].charAt(b) == 'W')
//				{
//					numW = numW + 1;
//				}
//				if(TMSequences[a].charAt(b) == 'V')
//				{
//					numI = numI + 1;
//				}
//			}
			
//			if(numV >= 2)
//			{
//				if(numT >= 1)
//				{
//					if(numI >= 2)
//					{
//						if(numW >= 2)
//						{
//							possibleCandidates = possibleCandidates + 1;
//						}
//					}
//				}
//			}
			
			
		}
//		System.out.println("NumberCandidates = " + possibleCandidates);
		return numMotifs;
	}
	
	
}
