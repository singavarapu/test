package com.krupa;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public final class Intro {
  public static final void main(final String[] args) {
	ThreadLocalRandom random = ThreadLocalRandom.current();
		
	List<Integer> list = new ArrayList<Integer>();
	
	System.out.printf("run\ttime (micros)%n");
	for ( int run = 0; run < 100; ++run ) {
	  long startTime = System.nanoTime();
		
	  for ( int i = 0; i < 25000; ++i ) {
	    list.add(random.nextInt());
	  }
	
	  long elapsedMicros = (System.nanoTime() - startTime) / 1000;
	  System.out.printf("%d\t%d%n", run, elapsedMicros);
	}
  }
}