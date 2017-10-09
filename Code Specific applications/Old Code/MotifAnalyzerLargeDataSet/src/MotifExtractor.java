
public class MotifExtractor {
	
	private int PPA[];
	
	
	public String MotifParsefier(String Motif)
	{
//		int MotifLength = Motif.length();
		int [] Palette = new int [Motif.length()];
		int PalettePositions[] = new int [Motif.length()];
		int coun = 0;
		char AminoAcidComparisonMatrix [] = {'A', 'R', 'N', 'D', 'C', 'E', 'Q', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V'};
		for(int a = 0; a < Motif.length(); a++)
		{
			int temper = 0;
			
			for(int b = 0; b < AminoAcidComparisonMatrix.length; b++)
			{
				if(AminoAcidComparisonMatrix[b] == Motif.charAt(a))
				{
					PalettePositions[coun] = a;
					Palette[a] = b;
					temper = 1;
					coun = coun + 1;
					break;
				}
			}
			
			if(temper == 0)
			{
				Palette[a] = -1;
			}
		}
		
//		int numberGaps = 0;
		
//		int GapPositions[] = new int[Motif.length()];
//		int co = 0;
//		for(int a = 1; a < Motif.length(); a++)
//		{
//			if(Palette[a] == -1)
//			{
//				if(Palette[a-1] != -1)
//				{
//					GapPositions[numberGaps] = a;
//					numberGaps = numberGaps + 1;
//				}
//			}
//		}
		
//		int GapLength [] = new int [numberGaps];
//		
//		for(int a = 0; a < numberGaps; a++)
//		{
//			GapLength[a] = 1;
//		}
//		
//		int counter = 0;
//		for(int a = 1; a < Motif.length(); a++)
//		{
//			if(Palette[a] == -1)
//			{
//				if(Palette[a-1] == -1)
//				{
//					GapLength[counter] = GapLength[counter] + 1; 
//				}
//			}
//			else if(Palette[a] != -1)
//			{
//				if(Palette[a-1] == -1)
//				{
//					counter = counter + 1; 
//				}
//			}
//		}
		
//		
//		System.out.println(Motif);
//		System.out.println(numberGaps);
//		for(int a = 0; a < numberGaps; a++)
//		{
//			System.out.println(a + " " + GapLength[a] + " " + GapPositions[a]);
//		}
		
		
		
		
		String AminoPalette = "";
		for(int b = 0; b < Palette.length; b++)
		{
			if(Palette[b] != -1)
			{
				AminoPalette = AminoPalette + AminoAcidComparisonMatrix[Palette[b]];
			}
		}
		
		int PalettePositionArray [] = new int [AminoPalette.length()];
		
		for(int a = 0; a < AminoPalette.length(); a++)
		{
			PalettePositionArray[a] = PalettePositions[a];	
		}
		PPA = PalettePositionArray;
		
//		System.out.println(AminoPalette);
//		for(int a = 0; a < coun; a++)
//		{
//			System.out.println(PalettePositions[a]);
//		
//		}
		return AminoPalette;
	}
	
	public int[] ReturnPPA () 
	{
		return PPA;
	}
	
}
