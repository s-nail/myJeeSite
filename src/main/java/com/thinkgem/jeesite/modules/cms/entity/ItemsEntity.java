package com.thinkgem.jeesite.modules.cms.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class ItemsEntity extends DataEntity<ItemsEntity> {

	private static final long serialVersionUID = 1L;
	private String id;
	private String itemName;
	private float price;
	private int inventory;
	

	@ExcelField(title="ID",type=1,align=2,sort=1)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ExcelField(title="商品名称",align=2,sort=20)
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@ExcelField(title="商品价格",align=2,sort=25)
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@ExcelField(title="库存量",align=2,sort=30)
	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	
}
