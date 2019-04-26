/**
 * 发票记录管理初始化
 */
var Invoice = {
    id: "InvoiceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Invoice.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '发票代码', field: 'invoiceCode', visible: true, align: 'center', valign: 'middle'},
            {title: '发票编码', field: 'invoiceId', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证号码', field: 'idCardNum', visible: true, align: 'center', valign: 'middle'},
            {title: '发票金额', field: 'amount', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'State', visible: true, align: 'center', valign: 'middle'},
            {title: '开票日期', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Invoice.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Invoice.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加发票记录
 */
Invoice.openAddInvoice = function () {
    var index = layer.open({
        type: 2,
        title: '添加发票记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/invoice/invoice_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看发票记录详情
 */
Invoice.openInvoiceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '发票记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/invoice/invoice_update/' + Invoice.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除发票记录
 */
Invoice.delete = function () {
    if (this.check()) {
        var selected = $('#' + this.id).bootstrapTable('getSelections');
        var ids = "";
        for(var i = 0; i < selected.length; i++) {
            ids += selected[i].id + ",";
        }
            var ajax = new $ax(Feng.ctxPath + "/invoice/delete", function (data) {
            Feng.success("删除成功!");
            Invoice.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("ids",ids);
        ajax.start();
    }
};

/**
 * 查询发票记录列表
 */
Invoice.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    queryData['invoiceId'] = $("#invoiceId").val();
    queryData['idCardNum'] = $("#idCardNum").val();
    queryData['state'] = $("#state").val();
    Invoice.table.refresh({query: queryData});
};
Invoice.reset = function () {
    $("#phone").val("");
    $("#invoiceId").val("");
    $("#idCardNum").val("");
    $("#state").val("");

    Invoice.search();
};
$(function () {
    var defaultColunms = Invoice.initColumn();
    var table = new BSTable(Invoice.id, "/invoice/list", defaultColunms);
    table.setPaginationType("server");
    Invoice.table = table.init();
});

Invoice.export = function () {
    $('#InvoiceTable').tableExport({type:'excel', fileName: new Date().getTime(), escape:'false'});
};

/**
 * 手动抽奖
 */
function draw(amount) {
    amount = $("#amount").val();
    var operation = function() {
        $.post("/invoice/draw", {amount: amount}, function (res) {
            var data = JSON.parse(res);
            Feng.success(data.msg);
        })
    };
    Feng.confirm("是否执行本次操作，注意：该操作不可撤销！",operation);
};
/**
 * 结束本期抽奖
 */
function end() {
    var operation = function() {
        $.post("/invoice/end", {}, function (res) {
            var data = JSON.parse(res);
            Feng.success(data.msg);
        })
    };
    Feng.confirm("是否执行本次操作，注意：该操作不可撤销！",operation);
};