let apiUrl = setApiUrl();
$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url: apiUrl,
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

function setApiUrl() {
    let privilege = window.location.pathname.split("/")[2];
    let roomType = window.location.pathname.split("/")[3];
    let commonPath = "/forum/api/v1/room/";
    if (privilege == "public") {
        return commonPath + "listPublic";
    } else {
        switch(roomType) {
            case "rooms":
                return commonPath + "listPrivate";
            case "subscribed":
                return commonPath + "listSubscribed";
            case "owned":
                return commonPath + "listOwned"
            default:
                return null;
        }
    }
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

    card.appendTo('#roomList');
}