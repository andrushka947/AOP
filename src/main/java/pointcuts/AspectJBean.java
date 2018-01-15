package pointcuts;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AspectJBean {
    public void foo() {
        System.out.println("foo");
    }
    public void foo1(int x) {
        System.out.println("x: " + x);
    }
    public void bar() {
        System.out.println("bar");
    }

    public static void main(String[] args) {
        AspectJBean bean = new AspectJBean();
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression("execution(* foo*( .. ))");

        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(bean);
        factory.addAdvisor(advisor);

        AspectJBean proxy = (AspectJBean) factory.getProxy();

        proxy.bar();
        proxy.foo();
        proxy.foo1(1);


    }
}
