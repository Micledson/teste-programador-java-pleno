package org.acme.order;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.product.Product;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            )
    )
    @JsonManagedReference
    public List<Product> products;

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @CreationTimestamp
    private Date date;

    @Column(name = "total_value")
    private Double totalValue;

}
