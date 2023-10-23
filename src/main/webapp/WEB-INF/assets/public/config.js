const CSRF_AJAX_CONFIG = {
    beforeSend: function (xhr) {
        const k = $("meta[name='_csrf_header']").attr("content");
        const v = $("meta[name='_csrf']").attr("content");
        if (k && v) {
            xhr.setRequestHeader(k, v);
        }
    }
};