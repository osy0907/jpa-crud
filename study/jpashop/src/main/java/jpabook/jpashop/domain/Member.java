package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String userName;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // order table 의 member field에 의해 매핑된 거울일 뿐
    private List<Order> orders = new ArrayList<>();

}
