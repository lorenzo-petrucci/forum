$(document).on('click', '[data-ref=signup]', function() {
    sendSignUpRequest();
});

$(document).on('keypress', function(e) {
    if(e.which == 13) {
        e.preventDefault();
        sendSignUpRequest();
    }
});

function sendSignUpRequest() {
    $.ajax({
        type: 'POST',
        url: '/forum/api/v1/author/create',
        data: {
            'username': $('#username').val(),
            'password': $('#password').val(),
            '_csrf': 'csrf'
        },
        success: function() {
            window.location.href = '/forum/public/login';
        },
        error: function(request) {
            showErrorBanner(request.responseText);
        }
    });
};

function showErrorBanner(errorMessage) {
    $('[data-ref=error-message]').text(errorMessage.replaceAll('"', ''));
    $('[data-ref=error-message]').removeClass('d-none');
};