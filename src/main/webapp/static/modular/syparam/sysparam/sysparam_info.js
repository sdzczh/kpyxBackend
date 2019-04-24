/**
 * 初始化系统参数配置详情对话框
 */
var SysparamInfoDlg = {
    sysparamInfoData : {}
};

/**
 * 清除数据
 */
SysparamInfoDlg.clearData = function() {
    this.sysparamInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysparamInfoDlg.set = function(key, val) {
    this.sysparamInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SysparamInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SysparamInfoDlg.close = function() {
    parent.layer.close(window.parent.Sysparam.layerIndex);
}

/**
 * 收集数据
 */
SysparamInfoDlg.collectData = function() {
    this
    .set('id')
    .set('keyName')
    .set('keyValue')
    .set('remark')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
SysparamInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sysparam/add", function(data){
        Feng.success("添加成功!");
        window.parent.Sysparam.table.refresh();
        SysparamInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysparamInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SysparamInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sysparam/update", function(data){
        Feng.success("修改成功!");
        window.parent.Sysparam.table.refresh();
        SysparamInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sysparamInfoData);
    ajax.start();
}

$(function() {

});
