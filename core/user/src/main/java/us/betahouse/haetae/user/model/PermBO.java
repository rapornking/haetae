/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.model;

import common.ToString;

import java.util.Map;

/**
 * 权限模型
 *
 * @author dango.yxm
 * @version : PermBO.java 2018/11/16 下午6:52 dango.yxm
 */
public class PermBO extends ToString {

    private static final long serialVersionUID = -6635013539070776764L;

    /**
     * 权限id
     */
    private String permId;

    /**
     * 权限名称
     */
    private String permName;

    /**
     * 权限描述
     */
    private String permDesc;

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo;

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermDesc() {
        return permDesc;
    }

    public void setPermDesc(String permDesc) {
        this.permDesc = permDesc;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}
