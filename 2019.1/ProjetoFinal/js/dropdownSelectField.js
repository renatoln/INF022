let dropMeso = $('#dropdownMeso');

dropMeso.empty();
dropMeso.append('<option selected="true">Mesorregião</option>');
dropMeso.prop('selectedIndex', 0);

const jsonData = getUrlJsonEstadoGeral();

$.getJSON(jsonData, function (data) {
  $.each(data.MESORREGIOES, function (key, value) {
    dropMeso.append($('<option></option>').attr('value', value.ID).text(value.NOME_MESORREGIAO));
  })
});

let dropMicro = $('#dropdownMicro');

dropMicro.empty();
dropMicro.append('<option selected="true">Microrregião</option>');
dropMicro.prop('selectedIndex', 0);

$.getJSON(jsonData, function (data) {
  $.each(data.MICRORREGIOES, function (key, value) {
    dropMicro.append($('<option></option>').attr('value', value.ID).text(value.NOME_MICRORREGIAO));
  })
});
