package com.junior.cloud.mall.cartorder.model.dao;

import com.junior.cloud.mall.cartorder.model.pojo.Cart;
import com.junior.cloud.mall.cartorder.model.vo.CartVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<CartVO> selectByUserId(Integer userId);

    Cart selectByUserIdAndProductId(Integer userId, Integer productId);

    int selectOrNot(Integer userId, Integer productId, Integer selected);
}