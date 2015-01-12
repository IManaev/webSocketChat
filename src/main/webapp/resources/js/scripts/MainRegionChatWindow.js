/**
 * Created by manaev on 31.12.14.
 */
/**
 * Created by manaev on 30.12.14.
 */
wsChat.module("ChatWindow", function (ChatWindow, wsChat, Backbone, Marionette, $, _) {

    ChatWindow.MessageModel = Backbone.Model.extend({
    });

    ChatWindow.MessagesCollection = Backbone.Collection.extend({
        model:ChatWindow.MessageModel,
        comparator:"time",
        initialize: function(models,options){}
    });

    ChatWindow.OnlineUsersModel = Backbone.Model.extend({
    });

    ChatWindow.OnlineUsersCollection = Backbone.Collection.extend({
        model:ChatWindow.OnlineUsersModel,
        comparator:null,
        initialize: function(models,options){}
    });

    ChatWindow.InitialModel = Backbone.Model.extend({});

    ChatWindow.OnlineUser = Backbone.Marionette.ItemView.extend({
        tagName: "div",
        className: "media conversation",
        initialize: function () {
            this.template = Handlebars.compile($("#wsChat-main-region-public-chat-window-online-user").html());
        }
    });

    ChatWindow.OnlineUsers = Backbone.Marionette.CompositeView.extend({
        childView: ChatWindow.OnlineUser,
        childViewContainer: "#online-chat-users-container",
        tagName: "div",
        initialize: function () {
            this.template = Handlebars.compile($("#wsChat-main-region-public-chat-window-online-user-container").html());
        }
    });

    ChatWindow.Message = Backbone.Marionette.ItemView.extend({
        tagName: "div",
        className: "msg-wrap",
        initialize: function () {
            this.template = Handlebars.compile($("#wsChat-main-region-public-chat-window-message").html());
        }
    });

    ChatWindow.Messages = Backbone.Marionette.CompositeView.extend({
        childView: ChatWindow.Message,
        childViewContainer: "#chat-window-messagebox-container",
        tagName: "div",
        initialize: function () {
            this.template = Handlebars.compile($("#wsChat-main-region-public-chat-window-message-container").html());
        }
    });

    ChatWindow.WindowLayout = Backbone.Marionette.LayoutView.extend({
        regions: {
            chatUsers: "#online-chat-users",
            chatWindow: "#chat-window-messagebox"
        },
        tagName: "div",
        className: "container",
        events:{
            "click #sendMessageButton":"doSend"
        },
        initialize: function () {
            this.template = Handlebars.compile($("#wsChat-main-region-public-chat-window").html());
            this.listenTo(this.model, 'change', this.refreshContacts);
        },
        onShow: function () {
            this.chatUsers.show(new ChatWindow.OnlineUsers({collection: new ChatWindow.OnlineUsersCollection(this.model.get("contacts"))}));
        },
        refreshContacts: function () {
            this.chatUsers.show(new ChatWindow.OnlineUsers({collection: new ChatWindow.OnlineUsersCollection(this.model.get("contacts"))}));
        },
        addMessage:function(message){
            if(this.chatWindow.currentView == null){
                this.chatWindow.show(new ChatWindow.Messages({collection: new ChatWindow.MessagesCollection([new ChatWindow.MessageModel(message)])}))
            } else{
                this.chatWindow.currentView.addChild(new ChatWindow.MessageModel(message),ChatWindow.Message)
            }
        },
        doSend:function(){
            var json = {content: $("#chatText").val(),username:wsChat.user.get("username")};
            wsChat.stompClient.send("/app/message", {}, JSON.stringify(json));
        }
    });
});