class printingChessBoard{
	public static void main(String[] args){
		int a = 1;
		for(int i=1;i<=16;i++){
			if(a==1){
				System.out.print(a+" ");
				a = 0;
			}
			else{
				System.out.print(a+" ");
				a = 1;
			}
			if(i%4==0){
				System.out.println();
				if(a == 1){
					a = 0;
				}
				else{
					a = 1;
				}
			}
		}
	}
}

/*
1 0 1 0 
0 1 0 1 
1 0 1 0 
0 1 0 1
*/