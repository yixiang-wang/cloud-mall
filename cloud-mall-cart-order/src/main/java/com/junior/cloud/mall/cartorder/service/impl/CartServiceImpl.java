package com.junior.cloud.mall.cartorder.service.impl;

import com.junior.cloud.mall.cartorder.feignclient.ProductFeignClient;
import com.junior.cloud.mall.cartorder.model.dao.CartMapper;
import com.junior.cloud.mall.cartorder.model.pojo.Cart;
import com.junior.cloud.mall.cartorder.model.vo.CartVO;
import com.junior.cloud.mall.cartorder.service.CartService;
import com.junior.cloud.mall.categoryproduct.model.pojo.Product;
import com.junior.cloud.mall.common.exception.MallExceptionEnum;
import com.junior.cloud.mall.common.exception.ProductException;
import com.junior.cloud.mall.common.utils.Constant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class CartServiceImpl implements CartService {
    @Resource
    private CartMapper cartMapper;
    @Resource
    private ProductFeignClient productFeignClient;

    @Override
    public List<CartVO> getList(Integer userId) {
        List<CartVO> cartList = cartMapper.selectByUserId(userId);
        for (int i = 0; i < cartList.size(); i++) {
            CartVO vo = cartList.get(i);
            Integer totalPrice = vo.getPrice() * vo.getQuantity();
            vo.setTotalPrice(totalPrice);
        }
        return cartList;
    }

    @Override
    public List<CartVO> add(Integer userId, Integer productId, Integer count) throws ProductException {
        validProduct(productId, count);
        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart == null) {
            cart = new Cart();
            cart.setProductId(productId);
            cart.setUserId(userId);
            cart.setQuantity(count);
            cart.setSelected(Constant.CartSelect.SELECT);
            int i = cartMapper.insertSelective(cart);
            if (i != 1) {
                throw new ProductException(MallExceptionEnum.INSERT_FAILED);
            }
        } else {
            Cart cartNew = new Cart();
            cartNew.setId(cart.getId());
            cartNew.setUserId(cart.getUserId());
            cartNew.setProductId(cart.getProductId());
            cartNew.setQuantity(cart.getQuantity() + count);
            cartNew.setSelected(Constant.CartSelect.SELECT);
            int i = cartMapper.updateByPrimaryKeySelective(cart);
            if (i != 1) {
                throw new ProductException(MallExceptionEnum.UPDATE_FAILED);
            }
        }
        return getList(userId);
    }

    @Override
    public List<CartVO> update(Integer userId, Integer productId, Integer count) throws ProductException {
        validProduct(productId, count);
        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart == null) {
            throw new ProductException(MallExceptionEnum.UPDATE_FAILED);
        } else {
            Cart cartNew = new Cart();
            cartNew.setId(cart.getId());
            cartNew.setUserId(cart.getUserId());
            cartNew.setProductId(cart.getProductId());
            cartNew.setQuantity(count);
            cartNew.setSelected(Constant.CartSelect.SELECT);
            int i = cartMapper.updateByPrimaryKeySelective(cartNew);
            if (i != 1) {
                throw new ProductException(MallExceptionEnum.UPDATE_FAILED);
            }
        }
        return getList(userId);
    }

    @Override
    public List<CartVO> delete(Integer userId, Integer productId) throws ProductException {
        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart == null) {
            throw new ProductException(MallExceptionEnum.UPDATE_FAILED);
        } else {
            Integer i = cartMapper.deleteByPrimaryKey(cart.getId());
            if (i != 1) {
                throw new ProductException(MallExceptionEnum.UPDATE_FAILED);
            }
        }
        return getList(userId);
    }

    @Override
    public List<CartVO> selectOrNot(Integer userId, Integer productId, Integer selected) throws ProductException {
        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart == null) {
            throw new ProductException(MallExceptionEnum.UPDATE_FAILED);
        } else {
            int i = cartMapper.selectOrNot(userId, productId, selected);
            if (i == 0) {
                throw new ProductException(MallExceptionEnum.UPDATE_FAILED);
            }
        }
        return getList(userId);
    }

    @Override
    public List<CartVO> selectAllOrNot(Integer userId, Integer selected) {
        cartMapper.selectOrNot(userId, null, selected);
        return getList(userId);
    }

    private void validProduct(Integer productId, Integer count) throws ProductException {
        Product product = productFeignClient.detailForFeign(productId);
        if (product == null || product.getStatus().equals(Constant.SaleStatus.NOT_SALE)) {
            throw new ProductException(MallExceptionEnum.NOT_SALE);
        }
        if (count > product.getStock()) {
            throw new ProductException(MallExceptionEnum.NOT_ENOUGH);
        }
    }
}
