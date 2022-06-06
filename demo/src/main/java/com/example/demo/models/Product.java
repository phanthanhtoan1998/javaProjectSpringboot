package com.example.demo.models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;


@Entity
@Table(name = "tblProduct")
public class Product {
    //primary
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) //AUTO
// you can also use "sequence" quy tac  tao ra truong id
//    @SequenceGenerator(name="Product_sequence"
//            ,sequenceName="Product_sequence"
//            ,allocationSize=1)
//
//    @GeneratedValue(strategy=GenerationType.SEQUENCE
//            ,generator="Product_sequence")


    private Long id;
    //  ràng buộc database =validate =contraint
    // nullable k  được phép null
    // unique  k được phép giống nhau
    //length  max  là 300  kt
    @Column(nullable = false, unique = true, length = 300)
    private String productName;
    private int year;
    private double price;
    private String url;

    public Product() {
    }
//    calculated fiedl =transient  : trường k được lưu trong csdl  nhưng được tính toán từ  trường  khác
    //Calendar lấy ra cái năm hiện tại
@Transient
private  int age ;
   public  int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR)- year ;
   }
    public Product(String productName, int year, double price, String url) {

        this.productName = productName;
        this.year = year;
        this.price = price;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getproductName() {
        return productName;
    }

    public void setproductName(String productName) {
        this.productName = productName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }
 //equals  2 truong product the nao la giong nhau
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return year == product.year && Double.compare(product.price, price) == 0 && age == product.age && Objects.equals(id, product.id) && Objects.equals(productName, product.productName) && Objects.equals(url, product.url);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, productName, year, price, url, age);
//    }
}
