package org.emsc.modules.boot.testOnline1.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.emsc.modules.boot.testOnline1.entity.FruitOrderP;
import org.emsc.modules.boot.testOnline1.entity.FruitOrder;
import org.emsc.modules.boot.testOnline1.vo.FruitOrderPage;
import org.emsc.modules.boot.testOnline1.service.IFruitOrderService;
import org.emsc.modules.boot.testOnline1.service.IFruitOrderPService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;


 /**
 * @Description: 订单表
 * @Author: emsc-boot
 * @Date:   2024-09-12
 * @Version: V1.0
 */
@Api(tags="订单表")
@RestController
@RequestMapping("/emsc/fruitOrder")
@Slf4j
public class FruitOrderController {
	@Autowired
	private IFruitOrderService fruitOrderService;
	@Autowired
	private IFruitOrderPService fruitOrderPService;
	
	/**
	 * 分页列表查询
	 *
	 * @param fruitOrder
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "订单表-分页列表查询")
	@ApiOperation(value="订单表-分页列表查询", notes="订单表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<FruitOrder>> queryPageList(FruitOrder fruitOrder,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FruitOrder> queryWrapper = QueryGenerator.initQueryWrapper(fruitOrder, req.getParameterMap());
		Page<FruitOrder> page = new Page<FruitOrder>(pageNo, pageSize);
		IPage<FruitOrder> pageList = fruitOrderService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param fruitOrderPage
	 * @return
	 */
	@AutoLog(value = "订单表-添加")
	@ApiOperation(value="订单表-添加", notes="订单表-添加")
    @RequiresPermissions("testOnline1:fruit_order:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody FruitOrderPage fruitOrderPage) {
		FruitOrder fruitOrder = new FruitOrder();
		BeanUtils.copyProperties(fruitOrderPage, fruitOrder);
		fruitOrderService.saveMain(fruitOrder, fruitOrderPage.getFruitOrderPList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param fruitOrderPage
	 * @return
	 */
	@AutoLog(value = "订单表-编辑")
	@ApiOperation(value="订单表-编辑", notes="订单表-编辑")
    @RequiresPermissions("testOnline1:fruit_order:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody FruitOrderPage fruitOrderPage) {
		FruitOrder fruitOrder = new FruitOrder();
		BeanUtils.copyProperties(fruitOrderPage, fruitOrder);
		FruitOrder fruitOrderEntity = fruitOrderService.getById(fruitOrder.getId());
		if(fruitOrderEntity==null) {
			return Result.error("未找到对应数据");
		}
		fruitOrderService.updateMain(fruitOrder, fruitOrderPage.getFruitOrderPList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单表-通过id删除")
	@ApiOperation(value="订单表-通过id删除", notes="订单表-通过id删除")
    @RequiresPermissions("testOnline1:fruit_order:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		fruitOrderService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "订单表-批量删除")
	@ApiOperation(value="订单表-批量删除", notes="订单表-批量删除")
    @RequiresPermissions("testOnline1:fruit_order:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.fruitOrderService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "订单表-通过id查询")
	@ApiOperation(value="订单表-通过id查询", notes="订单表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FruitOrder> queryById(@RequestParam(name="id",required=true) String id) {
		FruitOrder fruitOrder = fruitOrderService.getById(id);
		if(fruitOrder==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(fruitOrder);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "水果明细通过主表ID查询")
	@ApiOperation(value="水果明细主表ID查询", notes="水果明细-通主表ID查询")
	@GetMapping(value = "/queryFruitOrderPByMainId")
	public Result<List<FruitOrderP>> queryFruitOrderPListByMainId(@RequestParam(name="id",required=true) String id) {
		List<FruitOrderP> fruitOrderPList = fruitOrderPService.selectByMainId(id);
		return Result.OK(fruitOrderPList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param fruitOrder
    */
    @RequiresPermissions("testOnline1:fruit_order:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FruitOrder fruitOrder) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<FruitOrder> queryWrapper = QueryGenerator.initQueryWrapper(fruitOrder, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
         List<String> selectionList = Arrays.asList(selections.split(","));
         queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<FruitOrder> fruitOrderList = fruitOrderService.list(queryWrapper);

      // Step.3 组装pageList
      List<FruitOrderPage> pageList = new ArrayList<FruitOrderPage>();
      for (FruitOrder main : fruitOrderList) {
          FruitOrderPage vo = new FruitOrderPage();
          BeanUtils.copyProperties(main, vo);
          List<FruitOrderP> fruitOrderPList = fruitOrderPService.selectByMainId(main.getId());
          vo.setFruitOrderPList(fruitOrderPList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "订单表列表");
      mv.addObject(NormalExcelConstants.CLASS, FruitOrderPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("订单表数据", "导出人:"+sysUser.getRealname(), "订单表"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("testOnline1:fruit_order:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          // 获取上传文件对象
          MultipartFile file = entity.getValue();
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<FruitOrderPage> list = ExcelImportUtil.importExcel(file.getInputStream(), FruitOrderPage.class, params);
              for (FruitOrderPage page : list) {
                  FruitOrder po = new FruitOrder();
                  BeanUtils.copyProperties(page, po);
                  fruitOrderService.saveMain(po, page.getFruitOrderPList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
