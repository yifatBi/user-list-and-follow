// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-2.2.0.min
//= require_self
    //set for random user cookie
   function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }
    setCookie('user-id', Math.floor((Math.random() * 10) + 1), 3);
    //get cookie function for validation
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
        //call get data function to load cookie user data and update value according the user
        $.get('/home/getUserData',function(data){
           $('#userName').text(data.userName);
            $('#isFollow'+data.userId).prop('disabled',true);
           $("#row"+data.userId).addClass('table-success').prop('title', 'This is your user');;
           //update the following users
            data.following.forEach(function(val){
                $('#isFollow'+val).removeClass('follow').addClass('following').text('following');
            });
            $("#no-cookie").addClass('d-none');
            $("#content").removeClass('d-none');
        }).fail(function(err) {
            alert(err.responseText);
        });
        //after page is loaded add page handlers
        $(document).ready(function(){
            $('.follow,.following').click(function () {
                var self=$(this);
                var followersNum =$('#followersNum'+self.data('id'));
                $.ajax({url:'/home/followUser',
                    type: 'POST',
                    data:{userId:$(this).data('id'),followersNum: followersNum.text()},
                    //while ajax performing disable another ajax call
                    beforeSend:function(){
                        self.prop('disabled', true);
                    },
                    success: function(data){
                        //update text and functionality according result data
                        var action=data.action;
                        var followers=data.followerNum;
                        self.text(action).removeClass('following follow').addClass(action);
                        followersNum.text(followers);
                    },
                    error:function(error){
                        alert("there was an issue"+error)
                     },
                    complete:function(){
                         self.prop('disabled', false)
                    }
                })

            })
        })

    })(jQuery);
}
