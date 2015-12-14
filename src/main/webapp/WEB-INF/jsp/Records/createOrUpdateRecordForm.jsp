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
<script type="text/javascript">

    $(function () {

        $("#dateTime").datetimepicker({
//            addSliderAccess: true,
            timeInput: true,
            timeFormat: "HH:mm:ss",
            dateFormat: "yy-mm-dd",
//            stepMinute: 15,
//            autoSize: true,
//            sliderAccessArgs: { touchonly: false }
        }).datepicker("setDate", new Date());
    })
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


   <form:form modelAttribute="record">
        <%--<medicalclinic:selectField label="Doctor" name="stuff"  names="${stuff.size()}" size="5"/>--%>
       <select id="stuff">
           <c:forEach var="item" items="${stuff}">
               <option value="${item}"><c:out value="${item.lastName} ${item.firstName} ${item.middleName} : ${item.jobTitle}"/></option>
           </c:forEach>
       </select>
        <medicalclinic:inputField label="Date and time" name="dateTime"/>
        <medicalclinic:inputField label="comment" name="comment"/>

        <div>
            <c:forEach var="file" items="${uploadedFiles}">
                <a href="${pageContext.request.contextPath}/download/${file.id}.html">
                    <c:out value="${file.fileName}"></c:out>
                </a>
            </c:forEach>
        </div>

       <div class="form-actions">


            <input type="hidden" name="patientId" value="${record.patient.id}"/>
            <input type="hidden" name="stuffId" value="${record.stuff.id}"/>
                <c:choose>
                    <c:when test="${record['new']}">
                        <button type="submit">Add Record</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit">Update Record</button>
                    </c:otherwise>
                </c:choose>
   </form:form>

       </div>

    <c:choose>
        <c:when test="${record['new']}">
            <div>To upload files you should save Record first</div>
        </c:when>
        <c:otherwise>
            <form:form method="post" action="/uploadToRecord" enctype="multipart/form-data">
                <input type="hidden" name="currentUrl" value="${requestScope['javax.servlet.forward.request_uri']}"/>
                <input type="hidden" name="recordId" value="${record.id}"/>
                <input type="file" name="file"/>
                <input type="submit"/>
            </form:form>
        </c:otherwise>
    </c:choose>


    <br/>
    <b>Previous records</b>
    <table style="width: 100%;">
        <tr>
            <th>Date</th>
            <th>Stuff</th>
        </tr>
        <tr>
            <th>Details</th>
        </tr>
        <c:forEach var="record" items="${record.patient.records}">
            <c:if test="${!record['new']}">
                <tr>
                    <td><c:out value="${record.dateTime}"/></td>
                    <td><c:out value="${record.stuff.lastName} ${record.stuff.firstName} ${record.stuff.middleName} : ${record.stuff.jobTitle}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${record.comment}"/></td>
                </tr>
            </c:if>
        </c:forEach>

    </table>

</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
