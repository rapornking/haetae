/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.manager;

import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.asset.request.AssetRequest;

/**
 * 物资管理器
 *
 * @author guofan.cp
 * @version : AssetManager.java 2019/01/24 9:32 guofan.cp
 */
public interface AssetManager {
    /**
    * @Description: 创建物资
    * @Param: [request]
    */
    AssetBO create(AssetRequest request);

}
