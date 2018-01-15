package pointcuts;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcutAdvisor {
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        System.out.println("Static check: " + method.getName());
        return "foo".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return aClass -> aClass == FooBar.class;
    }
}

class StaticPointcutAdvice implements MethodInterceptor{
    public static void main(String[] args) {
        FooBar cls = new FooBar();
        Advisor advisor = new DefaultPointcutAdvisor(new SimpleStaticPointcut(), new StaticPointcutAdvice());

        ProxyFactory proxy = new ProxyFactory();
        proxy.addAdvisor(advisor);
        proxy.setTarget(cls);

        FooBar proxyCls = (FooBar) proxy.getProxy();

        proxyCls.bar();
        proxyCls.foo();

    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Invoking: " + methodInvocation.getMethod().getName());
        Object result = methodInvocation.proceed();
        System.out.println("Done: " + methodInvocation.getMethod().getName());
        return result;
    }
}

class FooBar {
    public void foo() {
        System.out.println("foo");
    }

    public void bar() {
        System.out.println("bar");
    }
}