package com.mini.service;

import com.github.pagehelper.PageInfo;
import com.mini.pojo.ProductInfo;
import com.mini.pojo.vo.ProductInfoVo;

import java.util.List;

public interface ProductInfoService {

    //show all products
    List<ProductInfo> getAll();

    //paginator
    PageInfo splitPage(int pageNum, int pageSize);

    //add product
    int save(ProductInfo info);

    //select product by primary key
    ProductInfo getByID(int id);

    //update product information
    int update(ProductInfo info);

    int delete(int pid);

    int deleteBatch(String[] ids);

    List<ProductInfo> selectCondition(ProductInfoVo vo);

    PageInfo splitPageVo(ProductInfoVo vo, int pageSize);


}
