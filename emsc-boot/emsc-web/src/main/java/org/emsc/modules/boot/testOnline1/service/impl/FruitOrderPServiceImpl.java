package org.emsc.modules.boot.testOnline1.service.impl;

import org.emsc.modules.boot.testOnline1.entity.FruitOrderP;
import org.emsc.modules.boot.testOnline1.mapper.FruitOrderPMapper;
import org.emsc.modules.boot.testOnline1.service.IFruitOrderPService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 水果明细
 * @Author: emsc-boot
 * @Date:   2024-09-12
 * @Version: V1.0
 */
@Service
public class FruitOrderPServiceImpl extends ServiceImpl<FruitOrderPMapper, FruitOrderP> implements IFruitOrderPService {
	
	@Autowired
	private FruitOrderPMapper fruitOrderPMapper;
	
	@Override
	public List<FruitOrderP> selectByMainId(String mainId) {
		return fruitOrderPMapper.selectByMainId(mainId);
	}
}
