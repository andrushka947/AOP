package pointcuts;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class SimpleNameMatchMethodPointcut {
    public static void main(String[] args) {
        NameBean bean = new NameBean();
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("foo");

        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(bean);
        factory.addAdvisor(advisor);

        NameBean proxy = (NameBean) factory.getProxy();

        proxy.foo();
        proxy.foo(1);
        proxy.bar();


    }

}

class NameBean {
    public void foo() {
        System.out.println("foo");
    }
    public void foo(int x) {
        System.out.println("x: " + x);
    }
    public void bar() {
        System.out.println("bar");
    }
}
