<?php

return array(

    /*
    |--------------------------------------------------------------------------
    | Custom Vespa Client Configuration
    |--------------------------------------------------------------------------
    |
    | This array will be passed to the Vespa client.
    |
    */

    'host' => env('VESPA_HOST', 'http://localhost:8080'),

    'model_columns' => [
        'status' => 'vespa_status',
        'date' => 'vespa_data_ultima_indexacao'
    ],

    'namespace' => [
        'myplace' => [
            'document' => [
                [
                    'type' => 'place',
                    'class' =>  \Vespa\Models\Place::class,
                    'table' =>  'places',
                ],
            ]
        ],
    ],

    'default' => [
        'client' => \Escavador\Vespa\Models\VespaRESTClient::class,
        'vespa_rest_client' => [
            'max_concurrency' => 128
        ],
        #define os valores defautl para os queryProfile mapeado no Vespa. Ver arquivos de configurações no caminho abaixo:
        # resources/config/vendor/vespa/src/main/application/search/query-profiles/
        'query_profile' => [
            #Os valores devem estar iguais aos do arquivo default.xml
            "default" => [
                'max_offset' => 2000,
                'max_hits' => 20,
            ]
        ],
        'limit' => 1000,
        'bulk' => 500,
        'set_language' => 'pt-BR'
    ],
);
