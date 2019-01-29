<?php

namespace Core\Controllers;

class Controller {

    protected $viewTemplate = '../cookburn/Views/templates/';
    protected $viewContent = '../cookburn/Views/';

    protected function render($template,$view, $title, $variables = [],$variables2 = null){
        ob_start();
        extract($variables);
        if(!is_null($variables2))extract($variables2);
        require($this->viewContent.$view.'.php');
        $content = ob_get_clean();
        require($this->viewTemplate.$template.'.php');
    }
}