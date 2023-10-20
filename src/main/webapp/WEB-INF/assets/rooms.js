$(document).on('click', '.nav-link', function() {
    if ($('.room-list') != null) {
        $('.room-list').remove();
        $('.nav-link').removeClass('active');
    }
    $(this).addClass('active');
    loadRooms($(this).attr('id'));
});

function loadRooms(roomCategory) {
    let roomContainer = $('<div/>', {
            'class': 'room-list'
        });
    let apiEndpoint = mapToEndpoint(roomCategory);
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

function mapToEndpoint(elementId) {
    let pascalCaseId = elementId.charAt(0).toUpperCase() + elementId.slice(1);
    return '/forum/api/v1/room/list' + pascalCaseId;
}

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