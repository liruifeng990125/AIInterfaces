$(document).ready(function() {
var flag = 0;
var urldata = null;
    // 设置 <li> 标签的颜色
   
$("#btn1").click(function () {
    flag = 1;
    urldata = "http://localhost:8080/helloIFlytek";
    console.log("flag   "+flag);

    //调用接口
    console.log("urldata ==== " + urldata);
        var inputContent = $("#inputdata").val();
        console.log("inputContent ==== " + inputContent);
        if ((flag === 1 || flag === 2 || flag === 3) && urldata !== null) {
            $.ajax({
                // 请求类型
                type: "POST",
                // 请求路径
                url: urldata,
                dataType: 'text',
                // 请求数据,发送到服务器的数据，可以把文本输入框的数据返回
                //JSON对象
                data: {
                    question: inputContent,
                    type: "baidu"

                },
                //成功回调,data是返回的数据 如"调用IBaiduAI接口成功"
                success: function (answer) {
                    console.log("调用成功" + answer);
                   
                    var li = $('<li>' + answer + '</li>');
                    li.css('color', 'purple');
                    $("#outputData").append(li);
                },

                // 失败回调
                error: err => {
                    console.log(err);
                }
            })

        }
        else {
            // 弹出提示
            alert('请选择模型');
        }

});
$("#btn2").click(function () {
    flag = 2;
    urldata = "http://localhost:8080/helloIBaidu";
    console.log(flag);
    console.log("urldata ==== " + urldata);
    //调用接口
        var inputContent = $("#inputdata").val();
        if ((flag === 1 || flag === 2 || flag === 3) && urldata !== null) {
            $.ajax({
                // 请求类型
                type: "POST",
                // 请求路径
                url: urldata,
                dataType: 'text',
                // 请求数据,发送到服务器的数据，可以把文本输入框的数据返回
                //JSON对象
                data: {
                    question: inputContent,
                    type: "baidu"

                },
                //成功回调,data是返回的数据 如"调用IBaiduAI接口成功"
                success: function (answer) {

                  
                    console.log("调用成功" + answer);

                    var li = $('<li>' + answer + '</li>');
                    li.css('color', 'green');
                    $("#outputData").append(li);
                },

                // 失败回调
                error: err => {
                    console.log(err);
                }
            })

        }
        else {
            // 弹出提示
            alert('请选择模型');
        }

});
$("#btn3").click(function () {
    flag = 3;
    urldata = "http://localhost:8080/hellochatGPT";
    console.log(flag);
   //调用接口
   console.log("urldata ==== " + urldata);
        var inputContent = $("#inputdata").val();
        if ((flag === 1 || flag === 2 || flag === 3) && urldata !== null) {
            $.ajax({
                // 请求类型
                type: "POST",
                // 请求路径
                url: urldata,
                dataType: 'text',
                // 请求数据,发送到服务器的数据，可以把文本输入框的数据返回
                //JSON对象
                data: {
                    question: inputContent,
                    type: "baidu"

                },
                //成功回调,data是返回的数据 如"调用IBaiduAI接口成功"
                success: function (answer) {
                    console.log("调用成功  " + answer);
                    console.log("flag" + flag);
                 

                    var li = $('<li>' + answer + '</li>');
                    li.css('color', 'red');
                    $("#outputData").append(li);
                },

                // 失败回调
                error: err => {
                    console.log(err);
                }
            })

        }
        else {
            // 弹出提示
            alert('请选择模型');
        }





});


$("#cleanContext").click(
    function () {
       // $("li").empty();
        $("li").remove();

    }
)

});
