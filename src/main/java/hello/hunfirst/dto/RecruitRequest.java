package hello.hunfirst.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecruitRequest {

    private Long recruitId;

    @NotBlank(message = "제목은 필수 항목입니다.")
    private String title;

    @NotBlank(message = "시작 날짜는 필수 항목입니다.")
    private String startDate;

    @NotBlank(message = "종료 날짜는 필수 항목입니다.")
    private String endDate;

    @NotBlank(message = "내용은 필수 항목입니다.")
    private String content;

    @NotBlank(message = "선호도는 필수 항목입니다.")
    private String favor;
    //DTO 사용 이유 : entity를 갖고오기엔 너무 무거워!

}
