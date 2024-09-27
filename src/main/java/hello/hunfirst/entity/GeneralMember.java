package hello.hunfirst.entity;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.List;

@Entity
@Getter
public class GeneralMember  {

    @Id
 //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GENERAL_MEMBER_ID")
    private String userId;

    private String password;

    private String name;

    private String location;

    private Long age;

    @OneToMany(mappedBy = "generalMember") // Like 엔티티의 member 필드를 참조
    private List<Liked> likedList;

    @OneToMany(mappedBy = "generalMember") // Inquiry 엔티티의 필드를 참조하는 관계 설정
    private List<Inquiry> inquiryList;

    @OneToMany(mappedBy = "generalMember") // Comment 엔티티의 필드를 참조하는 관계 설정
    private List<Comment> commentList;


    // 기본 생성자 (JPA에서 필수)
    public GeneralMember() {}

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }


    public void updatePassword(String password) {
        this.password=password;
    }

    public void updateName(String name) {
        this.name=name;
    }

    public void updateLocation(String location) {
        this.location=location;
    }

    public void updateAge(Long age){
        this.age=age;
    }


}
