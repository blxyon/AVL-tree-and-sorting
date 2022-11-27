public class Sorting {
	public static void insertionSort(int[][] data) {
		insertionSort (data, 0, data.length-1);
	}
	public static void insertionSort(int[][] data, int start, int end) {
		int j;
		for(int i=start+1;i<=end;i++)
		{
			int[] tempElem=data[i];
			//j=i-1;
			for(j=i-1;j>=start;j--)
			{
				//we move the elemets greater than tempElem to the 
				if(compareLines(tempElem,data[j])==-1)
				{
					data[j+1]=data[j];
				}
				else break;
				
			}
			data[j+1]=tempElem;
		}
	}
	static int[][] mergeSort(int[][] data)
	{//this merge sort resturns the sorted lines with an int[][] leaving the array data intact
		if(data.length==1 || data==null) return data;
		else 
		{
			int[][] newData=new int[data.length][data[0].length];
			for(int i=0;i<data.length;i++)
			{
				newData[i]=data[i];
			}
			mergeSort2(newData,0,data.length-1);
			return newData;
		}
	}
	static void mergeSort2(int[][] data, int i, int n)
	{//this merge sort will not return the data sorted, but change the data instead to the sorted array
		
		int[][] tempData=null;
		if(i < n)
		{
			int m = (n + i) / 2;
			mergeSort2(data, i, m);
			mergeSort2(data, m + 1, n);
			
			tempData=merge(data, i, m, n);
			for(int ii=0;ii<tempData.length;ii++)
			{//updating newData
				data[ii+i]=tempData[ii];
			}
		}
	}
	public static int[][] merge(int[][] data, int i, int m, int n)
	{
		int[][] arrFormed = new int[n-i+1][data[0].length];
		int arrFormedPointer=0;
		int firstListPointer=i;
		int secondListPointer=m+1;

		while(firstListPointer<=m && secondListPointer<=n)
		{
			if(compareLines(data[firstListPointer],data[secondListPointer])==-1)
			{
				arrFormed[arrFormedPointer] = data[firstListPointer];
				firstListPointer++;
			}
			else 
			{
				arrFormed[arrFormedPointer] = data[secondListPointer];
				secondListPointer++;
			}

			arrFormedPointer++;
		}

		while(firstListPointer<=m)
		{
			arrFormed[arrFormedPointer] = data[firstListPointer];
			firstListPointer++;
			arrFormedPointer++;
		}

		while(secondListPointer<=n)
		{
			arrFormed[arrFormedPointer]=data[secondListPointer];
			secondListPointer++;
			arrFormedPointer++;
		}
		return arrFormed;
	}
	public static int[][] hybridSort (int[][] data,int threshold) 
	{//similarly as in merge sort, this returns the sorted elements leavig data intact
		if(data.length==1 || data==null) return data;
		else if(data.length<=threshold)
		{
			insertionSort(data);
			return data;
		}
		else{
			int[][] newData=new int[data.length][data[0].length];
			for(int i=0;i<data.length;i++)
			{
				newData[i]=data[i];
			}
			hybridSort2(newData,threshold,0,newData.length-1);
			return newData;
		}
	}
	public static void hybridSort2(int[][] data,int threshold, int i, int n)
	{//this changes the data array
		int[][] tempData=null;
		if(i < n)
		{
			if(n-i+1<=threshold)
			{
				insertionSort(data, i, n);
			}
			else{
				int m = (n + i) / 2;
				hybridSort2(data,threshold, i, m);
				hybridSort2(data,threshold, m + 1, n);
				tempData= merge(data, i, m, n);
				for(int ii=0;ii<tempData.length;ii++)
				{//updating newData
					data[ii+i]=tempData[ii];
				}
			}
		}
	}
	static int compareLines(int[] a,int[] b) {  // You may make this public if you wish
		int n=a.length;
		if (n != b.length)
			return (a[a.length-1]+b[b.length-1]);  // this gives an error
		int i=0;
		while (i<n && a[i]==b[i])
			i++;   // skip equal elements
		if (i==n)
			return 0;
		if (a[i]<b[i])
			return -1;
		else return 1;
	}       // can add extra functions here

}
