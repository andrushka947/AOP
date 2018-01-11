import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class AfterAdvice {
    public static void main(String[] args) {

        Food food = new Food();
        //food.food();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(food);
        factory.addAdvice(new MyAfterReturnAdvice());

        Food proxy = (Food) factory.getProxy();
        proxy.food();

    }
}

class Food{
    public void food() {
        System.out.println("At first food comes...");
    }
}

class MyAfterReturnAdvice implements AfterReturningAdvice {
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("Then money comes:)");
    }
}