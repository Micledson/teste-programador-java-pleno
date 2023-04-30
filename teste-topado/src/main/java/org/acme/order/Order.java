package org.acme.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @CreationTimestamp
    private Date date;

    @Column(name = "total_value")
    private Double totalValue;

}
