package ru.spec.java.ee;

import java.util.Date;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless(mappedName = "date")
@LocalBean
public class DateBean implements DateService {

	@Inject
	private TestBeanImpl testBean;

	@Inject
	private long rnd;
	
	long count = 0;
	
	@Override
	@Asynchronous
	public Future<Long> increment() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new AsyncResult<Long>(count++);
	}

	@Override
	public Date getCurrentDate() {
		System.out.println("Get current date from server");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Date(rnd);
	}

	@PostConstruct
	public void init() {
		System.out.println("--------------New DateBean " + Thread.currentThread());
	}

}
