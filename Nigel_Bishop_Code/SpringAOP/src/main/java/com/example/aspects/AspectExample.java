package com.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component("myAspect")
@Aspect
public class AspectExample {

	
	
//	@Before("execution(* *(..))")
//	@Before("execution(* *doing*(..))")
//	public void beforeStuff(JoinPoint jp) {
//		System.out.println("-----------------");
//		System.out.println("Hello from before stuff  " + jp);
//		
//	}
	
//	
//	@After("execution(* *(..))")
//	public void afterStuff(JoinPoint jp) {
//		System.out.println("-----------------");
//
//		System.out.println("Hello from before stuff  " + jp);
//		
//	}
	
	@Around("execution(* *(..))")
	public void aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("INSIDE ADVICE");
		
		System.out.println("Doing stuff before");
		pjp.proceed();

		System.out.println("Doing stuff after  " + pjp);
		
	}
}
