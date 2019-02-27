var ajaxCallback = {};
$(function () {
    $(".convert :input[arg]").bind("click", function () {
        if (!checkArgs())
            return;
        var btn = $(this);
        var type = $(this).attr("arg");
        var args = [];
        $(".convert :input[prop]").each(function () {
            var dom = $(this);
            var v = $.trim(dom.val()).replace(/_/g, '%5F').replace(/=/g, '%3D').replace(/\+/g, '%2B');
            if (v == dom.attr('tipsinfo')) {
                return;
            }
            args.push(dom.attr('prop') + "=" + v);
        });
        var pdata = getData();
        var ptype = type;
        var parg = args.join("_");
        $(document).ajaxComplete(function () {
            showInfo2("处理中...", false);
            btn.attr('disabled', false);
        }).ajaxStart(function () {
            btn.attr('disabled', true);
            $("#formatinfo").html('');
            showInfo2("处理中...", true);
        }).ajaxError(function (event, request, settings) {
            showAlert("出错了，请稍后再试！", "提示");
        });
        $.post(location.href, {data: pdata, type: ptype, arg: parg}, function (data) {
            var dom = null;
            try {
                dom = $.parseJSON(data);
            } catch (e) {
                return false;
            }
            if (dom && dom.status) {
                if ($.isFunction(addStroage))
                    addStroage();
                if ($.isFunction(ajaxCallback)) {
                    var r = ajaxCallback({"data": pdata, "type": ptype, "arg": parg}, dom.data);
                    if (r) return;
                }
                return show(dom.data[0]);
            }
            showAlert(dom.data[0]);
        });
    });
    $(".convert input[event]").bind('keydown', function (event) {
        var attr = $(this).attr['event'];
        attr = attr ? attr : 'ENTER';
        attr = attr.split("|");
        if (event.keyCode == $.ui.keyCode[attr[0]]) {
            $(".convert :input[arg]").trigger('click');
        }
    });
    $(".convert a.ui-icon-close[forid]").bind('click', function () {
        var dom = '#' + $(this).attr('forid');
        setNode($(dom), '');
        showInfo("清理完成！");
    });
    $(".convert a.ui-icon-transferthick-e-w[forid]").bind('click', function () {
        var dom = '#' + $(this).attr('forid');
        var data = getNode($(dom));
        if (data.length < 1)
            return;
        setNode($('#' + $(this).attr('toid')), data);
        showInfo("将输出结果填到输入！！");
    });
    $(".convert a.ui-icon-copy[forid]").each(function () {
        copyClip(this);
    }).on("click", function () {
        copyClip(this);
    });
});

function copyClip(self) {
    var id = "#" + $(self).attr('forid');
    var value = getNode($(id));
    ;$(self).attr('data-clipboard-text', value);
    var clip = new ClipboardJS(self);
    clip.on('success', function (e) {
        clip.destroy();
        e.clearSelection();
        showInfo('复制成功');
    });
    clip.on('error', function (e) {
        clip.destroy();
        e.clearSelection();
        showInfo('复制失败');
    });
}

function getNode(dom) {
    var cNode = dom;
    var t = cNode[0].tagName.toLowerCase();
    var data = (t == 'textarea') ? cNode.val() : cNode.html();
    return cNode.attr('filters') ? filtersContent(data, cNode.attr('filters')) : data;
}

function setNode(dom, value) {
    value = !!value ? value : null;
    var t = dom[0].tagName.toLowerCase();
    (t == 'textarea' || t == 'input') ? dom.val(value) : dom.html(value);
}

function show(data) {
    var dom = $(":input[destin='true'],div[destin='true']");
    if (dom)
        return setNode(dom, data);
    return showAlert(data);
}

function getData() {
    var data = [];
    $(":input[source='true']").each(function () {
        var cNode = $(this);
        data.push(cNode.attr('filters') ? filtersContent(cNode.val(), cNode.attr('filters')) : cNode.val());
    })
    return data.join("^^^");
}

function checkArgs() {
    var nodes = $(":input[valid]");
    var len = nodes.length;
    for (var k = 0; k < len; k++) {
        var dom = $(nodes[k]);
        if (!valid(dom) || dom.val() == dom.attr("tipsinfo")) {
            showInfo(dom.attr("title"));
            dom.focus();
            return false;
        }
    }
    return true;
}

function valid(node) {
    var attr = node.attr('valid');
    if (!attr)
        return true;
    var valid = $.parseJSON(attr);
    if (!valid)
        return true;
    var v = $.trim(node.val());
    if (!!valid['minlength']) {
        if (v.length < valid['minlength'])
            return false;
    }
    if (!!valid['maxlength']) {
        if (v.length > valid['maxlength'])
            return false;
    }
    if (!!valid['min']) {
        if (parseInt(v) < valid['min'])
            return false;
    }
    if (!!valid['max']) {
        if (parseInt(v) > valid['min'])
            return false;
    }
    if (!!valid['digits']) {
        if (!(/^\d+$/.test(v)))
            return false;
    }
    if (!!valid['number']) {
        if (!(/^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(v)))
            return false;
    }
    return true;
}