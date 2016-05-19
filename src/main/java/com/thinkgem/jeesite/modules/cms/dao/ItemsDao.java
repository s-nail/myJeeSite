package com.thinkgem.jeesite.modules.cms.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cms.entity.ItemsEntity;

@MyBatisDao
public interface ItemsDao extends CrudDao<ItemsEntity> {

	/**
	 * 添加商品
	 * 
	 * @param itemsEntity
	 * @return
	 */
	// public int insert(ItemsEntity itemsEntity);

	/**
	 * 根据id删除商品
	 * 
	 * @param id
	 * @return
	 */
	// public int delete(ItemsEntity itemsEntity);

	/**
	 * 根据id更新商品信息
	 * 
	 * @param id
	 * @return
	 */
	// public int update(ItemsEntity itemsEntity);

	/**
	 * 查询商品信息
	 * 
	 * @param itemsEntity
	 * @return
	 */
	// public List<ItemsEntity> findList(ItemsEntity itemsEntity);

	/**
	 * 根据id查询商品信息
	 * 
	 * @param itemsEntity
	 * @return
	 */
	public ItemsEntity findItemByPrimaryKey(String id);

}
