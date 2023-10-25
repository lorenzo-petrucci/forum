$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url: 'public/api/v1/room/list',
        data: {
            'recordPerPage': 10,
            'pageNumber': 0
        },
        success: function(res) {
            $.each(res, function(i) {
                populateRoomCard(res[i]);
            });
        }
    });
});