<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="medicalclinic" tagdir="/WEB-INF/tags" %>


<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>


<body>
<script>
    $(function () {
        $("#date").datepicker({dateFormat: 'yy/mm/dd'});
    });
</script>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2><c:if test="${record['new']}">New </c:if>Record</h2>


    <b>Patient</b>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Patient ID</th>
            <th>Patient name</th>
            <th>Birth date</th>
            <th>Comment</th>
        </tr>
        </thead>
        <tr>
            <td><c:out value="${record.patient.id}"/></td>
            <td><c:out value="${record.patient.lastName} ${record.patient.firstName} ${record.patient.middleName}"/></td>
            <td><c:out value="${record.patient.birthDate}"/></td>
            <td><c:out value="${record.comment}"/></td>
        </tr>
    </table>

<%--
   <form:form modelAttribute="record">

        <medicalclinic:inputField label="date" name="dateTime"/>
        <medicalclinic:inputField label="description" name="description"/>

        <div class="form-actions">

            <input type="hidden" name="patientId" value="${record.patient.id}"/>

            <button type="submit">Add Visit</button>
        </div>
    </form:form>
--%>

    <br/>
    <b>Previous Visits</b>
    <table style="width: 333px;">
        <tr>
            <th>Date</th>
            <th>Description</th>
        </tr>
        <c:forEach var="record" items="${record.patient.records}">
            <c:if test="${!record['new']}">
                <tr>
                    <%--<td><joda:format value="${record.dateTime}" pattern="yyyy/MM/dd"/></td>--%>
                    <td><c:out value="${record.comment}"/></td>
                </tr>
            </c:if>
        </c:forEach>
    </table>

</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
