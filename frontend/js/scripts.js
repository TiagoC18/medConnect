include('js/jquery.easing.1.3.js');
include('js/jquery.color.js');
include('js/mathUtils.js');
include('js/superfish.js');
include('js/switcher.js');
include('js/jquery.mousewheel.js');
include('js/hoverSprite.js');
include('js/spin.js');
include('js/googleMap.js');
//----Include-Function----
function include(url){ 
  document.write('<script src="'+ url + '" type="text/javascript"></script>'); 
}
//--------global-------------
var isSplash = true;
var isAnim = true;
var isFirst = true;
var spinner;
var mapSpinner;
var MSIE = ($.browser.msie) && ($.browser.version <= 8)
//------DocReady-------------
$(document).ready(function(){ 
    if(location.hash.length == 0){
        //location.hash="!/"+$('#content > ul > li:first-child').attr('id');
    }
///////////////////////////////////////////////////////////////////
loaderInit();
function loaderInit(){
        var opts = {
              lines: 11,
              length: 10, 
              width: 10, 
              radius: 20, 
              rotate: 0, 
              color: '#28a4e1', 
              speed: 1.3, 
              trail: 60, 
              shadow: false,
              hwaccel: false, 
              className: 'spinner', 
              zIndex: 2e9, 
              top: 'auto', 
              left: 'auto' 
        };
        var target = $(".page_spinner > span");
        spinner = new Spinner(opts).spin();
        target.append(spinner.el) 
        ///////////////////////////////////////    
            var opts2 = {
              lines: 8,
              length: 0, 
              width: 8, 
              radius: 12, 
              rotate: 10, 
              color: '#4cbaf0', 
              speed: 1.3, 
              trail: 60, 
              shadow: false,
              hwaccel: false, 
              className: 'spinner', 
              zIndex: 2e9, 
              top: 'auto', 
              left: 'auto' 
        };
        var target2 = $(".google_map > span");
        mapSpinner = new Spinner(opts2).spin();
        target2.append(mapSpinner.el)  
     
} 
///////////////////////////////////////////////////////////////////

     $('ul#menu').superfish({
          delay:       500,
          animation:   {opacity:'show'},
          speed:       600,
          autoArrows:  false,
         dropShadows: false,
         	onInit: function(){
  				$("#menu > li > a").each(function(index){
  					var conText = $(this).find('.mText').text();
                    // $(this).append("<div class='_area'></div><div class='mTextOver'>"+conText+"</div>"); 
                    $(this).append("<div class='_area'></div><div class='_overPl'></div><div class='mText_over'>"+conText+"</div>");   
  				})
  	 		}
        }); 
});
  
 //------WinLoad-------------  
$(window).load(function(){  
////////////////////////////////////////////////////////    
$('.page_spinner').fadeOut();
spinner.stop();
$('body').css({overflow:'auto', 'min-height':'900px'})
$('.menu > ul > li').eq(0).css({'display':'none'});
///////////////////////////////////////////////////////
        
Init();
function Init(){
    $('._follow_list > li').hover(
        function(){
            $(this).find('a').stop().animate({top:"-5px"}, 400, 'easeOutCubic');
        },    
        function(){
            $(this).find('a').stop().animate({top:"0px"}, 400, 'easeOutCubic');
        }    
    )
}//End Init

///////////////////////////////////////////////
    var navItems = $('.menu > ul >li');
	var content=$('#content'),
		nav=$('.menu');

    	$('#content').tabs({
		preFu:function(_){
			_.li.css({left:"-1700px",'display':'none'});
		}
		,actFu:function(_){			
			if(_.curr){
				_.curr.css({left:'-1700px','display':'block'}).stop().delay(100).animate({left:"0px"},1000,'easeOutCubic');
                
                cont_resize(_.n);
                if ((_.n == 0) && ((_.pren>0) || (_.pren==undefined))){splashMode();}
                if (((_.pren == 0) || (_.pren == undefined)) && (_.n>0) ){contentMode(); }
                centrRepos();
            }
			if(_.prev){
			     _.prev.stop().animate({left:'1700px'},1000,'easeInOutCubic',function(){_.prev.css({'display':'none'});} );
             }
		}
	})
    
var _delay;
    function splashMode(){
        isSplash = true;
        $(".bg_pic").stop(true).animate({'margin-left':'-200px'}, 800, 'easeInOutCubic');
        $('._contacts').slideDown(600);
    }
    
    function contentMode(){  
        isSplash = false;   
        $(".bg_pic").stop(true).animate({'margin-left':'50px'}, 800, 'easeInOutCubic');     
        $('._contacts').slideUp(600);  
    }
    
    function cont_resize(_page){
        var li_W = $('#content > ul > li').eq(_page).find('.containerContent').innerHeight();
            //$('#content').css({height:li_W+"px", 'overflow':'visible'});
            
           // if(li_W < 1000){li_W = 1000}
         //   $('body').stop().animate({'min-height':li_W+'px'},700)
    }		
    
    
	nav.navs({
			useHash:true,
             hoverIn:function(li){
                        $(".mText", li).stop(true).animate({top:'100px'}, 400, 'easeOutCubic');
                        $(".mText_over", li).stop(true).animate({top:'0px'}, 400, 'easeOutCubic');
                        $("._overPl", li).stop(true).animate({top:'0px'}, 400, 'easeInOutCubic');
             },
                hoverOut:function(li){
                    if ((!li.hasClass('with_ul')) || (!li.hasClass('sfHover'))) {
                        $(".mText", li).stop(true).animate({top:'0px'}, 400, 'easeInOutCubic');
                        $(".mText_over", li).stop(true).animate({top:'-100px'}, 400, 'easeOutCubic');
                        $("._overPl", li).stop(true).animate({top:'-100px'}, 400, 'easeInOutCubic');
                    } 
                } 
		}).navs(function(n){			
			$('#content').tabs(n);
		})
        
//////////////////////////////////////////
   	var h_cont;
	function centrRepos() {
         h_cont = $('.center').height();
        // $('body').animate({'min-height':h_cont+'px'},400)
		var wh=$('body').height();
			m_top = ~~(wh-h_cont)/2;
			h_new = wh;
    
		//$('.center').stop().animate({'margin-top':m_top},600,'easeOutCubic');
            
        var _bottom = ((wh - $('header').height())/2)-50;
        if(isSplash){
            $('header').stop().animate({'bottom':_bottom+'px'},800,'easeInOutCubic');
            
        }else{
            $('header').stop().animate({'bottom':'55px'}, 800,'easeInOutCubic');
            
        }
        
	}
	centrRepos();
    ///////////Window resize///////
	$(window).resize(function(){
        centrRepos();
        }
    );

    } //window function
) //window load
