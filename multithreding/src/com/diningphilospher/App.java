package com.diningphilospher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executerService = null;
		Philospher[] philosphers = null;
		
		try{
			philosphers = new Philospher[Constants.NUMBER_OF_PHILOSPHERS];
			Chopsticks[] chopsticks = new Chopsticks[Constants.NUMBER_OF_CHOPSTICS];
			
			for(int i=0;i<Constants.NUMBER_OF_CHOPSTICS;i++){
				chopsticks[i] = new Chopsticks(i);
			}
			
			executerService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSPHERS);
			
			for(int i=0;i<Constants.NUMBER_OF_PHILOSPHERS;i++){
				philosphers[i] = new Philospher(i, chopsticks[i], chopsticks[(i+1)%Constants.NUMBER_OF_CHOPSTICS]);
				executerService.execute(philosphers[i]);
			}
			Thread.sleep(Constants.SIMULATION_RUNNING_TIME);
			
			for(Philospher p : philosphers){
				p.setFull(true);
			}
		}finally{
			executerService.shutdown();
			while(!executerService.isTerminated())
				Thread.sleep(1000);
			
			for(Philospher p: philosphers){
				System.out.println(p+ " eats "+ p.getCounter()+" times.");
			}
		}
		
		
	}

}
