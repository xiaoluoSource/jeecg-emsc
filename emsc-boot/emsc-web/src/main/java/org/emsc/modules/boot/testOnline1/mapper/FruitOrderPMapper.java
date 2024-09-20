package org.emsc.modules.boot.testOnline1.mapper;

import java.util.List;
import org.emsc.modules.boot.testOnline1.entity.FruitOrderP;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 水果明细
 * @Author: emsc-boot
 * @Date:   2024-09-12
 * @Version: V1.0
 */
public interface FruitOrderPMapper extends BaseMapper<FruitOrderP> {

	/**
	 * 通过主表id删除子表数据
	 *
	 * @param mainId 主表id
	 * @return boolean
	 */
	public boolean deleteByMainId(@Param("mainId") String mainId);

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId 主表id
   * @return List<FruitOrderP>
   */
	public List<FruitOrderP> selectByMainId(@Param("mainId") String mainId);
}
