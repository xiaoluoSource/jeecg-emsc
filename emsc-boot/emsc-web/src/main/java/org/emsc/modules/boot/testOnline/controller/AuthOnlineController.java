package org.emsc.modules.boot.testOnline.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.emsc.modules.boot.testOnline.entity.AuthOnline;
import org.emsc.modules.boot.testOnline.service.IAuthOnlineService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 权限自动生成验证
 * @Author: emsc-boot
 * @Date:   2024-09-10
 * @Version: V1.0
 */
@Api(tags="权限自动生成验证")
@RestController
@RequestMapping("/emsc/authOnline")
@Slf4j
public class AuthOnlineController extends JeecgController<AuthOnline, IAuthOnlineService> {
	@Autowired
	private IAuthOnlineService authOnlineService;
	
	/**
	 * 分页列表查询
	 *
	 * @param authOnline
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "权限自动生成验证-分页列表查询")
	@ApiOperation(value="权限自动生成验证-分页列表查询", notes="权限自动生成验证-分页列表查询")
	@GetMapping(value = "/list")
	@PermissionData(pageComponent="emsc/testonline/AuthOnlineList")
	public Result<IPage<AuthOnline>> queryPageList(AuthOnline authOnline,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AuthOnline> queryWrapper = QueryGenerator.initQueryWrapper(authOnline, req.getParameterMap());
		Page<AuthOnline> page = new Page<AuthOnline>(pageNo, pageSize);
		IPage<AuthOnline> pageList = authOnlineService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param authOnline
	 * @return
	 */
	@AutoLog(value = "权限自动生成验证-添加")
	@ApiOperation(value="权限自动生成验证-添加", notes="权限自动生成验证-添加")
	@RequiresPermissions("testOnline:auth_online:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AuthOnline authOnline) {
		authOnlineService.save(authOnline);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param authOnline
	 * @return
	 */
	@AutoLog(value = "权限自动生成验证-编辑")
	@ApiOperation(value="权限自动生成验证-编辑", notes="权限自动生成验证-编辑")
	@RequiresPermissions("testOnline:auth_online:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AuthOnline authOnline) {
		authOnlineService.updateById(authOnline);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "权限自动生成验证-通过id删除")
	@ApiOperation(value="权限自动生成验证-通过id删除", notes="权限自动生成验证-通过id删除")
	@RequiresPermissions("testOnline:auth_online:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		authOnlineService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "权限自动生成验证-批量删除")
	@ApiOperation(value="权限自动生成验证-批量删除", notes="权限自动生成验证-批量删除")
	@RequiresPermissions("testOnline:auth_online:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.authOnlineService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "权限自动生成验证-通过id查询")
	@ApiOperation(value="权限自动生成验证-通过id查询", notes="权限自动生成验证-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AuthOnline> queryById(@RequestParam(name="id",required=true) String id) {
		AuthOnline authOnline = authOnlineService.getById(id);
		if(authOnline==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(authOnline);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param authOnline
    */
    @RequiresPermissions("testOnline:auth_online:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AuthOnline authOnline) {
        return super.exportXls(request, authOnline, AuthOnline.class, "权限自动生成验证");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("testOnline:auth_online:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AuthOnline.class);
    }

}
