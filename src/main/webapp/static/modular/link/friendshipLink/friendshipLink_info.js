/**
 * 初始化友情链接管理详情对话框
 */
var FriendshipLinkInfoDlg = {
    friendshipLinkInfoData : {}
};

/**
 * 清除数据
 */
FriendshipLinkInfoDlg.clearData = function() {
    this.friendshipLinkInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FriendshipLinkInfoDlg.set = function(key, val) {
    this.friendshipLinkInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FriendshipLinkInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FriendshipLinkInfoDlg.close = function() {
    parent.layer.close(window.parent.FriendshipLink.layerIndex);
}

/**
 * 收集数据
 */
FriendshipLinkInfoDlg.collectData = function() {
    this
    .set('id')
    .set('title')
    .set('href')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
FriendshipLinkInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/friendshipLink/add", function(data){
        Feng.success("添加成功!");
        window.parent.FriendshipLink.table.refresh();
        FriendshipLinkInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.friendshipLinkInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FriendshipLinkInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/friendshipLink/update", function(data){
        Feng.success("修改成功!");
        window.parent.FriendshipLink.table.refresh();
        FriendshipLinkInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.friendshipLinkInfoData);
    ajax.start();
}

$(function() {

});
