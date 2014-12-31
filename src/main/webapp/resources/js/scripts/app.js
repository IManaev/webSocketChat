/**
 * Created by manaev on 30.12.14.
 */
var wsChat = new Marionette.Application();
wsChat.addRegions({
    mainRegion:"#main-region"
});
wsChat.on("start", function () {
    console.log("onstart")
    wsChat.mainRegion.show(new wsChat.MainRegion.RegionLayout({model:wsChat.user}))
});