package org.emsc.modules.boot.testOnline1.service;

import org.emsc.modules.boot.testOnline1.entity.FruitOrderP;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 水果明细
 * @Author: emsc-boot
 * @Date:   2024-09-12
 * @Version: V1.0
 */
public interface IFruitOrderPService extends IService<FruitOrderP> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<FruitOrderP>
	 */
	public List<FruitOrderP> selectByMainId(String mainId);
}
