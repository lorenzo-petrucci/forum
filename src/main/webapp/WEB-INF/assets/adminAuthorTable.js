
$(document).on('click', '[data-ref=search-author]', function() {
    loadAuthors();
});

function loadAuthors() {
    $.ajax({
        type: 'GET',
        url: '/forum/admin/api/v1/author/search',
        data: {
            'name': $('#author-name').val()
        },
        success: function(res) {
            populateBootstrapTable(res);
        }
    });
};

function populateBootstrapTable(authorList) {
    $('#table').bootstrapTable({
        columns: [{
            field: 'id',
            title: 'Id'
        }, {
            field: 'name',
            title: 'Name'
        }, {
            field: 'privilege',
            title: 'Privilege'
        }, {
            field: 'createdAt',
            title: 'Created at'
        }, {
            field: 'blocked',
            title: 'Blocked'
        }],
        data: authorList
    });
};