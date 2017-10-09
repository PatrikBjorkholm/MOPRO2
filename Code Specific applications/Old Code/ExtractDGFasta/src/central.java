import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class central {

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws IOException {
		// TODO Auto-generated method stub
		
		int numberTMregions = 0;		
		boolean PrintDG = false;
		
		
		
		FileDataRetriever FDR = new FileDataRetriever();
		String FastaInpath = args[0];
		String DGInpath = args[1]; 
		String FastaOutpath = args[2]; 
		String SignalPpath = args[3];
		
		
		String RawSequenceData[] = FDR.Analysis(FastaInpath);
		String RawDGData[] = FDR.Analysis(DGInpath);
		String SignalPData [] = FDR.Analysis(SignalPpath);
		
		String NegativeString = "No predicted TM helices";
		
		
		
		
		
		
		int numberSequences = 0;
		for(int a = 0; a < RawSequenceData.length; a++)
		{
			if(RawSequenceData[a].charAt(0) == '>')
			{
				numberSequences = numberSequences + 1;
			}
		}
		
//		for(int a = 0; a < RawDGData.length; a++)
//		{
//			System.out.println(RawDGData[a]);
//		}
		String SeqNames[] = new String[numberSequences];
		String Sequences[] = new String[numberSequences];
		int Index[] = new int [numberSequences];
		int NumberTMsequences[] = new int [numberSequences];
//		sequence, TMsegmen, Start and end.
		int TMSequencePositions[][][] = new int [numberSequences][50][2];
		double TMDeltaGValue [][] = new double [numberSequences][50];
		
		for(int a = 0; a < numberSequences; a++)
		{
			SeqNames[a] =  "";
			Sequences[a] = "";
		}
		
		
		
		int counter = -1;
		for(int a = 0; a < RawSequenceData.length; a++)
		{
			if(RawSequenceData[a].charAt(0) == '>')
			{
				counter = counter + 1;
				SeqNames[counter] = RawSequenceData[a];
			}
			else
			{
				Sequences[counter] = Sequences[counter] + RawSequenceData[a];
			}
		}
		
		int counter2 = -1;
		
		for(int a = 0; a < RawDGData.length; a++)
		{
			if(RawDGData[a].charAt(0) == '>')
			{
				counter2 = counter2 + 1;
				if(RawDGData[a+1].length() >= NegativeString.length())
				{
					if(NegativeString.equals(RawDGData[a+1].substring(0, NegativeString.length())))
					{
						Index[counter2] = 1;
					}
				}
				else
				{
					for(int b = a+3;b < RawDGData.length; b++)
					{
						if(RawDGData[b].charAt(0) == '>')
						{
							break;
						}
						else
						{
//							sequence, TMsegmen, Start and end
							int tempint = 0;
							for(int c = 0; c < RawDGData[b].length(); c++)
							{
								if(RawDGData[b].charAt(c) == '-')
								{
									tempint = c;
									break;
								}
							}
							
//							System.out.println(RawDGData[b]);
							TMSequencePositions[counter2][NumberTMsequences[counter2]][0] = Integer.parseInt(RawDGData[b].substring(0,tempint));
							
							 
							
							for(int c = tempint+1; c < RawDGData[b].length(); c++)
							{
								if(RawDGData[b].charAt(c) == '\t')
								{
									TMSequencePositions[counter2][NumberTMsequences[counter2]][1] = Integer.parseInt(RawDGData[b].substring(tempint+1, c));
								
									break;
								}
							}
							
							int pos[] = {0,0};
							
							for(int c = 0; c < RawDGData[b].length(); c++)
							{
								if(RawDGData[b].charAt(c) == '.')
								{
									pos[0] = c-1;
									pos[1] =c+4;
									break;
								}
							}
							
							TMDeltaGValue[counter2][NumberTMsequences[counter2]] = Double.parseDouble(RawDGData[b].substring(pos[0],pos[1])); 
//							for(int c = 1; c < 15; c++)
//							{
//								if(RawDGData[b].charAt(RawDGData[b].length()-c) == '\t')
//								{
//									System.out.println(RawDGData[b].substring(RawDGData[b].length()-c+1, RawDGData[b].length()));
//									TMDeltaGValue[counter2][NumberTMsequences[counter2]] = Double.parseDouble(RawDGData[b].substring(RawDGData[b].length()-c+1, RawDGData[b].length()));
//									break;
//								}
//							}
//							System.out.println(RawDGData[b] + "\t" + TMDeltaGValue[counter2][NumberTMsequences[counter2]] + "\t" + pos[0] + "\t" + pos[1]);
							NumberTMsequences[counter2] = NumberTMsequences[counter2] + 1;
							 
						}
					}
				}
			}
		}
		
//		for(int a = 0; a < numberSequences; a++)
//		{
//			if(Index[a] == 0)
//			{
//				System.out.println(SeqNames[a]);
//				for(int b = 0; b < NumberTMsequences[a]; b++)
//				{
//					System.out.println(TMSequencePositions[a][b][0] + " " + TMSequencePositions[a][b][1] + " " + TMDeltaGValue[a][b]);  
//				}
//			}
//		}
		
		ExtractSignalPdata ESP = new ExtractSignalPdata();
		
		int SignalP[] = ESP.ExtractPositionInSignalP(SignalPData);
		ExtractTMsegmentsAndNearMembraneRegions ETMAN = new ExtractTMsegmentsAndNearMembraneRegions(); 
		ETMAN.ExtractFragments(SeqNames, Sequences, NumberTMsequences, TMSequencePositions, TMDeltaGValue, SignalP, FastaOutpath);
		
		
//		System.out.println(counter);
//		System.out.println(counter2);
		if(PrintDG == true)
		{
		
//		FileOutputStream fout;
//		try
//		{
//			fout = new FileOutputStream (DGlistOutpath);
//			
			for(int a = 0; a <= counter; a++)
			{
//				if(Index[a] == 1)
//				{
//					new PrintStream(fout).println(SeqNames[a]);
					for(int b = 0; b < NumberTMsequences[a]; b++)
					{
						
						char classificationChar = 'u';
						
						if(b == 0)
						{
							classificationChar = 's';
						}
						else if(b+1 == NumberTMsequences[a])
						{
							classificationChar = 'e';
						}
						else
						{
							classificationChar = 'i';
						}
						
						System.out.println(classificationChar + "\t" + TMSequencePositions[a][b][0] + "\t" + TMSequencePositions[a][b][1] + "\t" + TMDeltaGValue[a][b]);  
					}
//				}
			}
//				fout.close();
//			}
//			catch (IOException e)
//			{
//				System.err.println ("Unable to write to file");
//				System.exit(-1);
//			}
		}
		
	}}
