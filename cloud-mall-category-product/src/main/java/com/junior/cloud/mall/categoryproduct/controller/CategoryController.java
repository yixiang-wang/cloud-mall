package com.junior.cloud.mall.categoryproduct.controller;

import com.github.pagehelper.PageInfo;
import com.junior.cloud.mall.categoryproduct.model.request.AddCategoryReq;
import com.junior.cloud.mall.categoryproduct.model.request.UpdateCategoryReq;
import com.junior.cloud.mall.categoryproduct.model.vo.CategoryVO;
import com.junior.cloud.mall.categoryproduct.service.CategoryService;
import com.junior.cloud.mall.common.exception.CategoryException;
import com.junior.cloud.mall.common.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/admin/category/add")
    public ResponseUtils add(@Valid @RequestBody AddCategoryReq category) throws CategoryException {
        categoryService.insertCategory(category);
        return ResponseUtils.success();
    }

    @PostMapping("/admin/category/update")
    public ResponseUtils update(@Valid @RequestBody UpdateCategoryReq category) throws CategoryException {
        categoryService.updateCategory(category);
        return ResponseUtils.success();
    }

    @PostMapping("/admin/category/delete")
    public ResponseUtils delete(Integer id) throws CategoryException {
        categoryService.deleteCategory(id);
        return ResponseUtils.success();
    }

    @GetMapping("/admin/category/list")
    public ResponseUtils selectList(Integer pageNum, Integer pageSize) {
        PageInfo pageInfo = categoryService.selectAllAdmin(pageNum, pageSize);
        return ResponseUtils.success(pageInfo);
    }

    @GetMapping("/category/list")
    public ResponseUtils selectByUser() {
        List<CategoryVO> categoryVOList = categoryService.selectAllUser(0);
        return ResponseUtils.success(categoryVOList);
    }

}
