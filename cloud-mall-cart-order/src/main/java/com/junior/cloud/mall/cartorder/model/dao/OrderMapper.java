package com.junior.cloud.mall.cartorder.model.dao;

import com.junior.cloud.mall.cartorder.model.pojo.Order;
import com.junior.cloud.mall.cartorder.model.query.OrderStatisticsQuery;
import com.junior.cloud.mall.cartorder.model.vo.OrderStatisticsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectByOrderNo(String orderNo);

    List<Order> selectByUserId(Integer userId);

    List<OrderStatisticsVO> selectOrderStatistics(@Param("query") OrderStatisticsQuery query);
}