<?php

namespace Core\Controllers;

class Model{
    private $db_instance;

    public function get_db(){
        if(is_null($this->db_instance)){
            $this->db_instance = $db = new Database();
        }
        return $this->db_instance;
    }

    public function findByID() {

    }
}