$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/view/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true , hidden:true},
            { label: '景点名称', name: 'name', index: 'name', width: 80 , templet: '#name', event: 'name-event'},
			{ label: '地区', name: 'area', index: 'area', width: 80 },
			{ label: '交通方式', name: 'transport', index: 'transport', width: 80 },
			{ label: '价格', name: 'price', index: 'Price', width: 80 },
			{ label: '线路', name: 'line', index: 'line', width: 80 },
			{ label: '酒店名称', name: 'hotelName', index: 'hotel_name', width: 80 },
            { label: '时间', name: 'time', index: 'time', width: 80},
			{ label: '图片', name: 'picture', index: 'picture', width: 80 , hidden:true},
			{ label: '备注', name: 'remarks', index: 'Remarks', width: 80 , hidden:true},
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });

    new AjaxUpload('#load', {
        action: baseURL + "sys/oss/upload",
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete : function(file, r){
            if(r.code == 0){
              vm.view.picture=r.url;
            }else{
                alert(r.msg);
            }
        }
    });
    new AjaxUpload('#load1', {
        action: baseURL + "sys/oss/upload",
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete : function(file, r){
            if(r.code == 0){
                vm.view.picture1=r.url;
            }else{
                alert(r.msg);
            }
        }
    });
    new AjaxUpload('#load2', {
        action: baseURL + "sys/oss/upload",
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete : function(file, r){
            if(r.code == 0){
                vm.view.picture2=r.url;
            }else{
                alert(r.msg);
            }
        }
    });
    new AjaxUpload('#load3', {
        action: baseURL + "sys/oss/upload",
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
                alert('只支持jpg、png、gif格式的图片！');
                return false;
            }
        },
        onComplete : function(file, r){
            if(r.code == 0){
                vm.view.picture3=r.url;
            }else{
                alert(r.msg);
            }
        }
    });
});
laydate.render({
    elem: '#time'//指定元素
    ,type: 'month'
    ,trigger: 'click'
    ,done: function(value, date, endDate){
        vm.view.time = value;
    }
});
laydate.render({
    elem: '#time1'//指定元素
    ,type: 'month'
    ,trigger: 'click'
    ,done: function(value, date, endDate){
        vm.q.time = value;
    }
});
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		view: {
            hotelName:[]
        },
        det:{},
        dict: {},//字典
        Lists:{},//字典接受
        Line:{},//线路接受
		hotel:{},//酒店
		Hotel:{},
        q:{
            name: null,
            price: null,
            area: null,
            hotelName: null,
            time: null,
            line:null,
            pricemax:null,
        },
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.view = {};
            this.getDict()
			this.getHotel()
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
        //新增时获取数据	课程信息
        getDict: function () {
            var url = baseURL + "sys/dict/selectToAdd";
            $.ajax({
                type: "GET",
                url: url,
                contentType: "application/json",
                data: vm.dict,
                success: function (r) {
                    if (r.code === 0) {
                        vm.Lists = r.dict;
                    } else {
                        alert(r.msg);
                    }
                },
                error: function () {
                }
            });
        },
        //新增时获取数据	课程信息
        getHotel: function () {
            var url = baseURL + "sys/hotel/hotel";
            $.ajax({
                type: "GET",
                url: url,
                contentType: "application/json",
                data: vm.hotel,
                success: function (r) {
                    if (r.code === 0) {
                        vm.hotel = r.hotel;
                        console.log(r.hotel)
                    } else {
                        alert(r.msg);
                    }
                },
                error: function () {
                }
            });
        },
		saveOrUpdate: function (event) {
			var url = vm.view.id == null ? "sys/view/save" : "sys/view/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.view),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/view/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(){
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }
			$.get(baseURL + "sys/view/info/"+ids, function(r){
                vm.view = r.view;
            });
            this.getDict()
            this.getHotel()
		},
        getIn: function(){
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }
            $.get(baseURL + "sys/view/info/"+ids, function(r){
                vm.det = r.view;
            });


            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "查看详情",
                area: ['600px', '400px'],
                shadeClose: false,
                content: jQuery("#detai"),
                btn1: function (index) {
                }
            });


        },
        diy: function(){
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            var url="sys/diy/"+id
            $.ajax({
                type: "POST",
                url: baseURL +url,
                contentType: "application/json",
                data:	{Viewid:id},
                success: function (r) {
                    if (r.code === 0) {
                        layer.alert('收藏成功', function(){
                            location.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                },
                error: function () {
                }
            });
		},
        detai: function(){
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            var url="sys/diy/"+id
            $.ajax({
                type: "POST",
                url: baseURL +url,
                contentType: "application/json",
                data:	{Viewid:id},
                success: function (r) {
                    if (r.code === 0) {



                    } else {
                        alert(r.msg);
                    }
                },
                error: function () {
                }
            });
        },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'name': vm.q.name,  'price': vm.q.price , 'area': vm.q.area, 'hotelName': vm.q.hotelName, 'line': vm.q.line, 'time': vm.q.time, 'pricemax': vm.q.pricemax},
                page:page
            }).trigger("reloadGrid");
		}
	}
});