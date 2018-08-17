/*********************samiksha map**************************/
var firstElement = false;
myAppConstructor
		.directive(
				"samikshaMap",
				function($window) {
					function link(scope, el) {
						
						if(firstElement){
							scope.element2 = el[0];
						}
						else{
							scope.element1 = el[0];
							firstElement = true;
						}
						var DELAY = 00, clicks = 0, timer = null;

						var url = location.pathname;

						function onmousemove(d) {
							d3
									.select(".map_popover")
									.style("display", "block")
									.style("left", (d3.event.pageX) - 190 + "px")// TODO:
									// make it dynamic so that position would be according to the text length
									.style("top", (d3.event.pageY - 140) + "px")
									.style("opacity", "1");

						}
						function onmouseout(d) {
							d3
									.select(".map_popover")
									.style("display", "none");
						}

						function onover(d) {
							d3.selectAll(".activehover").classed("activehover",
									false);
							var rank,datavalue;
							if(d.properties.utdata != null)
								d3.select(".map_popover_content").html(
									"<strong>Area Name:</strong> <span style='color: #386d5c;'>"
											+ d.properties.NAME1_.toLowerCase() + "</span>" + "<br/>"
									+"<strong>Value:</strong> <span style='color: #386d5c;'>"
									+ d.properties.utdata.value + "</span>");
							else
								d3.select(".map_popover_content").html(
										"<strong>Area Name:</strong> <span style='color: #386d5c;'>"
												+ d.properties.NAME1_.toLowerCase() + "</span>" + "<br/>"
										+"<strong>Value:</strong> <span style='color: #386d5c;'>"
										+ "NA" + "</span>");
							
							if(d3.selectAll(".activeClicked")[0].length == 0)
							d3.select(this.parentNode.appendChild(this))
									.classed("activehover", true);
						}
						
						function onclick(d){
							if(!$("#largeMapContainer").height()){
								d3.selectAll(".activeClicked").classed("activeClicked",
										false);
								scope.selectedDistrictId = d.properties.utdata.areaId;
								scope.selectedDistrictName = d.properties.NAME1_.toLowerCase();
								scope.lineChartValue();
								d3.select(this.parentNode.appendChild(this))
								.classed("activeClicked", true);
							}
						}
						
						scope.mapSetup = function(url, callbackMethod, el, us, feature) {
							d3.select(el).selectAll("*").remove();
							var w = scope.getWindowDimensions();
							
							var width = d3.select(el)[0][0].parentNode.clientWidth, height = 300, centered;
							scope.rectHeight = 300; //used to align legend on utdata watch
							if(width == $(window).width()){
								width = $(window).width()-30-$(window).width()/20;
								height = $(window).height() - 150;
								scope.rectHeight = $(window).height() - 150;
							}
							var projection = d3.geo.mercator().scale(1);
							var path = d3.geo.path().projection(projection);
							
							if($(window).width() < 480)
								var svg = d3.select(el).append("svg").attr("id","mapsvg").attr("class", "mapsvg").attr("width", width).attr("height", height+150);
							else
								var svg = d3.select(el).append("svg").attr("id","mapsvg").attr("class", "mapsvg").attr("width", width).attr("height", height);
							svg.append("rect").attr({style : "fill:none;pointer-events:all;"})
							.attr("width", width)
							.attr("height", height)
//							.on("click", clicked)
							
							.on("mouseover",function() {
										d3.selectAll(".activehover").classed("activehover", false);
							});

							var g = svg.append("g").attr("id", "mapg");

							

												var b = path.bounds(feature), s = 0.95 / Math.max((b[1][0] - b[0][0])/ width,(b[1][1] - b[0][1])/ height);
												projection.scale(s);
												b = d3.geo.bounds(feature);
												projection.center([(b[1][0] + b[0][0]) / 2,(b[1][1] + b[0][1]) / 2 ]);
												projection.translate([ width / 2,height / 2 ]);

												g.append("g").attr("id", "districts")
														.selectAll("path")
														.data(topojson.feature(us,us.objects.UP_District).features)
														.enter()
														.append("path")
														.attr("d", path)
														.on("mouseover", onover)
														.on("mouseout", onmouseout)
														.on("click", onclick).style("cursor", "pointer");
												g.on("mousemove", onmousemove);
												

						};

						scope.$watch("utdata",function() {
							console.log(d3.selectAll(".mapsvg"));
							console.log(scope.utdata)
							if(scope.utdata != undefined){
								$(".loader").css("display", "block")
								setTimeout(function(){
									$(".loader").css("display", "none");
									d3.selectAll(".mapsvg").selectAll("text").selectAll("*").remove();				
									d3.selectAll(".mapsvg").selectAll("path")
									.attr("class",function(d) {
										if (!(scope.utdata && scope.utdata.dataCollection)) {
											d.properties.utdata = null;
											return;
										}
										for (var i = 0; i < scope.utdata.dataCollection.length; i++) {
											if (d.properties && d.properties.ID_ == scope.utdata.dataCollection[i].areaCode) {
												d.properties.utdata = scope.utdata.dataCollection[i];
												if(scope.selectedDistrictId){
													if(scope.selectedDistrictId == d.properties.utdata.areaId){
														return scope.utdata.dataCollection[i].cssClass + " activeClicked";
													}
													else{
														return scope.utdata.dataCollection[i].cssClass;
													}
												}
												else{
													return scope.utdata.dataCollection[i].cssClass;
												}
												
											} else {
												if (d.properties) {
													d.properties.utdata = null;
												}
											}
										}
									});
									d3.selectAll("path.firstslices").attr("fill", "#386d5c").style("background-color", "#386d5c");
									d3.selectAll("path.secondslices").attr("fill", "#fdae61").style("background-color", "#fdae61");
									d3.selectAll("path.thirdslices").attr("fill", "#a6d96a").style("background-color", "#a6d96a");
									d3.selectAll("path.fourthslices").attr("fill", "#1a9641").style("background-color", "#1a9641");
									d3.selectAll("path.fifthslices").attr("fill", "#aaaaaa").style("background-color", "#aaaaaa");
									// Initialize legend
									function updateLegend(data){
									    var legend = d3.select("svg#mapsvg").selectAll("g.legend")
									        .data(data);
									    
									    if($(window).width() < 480)
										    var legendEnter = legend.enter().append("g")
										        .attr("class", "legend")
										        .attr("transform", function(d, i) { 
										        	return "translate(0," + (300 + i * 20) + ")"; 
										        	});
									    else{
									    	var legendEnter = legend.enter().append("g")
									        .attr("class", "legend")
									        .attr("transform", function(d, i) { 
									        	return "translate(0," + (200 + i * 20) + ")"; 
									        	});
									    }
									    
									    
									    legendEnter.append("rect")
									        .attr("width", 14)
									        .attr("height", 14);
									        
									    legendEnter.append("text")
									        .attr("y", 9)
									        .attr("dy", ".30em")
									        .style("text-anchor", "start");
									    
									    legend.select("rect")
									        .attr("x", 0)   
									        .style("fill", function(d) { return color[d.value]; });        
									        
									    legend.select("text")
									        .attr("x", 20).attr("fill", "#333")
									        .style({"font-size": "13px", "font-weight": "500"})
									        .text(function(d) { return d.desc; });
									    
									    legend.exit().remove();
									}
									// Data
									
									var color = {
											firstslices: "#386d5c",
											secondslices: "#fdae61",
											thirdslices: "#a6d96a",
											fourthslices: "#1a9641",
											fifthslices: "#aaaaaa"	
									}
									updateLegend(scope.utdata.legends);


								})
								
							}
							
//							d3.selectAll(".mapsvg").selectAll("text").each(insertLinebreaks);
							
						}, true);	
						
					}
					return {
						link : link,
						restrict : "E"
					};
				});





