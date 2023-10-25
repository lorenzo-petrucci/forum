$(document).on('click', '[data-ref=authors]', function() {
    $('li').children('a').removeClass('active');
    $(this).addClass('active');
    populateAuthorsTable();
});

$(document).on('click', '[data-ref=rooms]', function() {
    $('li').children('a').removeClass('active');
    $(this).addClass('active');
});

$(document).on('click', '[data-ref=threads]', function() {
    $('li').children('a').removeClass('active');
    $(this).addClass('active');
});

$(document).on('click', '[data-ref=messages]', function() {
    $('li').children('a').removeClass('active');
    $(this).addClass('active');
});

function populateAuthorsTable() {
    $('#table').bootstrapTable({
        pagination: true,
        sidePagination: 'server',
        search: true,
        rowStyle: blockedAuthorRowStyle,
        url: '/forum/admin/api/v1/author/search',
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
        }]
    });
};

function blockedAuthorRowStyle(row, index) {
    if (row.blocked) {
        return {classes: 'table-danger'}
    }
    return {classes: ''}
};