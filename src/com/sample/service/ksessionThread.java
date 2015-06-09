package com.sample.service;

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
