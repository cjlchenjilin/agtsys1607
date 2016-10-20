package org.agtsys.controller;

import java.util.List;

import org.agtsys.constants.WebConstants;
import org.agtsys.domain.SystemConfig;
import org.agtsys.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("systemconfig/")
public class SystemConfigController {
	@Autowired
	private SystemConfigService systemConfigService;

	// 获取配置管理页面根据类型
	@RequestMapping(value = "manage/{configtype}", method = RequestMethod.GET)
	public String getManage(@PathVariable Integer configtype, Model model)
			throws Exception {
		model.addAttribute("configtype", configtype);
		return "systemconfig_manage";
	}

	// 获取指定类型的数据集
	@RequestMapping(value = "list/{configtype}")
	public @ResponseBody Object getList(@PathVariable Integer configtype)
			throws Exception {
		SystemConfig systemconfig = new SystemConfig();
		systemconfig.setConfigtype(configtype);
		List<SystemConfig> scs = systemConfigService
				.getSystemConfigByConfigType(systemconfig);
		return scs;
	}

	// 获取添加配置页面根据类型
	@RequestMapping(value = "add/{configtype}", method = RequestMethod.GET)
	public String getAdd(@PathVariable Integer configtype, Model model)
			throws Exception {
		model.addAttribute("configtype", configtype);
		return "add_systemconfig";
	}

	// 检查类型名称是否存在
	@RequestMapping(value = "check", method = RequestMethod.POST)
	public @ResponseBody String check(SystemConfig systemconfig)
			throws Exception {
		List<SystemConfig> scs = systemConfigService
				.getSystemConfigByConfigType(systemconfig);
		if (scs == null || scs.size() == 0) {
			return WebConstants.OPERATION_MESSAGE_SUCCESS;
		} else {
			return WebConstants.OPERATION_MESSAGE_FAIL;
		}
	}

	// 添加新类型
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public @ResponseBody String doAdd(SystemConfig systemconfig)
			throws Exception {
		if (systemConfigService.insertSystemConfig(systemconfig) == 1) {
			return WebConstants.OPERATION_MESSAGE_SUCCESS;
		} else {
			return WebConstants.OPERATION_MESSAGE_FAIL;
		}
	}

	// 删除类型
	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public @ResponseBody String doDelete(@PathVariable(value="id") Long id)
			throws Exception {
		SystemConfig systemconfig = new SystemConfig();
		systemconfig.setId(id);
		if (systemConfigService.deleteSystemConfigById(systemconfig) == 1) {
			return WebConstants.OPERATION_MESSAGE_SUCCESS;
		} else {
			return WebConstants.OPERATION_MESSAGE_FAIL;
		}
	}
}
