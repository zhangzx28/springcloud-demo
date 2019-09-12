package com.person.product.server.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class ProductCategory {

  @Id
  @GeneratedValue
  private Integer categoryId;
  private String categoryName;
  private Integer categoryType;
  private Date createTime;
  private Date updateTime;
}
