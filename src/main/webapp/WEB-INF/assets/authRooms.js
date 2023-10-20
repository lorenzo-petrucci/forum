$(document).ready(loadRooms('public'));

$(document).on('click', '.nav-link', function() {
    $('.nav-link').removeClass('active');
    $(this).addClass('active');
    $('[data-ref=populated]').remove();
    loadRooms($(this).data('ref'));
});