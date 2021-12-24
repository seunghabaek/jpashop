package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delievery {

    @Id @GeneratedValue
    @Column(name = "delievery_id")
    private String id;

    @OneToOne(mappedBy = "delievery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)//ordinary 쓰지말 것. 순서 문제.
    private DelieveryStatus status; //enum type [READY, COMP]
}
