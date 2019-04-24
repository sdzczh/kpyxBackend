/**
 * 系统参数配置管理初始化
 */
var Sysparam = {
    id: "SysparamTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Sysparam.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'keyName', field: 'keyName', visible: true, align: 'center', valign: 'middle'},
            {title: 'keyValue', field: 'keyValue', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Sysparam.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Sysparam.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加系统参数配置
 */
Sysparam.openAddSysparam = function () {
    var index = layer.open({
        type: 2,
        title: '添加系统参数配置',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sysparam/sysparam_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看系统参数配置详情
 */
Sysparam.openSysparamDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '系统参数配置详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sysparam/sysparam_update/' + Sysparam.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除系统参数配置
 */
Sysparam.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/sysparam/delete", function (data) {
            Feng.success("删除成功!");
            Sysparam.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("sysparamId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询系统参数配置列表
 */
Sysparam.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Sysparam.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Sysparam.initColumn();
    var table = new BSTable(Sysparam.id, "/sysparam/list", defaultColunms);
    table.setPaginationType("client");
    Sysparam.table = table.init();
});
