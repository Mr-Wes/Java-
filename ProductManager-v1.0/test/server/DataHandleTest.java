package server;

import org.junit.jupiter.api.Test;

class DataHandleTest {

	@Test
	void test() {
		//1:测试socket连接是否成功
		//2：测试读写是否能用
		DataHandle.getInstance().test();
		
	}

}
