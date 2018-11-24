/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.builder.ActivityRecordBOBuilder;
import us.betahouse.haetae.activity.dal.service.ActivityRecordRepoService;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.activity.model.ActivityRecordBO;
import us.betahouse.haetae.activity.request.ActivityRecordRequest;
import us.betahouse.haetae.activity.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MessiahJK
 * @version : ActivityRecordManagerImpl.java 2018/11/23 0:09 MessiahJK
 */
@Service
public class ActivityRecordManagerImpl implements ActivityRecordManager {

    @Autowired
    private ActivityRecordRepoService activityRecordRepoService;

    @Autowired
    private ActivityRepoService activityRepoService;

    /**
     * 创建活动记录
     *
     * @param request
     * @return
     */
    @Override
    public ActivityRecordBO create(ActivityRecordRequest request) {
        ActivityBO activityBO = activityRepoService.queryActivityByActivityId(request.getActivityId());
        // 校验活动是否有效
        ActivityUtil.isValided(activityBO);

        // 活动记录时长处理
        int time = 0;
        if (request.getTime() != null) {
            time = (int) Math.round(request.getTime() * 10);
        }
        if (time == 0 && activityBO.getDefaultTime() != null) {
            time = activityBO.getDefaultTime();
        }

        ActivityRecordBOBuilder builder = ActivityRecordBOBuilder.getInstance()
                .withActivityId(request.getActivityId())
                .withScannerUserId(request.getScannerUserId())
                .withTime(time)
                .withType(request.getType())
                .withStatus(request.getStatus())
                .withStatus(request.getTerm())
                .withGrades(request.getGrades())
                .withExtInfo(request.getExtInfo())
                ;

        // 绑上用户id
        builder.withUserId(request.getUserId());
        return activityRecordRepoService.createActivityRecord(builder.build());
    }

    /**
     * 通过用户id查询活动记录
     *
     * @param userId
     * @return
     */
    @Override
    public List<ActivityRecordBO> findByUserId(String userId) {
        return activityRecordRepoService.queryActivityRecordByUserId(userId);
    }

    /**
     * 通过用户id和类型查找活动记录
     *
     * @param userId
     * @param type
     * @return
     */
    @Override
    public List<ActivityRecordBO> findByUserIdAndType(String userId, String type) {
        return activityRecordRepoService.queryActivityRecordByUserIdAndType(userId, type);
    }

    /**
     * 通过活动id统计活动记录条数
     *
     * @param activityId
     * @return
     */
    @Override
    public Long countByActivityId(String activityId) {
        return activityRecordRepoService.countActivityRecordByActivityId(activityId);
    }

    @Override
    public List<ActivityRecordBO> batchCreate(ActivityRecordRequest request, List<String> userIds) {
        ActivityBO activityBO = activityRepoService.queryActivityByActivityId(request.getActivityId());
        // 校验活动是否有效
        ActivityUtil.isValided(activityBO);

        // 活动记录时长处理
        int time = 0;
        if (request.getTime() != null) {
            time = (int) Math.round(request.getTime() * 10);
        }
        if (time == 0 && activityBO.getDefaultTime() != null) {
            time = activityBO.getDefaultTime();
        }

        ActivityRecordBOBuilder builder = ActivityRecordBOBuilder.getInstance()
                .withActivityId(request.getActivityId())
                .withScannerUserId(request.getScannerUserId())
                .withExtInfo(request.getExtInfo())
                .withTime(time)
                .withType(request.getType())
                .withStatus(request.getStatus())
                .withStatus(request.getTerm())
                .withGrades(request.getGrades());


        List<ActivityRecordBO> activityRecordList = new ArrayList<>();
        for (String userId : userIds) {
            // 绑上用户id
            builder.withUserId(request.getUserId());
            activityRecordList.add(builder.build());
        }
        return activityRecordRepoService.batchCreateActivityRecord(activityRecordList);
    }


}