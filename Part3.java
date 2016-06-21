package lab_9_pack;

class T4 extends Thread{
	public static int x,y,z;
	public static synchronized void f(){
		x=x+1;
		y=y+1;
		z=z+x-y;
	}
	public static void printValues(){
		System.out.println("x="+x);
		System.out.println("y="+y);
		System.out.println("z="+z);
	}
	public void run(){}
}

class T5 extends T4{
	@Override
	public void run(){
		for(int i=0;i<1000000;++i){
			f();
		}
	}
}

public class Part3 {
	public static void main(String[] args) throws InterruptedException{
		long startTime=System.nanoTime();
		Thread t5=new T5();
		Thread t6=new T5();
		t5.start();
		t6.start();
		t5.join();
		t6.join();
		T5.printValues();
		long endTime=System.nanoTime();
		System.out.println("Computation took "+((endTime-startTime)/1000000)+" milliseconds");
	}
}
