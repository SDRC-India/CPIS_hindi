
<!-- Nav Menu Section -->
<div class="logo-menu" ng-controller="navCtrl" ng-cloak>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container-fluid" style="background-color: #254e71;">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="col-md-12">
				<div class="navbar-header col-md-12 navbar-header-mobview">
					<div class="col-lg-3 col-md-3 col-sm-4 col-xs-12  mobView">
						<ul class="header-links">
							<li ng-click="changeLang('en')"><a href="#">English</a></li>
							<li ng-click="changeLang('hi')"><a href="#">&#2361;&#2367;&#2344;&#2381;&#2342;&#2368;
							</a></li>
						</ul>
					</div>
					<div class="col-lg-5 col-md-5 col-sm-4 col-xs-12  text-right">
						<ul class="header-links navbar-right hidden-xs" >
							<label>Font Size:</label>
							<li><a href="javascript:void(0);"
								onClick="changemysize(12);"><small>-A</small></a></li>
							<li><a href="javascript:void(0);"
								onClick="changemysize(14);">A</a></li>
							<li><a href="javascript:void(0);"
								onClick="changemysize(16);"><big>A+</big></a></li>
						</ul>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12  text-right headertext">
						<ul class="header-links navbar-right header-border ">
							<!--<li><form>
  <input class="searchbutton" type="text" name="search" placeholder="Search.." style="color: #ffffff;"><i class="fa fa-search fasearch" style="color: #616a7d;" aria-hidden="true"></i>
</form></li>-->
							
							<li ng-repeat="link in toplinks" ng-cloak
								class="{{link.className}} mobview"><a href="{{link.url}}"
								class="{{link.sub && 'dropdown-toggle' || ''}}"
								data-toggle="{{link.sub && 'dropdown' || ''}}">{{link.name}}
									<b ng-show="link.sub" class="caret"></b>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!--<script src="resources/js/classie.js"></script>
		<script src="resources/js/uisearch.js"></script>
		<script>
			new UISearch( document.getElementById( 'sb-search' ) );
		</script>-->
		<!-- logo row starts -->
		<div class="container-fluid header_bg headerbackground">
			<div class="col-md-12 headerbackground">
				<div class="col-md-9 col-sm-6 col-xs-6 headerbackground">
					<div class="col-md-12 logo_holder">
						<a  href="http://mahilakalyan.up.nic.in/" target="_blank">
						<img src="resources/img/wcd_logo.jpg" alt="WCD UP"  height="70px" class="header-logo-image img-responsive" />
						</a>
						<h2 class="hidden-xs hidden-sm">{{title}}</h2>
						<h3 class="hidden-xs hidden-sm">{{subtitle1}}<br>
						{{subtitle}}</h3>
<!-- 						<h3 class="hidden-xs hidden-sm"></h3> -->
					</div>
					<h4 class=" hidden-md hidden-lg mobile-header">CPIS</h4>
				</div>
				<div class="col-md-3 col-sm-6 col-xs-6  imageRespnsiveTab nav-logo-padding">
					<div class="logo_holder">
					<a  href="http://up.gov.in/" target="_blank"><img class="img img-responsive img-circle logo-header-circle"
							src="resources/img/up_logo.png" alt="Uttar Pradesh Government" /></a>
					</div>
				</div>
			</div>
		</div>
		<!-- logo row ends -->

		<!-- menu row starts -->
		<div style="background-color: #fff;" class="container-fluid headerborder" >
			<div class="container-fluid">
				<div class="header" header col-md-12 id="home" >
					<div class="content col-md-12">
						<nav class="navbar navbar-default col-md-12 bordertopbuttom"
							role="navigation">
							<div class="container">
								<div class="navbar-header">
									<button type="button" class="navbar-toggle"
										data-toggle="collapse"
										data-target="#bs-example-navbar-collapse-1">
										<span class="sr-only">Toggle navigation</span> <span
											class="icon-bar"></span> <span class="icon-bar"></span> <span
											class="icon-bar"></span>
									</button>
								</div>
								<!--/.navbar-header-->
								<div class="collapse navbar-collapse menu-gap"
									id="bs-example-navbar-collapse-1">
									<ul class="nav navbar-nav headerfont menuLists">
									
									<li><a href="home">Home</a></li>
									<li><a href="about">About Us</a></li>
									<li class="dropdownli"><a href="#">ICPS Structure<b class="caret"></b></a>
										<ul class="dropdown-menu">
											<li ><a href="structure">Structure</a></li>
											<li class="subActive"><a href="cciInfoMap">Info Map</a></li>
										</ul>
									</li>
									<li><a href="programme">Programme</a></li>
									<li><a href="rti">RTI</a></li>
									<li class="dropdownliGallery"><a href="#">Gallery<b class="caret"></b></a>
										<ul class="dropdown-menu">
											<li class="subActive"><a href="gallery">Photo Gallery</a></li>
											<li><a href="audiogallery">Audio Gallery</a></li>
											<li><a href="videogallery">Video Gallery</a></li>
										</ul>
									</li>
									<li><a href="ccts_login" class="border">CCTS Login</a></li>
									

<!-- 										<li ng-repeat="link in links"  ng-cloak -->
<!-- 											class="{{link.className}}" ng-click="activate(link)" -->
<!-- 											ng-class="{'activeMenu' : active == link}" -->
<!-- 											><a href="{{link.url}}" -->
<!-- 											class="{{link.sub && 'dropdown-toggle' || ''}}" -->
<!-- 											ng-class="{'border': link.url=='ccts_login'}" -->
<!-- 											 data-toggle="{{link.sub && 'dropdown' || ''}}" >{{link.name}} -->
<!-- 												<b ng-show="link.sub" class="caret"></b> -->
<!-- 										</a> -->
<!-- 											<ul class="dropdown-menu" ng-show="link.sub"> -->
<!-- 												<li ng-repeat="subItem in link.sub" ng-cloak><a -->
<!-- 													href="{{subItem.url}}">{{subItem.name}}</a></li> -->
<!-- 											</ul></li> -->
									</ul>
								</div>
								<!--/.navbar-collapse-->
								<!--/.navbar-->
							</div>
						</nav>
					</div>
				</div>
			</div>
		</div>
		<!-- menu row ends -->

	</nav>
</div>

