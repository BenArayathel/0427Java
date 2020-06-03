package com.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 * Much like Hibernate annotations implmement JPA annotations,
 * Spring AOP creates an implementation of aspjectj. It simplifies aspectj, BUT it reduces the functionality as a result
 * Basically, Spring AOP abstracts aspectj from us.
 * 
 * What is an aspect?
 * 	An aspect is a modularization of cross-cutting concerns (CCCs)
 * 
 * Like everything in Java, it takes the form... of a class!
 * 
 * You can also think of an aspect as a collection of advice.
 */

@Component
@Aspect
public class AspectExample {
	
	/*
	 * What is an advice?
	 * 	An advice is an action taken by an aspect. In Spring AOP, advice is triggered by method invocations.
	 * 
	 * In Java, an advice takes the form of a method itself
	 * 
	 * types of advice (timings):
	 * 	@Before: executes before the method runs
	 * 	@After: executes after the method runs
	 * 	@AfterReturning: executes after the method returns successfully (including void types)
	 * 	@AfterThrowing: executes after something is thrown
	 * 	@Around: THE MOST POWERFUL ADVICE! It can alter logic during, before, or after execution.
	 * 
	 * A JoinPoint is the possible points in the runtime of the program WHERE an advice can be invoked. In Spring AOP
	 * 	JoinPoints take the form of an object injected into an advice by Spring AOP. This object contains information
	 * 	about the current state of the runtime
	 * 
	 * Pointcuts are expressions that are used to select SPECIFIC join points (e.g. target some set of methods). I.e, 
	 * 	pointcuts target a subset of all JoinPoints. Pointcuts take the form of a pointcut expression (similar to a regex string)
	 * 	"*" wildcard for return types and method naming ALSO our wildcard for a SINGLE parameter in the parameter list
	 * 	".." wildcard for our parameter list (any number of parameters)
	 * 	<access-modifier> for specifying a certain 
	 * 
	 */
	
	// This code will always be executed before our function!
//	@Before("execution(* *(..))")// This string is an example of an advice
	// First star is return type, second star is name
//	@Before("execution(* *doing*(..))")
//	@Before("execution(int *doing*(..))")
//	@Before("execution(protected* *do*(*,*))")
//	@Before("execution(* *(char, int))")
//	@Before("execution(* *(Integer))")
//	public void beforeStuff(JoinPoint jp) {
//		System.out.println("-------------------------------");
////		System.out.println("Hello, doing stuff before! "+jp.getSignature());
//		System.out.println("Warming up!");
//	}
//	
//	@After("execution(* *(..))")
//	public void afterStuff(JoinPoint jp) {
//		System.out.println("Relaxing.");
//		System.out.println("-------------------------------");
//	}
	
	@Around("execution(* *(..))")
	public void aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		
		for (Object o : pjp.getArgs()) {
			System.out.print('\t'+o.toString());
		}
		System.out.println();
		System.out.println("INSIDE ADVICE");
		
		System.out.println("Doing stuff before!");
		
		pjp.proceed();
		System.out.println("Doing stuff after!");
	}
}
