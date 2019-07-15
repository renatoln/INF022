var width = 600;
var height = 600;

let svgMap = d3.select("svg")
    .attr('width', width)
    .attr('height', height);
console.log('0');
svgMap.append('rect')
    .attr('class', 'background')
    .attr('width', width)
    .attr('height', height)
    .on("click", clicked);

console.log('1');

var g = svgMap.append('g');
console.log('2');
var d3json = d3.json('http://servicodados.ibge.gov.br/api/v1/localidades/estados/29/municipios', function(err, data) {
    console.log(d3json);
    if (err) throw err;
    console.log('2.2');
    g.append('g')
        .attr('id', 'municipio')
        .selectAll('path')
        .data(topojson.feature(id, id.objects.municipio).feature)
        .enter().append('path')
        .attr('d', path)
        .on('click', clicked);
    console.log('2.3');
    g.append('path')
        .datum(topojson.mesh(municipio, municipio.objects.municipio, function(a, b) { return a != b; }))
        .attr('id', 'mun-border')
        .attr('d', path);
    console.log('2.3');
});
console.log(d3json);

function clicked(d) {
    var x, y, k;

    if (d && centered !== d) {
        var centroid = path.centroid(d);
        x = centroid[0];
        y = centroid[1];
        k = 4;
        centered = d;
    } else {
        x = width / 2;
        y = height / 2;
        k = 1;
        centered = null;
    }

    g.selectAll("path")
        .classed("active", centered && function(d) { return d === centered; });

    g.transition()
        .duration(750)
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")scale(" + k + ")translate(" + -x + "," + -y + ")")
        .style("stroke-width", 1.5 / k + "px");

}
console.log('4');


/*svgMap.addEventListener(
    d3.select('path')
    .style('fill', "red")
    .on('mouseclicked')
);*/
//let vet = document.getElementsByTagName('path');
//console.log(vet);
//console.log(vet[0].href.animVal);

/*for (let i = 0; i < vet.length; i++) {
    vet[i].addEventListener("click", function() {
        if (vet[i].href.animVal == "#bahia") {
            window.location.href = "teste_Svg.html";
        }
    })
}
svgMap.draw("svg", tooltipHtml);*/