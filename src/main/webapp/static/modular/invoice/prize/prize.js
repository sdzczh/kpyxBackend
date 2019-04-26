/**
 * 中奖记录管理初始化
 */
var Prize = {
    id: "PrizeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Prize.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '发票代码', field: 'invoice_code', visible: true, align: 'center', valign: 'middle'},
            {title: '发票编码', field: 'invoice_id', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证号', field: 'id_card_num', visible: true, align: 'center', valign: 'middle'},
            {title: '期数', field: 'number', visible: true, align: 'center', valign: 'middle'},
            {title: '中奖类型', field: 'Type', visible: true, align: 'center', valign: 'middle'},
            {title: '开票日期', field: 'create_date', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'create_time', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'update_time', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Prize.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Prize.seItem = selected[0];
        return true;
    }
};


Prize.export = function () {
    $('#PrizeTable').tableExport({type:'excel', fileName: new Date().getTime(), escape:'false'});
};

/**
 * 点击添加中奖记录
 */
Prize.openAddPrize = function () {
    var index = layer.open({
        type: 2,
        title: '添加中奖记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/prize/prize_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看中奖记录详情
 */
Prize.openPrizeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '中奖记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/prize/prize_update/' + Prize.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除中奖记录
 */
Prize.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/prize/delete", function (data) {
            Feng.success("删除成功!");
            Prize.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("prizeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询中奖记录列表
 */
Prize.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    queryData['invoiceId'] = $("#invoiceId").val();
    queryData['idCardNum'] = $("#idCardNum").val();
    queryData['type'] = $("#type").val();
    queryData['number'] = $("#number").val();
    Prize.table.refresh({query: queryData});
};
Prize.reset = function () {
    $("#phone").val("");
    $("#invoiceId").val("");
    $("#idCardNum").val("");
    $("#type").val("");
    $("#number").val("");

    Prize.search();
};
$(function () {
    var defaultColunms = Prize.initColumn();
    var table = new BSTable(Prize.id, "/prize/list", defaultColunms);
    table.setPaginationType("server");
    Prize.table = table.init();
});
