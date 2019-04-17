/**
 * 初始化入围名单详情对话框
 */
var SelectionInfoDlg = {
    selectionInfoData : {}
};

/**
 * 清除数据
 */
SelectionInfoDlg.clearData = function() {
    this.selectionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SelectionInfoDlg.set = function(key, val) {
    this.selectionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SelectionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SelectionInfoDlg.close = function() {
    parent.layer.close(window.parent.Selection.layerIndex);
}

/**
 * 收集数据
 */
SelectionInfoDlg.collectData = function() {
    this
    .set('id')
    .set('invoiceId')
    .set('number')
    .set('state')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
SelectionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/selection/add", function(data){
        Feng.success("添加成功!");
        window.parent.Selection.table.refresh();
        SelectionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.selectionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SelectionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/selection/update", function(data){
        Feng.success("修改成功!");
        window.parent.Selection.table.refresh();
        SelectionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.selectionInfoData);
    ajax.start();
}

$(function() {

});
