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
        onClickRow: editAuthor,
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
            title: 'Created at',
            formatter: formatDate
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

let currentAuthorId;
function editAuthor(row, element, field) {
    $('#authorModalLabel').text(row.name);
    $('#setPrivilege').val(row.privilege);
    $('#isBlocked').attr('checked', row.blocked);
    $('#authorModal').modal('show');
    currentAuthorId = row.id;
};

function formatDate(value, row, index, field) {
    return new Date(value).toLocaleString();
};

$(document).on('click', '#saveAuthorChanges', function() {
    console.log($('#setPrivilege').val());
    console.log($('#isBlocked').is(':checked'));
    console.log(currentAuthorId);

    let apiUrl = '/forum/admin/api/v1/author/' + currentAuthorId + '/edit';
    $.ajax({
        type: 'POST',
        url: apiUrl,
        beforeSend: CSRF_AJAX_CONFIG.beforeSend,
        data: {
            'privilege': $('#setPrivilege').val(),
            'isBlocked': $('#isBlocked').is(':checked')
        },
        success: function() {
            $('#table').bootstrapTable('refresh');
        }
    });
});