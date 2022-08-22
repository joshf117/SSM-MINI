package com.mini.service.impl;

import com.mini.mapper.ProductTypeMapper;
import com.mini.pojo.ProductType;
import com.mini.pojo.ProductTypeExample;
import com.mini.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAll(){
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
