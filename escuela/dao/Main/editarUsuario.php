<?php
require_once(dirname(__FILE__)."/../../core/utils.php");
require_once(dirname(__FILE__)."/../../library/nusoap/lib/nusoap.php");

$db = connectDB();

$id = $_REQUEST['id'];
$nick = $_REQUEST['nick'];
$pass = $_REQUEST['pass'];
$nombre = $_REQUEST['nombre'];
//Encriptamos el pass
$cliente = new nusoap_client("http://atc.mx/android/libraryPHP/encriptado/seguridad.php?wsdl",true);
$parametros = array('password'=>$pass);
$passEncriptado = $cliente->call('encriptar',$parametros);

//Armamos la consulta
$consulta = "UPDATE usuario
            SET nombre = '".$nombre."',nick = '".$nick."',pass ='".$passEncriptado."'
            WHERE id = '".$id."'";

$resultado = $db->query($consulta);

if ($resultado) {
echo "Se actualizo correctamente";
}

 ?>
