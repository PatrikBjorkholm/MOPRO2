
public class central {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int StartRun = Integer.parseInt(args[0]);
		int FinishRun = Integer.parseInt(args[1]);
		
		
		System.out.println("mkdir ScriptFolder");
		System.out.println("chmod 777 ScriptFolder");
		System.out.println("mkdir ScriptFolder/SubscriptFolder");
		System.out.println("chmod 777 ScriptFolder/SubscriptFolder");
		for(int a = StartRun; a < FinishRun; a++)
		{
			System.out.println("java -jar MakeScript.jar " + a + " " + (a+1)
					+ " >> ScriptFolder/SubscriptFolder/MoproSub_" + a + "_" + (a+1)+".sh");
		}
		
		System.out.println("java -jar MakeSubmitingScripts.jar " + StartRun + " " + FinishRun +
				" >> ScriptFolder/CentralScript_" + StartRun + "_" + FinishRun +".sh");
	}

}
