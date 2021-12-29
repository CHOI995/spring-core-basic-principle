package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class NetworkClient {
	
	private String url;
	
	public NetworkClient() {
		System.out.println("생성자 호출, url = " + url);
		connect();
		call("초기화 연결 메시지");
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void call(String message) {
		System.out.println("call: " + url + "message = " + message);
	}
	
	public void connect() {
		System.out.println("connect : " + url);
	}
	
	// 서비스 종료시 호출
	public void disconnect() {
		System.out.println("close : "+ url);
	}

	@PostConstruct
	public void init() throws Exception {
		System.out.println("NetworkClient.init");
		connect();
		call("초기화 연결 메시지");
		
	}

	@PreDestroy
	public void close() throws Exception {
		System.out.println("NetworkClient.destroy");
		disconnect();
		
	}

}
