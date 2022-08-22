package com.mini.test;

import com.mini.mapper.ProductInfoMapper;
import com.mini.pojo.ProductInfo;
import com.mini.pojo.vo.ProductInfoVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SQLTest {
    @Autowired
    ProductInfoMapper mapper;
    @Test
    public void testSelectionCondition(){
        ProductInfoVo vo = new ProductInfoVo();
        vo.setPname("4");
        vo.setLprice(4000);
        List<ProductInfo> productInfos = mapper.selectCondition(vo);
        productInfos.forEach(productInfo -> System.out.println(productInfo));
    }
}
