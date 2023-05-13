package com.xiaye.rescuebackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaye.rescuebackend.exception.ExceptionFactory;
import com.xiaye.rescuebackend.mapper.UserMapper;
import com.xiaye.rescuebackend.model.Company;
import com.xiaye.rescuebackend.model.User;
import com.xiaye.rescuebackend.service.CompanyService;
import com.xiaye.rescuebackend.service.UserService;
import com.xiaye.rescuebackend.types.ResultCodeEnum;
import com.xiaye.rescuebackend.types.RoleNameEnum;
import com.xiaye.rescuebackend.types.UserApprovedEnum;
import com.xiaye.rescuebackend.vo.PageParam;
import com.xiaye.rescuebackend.vo.UpdatePasswordParam;
import com.xiaye.rescuebackend.vo.UserInfoVo;
import com.xiaye.rescuebackend.vo.UserParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    /**
     * 公司管理服务
     */
    private final CompanyService companyService;

    @Autowired
    public UserServiceImpl(CompanyService companyService) {
        this.companyService = companyService;
    }

    public User selectUserByPhoneNumberAndApproved(String phoneNumber) {
        if (ObjectUtil.isEmpty(phoneNumber)) {
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_IS_BLANK);
        }
        return baseMapper.selectByUserPhoneAndApproved(phoneNumber);
    }

    @Override
    public User selectUserByPhoneNumber(String phoneNumber) {
        if (ObjectUtil.isEmpty(phoneNumber)) {
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_IS_BLANK);
        }
        return baseMapper.selectByUserPhoneAndApproved(phoneNumber);
    }

    @Override
    public Boolean exitUserByPhoneNumber(String phoneNumber) {
        if (ObjectUtil.isEmpty(phoneNumber)) {
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_IS_BLANK);
        }
        return selectUserByPhoneNumber(phoneNumber) != null;
    }

    @Override
    public UserInfoVo selectUserInfoById(Long id) {
        User user = baseMapper.selectById(id);
        Company company = companyService.getById(user.getCompanyId());
        return UserInfoVo.of(user, company);
    }

    @Override
    public User addCompanyAdminUser(UserParam userParam) {
        return null;
    }

    @Override
    public Page<UserInfoVo> selectUserPageByUserRole(Long id, PageParam param) {
        //通过用户的id
        User curUser = baseMapper.selectById(id);
        if (ObjectUtil.isEmpty(curUser)) {
            throw ExceptionFactory.createParamException(ResultCodeEnum.PARAM_IS_BLANK);
        }

        Page<UserInfoVo> page = PageParam.to(param);
        //如果是用户的角色为系统管理员,就返回所有公司管理员的分页查询列表
        if (RoleNameEnum.SYSTEM_ADMIN.equals(curUser.getRole())) {
            page = baseMapper.pageUserInfoVoByUserRole(page, RoleNameEnum.COMPANY_ADMIN);
        }
        //如果用户角色为公司管理员，就查看当前公司所有的管理员和员工信息
        //如果用户角色为公司员工，就可以查看用户所在公司的管理员和员工信息
        page = baseMapper.pageUserInfoVoByCompanyId(page, curUser.getCompanyId());
        return page;
    }

    @Override
    @Transactional
    public Boolean approveUser(Long id) {
        //更新数据，限制更新
        User user = new User();
        user.setId(id);
        user.setApproved(UserApprovedEnum.AUDITED);
        return baseMapper.updateById(user) > 0;
    }

    @Override
    public Boolean updatePasswordForUser(Long userId, UpdatePasswordParam updatePasswordParam) {
        //通过userid查找出用户
        User user = baseMapper.selectById(userId);
        //校验old密码和store密码是否一致
        if (!user.getPassword().equals(updatePasswordParam.getOldPassword())) {
            return false;
        }
        user.setPassword(updatePasswordParam.getNewPassword());
        return this.saveOrUpdate(user);
    }

    @Override
    @Transactional
    public Boolean deleteUserByUserIdForDifferentRoles(Long userId, Long opUserId) {
        //添加事务，
        User opUser = baseMapper.selectById(userId);

        return this.removeById(userId);
    }

    @Override
    public List<User> getUserByCompanyId(Long companyId) {
        if (companyId == null) {
            return Collections.emptyList();
        }
        //QueryChainWrapper<User> queryChainWrapper = this.query();
        LambdaQueryChainWrapper<User> queryChainWrapper = this.lambdaQuery();
        queryChainWrapper.eq(User::getCompanyId, companyId);
        return this.list(queryChainWrapper);
    }
}
