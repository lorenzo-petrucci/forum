let modifiedAuthors = {};

$(document).on('click', '#search-author', function() {
    $('[data-ref=populated-author]').remove();
    loadAuthors();
    $('[data-ref=author-list]').removeClass('d-none');
    $('#modify-blocked-status').removeClass('d-none');
});

$(document).on('click', '#modify-blocked-status', function() {
    $.each(modifiedAuthors, function(key, value) {
        blockAuthor(key, value);
    });
    modifiedAuthors = {};
    $('[data-ref=populated-author]').remove();
    $('[data-ref=author-list]').addClass('d-none');
    $('#modify-blocked-status').addClass('d-none');
});

$(document).on('change', '[data-ref=is-blocked]', function() {
    modifiedAuthors[$(this).parent().siblings("[data-ref=id]").text()] = $(this).is(":checked");
});

function loadAuthors() {
    $.ajax({
        type: 'GET',
        url: '/forum/admin/api/v1/author/search',
        data: {
            'name': $('#author-name').val()
        },
        success: function(res) {
            $.each(res, function(i) {
                populateAuthorCard(res[i]);
            });
        }
    });
};

function blockAuthor(id, block) {
    $.ajax({
        type: 'POST',
        url: '/forum/admin/api/v1/author/block',
        beforeSend: CSRF_AJAX_CONFIG.beforeSend,
        data: {
            'id': id,
            'block': block
        }
    });
};

function populateAuthorCard(author) {
    let authorRow = $('[data-ref=author-template]').clone();
    authorRow.children('[data-ref=id]').text(author.id);
    authorRow.children('[data-ref=name]').text(author.name);
    authorRow.children('[data-ref=privilege]').text(author.privilege);
    authorRow.children('[data-ref=created-at]').text(new Date(author.createdAt).toLocaleDateString("en-US"));
    if(author.blocked) {
        authorRow.children('[data-ref=block]').children('[data-ref=is-blocked]').attr('checked', true);
    }
    authorRow.attr('data-ref', 'populated-author');
    authorRow.removeClass('d-none');
    authorRow.appendTo('[data-ref=author-list]');
};