<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class=" ext-strict x-viewport">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="description" content="JSON在线视图查看器(Online JSON Viewer)">
    <meta name="keywords" content="JSON在线视图查看器(Online JSON Viewer)"/>
    <title>JSON在线视图查看器(Online JSON Viewer)</title>
    <%@include file="../taglib.jsp"%>
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/jsonview/ext-all.css">
    <script type="text/javascript" src="${ctxs}/js/jsonview/ext-base.js"></script>
    <script type="text/javascript" src="${ctxs}/js/jsonview/ext-all.js"></script>
    <script type="text/javascript" src="${ctxs}/js/jsonview/jsonviewercn.js"></script>
</head>
    <div id="ext-comp-1004" class="x-tab-panel" style="width: 1240px;margin-top:80px ">
        <div class="x-tab-panel-header x-unselectable" id="ext-gen21"
             style="-webkit-user-select: none; width: 1238px; ">
            <div class="x-tab-strip-wrap" id="ext-gen25">
                <ul class="x-tab-strip x-tab-strip-top" id="ext-gen27">
                    <li class="" id="ext-comp-1004__viewerPanel">
                        <a class="x-tab-strip-close" onclick="return false;"></a><a class="x-tab-right"
                                                                                    href="#"
                                                                                    onclick="return false;"><em
                            class="x-tab-left"><span class="x-tab-strip-inner"><span
                            class="x-tab-strip-text ">Viewer</span></span></em></a>
                    </li>
                    <li class=" x-tab-strip-active" id="ext-comp-1004__textPanel">
                        <a class="x-tab-strip-close" onclick="return false;"></a><a class="x-tab-right"
                                                                                    href="#"
                                                                                    onclick="return false;"><em
                            class="x-tab-left"><span class="x-tab-strip-inner"><span
                            class="x-tab-strip-text ">Text</span></span></em></a>
                    </li>
                    <li class="x-tab-edge" id="ext-gen28"></li>
                    <div class="x-clear" id="ext-gen29"></div>
                </ul>
            </div>
            <div class="x-tab-strip-spacer" id="ext-gen26"></div>
        </div>
    </div>


    <div id="ext-comp-1001" class="x-tip"
         style="position: absolute; z-index: 20000; visibility: hidden; display: none; ">
        <div class="x-tip-tl">
            <div class="x-tip-tr">
                <div class="x-tip-tc">
                    <div class="x-tip-header x-unselectable" id="ext-gen10" style="-webkit-user-select: none; "><span
                            class="x-tip-header-text"></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="x-tip-bwrap" id="ext-gen11">
            <div class="x-tip-ml">
                <div class="x-tip-mr">
                    <div class="x-tip-mc">
                        <div class="x-tip-body" id="ext-gen12" style="height: auto; "></div>
                    </div>
                </div>
            </div>
            <div class="x-tip-bl x-panel-nofooter">
                <div class="x-tip-br">
                    <div class="x-tip-bc"></div>
                </div>
            </div>
        </div>
    </div>
    <!--内容块结束-->
    </div>
    <div class="panel-footer"></div>

    </div>

    </body>
    <div id="cpbtn"></div>
</html>