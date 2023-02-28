package com.junior.cloud.mall.categoryproduct.service;

import com.github.pagehelper.PageInfo;
import com.junior.cloud.mall.categoryproduct.model.request.AddCategoryReq;
import com.junior.cloud.mall.categoryproduct.model.request.UpdateCategoryReq;
import com.junior.cloud.mall.categoryproduct.model.vo.CategoryVO;
import com.junior.cloud.mall.common.exception.CategoryException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {
    public void insertCategory(AddCategoryReq category) throws CategoryException;

    //更新目录
    @Transactional(rollbackFor = Exception.class)
    void updateCategory(UpdateCategoryReq category) throws CategoryException;

    void deleteCategory(Integer id) throws CategoryException;

    PageInfo selectAllAdmin(Integer pageNum, Integer pageSize);

    List<CategoryVO> selectAllUser(Integer parentId);
}
