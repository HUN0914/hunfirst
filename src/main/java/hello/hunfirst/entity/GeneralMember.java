package hello.hunfirst.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;  // 유효성 검사를 위한 올바른 @NotNull 패키지

import java.util.List;


@Entity
@Data
@NotNull
public class GeneralMember  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GENERAL_MEMBER_ID")
    private String userId;

    private String password;

    private String name;

    private String location;

    private Long age;

    @OneToMany
    @JoinColumn(name="Inquiry_ID")
    private List<Inquiry> inquiryList;

    @OneToMany
    @JoinColumn(name="COM_ID")
    private List<Comment> commentList;



    // 기본 생성자 (JPA에서 필수)
    public GeneralMember() {}

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }


}
