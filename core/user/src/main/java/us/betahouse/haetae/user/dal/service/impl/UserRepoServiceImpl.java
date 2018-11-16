/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.user.dal.model.perm.UserDO;
import us.betahouse.haetae.user.dal.repo.perm.UserDORepo;
import us.betahouse.haetae.user.dal.service.UserRepoService;
import us.betahouse.haetae.user.model.UserBO;

/**
 * 用户服务实现
 * @author dango.yxm
 * @version : UserRepoServiceImpl.java 2018/11/16 下午7:39 dango.yxm
 */
@Service
public class UserRepoServiceImpl implements UserRepoService {

    @Autowired
    private UserDORepo userDORepo;

    @Override
    public UserBO queryByUserName(String userName) {
        return convert(userDORepo.findByUsername(userName));
    }


    /**
     * DO2BO
     * @param userDO
     * @return
     */
    private UserBO convert(UserDO userDO){
        if(userDO == null){
            return null;
        }
        UserBO userBO = new UserBO();
        userBO.setUserId(userDO.getUserId());
        userBO.setUserName(userDO.getUsername());
        userBO.setPassword(userDO.getPassword());
        userBO.setSalt(userDO.getSalt());
        userBO.setOpenId(userDO.getOpenId());
        userBO.setLastLoginIP(userDO.getLastLoginIP());
        return userBO;
    }

    /**
     * BO2DO
     * @param userBO
     * @return
     */
    private UserDO convert(UserBO userBO){
        if(userBO == null){
            return null;
        }
        UserDO userDO = new UserDO();
        userDO.setUserId(userBO.getUserId());
        userDO.setUsername(userBO.getUserName());
        userDO.setPassword(userBO.getPassword());
        userDO.setSalt(userBO.getSalt());
        userDO.setOpenId(userBO.getOpenId());
        userDO.setLastLoginIP(userBO.getLastLoginIP());
        return userDO;
    }
}
