<?php
  require_once(dirname(__FILE__)."/../../core/utils.php");

  $db = connectDB();

  //Armamos la consulta
  $consulta = "SELECT *
  FROM materia";

  $resultado = $db->query($consulta);

  //Pasamos los datos a json

  $datos = array();
  foreach ($resultado as $row)
  $datos[] = $row;
  echo json_encode($datos);
 ?>
