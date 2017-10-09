import java.io.IOException;


public class central {

	public static void main(String[] args)  throws IOException 
	{
		// TODO Auto-generated method stub

		
		int StartPos  = Integer.parseInt(args[0]);
		int EndPos = Integer.parseInt(args[1]);
		String Project = "b2014300";
		String CoreCommand = "#SBATCH -p core -n 1";
		String TimeCommand = "4:40:00";
		String jobName = "MOPRORunAll_" + StartPos +"_" + EndPos;
		FileDataRetriever FDR = new FileDataRetriever();
		
		String List[] = FDR.Analysis("ListOfThirdLayerFiles.txt");
		
		
		
		
//		
//		for(int a = 1; a < 17; a++)
//		{
//			System.out.println(List[a]);
//		}
		
		String PrintString = "#! /bin/bash -l     \n#SBATCH -A " + Project +"\n#SBATCH -M milou\n"
				+ CoreCommand + "\n" + "#SBATCH -t "+TimeCommand +"\n"+ "#SBATCH -J " + jobName
				+"\n#SBATCH -e /home/patrikb/glob/MOPRO/Scripts/Reports/"+jobName+"_SLURM_Job_id=%j.stderr.txt\n"
				+ "#SBATCH -o /home/patrikb/glob/MOPRO/Scripts/Reports/"+jobName+  "_SLURM_Job_id=%j.stdout.txt\n"
//				+"#SBATCH --mail-type=All\n#SBATCH --mail-user=patbj363@gmail.com"
				+ "\n\n";
		
		for(int a =  StartPos; a < EndPos;a++)
		{
			PrintString = PrintString + "cd /home/patrikb/glob/MOPRO/Results/Plasma\n";
			PrintString = PrintString + "mkdir " + List[a]+"\n";
			PrintString = PrintString + "cd /home/patrikb/glob/MOPRO/Bin\n";
			PrintString = PrintString + "java -jar -Xmx2048m MOPRO2.12.jar /home/patrikb/glob/MOPRO/Data/MotifFiles/thirdLayer/"
					+ List[a] + " /home/patrikb/glob/MOPRO/Data/MOPROdataFiles/MOPROdataFiles/MammalianPlasma.txt >> /home/patrikb/glob/MOPRO/Results/Plasma/" +
					List[a]+"/Result.txt\n";
			
		}
		
		System.out.println(PrintString);
		
	}

}
