/*********************samiksha map**************************/

myAppConstructor
		.directive(
				"samikshaMapPopout",
				function($window) {
					function link(scope, el) {
						var el = el[0];
						var DELAY = 300, clicks = 0, timer = null;

						var url = location.pathname;
						$(".backbtn").click(function() {
											console.log("map area type"+ scope.selectedMapAreaType);
											scope.selectMapAreaType(scope.selectedMapAreaType);
											scope.shoulddisappear = "true";
						});

						function onmousemove(d) {
							d3
									.select(".map_popover")
									.style("display", "block")
									.style("left", (d3.event.pageX) - 80 + "px")// TODO:
									// make it dynamic so that position would be according to the text length
									.style("top", (d3.event.pageY - 70) + "px")
									.style("opacity", "1");

						}

						function onover(d) {
							d3.selectAll(".activehover").classed("activehover",
									false);
							var rank,datavalue;
							if(d.properties.utdata != null)
								d3.select(".map_popover_content").html(
									"<strong>Area Name:</strong> <span style='color:white'>"
											+ d.properties.NAME1_ + "</span>" + "<br/>"
									+"<strong>Value:</strong> <span style='color:white'>"
									+ d.properties.utdataPopout.value + "</span>");
							else
								d3.select(".map_popover_content").html(
										"<strong>Area Name:</strong> <span style='color:white'>"
												+ d.properties.NAME1_ + "</span>" + "<br/>"
										+"<strong>Value:</strong> <span style='color:white'>"
										+ "NA" + "</span>");

							/*if (d.properties.utdata && d.properties.utdata.rank) {
								rank = d.properties.utdata.rank;
								datavalue=d.properties.utdata.value;
							} else if (d.properties.computeutdata && d.properties.computeutdata.rank) {
								rank = d.properties.computeutdata.rank;
								datavalue= parseFloat( d.properties.computeutdata.value);
							}else{
								rank = "Not Available";
								datavalue = "Not Available";
							}
							
						
							d3.select(".map_popover_close").html(
									"<strong>Rank:</strong> <span style='color:white'>"
									+ rank + "</span>"
									+ "<br><strong>Value:</strong> <span style='color:white'>"
											+ datavalue + "</span>");*/
							
							d3.select(this.parentNode.appendChild(this))
									.classed("activehover", true);
						}
						
						function drilldown(d) {
                            
							if (d.properties && d.properties.NAME1_ == "Assam") {
								scope.shoulddisappear = false;

								if (d.properties.NAME1_
										&& scope.shouldDrilldown) {
									scope.shoulddisappear = false;

									scope.closeViz();
									d3.select(".map_popover").style("display",
											"none");
									scope.selectedGranularity = new ValueObject(
											d.properties.ID_,
											d.properties.NAME1_);
									scope.selectedChildAreaLevel = scope.selectedChildAreaLevel + 1;
									var url = "resources/geomaps/"
											+ d.properties.NAME1_ + ".json";
									scope.mapSetup(url, function() {
										scope.getutdata();
									});

									$(".backbtn").toggleClass("hidden");

								}
							}
						}
						scope.isIndiaMap=true;
						scope.mapSetupPopout = function(url, callbackMethod) {
							d3.select(el).selectAll("*").remove();
							var w = scope.getWindowDimensions();
							var feature = "";
							var width = $(window).width()-30-$(window).width()/20, height = 500, centered;
							var projection = d3.geo.mercator().scale(1);
							var path = d3.geo.path().projection(projection);

							var svg = d3.select(el).append("svg").attr("id","mapsvg").attr("width", width).attr("height", height);
							svg.append("rect").attr({style : "fill:none;pointer-events:all;"})
							.attr("width", width)
							.attr("height", height)
//							.on("click", clicked)
							.on("mouseover",function() {
										d3.select(".map_popover").style("display", "none");
										d3.selectAll(".activehover").classed("activehover", false);
							});

							var g = svg.append("g").attr("id", "mapg");

							d3.json(url,function(error, us) {
												feature = topojson.feature(us,us.objects.Uttar_Pradesh_r1);

												var b = path.bounds(feature), s = 0.95 / Math.max((b[1][0] - b[0][0])/ width,(b[1][1] - b[0][1])/ height);
												projection.scale(s);
												b = d3.geo.bounds(feature);
												projection.center([(b[1][0] + b[0][0]) / 2,(b[1][1] + b[0][1]) / 2 ]);
												projection.translate([ width / 2,height / 2 ]);

												g.append("g").attr("id", "districts")
														.selectAll("path")
														.data(topojson.feature(us,us.objects.Uttar_Pradesh_r1).features)
														.enter()
														.append("path")
														.attr("d", path)
														.on("mouseover", onover);

												g.on("mousemove", onmousemove);

												// --------------------------------------------------------------
												// Labeling for bihar state
												// only//////
												var result = url.match(/Assam/i);
												
												/*if (result) {
													scope.isIndiaMap=false;
													g.selectAll("text")
													.data(topojson.feature(us,us.objects.layer1).features)
													.enter().append("svg:text")
													.attr("x",function(d) {
														if(d.properties.NAME1_ == "Tinsukia"){
															return (path.centroid(d)[0])/1.01;
														}else if(d.properties.NAME1_ == "Golaghat"){
															return (path.centroid(d)[0])/0.98;
														}else if(d.properties.NAME1_ == "KarbiAnglong"){
															return (path.centroid(d)[0])/0.96;
														}else if(d.properties.NAME1_ == "Kamrup"){
															return (path.centroid(d)[0])/1.03;
														}else if(d.properties.NAME1_ == "Nagaon"){
															return (path.centroid(d)[0])/1.01;
														}else if(d.properties.NAME1_ == "Dhubri"){
															return (path.centroid(d)[0])/1.01;
														}else if(d.properties.NAME1_ == "Sivasagar"){
															return (path.centroid(d)[0])/1.01;
														}  else {
															return path.centroid(d)[0];
														}
													})
													.attr("y",function(d) {
														if(d.properties.NAME1_ == "Dhubri"){
															return (path.centroid(d)[1]) / 0.99;// block
														}else if (d.properties.NAME1_ == "Sheikhpura") {
															return (path.centroid(d)[1]) / 0.99;// block
														}else if (d.properties.NAME1_ == "Kamrup") {
															return (path.centroid(d)[1]) / 0.95;// block
														}else if (d.properties.NAME1_ == "Kamrupmetropolitan") {
															return (path.centroid(d)[1]) / 0.99;// block
														}else if (d.properties.NAME1_ == "Karimganj") {
															return (path.centroid(d)[1]) / 1.05;// block
														}else if (d.properties.NAME1_ == "Darrang") {
															return (path.centroid(d)[1]) / 1.03;// block
														}else if (d.properties.NAME1_ == "Nagaon") {
															return (path.centroid(d)[1]) / 0.96;// block
														}else if (d.properties.NAME1_ == "Barpeta") {
															return (path.centroid(d)[1]) / 0.96;// block
														}else if (d.properties.NAME1_ == "Nalbari") {
															return (path.centroid(d)[1]) / 1.03;// block
														}else if (d.properties.NAME1_ == "DimaHasao") {
															return (path.centroid(d)[1]) / 1.03;// block
														}else if(d.properties.NAME1_ == "Goalpara"){
															return (path.centroid(d)[1]) / 1.03;// block
														}
														else {
															return path.centroid(d)[1];
														}
													})
													.attr('font-size','9pt')
													.on("mouseover", onover)
													.on("mousemove", onmousemove);
												}*/

												if (callbackMethod)
													callbackMethod();
											});

							function clickHandler(d) {
								clicks++; // count clicks

								if (clicks === 1) {

									timer = setTimeout(function() {

										clicked(d); // perform
										// single-click
										// action
										clicks = 0; // after action performed,
										// reset counter
									}, DELAY);

								} else {
									clearTimeout(timer); // prevent
									// single-click
									// action

									drilldown(d); // perform
									// double-click
									// action
									clicks = 0; // after action performed, reset
									// counter
								}
							}

							function clicked(d) {

								var x, y, k;
								if (d && centered !== d) {
									var centroid = path.centroid(d);
									x = centroid[0];
									y = centroid[1];
									k = 2.5;
									centered = d;
								} else {
									x = (width / 2) - 36;// this is to fix
									// the movement of
									// map when clicked.
									y = height / 2;
									k = 1;
									centered = null;
								}

								g.selectAll("path").classed("active",
										centered && function(d) {
											return d === centered;
										});

								g.transition().duration(750).attr(
										"transform",
										"translate(" + width / 2 + "," + height
												/ 2 + ")scale(" + k
												+ ")translate("
												+ (-x - width * 3 / 100) + ","
												+ -y + ")").style(
										"stroke-width", 1.5 / k + "px");

								scope.disablePdf = (d == null) ? false
										: scope.selectedArea == null ? true
												: d == scope.selectedArea ? false
														: true;
							}

						};

						scope.$watch("utdataPopout",function() {
							console.log("utData changed");
							
							var insertLinebreaks = function (d) {
								if(d.properties.utdata){
									 var el = d3.select(this);
									    if(scope.isIndiaMap){
									    }else{
									    	el.selectAll("*").remove();
									    	el.append('tspan').style("text-anchor","middle").text(d.properties.SHORTNAME1_);
										    el.append('tspan').style("text-anchor","middle").text(parseFloat(d.properties.utdata.value)).attr('x', el.attr("x")).attr('dy', '15');
									    }
								}
							};
							d3.select("#mapsvg").selectAll("text").selectAll("*").remove();				
							d3.select("#mapsvg").selectAll("path")
							.attr("class",function(d) {
								if (!(scope.utdataPopout && scope.utdataPopout.dataCollection)) {
									d.properties.utdataPopout = null;
									return;
								}
								for (var i = 0; i < scope.utdataPopout.dataCollection.length; i++) {
									if (d.properties && d.properties.ID_ == scope.utdataPopout.dataCollection[i].areaCode) {
										d.properties.utdataPopout = scope.utdataPopout.dataCollection[i];
										return scope.utdataPopout.dataCollection[i].cssClass;
									} else {
										if (d.properties) {
											d.properties.utdataPopout = null;
										}
									}
								}
							});
							d3.select("#mapsvg").selectAll("text").each(insertLinebreaks);
							
						}, true);	
						
						scope.$watch("computeutdata",function() {
							
							var insertLinebreaks = function (d) {
								if(d.properties.computeutdata){
									 var el = d3.select(this);
									    if(scope.isIndiaMap){
									    	if(d.properties.NAME1_ == "Assam"){
									    		el.selectAll("*").remove();
									    		el.append('tspan').style("text-anchor","middle").text(d.properties.NAME1_);
											    el.append('tspan').style("text-anchor","middle").text(parseFloat(d.properties.computeutdata.value)).attr('x', el.attr("x")).attr('dy', '15');
									    	}
									    }else{
									    	el.selectAll("*").remove();
									    	el.append('tspan').style("text-anchor","middle").text(d.properties.SHORTNAME1_);
										    el.append('tspan').style("text-anchor","middle").text(parseFloat(d.properties.computeutdata.value)).attr('x', el.attr("x")).attr('dy', '15');
									    }
								}
							};
							d3.select("#mapsvg").selectAll("text").selectAll("*").remove();				
							d3.select("#mapsvg").selectAll("path")
							.attr("class",function(d) {
								if (!(scope.computeutdata && scope.computeutdata.dataCollection)) {
									d.properties.computeutdata = null;
									return;
								}
								for (var i = 0; i < scope.computeutdata.dataCollection.length; i++) {
									if (d.properties && d.properties.ID_ == scope.computeutdata.dataCollection[i].areaCode) {
										d.properties.computeutdata = scope.computeutdata.dataCollection[i];
										return scope.computeutdata.dataCollection[i].cssClass;
									} else {
										if (d.properties) {
											d.properties.computeutdata = null;
										}
									}
								}
							});
							d3.select("#mapsvg").selectAll("text").each(insertLinebreaks);
							
						}, true);
						
						
						/*scope.$watch(scope.getWindowDimensions, function(
								newValue, oldValue) {
							scope.svgHeight = (newValue.h - 190);
							scope.svgWidth = (newValue.w);
							scope.style = function() {
								return {
									'height' : (newValue.h - 190) + 'px',
									'width' : (newValue.w) + 'px'
								};
							};
							w = scope.getWindowDimensions();
							width = w.w, height = w.h;
							d3.select("#mapsvg").attr({
								width : width,
								height : height - 190
							});
							d3.select("#mapsvg").selectAll("rect").attr({
								width : width,
								height : height - 190
							});

						}, true);*/
					}
					return {
						link : link,
						restrict : "E"
					};
				});





