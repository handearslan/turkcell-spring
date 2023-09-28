package com.turkcell.spring.workshop.repositories;

import com.turkcell.spring.workshop.entities.Category;
import com.turkcell.spring.workshop.entities.dtos.CategoryForListingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

    List<Category> findByCategoryNameContaining(String categoryName);

    //List<Category> findByIdGreaterThanEqual(int id);
    //   List<Category> findByDescription(String description);

    //Native SQL
    //JPQL -> JPA nın SQL e neredeyse birebir benzer versiyonu
    //JPQL -> Table ismi yerine entity yazmak

    @Query(value = "SELECT c FROM Category c WHERE c.categoryName LIKE %:name%", nativeQuery = false)
    List<Category> search(@Param("name") String categoryName);

    @Query(value = "SELECT * FROM categories WHERE category_name LIKE %:categoryName%", nativeQuery = true)
    List<Category> searchNative(String categoryName);

    @Query(value="SELECT new " +
            "com.turkcell.spring.workshop.entities.dtos.CategoryForListingDto" +
            "(c.categoryId, c.categoryName) FROM Category c")
    List<CategoryForListingDto> getForListing();

}
