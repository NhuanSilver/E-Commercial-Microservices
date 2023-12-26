/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 11:10 PM
 *  * Last Modified: 12/26/23, 11:10 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.implement.serivceImpl;

import com.ecommercial.productservice.base.exception.ProductServiceException;
import com.ecommercial.productservice.base.mongo.BaseRepository;
import com.ecommercial.productservice.model.input.CreateProductInput;
import com.ecommercial.productservice.model.input.UpdateProductInput;
import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.model.product.ProductDetail;
import com.ecommercial.productservice.model.product.ProductVariant;
import com.ecommercial.productservice.model.size.DimensionUnit;
import com.ecommercial.productservice.model.weight.WeightUnit;
import com.ecommercial.productservice.repository.ProductRepository;
import com.ecommercial.productservice.service.ProductService;
import com.ecommercial.productservice.utils.GeneralIdUtils;
import com.ecommercial.productservice.utils.MoneyCalculateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseRepository implements ProductService {
    @Autowired
    private final ProductRepository productRepository;

    @Override
    public ProductDetail createProduct(CreateProductInput productInput) throws ProductServiceException {
        validateProductVariantInput(productInput);
        Product product = productInput.getProduct();
        product.setId(generateId());
        product.setCreatedAt(new Date());

        double mediumPrice = 0;
        List<ProductVariant> productVariant = productInput.getProductVariants();
        for (ProductVariant variant : productVariant) {
            variant.setId(GeneralIdUtils.generateId());
            variant.setProductId(product.getId());
            variant.setProductName(product.getName());
            variant.setCreatedAt(new Date());
            variant.setWeightUnit(WeightUnit.GRAMS);
            variant.getDimension().setDimensionUnit(DimensionUnit.CM);
            variant.setSalePrice(MoneyCalculateUtils.calculateDiscount(variant.getPrice().getAmount(), variant.getDiscount()));
            mediumPrice += variant.getPrice().getAmount();

        }
        mediumPrice = mediumPrice / productVariant.size();

        product.setMediumPrice(MoneyCalculateUtils.getMoney(mediumPrice));
        product.setSalePrice(MoneyCalculateUtils.calculateDiscount(mediumPrice, product.getDiscount()));

        return productRepository.createProduct(product, productVariant);
    }

    @Override
    public Product getProductById(String id) throws ProductServiceException {
        Product product = productRepository.getProductById(id);
        validateProduct(product);
        return productRepository.getProductById(id);
    }

    @Override
    public ProductDetail getProductDetailById(String id) throws ProductServiceException {
        ProductDetail productDetail = productRepository.getProductDetailById(id);
        validateProduct(productDetail.getProduct());
        return productRepository.getProductDetailById(id);
    }

    @Override
    public Product updateProduct(String id, UpdateProductInput updateProductInput) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {
    }

    @Override
    public List<Product> pagingProduct(int page, int size) {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProduct() throws ProductServiceException {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            validateProduct(product);
        }
        return productRepository.findAll();
    }

    @Override
    public ProductVariant getProductVariantById(String variantId) throws ProductServiceException {
        ProductVariant data = productRepository.getProductVariantById(variantId);
        if (null == data) {
            throw new ProductServiceException("not_found", "Không tìm thấy biến thể sản phẩm", "Product variant is empty");
        }
        return productRepository.getProductVariantById(variantId);
    }

    //    validate input

    private void validateProductVariantInput(CreateProductInput productInput) throws ProductServiceException {
        if (null == productInput) {
            throw new ProductServiceException("not_found", "Vui lòng nhập thông tin của sản phẩm biến thể", "Product variant is empty");
        }
        if (null == productInput.getProduct()) {
            throw new ProductServiceException("not_found", "Vui lòng nhập thông tin của sản phẩm", "Product is empty");
        }
        if (null == productInput.getProduct().getDescription()) {
            throw new ProductServiceException("not_found", "Vui lòng nhập mô tả của sản phẩm", "Product description is empty");
        }
        if (null == productInput.getProduct().getFeaturedImageUrl()) {
            throw new ProductServiceException("not_found", "Vui lòng nhập hình ảnh mô tả của sản phẩm", "Product featured image url is empty");

        }
        if (null == productInput.getProduct().getIndustrialId()) {
            throw new ProductServiceException("not_found", "Vui lòng chọn nghành hàng của sản phẩm", "Product industrial type is empty");

        }
        if (null == productInput.getProduct().getTradeMarkId()) {
            throw new ProductServiceException("not_found", "Vui lòng chọn thương hiệu của sản phẩm", "Trade mark is empty");
        }
        if (null == productInput.getProduct().getName()) {
            throw new ProductServiceException("not_found", "Vui lòng tên của sản phẩm", "Product name is empty");

        }


        validateVariants(productInput.getProductVariants());
    }

    private void validateVariants(List<ProductVariant> productVariantList) throws ProductServiceException {
        for (ProductVariant variant : productVariantList) {
            if (null == variant)
                throw new ProductServiceException("not_found", "Vui lòng truyền thông tin biến thể của sản phầm", "Product variant is empty");
            if (null == variant.getColor()) {
                throw new ProductServiceException("not_found", "Vui lòng chọn màu của sản phầm", "Product variant color is empty");
            }
            if (null == variant.getPrice() || variant.getPrice().getAmount() <= 0) {
                throw new ProductServiceException("not_found", "Vui lòng nhập giá của sản phầm", "Product variant price is empty");
            }
            if (variant.getWeight() <= 0 || variant.getWeight() > 1600000) {
                throw new ProductServiceException("invalid_data", " Vui lòng nhập khối lượng trong khoảng 0 < và < 1.600.000 gram.", "ProductVariant.weight is null or <= 0");

            }
            if (variant.getImageUrl() == null) {
                throw new ProductServiceException("invalid_data", "Vui lòng nhập hình ảnh của biến thể sản phẩm.", "ProductVariant.imageUrl is null");
            }
            if (null == variant.getDimension()) {
                throw new ProductServiceException("invalid_data", " Vui lòng nhập thông tin  kích thước.", "ProductVariant.Dimension is null");
            }
            if (variant.getDimension().getLength() <= 0 || variant.getDimension().getLength() > 200) {
                throw new ProductServiceException("invalid_data", " Vui lòng nhập chiều dài trong thông tin  kích thước > 0 và < 200 cm.", "ProductVariant.Dimension.lenght is null or < 0");

            }
            if (variant.getDimension().getWidth() <= 0 || variant.getDimension().getWidth() > 200) {
                throw new ProductServiceException("invalid_data", " Vui lòng nhập chiều rộng trong thông tin  kích thước > 0 và < 200 cm.", "ProductVariant.Dimension.weight is null or < 0");

            }
            if (variant.getDimension().getHeight() <= 0 || variant.getDimension().getHeight() > 200) {
                throw new ProductServiceException("invalid_data", " Vui lòng nhập chiều cao trong thông tin  kích thước > 0 và < 200 cm.", "ProductVariant.Dimension.height is null or < 0");

            }
            long quantityAvailable = variant.getQuantityAvailable();
            if (quantityAvailable <= 0) {
                throw new ProductServiceException("not_found", "Vui lòng nhập số lượng sản phẩm", "Product quantity available is empty or not positive");
            }
        }
    }

    private void validateProduct(Product product) throws ProductServiceException {
        if (null == product) {
            throw new ProductServiceException("not_found", "Không tìm thấy sản phẩm", "Product is empty");
        }
    }

}
