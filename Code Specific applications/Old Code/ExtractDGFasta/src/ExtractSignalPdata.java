
public class ExtractSignalPdata {

	public int [] ExtractPositionInSignalP (String Data[])
	{
		
		int SignalPposition [] = new int[Data.length-1];
		
		int positions = 0;
		
		for(int a = 0; a < Data[0].length();a++)
		{
			if(Data[0].charAt(a) == '?')
			{
				positions = a;
			}
		}
		
		System.out.println(positions);
		
		for(int a = 1; a < Data.length;a++)
		{
			char TempChar = ' ';
			for(int b = 30; b< Data[a].length();b++)
			{
				if( Data[a].charAt(b) == 'N')
				{
					TempChar = 'N';
					break;
				}
				else if(Data[a].charAt(b) == 'Y')
				{
					TempChar = 'Y';
					break;
				}
			}
			
			
//			char TempChar = Data[a].charAt(Data[a].charAt(74));//Data[a].length()-1));
			String TempString = Data[a].substring(34,36); //((Data[a].length()-15), (Data[a].length()-13));
			
			TempString = TempString.replaceAll(" ","");
//			System.out.println(TempChar + "\t" + TempString);
			
			if(TempChar == 'N')
			{
				SignalPposition[a-1] = 0;
			}
			else if(TempChar == 'Y')
			{
				SignalPposition[a-1] = Integer.parseInt(TempString);
			}
		}
		
		return SignalPposition;
	}
}
