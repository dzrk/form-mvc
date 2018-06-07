
jQuery(document).ready(function($) {
    jQuery("#mailDiv").load('iploc.php');
    var imageUrl = $('#bg-image-get img').attr('src');
    var HomeimageUrl = $('#home-bg-image-get img').attr('src');


    $('#post-img').css('background-image', 'url(' + imageUrl + ')');

    $('#home-bg-image-print').css('background-image', 'url(' + HomeimageUrl + ')');

    $('body').on('click', '.bar', function() {
        if ($('#myNav').hasClass('open')) {
            $('#myNav').css('height', '0').removeClass('open');
        } else {
            $('#myNav').css('height', '100%').addClass('open');
        }

    });
    $(document).keyup(function(e) {
        if (e.which == 27) {
            CloseMenuBar();
        } // esc
    });
    $('.close-nav').click(function() {
        CloseMenuBar();
        jQuery(this).removeClass('close-nav');
    });
});