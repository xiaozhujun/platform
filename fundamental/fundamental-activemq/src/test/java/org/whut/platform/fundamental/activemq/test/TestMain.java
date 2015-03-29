package org.whut.platform.fundamental.activemq.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.jms.JMSException;
import javax.jms.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 15-3-21
 * Time: 下午2:30
 * To change this template use File | Settings | File Templates.
 */
public class TestMain {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new FileSystemXmlApplicationContext(System.getProperty("user.dir")
                + "\\fundamental\\fundamental-activemq\\src\\main\\resources\\META-INF\\spring\\activemq-applicationContext.xml");

        TestProducer producer = (TestProducer) context.getBean("testProducer");

        for(int i=0;i<6;i++) {
            TestListener listener = (TestListener) context.getBean("testListener");
            listener.register(new Queue() {
                @Override
                public String getQueueName() throws JMSException {
                    return TestConstants.TEST_TOPIC;
                }
            });
        }

        Executor executor = Executors.newFixedThreadPool(1000);

        for (int i=0; i<1000; i++) {
            executor.execute(new TestRunnable(producer));
        }

        Thread.sleep(1000);


    }

    static class TestRunnable implements Runnable {
        private TestProducer producer;

        TestRunnable(TestProducer producer) {
            this.producer = producer;
        }

        @Override
        public void run() {
            for (int i=0; i< 1000; i++) {
                String str = Thread.currentThread() + " " + Math.random()*1000;
                System.out.println("Stra: " + str);
                producer.dispatchMessage(str);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }
            }

        }
    }
}
