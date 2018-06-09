package com.learning;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedQueue {

	public static void main(String[] args) {

		BlockingQueue<DelayedWorker> queue = new DelayQueue<>();
		
		try {
			queue.put(new DelayedWorker(1000, "This is first msg."));
			queue.put(new DelayedWorker(10000, "This is second msg."));
			queue.put(new DelayedWorker(4000, "This is third msg."));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(!queue.isEmpty()){
			try {
				System.out.println(queue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class DelayedWorker implements Delayed{

	private long duaration;
	private String msg;
	
	public DelayedWorker(long d, String s) {
		this.duaration = System.currentTimeMillis()+d;
		this.msg = s;
	}
	
	public long getDuaration() {
		return duaration;
	}

	public void setDuaration(long duaration) {
		this.duaration = duaration;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public int compareTo(Delayed del) {

		if(this.duaration < ((DelayedWorker) del).getDuaration())
			return -1;
		
		if(this.duaration < ((DelayedWorker) del).getDuaration())
			return +1;
		return 0;
	}

	@Override
	public long getDelay(TimeUnit arg0) {
		return arg0.convert(duaration- System.currentTimeMillis(),TimeUnit.MILLISECONDS);
	}
	
	@Override
	public String toString() {
		return this.msg;
	}
}