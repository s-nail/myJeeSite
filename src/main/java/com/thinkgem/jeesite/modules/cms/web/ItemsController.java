package com.thinkgem.jeesite.modules.cms.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.ItemsEntity;
import com.thinkgem.jeesite.modules.cms.service.ItemsService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/cms")
public class ItemsController extends BaseController {

	@Autowired
	private ItemsService itemsService;

	@RequestMapping(value = { "myUserList" })
	public String toUserList(Model model) {
		model.addAttribute("info", "来自MyController---userList");
		return "modules/cms/myUserList";
	}

	/*
	 * 商品列表
	 */
	@RequestMapping(value = "/myItemsList")
	public String getItemsList(ItemsEntity itemsEntity, HttpServletRequest request, HttpServletResponse response,Model model) {
		
		Page<ItemsEntity> page=itemsService.findPage(new Page<ItemsEntity>(request, response), itemsEntity);

		model.addAttribute("page",page);
		return "modules/cms/myItemsList";
	}

	/*
	 * 添加商品
	 */
	@RequiresPermissions("cms:items:edit")
	@RequestMapping(value = { "myItemForm" })
	public String form(HttpServletRequest request,ItemsEntity itemsEntity, Model model) {
		String id=request.getParameter("id");
		if (id!=null&&!id.isEmpty()) {
			System.out.println("修改，id"+id);
			itemsEntity=itemsService.findItemByPrimaryKey(id);
			System.out.println("itemsEntity："+itemsEntity);
		}
		model.addAttribute("itemsEntity" ,itemsEntity);
		System.out.println("itemsEntity："+itemsEntity);
		return "modules/cms/myItemForm";
	}
	
	/*
	 * 保存添加的商品
	 */
	@RequiresPermissions("cms:items:edit")
	@RequestMapping(value = { "save" })
	public String save(HttpServletRequest request,ItemsEntity itemsEntity, Model model,
			RedirectAttributes redirectAttributes) {

		//itemsEntity.setCreateBy(UserUtils.getUser());
		
		System.out.println("##################################");
		System.out.println("商品名称:" + itemsEntity.getItemName() + "商品价格:"
				+ itemsEntity.getPrice() + "库存量:" + itemsEntity.getInventory()
				+ "创建时间:" + itemsEntity.getCreateDate() + "创建人:"
				+ itemsEntity.getCreateBy());
		System.out.println("##################################");

		if (!beanValidator(model, itemsEntity)) {
			return form(request, itemsEntity , model);
		}
	
		itemsService.save(itemsEntity);

		addMessage(redirectAttributes, "保存商品'" + itemsEntity.getItemName()
				+ "'成功");
		return "redirect:" + adminPath + "/cms/myItemsList";
	}
	
	/**
	 * 根据id删除商品
	 */
	@RequiresPermissions("cms:items:edit")
	@RequestMapping(value={"/delete"})
	public String delete(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		String id=request.getParameter("id");
	ItemsEntity itemsEntity=new ItemsEntity();
	itemsEntity.setId(id);
	itemsService.delete(itemsEntity);
	addMessage(redirectAttributes, "删除数据成功！");
		
	return "redirect:" + adminPath + "/cms/myItemsList";
	}
	
	
	
	/**
	 * 导出用户数据
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cms:items:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ItemsEntity itemsEntity, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商品数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<ItemsEntity> page = itemsService.findItems(new Page<ItemsEntity>(request, response, -1), itemsEntity);
    		new ExportExcel("商品数据", ItemsEntity.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商品失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/cms/myItemsList";
    }
	

	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cms:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商品数据导入模板.xlsx";
    		List<ItemsEntity> list = Lists.newArrayList(); 
    		list.add(new ItemsEntity());
    		new ExportExcel("商品数据", ItemsEntity.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/cms/myItemsList";
    }	
	
}
