$(document).on('click', '[data-ref=signup]', function() {
    console.log($('#username').val());
    console.log($('#password').val());
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
        }
    });
});
