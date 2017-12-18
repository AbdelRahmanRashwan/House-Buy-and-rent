var notifications;
var not_showed_ads = 0;

function addNotificationItem(notification) {
    var notification_li = document.createElement('li');
    notification_li.className = 'notification_item';
    var p = document.createElement('p');
    p.innerHTML = notification['description'];

    if (notification['showed'] === false) {
        notification_li.className+=' not_showed';
        not_showed_ads++;
    }
    notification_li.appendChild(p);
    notification_li.addEventListener('click',function () {
        window.location.href = "show_ad.jsp?id="+notification['advertisementId'];
    });
    document.getElementById('notifications_container').appendChild(notification_li);
}

function get_notifications(user_id) {
    // $.ajax({url: "Notifications?user_id="+user_id, success: function(result){
    //         notifications = JSON.parse(result);
    //         not_showed_ads = 0;
    //
    //         if(notifications != null) {
    //             for (var notification of notifications) {
    //
    //                 addNotificationItem(notification);
    //             }
    //         }
    //
    //         if(not_showed_ads >0 ) {
    //             $('#noti_Counter')
    //                 .css({opacity: 0})
    //                 .text(not_showed_ads)
    //                 .css({top: '-10px'})
    //                 .animate({top: '-2px', opacity: 1}, 500);
    //             $('#noti_Button').css({background:'#FFF'})
    //         }else{
    //             $('#noti_Counter').hide();
    //         }
    //     }});
}