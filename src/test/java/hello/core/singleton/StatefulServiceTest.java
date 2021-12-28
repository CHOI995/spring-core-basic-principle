package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
	
	@Test
	void statefulServiceSingleton() {
		ApplicationContext ac =  new AnnotationConfigApplicationContext(TestingConfig.class);

		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);
		
		// ThreadA: A사용자 10000원 주문
		int userAPrice = statefulService1.order("userA", 10000);
		// ThreadB: B사용자 20000원 주문
		int userBPrice = statefulService2.order("userA", 20000);
		// ThreadA: 사용자A 주문 금액 조회
		
		System.out.println("price = " + userAPrice);
		
		assertThat(userAPrice).isEqualTo(10000);
		
		
	}
	
	static class TestingConfig {
		
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}

}