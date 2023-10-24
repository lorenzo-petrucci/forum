function loadRooms(apiUrl, roomType='') {
    $('[data-ref=populated]').remove();
    $.ajax({
        type: 'GET',
        url: apiUrl,
        data: {
            'recordPerPage': 10,
            'pageNumber': 0,
            'roomType': roomType
        },
        success: function(res) {
            $.each(res, function(i) {
                populateRoomCard(res[i]);
            });
        }
    });
};

function populateRoomCard(room) {
    let roomCard = $('[data-ref=room]').clone();
    roomCard.children('[data-ref=author]').text(room.authorName);
    roomCard.children('[data-ref=title]').children().first().text(room.title);
    roomCard.children('[data-ref=date]').text(new Date(room.createdAt));
    roomCard.attr('data-ref', 'populated').removeClass('d-none');
    roomCard.appendTo('[data-ref=room-list]');
};

