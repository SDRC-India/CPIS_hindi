<div id="copyright">
 
  

	<div class="copyright-wrapper" >
    <div class="container-fluid" >
     <div class="col-md-12" >
    
      <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12  hover_links "> 
<p class="supportedBY">Supported by<a href="http://www.unicef.org" target="_blank">&nbsp;<img src="resources/images/unicef-white.png" alt="UNICEF" class="unicef_img_logo" ></a></p> 

 </div> 

 <div class="col-lg-5 col-md-4 col-sm-12 col-xs-12 hover_links footer-text-align">
<p class="termsCondition">&copy; Copyright 2017. <a  href="/CPIS/terms" style= "color:#fff;" >Terms of Use</a> | <a  href="/CPIS/disclaimer" style= "color:#fff;" > Disclaimer</a> | <a  href="/CPIS/privacyPolicy" style= "color:#fff;" > Privacy Policy</a> | <a  href="/CPIS/sitemap" style= "color:#fff;" > Sitemap</a></p>
</div>
       
	<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12 footer-text-align" style="color: #fff;">
                                 
	<p class="visitorCount" ng-cloak>Visitor Count: <span style="color: #f06626;">{{count}}</span></p>
	
    </div>
    
    <div class="col-lg-2 col-md-3 col-sm-12 col-xs-12 hover_links footer-text-align">
		<p class="poweredBy">Powered by<a href="http://www.sdrc.co.in" target="_blank" style="text-decoration: none; color: #f06626;">&nbsp;SDRC</a></p>
		</div>
		</div>
    		</div>
    <div class="clear"></div>
    
    
   
  </div>
	</div>
	
	<!-- Copyright Section End-->
	<script type="text/javascript">
$(document).ready(function(){

		if(location.pathname.includes("home") || location.pathname.includes("about") || location.pathname.includes("structure") || location.pathname.includes("cciInfoMap") || location.pathname.includes("programme") || location.pathname.includes("rti") || location.pathname.includes("gallery") 
				|| location.pathname.includes("audiogallery") || location.pathname.includes("videogallery") || location.pathname.includes("videogallery")){
       	$(".openNavMenu").addClass("activeMenu");
       }
})


</script>

<script type="text/javascript">
	$(".menuLists a").filter(function(){
    return this.href == location.href.replace(/#.*/, "");
}).addClass("activeMenus");
</script>

<script type="text/javascript">
$(document).ready(function() {
	if(window.location.pathname.indexOf("structure") >= 0 || window.location.pathname.indexOf("cciInfoMap") >= 0){
	$(".dropdownli").addClass("activeMenu");
	$(".dropdownli").siblings().removeClass("activeMenu");
	}
	
	if(window.location.pathname.indexOf("gallery") >= 0 || window.location.pathname.indexOf("audiogallery") >= 0 || window.location.pathname.indexOf("videogallery") >= 0){
		$(".dropdownliGallery").addClass("activeMenu");
		$(".dropdownliGallery").siblings().removeClass("activeMenu");
		}
	} );
</script>











