/**
 * 视频管理管理初始化
 */
var Videos = {
    id: "VideosTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Videos.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '视频名称', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '视频地址', field: 'videoUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Videos.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Videos.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加视频管理
 */
Videos.openAddVideos = function () {
    var index = layer.open({
        type: 2,
        title: '添加视频管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/videos/videos_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看视频管理详情
 */
Videos.openVideosDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '视频管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/videos/videos_update/' + Videos.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除视频管理
 */
Videos.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/videos/delete", function (data) {
            Feng.success("删除成功!");
            Videos.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("videosId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询视频管理列表
 */
Videos.search = function () {
    var queryData = {};
    queryData['title'] = $("#title").val();
    Videos.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Videos.initColumn();
    var table = new BSTable(Videos.id, "/videos/list", defaultColunms);
    table.setPaginationType("client");
    Videos.table = table.init();
});
