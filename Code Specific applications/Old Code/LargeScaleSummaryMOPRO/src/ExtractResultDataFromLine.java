
public class ExtractResultDataFromLine {
	
	private double P = 0.0;
	private double Z = 0.0;
	
	public String Parse (String Line)
	{
		int pos = 0;
		String Motif = "";
		double P_value = 0.0;
		double Z_value = 0.0;
		for(int a = 0; a < Line.length(); a++)
		{
			if(Line.charAt(a) == '\t')
			{
				Motif = Line.substring(0,a);
				pos = a +1;
				break;
				
			}
		}
		for(int a = pos; a < Line.length(); a++)
		{
			if(Line.charAt(a) == '\t')
			{
				P_value = Double.parseDouble(Line.substring(pos,a));
				pos = a +1;
				break;
			}
		}
		for(int a = pos; a < Line.length(); a++)
		{
			if(Line.charAt(a) == '\t')
			{
				Z_value = Double.parseDouble(Line.substring(pos,a));
				pos = a +1;
				break;
			}
		}
		P = P_value;
		Z = Z_value;
//		System.out.println(Z);
		
		return Motif;
	}
	
	public double returnZ ()
	{
		return Z;
	}
	
//	Motif	MT	                    MR	   MPL MPN	MPH
//	AAAAA	0.0	16.653599732040952	1000	0	0	64
//	AAAAR	0.813	-0.6735257496201834	187	304	509	1
}
