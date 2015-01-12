/**
 * Created by manaev on 31.12.14.
 */
/**
 * Created by manaev on 30.12.14.
 */
wsChat.module("MainRegion", function (MainRegion, wsChat, Backbone, Marionette, $, _) {

    MainRegion.ContactView = Backbone.Marionette.ItemView.extend({
        tagName:"div",
        className:"list-group",
        initialize: function () {
            this.template = Handlebars.compile($("#wsChat-main-region-menu-contacts-contact").html());
        }
    });

    MainRegion.ContactsView = Backbone.Marionette.CompositeView.extend({
        childView: MainRegion.ContactView,
        childViewContainer: "#contacts-list",
        tagName: "div",
        className: "container-fluid",
        initialize: function () {
            this.template = Handlebars.compile($("#wsChat-main-region-menu-contacts").html());
        }
    });

    MainRegion.MenuLayout = Backbone.Marionette.LayoutView.extend({
        regions: {
            contacts: "#contacts",
            videos: "#videos",
            music: "#music"
        },
        tagName: "div",
        className: "container-fluid",
        initialize: function () {
            this.template = Handlebars.compile($("#wsChat-main-region-menu").html());
        },
        onShow:function(){
            this.contacts.show(new MainRegion.ContactsView({collection: new MainRegion.UserContacts(this.model.get("contacts"))}))
        }
    });
});