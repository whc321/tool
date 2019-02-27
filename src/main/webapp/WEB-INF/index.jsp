<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>HTML-DEV-ToolLink</title>
    <%@include file="taglib.jsp"%>
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/index/index.css" />
</head>
<body>
<div class="con-main">
    <div class="search-box" style="display: none;">
        <input type="text" placeholder="请输入关键字" class="txt-search" id="searchKey" autofocus="autofocus">
        <a href="javascript:void(0);" onclick="javascript:clearSearch();" class="close-del">×</a>
        <button class="go-search" onclick="javascript:goSearch();">搜索</button>
    </div>
    <div class="main-box">
        <div class="classify-title"><span>JSON</span> <span>（bejson-JSON相关工具）</span></div>
        <div class="main-box">
            <div class="block-box"><a href="javascript:void(0);" title="http://www.bejson.com/jsonviewernew/">
                <div class="up-box"><span class="tit">JSON视图</span> <span class="path">（http://www.bejson.com/jsonviewernew/）</span>
                </div>
            </a> <a href="${ctx}/json/jsonview" title="JSON在线视图查看器(Online JSON Viewer)" target="_blank">
                <div class="down-box">JSON在线视图查看器(Online JSON Viewer)</div>
            </a></div>
        </div>
        <div class="classify-title"><span>编码/加密</span> <span>（bejson-编码/加密工具）</span></div>
        <div class="main-box">
            <div class="block-box"><a href="javascript:void(0);" title="https://www.bejson.com/enc/base64/">
                <div class="up-box"><span class="tit">Base64编码、解码</span> <span class="path">（https://www.bejson.com/enc/base64/）</span>
                </div>
            </a> <a href="${ctx}/base64" title="Base64加密、解密" target="_blank">
                <div class="down-box">Base64加密、解密</div>
            </a></div>
            <div class="block-box"><a href="javascript:void(0);" title="https://www.bejson.com/enc/md5/">
                <div class="up-box"><span class="tit">MD5加密</span> <span
                        class="path">（https://www.bejson.com/enc/md5/）</span></div>
            </a> <a href="${ctx}/md5" title="md5加密、解密" target="_blank">
                <div class="down-box">md5加密、解密</div>
            </a></div>
            <div class="block-box"><a href="javascript:void(0);" title="http://tool.chacuo.net/cryptaes">
                <div class="up-box"><span class="tit">在线AES加密解密、AES在线加密解密</span> <span class="path">（http://tool.chacuo.net/cryptaes）</span>
                </div>
            </a> <a href="${ctx}/cryptaes" title="在线AES加密解密、AES在线加密解密"
                    target="_blank">
                <div class="down-box">在线AES加密解密、AES在线加密解密</div>
            </a></div>
            <div class="block-box" style="display: none;"><a href="javascript:void(0);" title="https://www.bejson.com/enc/urlencode/">
                <div class="up-box"><span class="tit">URL编码</span> <span class="path">（https://www.bejson.com/enc/urlencode/）</span>
                </div>
            </a> <a href="https://www.bejson.com/enc/urlencode/" title="在线url网址编码、解码" target="_blank">
                <div class="down-box">在线url网址编码、解码</div>
            </a></div>
            <div class="block-box" style="display: none;"><a href="javascript:void(0);"
                                      title="https://www.bejson.com/convert/unicode_chinese/">
                <div class="up-box"><span class="tit">unicode中文互转</span> <span class="path">（https://www.bejson.com/convert/unicode_chinese/）</span>
                </div>
            </a> <a href="https://www.bejson.com/convert/unicode_chinese/" title="在线unicode转中文,中文转unicode"
                    target="_blank">
                <div class="down-box">在线unicode转中文,中文转unicode</div>
            </a></div>
        </div>
        <div class="classify-title"><span>格式化</span> <span>（bejson-格式化工具）</span></div>
        <div class="main-box" style="display: none;">
            <div class="block-box"><a href="javascript:void(0);" title="https://www.bejson.com/otherformat/xml/">
                <div class="up-box"><span class="tit">XML压缩、格式化</span> <span class="path">（https://www.bejson.com/otherformat/xml/）</span>
                </div>
            </a> <a href="" title="可以对JXML进行格式化排版，整齐的进行显示。可以对XML进行加密，加密压缩。"
                    target="_blank">
                <div class="down-box">可以对JXML进行格式化排版，整齐的进行显示。可以对XML进行加密，加密压缩。</div>
            </a></div>
        </div>

        <div class="classify-title" style="display: none;"><span>后端</span> <span>（bejson-后端工具）</span></div>
        <div class="main-box" style="display: none;">
            <div class="block-box"><a href="javascript:void(0);" title="http://www.bejson.com/json2javapojo/new/">
                <div class="up-box"><span class="tit">JSON生成Java实体类</span> <span class="path">（http://www.bejson.com/json2javapojo/new/）</span>
                </div>
            </a> <a href="http://www.bejson.com/json2javapojo/new/" title="在线JSON字符串转Java实体类(JavaBean)" target="_blank">
                <div class="down-box">在线JSON字符串转Java实体类(JavaBean)</div>
            </a></div>
            <div class="block-box"><a href="javascript:void(0);" title="http://www.bejson.com/devtools/sql2pojo/">
                <div class="up-box"><span class="tit">mysql转Java实体类</span> <span class="path">（http://www.bejson.com/devtools/sql2pojo/）</span>
                </div>
            </a> <a href="http://www.bejson.com/devtools/sql2pojo/" title="在线SQL转Java实体类(POJO,JavaBean)"
                    target="_blank">
                <div class="down-box">在线SQL转Java实体类(POJO,JavaBean)</div>
            </a></div>
            <div class="block-box"><a href="javascript:void(0);"
                                      title="https://www.bejson.com/devtools/properties2yaml/">
                <div class="up-box"><span class="tit">properties转yaml</span> <span class="path">（https://www.bejson.com/devtools/properties2yaml/）</span>
                </div>
            </a> <a href="https://www.bejson.com/devtools/properties2yaml/"
                    title="在线properties转yaml工具(properties to yaml)" target="_blank">
                <div class="down-box">在线properties转yaml工具(properties to yaml)</div>
            </a></div>
            <div class="block-box"><a href="javascript:void(0);" title="https://www.bejson.com/validators/yaml_editor/">
                <div class="up-box"><span class="tit">YAML在线编辑(校验)器</span> <span class="path">（https://www.bejson.com/validators/yaml_editor/）</span>
                </div>
            </a> <a href="https://www.bejson.com/validators/yaml_editor/" title="YAML在线编辑器,实时查看您的YAML文件编辑情况"
                    target="_blank">
                <div class="down-box">YAML在线编辑器,实时查看您的YAML文件编辑情况</div>
            </a></div>
        </div>
        <div class="classify-title"><span>转换</span> <span>（bejson-转换工具）</span></div>
        <div class="main-box" style="display: none;">
            <div class="block-box"><a href="javascript:void(0);" title="https://www.bejson.com/convert/unix/">
                <div class="up-box"><span class="tit">Unix时间转换</span> <span class="path">（https://www.bejson.com/convert/unix/）</span>
                </div>
            </a> <a href="https://www.bejson.com/convert/unix/" title="Unix时间戳转换" target="_blank">
                <div class="down-box">Unix时间戳转换</div>
            </a></div>
        </div>
        <div class="classify-title"><span>文本比较工具</span> <span>（两个文本比较差异）</span></div>
        <div class="main-box">
            <div class="block-box"><a href="javascript:void(0);" title="http://www.jq22.com/textDifference">
                <div class="up-box"><span class="tit">在线文本差异比较</span> <span class="path">（http://www.jq22.com/textDifference）</span>
                </div>
            </a> <a href="${ctx}/textDifference" title="在线文本差异比较" target="_blank">
                <div class="down-box">在线文本差异比较</div>
            </a></div>
        </div>
    </div>
</div>

</body>
</html>
