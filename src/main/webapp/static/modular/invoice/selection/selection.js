/**
 * 入围名单管理初始化
 */
var Selection = {
    id: "SelectionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Selection.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '发票代码', field: 'invoice_code', visible: true, align: 'center', valign: 'middle'},
            {title: '发票编码', field: 'invoice_id', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证号', field: 'id_card_num', visible: true, align: 'center', valign: 'middle'},
            {title: '期数', field: 'number', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'State', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Selection.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Selection.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加入围名单
 */
Selection.openAddSelection = function () {
    var index = layer.open({
        type: 2,
        title: '添加入围名单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/selection/selection_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看入围名单详情
 */
Selection.openSelectionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '入围名单详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/selection/selection_update/' + Selection.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除入围名单
 */
Selection.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/selection/delete", function (data) {
            Feng.success("删除成功!");
            Selection.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("selectionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询入围名单列表
 */
Selection.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    queryData['invoiceId'] = $("#invoiceId").val();
    queryData['idCardNum'] = $("#idCardNum").val();
    queryData['number'] = $("#number").val();
    Selection.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Selection.initColumn();
    var table = new BSTable(Selection.id, "/selection/list", defaultColunms);
    table.setPaginationType("server");
    Selection.table = table.init();
});
Selection.reset = function () {
    $("#phone").val("");
    $("#invoiceId").val("");
    $("#idCardNum").val("");
    $("#number").val("");

    Selection.search();
};