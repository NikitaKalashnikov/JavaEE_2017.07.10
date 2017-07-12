package ru.spec.java.ee;

import java.util.Date;
import java.util.concurrent.Future;

import javax.ejb.Remote;

@Remote
public interface DateService {
	
	public Date getCurrentDate();

	Future<Long> increment();

}