/**************samiksha line chart*******************/

myAppConstructor
.directive(
		"samikshaLine",
		function($window) {
			function link(scope, el) {

				var el = el[0];
				var clicks = 0;

				// Render graph based on 'data'
				scope.$watch("dataprovider", function(data) {
					// remove all
//					console.log(data);
					function draw(data) {
						var w = $(window);
						var wnw =d3.select(el)[0][0].parentNode.clientWidth;
						if(el.id=='ciclLineChart')
							{
							wnw= $(document).outerWidth() * 39 / 100
							}
						var wnh = (w.width()> 940)? 950: 668;
					d3.select("#trendsvg");
//						var margin = {
//							top : 60,
//							right : 45,
//							bottom : 60,
//							left :  30,
//						},
						var margin = {
								top : wnh/10,
								right : wnw/120,
								bottom : wnh/10,
								left : wnw/10
							}, 
//							width = $(document).outerWidth() * (wnw/17) / 140;
							width = wnw - (3 * (margin.left + margin.right)), 
							height = wnw > 940 ? (wnh/2.8) : (wnh/2.8)- margin.top - margin.bottom;

						// set the ranges
						var x = d3.scale.ordinal().rangeRoundBands(
								[ 0, width ], 1.0);// author:anyatama
						var y = d3.scale.linear().rangeRound([ height, 0 ]);

						// define the axis
						var xAxis = d3.svg.axis().scale(x).orient("bottom")
								.ticks(5);
						var yAxis = d3.svg.axis().scale(y).orient("left")
								.ticks(5);

						// // Define the line
						var lineFunction = d3.svg.line().x(function(d) {
							return x(d.date);
						}).y(function(d) {
							return y(d.value);
						}).interpolate("line");;

						// Adds the svg canvas
						
						if(wnw > 940){
							var svg = d3.select(el).append("svg").attr("id",
							"trendsvg").attr("width",
									wnw).attr(
							"height",
							height + margin.top + margin.bottom)
							.append("g").attr(
									"transform",
									"translate(" + margin.left + ","
											+ margin.top + ")").style(
									"fill", "#000");
						}
						else{
							var svg = d3.select(el).append("svg").attr("id",
							"trendsvg").attr("width",
									wnw).attr(
							"height",
							height + margin.top + margin.bottom)
							.append("g").attr(
									"transform",
									"translate(" + 2 * margin.left + ","
											+ margin.top + ")").style(
									"fill", "#000");
						}

						// Get the data
						data.forEach(function(d) {
//							console.log(d);
							d.date = d.date;
							d.value = +d.value;
						});

						x.domain(data.map(function(d) {
							return d.date;
						}));
						// Y domain set using loop
						var flag = false;
						data.forEach(function (d){
							if(d.value  > 100){
								y.domain([ 0, d3.max(data, function(d) {
									return d.value;
								}) ]);
								flag = true;
						}
					});
						
						if(!flag){
							y.domain([ 0, 100 ]);
							
						}
					

						// Nest the entries by symbol
						var dataNest = d3.nest().key(function(d) {
							return d.source;
						}).entries(data);

						// Loop through each symbol / key
//						var color = d3.scale.category10(); // 
						var color = d3.scale.ordinal().range(
						 [ "#28323c", "#d3d3d3", "#7b6888",
						 "#66CCFF", "#9c8305" ,"#101b4d" , "#17becf"]);
						// Add the X Axis
						//============Text wrap function in x-axis of column chart=====================
						//************ end ************
						
						 svg.append("g").attr("class", "x axis").attr(
								"transform", "translate(0," + height + ")")
								.call(xAxis).append("text").attr("x",
										width - margin.right).attr("y",
										margin.bottom).attr("dx", ".71em")
								.style("text-anchor", "middle").text(
										"Time Period").attr("x", width / 2)// 
										.attr("y", 49.28).style({"fill":"#000", "font-weight": "bold"});

						svg.selectAll(".tick text").style("text-anchor",
								"end").attr("dx", "-.8em").attr("dy",
								".15em").attr("transform", function(d) {
							return "rotate(-25)";
						});

						

						// Add the Y Axis
						var ya = svg.append("g").attr("class", "y axis").call(yAxis);
						ya.selectAll("text");
						ya.append("text").attr("transform",
								"rotate(-90)").attr("y", -60).attr("x", -height/2).attr(
								"dy", ".71em").style("text-anchor",
								"end").style({"font-weight": "bold"}).text(function(d){return "Number";}).style("fill",
								"#000");
				
						// adding multiple line chart

						for (var index = 0; index < dataNest.length; index++) {

							var series = svg.selectAll(".series").data(
									dataNest[index].values).enter().append(
									"g").attr("class", "series").attr("id",
									"tag" + dataNest[index].key);

							series.select(".line").data(function() {
								return dataNest[index].values;
							}).enter().append("path")
								.attr("class", "line")
									.attr("id", "tag" + dataNest[index].key)
									.attr("d",function(d) {
												return lineFunction(dataNest[index].values);
											}).style("stroke", function(d) {
										return color(dataNest[index].key);
									}).style("stroke-width", "2px").style(
											"fill", "none").style("shape-rendering","auto");

							series.select(".point").data(function() {
								return dataNest[index].values;
							}).enter().append("circle").attr("id",
									"tag" + dataNest[index].key).attr(
									"class", "point").attr("cx",
									function(d) {
										return x(d.date);
									}).attr("cy", function(d) {
								return y(d.value);
							}).attr("r", "3px").style("fill", function(d) {
								return color(dataNest[index].key);
							}).style("stroke", "grey").style(
									"stroke-width", "2px").on("mouseover",
									function(d) {
										showPopover.call(this, d);
									}).on("mouseout", function(d) {
								removePopovers();
							});

							/*svg.append("text").attr("x", 15)// 
							.attr("y", (index * 30) -20).style("font-size","15px")// ("y", height
							// +
							// (margin.right
							// / 2) + 5)

							.attr("class", "legend").style("fill",
									function() {
										return color(dataNest[index].key);
									}).text("cases registered");*/

							svg.append("rect").attr("x", 0)// author
							// anyatama
							.attr("y", (index * 30)-30).attr("rx", 2).attr("ry",
									2).attr("width", (wnw/60)).attr("height", (wnw/60))
									.style("fill", function(d) {
										return color(dataNest[index].key);
									}).attr("id", 'rext' + index).attr(
											"key", dataNest[index].key)
									.style("stroke", "grey")
									.on("click", function() { // ************author:kamal***
										// Determine if current line is
										// visible
										rectClickHandler.call(this);
									});

						}


						// End adding multiple line chart

						// click handler for hiding series data
						function rectClickHandler() {

							var disName;
							var fillColor;
							if (d3
									.select(
											"#tag"
													+ dataNest[parseInt($(this)[0].id
															.substr($(this)[0].id.length - 1))].key)
									.style("display") == "none") {
								disName = "block";
								fillColor = color(dataNest[parseInt($(this)[0].id
										.substr($(this)[0].id.length - 1))].key);
							} else {
								disName = "none";
								fillColor = "#fff";
							}
							svg.selectAll("#" + $(this)[0].id + "").style(
									"fill", fillColor);
							svg
									.selectAll(
											"#tag"
													+ dataNest[parseInt($(this)[0].id
															.substr($(this)[0].id.length - 1))].key)
									.style("display", disName);
						}

						function removePopovers() {
							$('.popover').each(function() {
								$(this).remove();
							});
						}
						function showPopover(d) {
							$(this).popover(
									{
										title : '',
										placement : 'auto top',
										container : 'body',
										trigger : 'manual',
										html : true,
										content : function() {

											return "<span style='color:#00a99d'>" + "Time Period : "
													+ "</span>" + "<span style='color:red'>"
													+ d.date + "</span>" + "<br/>"
													+ "<span style='color:#00a99d'>"
													+ "Data Value : " + "</span>"
													+ "<span style='color:red'>" + d.value
													+ "</span>";

										}
									});
							$(this).popover('show');
							//$('.popover.fade.top.in').css('top', parseFloat($('.popover.fade.top.in').css('top').slice(0, -2))+$(window).scrollTop());
						}
					};
					draw(data);
					
		});
	}
	return {
		restrict : "E",
		scope : {
			dataprovider : "="
		},
		link : link
	};

});
$(".download-container").hover(function(){
	if(parseFloat($(this).css("right").slice(0, -2)) < -50)
	$(this).animate({right: 0});
}, function(){
	var self = this;
	if(parseFloat($(this).css("right").slice(0, -2)) > -50)
		$(this).animate({right: '-110px'});
//	else
//		setTimeout(function(){
//			$(self).animate({right: '-100px'});
//		}, 2000);
});




