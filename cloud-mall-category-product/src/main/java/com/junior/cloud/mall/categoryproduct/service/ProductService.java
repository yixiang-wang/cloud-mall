package com.junior.cloud.mall.categoryproduct.service;

import com.github.pagehelper.PageInfo;
import com.junior.cloud.mall.categoryproduct.model.pojo.Product;
import com.junior.cloud.mall.categoryproduct.model.request.AddProductReq;
import com.junior.cloud.mall.categoryproduct.model.request.ListProductReq;
import com.junior.cloud.mall.categoryproduct.model.request.UpdateProductReq;
import com.junior.cloud.mall.common.exception.ProductException;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

public interface ProductService {
    void addProduct(AddProductReq productReq) throws ProductException;

    @Transactional(rollbackFor = Exception.class)
    void updateProduct(UpdateProductReq productReq) throws ProductException;

    @Transactional(rollbackFor = Exception.class)
    void deleteProduct(Integer id) throws ProductException;

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo selectAllAdmin(Integer pageNum, Integer pageSize);

    PageInfo userList(ListProductReq productReq);

    Product getDetail(Integer id);

    void batchByExcel(File destFile) throws IOException, ProductException;

    void updateForFeign(Integer productId, Integer stock);
}
