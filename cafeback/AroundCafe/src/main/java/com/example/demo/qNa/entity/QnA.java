package com.example.demo.qNa.entity;

import com.example.demo.member.entity.Member;
import com.example.demo.mypage.cafe.entity.CafeMenu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@RequiredArgsConstructor
@Entity
public class QnA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qna_no;

    @Column(length = 32, nullable = true)
    private Long received_no;

    @Column(length = 32, nullable = true)
    private String type;

    @CreatedDate
    @Column(length = 128, nullable = true)
    private String regDate;

    @PrePersist
    public void onPrePersist(){
        this.regDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "member_no")
    private Member memberInfo;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "qnA", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<QnAComment> comments = new HashSet<>();

    @Builder
    public QnA (Long received_no, Member member, String type){
        this.received_no = received_no;
        memberInfo = member;
        this.type = type;
    }

}
