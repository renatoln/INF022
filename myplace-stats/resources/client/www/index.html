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
    <link rel="stylesheet" href="./plugins/chartist/css/chartist.min.css">
    <link rel="stylesheet" href="./plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">
    <!-- Custom Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/style2.css" rel="stylesheet">
    <link href="css/tab.css" rel="tab">

</head>

<body>


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

                                    <div class="form-group input-group mb-3">
                                        <label id="labelPeriodos" for="valores"><strong
                                                style="text-align: center;">Período: 6</strong></label>
                                        <div class="col-sm-2" style="margin-right: 50px;">
                                            <input id="myRange" type="range" min="2019.01" max="2019.06" step="0.01" />
                                        </div>
                                        <label for="valores"><strong
                                                style="text-align: center;">Atributos</strong></label>
                                        <div class="col-sm-3">
                                            <select id="dropdownAtributos" class="custom-select custom-select mb-3"
                                                name="DropAtributos" onchange=checkAlert(event)></select>
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
                                <div id="filter-records"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Map -->
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <section id="wrapper">
                                <div id="municipios">
                                    <svg id='mapa' class='img-responsive' viewBox="0 0 945 1030"></svg>
                                    <div id="popUp">
                                        <ul>
                                            <li id="lineChart"><a>Line Chart</a></li>
                                            <li id="barChart"><a>Bar Chart</a></li>
                                            <li id="sunChart"><a>Sunburst</a></li>
                                            <li id="atrCompare"><a>Comparacao de Atributos</a></li>
                                            <li><a>Grafico Torta</a></li>
                                            <li><a>Grafico Multilinha</a></li>
                                        </ul>
                                    </div>
                                </div>

                            </section>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="card card-widget">
                            <div class="card-body">
                                <ul class="tab">
                                    <div class="tablinks">
                                        <button class="btn btn-md btn-info"
                                            onclick="openChart(event, 'sunChartTab')">Sunburst</button>
                                    </div>
                                    <div id="sunChartTab" class="tabcontent">
                                        <div class="row">
                                            <div class="container">
                                                <div class="card" style="float: right; ">
                                                    <div class="col-lg-12">
                                                        <section>
                                                            <div id="sunburstChartTab"></div>
                                                        </section>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </ul>
                            </div>
                        </div>
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
                </div>
            </div>
        </div>
    </div>


    </div>


    <script type="text/javascript">
        
    </script>

    <script src="plugins/common/common.min.js"></script>
    <script src="js/custom.min.js"></script>

    <!-- Pignose Calender -->
    <script src="plugins/moment/moment.min.js"></script>
    <script src="plugins/pg-calendar/js/pignose.calendar.min.js"></script>
    <!-- ChartistJS -->
    <script src="plugins/chartist/js/chartist.min.js"></script>
    <script src="plugins/chartist-plugin-tooltips/js/chartist-plugin-tooltip.min.js"></script>

    <!-- Imports do Projeto Snap.js -->
    <script src="js/jquery.min.js"></script>
    <script src="js/snap.svg-min.js"></script>
    <script src="js/snaptoolkit.js"></script>
    <script src="js/mapa.js"></script>
    <script src="js/jquery.modal.min.js"></script>

    <!-- Imports da coloracao e do grafico de evolucao -->
    <script type="text/javascript" src="js/plotly-latest.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <script type="text/javascript" src="js/p5.min.js"></script>
    <script type="text/javascript" src="js/sketch.js"></script>

    <!-- Search -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/search.js"></script>
    <script type="text/javascript" src="js/dropdownSelectField.js"></script>

    <!-- Tab -->
    <script type="text/javascript" src="js/tab.js"></script>

    <!--D3 Data-Driven Documents-->
    <script src="js/d3.v5.min.js"></script>

</body>

</html>
