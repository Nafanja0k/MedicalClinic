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

    <h2>Patient Information</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${patient.firstName} ${patient.middleName} ${patient.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Birth date</th>
            <td><c:out value="${patient.birthDate}"/></td>
        </tr>
<%--        <tr>
            <th>City</th>
            <td><c:out value="${patient.city}"/></td>
        </tr>
        <tr>
            <th>Telephone</th>
            <td><c:out value="${patient.telephone}"/></td>
        </tr>--%>
        <tr>
            <td>
                <spring:url value="{patientId}/edit.html" var="editUrl">
                    <spring:param name="patientId" value="${patient.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Edit Patient</a></td>
            <td>
                <spring:url value="{patientId}/records/new.html" var="addUrl">
                    <spring:param name="patientId" value="${patient.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(addUrl)}" class="btn btn-success">Add New Record</a></td>
        </tr>
    </table>
    <h2>Pets and Visits</h2>
        <table class="table" style="width:600px;">
            <tr>
                <td valign="top" style="width: 120px;">
                    <dl class="dl-horizontal">
<%--
                        <dt>Name</dt>
                        <dd><c:out value="${pet.name}"/></dd>
                        <dt>Birth Date</dt>
                        <dd><joda:format value="${pet.birthDate}" pattern="yyyy-MM-dd"/></dd>
                        <dt>Type</dt>
                        <dd><c:out value="${pet.type.name}"/></dd>
--%>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        <thead>
                        <tr>
                            <th>Visit Date</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <c:forEach var="record" items="${patient.records}">
                            <tr>
                                <td><c:out value="${record.dateTime}"/></td>
                                <td><c:out value="${record.comment}"/></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>
                                <spring:url value="/patients/{patientId}/edit" var="patientUrl">
                                    <spring:param name="patientId" value="${patient.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(patientUrl)}">Edit Patient</a>
                            </td>
                            <td>
                                <spring:url value="/patients/{patientId}/records/new" var="recordsUrl">
                                    <spring:param name="patientId" value="${patient.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(recordsUrl)}">Add Record</a>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>


    <jsp:include page="../fragments/footer.jsp"/>

</div>

</body>

</html>
