<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!--
PetClinic :: a Spring Framework demonstration
-->

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>PetClinic :: a Spring Framework demonstration</title>


    <spring:url value="/webjars/bootstrap/2.3.0/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>

    <spring:url value="/resources/css/medicalclinic.css" var="medicalclinicCss"/>
    <link href="${medicalclinicCss}" rel="stylesheet"/>

    <spring:url value="/webjars/jquery/2.0.3/jquery.js" var="jQuery"/>
    <script src="${jQuery}"></script>

    <!-- jquery-ui.js uploadFile is really big so we only load what we need instead of loading everything -->
    <spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.core.js" var="jQueryUiCore"/>
    <script src="${jQueryUiCore}"></script>

    <spring:url value="/webjars/jquery-ui/1.10.3/ui/jquery.ui.datepicker.js" var="jQueryUiDatePicker"/>
    <script src="${jQueryUiDatePicker}"></script>

    <!-- jquery-ui.css uploadFile is not that big so we can afford to load it -->
    <spring:url value="/webjars/jquery-ui/1.10.3/themes/base/jquery-ui.css" var="jQueryUiCss"/>
    <link href="${jQueryUiCss}" rel="stylesheet"/>

    <link rel="stylesheet" media="all" type="text/css" href="/webjars/jQuery-Timepicker-Addon/1.5.3/src/jquery-ui-timepicker-addon.css" />
    <script src="/webjars/jQuery-Timepicker-Addon/1.5.3/dist/jquery-ui-timepicker-addon.js"></script>
    <script src="/webjars/jQuery-Timepicker-Addon/1.5.3/dist/jquery-ui-sliderAccess.js"></script>
    <%--<script src="/webjars/jQuery-Timepicker-Addon/1.5.3/src/i18n/jquery-ui-timepicker-addon-i18n.js"></script>--%>
</head>


