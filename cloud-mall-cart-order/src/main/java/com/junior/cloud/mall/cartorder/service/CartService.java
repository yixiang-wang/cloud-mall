package com.junior.cloud.mall.cartorder.service;

import com.junior.cloud.mall.cartorder.model.vo.CartVO;
import com.junior.cloud.mall.common.exception.ProductException;

import java.util.List;

public interface CartService {
    List<CartVO> getList(Integer userId);

    List<CartVO> add(Integer userId, Integer productId, Integer count) throws ProductException;

    List<CartVO> update(Integer userId, Integer productId, Integer count) throws ProductException;

    List<CartVO> delete(Integer userId, Integer productId) throws ProductException;

    List<CartVO> selectOrNot(Integer userId, Integer productId, Integer selected) throws ProductException;

    List<CartVO> selectAllOrNot(Integer userId, Integer selected);
}
