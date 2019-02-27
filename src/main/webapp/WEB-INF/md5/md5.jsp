<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
    <meta name="keywords" content="md5加密、解密">
    <meta name="description" content="md5加密、解密">
    <title>md5加密、解密-BeJSON.com</title>
    <%@include file="../taglib.jsp"%>
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/base64/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/base64/global.css">
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/base64/bootstrap.min.css">
    <script type="text/javascript" src="${ctxs}/js/base64/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctxs}/js/base64/clipboard.min.js"></script>
    <script type="text/javascript" src="${ctxs}/js/md5/md5.js"></script>
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
                                    <h4 class="media-heading">MD5加密</h4>
                                    <div id="desc1">MD5加密</div>
                                </div>
                            </div>

                        </div>
                        <div class="panel-body">
                            <!--内容块开始-->
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">加密前</span>
                                <input type="text" class="form-control" id="str" placeholder="要加密的文本"
                                       aria-describedby="basic-addon1">
                            </div>

                            <div class="input-group" style="margin-top: 20px;">
                                <span class="input-group-addon" id="basic-addon1">加密后</span>
                                <input type="text" class="form-control" id="estr" placeholder="加密后的文本"
                                       aria-describedby="basic-addon1">
                            </div>
                            <div class="btn-group" role="group" aria-label="..." style="margin-top: 20px;">
                                <button type="button" class="btn btn-primary" onclick="md5encode();">MD5加密</button>
                                <button type="button" class="btn btn-danger" onclick="Empty();">清空结果</button>
                            </div>

                            <!--内容块结束-->
                        </div>
                        <input type="hidden" id="encode" value="1">
                        <div class="panel-footer"></div>
                    </div>
                    <script type="text/javascript">
                        function md5encode() {
                            $("#estr").val(CryptoJS.MD5($("#str").val()));
                        }


                        function Empty() {
                            document.getElementById('str').value = '';
                            document.getElementById('estr').value = '';
                            document.getElementById('str').select();
                        }

                        function GetFocus() {
                            document.getElementById('str').focus();
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