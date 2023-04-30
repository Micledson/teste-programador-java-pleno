package org.acme.client;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue

    private Long id;

    private String name;

    private String CPF;

    private String phone;

    private String email;
}