/**
 * Created by manaev on 30.12.14.
 */
wsChat.module("MainRegion", function (MainRegion, wsChat, Backbone, Marionette, $, _) {

    MainRegion.User = Backbone.Model.extend({
    });

    MainRegion.UserContact = Backbone.Model.extend({
        defaults:{
            username:null,
            eMail:null,
            onLine:null
        }
    });
    MainRegion.UserContacts = Backbone.Collection.extend({
        model:MainRegion.UserContact,
        comparator:"username",
        initialize: function(models,options){}
    });
});