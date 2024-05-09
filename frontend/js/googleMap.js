$(window).load(function(){
	var mapUrl = "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3027.7935366702213!2d-8.657476923391526!3d40.63443497140541!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd239800adc211dd%3A0x334a8e212aaee06d!2sCentro%20Hospitalar%20do%20Baixo%20Vouga%20-%20Aveiro!5e0!3m2!1spt-PT!2spt!4v1715277011757!5m2!1spt-PT!2spt";
    var onLoadWebSite = true;
    var googleMapHolder = $(".google_map");
    var backgroundColor = googleMapHolder.css("backgroundColor");
    var mapWidth = googleMapHolder.css("width");
    var mapHeight = googleMapHolder.css("height");
    var borderTopLeftRadius = googleMapHolder.css("borderTopLeftRadius");
    var borderTopRightRadius = googleMapHolder.css("borderTopLeftRadius");
    var borderBottomLeftRadius = googleMapHolder.css("borderTopLeftRadius");
    var borderBottomRightRadius = googleMapHolder.css("borderTopLeftRadius");
    var addMap = false;
    var idPage;
    var intervalCall;
	    
    if(backgroundColor == "rgba(0, 0, 0, 0)"){
        backgroundColor= "#ffffff";
    }
    verificationPageHandler();
    if(onLoadWebSite == false){
        $(window).bind("hashchange", verificationPageHandler);
    }
    function verificationPageHandler(){
        if(onLoadWebSite == false){
        	idPage = "#"+window.location.hash.substring(3, window.location.hash.length);
        	if(idPage != "#"){
				if(googleMapHolder.parents(idPage).length != 0){
	                addGoogleMapHandler();
	                
       			}	
        	}
        }else{
            addGoogleMapHandler();
        }
    }
    function addGoogleMapHandler(){
        if(!addMap){
            addMap = true;
            $(window).unbind("hashchange", verificationPageHandler);
            googleMapHolder.css({"overflow":"hidden"});
            //googleMapHolder.append("<div id='loaderPart' style='position:absolute; z-index:1; width:"+mapWidth+"; height:"+mapHeight+"; background:"+backgroundColor+" url(images/googleMapLoader.gif) no-repeat 50%; border-top-left-radius:"+borderTopLeftRadius+"; border-top-right-radius:"+borderTopRightRadius+"; border-bottom-right-radius:"+borderBottomLeftRadius+"; border-bottom-left-radius:"+borderBottomRightRadius+";'></div>");
            googleMapHolder.append("<div id='loaderPart' style='position:absolute; z-index:1; width:"+mapWidth+"; height:"+mapHeight+"; background:"+backgroundColor+"; border-top-left-radius:"+borderTopLeftRadius+"; border-top-right-radius:"+borderTopRightRadius+"; border-bottom-right-radius:"+borderBottomLeftRadius+"; border-bottom-left-radius:"+borderBottomRightRadius+";'></div>");
            intervalCall = setInterval(addIframe, 200)
        }
        function addIframe(){
        	if($(idPage).css("display")!="none"){
        		clearInterval(intervalCall);
	     	  	googleMapHolder.append("<iframe width='"+mapWidth+"' height='"+mapHeight+"' frameborder='0' src='"+mapUrl+"' style='position:absolute; z-index:0; border-top-left-radius:"+borderTopLeftRadius+"; border-top-right-radius:"+borderTopRightRadius+"; border-bottom-right-radius:"+borderBottomLeftRadius+"; border-bottom-left-radius:"+borderBottomRightRadius+";'></iframe>");
	        	googleMapHolder.find("iframe").load(googleMapLoadCompleteHandler);
			}
        }
    }
    function googleMapLoadCompleteHandler(){
    	var loaderPart = googleMapHolder.find("#loaderPart");
        mapSpinner.stop();
        loaderPart.delay(100).fadeOut(500, function(){loaderPart.css({"display":"none"});});
        
    }
})