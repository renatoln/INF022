<?php

return [
    /*
    |--------------------------------------------------------------------------
    | MyPlace Extractors config
    |--------------------------------------------------------------------------
    |
    | Here you can specify MyPlace Extractors configs
    |
    */

    'info' => [
        'inep_censo_superior_aluno' => [
            [
                'class' => Plugins\Extractors\Inep\CensoEducacaoSuperior\StudentExtractor::class,
                'path' => 'storage/dataset/censo-educacao-superior/DM_ALUNO.CSV',
                'delimiter' => '|',
                'periods' =>  [
                    '2013', '2014', '2015', '2016', '2017'
                ]
            ],
        ]
    ],
];
