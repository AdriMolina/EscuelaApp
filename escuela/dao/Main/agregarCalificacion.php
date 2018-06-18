<?php
require_once(dirname(__FILE__)."/../../core/utils.php");
$calificacion =$_REQUEST['calificacion'];
$materia_id = $_REQUEST['materia_id'];
$usuario_id = $_REQUEST['usuario_id'];

$db = connectDB();

//Armamos la consulta
$consulta = "INSERT INTO evaluacion(calificacion,usuario_id,materia_id)VALUES('".$calificacion."','".$usuario_id."','".$materia_id."')";

$resultado = $db->query($consulta);

if ($resultado) {
echo "Se inserto correctamente";
}

 ?>
