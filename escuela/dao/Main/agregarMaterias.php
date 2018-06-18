<?php
  require_once(dirname(__FILE__)."/../../core/utils.php");

  $db = connectDB();
  //Recibios las variables necesarias para la inserccion
  $clave = $_REQUEST['clave'];
  $nombre = $_REQUEST['nombre'];
  //Armamos la consulta
  $consulta = "INSERT INTO materia (clave, nombre) VALUES ('".$clave."', '".$nombre."');";

  $resultado = $db->query($consulta);

if ($resultado)
  echo "Registrado";
 ?>
