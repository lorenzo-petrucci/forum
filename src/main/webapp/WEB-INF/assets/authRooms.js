const API_URL = '/forum/private/api/v1/room/list';
$(document).ready(loadRooms(API_URL, 'public'));

$(document).on('click', '.nav-link', function() {
    $('.nav-link').removeClass('active');
    $(this).addClass('active');
    loadRooms(API_URL, $(this).data('ref'));
});

function loadRooms(apiUrl, roomType) {
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
    roomCard.children('[data-ref=date]').text(new Date(room.createdAt).toLocaleString());
    roomCard.attr('data-ref', 'populated').removeClass('d-none');
    roomCard.appendTo('[data-ref=room-list]');
};
