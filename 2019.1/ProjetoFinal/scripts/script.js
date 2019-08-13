//console.log("hello");

//let cidades = document.getElementsByClassName("mun");
/*let hoverColor = "#2574A9";
let normalColor = "#EEDDB3";
let popUp = document.getElementById("popUp");

let mouseX;
let mouseY;

let wrapper = document.getElementById("wrapper");

let grafico = document.getElementById("grafico");

window.onload = function(){
	ponto();
}

wrapper.addEventListener("click",function(){
		popUp.style.visibility = "hidden";
});

for(let cidade of cidades){
	cidade.addEventListener("mouseover",function(){
		cidade.style.fill = hoverColor;
	});

	cidade.addEventListener("mouseout",function(){
		cidade.style.fill = normalColor;
	});

	cidade.addEventListener("contextmenu",function(ev){
		ev.preventDefault();
		mouseX = ev.clientX;
		mouseY = ev.clientY;

		popUp.style.top = `${mouseY}px`;
		popUp.style.left = `${mouseX}px`;

		popUp.style.visibility = "visible";
		return false;
	},false);
}*/

/****************************************************************************************************************/
/****************************************************************************************************************/
/*****************************************Menu*******************************************************************/
/****************************************************************************************************************/

function ponto(){
	var x1 = [];
var x2 = [];
var y1 = [];
var y2 = [];
for (var i = 1; i < 500; i++) 
{
  k = Math.random();
  x1.push(k*5);
  x2.push(k*10);
  y1.push(k);
  y2.push(k*2);
}
var trace1 = {
  x: x1,
  y: y1,
  name: 'control',
  autobinx: false, 
  histnorm: "count", 
  marker: {
    color: "rgba(255, 100, 102, 0.7)", 
     line: {
      color:  "rgba(255, 100, 102, 1)", 
      width: 1
    }
  },  
  opacity: 0.5, 
  type: "histogram", 
  xbins: {
    end: 2.8, 
    size: 0.06, 
    start: .5
  }
};
var trace2 = {
  x: x2,
  y: y2, 
  autobinx: false, 
  marker: {
          color: "rgba(100, 200, 102, 0.7)",
           line: {
            color:  "rgba(100, 200, 102, 1)", 
            width: 1
    } 
       }, 
  name: "experimental", 
  opacity: 0.75, 
  type: "histogram", 
  xbins: { 
    end: 4, 
    size: 0.06, 
    start: -3.2

  }
};
var data = [trace1, trace2];
var layout = {
  bargap: 0.05, 
  bargroupgap: 0.2, 
  barmode: "overlay", 
  title: "Sampled Results", 
  xaxis: {title: "Value"}, 
  yaxis: {title: "Count"}
};
Plotly.newPlot('myDiv', data, layout);
}


/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/



function doughnut(){
	Plotly.d3.csv("https://raw.githubusercontent.com/plotly/datasets/master/finance-charts-apple.csv", function(err, rows){

  function unpack(rows, key) {
  return rows.map(function(row) { return row[key]; });
}


var trace1 = {
  type: "scatter",
  mode: "lines",
  name: 'AAPL High',
  x: unpack(rows, 'Date'),
  y: unpack(rows, 'AAPL.High'),
  line: {color: '#cf2317'}
}

var trace2 = {
  type: "scatter",
  mode: "lines",
  name: 'AAPL Low',
  x: unpack(rows, 'Date'),
  y: unpack(rows, 'AAPL.Low'),
  line: {color: '#7f7f7f'}
}

var data = [trace1,trace2];

var layout = {
  title: 'Time Series with Rangeslider',
  xaxis: {
    autorange: true,
    range: ['2015-02-17', '2017-02-16'],
    rangeselector: {buttons: [
        {
          count: 1,
          label: '1m',
          step: 'month',
          stepmode: 'backward'
        },
        {
          count: 6,
          label: '6m',
          step: 'month',
          stepmode: 'backward'
        },
        {step: 'all'}
      ]},
    rangeslider: {range: ['2015-02-17', '2017-02-16']},
    type: 'date'
  },
  yaxis: {
    autorange: true,
    range: [86.8700008333, 138.870004167],
    type: 'linear'
  }
};

Plotly.newPlot('myDiv', data, layout);
})
}

