/**
 * Created by manaev on 30.12.14.
 */

var errorCallback = function(error) {
   alert(error);
};


var wsChat = new Marionette.Application();
wsChat.addRegions({
    mainRegion:"#main-region"
});
wsChat.on("start", function () {
    wsChat.stompClient = Stomp.over(new SockJS("/webSocketChat/ws"));
    wsChat.stompClient.connect("guest", "guest", connectCallback, errorCallback);
    wsChat.mainRegionView = new wsChat.MainRegion.RegionLayout({model:wsChat.user});
    wsChat.mainRegion.show(wsChat.mainRegionView)
});


var connectCallback = function(){
    wsChat.stompClient.subscribe('/user/' + wsChat.user.get("username") + '/participants',function(data){
        var receivedContacts = JSON.parse(data.body);
        var contacts = [];
        _.each(receivedContacts, function(contact){
            if(contact.username != wsChat.user.get("username")){
                contacts.push(contact);
            }
        });
        wsChat.onlineUsers.set("contacts",contacts);

    });
    wsChat.stompClient.subscribe("/chat/messages",function(data){
        wsChat.mainRegionView.addMessage(JSON.parse(data.body));
    });
};

