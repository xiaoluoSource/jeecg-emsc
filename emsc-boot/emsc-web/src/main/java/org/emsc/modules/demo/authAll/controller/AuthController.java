package org.emsc.modules.demo.authAll.controller;

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
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.emsc.modules.demo.authAll.entity.Auth;
import org.emsc.modules.demo.authAll.service.IAuthService;

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
 * @Description: 不设置权限配置
 * @Author: jeecg-boot
 * @Date:   2024-09-03
 * @Version: V1.0
 */
@Api(tags="不设置权限配置")
@RestController
@RequestMapping("/authAll/auth")
@Slf4j
public class AuthController extends JeecgController<Auth, IAuthService> {
	@Autowired
	private IAuthService authService;
	
	/**
	 * 分页列表查询
	 *
	 * @param auth
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "不设置权限配置-分页列表查询")
	@ApiOperation(value="不设置权限配置-分页列表查询", notes="不设置权限配置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Auth>> queryPageList(Auth auth,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Auth> queryWrapper = QueryGenerator.initQueryWrapper(auth, req.getParameterMap());
		Page<Auth> page = new Page<Auth>(pageNo, pageSize);
		IPage<Auth> pageList = authService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param auth
	 * @return
	 */
	@AutoLog(value = "不设置权限配置-添加")
	@ApiOperation(value="不设置权限配置-添加", notes="不设置权限配置-添加")
	@RequiresPermissions("authAll:auth:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody Auth auth) {
		authService.save(auth);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param auth
	 * @return
	 */
	@AutoLog(value = "不设置权限配置-编辑")
	@ApiOperation(value="不设置权限配置-编辑", notes="不设置权限配置-编辑")
	@RequiresPermissions("authAll:auth:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody Auth auth) {
		authService.updateById(auth);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "不设置权限配置-通过id删除")
	@ApiOperation(value="不设置权限配置-通过id删除", notes="不设置权限配置-通过id删除")
	@RequiresPermissions("authAll:auth:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		authService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "不设置权限配置-批量删除")
	@ApiOperation(value="不设置权限配置-批量删除", notes="不设置权限配置-批量删除")
	@RequiresPermissions("authAll:auth:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.authService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "不设置权限配置-通过id查询")
	@ApiOperation(value="不设置权限配置-通过id查询", notes="不设置权限配置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Auth> queryById(@RequestParam(name="id",required=true) String id) {
		Auth auth = authService.getById(id);
		if(auth==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(auth);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param auth
    */
    @RequiresPermissions("authAll:auth:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Auth auth) {
        return super.exportXls(request, auth, Auth.class, "不设置权限配置");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("authAll:auth:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Auth.class);
    }

}
