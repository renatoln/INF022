
let dropMeso = $('#dropdownMeso');

dropMeso.empty();
dropMeso.append('<option selected="true" disabled>Mesorregião</option>');
dropMeso.prop('selectedIndex', 0);

const jsonData = '../jsons/MUNICIPIOS_JSON_TESTES.json';

$.getJSON(jsonData, function (data) {
  $.each(data, function (key, value) {
    dropMeso.append($('<option></option>').attr('value', value.MESORREGIOES.ID).text(value.MESORREGIOES.NOME_MESORREGIAO));
  })
});

let dropMicro = $('#dropdownMicro');

dropMicro.empty();
dropMicro.append('<option selected="true" disabled>Microrregião</option>');
dropMicro.prop('selectedIndex', 0);

const jsonData2 = '../jsons/MUNICIPIOS_JSON_TESTES.json';

$.getJSON(jsonData2, function (data) {
  $.each(data, function (key, value) {
    dropMicro.append($('<option></option>').attr('value', value.MICRORREGIOES.ID).text(value.MICRORREGIOES.NOME_MICRORREGIAO));
  })
});
