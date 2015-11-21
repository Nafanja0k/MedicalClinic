<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Stuff Information</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${stuff.firstName} ${stuff.middleName} ${stuff.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Birth date</th>
            <td><b><c:out value="${stuff.birthDate}"/></b></td>
        </tr>

<%--        <tr>
            <th>Address</th>
            <td><c:out value="${stuff.address}"/></td>
        </tr>
        <tr>
            <th>City</th>
            <td><c:out value="${stuff.city}"/></td>
        </tr>
        <tr>
            <th>Telephone</th>
            <td><c:out value="${stuff.telephone}"/></td>
        </tr>--%>
        <tr>
            <td>
                <spring:url value="{stuffId}/edit.html" var="editUrl">
                    <spring:param name="stuffId" value="${stuff.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Edit Stuff</a></td>
            <td>
            </td>
        </tr>
    </table>


    <jsp:include page="../fragments/footer.jsp"/>

</div>

</body>

</html>
