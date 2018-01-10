import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

public class SimpleAdvice {
    public static void main(String[] args) {
        GuineaPig pig = new GuineaPig();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(pig);
        factory.addAdvice(new MyAdvice());

        GuineaPig proxy = (GuineaPig) factory.getProxy();

        pig.sayWorld();

        System.out.println("___________________");

        proxy.sayWorld();
    }
}

class GuineaPig {
    public void sayWorld() {
        System.out.println("World");
    }
}

class MyAdvice implements MethodInterceptor {
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Hello");
        Object obj = methodInvocation.proceed();
        System.out.println("!");
        return obj;
    }
}