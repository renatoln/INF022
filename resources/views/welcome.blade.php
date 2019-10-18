<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>My Place Stats</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
    <!-- Chartist -->
    <link rel="stylesheet" href="plugins/chartist/css/chartist.min.css">
    <link rel="stylesheet" href="plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">
    <!-- Custom Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/style2.css" rel="stylesheet">

</head>

<body>

<div>
    <div id="main-wrapper">


        <div class="content-body">

            <div class="container-fluid mt-3">
                <!-- Busca -->
                <div class="row">
                    <div class="col-12">
                        <div class="card" style="height: 19rem;">
                            <div class="container" style="padding: 16px;">
                                <form role="form">
                                    <!-- Field Categoria -->
                                    <div class="form-group input-group mb-3">
                                        <label for="categoria"><strong>Categoria</strong></label>
                                        <div class="col-sm-6">
                                            <input type="input" name="categoria" class="form-control mb-4"
                                                id="txt-search-categoria" placeholder="Categoria">
                                        </div>
                                    </div>
                                    <!-- Fields Valores -->
                                    <div class="form-group input-group mb-3">
                                        <label for="valores"><strong
                                                style="text-align: center;">Valores</strong></label>
                                        <label for="valores"><strong
                                                style="text-align: center; margin-left: 1cm">De</strong></label>
                                        <div class="col-sm-2">
                                            <input type="input" name="valorMin" class="form-control mb-4"
                                                id="txt-search-valorMin" placeholder="Valor mínimo">
                                        </div>
                                        <label for="valores"><strong style="text-align: center;">Até</strong></label>
                                        <div class="col-sm-2">
                                            <input type="input" name="valorMax" class="form-control mb-4"
                                                id="txt-search-valorMax" placeholder="Valor maximo">
                                        </div>


                                    </div>

                                    <!-- Fields Select Meso e Micro -->
                                    <div class="form-group input-group mb-3">
                                        <label for="valores"><strong
                                                style="text-align: center;">Mesorregião</strong></label>
                                        <div class="col-sm-3">
                                            <select id="dropdownMeso" class="custom-select custom-select mb-3"
                                                name="DropMesorregião"></select>
                                        </div>

                                        <label for="valores"><strong
                                                style="text-align: center;">Microrregião</strong></label>
                                        <div class="col-sm-3">
                                            <select id="dropdownMicro" class="custom-select custom-select mb-3"
                                                name="DropMicrorregião"></select>
                                        </div>

                                        <div class="col-sm-2">
                                            <input id="search_button" class="btn btn-md btn-info " style="float: right;"
                                                type="button" value="Pesquisar" onclick="initSearch();" />
                                        </div>
                                        <div class="col-sm-2">
                                            <input id="clear_button" class="btn btn-md btn-info " style="float: right;"
                                                type="button" value="Limpar" onclick="clearSearch();" />
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="card" style="height: 19rem;">
                            <label for="labelPeriodos"><strong
                                    style="text-align: center;">Períodos:</strong></label>
                            <div class="col-sm-8 dinamicos">
                                <select id="dropdownPeriodos" class="custom-select custom-select mb-3"
                                    name="DropPeriodos" onchange=changeTime(event)></select>
                            </div>
                            <label for="labelEstrategia"><strong
                                    style="text-align: center;">Estratégia de coloração:</strong></label>
                            <div class="col-sm-8 dinamicos">
                                <select id="dropdownEstrategia" class="custom-select custom-select mb-3"
                                    name="DropEstrategia" onchange=changeColorOption(event)>
                                        <option value="1">Min-Max</option>
                                        <option value="2">Percentis</option>
                                        <option value="3">Log</option>
                                    </select>
                            </div>
                            <label for="valores"><strong
                                    style="text-align: center;">Atributos:</strong></label>
                            <div class="col-sm-8 dinamicos">
                                <select id="dropdownAtributos" class="custom-select custom-select mb-3"
                                    name="DropAtributos" onchange=checkAlert(event)></select>
                            </div>
                        </div>
                    </div>

                </div>

                <!-- Atributos dinâmicos -->

                <!-- Map -->
                <div class="row">
                    <div class="col-lg-8">
                        <div class="card card-widget">
                            <div class="card-head">
                                <ul class="nav nav-tabs" id="nav-tab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" data-toggle="tab" role="tab" href="#g1">Mapa</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="tab" role="tab" href="#g2">Sunburst</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="tab" role="tab" href="#g3">TreeMap</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="tab" role="tab" href="#g4">Zoomable Sunburst</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="tab" role="tab" href="#g5">Bubble Chart</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="tab" role="tab" href="#g6">Gauge</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="card-body">
                                <div class="tab-content">
                                    <div id="g1" class="tab-pane active in">
                                        <h3>Mapa da Bahia</h3>
                                        <svg id='mapa' class='img-responsive' viewBox="0 0 945 1030"></svg>
                                    </div>
                                    <div id="g2" class="tab-pane">
                                        <h3>Sunburst</h3>
                                        <div id="div_Sunburst" style="width: 510px; height: 510px;"></div>
                                    </div>
                                    <div id="g3" class="tab-pane">
                                        <h3>TreeMap</h3>
                                        <label for="tamanho"><strong
                                            style="text-align: left;">Tamanho</strong></label>
                                        <div class="col-sm-4">
                                            <select id="dropdownTamanho" class="custom-select custom-select mb-3"
                                                name="DropVolume" ></select>
                                        </div>
                                        <label for="cor"><strong
                                            style="text-align: right;">Cor</strong></label>
                                        <div class="col-sm-4">
                                            <select id="dropdownCor" class="custom-select custom-select mb-3"
                                                name="DropCor" ></select>
                                        </div>
                                        <div id="div_TreeMap" style="widows: 900px; height:500px;"></div>
                                    </div>
                                     <div id="g4" class="tab-pane">
                                        <h3>Zoomable Sunburst</h3>
                                         <select id="ddw_atributos_ZoomableSunburst" class="custom-select custom-select mb-3"
                                                 name="ddw_atributos_ZoomableSunburst"  onchange="atualizaZoomableSunburst('div_ZoomableSunburst')">
                                         </select>

                                        <div id="div_ZoomableSunburst" style="width: 510px; height: 510px;"></div>
                                    </div>
                                    <div id="g5" class="tab-pane">
                                        <h3>Bubble Chart</h3>
                                        <div id="div_BubbleChart" style="width: 510px; height: 510px;"></div>
                                    </div>
                                    <div id="g6" class="tab-pane">
                                        <h3>Gauge</h3>
                                        <div id="div_Gauge" style="width: 510px; height: 510px;"></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <section id="wrapper">
                            <div id="municipios">
                                <div id="popUp">
                                    <ul>
                                        <li id="atrCompare"><a>Line Chart</a></li>
                                        <li id="barChart"><a>Bar Chart</a></li>
                                        <li id="sunChart"><a>Sunburst</a></li>
                                        <li id="radarChart"><a>Radar</a></li>
                                    </ul>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Grafico -->
    <div class="row">
        <div class="container">
            <div class="card" style="float: right; ">
                <div class="col-lg-12">
                    <section>
                        <div id="myDiv"></div>
                    </section>
                    <section>
                        <div id="myDiv2"></div>
                    </section>
                    <section>
                        <div id="myDiv3"></div>
                    </section>
                    <section>
                        <div id="myDiv4"></div>
                    </section>
                </div>
            </div>
        </div>
    </div>


