package com.blog.controller.admin;

import com.blog.pojo.Type;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){
        PageHelper.startPage(pagenum, 5);
        List<Type> allType = typeService.getAllType();
        //得到分页结果对象
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String toAddType(Model model){
        model.addAttribute("type", new Type());   //返回一个type对象给前端th:object
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String toEditType(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String addType(Type type, RedirectAttributes attributes){   //新增
        Type t = typeService.getTypeByName(type.getName());
        if(t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }else {
            attributes.addFlashAttribute("msg", "添加成功");
        }
        typeService.saveType(type);
        return "redirect:/admin/types";   //不能直接跳转到types页面，否则不会显示type数据(没经过types方法)
    }

    @PostMapping("/types/{id}")
    public String editType(@PathVariable Long id, Type type, RedirectAttributes attributes){  //修改
        Type t = typeService.getTypeByName(type.getName());
        if(t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }else {
            attributes.addFlashAttribute("msg", "修改成功");
        }
        typeService.updateType(type);
        return "redirect:/admin/types";   //不能直接跳转到types页面，否则不会显示type数据(没经过types方法)
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/types";
    }
}
