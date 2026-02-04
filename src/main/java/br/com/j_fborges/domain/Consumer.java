package br.com.j_fborges.domain;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Entity
@Table(name = "TB_CONSUMER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consumer{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consumer_seq")
    @SequenceGenerator(name = "consumer_seq", sequenceName = "sq_consumer", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "ID_NUMBER", nullable = false, unique = true)
    private Long idNumber;

    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Column(name = "TELEPHONE", nullable = false)
    private Long tel;

    @Column(name = "ADDRESS", nullable = false, length = 100)
    private String address;

    @Column(name = "ADDRESS_NUMBER", nullable = false)
    private Integer addressNumber;

    @Column(name = "CITY", nullable = false, length = 100)
    private String city;

    @Column(name = "STATE", nullable = false, length = 50)
    private String state;

    public Consumer(String id, String name, String idNumber, String email, String tel, String address, String addressNumber, String city, String state) {
        this.id = Long.valueOf(id);
        this.name = name;
        this.idNumber = Long.valueOf(idNumber);
        this.email = email;
        this.tel = Long.valueOf(tel);
        this.address = address;
        this.addressNumber = Integer.valueOf(addressNumber);
        this.city = city;
        this.state = state;
    }

    public Consumer(String name, String idNumber, String email, String tel, String address, String addressNumber, String city, String state) {
        this.name = name;
        this.idNumber = Long.valueOf(idNumber);
        this.email = email;
        this.tel = Long.valueOf(tel);
        this.address = address;
        this.addressNumber = Integer.valueOf(addressNumber);
        this.city = city;
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return Objects.equals(idNumber, consumer.idNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idNumber);
    }
}
