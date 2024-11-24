package hello.hunfirst;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hello.hunfirst.entity.Recruit;
import hello.hunfirst.service.RecruitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HunfirstApplicationTests {

	private final RecruitService recruitService;

	@Autowired
	public HunfirstApplicationTests(RecruitService recruitService) {
		this.recruitService = recruitService;
	}

	@Test
	void contextLoads() {
	}

}