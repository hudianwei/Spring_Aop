import com.ActionImpl.ActionImpl;
import com.IAction.IAction;
import com.Proxy.DynamicProxyHandler;
import com.Proxy.Proxy;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:applicationContext.xml")
public class TestDemo {
    /*
     * AOP（Aspect Oriented Programming），通常称为面向切面编程。它利用一种称为"横切"的技术，剖解开封装的对象内部，
     * 并将那些影响了多个类的公共行为封装到一个可重用模块，并将其命名为"Aspect"，即切面。所谓"切面"，简单说就是那些与业务无关，
     * 却为业务模块所共同调用的逻辑或责任封装起来，便于减少系统的重复代码，降低模块之间的耦合度，
     * 并有利于未来的可操作性和可维护性。*/
    public static void main(String[] args) {
       /* Proxy proxy = new Proxy(new ActionImpl());
        proxy.DoSomething();*/
       /* ActionImpl a = new ActionImpl();
        IAction dph = (IAction) java.lang.reflect.Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IAction.class}, new DynamicProxyHandler(a));
        dph.DoSomething();*/
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IAction action = (IAction) context.getBean("actionImpl");
        action.DoSomething();
    }

    /*
     * 静态代理，本质是通过构造函数进行注入，只需要关注业务逻辑本身，保证了业务的重用性。
     * 代理对象的一个接口只服务于一种类型的对象，如果要代理的方法很多，势必要为每一种方法都进行代理，静态代理在程序规模稍大时就无法胜任了。
     * */
    @Test
    public void StaticProxy() {
        Proxy proxy = new Proxy(new ActionImpl());
        proxy.DoSomething();
    }

    /*
     * 动态代理，生成的代理对象不需要修改，就可以代理多个目标类，多个目标方法
     *这里使用的是 JDK 动态代理，要求是必须要实现接口。与之对应的另外一种动态代理实现模式 Cglib，则不需要
     * */
    @Test
    public void Proxy() {
        IAction dph = (IAction) java.lang.reflect.Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IAction.class}, new DynamicProxyHandler(new ActionImpl()));
        dph.DoSomething();
    }

    /*
     *AOP实现原理也是动态代理
     * */
 /*   1.target：目标类，需要被代理的类。例如：ActionImpl
　　2.Joinpoint(连接点):所谓连接点是指那些可能被拦截到的方法。例如：所有的方法
　　3.PointCut 切入点：已经被增强的连接点。例如：DoSomething()
　　4.advice 通知/增强，增强代码。例如：after、before
　　5. Weaving(织入):是指把增强advice应用到目标对象target来创建新的代理对象proxy的过程.
　　6.proxy 代理类：通知+切入点
　　7. Aspect(切面): 是切入点pointcut和通知advice的结合*/
    @Test
    public void Aop() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IAction action = (IAction) context.getBean("actionImpl");
        action.DoSomething();
    }
}
