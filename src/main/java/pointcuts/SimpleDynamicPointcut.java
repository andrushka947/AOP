package pointcuts;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut{
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        System.out.println("Static check for " + method.getName());
        return "withdraw".equals(method.getName());
    }

    @Override
    public boolean matches(Method method, Class<?> aClass, Object... objects) {
        System.out.println("Dynamic check for " + method.getName());
        double x = (double) objects[0];
        if (x > 100) {
            System.out.println("Not enough money in ATM");
            return false;
        }
        return true;
    }

    @Override
    public ClassFilter getClassFilter() {
        return aClass -> aClass == ATM.class;
    }
}

class ATM {
    public void withdraw(double amount) {
        System.out.println("Withdraw: " + amount);
    }

    public void deposit(double amount) {
        System.out.println("deposit: " + amount);
    }

    public static void main(String[] args) {
        ATM atm = new ATM();

        Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new SimpleAdvice());

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(atm);

        ATM proxy = (ATM) factory.getProxy();

        proxy.withdraw(99);
        proxy.withdraw(101);

        proxy.deposit(101);

    }
}

class SimpleAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("--------------------------------------------------------");
        System.out.println("Invoking: " + methodInvocation.getMethod().getName());
        Object result = methodInvocation.proceed();
        System.out.println("Done: " + methodInvocation.getMethod().getName());
        System.out.println("--------------------------------------------------------");
        return result;
    }
}