</div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <script src="plugins/common/common.min.js"></script>
    <script src="js/custom.min.js"></script>

    <!--D3 Data-Driven Documents-->
    <script src="js/d3.v5.min.js"></script>
    <!-- Chartjs -->
    <script type="text/javascript" src="js/charts/loader.js"></script>
    <script type="text/javascript" src="js/charts/jsapi.corechart.js"></script>
    <script type="text/javascript" src="js/charts/jsapi.treemap.js"></script>
    <script type="text/javascript" src="js/charts/jsapi.gauge.js"></script>
    <script src="plugins/chart.js/Chart.bundle.min.js"></script>
    <!-- Pignose Calender -->
    <script src="plugins/moment/moment.min.js"></script>
    <script src="plugins/pg-calendar/js/pignose.calendar.min.js"></script>
    <!-- ChartistJS -->
    <script src="plugins/chart.js/Chart.bundle.min.js"></script>
    <script src="plugins/chartist/js/chartist.min.js"></script>
    <script src="plugins/chartist-plugin-tooltips/js/chartist-plugin-tooltip.min.js"></script>


    <!-- Imports do Projeto Snap.js -->
    <script src="js/jquery.min.js"></script>
    <script src="js/snap.svg-min.js"></script>
    <script src="js/snaptoolkit.js"></script>
    <script src="js/mapa.js"></script>
    <script src="js/jquery.modal.min.js"></script>


    <!-- Imports Visões -->
    <script type="text/javascript" src="js/views/radar.js"></script>
    <script type="text/javascript" src="js/views/sunburst.js"></script>
    <script type="text/javascript" src="js/views/gauge.js"></script>
    <script type="text/javascript" src="js/views/treemap.js"></script>
    <script type="text/javascript" src="js/views/zoomable-sunburst.js"></script>
    <script type="text/javascript" src="js/views/bubblechart.js"></script>


    <!-- Imports da coloracao e do grafico de evolucao -->
    <script type="text/javascript" src="js/plotly-latest.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <script type="text/javascript" src="js/p5.min.js"></script>
    <script type="text/javascript" src="js/sketch.js"></script>

    <!-- Search -->
    <script type="text/javascript" src="js/search.js"></script>
    <script type="text/javascript" src="js/dropdownSelectField.js"></script>
</body>

</html>
