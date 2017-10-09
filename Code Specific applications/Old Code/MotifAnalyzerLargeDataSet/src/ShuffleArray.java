import java.util.Random;


	public class ShuffleArray 
	{
		
		public static void shuffleArray(int[] a) 
		{
			int n = a.length;
			Random random = new Random();
			random.nextInt();
			for (int i = 0; i < n; i++) {
				int change = i + random.nextInt(n - i);
				swap(a, i, change);
			}
		}

		private static void swap(int[] a, int i, int change) {
			int helper = a[i];
			a[i] = a[change];
			a[change] = helper;
		}

		public String main(String Sequence) 
		{
//			Generate specific integer array 
			
			int[] a = new int[Sequence.length()];
			
			for(int b = 0; b < a.length; b++)
			{
				a[b] = b;
			}
			
//			int[] a = new int[] {0, 1, 2, 3, 4, 5, 6, 7,8,9,10,11,12,13,14,15,16,17,18,19,20 };
			
			String NewString = "";
			shuffleArray(a);
			for (int i : a) {
				NewString = NewString + Sequence.charAt(i);
			}
			return NewString;
		}
	}

