/**
 * Banner管理管理初始化
 */
var Banner = {
    id: "BannerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Banner.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'Banner类型 0顶部 1中部', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: 'BannerUrl', field: 'imgUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '超链接地址', field: 'href', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Banner.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Banner.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加Banner管理
 */
Banner.openAddBanner = function () {
    var index = layer.open({
        type: 2,
        title: '添加Banner管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/banner/banner_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看Banner管理详情
 */
Banner.openBannerDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'Banner管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/banner/banner_update/' + Banner.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除Banner管理
 */
Banner.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/banner/delete", function (data) {
            Feng.success("删除成功!");
            Banner.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bannerId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询Banner管理列表
 */
Banner.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Banner.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Banner.initColumn();
    var table = new BSTable(Banner.id, "/banner/list", defaultColunms);
    table.setPaginationType("client");
    Banner.table = table.init();
});
