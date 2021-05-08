package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비지니스 로직==// 도메인 주도 설계라고 할 때 엔티티 자체가 해결 할 수 있는 것들이나 엔티티 안에 비지니스 로직을 넣는게 좋다. 객체지향
    // 재고수량이 Item 엔티티 안에 있는데 이 데이터를 가지고 있는 거에서 비지니스 로직이 나가는게 응집도가 있기 때문
    // 데이터를 가지고 있는 쪽에 비지니스 메소드가 있는 게 좋음. 관리의 장점도 있음.
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0)
            throw new NotEnoughStockException("need more stock");
        this.stockQuantity = restStock;
    }
}
