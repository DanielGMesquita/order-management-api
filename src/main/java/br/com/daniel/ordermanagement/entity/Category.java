package br.com.daniel.ordermanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "category")
@Data
@Builder
@ToString(exclude = "productList")
@EqualsAndHashCode(exclude = "productList")
@NoArgsConstructor
@AllArgsConstructor
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Integer categoryId;

  @Column(name = "category_name")
  private String categoryName;

  @OneToMany(mappedBy = "category")
  @JsonManagedReference
  private List<Product> productList;
}
