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
    <!-- Bootstrap plugins CSS -->
    <link href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/pygments-manni.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/font-awesome.css"/>" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="<c:url value="/resources/js/html5shiv.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/respond.min.js"/>"></script>
    <![endif]-->
    <!-- Bootstrap core JavaScript ================================================== -->

    <script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/moment.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.ru.js"/>"></script>
</head>

<body>
<!--
    <header>
    	<h1><span class="smallText">Let's</span> Steppin</h1>
    </header>
-->
<div class="container">
    <div class="row">
        <tiles:insertAttribute name="body" />
    </div>
    <br/><br/><br/>
</div>
</body>
</html>

