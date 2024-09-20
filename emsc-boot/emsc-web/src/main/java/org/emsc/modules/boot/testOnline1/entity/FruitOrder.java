package org.emsc.modules.boot.testOnline1.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 订单表
 * @Author: emsc-boot
 * @Date:   2024-09-12
 * @Version: V1.0
 */
@ApiModel(value="fruit_order对象", description="订单表")
@Data
@TableName("fruit_order")
public class FruitOrder implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
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
}
