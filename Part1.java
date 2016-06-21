package lab_9_pack;

//Part1 thread is about 1/2 faster than Part2 threads and synchronized threads in Part3 are about 
//4 times slower than unsynchronized threads in Part2

class T1 extends Thread{
	public static int x,y,z;
	public static void f(){
		x=x+1;
		y=y+1;
		z=z+x-y;
	}
	public static void printValues(){
		System.out.println("x="+x);
		System.out.println("y="+y);
		System.out.println("z="+z);
	}
	public void run(){
		for(int i=0;i<2000000;++i){
			f();
		}
		printValues();
	}
}

public class Part1 {
	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		long startTime=System.nanoTime();
		Thread t1=new T1();
		t1.start();
		t1.join();
		long endTime=System.nanoTime();
		System.out.println("Computation took "+((endTime-startTime)/1000000)+" milliseconds");
	}
}
