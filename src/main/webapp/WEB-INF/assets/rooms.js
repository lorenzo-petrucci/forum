$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url: '/forum/api/v1/room/listPublic',
        data: {
            'recordPerPage': 10,
            'pageNumber': 0
        },
        success: function(res) {
            $.each(res, function(i) {
                createRoomCard(res[i]);
            });
        }
    });
});

function createRoomCard(room) {
    card = $('<div/>', {
        'class': 'card my-3'
    });

    $('<div/>', {
        'class': 'card-header',
        'text': room.authorId
    }).appendTo(card);

    cardBody = $('<div/>', {
        'class': 'card-body'
    });

    $('<h5/>', {
        'class': 'card-title',
        'text': room.title
    }).appendTo(cardBody);

    cardBody.appendTo(card);

    $('<div/>', {
        'class': 'card-footer text-body-secondary',
        'text': room.createdAt
    }).appendTo(card);

    card.appendTo('#roomList');
}