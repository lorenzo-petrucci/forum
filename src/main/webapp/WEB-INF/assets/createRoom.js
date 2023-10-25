$(document).on('click', '[data-ref=new-room]', function() {
    $('[data-ref=create-room-form]').removeClass('d-none');
});

$(document).on('click', '[data-ref=create-room]', function() {
    $.ajax({
        type: 'POST',
        url: '/forum/private/api/v1/room/create',
        beforeSend: CSRF_AJAX_CONFIG.beforeSend,
        data: {
            'uuid': crypto.randomUUID(),
            'title': $('[data-ref=room-title]').val(),
            'createdAt': new Date(Date.now()).toISOString(),
            'isPublic': !$('[data-ref=is-private]').is(':checked'),
            'isActive': true
        },
        success: function(res) {
            $('.nav-link').removeClass('active');
            $('[data-ref=owned]').addClass('active');
            loadRooms('/forum/private/api/v1/room/list', 'owned');
        }
    });
    $('[data-ref=create-room-form]').addClass('d-none');
});