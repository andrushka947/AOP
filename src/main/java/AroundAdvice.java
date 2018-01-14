import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class AroundAdvice {
    public static void main(String[] args) {
        StringConcat sConcat = new StringConcat();

        ProxyFactory proxy = new ProxyFactory();
        proxy.addAdvice(new TimeCheckAdvice());
        proxy.setTarget(sConcat);

        StringConcat proxySConcat = (StringConcat) proxy.getProxy();
        System.out.println("s: " + proxySConcat.concat());

        StringBuilderConcat sbConcat = new StringBuilderConcat();
        proxy.setTarget(sbConcat);
        StringBuilderConcat proxySbConcat = (StringBuilderConcat) proxy.getProxy();

        System.out.println("sb: " + proxySbConcat.concat());
    }
}

class StringConcat {
    public String concat() {
        String result = "";
        for (int i = 0; i < 45000; i++) {
            result += i + ".";
        }
        return result;
    }
}

class StringBuilderConcat {
    public String concat() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 45000; i++) {
            result.append(i).append(".");
        }
        return result.toString();
    }
}

class TimeCheckAdvice implements MethodInterceptor {
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        StopWatch watch = new StopWatch();
        watch.start(methodInvocation.getMethod().getName());
        Object result = methodInvocation.proceed();
        watch.stop();

        dumpInfo(watch, methodInvocation);
        return result;
    }

    private void dumpInfo(StopWatch watch, MethodInvocation invocation) {
        Method m = invocation.getMethod();
        System.out.println("Method: " + m.getName());
        System.out.println("Arguments:");
        Arrays.stream(invocation.getArguments()).forEach(System.out::println);
        System.out.println("Return type:");
        System.out.println(m.getReturnType());

        System.out.println(watch.getTotalTimeMillis());


    }
}