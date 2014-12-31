/**
 * Created by manaev on 30.12.14.
 */
wsChat.module("MainRegion", function (MainRegion, wsChat, Backbone, Marionette, $, _) {

    MainRegion.RegionLayout = Backbone.Marionette.LayoutView.extend({
        regions: {
            leftMenu: "#wsLeft",
            rightMenu: "#wsRight"
        },
        tagName: "div",
        className: "container-fluid",
        initialize: function () {
            this.template = Handlebars.compile($("#wsChat-main-region-layout").html());
        },
        onShow: function(){
            this.leftMenu.show(new MainRegion.MenuLayout({model:this.model}));
            this.rightMenu.show(new wsChat.ChatWindow.WindowLayout({model:wsChat.onlineUsers}));
        },
        addMessage: function(message){
            this.rightMenu.currentView.addMessage(message);
        }
    });
});