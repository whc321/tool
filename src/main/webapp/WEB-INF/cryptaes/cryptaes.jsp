<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html lang="zh_cn">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
    <meta name="keywords" content="在线AES加密解密、AES在线加密解密、AES encryption and decryption">
    <meta name="description" content="在线AES加密解密、AES在线加密解密、AES encryption and decryption">
    <title>在线AES加密解密、AES在线加密解密、AES encryption and decryption</title>
    <%@include file="../taglib.jsp"%>
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/cryptaes/custom.css">
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/cryptaes/web.css">
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/base64/bootstrap.min.css">
    <script type="text/javascript" src="${ctxs}/js/base64/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctxs}/js/base64/layer.js"></script>
    <script type="text/javascript" src="${ctxs}/js/cryptaes/ZeroClipboard.js"></script>
    <script type="text/javascript" src="${ctxs}/js/cryptaes/p.js"></script>
    <script type="text/javascript" src="${ctxs}/js/cryptaes/main.js"></script>
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

                    <div id="main">
                        <div class="section_content">
                            <div class="convert  my-alert-info my-alert">
                                <div>
                                    <label class="f14 bold">AES加密模式:</label>
                                    <select prop="m" class="h30">
                                        <option value="ecb">ECB</option>
                                        <option value="cbc">CBC</option>
                                        <option value="ctr">CTR</option>
                                        <option value="ofb">OFB</option>
                                        <option value="cfb">CFB</option>
                                    </select> &nbsp;
                                    <label class="f14 bold">填充:</label>
                                    <select prop="pad" class="h30">
                                        <option value="no">NoPadding</option>
                                        <option value="pkcs5">PKCS5Padding</option>
                                        <option value="pkcs7">PKCS5Padding</option>
                                        <option value="zero" selected>ZeroBytePadding</option>
                                        <option value="iso10126">ISO10126Padding</option>
                                        <option value="ansix923">ANSIX923</option>
                                    </select>
                                    <label class="f14 bold">数据块:</label>
                                    <select prop="block" class="h30">
                                        <option value="128">128位</option>
                                        <option value="192">192位</option>
                                        <option value="256">256位</option>
                                    </select>
                                    &nbsp;
                                    <label class="f14 bold">密码:</label>
                                    <input type="text" prop="p" valid='{"minlength":1}' tipsinfo="请输入密码！" title="请输入密码！" class="inline-block sareainput f16p " />
                                    <label class="f14 bold">偏移量:</label>
                                    <input type="text" prop="i" tipsinfo="iv偏移量，ecb模式不用填写！" title="iv偏移量，ecb模式不用填写！" class="inline-block sareainput f16p " />
                                    <label class="f14 bold">输出:</label>
                                    <select prop="o" class="h30">
                                        <option value="0">base64</option>
                                        <option value="1">hex</option>
                                    </select>
                                    <label class="f14 bold p_l10">字符集：</label>
                                    <select prop='s' class="h30">
                                        <option value="utf-8">utf8</option>
                                        <option value="gb2312">gb2312</option>
                                        <option value="gbk">gbk</option>
                                        <option value="gb18030">gb18030</option>
                                    </select>
                                </div>
                                <div class="m_t10">
                                    <span class="l">待加密、解密的文本:</span>
                                    <a class="ui-icon ui-icon-copy l" title="复制" forid="converts"></a>
                                    <a class="ui-icon ui-icon-close l" title="清除" forid="converts"></a>&nbsp;&nbsp;&nbsp;
                                </div>
                                <textarea id="converts" name="converts" source='true' drag="text" class="textarea f14 blue" valid='{"minlength":1}' title="请输入需要转换文本！" tipsinfo="请输入需要转换文本！"></textarea>
                                <input id="t" prop='t' type="hidden" value="0">
                                <div class="tc">
                                    <input type="button" value="AES加密" title="AES加密" wait="正在处理，稍后..." class="btn f14 h30" arg="aes" onclick="javascript:$('#t').val(0);" />&nbsp;&nbsp;&nbsp;&nbsp;
                                    <input type="button" value="AES解密" title="AES解密" wait="正在处理，稍后..." class="btn f14 h30" arg="aes" onclick="javascript:$('#t').val(1);" />
                                </div>
                                <div class="inline-block clearfix m_t10">
                                    <span id='formatinfo' class="r"></span>
                                    <span class="l">AES加密、解密转换结果(base64了):</span>
                                    <a class="ui-icon ui-icon-copy l" title="复制" forid="convertd"></a>
                                    <a class="ui-icon ui-icon-close l" title="清除" forid="convertd"></a>
                                    <a class="ui-icon ui-icon-transferthick-e-w l" title="填充到输入框" forid="convertd" toid="converts"></a>
                                </div>
                                <textarea name="convertd" id="convertd" class="textarea f14 blue" destin="true"></textarea>
                            </div>
                        </div>
                    </div>


                    <%--<div class="panel panel-default">
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
                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</div>












</body>
</html>
