package com.thinkgem.jeesite.modules.cms.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cms.dao.ItemsDao;
import com.thinkgem.jeesite.modules.cms.entity.ItemsEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

@Service
@Transactional(readOnly = true)
public class ItemsService extends CrudService<ItemsDao, ItemsEntity> {
	@Autowired
	private ItemsDao itemsDao;
	
	/**
	 * 根据id查询商品
	 * 
	 * @param id
	 * @return
	 */
	public ItemsEntity findItemByPrimaryKey(String id) {
		return itemsDao.findItemByPrimaryKey(id);
	}


	/**
	 * 查询商品列表
	 * 
	 * @param itemsEntity
	 * @return
	 */
	public Page<ItemsEntity> findPage(Page<ItemsEntity> page,
			ItemsEntity itemsEntity) {
		itemsEntity.getSqlMap().put("dsf", dataScopeFilter(itemsEntity.getCurrentUser(), "o", "u"));
		itemsEntity.setPage(page);
		page.setList(dao.findList(itemsEntity));
		
		return page;
	}

	/**
	 * 添加商品
	 * 
	 * @param itemsEntity
	 * @return
	 */
	@Transactional(readOnly = false)
	public void save(ItemsEntity itemsEntity) {
		if (StringUtils.isBlank(itemsEntity.getId())) {
			itemsEntity.preInsert();
			itemsDao.insert(itemsEntity);
		} else {
			itemsEntity.preUpdate();
			itemsDao.update(itemsEntity);
		}

	}
	
	
	public Page<ItemsEntity> findItems(Page<ItemsEntity> page, ItemsEntity itemsEntity) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		itemsEntity.getSqlMap().put("dsf", dataScopeFilter(itemsEntity.getCurrentUser(), "o", "a"));
		// 设置分页参数
		itemsEntity.setPage(page);
		// 执行分页查询
		page.setList(itemsDao.findList(itemsEntity));
		return page;
	}
	

	/**
	 * 根据id删除商品
	 * 
	 * @param id
	 * @return
	 */
	public int deleteItemByPrimaryKey(Integer id) {
		return id;
	}

	/**
	 * 根据id更新商品信息
	 * 
	 * @param id
	 * @return
	 */
	public int updateItemByPrimaryKey(Integer id) {
		return id;
	}

}
