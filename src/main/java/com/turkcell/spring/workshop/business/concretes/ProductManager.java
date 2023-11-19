package com.turkcell.spring.workshop.business.concretes;

import com.turkcell.spring.workshop.business.abstracts.ProductService;
import com.turkcell.spring.workshop.core.exceptions.BusinessException;
import com.turkcell.spring.workshop.entities.Product;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForAddDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForListingDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForListingIdDto;
import com.turkcell.spring.workshop.entities.dtos.Product.ProductForUpdateDto;
import com.turkcell.spring.workshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final MessageSource messageSource;

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

    public void addProduct(ProductForAddDto request) {
        productWithSameNameShouldNot(request.getProductName());
        // addProductUnitPriceControl(request);

        /*Product newProduct = Product.builder()
                .productName(request.getProductName())
                .unitPrice(request.getUnitPrice())
                .unitsInStock(request.getUnitsInStock())
                .category(Category.builder().categoryId(request.getCategoryId()).build())
                .supplier(Supplier.builder().supplierID(request.getSupplierID()).build())
                .discontinued(0)
                .build();

        productRepository.save(newProduct);*/

        Product productFromAutoMapping = modelMapper.map(request, Product.class);
        productRepository.save(productFromAutoMapping);
    }

    //Siparişe eklenen her ürünün stok adeti quantity kadar azaltılmalıdır. +
    public void setUnitInStock(short quantity, int productID) {
        Product product = productRepository.findByProductID(productID);
        product.setUnitsInStock((short) (product.getUnitsInStock() - quantity));
        productRepository.save(product);

    }

   /* private void addProductUnitPriceControl(ProductForAddDto request) {
        // Product existingProduct = productRepository.findByUnitPrice(unitPrice);
        if (request.getUnitPrice() < 0) {
            //throw new BusinessException("Ürün fiyatı 0'dan küçük olmamalıdır.");
            throw new BusinessException
                    (messageSource.getMessage("productPriceNotNegative", null, LocaleContextHolder.getLocale()));
        }
    }*/

    private void productWithSameNameShouldNot(String productName) {
        Product productWithSameName = productRepository.findByProductName(productName);
        if (productWithSameName != null)
            //throw new BusinessException("Aynı ürün mevcut.Başka ürün ismi giriniz.");
            throw new BusinessException
                    (messageSource.getMessage("productNameAllReady", null, LocaleContextHolder.getLocale()));

    }

   /* private void productWithSameNameShouldNotExist(String productName) {
        //  Product productWithSameName = productRepository.findByProductName(productName);
        if (productName == null || !productName.startsWith("HND")) {
            //throw new BusinessException("Ürün adı 'HND' ile başlamalıdır.");
            throw new BusinessException
                    (messageSource.getMessage("productNameStartHND", null, LocaleContextHolder.getLocale()));
        }
    }*/

    //ürün günceller
    @Override
    public void updateProduct(int productID, ProductForUpdateDto product) {

        productWithSameNameShouldNot(product.getProductName());

        //productWithSameNameShouldNotExist(product.getProductName());

        productWithSameNameShouldNot(product.getProductName());

        /*Product productToUpdate = returnProductByIdIfExists(productID);

        productToUpdate.setProductName(product.getProductName());
        productToUpdate.setUnitPrice(product.getUnitPrice());*/
        Product productFromAutoMapping = modelMapper.map(product, Product.class);
        productRepository.save(productFromAutoMapping);

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
            //throw new BusinessException("Böyle bir ürün bulunamadı.");
            throw new BusinessException
                    (messageSource.getMessage("productNotFound", null, LocaleContextHolder.getLocale()));
        return productToDelete;
    }
}