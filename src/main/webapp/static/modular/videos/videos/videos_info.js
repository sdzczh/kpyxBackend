/**
 * 初始化视频管理详情对话框
 */
var VideosInfoDlg = {
    videosInfoData : {}
};

/**
 * 清除数据
 */
VideosInfoDlg.clearData = function() {
    this.videosInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideosInfoDlg.set = function(key, val) {
    this.videosInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VideosInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VideosInfoDlg.close = function() {
    parent.layer.close(window.parent.Videos.layerIndex);
}

/**
 * 收集数据
 */
VideosInfoDlg.collectData = function() {
    this
    .set('id')
    .set('title')
    .set('videoUrl')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
VideosInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videos/add", function(data){
        Feng.success("添加成功!");
        window.parent.Videos.table.refresh();
        VideosInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videosInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VideosInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/videos/update", function(data){
        Feng.success("修改成功!");
        window.parent.Videos.table.refresh();
        VideosInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.videosInfoData);
    ajax.start();
}

$(function() {

});
