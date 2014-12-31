/**
 * Created by manaev on 30.12.14.
 */

var errorCallback = function(error) {
    console.log("ERROR");
    console.log(error);
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
        console.log("contacts received")
        console.log(contacts)
        wsChat.onlineUsers.set("contacts",contacts);

    });
    wsChat.stompClient.subscribe("/chat/messages",function(data){
        console.log("message received")
        console.log(data);
        wsChat.mainRegionView.addMessage(JSON.parse(data.body));
    });
};

