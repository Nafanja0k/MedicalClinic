<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="medicalclinic" tagdir="/WEB-INF/tags" %>


<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>
        <c:if test="${patient['new']}">New </c:if> Patient
    </h2>
    <form:form modelAttribute="patient" class="form-horizontal" id="add-patient-form">
        <medicalclinic:inputField label="First Name" name="firstName"/>
        <medicalclinic:inputField label="Middle Name" name="middleName"/>
        <medicalclinic:inputField label="Last Name" name="lastName"/>
<%--        <medicalclinic:inputField label="Address" name="address"/>
        <medicalclinic:inputField label="City" name="city"/>
        <medicalclinic:inputField label="Telephone" name="telephone"/>--%>

        <div class="form-actions">
            <c:choose>
                <c:when test="${patient['new']}">
                    <button type="submit">Add Patient</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Update Patient</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
