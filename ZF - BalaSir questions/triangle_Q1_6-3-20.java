class printingNumbersInTriangleShape{
	public static void main(String[] args) {
		for(int i=1;i<=10;i++){
			System.out.print(i+" ");
			if(i==4 || i==7 || i==9 || i==10){
				System.out.println();
			}
		}
	}
}

/*
1 2 3 4 
5 6 7 
8 9 
10
 */