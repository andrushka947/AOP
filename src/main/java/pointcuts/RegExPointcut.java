package pointcuts;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

public class RegExPointcut {
    public static void main(String[] args) {

        RegExBean bean = new RegExBean();
        JdkRegexpMethodPointcut pc = new JdkRegexpMethodPointcut();
        pc.setPattern(".*foo*.");
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(bean);

        RegExBean proxy = (RegExBean) factory.getProxy();
        proxy.foo();
        proxy.foo1(1);
        proxy.bar();

    }

}

class RegExBean {
    public void foo() {
        System.out.println("foo");
    }
    public void foo1(int x) {
        System.out.println("x: " + x);
    }
    public void bar() {
        System.out.println("bar");
    }
}