/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/

function combo(){
	Plotly.d3.csv("https://raw.githubusercontent.com/plotly/datasets/master/finance-charts-apple.csv", function(err, rows){

  function unpack(rows, key) {
  return rows.map(function(row) { return row[key]; });
}


var trace1 = {
  type: "scatter",
  mode: "lines",
  name: 'AAPL High',
  x: unpack(rows, 'Date'),
  y: unpack(rows, 'AAPL.High'),
  line: {color: '#17BECF'}
}

var trace2 = {
  type: "scatter",
  mode: "lines",
  name: 'AAPL Low',
  x: unpack(rows, 'Date'),
  y: unpack(rows, 'AAPL.Low'),
  line: {color: '#7F7F7F'}
}

var data = [trace1,trace2];

var layout = {
  title: 'Basic Time Series',
};

Plotly.newPlot('myDiv', data, layout);
})
}

/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/

function pie(){
	var data = [{
  type: "sunburst",
  ids: [
    "North America", "Europe", "Australia", "North America - Football", "Soccer",
    "North America - Rugby", "Europe - Football", "Rugby",
    "Europe - American Football","Australia - Football", "Association",
    "Australian Rules", "Autstralia - American Football", "Australia - Rugby",
    "Rugby League", "Rugby Union"
  ],
  labels: [
    "North<br>America", "Europe", "Australia", "Football", "Soccer", "Rugby",
    "Football", "Rugby", "American<br>Football", "Football", "Association",
    "Australian<br>Rules", "American<br>Football", "Rugby", "Rugby<br>League",
    "Rugby<br>Union"
  ],
  parents: [
    "", "", "", "North America", "North America", "North America", "Europe",
    "Europe", "Europe","Australia", "Australia - Football", "Australia - Football",
    "Australia - Football", "Australia - Football", "Australia - Rugby",
    "Australia - Rugby"
  ],
  outsidetextfont: {size: 20, color: "#377eb8"},
  // leaf: {opacity: 0.4},
  marker: {line: {width: 2}},
}];

var layout = {
  margin: {l: 0, r: 0, b: 0, t:0},
  sunburstcolorway:["#636efa","#ef553b","#00cc96"],
};


Plotly.newPlot('myDiv', data, layout);
}


/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/
/****************************************************************************************************************/


function plastic(){
	var trace1 = {
  x: [1995, 1996, 1997, 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012],
  y: [219, 146, 112, 127, 124, 180, 236, 207, 236, 263, 350, 430, 474, 526, 488, 537, 500, 439],
  name: 'Rest of world',
  marker: {color: 'rgb(55, 83, 109)'},
  type: 'bar'
};

var trace2 = {
  x: [1995, 1996, 1997, 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012],
  y: [16, 13, 10, 11, 28, 37, 43, 55, 56, 88, 105, 156, 270, 299, 340, 403, 549, 499],
  name: 'China',
  marker: {color: 'rgb(26, 118, 255)'},
  type: 'bar'
};

var data = [trace1, trace2];

var layout = {
  title: 'US Export of Plastic Scrap',
  xaxis: {tickfont: {
      size: 14,
      color: 'rgb(107, 107, 107)'
    }},
  yaxis: {
    title: 'USD (millions)',
    titlefont: {
      size: 16,
      color: 'rgb(107, 107, 107)'
    },
    tickfont: {
      size: 14,
      color: 'rgb(107, 107, 107)'
    }
  },
  legend: {
    x: 0,
    y: 1.0,
    bgcolor: 'rgba(255, 255, 255, 0)',
    bordercolor: 'rgba(255, 255, 255, 0)'
  },
  barmode: 'group',
  bargap: 0.15,
  bargroupgap: 0.1
};

Plotly.newPlot('myDiv', data, layout);
}