package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-10
 */
@TableName("report")
public class Report extends Model<Report> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 举报人姓名
     */
    private String name;
    /**
     * 举报人手机号
     */
    private String phone;
    /**
     * 被举报企业名称
     */
    @TableField("be_reported_company_name")
    private String beReportedCompanyName;
    /**
     * 被举报者姓名
     */
    @TableField("be_reported_name")
    private String beReportedName;
    /**
     * 被举报人地址
     */
    @TableField("be_reported_address")
    private String beReportedAddress;
    /**
     * 经济性质
     */
    @TableField("economic_nature")
    private String economicNature;
    /**
     * 证明信息
     */
    @TableField("prove_information")
    private String proveInformation;
    /**
     * 举报内容
     */
    private String content;
    /**
     * 图片地址
     */
    @TableField("img_url")
    private String imgUrl;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBeReportedCompanyName() {
        return beReportedCompanyName;
    }

    public void setBeReportedCompanyName(String beReportedCompanyName) {
        this.beReportedCompanyName = beReportedCompanyName;
    }

    public String getBeReportedName() {
        return beReportedName;
    }

    public void setBeReportedName(String beReportedName) {
        this.beReportedName = beReportedName;
    }

    public String getBeReportedAddress() {
        return beReportedAddress;
    }

    public void setBeReportedAddress(String beReportedAddress) {
        this.beReportedAddress = beReportedAddress;
    }

    public String getEconomicNature() {
        return economicNature;
    }

    public void setEconomicNature(String economicNature) {
        this.economicNature = economicNature;
    }

    public String getProveInformation() {
        return proveInformation;
    }

    public void setProveInformation(String proveInformation) {
        this.proveInformation = proveInformation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Report{" +
        ", id=" + id +
        ", name=" + name +
        ", phone=" + phone +
        ", beReportedCompanyName=" + beReportedCompanyName +
        ", beReportedName=" + beReportedName +
        ", beReportedAddress=" + beReportedAddress +
        ", economicNature=" + economicNature +
        ", proveInformation=" + proveInformation +
        ", content=" + content +
        ", imgUrl=" + imgUrl +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
