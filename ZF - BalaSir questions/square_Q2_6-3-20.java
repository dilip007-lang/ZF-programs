class printingSquare{
	public static void main(String[] args) {
		int num = 1;
		for(int i=1;i<=16;i++){	
			System.out.print(num+" ");
			if(i<4 || (i>8 && i<12)){
				num++;
			}
			else if((i>4 && i<8) || (i>12)){
				num--;
			}
			if(i%4==0){
				System.out.println();
				num+=4;
			}
		}
	}
}
	
/*
1 2 3 4 
8 7 6 5 
9 10 11 12 
16 15 14 13
*/