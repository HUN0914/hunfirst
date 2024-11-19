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

	@Test
	public void testFixRecruit() {
		// Given
		Long recruitId = 1L;
		String newTitle = "새로운 제목";
		String newContent = "새로운 내용";
		String newStartDate = "2024-01-01";
		String newEndDate = "2024-12-31";
		String newFavor = "선호도2";

		recruitService.updateRecruit(recruitId, newTitle, newContent, newStartDate, newEndDate, newFavor);

		Recruit updatedRecruit = recruitService.findById(recruitId);
		assertEquals(newTitle, updatedRecruit.getTitle());
		assertEquals(newContent, updatedRecruit.getContent());
		assertEquals(newStartDate, updatedRecruit.getStartDate());
		assertEquals(newEndDate, updatedRecruit.getEndDate());
		assertEquals(newFavor, updatedRecruit.getFavor());
	}
}