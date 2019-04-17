/**
 * 友情链接管理管理初始化
 */
var FriendshipLink = {
    id: "FriendshipLinkTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
FriendshipLink.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '网站名称', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '超链接', field: 'href', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
FriendshipLink.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        FriendshipLink.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加友情链接管理
 */
FriendshipLink.openAddFriendshipLink = function () {
    var index = layer.open({
        type: 2,
        title: '添加友情链接管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/friendshipLink/friendshipLink_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看友情链接管理详情
 */
FriendshipLink.openFriendshipLinkDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '友情链接管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/friendshipLink/friendshipLink_update/' + FriendshipLink.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除友情链接管理
 */
FriendshipLink.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/friendshipLink/delete", function (data) {
            Feng.success("删除成功!");
            FriendshipLink.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("friendshipLinkId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询友情链接管理列表
 */
FriendshipLink.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    FriendshipLink.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = FriendshipLink.initColumn();
    var table = new BSTable(FriendshipLink.id, "/friendshipLink/list", defaultColunms);
    table.setPaginationType("client");
    FriendshipLink.table = table.init();
});
