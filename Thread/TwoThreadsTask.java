package com;

public class TwoThreadsTask {
	public static void main(String[] args) {
		Canon bofor=new Canon();
        ShootingTask st=new ShootingTask(bofor);
		
		Thread naina=new Thread(st,"naina");
		Thread shabeer=new Thread(st,"shabeer");
		
		naina.start();
		shabeer.start();
	}

}
class ShootingTask implements Runnable{
	Canon gun;
	 public ShootingTask(Canon gun) {
		 this.gun=gun;
		
	}
	
	@Override
	public void run() {
		Thread t= Thread.currentThread();
		if(t.getName().equals("naina")) {
			for(int i=0;i<5;i++) {
				gun.fill();
			}
		}else if(t.getName().equals("shabeer")) {
			for(int i=0;i<5;i++) {
				gun.shoot();
			}
			
			}		
	}
	
}
class Canon{
	boolean flag;
	synchronized public void fill() {
		Thread t=Thread.currentThread();
		String name=t.getName();
		if(flag) {
			try {wait();
			}catch (Exception e) {}
		}
		System.out.println(name+ " Fills the gun.....");
		flag=true;
		notify();
		
	}
	synchronized public void shoot() {
		Thread t=Thread.currentThread();
		String name=t.getName();
		if(!flag) {
			try{wait();}
			catch(Exception e) {}
		}
		System.out.println(name+ " shoot the gun.....");
		flag=false;
		notify();
	}
		
	}


