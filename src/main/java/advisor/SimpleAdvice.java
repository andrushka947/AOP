package advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("________________________________________________________");
        System.out.println("Invoking: " + methodInvocation.getMethod().getName());
        Object result = methodInvocation.proceed();
        System.out.println("Done: " + methodInvocation.getMethod().getName());
        System.out.println("________________________________________________________");
        return result;
    }
}
