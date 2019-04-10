/**
 * 初始化发票记录详情对话框
 */
var InvoiceInfoDlg = {
    invoiceInfoData : {}
};

/**
 * 清除数据
 */
InvoiceInfoDlg.clearData = function() {
    this.invoiceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InvoiceInfoDlg.set = function(key, val) {
    this.invoiceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InvoiceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
InvoiceInfoDlg.close = function() {
    parent.layer.close(window.parent.Invoice.layerIndex);
}

/**
 * 收集数据
 */
InvoiceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('invoiceCode')
    .set('invoiceId')
    .set('phone')
    .set('idCardNum')
    .set('amount')
    .set('state')
    .set('createDate')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
InvoiceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/invoice/add", function(data){
        Feng.success("添加成功!");
        window.parent.Invoice.table.refresh();
        InvoiceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.invoiceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
InvoiceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/invoice/update", function(data){
        Feng.success("修改成功!");
        window.parent.Invoice.table.refresh();
        InvoiceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.invoiceInfoData);
    ajax.start();
}

$(function() {

});
