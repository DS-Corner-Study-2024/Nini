package com.springboot.relationship.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name="provider")
public class Provider extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // @OneToMny의 기본 fetch 전략이 Lazy이기 때문에 즉시 로딩으로 조정
    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Product> productList = new ArrayList<>();
}