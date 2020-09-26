<!DOCTYPE html>
<!--[if lt IE 7]>
<html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>
<html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>
<html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans"/>
    <style>
        .body {
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            align-content: center;
            justify-content: center;
            height: 100vh;
        }
        ul {
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0px 0px 53px 0px rgba(0, 0, 0, 0.19);
            display: flex;
            flex-direction: column;
            min-width: 400px;
            list-style: none;
        }
        li {
            padding: 8px;
            margin-bottom: 15px;
            border-radius: 4px;
            border: gray 1px solid;
        }
        a {
            color: black;
            font-family: Open Sans sans-serif;
            font-size: 24px;
            text-decoration: none;
        }
        a:visited {
            color: black;
            text-decoration: none;
        }

    </style>
</head>
<body>
    <header>
        <div>
            <a href="calc">Go Calc</a>
            <a href="history">Go History</a>
        </div>
    </header>

    <div class="forms">
        <!--[if lt IE 7]>
        <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="#">upgrade your
            browser</a> to improve your experience.</p>
        <![endif]-->
        <ul>
            <#list systems as system>
                <li>${system.getuName()} ID ${system.getuID()}</li>
            </#list>
        </ul>
    </div>
</body>
</html>
