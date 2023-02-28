package com.junior.cloud.mall.categoryproduct.controller;

import com.junior.cloud.mall.categoryproduct.common.ProductConstant;
import com.junior.cloud.mall.categoryproduct.model.request.AddProductReq;
import com.junior.cloud.mall.categoryproduct.model.request.UpdateProductReq;
import com.junior.cloud.mall.categoryproduct.service.ProductService;
import com.junior.cloud.mall.common.exception.MallExceptionEnum;
import com.junior.cloud.mall.common.exception.ProductException;
import com.junior.cloud.mall.common.utils.Constant;
import com.junior.cloud.mall.common.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class ProductController {
    @Resource
    private ProductService productService;

    @Value("${file.upload.uri}")
    String uri;

    @PostMapping("/admin/product/add")
    public ResponseUtils addProduct(@Valid @RequestBody AddProductReq productReq) throws ProductException {
        productService.addProduct(productReq);
        return ResponseUtils.success();
    }

    @PostMapping("/admin/product/update")
    public ResponseUtils updateProduct(@Valid @RequestBody UpdateProductReq productReq) throws ProductException {
        productService.updateProduct(productReq);
        return ResponseUtils.success();
    }

    @PostMapping("/admin/product/delete")
    public ResponseUtils deleteProduct(Integer id) throws ProductException {
        productService.deleteProduct(id);
        return ResponseUtils.success();
    }

    @PostMapping("/admin/upload/file")
    public ResponseUtils uploadFile(@RequestParam("file") MultipartFile file) throws ProductException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + suffixName;
        File fileDirectory = new File(ProductConstant.FILE_UPLOAD_DIR);
        File destFile = new File(ProductConstant.FILE_UPLOAD_DIR + newFileName);
        if (!fileDirectory.exists()) {
            if (!fileDirectory.mkdir()) {
                throw new ProductException(MallExceptionEnum.MKDIR_FAILED);
            }
        }
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String result;
            result = "http://"+uri+ "/images/" + newFileName;
        return ResponseUtils.success(result);
    }


    @PostMapping("/admin/product/batchUpdateSellStatus")
    public ResponseUtils batchUpdateSellStatus(Integer[] ids, Integer sellStatus) {
        productService.batchUpdateSellStatus(ids, sellStatus);
        return ResponseUtils.success();
    }

    @GetMapping("/admin/product/list")
    public ResponseUtils adminList(Integer pageNum, Integer pageSize) {
        return ResponseUtils.success(productService.selectAllAdmin(pageNum, pageSize));
    }

    @PostMapping("/admin/upload/product")
    public ResponseUtils batchByExcel(@RequestParam("file") MultipartFile file) throws ProductException, IOException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + suffixName;
        File fileDirectory = new File(ProductConstant.FILE_UPLOAD_DIR);
        File destFile = new File(ProductConstant.FILE_UPLOAD_DIR + newFileName);
        if (!fileDirectory.exists()) {
            if (!fileDirectory.mkdir()) {
                throw new ProductException(MallExceptionEnum.MKDIR_FAILED);
            }
        }
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productService.batchByExcel(destFile);
        return ResponseUtils.success();
    }
}
