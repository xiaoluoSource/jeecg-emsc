package org.emsc.modules.boot.testOnline1.service.impl;

import org.emsc.modules.boot.testOnline1.entity.FruitOrder;
import org.emsc.modules.boot.testOnline1.entity.FruitOrderP;
import org.emsc.modules.boot.testOnline1.mapper.FruitOrderPMapper;
import org.emsc.modules.boot.testOnline1.mapper.FruitOrderMapper;
import org.emsc.modules.boot.testOnline1.service.IFruitOrderService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 订单表
 * @Author: emsc-boot
 * @Date:   2024-09-12
 * @Version: V1.0
 */
@Service
public class FruitOrderServiceImpl extends ServiceImpl<FruitOrderMapper, FruitOrder> implements IFruitOrderService {

	@Autowired
	private FruitOrderMapper fruitOrderMapper;
	@Autowired
	private FruitOrderPMapper fruitOrderPMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(FruitOrder fruitOrder, List<FruitOrderP> fruitOrderPList) {
		fruitOrderMapper.insert(fruitOrder);
		if(fruitOrderPList!=null && fruitOrderPList.size()>0) {
			for(FruitOrderP entity:fruitOrderPList) {
				//外键设置
				entity.setOrderId(fruitOrder.getId());
				fruitOrderPMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(FruitOrder fruitOrder,List<FruitOrderP> fruitOrderPList) {
		fruitOrderMapper.updateById(fruitOrder);
		
		//1.先删除子表数据
		fruitOrderPMapper.deleteByMainId(fruitOrder.getId());
		
		//2.子表数据重新插入
		if(fruitOrderPList!=null && fruitOrderPList.size()>0) {
			for(FruitOrderP entity:fruitOrderPList) {
				//外键设置
				entity.setOrderId(fruitOrder.getId());
				fruitOrderPMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		fruitOrderPMapper.deleteByMainId(id);
		fruitOrderMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			fruitOrderPMapper.deleteByMainId(id.toString());
			fruitOrderMapper.deleteById(id);
		}
	}
	
}
