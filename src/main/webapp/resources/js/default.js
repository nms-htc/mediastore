(function ($) {
    $(document).ready(function () {
        $('#cssmenu > ul').prepend('<li class=\"mobile\"><a href=\"#\"><span>Menu <i>&#9776;</i></span></a></li>');
        $('#cssmenu > ul > li > a').click(function (e) {
            $('#cssmenu li').removeClass('active');
            $(this).closest('li').addClass('active');
            var checkElement = $(this).next();
            if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
                $(this).closest('li').removeClass('active');
                checkElement.slideUp('normal');
            }
            if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
                $('#cssmenu ul ul:visible').slideUp('normal');
                checkElement.slideDown('normal');
            }
            if ($(this).parent().hasClass('mobile')) {
                e.preventDefault();
                $('#cssmenu').toggleClass('expand');
            }
            if ($(this).closest('li').find('ul').children().length === 0) {
                return true;
            } else {
                return false;
            }
        });

    });
})(jQuery);

/**
 * He he he
 * @param {type} xhr
 * @param {type} status
 * @param {type} args
 * @param {type} dialogName
 * @param {type} cbBooleanParam
 * @returns {undefined}
 */
function handleDialogSubmit(xhr, status, args, dialogName, cbBooleanParam) {
    var exp = true;
    if (cbBooleanParam) {
        exp = args[cbBooleanParam];
    }
    if (args.validationFailed || !exp) {
        PF(dialogName).jq.effect("shake", {times: 5}, 100);
    } else {
        PF(dialogName).hide();
    }
}