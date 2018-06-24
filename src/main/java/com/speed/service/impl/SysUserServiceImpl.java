package com.speed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.speed.entity.SysUser;
import com.speed.mapper.SysUserMapper;
import com.speed.service.ISysUserService;

/**
 *
 * SysUser 表数据服务层接口实现类
 *
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	@Autowired private SysUserMapper userMapper;
	@Override
	public SysUser login(SysUser sysUser) {
		// TODO Auto-generated method stub
		String password = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
		return this.selectOne(new EntityWrapper<SysUser>().eq("userName", sysUser.getUserName()).eq("password", password));
	}
}