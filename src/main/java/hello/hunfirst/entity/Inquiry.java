package hello.hunfirst.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Inquiry_ID")
    private Long inquiryId;

    @NotNull
    private String title;
    @NotNull
    private String content;

    @ManyToOne
    @JoinColumn(name="GENERAL_MEMBER_ID")
    private GeneralMember generalMember;

    @OneToMany(mappedBy = "inquiry")
    private List<Comment> commentList;

}
