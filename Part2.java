package lab_9_pack;

class T2 extends T1{
	public void run(){
		for(int i=0;i<1000000;++i){
			f();
		}
	}
}

public class Part2 {
	public static void main(String[] args) {
		long startTime=System.nanoTime();
		Thread t2=new T2();
		Thread t3=new T2();
		t2.start();
		t3.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		T1.printValues();
		long endTime=System.nanoTime();
		System.out.println("Computation took "+((endTime-startTime)/1000000)+" milliseconds");
	}
}

