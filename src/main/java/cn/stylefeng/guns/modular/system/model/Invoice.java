package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
@TableName("invoice")
public class Invoice extends Model<Invoice> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 发票代码
     */
    @TableField("invoice_code")
    private String invoiceCode;
    /**
     * 发表编码
     */
    @TableField("invoice_id")
    private String invoiceId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 身份证号码
     */
    @TableField("id_card_num")
    private String idCardNum;
    /**
     * 发票金额
     */
    private BigDecimal amount;
    /**
     * 状态 0有效 1无效
     */
    private Integer state;
    /**
     * 开票日期
     */
    @TableField("create_date")
    private Date createDate;
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

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        return "Invoice{" +
        ", id=" + id +
        ", invoiceCode=" + invoiceCode +
        ", invoiceId=" + invoiceId +
        ", phone=" + phone +
        ", idCardNum=" + idCardNum +
        ", amount=" + amount +
        ", state=" + state +
        ", createDate=" + createDate +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
