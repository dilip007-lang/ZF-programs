class printingChessBoard{
	public static void main(String[] args){
		int num = 1;
		int noOfRows = 4;
		int noOfColumns = 4;

		/*Method - 1*/
		for(int i=1;i<=noOfRows;i++)
		{
			for(int j=1;j<=noOfColumns;j++)
			{
				if(num==1){
					System.out.print(num+" ");
					//System.out.print("1 ");
					num = 0;
				}
				else{
					System.out.print(num+" ");
					//System.out.print("0 ");
					num = 1;
				}
			}
			if(num==1){
				num = 0;
			}
			else{
				num=1;
			}
			System.out.println();	
		} 
		

		/*num = 1;
		for(int i=1;i<=noOfRows;i++)
		{
			for(int j=1;j<=noOfColumns;j++)
			{
				if(num==1){
				System.out.print("1 ");
				}
				else{
					System.out.print("0 ");
				}
				num*=-1;
			}
			num*=-1;
			System.out.println();	
		}*/
	}
}


	//System.out.println();
		/*Method - 2*/
		/*num = 0;
		for(int i=1;i<=noOfRows;i++)
		{
			if(num==1){
				num = 0;
				System.out.print(num+" ");
				num = 1;
			}
			else{
				num = 1;
				System.out.print(num+" ");
				num = 0;
			}
				for(int j=2;j<=noOfColumns;j++)
				{
					if(num==1){
					System.out.print(num+" ");
					num = 0;
					}
					else{
						System.out.print(num+" ");
						num = 1;
					}
				}
			System.out.println();	
		}
		System.out.println();*/



		/*Method - 3*/
		/*num = 0;
		for(int i=1;i<=noOfRows;i++)
		{
			if(num==1){
				System.out.print("0 ");
				num = 1;
			}
			else{
				System.out.print("1 ");
				num = 0;
			}
				for(int j=2;j<=noOfColumns;j++)
				{
					if(num==1){
					System.out.print("1 ");
					num = 0;
					}
					else{
						System.out.print("0 ");
						num = 1;
					}
				}
			System.out.println();	
		} 
		System.out.println();*/



		/*Method - 4*/
		/*num = 1;
		for(int i=1;i<=noOfRows;i++)
		{
			for(int j=1;j<=noOfColumns;j++)
			{
				if(num==1){
				System.out.print(num+" ");
					if(j!=noOfColumns)
					{
					num = 0;
					}
				}
				else{
					System.out.print(num+" ");
					if(j!=noOfColumns)
					{
					num = 1;
					}
				}
			}
			System.out.println();	
		}
		System.out.println();

		/*Method - 5*/
		/*num = 1;
		for(int i=1;i<=noOfRows;i++)
		{
			for(int j=1;j<=noOfColumns;j++)
			{
				if(num==1){
				System.out.print("1 ");
				}
				else{
					System.out.print("0 ");
				}
				num*=-1;
			}
			num*=-1;
			System.out.println();	
		}
		*/

