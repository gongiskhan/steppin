<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="http://goncalogomes.info">

    <title>Steppin</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <!-- Bootstrap theme CSS -->
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<!--
    <header>
    	<h1><span class="smallText">Let's</span> Steppin</h1>
    </header>
-->
<div class="container">

    <tiles:insertAttribute name="body" />

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
</body>
</html>

