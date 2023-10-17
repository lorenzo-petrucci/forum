$(document).ready(function() {
    $.ajax({
        'type': 'GET',
        'url': '/forum/api/v1/room/listPublic',
        success: function(res) {
            $.each(res, function(i) {
                createCard(res[i]);
            });
            console.log(res);
        }
    });
});

function createCard(room) {
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