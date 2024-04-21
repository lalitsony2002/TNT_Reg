package Practice;

public class Array_String {
	
	int arr[] = {1, 2, 3, 4, 2, 7, 8, 8, 3};
	
	public void test()
	{
	 for(int i=0; i<arr.length;i++)
		 for(int j=i+1 ;j<arr.length;j++)
			 if(arr[i]==arr[j])
			 {
				 System.out.println(arr[i]);
			 }
	 
	}

	public static void main(String[] args) {

		Array_String obj = new Array_String();
		obj.test();
		
	}

}
