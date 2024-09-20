package org.emsc.modules.boot.testOnline1.service;

import org.emsc.modules.boot.testOnline1.entity.FruitOrderP;
import org.emsc.modules.boot.testOnline1.entity.FruitOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 订单表
 * @Author: emsc-boot
 * @Date:   2024-09-12
 * @Version: V1.0
 */
public interface IFruitOrderService extends IService<FruitOrder> {

	/**
	 * 添加一对多
	 *
	 * @param fruitOrder
	 * @param fruitOrderPList
	 */
	public void saveMain(FruitOrder fruitOrder,List<FruitOrderP> fruitOrderPList) ;
	
	/**
	 * 修改一对多
	 *
   * @param fruitOrder
   * @param fruitOrderPList
	 */
	public void updateMain(FruitOrder fruitOrder,List<FruitOrderP> fruitOrderPList);
	
	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
