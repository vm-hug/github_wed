package com.ck.Service;

import com.ck.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    Product getProductById(long id);

    void deleteProductById(long id);
    List<Product> getAllProduct(String keyword);
    // code written below for pagination
    Page<Product> findPaginated(int pageNo , int pageSize , String sortField , String sortDir);
}
