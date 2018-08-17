var margin = {top: 20, right: 160, bottom: 35, left: 30};

var width = 600 - margin.left - margin.right,
    height = 300 - margin.top - margin.bottom;

var svg = d3.select(".barChart")
  .append("svg")
  .attr("width", width + margin.left + margin.right)
  .attr("height", height + margin.top + margin.bottom)
  .append("g")
  .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


/* Data in strings like it would be if imported from a csv */

var data = [
  { category: "General", boys: "02", girls: "05" },
  { category: "OBC", boys: "06", girls: "04" },
  { category: "SC", boys: "05", girls: "05" },
  { category: "ST", boys: "01", girls: "0" },
  { category: "EBC", boys: "02", girls: "06" },
  { category: "Others", boys: "03", girls: "07" },
];

// Transpose the data into layers
var dataset = d3.layout.stack()(["girls", "boys"].map(function(childType) {
  return data.map(function(d) {
    return {x: d.category, y: +d[childType]};
  });
}));


// Set x, y and colors
var x = d3.scale.ordinal()
  .domain(dataset[0].map(function(d) { return d.x; }))
  .rangeRoundBands([10, width-10], 0.02);

var y = d3.scale.linear()
  .domain([0, d3.max(dataset, function(d) {  return d3.max(d, function(d) { return d.y0 + d.y; });  })])
  .range([height, 0]);

var colors = ["#ffc000", "#ff9402"];


// Define and draw axes
var yAxis = d3.svg.axis()
  .scale(y)
  .orient("left")
  .ticks(5)
//  .tickSize(-width, 0, 0)
//  .tickFormat( function(d) { return d } );

var xAxis = d3.svg.axis()
  .scale(x)
  .orient("bottom");
//  .tickFormat(function(d) { return d });

svg.append("g")
  .attr("class", "y axis")
  .attr("transform", "translate(10,0)")
  .call(yAxis);

svg.append("g")
  .attr("class", "x axis")
  .attr("transform", "translate(0," + height + ")")
  .call(xAxis);


// Create groups for each series, rects for each segment 
var groups = svg.selectAll("g.cost")
  .data(dataset)
  .enter().append("g")
  .attr("class", "cost")
  .style("fill", function(d, i) { return colors[i]; });

var rect = groups.selectAll("rect")
  .data(function(d) { return d; })
  .enter()
  .append("rect")
  .attr("x", function(d) { return x(d.x) + 14; })
  .attr("y", function(d) { return y(d.y0 + d.y); })
  .attr("height", function(d) { return y(d.y0) - y(d.y0 + d.y); })
//  .attr("width", x.rangeBand())
  .attr("width", 30);
//  .on("mouseover", function() { tooltip.style("display", null); })
//  .on("mouseout", function() { tooltip.style("display", "none"); })
//  .on("mousemove", function(d) {
//    var xPosition = d3.mouse(this)[0] - 15;
//    var yPosition = d3.mouse(this)[1] - 25;
//    tooltip.attr("transform", "translate(" + xPosition + "," + yPosition + ")");
//    tooltip.select("text").text(d.y);
//  });


// Draw legend
var legend = svg.selectAll(".legend")
  .data(colors)
  .enter().append("g")
  .attr("class", "legend")
  .attr("transform", function(d, i) { return "translate(25," + i * 19 + ")"; });
 
legend.append("rect")
  .attr("x", width - 18)
  .attr("width", 18)
  .attr("height", 18)
  .style("fill", function(d, i) {return colors.slice().reverse()[i];});
 
legend.append("text")
  .attr("x", width + 5)
  .attr("y", 9)
  .attr("dy", ".35em")
  .style("text-anchor", "start")
  .text(function(d, i) { 
    switch (i) {
      case 0: return "Girls";
      case 1: return "Boys";
    }
  });


// Prep the tooltip bits, initial display is hidden
//var tooltip = svg.append("g")
//  .attr("class", "tooltip")
//  .style("display", "none");
//    
//tooltip.append("rect")
//  .attr("width", 30)
//  .attr("height", 20)
//  .attr("fill", "white")
//  .style("opacity", 0.5);
//
//tooltip.append("text")
//  .attr("x", 15)
//  .attr("dy", "1.2em")
//  .style("text-anchor", "middle")
//  .attr("font-size", "12px")
//  .attr("font-weight", "bold");
