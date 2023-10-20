$(document).on('click', '.nav-link', function() {
    if ($('.room-list') != null) {
        $('.room-list').remove();
        $('.nav-link').removeClass('active');
    }
    $(this).addClass('active');
    let apiEndpoint = mapToEndpoint($(this).attr('id'))
    loadRooms(apiEndpoint);
});

function mapToEndpoint(elementId) {
    let pascalCaseId = elementId.charAt(0).toUpperCase() + elementId.slice(1);
    return '/forum/api/v1/room/list' + pascalCaseId;
}