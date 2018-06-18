<?php
    require("config.php");

    function connectDB()
    {
        $db = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
        if($db->connect_error)
            die();
        else
        {
            $db->set_charset("utf8");
            return $db;
        }
    }
?>