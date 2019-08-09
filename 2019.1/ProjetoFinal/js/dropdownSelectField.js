
let dropMeso = $('#dropdownMeso');

dropMeso.empty();
dropMeso.append('<option selected="true" disabled>Mesorregião</option>');
dropMeso.prop('selectedIndex', 0);

const jsonData = '../jsons/MUNICIPIOS_JSON_TESTES.json';

$.getJSON(jsonData, function (data) {
  $.each(data.MESORREGIOES, function (key, value) {
    dropMeso.append($('<option></option>').attr('value', value.ID).text(value.NOME_MESORREGIAO));
  })
});

let dropMicro = $('#dropdownMicro');

dropMicro.empty();
dropMicro.append('<option selected="true" disabled>Microrregião</option>');
dropMicro.prop('selectedIndex', 0);

$.getJSON(jsonData, function (data) {
  $.each(data.MICRORREGIOES, function (key, value) {
    dropMicro.append($('<option></option>').attr('value', value.ID).text(value.NOME_MICRORREGIAO));
  })
});
