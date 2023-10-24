$(document).on('click', '[data-ref=new-room]', function() {
    $('[data-ref=create-room-form]').removeClass('d-none');
});

$(document).on('click', '[data-ref=create-room]', function() {
    let title = $('[data-ref=room-title]').val();
    let createdAt = new Date(Date.now()).toISOString();

    $.ajax({
        type: 'POST',
        url: '/forum/private/api/v1/room/create',
        beforeSend: CSRF_AJAX_CONFIG.beforeSend,
        data: {
            'uuid': crypto.randomUUID(),
            'title': title,
            'authorId': AUTHOR_ID,
            'createdAt': createdAt,
            'isPublic': !$('[data-ref=is-private]').is(':checked'),
            'isActive': true
        }
    });

    let roomCard = $('[data-ref=room]').clone();
    roomCard.children('[data-ref=author]').text(AUTHOR_NAME);
    roomCard.children('[data-ref=title]').children().first().text(title);
    roomCard.children('[data-ref=date]').text(createdAt);
    roomCard.attr('data-ref', 'populated').removeClass('d-none');
    $('[data-ref=room]').after(roomCard);

    $('[data-ref=create-room-form]').addClass('d-none');
});