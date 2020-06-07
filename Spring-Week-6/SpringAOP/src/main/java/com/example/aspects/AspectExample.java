package com.example.aspects;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 * Much like Hibernate annotations implement JPA annotations..
 * Spring AOP create an implementation of aspectj. It simplifies aspectj BUT it reduces the functionality as a result 
 * Basically, Spring AOP abstracts aspectj from us. 
 * 
 * What is an aspect?
 * 		An aspect is a modularization of cross cutting concerns (CCC)
 * 
 * In Java, it takes the form of a class. You can ALSO think of an aspet as a collection of advice. 
 * 
 */

@Component("myAspect")
@Aspect
public class AspectExample {
	
	/*
	 * What is an advice?
	 * 		An advice is an action taken by an spect. In Spring AOP advice is triggered by method invocations. 
	 * 
	 * In Java, an advice takes the form of a method itself
	 * 
	 * Types of advice (timings):
	 * 		@Before: executes before the method runs
	 * 		@After: executes after the method runs
	 * 		@AfterReturning: executes after the method returns SUCCESFULLY (including void return types)
	 * 		@AfterThrowing: executes after something is thrown 
	 * 		@Around: it's the most POWERFUL. It can alter logic during, before or after. 
	 * 
	 * JoinPoint are the possible points in the runtime of the program WHERE an advice can be invoked. In Spring AOP, 
	 * 	JoinPoints take the form of an object injected into an advice by Spring AOP. Theis object contains informatino 
	 * 	about the current state of the runtime. 
	 * 
	 * Pointcuts are expressions that are used to select SPECIFIC joinpoints. In other words, pointcuts target a subset of all 
	 * 	joinpoints. Pointcuts take the form of a expression ( a string the uses regular expressions).
	 * 
	 * Pointcut expression symbols:
	 * "*" is our wildcard for return types and method naming 
	 * ".." is our wildcard for our parameter list (any number of parameters)
	 * "*" is ALSO our wildcard for a SINGLE parameter in the parameter list
	 */
	
	
//	@Before("execution(* *(..))") //The string s an example of an advice 
//	@Before("execution(* *doing*(..))")
//	@Before("execution(int *doing*(..))")
//	@Before("execution(protected* *do*(*,*))")
//	@Before("execution(* *(char, int))")
	@Before("execution(* *(Integer))")
//	public void beforeStuff(JoinPoint jp) {
//		System.out.println("------------------------------------------------");
////		System.out.println("Hello, doing stuff before! " + jp.getSignature());
//		System.out.println("Warming up!");
//	}
//	
//	@After("execution(* *(..))")
//	public void afterStuff(JoinPoint jp) {
//		System.out.println("Relaxing ");
//		System.out.println("------------------------------------------------");
//	}
	
	@Around("execution(* *anotherMethod*(..))")
	public void aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		
		
		System.out.println("INSIDE ADVICE ");
		
		System.out.println("Doing stuff before!");
		
		System.out.println(pjp.getArgs());
		
		pjp.proceed(); //will cause issues with parameters that are not objects 
		
		System.out.println("Doing stuff after");
	}

}
