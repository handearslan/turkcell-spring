package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.ProductService;
import com.turkcell.spring.workshop.business.exceptions.BusinessException;
import com.turkcell.spring.workshop.entities.Category;
import com.turkcell.spring.workshop.entities.Product;
import com.turkcell.spring.workshop.entities.Supplier;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForUpdateDto;
import com.turkcell.spring.workshop.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override //Tüm ürünleri getirir.
    public List<ProductForListingDto> getAll() {

        return productRepository.getForListing();
    }


    //Id 'ye göre ürün getirir.
    public List<ProductForListingIdDto> getById(int productID) {

        return productRepository.getForListingId(productID);
    }

    public float getByUnitPrice(int productID) {

        return productRepository.findByProductID(productID).getUnitPrice();
    }

    @Override
    public short getUnitInStock(int productID) {
        return productRepository.findByProductID(productID).getUnitsInStock();
    }



    //ürün ekler
    public void addProduct(ProductForAddDto request) {
        productWithSameNameShouldNot(request.getProductName());
        // addProductUnitPriceControl(request);


        Product newProduct = Product.builder()
                .productName(request.getProductName())
                .unitPrice(request.getUnitPrice())
                .unitsInStock(request.getUnitsInStock())
                .category(Category.builder().categoryId(request.getCategoryId()).build())
                .supplier(Supplier.builder().supplierID(request.getSupplierID()).build())
                .discontinued(0)
                .build();

        productRepository.save(newProduct);

    }

    public void setUnitInStock(short quantity, int productID){
        Product product = productRepository.findByProductID(productID);
        product.setUnitsInStock((short) (product.getUnitsInStock()-quantity));
        productRepository.save(product);

    }

//Siparişe eklenen her ürünün stok adeti quantity kadar azaltılmalıdır.

   /* private void addProductUnitPriceControl(ProductForAddDto request) {
        // Product existingProduct = productRepository.findByUnitPrice(unitPrice);
        if (request.getUnitPrice() < 0) {
            throw new BusinessException("Ürün fiyatı 0'dan küçük olmamalıdır.");
        }
    }*/

    private void productWithSameNameShouldNot(String productName) {
        Product productWithSameName = productRepository.findByProductName(productName);
        if (productWithSameName != null)
            throw new BusinessException("Aynı ürün mevcut.Başka ürün ismi giriniz.");
    }

   /* private void productWithSameNameShouldNotExist(String productName) {
        //  Product productWithSameName = productRepository.findByProductName(productName);
        if (productName == null || !productName.startsWith("HND")) {
            throw new BusinessException("Ürün adı 'HND' ile başlamalıdır.");
        }
    }*/

    //ürün günceller
    @Override
    public void updateProduct(int productID, ProductForUpdateDto product) {

        productWithSameNameShouldNot(product.getProductName());

        //productWithSameNameShouldNotExist(product.getProductName());

        productWithSameNameShouldNot(product.getProductName());

        Product productToUpdate = returnProductByIdIfExists(productID);

        productToUpdate.setProductName(product.getProductName());
        productToUpdate.setUnitPrice(product.getUnitPrice());

    }

    //ürün siler
    public void deleteProduct(int productID) {
        Optional<Product> optionalProduct = productRepository.findById(productID);

        Product productToDelete = returnProductByIdIfExists(productID);

        productRepository.delete(productToDelete);
    }

    private Product returnProductByIdIfExists(int productID) {
        Product productToDelete = productRepository.findById(productID).orElse(null);
        if (productToDelete == null)
            throw new BusinessException("Böyle bir sipariş bulunamadı.");
        return productToDelete;
    }
}

    /*Ürün ismi 3 haneden kısa olamaz.
        Supplier id ve category id boş geçilemez ve 0'dan küçük eşit olamaz
        Unit Price boş geçilemez ve 0'dan küçük olamaz
        Stock bilgisi 0dan küçük olamaz
        Birebir aynı isimde ikinci ürün eklenemez.*/







