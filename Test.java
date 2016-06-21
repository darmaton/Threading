package lab_9_pack;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test {

	public static void main(String[] args) {
		final CyclicBarrier gate=new CyclicBarrier(3);
		
		Thread t1=new Thread(){
			public void run(){
				try {
					gate.await();
					for(int i=0;i<100;++i){
						System.out.println("Me doing stuff");
						Thread.sleep(500);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Thread t2=new Thread(){
			public void run(){
				try {
					gate.await();
					for(int i=0;i<100;++i){
						System.out.println("I'm speaking proper English");
						Thread.sleep(500);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t1.start();
		t2.start();
		
		try {
			gate.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Both threads started");
	}

}
