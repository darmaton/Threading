package lab_9_pack;

import java.util.ArrayList;
import java.util.Random;

//The percentage of speed multithreading has over single threading on this system is 27%
//10.197.1.8:8080

//contains the matrix and sum of random numbers in matrix
class MatrixHolder{
	public int[][] matrix=new int[650][1000000];
	public int sum;
	public MatrixHolder(){
		Random rand=new Random();
		//initialize matrix with random numbers
		for(int x=0;x<matrix.length;++x){
			for(int y=0;y<matrix[x].length;++y){
				int randomNum=rand.nextInt(200);
				matrix[x][y]=randomNum;
			}
		}
	}
	public void resetSum(){
		sum=0;
	}
}
//thread that compute sum for entire matrix
class SingleThread extends Thread {
	public SingleThread(){}
	public void run(MatrixHolder matrixHolder){
		for(int x=0;x<matrixHolder.matrix.length;++x){
			for(int y=0;y<matrixHolder.matrix[x].length;++y){
				matrixHolder.sum+=(int)Math.log(matrixHolder.matrix[x][y]);
			}
		}
	}
}
//thread that computes sum for a row of a matrix
class MultiThread extends Thread {
	private MatrixHolder m;
	private int r;
	private int thisSum;
	public MultiThread(MatrixHolder matrixHolder,int row){
		m=matrixHolder;
		r=row;
	}
	public void logArray(){
		for(int y=0;y<m.matrix[r].length;++y){
			thisSum+=(int)Math.log(m.matrix[r][y]);
		}
	}
	
	public void run(){
		logArray();
	}
	public int getSum(){
		return thisSum;
	}
}
public class Part4 {
	public static void main(String[] args) {
		MatrixHolder matrixHolder=new MatrixHolder();
		ArrayList<MultiThread> array=new ArrayList<MultiThread>();
		try{
			long startTime=System.nanoTime();
			SingleThread singleThread=new SingleThread();
			singleThread.run(matrixHolder);
			singleThread.join();
			long endTime=System.nanoTime();
			System.out.println("Single thread sum: "+matrixHolder.sum);
			System.out.println("Computation took "+((endTime-startTime)/1000000)+" milliseconds");
			
			System.out.println("");
			matrixHolder.resetSum();
			
			startTime=System.nanoTime();
			
			//create same number of threads as number of rows in matrix 
			for(int i=0;i<matrixHolder.matrix.length;++i){
				array.add(new MultiThread(matrixHolder,i));
				array.get(i).start();
			}
			//start each thread at same time
			for(int i=0;i<matrixHolder.matrix.length;++i){
				array.get(i).join();
				matrixHolder.sum+=array.get(i).getSum();
			} 
			endTime=System.nanoTime();
			System.out.println("Multi thread sum: "+matrixHolder.sum);
			System.out.println("Computation took "+((endTime-startTime)/1000000)+" milliseconds");
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
