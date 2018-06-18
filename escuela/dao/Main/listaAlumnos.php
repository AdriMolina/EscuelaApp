<?php
require_once(dirname(__FILE__)."/../../core/utils.php");

$db = connectDB();

//Armamos la consulta
$consulta = "SELECT u.id AS usuario_id,u.nombre AS usuario_nombre,u.nick,u.pass,ca.id AS categoria_id,ca.nombre AS categoria_nombre,ca.activo
            FROM usuario u
            INNER JOIN categoria ca ON ca.id = u.categoria_id
            WHERE ca.nombre = 'Alumno'";

$resultado = $db->query($consulta);

//Pasamos los datos a JSON
$datos =array();
foreach ($resultado as $row) {
  $datos[]=$row;
}
echo json_encode($datos);

 ?>