/**************samiksha line chart*******************/

myAppConstructor
.directive(
		"samikshaLinePopout",
		function($window) {
			console.log("hello lines");
			function link(scope, el) {

				var el = el[0];
				var clicks = 0;

				// Render graph based on 'data'
				scope.$watch("dataprovider", function(data) {
					// remove all
//					console.log(data);
					function draw(data) {
						var w = $(window);
						var wnw =(w.width()< 413)? w.width(): w.width() * 50 / 100;
						var wnh = (w.width()> 940)? 950: 368;
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
							width = (wnw/1.5)- margin.left - margin.right, 
							height = (wnh/2.8)- margin.top - margin.bottom;

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
						});

						// Adds the svg canvas
						var svg = d3.select(el).append("svg").attr("id",
								"trendsvg").attr("width",
								width + margin.left + margin.right).attr(
								"height",
								height + margin.top + margin.bottom)
								.append("g").attr(
										"transform",
										"translate(" + margin.left + ","
												+ margin.top + ")").style(
										"fill", "#000");

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
										"Time Period").attr("x", width - 45)// 
										.attr("y", height-80).style("fill",
										"#000");

						svg.selectAll(".tick text").style("text-anchor",
								"end").attr("dx", "-.8em").attr("dy",
								".15em").attr("transform", function(d) {
							return "rotate(-25)";
						});

						

						// Add the Y Axis
						var ya = svg.append("g").attr("class", "y axis").call(yAxis);
						ya.selectAll("text");
						ya.append("text").attr("transform",
								"rotate(-90)").attr("y", 7).attr(
								"dy", ".71em").style("text-anchor",
								"end").text(function(d){return "Number";}).style("fill",
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
									.attr("id", "tag" + dataNest[index].key)
									.attr("d",function(d) {
												return lineFunction(dataNest[index].values);
											}).style("stroke", function(d) {
										return color(dataNest[index].key);
									}).style("stroke-width", "2px").style(
											"fill", "none").style("incorporate", "ordinal");

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
							$('.popover.fade.top.in').css('top', parseFloat($('.popover.fade.top.in').css('top').slice(0, -2))+$(window).scrollTop());
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




