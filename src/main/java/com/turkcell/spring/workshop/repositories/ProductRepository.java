package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.Product;
import com.turkcell.spring.workshop.entities.dtos.ProductForListingDto;
import com.turkcell.spring.workshop.entities.dtos.ProductForListingIdDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    //Derived Method
    List<Product> findByUnitPrice(double unitPrice);
    List<Product> UnitsInStockGreaterThanEqual(short UnitsInStock);
    List<Product> findByQuantityUnitIsNotNull();
    List<Product> findByQuantityUnitIsNull();


    //SELECT  product_name, unit_price FROM products
    //    WHERE unit_price BETWEEN 50 AND 100


    //JPQL
    @Query(value = "SELECT p FROM Product p WHERE p.unitPrice BETWEEN :a and :b")
    List<Product> search(@Param("a") float min, @Param("b") float max);

    //SELECT product_name,unit_price FROM products WHERE unit_price > ( SELECT AVG(unit_price) FROM products )
    @Query(value = "SELECT p FROM Product p WHERE p.unitPrice=18")
    List<Product> thenAvg();

    @Query(value = "SELECT p FROM Product p WHERE p.discontinued=1")
    List<Product> discontinued();


    //Native SQL

    @Query(value = "SELECT * FROM products WHERE unit_price > 30", nativeQuery = true)
    List<Product> searchNative();

    @Query(value = "SELECT * FROM products WHERE quantity_per_unit LIKE %:als%", nativeQuery = true)
    List<Product> searchNativeAls(String als);
    @Query(value="SELECT new " +
            "com.turkcell.spring.workshop.entities.dtos.ProductForListingDto(p.productID, " +
            "p.productName ," +
            " p.quantityPerUnit , p.unitPrice, p.unitsInStock,p.unitsOnOrder ," +
            "p.discontinued, p.quantityUnit) FROM Product p")
    List<ProductForListingDto> getForListing();

    @Query(value="SELECT new " +
            "com.turkcell.spring.workshop.entities.dtos.ProductForListingIdDto(p.productID, " +
            "p.productName ," +
            " p.quantityPerUnit , p.unitPrice, p.unitsInStock,p.unitsOnOrder , p.quantityUnit,p.reorderLevel) FROM Product p WHERE p.productID = :productID")
   List<ProductForListingIdDto> getForListingId(int productID );


}