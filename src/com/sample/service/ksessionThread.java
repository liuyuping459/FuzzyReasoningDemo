package com.sample.service;

/**
 * the thread for load rete
 * @author liu
 *
 */
public class ksessionThread implements Runnable {
	private DroolsService droolsService;
	
	public ksessionThread(DroolsService droolsService) {
		this.droolsService = droolsService;
	}
	
	@Override
	public void run() {
		while(true) {
			droolsService.ksessionReady();
		}
	}

}
