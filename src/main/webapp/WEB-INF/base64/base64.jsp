<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
    <meta name="keywords" content="Base64加密、解密">
    <meta name="description" content="Base64加密、解密">
    <title>Base64加密、解密</title>
    <%@include file="../taglib.jsp"%>
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/base64/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/base64/global.css">
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/base64/bootstrap.min.css">
    <script type="text/javascript" src="${ctxs}/js/base64/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctxs}/js/base64/clipboard.min.js"></script>
    <script type="text/javascript" src="${ctxs}/js/base64/base64.js"></script>
    <script type="text/javascript" src="${ctxs}/js/base64/layer.js"></script>
</head>

<body>
<div class="aw-container-wrap">
    <div class="container">
        <div class="row">
            <div class="aw-content-wrap clearfix">
                <div class=" aw-main-content" style="min-height:700px;max-height:2000px">
                    <nav class="navbar navbar-default" id="top_menu">
                        <div class="container-fluid">
                            <ul class="nav navbar-nav" style="width: 100%;">
                                <li>
                                    <a href="/">首页</a>
                                </li>

                            </ul>

                        </div>
                    </nav>
                    <div style="clear:both"></div>
                    <!-- content st -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="media">
                                <div class="media-body">
                                    <h4 class="media-heading">Base64加密、解密</h4>
                                    <div id="desc1">Base64加密、解密</div>
                                </div>
                            </div>

                        </div>
                        <div class="panel-body">
                            <!--内容块开始-->
                            <div>
                                <textarea id="content" name="RawJson" class="json_input" rows="10" style="width: 100%;"
                                          spellcheck="false" placeholder="请输入Base64编码字符串"></textarea>
                            </div>
                            <div class="btn-group" role="group" aria-label="...">
                                <button type="button" class="btn btn-primary" onclick="base64_encode();">BASE64加密
                                </button>
                                <button type="button" class="btn btn-primary" onclick="base64_decode();">BASE64解密
                                </button>
                                <button type="button" class="btn btn-primary" onclick="exchange();">交换内容</button>
                                <button type="button" class="btn btn-danger" onclick="Empty();">清空结果</button>
                            </div>

                            <div class="btn-group" role="group" aria-label="...">
                                <button id="sels" type="button" class="btn btn-default dropdown-toggle"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    UTF-8<span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="javascript:;" onclick="sj(1,this)">UTF-8</a></li>
                                    <li><a href="javascript:;" onclick="sj(2,this)">GB2312</a></li>
                                </ul>
                            </div>
                            <div style="padding-top: 10px;">
                                <textarea id="result" name="RawJson" class="json_input" rows="10" style="width: 100%;"
                                          spellcheck="false" placeholder="加密或解密后的Base64编码字符串"></textarea>
                            </div>
                            <!--内容块结束-->
                        </div>
                        <input type="hidden" id="encode" value="1">
                        <div class="panel-footer"></div>
                    </div>
                    <script type="text/javascript">
                        function exchange() {
                            var temp = jQuery("#content").val();
                            jQuery("#content").val(jQuery("#result").val())
                            jQuery("#result").val(temp);
                        }

                        function sj(s, obj) {
                            $("#encode").val(s);
                            $("#sels").text($(obj).text());
                        }

                        function base64_encode() {
                            var encode = $("#encode").val();
                            if (encode == 1) {
                                var str = CryptoJS.enc.Utf8.parse(jQuery("#content").val());
                                var base64 = CryptoJS.enc.Base64.stringify(str);
                            } else {
                                var str = jQuery("#content").val();
                                var base64 = encode64gb2312(str);
                            }
                            jQuery("#result").val(base64);
                        }

                        function base64_decode() {
                            var encode = $("#encode").val();
                            if (encode == 1) {
                                var words = CryptoJS.enc.Base64.parse(jQuery("#content").val());
                                jQuery("#result").val(words.toString(CryptoJS.enc.Utf8));
                            } else {
                                var words = jQuery("#content").val();
                                var base64 = decode64gb2312(words);
                                jQuery("#result").val(base64);
                            }
                        }

                        function Empty() {
                            document.getElementById('content').value = '';
                            document.getElementById('result').value = '';
                            document.getElementById('content').select();
                        }

                        function GetFocus() {
                            document.getElementById('content').focus();
                        }
                    </script>
                    <!-- content ed -->
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>