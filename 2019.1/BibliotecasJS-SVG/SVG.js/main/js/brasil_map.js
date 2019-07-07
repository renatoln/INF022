/* eslint-disable no-undef */

$(document).ready(function() {

    var image = SVG('svgimage');
    $.get('../svg/Brasil.svg', function(contents) {
      var $tmp = $('svg', contents);
      image.svg($tmp.html());
    }, 'xml');
  
    /* Definir funções de tooltip
    $('#svgimage').hover(
      function() {
        image.select('rect').fill('blue');
      },
      function() {
        image.select('rect').fill('yellow');
      }
    ); */
  
  });