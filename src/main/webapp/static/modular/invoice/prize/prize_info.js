/**
 * 初始化中奖记录详情对话框
 */
var PrizeInfoDlg = {
    prizeInfoData : {}
};

/**
 * 清除数据
 */
PrizeInfoDlg.clearData = function() {
    this.prizeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PrizeInfoDlg.set = function(key, val) {
    this.prizeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PrizeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PrizeInfoDlg.close = function() {
    parent.layer.close(window.parent.Prize.layerIndex);
}

/**
 * 收集数据
 */
PrizeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('invoiceId')
    .set('type')
    .set('number')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
PrizeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/prize/add", function(data){
        Feng.success("添加成功!");
        window.parent.Prize.table.refresh();
        PrizeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.prizeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PrizeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/prize/update", function(data){
        Feng.success("修改成功!");
        window.parent.Prize.table.refresh();
        PrizeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.prizeInfoData);
    ajax.start();
}

$(function() {

});
