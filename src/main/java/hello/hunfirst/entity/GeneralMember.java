package hello.hunfirst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "General_Member") // 원하는 테이블 이름 지정
@NotNull
public class GeneralMember  {

    @Id
    @Column(name = "USER_ID", updatable = false, nullable = false, unique = true)
    private String userId; // 자동 생성되는 식별자

    private String password;
    private String name;
    private String location;
    private Long age;

    @OneToMany(mappedBy = "generalMember")
    private List<Liked> likedList;

    @OneToMany(mappedBy = "generalMember")
    private List<Inquiry> inquiryList;

    @OneToMany(mappedBy = "generalMember")
    private List<Comment> commentList;

    // 기본 생성자 (JPA에서 필수)
    public GeneralMember() {}

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}
