<?php

namespace Vespa\Models;
use Escavador\Traits\VespaTrait;
use Escavador\Vespa\Interfaces\AbstractDocument;
use Escavador\Vespa\Models\AbstractChild;

class Place implements AbstractDocument
{
    use VespaTrait;

    public function __construct($id, $name, $parent_id)
    {
        $this->id = $id;
        $this->name= $name;
        $this->parent_id = $parent_id;
    }

    public function getVespaDocumentId()
    {
        return $this->id;
    }

    public static function instanceByVespaChildResponse(AbstractChild $child) : AbstractDocument
    {
        $id = $child->field('id');
        $name = $child->field('name');
        $parent_id = $child->field('parent_id');

        //TODO
        return null;
    }

    public function getVespaDocumentFields()
    {
        return [
            'id' => $this->id,
            'name' => $this->name,
            'parent_id' => $this->parent_id
        ];
    }

    public static function markAsVespaIndexed($documents)
    {
        //TODO
    }

    public static function getVespaDocumentsToIndex(int $limit)
    {
        //TODO
       return [];
    }
}
