function loadRooms(apiEndpoint) {
    let roomContainer = $('<div/>', {
            'class': 'room-list'
        });
    $.ajax({
        type: 'GET',
        url: apiEndpoint,
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
    $('.container').last().append(roomContainer);
};

function createRoomCard(room) {
    let card = $('<div/>', {
        'class': 'card my-3'
    });

    $('<div/>', {
        'class': 'card-header',
        'text': room.authorName
    }).appendTo(card);

    let cardBody = $('<div/>', {
        'class': 'card-body'
    });

    $('<h5/>', {
        'class': 'card-title',
        'text': room.title
    }).appendTo(cardBody);

    cardBody.appendTo(card);

    $('<div/>', {
        'class': 'card-footer text-body-secondary',
        'text': new Date(room.createdAt).toLocaleString()
    }).appendTo(card);

    card.appendTo('.room-list');
}