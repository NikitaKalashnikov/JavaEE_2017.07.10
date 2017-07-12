package ru.spec.java.client;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ru.spec.java.ee.DateService;
import ru.spec.java.ee.MySingletonRemote;
import ru.spec.java.ee.TestBeanRemote;

public class Main {

	public static void main(String[] args) throws NamingException, InterruptedException, ExecutionException {
		
		Context ctx = new InitialContext();
		
	//	test(ctx);
		
	//	stateless(ctx);
		
		MySingletonRemote s = (MySingletonRemote) ctx.lookup("MySingleton");
		int MAX = 15;
		Future<Long>[] array = new Future[MAX];
		for (int i = 0; i < MAX; i++) {
			array[i] = s.increment();
			
		}
		System.out.println("--------------------------------");
		for (Future<Long> future: array) {
			System.out.println(future.get());
		}
	}

	private static void stateless(Context ctx) throws NamingException {
		DateService dateBean = (DateService) ctx.lookup("date");
		Date currentDay = dateBean.getCurrentDate();
		
		for (int i = 0; i < 10; i++) {		
		//	new Thread(() -> callServer(dateBean)).start();
			callServer(dateBean);
		}
		System.out.println(currentDay);
	}

	private static void callServer(DateService dateBean) {		
		System.out.println(dateBean.increment().isDone());
	}

	private static void test(Context ctx) throws NamingException {
		TestBeanRemote bean = (TestBeanRemote) ctx.lookup("echo");
		String result = bean.echo("hello from client");
		System.out.println(result);
	}
}
