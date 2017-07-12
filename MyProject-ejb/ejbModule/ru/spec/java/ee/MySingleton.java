package ru.spec.java.ee;

import java.util.Date;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Session Bean implementation class MySingleton
 */
@Singleton(mappedName = "MySingleton")
@LocalBean
@Lock(LockType.READ)
@Startup
public class MySingleton implements MySingletonRemote {

	long count = 0;
	
	public MySingleton() {
		System.out.println("+++MySingleton() " + Thread.currentThread());
	}

	@PostConstruct
	public void init() {
		System.out.println(">>>MySingleton.init() " + Thread.currentThread());
	}
	

	
	@Override
	@Asynchronous
	public Future<Long> increment() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		return new AsyncResult<Long>(count++);
	}

	@Schedule(persistent = false, second = "*/10", minute = "*", hour = "*", month = "*", year = "*", dayOfMonth = "*")
	public void helloWorld() {
		System.out.println("hello " + new Date());
	}
	
}
