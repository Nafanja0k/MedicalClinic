<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="medicalclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>


<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>

<script>
    $(function () {
        $("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
    });
</script>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>
        <c:if test="${stuff['new']}">New </c:if> Stuff
    </h2>
    <form:form modelAttribute="stuff" class="form-horizontal" id="add-stuff-form">
        <medicalclinic:inputField label="First Name" name="firstName"/>
        <medicalclinic:inputField label="Middle Name" name="middleName"/>
        <medicalclinic:inputField label="Last Name" name="lastName"/>
        <medicalclinic:inputField label="Job title" name="jobTitle"/>
        <medicalclinic:inputField label="Birth Date" name="birthDate"/>

<%--        <medicalclinic:inputField label="Address" name="address"/>
        <medicalclinic:inputField label="City" name="city"/>
        <medicalclinic:inputField label="Telephone" name="telephone"/>--%>

        <div class="form-actions">
            <c:choose>
                <c:when test="${stuff['new']}">
                    <button type="submit">Add Stuff</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Update Stuff</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
