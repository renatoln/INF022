<?php

namespace Plugins\Extractors\Inep\CensoEducacaoSuperior;

class StudentExtractor implements MyPlaceExtractor
{
    public function __construct()
    {
//        $key_config = 'extractors.info.' . Str::snake(StudentExtractor::class);

        $dataset_path = config();
    }

    public function extract()
    {
        $handle = fopen('characters.csv', "r");
        $header = true;

        while ($csvLine = fgetcsv($handle, 1000, "|")) {

            if ($header) {
                $header = false;
            } else {
                Character::create([
                    'name' => $csvLine[0] . ' ' . $csvLine[1],
                    'job' => $csvLine[2],
                ]);
            }
        }
    }
}
