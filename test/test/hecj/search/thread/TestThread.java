package test.hecj.search.thread;

import java.util.Map;

public class TestThread extends Thread{
	
	static TestThread t =null;
	
	TestThread getIn(){
		return t;
	}
	
	 @Override
	public void run() {
		 t=this;
//		 System.out.println("run.........");
		 
	}
	 
	 
	 public TestThread(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		TestThread t = new TestThread("001");
		t.start();
		t.getId();
//		System.out.println(t.getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Thread, StackTraceElement[]>  map  = Thread.getAllStackTraces();
		for(Thread tt:map.keySet()){
			System.out.println(tt.getName()+"=="+tt.isAlive());
			
		}
		
		System.out.println(Thread.currentThread().getName());
		
		
	}
}
