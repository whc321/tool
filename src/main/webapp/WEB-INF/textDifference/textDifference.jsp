<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>在线文本差异对比</title>
    <meta name="description" content="本工具可以方便大家快速对比两个文本文件中的不同之处。结果清晰明了，可快带替换差异内容并将结果直接下载。"/>
    <meta name="keywords" content="在线文本差异对比,文本对比,文本比较,文本比较工具,代码差异对比,内容差异对比"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <%@include file="../taglib.jsp"%>
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/base64/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/textDifference/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/textDifference/my.css">
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/textDifference/dl.css">
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/textDifference/dm-m.css">
    <link rel="stylesheet" type="text/css" href="${ctxs}/css/textDifference/dm-c.css">

    <link rel="stylesheet" type="text/css" href="${ctxs}/css/base64/bootstrap.min.css">
    <script type="text/javascript" src="${ctxs}/js/base64/bootstrap.min.js"></script>

    <script type="text/javascript" src="${ctxs}/js/textDifference/index.js"></script>
    <script type="text/javascript" src="${ctxs}/js/textDifference/m.js"></script>
    <script type="text/javascript" src="${ctxs}/js/textDifference/dm-c.min.js"></script>
    <script type="text/javascript" src="${ctxs}/js/textDifference/dm-m.js"></script>
    <script>var n = 1;</script>

    <style type='text/css'>
        .drop_zone {
            border: 2px dashed #BBBBBB;
            border-radius: 5px 5px 5px 5px;
            color: #BBBBBB;
            padding: 10px 25px;
            text-align: center;
            align: center;
            width: 80%;
        }

        #path-lhs, #path-rhs {
            font-size: 40px;
            font-family: Arial;
            color: #C9C9C9
        }

        #path-rhs {
            padding-left: 20px
        }

        #compare-editor-lhs {
            background-color: #212121;
        }

        #compare-editor-rhs {
            background-color: #002035
        }

        #compare-lhs-margin, #compare-rhs-margin {
            display: none
        }

        .CodeMirror

        -
        scroll {
            background -color: #1d1f20;
        }

        #merge-lhs-4 {
            font-family: "宋体";
        }

        .save-link {
            color: #F8F8F8;
        }

        .dmsm {
            font-family: "微软雅黑";
            font-size: 12px;
            padding-left: 20px;
        }

        #compare .CodeMirror-lines pre {
            transition: all 1s ease 0s;
        }

        #compare .CodeMirror-lines pre:hover {
            background-color: #FDFFC6;
            color: #000000
        }

        td, th {
            border: 0px;
        }

        .wbbt {
            text-align: center;
            border-bottom: 1px solid #333333;
            line-height: 40px
        }

        .txtbjdel {
            color: #C9C9C9;
            font-size: 15px;
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">

        $(document).ready(function () {
            $('#compare').mergely({
                width: 'auto',
                height: 'auto', // containing div must be given a height
                cmsettings: {readOnly: false},
            });

            function checkFileList(files) {
                if (typeof window.FileReader !== 'function')
                    error_msg("The file API isn't supported on this browser yet.");

                if (files.length > 0) readFile(files[0], "lhs");
                if (files.length > 1) readFile(files[1], "rhs");
            }

            function readFile(file, side) {
                var reader = new FileReader();
                reader.onload = function file_onload() {
                    // document.getElementById('td1').innerHTML = ..
                    $('#path-' + side).text(file.name);
                    $('#compare').mergely(side, reader.result);
                }
                reader.readAsBinaryString(file);

            }

            function handleDragOver(evt) {
                evt.stopPropagation();
                evt.preventDefault();
                evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
            }

            function handleFileSelect(evt) {
                document.getElementById('drop_zone').visibility = "collapse";
                evt.stopPropagation();
                evt.preventDefault();
                var files = evt.dataTransfer.files; // FileList object.
                checkFileList(files);
            }

            var dropZone = document.getElementById('drop_zone');
            document.body.addEventListener('dragover', handleDragOver, false);
            document.body.addEventListener('drop', handleFileSelect, false);

            function download_content(a, side) {


                //a.innerHTML = "preparing content..";
                var txt = $('#compare').mergely('get', side);
                var datauri = "data:plain/text;charset=UTF-8," + encodeURIComponent(txt);
                a.setAttribute('download', side + ".txt");
                a.setAttribute('href', datauri);
                //a.innerHTML = "content ready.";
            }

            document.getElementById('save-lhs').addEventListener('mouseover', function () {
                download_content(this, "lhs");
            }, false);
            document.getElementById('save-rhs').addEventListener('mouseover', function () {
                download_content(this, "rhs");
            }, false);
        });

    </script>
</head>
<body data-spy="scroll" data-target=".navbar-example" style="overflow-x:hidden">

<div class="aw-container-wrap">
    <div class="container">
        <div class="row">
            <div class="aw-content-wrap clearfix">
                <div class=" aw-main-content" style="">

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


                    <!-- content ed -->
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid m0 bod top70" id="zt">
    <table align="center" style="width: 100%;">
        <tr style="">
            <td style="width: 50%;"><tt id="path-lhs"></tt> &nbsp; <a
                    id="save-lhs" class="save-link" href="#"><i class="fa fa-floppy-o" aria-hidden="true"></i>
                save</a><span class="dmsm">(在下面输入您的代码或文本，或直接把文件拖入到框中，内容会自动获取)</span> <span class="txtbjdel txta"><i
                    class="fa fa-trash-o"></i> 清除A内容</span></td>
            <td style="width: 50%;"><tt id="path-rhs"></tt> &nbsp; <a
                    id="save-rhs" class="save-link" href="#"><i class="fa fa-floppy-o" aria-hidden="true"></i>
                save</a><span class="dmsm">(在下面输入您的代码或文本，或直接把文件拖入到框中)</span> <span class="txtbjdel txtb"><i
                    class="fa fa-trash-o"></i> 清除B内容</span></td>
        </tr>
    </table>
    <div id="mergely-resizer" style="height:800px;">
        <div id="compare">
        </div>
    </div>
</div>

</body>
</html>