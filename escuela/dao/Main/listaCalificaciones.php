<?php

require_once(dirname(__FILE__)."/../../core/utils.php");
 $usuario = $_REQUEST['usuario'];
$db = connectDB();

//Armamos la consulta
$consulta = "SELECT ev.id AS evaluacion_id,ev.calificacion,ev.fecha,ma.id AS materia_id,ma.clave,ma.nombre,ma.activo
            FROM evaluacion ev
            INNER JOIN materia ma ON ma.id = ev.materia_id
            INNER JOIN usuario u ON u.id = ev.usuario_id
            WHERE u.id ='".$usuario."'";

$resultado = $db->query($consulta);

//Pasamos los datos a JSON
$datos =array();
foreach ($resultado as $row) {
  $datos[]=$row;
}
echo json_encode($datos);
 ?>
