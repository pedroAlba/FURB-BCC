<?php header("Content-type: text/html; charset=utf-8"); ?>
<?php 
$nome  = $_POST['nome'];
$email = $_POST['email'];
$assunto = $_POST['assunto'];
$msg = $_POST['msg']. " \n\nEnviado por: ". $nome . ' <'. $email . '>';

if ($nome != null or $email != null){

    $emailenviar = "leofronza@furb.br";
    $destino = $emailenviar;

    $enviaremail = mail($destino, $assunto, $msg);

    echo " <meta http-equiv='refresh' content='1;URL=../pages/contato.html'>";
}

?>