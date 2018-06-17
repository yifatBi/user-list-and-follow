// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-2.2.0.min
//= require bootstrap
//= require_self
   function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }
    setCookie('user-id', Math.floor((Math.random() * 10) + 1), 3)
    function getCookie(name) {
        var re = new RegExp(name + "=([^;]+)");
        var value = re.exec(document.cookie);
        return (value != null) ? unescape(value[1]) : null;
    }
if (typeof jQuery !== 'undefined') {
    (function($) {
        $(document).ajaxStart(function() {
            $('#spinner').fadeIn();
        }).ajaxStop(function() {
            $('#spinner').fadeOut();
        }).on('mouseover', '.following', function() {
            $(this).text("unfollow")
        }).on('mouseout', '.following', function() {
            $(this).text("following")
        });
        $(document).ready(function(){
            $('.follow,.following').click(function () {
                console.log('action');
                var self=$(this);
                $.ajax({url:'/user/followUser',
                    type: 'POST',
                    data:{userId:$(this).data('id'),follow: 1},
                    beforeSend:function(){
                    self.prop('disabled', true);
                    },
                success: function(resp){
                    var action=resp.action;
                    var followers=resp.followerNum
                    self.text(action).removeClass('following follow').addClass(action);
                    $('#followersNum'+self.data('id')).text(followers);
                },
                error:function(error){
                    alert("there was an issue")
                 },
                    complete:function(){
                    self.prop('disabled', false)
                    }
                })

            })
        })

    })(jQuery);
}
