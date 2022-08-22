package com.mini.controller;

import com.github.pagehelper.PageInfo;
import com.mini.pojo.ProductInfo;
import com.mini.pojo.vo.ProductInfoVo;
import com.mini.service.ProductInfoService;
import com.mini.utils.FileNameUtil;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {

    public static final int PAGE_SIZE = 5;

    @Autowired
    ProductInfoService productInfoService;

    String saveFileName = "";

    @RequestMapping("/getAll.action")
    public String getAll(HttpServletRequest request){
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list",list);
        return "product";
    }
    
    @RequestMapping("/split.action")
    public String split(HttpServletRequest request){
        PageInfo info = null;
        Object vo = request.getSession().getAttribute("prodVo");
        if (vo != null){
            info = productInfoService.splitPageVo( (ProductInfoVo) vo, PAGE_SIZE);
            request.getSession().removeAttribute("prodVo");
        } else {
            info = productInfoService.splitPage(1,PAGE_SIZE);
        }
        request.setAttribute("info",info);
        return "product";
    }

    @ResponseBody
    @RequestMapping("/ajaxSplit.action")
    public void ajaxSplit(ProductInfoVo vo, HttpSession session){
        PageInfo info = productInfoService.splitPageVo(vo, PAGE_SIZE);
        session.setAttribute("info",info);
    }

    @ResponseBody
    @RequestMapping("/condition.action")
    public void condition(ProductInfoVo vo, HttpSession session){
        List<ProductInfo> list = productInfoService.selectCondition(vo);
        session.setAttribute("list",list);
    }

    @ResponseBody
    @RequestMapping("/ajaxImg.action")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest request){
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        System.out.println(saveFileName);
        String path = request.getServletContext().getRealPath("/static/image_big");
        try {
            pimage.transferTo(new File(path + File.separator + saveFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);
        return object.toString();
    }

    @RequestMapping("/save.action")
    public String save(ProductInfo info, HttpServletRequest request){
        info.setpImage(saveFileName);
        info.setpDate(new Date());
        int num = -1;
        try {
            num = productInfoService.save(info);
        } catch (Exception e){
            e.printStackTrace();
        }
        if (num > 0){
            request.setAttribute("msg","Add successfully!");
        } else {
            request.setAttribute("msg","Add unsuccessfully!");
        }
        saveFileName = "";
        return "forward:/prod/split.action"; //"redirect:/prod/split.action"

    }

    @RequestMapping("/one.action")
    public String one(int pid, ProductInfoVo vo, Model model, HttpSession session){
        ProductInfo info = productInfoService.getByID(pid);
        model.addAttribute("prod",info);
        session.setAttribute("prodVo",vo);
        return "update";
    }

    @RequestMapping("/update.action")
    public String update(ProductInfo info, HttpServletRequest request){
        if(!saveFileName.equals("")){
            info.setpImage(saveFileName);
        }
        int num = -1;
        try {
            num = productInfoService.update(info);

        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("********"+num);

        if (num > 0){
            request.setAttribute("msg","Update successfully!");
        } else {
            request.setAttribute("msg","Update unsuccessfully!");
        }
        saveFileName = "";
        return "forward:/prod/split.action";
    }
    
    @RequestMapping("/delete.action")
    public String delete(int pid, ProductInfoVo vo, HttpServletRequest request){
        int num = -1;
        try {
            num = productInfoService.delete(pid);
            if (num > 0) {
                request.setAttribute("msg", "Delete successfully!");
                request.getSession().setAttribute("deleteProdVo",vo);
            } else {
                request.setAttribute("msg", "Delete unsuccessfully!");
            }
        }catch(Exception e){
            request.setAttribute("msg","Product cannot be deleted!");
        }

        return "forward:/prod/deleteAjaxSplit.action";
    }

    @ResponseBody
    @RequestMapping("/deleteAjaxSplit.action")
    public Object deleteAjaxSplit(HttpServletRequest request){
        PageInfo info = null;
        Object vo = request.getSession().getAttribute("deleteProdVo");
        if(vo!=null){
            info = productInfoService.splitPageVo((ProductInfoVo)vo, PAGE_SIZE);
        }else{
            info = productInfoService.splitPage(1,PAGE_SIZE);
        }
        request.getSession().setAttribute("info",info);
        return request.getAttribute("msg");
    }

    @RequestMapping("/deleteBatch.action")
    public String deleteBatch(String pids, HttpServletRequest request){
        String[] ps = pids.split(",");

        try {
            int num = productInfoService.deleteBatch(ps);
            if (num > 0){
                request.setAttribute("msg","Batch delete successfully!");
            } else {
                request.setAttribute("msg","Batch delete unsuccessfully!");
            }
        }catch(Exception e){
            request.setAttribute("msg","Product cannot be deleted!");
        }

        return "forward:/prod/deleteAjaxSplit.action";

    }

}
