import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class Before {
    public static void main(String[] args) {

        MessageWriter writer = new MessageWriter();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(writer);
        factory.addAdvice(new BeforeAdvice());

        MessageWriter proxy = (MessageWriter) factory.getProxy();

        proxy.sayWorld();
    }
}

class MessageWriter {
    public void sayWorld() {
        System.out.println("World");
    }
}

class BeforeAdvice implements MethodBeforeAdvice {
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before method '" + method.getName() + "', I'd say...");
        //method.invoke(new MessageWriter()); also i can call a method here
    }
}
