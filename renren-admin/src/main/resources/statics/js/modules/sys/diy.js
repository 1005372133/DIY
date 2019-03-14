$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/diy/selectByDiy',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
            { label: '景点名称', name: 'name', index: 'name', width: 80 , templet: '#name', event: 'name-event'},
			{ label: '地区', name: 'area', index: 'area', width: 80 },
			{ label: '交通方式', name: 'transport', index: 'transport', width: 80 },
			{ label: '价格', name: 'price', index: 'Price', width: 80 },
			{ label: '酒店', name: 'hotelId', index: 'hotel_id', width: 80 },
			{ label: '酒店名称', name: 'hotelName', index: 'hotel_name', width: 80 },
			{ label: '图片', name: 'picture', index: 'picture', width: 80 },
			{ label: '备注', name: 'remarks', index: 'Remarks', width: 80 },
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
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		view: {},
        dict: {},//字典
        Lists:{},//字典接受
		hotel:{},//酒店
		Hotel:{},
        q:{
            hotelName: null,
            price: null
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
		getInfo: function(id){
			$.get(baseURL + "sys/view/info/"+id, function(r){
                vm.view = r.view;
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
						console.log(id)
                    	alert("新增成功")
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
                postData:{'hotelName': vm.q.hotelName,  price: vm.q.price},
                page:page
            }).trigger("reloadGrid");
		}
	}
});