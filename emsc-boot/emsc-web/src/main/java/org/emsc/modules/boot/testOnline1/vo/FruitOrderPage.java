package org.emsc.modules.boot.testOnline1.vo;

import java.util.List;
import org.emsc.modules.boot.testOnline1.entity.FruitOrder;
import org.emsc.modules.boot.testOnline1.entity.FruitOrderP;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 订单表
 * @Author: emsc-boot
 * @Date:   2024-09-12
 * @Version: V1.0
 */
@Data
@ApiModel(value="fruit_orderPage对象", description="订单表")
public class FruitOrderPage {

	/**主键*/
	@ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**水果*/
	@Excel(name = "水果", width = 15)
	@ApiModelProperty(value = "水果")
    private java.lang.String shuiguo;
	/**订购人*/
	@Excel(name = "订购人", width = 15)
	@ApiModelProperty(value = "订购人")
    private java.lang.String dingGouName;
	/**金额*/
	@Excel(name = "金额", width = 15)
	@ApiModelProperty(value = "金额")
    private java.lang.String money;
	/**订单编码*/
	@Excel(name = "订单编码", width = 15)
	@ApiModelProperty(value = "订单编码")
    private java.lang.String code;

	@ExcelCollection(name="水果明细")
	@ApiModelProperty(value = "水果明细")
	private List<FruitOrderP> fruitOrderPList;

}
