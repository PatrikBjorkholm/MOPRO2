
public class central {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int StartPos = Integer.parseInt(args[0]);
		int EndPos = Integer.parseInt(args[1]);
		
		for(int a = StartPos; a < EndPos; a++)
		{
			System.out.println("chmod 777 SubscriptFolder/MoproSub_" +a + "_"+(a+1) +".sh");
			System.out.println("sbatch SubscriptFolder/MoproSub_" +a + "_"+(a+1) +".sh");
		}
		
		
		
		
	}

}
