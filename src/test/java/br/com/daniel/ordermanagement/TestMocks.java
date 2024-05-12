package br.com.daniel.ordermanagement;

import br.com.daniel.ordermanagement.entity.Category;
import br.com.daniel.ordermanagement.entity.Orders;
import br.com.daniel.ordermanagement.entity.Product;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestMocks {
  public static final Orders firstOrder =
      Orders.builder().orderId(1).orderDate(LocalDateTime.parse("2021-10-10T00:00:00")).build();

  public static final Orders secondOrder =
      Orders.builder().orderId(2).orderDate(LocalDateTime.parse("2022-10-10T00:00:00")).build();

  public static final Category foodCategory =
      Category.builder().categoryId(1).categoryName("food").productList(new ArrayList<>()).build();

  public static final Category healthCategory =
      Category.builder()
          .categoryId(2)
          .categoryName("health")
          .productList(new ArrayList<>())
          .build();

  public static final Product beer = Product.builder().productId(1).productName("beer").build();

  public static final Product toothPaste =
      Product.builder().productId(2).productName("toothPaste").build();
}
