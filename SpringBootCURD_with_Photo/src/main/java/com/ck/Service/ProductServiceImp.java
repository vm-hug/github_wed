package com.ck.Service;

import com.ck.models.Product;
import com.ck.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> optional = productRepository.findById(id);
        Product product = null;
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            throw new RuntimeException("Employee not found for id :: " + id);
        }
        return product;
    }

    @Override
    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProduct(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return productRepository.search(keyword);
        } else {
            return (List<Product>) productRepository.findAll();
        }
    }

    // code given below use for pagination nad sorting
    @Override
    public Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort =
                sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                        Sort.by(sortField).ascending() :
                        Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1 , pageSize , sort);
        return this.productRepository.findAll(pageable);
    }
}
