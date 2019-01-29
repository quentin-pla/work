<?php

namespace Core\Controllers;

use PDO;

class Database{

    private $db_name;

    private $db_user;

    private $db_pass;

    private $db_host;

    private $pdo;

    public function __construct($db_name = 'quentin-pla_cookburn',
                                $db_user = '166364_cookburn',
                                $db_pass = '#Brrete1Steak#',
                                $db_host = 'mysql-quentin-pla.alwaysdata.net'){
        $this->db_name = $db_name;
        $this->db_user = $db_user;
        $this->db_pass = $db_pass;
        $this->db_host = $db_host;
    }

    private function getPDO(){
        if($this->pdo === null){
            $pdo = new PDO('mysql:dbname=' . $this->db_name . ';host=' . $this->db_host, $this->db_user, $this->db_pass);
            $pdo->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
            $this->pdo = $pdo;
        }
        return $this->pdo;
    }

    public function query($statement, $class_name = null){
        $req = $this->getPDO()->query($statement);
        if(!is_null($class_name))$datas = $req->fetchAll(PDO::FETCH_CLASS, $class_name);
        else $datas = $req->fetchAll();
        return $datas;
    }

    public function prepare($statement, $attributes, $class_name, $one = false){
        $req = $this->getPDO()->prepare($statement);
        $req->execute($attributes);
        $req->setFetchMode(PDO::FETCH_CLASS,$class_name);
        if($one){
            $datas = $req->fetch();
        } else {
            $datas = $req->fetchAll();
        }
        return $datas;
    }
    public function insert($statement, $attributes){
        $pdo = $this->getPDO();
        $req = $pdo->prepare($statement);
        $req->execute($attributes);
        return $pdo->lastInsertId();
    }
    public function insert2($statement, $attributes){
        $pdo = $this->getPDO();
        $req = $pdo->prepare($statement);
        $req->execute($attributes);
    }
    public function insert3($statement){
        $pdo = $this->getPDO();
        $req = $pdo->prepare($statement);
        $req->execute();
    }
}