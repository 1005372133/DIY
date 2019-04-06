$(function () {
    $.post("sys/view/list",{},function (list) {
        var row_hot ="";
        for (var i = 0; i < list.page.list.length; i++) {
            row_hot += '<div class="col-md-3">\n' +
                '                                <a href="404.html?rid='+list.page.list[i].id+'">\n' +
                '                                    <img src="'+list.page.list[i].picture+'" alt="">\n' +
                '                                    <div class="has_border">\n' +
                '                                        <h3>'+list.page.list[i].name+'</h3>\n' +
                '                                        <div class="price">网付价<em>￥</em><strong>'+list.page.list[i].price+'</strong><em>起</em></div>\n' +
                '                                    </div>\n' +
                '                                </a>\n' +
                '                            </div>';
        }
        $("#row_hot").html(row_hot);


        var row_time ="";
        for (var i = 0; i < list.page.list.length; i++) {
            row_time += '<div class="col-md-3">\n' +
                '                                <a href="404.html?rid='+list.page.list[i].id+'">\n' +
                '                                    <img src="'+list.page.list[i].picture+'" alt="">\n' +
                '                                    <div class="has_border">\n' +
                '                                        <h3>'+list.page.list[i].name+'</h3>\n' +
                '                                        <div class="price">网付价<em>￥</em><strong>'+list.page.list[i].price+'</strong><em>起</em></div>\n' +
                '                                    </div>\n' +
                '                                </a>\n' +
                '                            </div>';
        }
        $("#row_time").html(row_time);



    });
})
/*轮播*/
layui.use('carousel', function(){
    var carousel = layui.carousel;
    //建造实例
    carousel.render({
        elem: '#test1'
        ,width: '100%' //设置容器宽度
        ,height: '500px'
        ,arrow: 'always' //始终显示箭头
        //,anim: 'updown' //切换动画方式
    });
});
var vm = new Vue({
    el:'#rrapp',
    data:{
        username: '',
        password: '',
        captcha: '',
        error: false,
        errorMsg: '',
        src: 'captcha.jpg',
        uname:'',
        upassword:'',
        uemail:'',
        umobile:'',
    },
    beforeCreate: function(){
        if(self != top){
            top.location.href = self.location.href;
        }
    },
    methods: {
        //打开登陆模态
        loginOpen: function(){
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "登陆",
                area: ['600px', '400px'],
                shadeClose: false,
                content: jQuery("#login123"),
            });
        },

        diy: function(){
            var url="sys/view/list"
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data:null,
                success: function (r) {
                    if (r.code === 0) {
                        alert("收藏成功")
                    } else {
                        alert(r.msg);
                    }
                },
                error: function () {
                }
            });
        },
        //  刷新验证码
        refreshCode: function(){
            this.src = "captcha.jpg?t=" + $.now();
        },

        //  登陆
        login: function (event) {
            var data = "username="+vm.username+"&password="+vm.password+"&captcha="+vm.captcha;
            $.ajax({
                type: "POST",
                url: "sys/login",
                data: data,
                dataType: "json",
                success: function(result){
                    if(result.code == 0){//登录成功
                        console.log(result)
                        if (vm.username==='admin'){
                            parent.location.href ='index.html';
                        }
                        else {
                            parent.location.href ='404.html';
                        }
                    }else{
                        vm.error = true;
                        vm.errorMsg = result.msg;

                        vm.refreshCode();
                    }
                }
            });
        },
        //  注册
        regester: function(){
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "注册用户",
                area: ['600px', '400px'],
                shadeClose: false,
                content: jQuery("#regist"),
                btn: ['注册','取消'],
                btn1: function (index) {
                    var data = "password=" + vm.upassword + "&username=" + vm.upassword+ "&email=" + vm.uemail+ "&mobile=" + vm.umobile;
                    //  console.log(this.vm.uname)
                    $.ajax({
                        type: "PUT",
                        url: "sys/user/register",
                        data: data,
                        dataType: "json",
                        success: function(result){
                            alert("注册成功")
                            layer.close(index);
                        }
                    });
                }
            });
        }
    },
    mounted:{

    }
});