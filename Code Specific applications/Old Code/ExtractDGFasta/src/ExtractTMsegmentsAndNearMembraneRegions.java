import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class ExtractTMsegmentsAndNearMembraneRegions {

	public void ExtractFragments (String SeqNames[], String Sequences[], int numberTM[], int TMpositions[][][], double [][] DeltaG, int SignalP[], String FastaOutpath)
	{
		
		
		String FragmentSequences = "";
		
		int numberFragmentsSeqs = 0 ;
		
		double DgThresshold = 4.0;
		
		boolean Verbose = true;
		
		int totalNumberTM = 0;
		int SequenceAnalysisStartPosition = 0; 
		for(int a = 0; a < numberTM.length; a++)
		{
			totalNumberTM = totalNumberTM + numberTM[a];
		}
		
		int chosenProtein[] = new int[Sequences.length];
		
//		System.out.println(Sequences.length);
		
//		System.out.println(numberTM.length);
//		System.out.println(SignalP.length);
//		
		for(int a = 0; a < Sequences.length; a++)
		{	
			if(numberTM[a] != 0)
			{
//				System.out.println(numberTM[a]);
				if(Verbose == true)
				{
//					System.out.println(">" +SeqNames[a]);
//					System.out.println(Sequences[a]);
				}
				
				int chargeCalculationSsidePos = 0;
				int chargeCalculationEsidePos = 0;
				int chargeCalculationSsideNeg = 0;
				int chargeCalculationEsideNeg = 0;
				int totalCountSside = 0;
				int totalCountEside = 0;
				
				String TempString = "";
				String ProximityString = "";
				String SignalString = "";				
				
				char PositionChar = 's';
				int currentTM = 0;
				boolean sser = true;
				
				
				
				
				for(int b = SequenceAnalysisStartPosition; b < Sequences[a].length(); b++)
				{
//					System.out.println(SignalP[a] + "\t" + b);
					if(SignalP[a] > b)
					{
						SignalString = SignalString + "S";
					}
					else
					{
						SignalString = SignalString + "t";
					}
				}
				
//	Analysis for multispanning TM-proteins			
				if(numberTM[a] > 1)
				{
				for(int b = SequenceAnalysisStartPosition; b < Sequences[a].length(); b++)
				{
// Sequence Siding relative to Start by generating a tempstring that locates the 
// TM regions and the start and not-start side. 				
					if(PositionChar == 's')
					{
						if(TMpositions[a][currentTM][0] == TempString.length()+1)
						{
							ProximityString = ProximityString + 'T';
							PositionChar = 'T';
							TempString = TempString + PositionChar; 

						}
						else
						{
							TempString = TempString + PositionChar;
//							Looking downstream
//							18	-15 = 3 <= c samt skilt ifrŒn noll!
//							
							if(TMpositions[a][currentTM][0]-15 <= TempString.length()+1)
							{
								if(TMpositions[a][currentTM][0] != 0)
								{
									ProximityString = ProximityString + 'p';
								}
								else
								{
									
									if(TMpositions[a][currentTM-1][1] >= TempString.length()+1-15)
									{
										ProximityString = ProximityString + 'p';
									}
									else
									{
										ProximityString = ProximityString + 'd';
									}
								}
							}
							else if(currentTM == 0)
							{
								ProximityString = ProximityString + 'd';
							}
							else if(TMpositions[a][currentTM][1] >= TempString.length()+1-15)// <---
							{
									ProximityString = ProximityString + 'p';								
							}
							else
							{
								ProximityString = ProximityString + 'd';
							}
						}
					}
					else if(PositionChar == 'T')
					{
						if(TMpositions[a][currentTM][1] == TempString.length()+1)
						{
							if(sser == true)
							{
								PositionChar = 'e';
								TempString = TempString + PositionChar;
								ProximityString = ProximityString + 'p';
//								System.out.println(TMpositions[a][currentTM][0] + "\t" + TMpositions[a][currentTM][1]);
								currentTM = currentTM + 1;

								sser = false;
								
							}
							else if(sser == false)
							{
								PositionChar = 's';
								ProximityString = ProximityString + 'p';
								TempString = TempString + PositionChar;
//								System.out.println(TMpositions[a][currentTM][0] + "\t" + TMpositions[a][currentTM][1]);
								currentTM = currentTM + 1;

								sser = true;
							}	
						}
						else
						{
							ProximityString = ProximityString + 'T';
							TempString = TempString + PositionChar; 
						}
					}
					else if(PositionChar == 'e')
					{
						if(TMpositions[a][currentTM][0] == TempString.length()+1)
						{
							ProximityString = ProximityString + 'T';
							PositionChar = 'T';

							TempString = TempString + PositionChar; 
						}
						else
						{
							TempString = TempString + PositionChar;
//							Looking downstream
							if(TMpositions[a][currentTM][0]-15 <= TempString.length()+1)
							{
								if(TMpositions[a][currentTM][0] != 0)
								{
									ProximityString = ProximityString + 'p';
								}
								else
								{
									if(TMpositions[a][currentTM-1][1] >= TempString.length()+1-15)
									{
										ProximityString = ProximityString + 'p';
									}
									else
									{
										ProximityString = ProximityString + 'd';
									}
								}
							}
//							Looking upstream 
//							18 >= (22-15) 7
							else if(TMpositions[a][currentTM-1][1] >= TempString.length()+1-15)
							{
								ProximityString = ProximityString + 'p';
							}
							else
							{
								ProximityString = ProximityString + 'd';
							}
							
						}
					}
				}
				}
				
//				Analysis for single spanning TM-proteins			
				else if(numberTM[a] == 1)
				{
					for(int b = SequenceAnalysisStartPosition; b < Sequences[a].length(); b++)
					{
						if(PositionChar == 's')
						{
							if(TMpositions[a][0][0] == TempString.length()+1)
							{
								ProximityString = ProximityString + 'T';
								PositionChar = 'T';
								TempString = TempString + PositionChar; 
							}
							else
							{
								TempString = TempString + PositionChar;
								if(TMpositions[a][0][0]-15 <= TempString.length()+1)
								{
									if(TMpositions[a][currentTM][0] != 0)
									{
										ProximityString = ProximityString + 'p';
									}
									else
									{
										ProximityString = ProximityString + 'd';
									}
								}
								else
								{
									ProximityString = ProximityString + 'd';
								}
							}
						}
						else if(PositionChar == 'T')
						{
							if(TMpositions[a][0][1] == TempString.length()+1)
							{
								PositionChar = 'e';
								TempString = TempString + PositionChar;
								ProximityString = ProximityString + 'p';
							}
							else
							{
								TempString = TempString + PositionChar;
								ProximityString = ProximityString + 'T';
							}
						}
						else if(PositionChar == 'e')
						{
							TempString = TempString + PositionChar;
							if(TMpositions[a][0][1] >= TempString.length()+1-15)
							{
									ProximityString = ProximityString + 'p';
							}
							else
							{
								ProximityString = ProximityString + 'd';
							}
							
						}
							
					}
				}
				
				
				
				
				
				
				
				
				
				
				
// Charge analysis 
				for(int b = SignalP[a]; b < Sequences[a].length(); b++)
				{
					if(ProximityString.charAt(b) == 'p')
					{
						if(TempString.charAt(b) == 's')
						{
							totalCountSside = totalCountSside + 1;
//							Positved charged aa
							if(Sequences[a].charAt(b) == 'R')
							{
								chargeCalculationSsidePos = chargeCalculationSsidePos + 1;
							}
							else if(Sequences[a].charAt(b) == 'R')
							{
								chargeCalculationSsidePos = chargeCalculationSsidePos + 1;
							}
							else if(Sequences[a].charAt(b) == 'D')
							{
								chargeCalculationSsideNeg = chargeCalculationSsideNeg + 1;
							}
							else if(Sequences[a].charAt(b) == 'E')
							{
								chargeCalculationSsideNeg = chargeCalculationSsideNeg + 1;
							}
						}
						else if(TempString.charAt(b) == 'e')
						{
							totalCountEside = totalCountEside + 1;
							if(Sequences[a].charAt(b) == 'R')
							{
								chargeCalculationEsidePos = chargeCalculationEsidePos + 1;
							}
							else if(Sequences[a].charAt(b) == 'R')
							{
								chargeCalculationEsidePos = chargeCalculationEsidePos + 1;
							}
							else if(Sequences[a].charAt(b) == 'D')
							{
								chargeCalculationEsideNeg = chargeCalculationEsideNeg + 1;
							}
							else if(Sequences[a].charAt(b) == 'E')
							{
								chargeCalculationEsideNeg = chargeCalculationEsideNeg + 1;
							}
						}
					}
				}
				
				
				
				
				
//	Positive charges net (+)			
				int NetChargeSside = chargeCalculationSsidePos-chargeCalculationSsideNeg;
				int NetChargeEside = chargeCalculationEsidePos-chargeCalculationEsideNeg;;
//				System.out.println("Net charge positive (start-terminal, end terminal and s-e)\t" + NetChargeSside + "\t" + NetChargeEside + "\t" + (NetChargeSside-NetChargeEside));
				
				if(Math.abs(NetChargeSside-NetChargeEside) >= 2)
				{
					for(int b = 0; b < numberTM[a]; b++)
					{
						if(DeltaG[a][b] <= DgThresshold)
						{
							chosenProtein[a] = 1;
						}
					}
				}
				
//				Category 1= S, positive Catergory 2= S negative
				
				char categoryChar = ' ';
				
				if(chosenProtein[a] == 1)
				{
					if(NetChargeSside > NetChargeEside)
					{
						categoryChar = 1;
					}
					else if(NetChargeSside < NetChargeEside)
					{
						categoryChar = 2;
					}
				}
				
				
				int numberPassedTMs = 0;
				
				
				if(chosenProtein[a] == 1)
				{
					for(int b = 0; b < numberTM[a]; b++)
					{
						if(DeltaG[a][b] <= DgThresshold)
						{
							numberPassedTMs= numberPassedTMs+1;
						}
					}
				}
				

				//Number Passed TMS
				if(numberPassedTMs > 0)
				{
//					System.out.println("passesHere");
					
					int InternalCounter = 0;
					
					String [] SeqQualificationString= new String [numberPassedTMs];
					
					for(int c = 0; c < numberPassedTMs; c++)
					{
						SeqQualificationString[c] ="";
					}
					
					for(int b = 0; b < numberTM[a]; b++)
					{
						if(DeltaG[a][b] <= DgThresshold)
						{
//							System.out.println("run = " +  b + "\t" + TMpositions[a][b][0] + "\t" + TMpositions[a][b][1]);
							for(int c = 0; c < Sequences[a].length(); c++)
							{
								if(SignalString.charAt(c) == 'S')
								{
									SeqQualificationString [InternalCounter] = SeqQualificationString [InternalCounter] + '0';
								}
								else
								{
									if(ProximityString.charAt(c) == 'p')
									{
										if(c < TMpositions[a][b][0])
										{
											if(c >= TMpositions[a][b][0]-17)
											{
												SeqQualificationString [InternalCounter] = SeqQualificationString [InternalCounter] + '1';
											}
											else
											{
												SeqQualificationString [InternalCounter] = SeqQualificationString [InternalCounter] + '0';
											}
										}
//										om sant ska c stšrre en 158 men mindre Šn 158 + 15 
										else 
										{
											if(c+1 >= TMpositions[a][b][1])
											{
												if(c <= TMpositions[a][b][1]+15)
												{
													SeqQualificationString [InternalCounter] = SeqQualificationString [InternalCounter] + '1';
												}
												else
												{
													SeqQualificationString [InternalCounter] = SeqQualificationString [InternalCounter] + '0';
												}
											}
											else
											{
												SeqQualificationString [InternalCounter] = SeqQualificationString [InternalCounter] + '0';
											}
										}
									}
									else if(ProximityString.charAt(c) == 'T')
									{
										if(c+1 >= TMpositions[a][b][0])
										{
											if(c+1 <= TMpositions[a][b][1])
											{
												SeqQualificationString [InternalCounter] = SeqQualificationString [InternalCounter] + '1';
											}
											else
											{
												SeqQualificationString [InternalCounter] = SeqQualificationString [InternalCounter] + '0';
											}
										}
//										else if(c <= TMpositions[a][b][1])
//										{
//											SeqQualificationString [InternalCounter] = SeqQualificationString [InternalCounter] + '1';
//										}
										else
										{
											SeqQualificationString [InternalCounter] = SeqQualificationString [InternalCounter] + '0';
										}
									}
									else
									{
										SeqQualificationString [InternalCounter] = SeqQualificationString [InternalCounter] + '0';
									}
								}
							}
							InternalCounter = InternalCounter + 1;
						}
						
					}
//					System.out.println("passesHere\t" + InternalCounter);
//					System.out.println(numberPassedTMs + "\t"+numberTM[a]);
				
					if(Verbose == true)
					{
//						System.out.println(TempString);
//						System.out.println(ProximityString);
						
//						for(int b = 0; b < numberTM[a]; b++)
//						{
//								System.out.println(TMpositions[a][b][0] + "\t" + TMpositions[a][b][1]);
//						}
//						System.out.println(SignalString);
						
						String Temper ="";
						
						
						for(int b = 0; b < InternalCounter;b++)
						{
							char Achar = '0';
							Temper = "";
							for(int c = 0; c < SeqQualificationString[b].length(); c++)
							{

								if(SeqQualificationString[b].charAt(c) == '1')
								{
									if(ProximityString.charAt(c) == 'p')
									{
										
										if(c == 0)
										{
											Temper = Temper + Sequences[a].charAt(c);
										}
										else
										{
											if(ProximityString.charAt(c-1) == 'T')
											{
												Achar = TempString.charAt(c);
												Temper = Temper + " " + Sequences[a].charAt(c);
											}
											else
											{
												Temper = Temper + Sequences[a].charAt(c);
											}
										}
									}
									if(ProximityString.charAt(c) == 'T')
									{
										if(c == 0)
										{
											Temper = Temper + Sequences[a].charAt(c);
										}
										else
										{
											if(ProximityString.charAt(c-1) == 'p')
											{
												
												Temper = Temper + " " + Sequences[a].charAt(c);
											}
											else
											{
												Temper = Temper + Sequences[a].charAt(c);
											}
										}
									}
								}
							}
							Temper = Temper + " " + Achar + " " +(NetChargeSside-NetChargeEside) + " "+ SeqNames[a].substring(4,10) + " " +DeltaG[a][b] ;
							numberFragmentsSeqs = numberFragmentsSeqs + 1;
							FragmentSequences = FragmentSequences + Temper + "\n";
//							System.out.println(SeqQualificationString[b]);
							if(Temper.charAt(0) == ' ')
							{
								if(Temper.length()>34)
								System.out.println(Temper.substring(1));
							}
							else
							{
								if(Temper.length()>35)
								System.out.println(Temper);
							}
						}
//						for(int b = 0; b < numberTM[a]; b++)
//						{
//							System.out.println("DG TM "+ b + "\t" + DeltaG[a][b]);
//							
//						}
//					1234567890123456789012345678	
//					ELTFELPDSAR 0 4 H0VX97 0.102
//						System.out.println("S-side Total = " + totalCountSside);
//						System.out.println("E-side Total = " + totalCountEside);
//						System.out.println("S-side Pos   = " + chargeCalculationSsidePos);
//						System.out.println("E-side Pos   = " + chargeCalculationEsidePos);
//						System.out.println("S-side Neg   = " + chargeCalculationSsideNeg);
//						System.out.println("E-side Neg   = " + chargeCalculationEsideNeg);
					}
				}
				
//				for(int b = 0; b < Sequences[a].length(); b++)
//				{
//					if(SignalString.charAt(b) == 't')
//					{
//						if(ProximityString.charAt(b) == 'p')
//						{
//// If step one
//							if(b == 0)
//							{
//								if(DeltaG[a][currentTempTM] <= 0)
//								{
//									tmpString = tmpString + Sequences[a].charAt(b);
//								}
//							}
//							else if(ProximityString.charAt(b-1) == 'T')
//							{
//								if(DeltaG[a][currentTempTM] <= 0)
//								{
//									
//									tmpString = tmpString + Sequences[a].charAt(b);
//								}
//							}
//							else if(ProximityString.charAt(b-1) == 'p')
//							{
//								if(DeltaG[a][currentTempTM] <= 0)
//								{
//									tmpString = tmpString + Sequences[a].charAt(b);
//								}
//							}
//								
//						}
//					}
//				}
				
				
//				if()
//				System.out.println(numberFragmentsSeqs);
//				FileOutputStream fout;
//				try
//				{
//					fout = new FileOutputStream (FastaOutpath);
//					new PrintStream(fout).println(FragmentSequences);
//					fout.close();
//				}
//				
//				catch (IOException e)
//				{
//					System.err.println ("Unable to write to file");
//					System.exit(-1);
//				}
				
				
				
//				counter = counter + 1;
			}
		}
	}
}
