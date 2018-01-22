package advisor;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

public class NameMatchAdvisor {
    public static void main(String[] args) {

        SimpleBean bean = new SimpleBean();

        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(new SimpleAdvice());
        advisor.addMethodName("foo");
        advisor.addMethodName("bar");

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(bean);

        SimpleBean proxy = (SimpleBean) factory.getProxy();
        proxy.foo();
        proxy.bar();
        proxy.method();

    }


}

class SimpleBean {
    public void foo() {
        System.out.println("foo");
    }
    public void bar() {
        System.out.println("bar");
    }
    public void method() {
        System.out.println("method");
    }
}
