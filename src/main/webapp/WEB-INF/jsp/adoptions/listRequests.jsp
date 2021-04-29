<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="listRequests">
	<table class="table-buttons">
        <tr>
            <td>
                <a class="btn btn-default" href='<spring:url value="/adoptions/requests/new" htmlEscape="true"/>'>New Adoption Request</a>
        </tr>
    </table>

    <br/>
    <br/>
    <br/>

    <h2>Adoptions Requests</h2>

    <table id="requestsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Owner Name</th>
            
            <%-- COMPLETAR
            <th>Specialties</th>
            <th>Delete</th>
            <th>Edit</th>/ --%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${request.adoptionRequestList}" var="request">
            <tr>
                <td>
                    <c:out value="${request.firstName} ${request.lastName}"/>
                </td>
                <%-- COMPLETAR
                <td>
                    <c:forEach var="specialty" items="${vet.specialties}">
                        <c:out value="${specialty.name} "/>
                    </c:forEach>
                    <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
                </td>
				<td>
                    <spring:url value="/vets/{vetId}/delete" var="deleteVetUrl">
                        <spring:param name="vetId" value="${vet.id}"/>
                    </spring:url>
                    <a class="glyphicon glyphicon-trash" href="${fn:escapeXml(deleteVetUrl)}"></a>
                </td>
                <td>
                    <spring:url value="/vets/{vetId}/edit" var="vetUrl">
						<spring:param name="vetId" value="${vet.id}" />
					</spring:url> 
                    <a class="glyphicon glyphicon-pencil" href="${fn:escapeXml(vetUrl)}"></a>
                </td>
                --%>
                <td>
                    <spring:url value="/adoptions/requests/{requestId}/applications/new" var="requestUrl">
						<spring:param name="requestId" value="${request.id}" />
					</spring:url> 
                    <a class="glyphicon glyphicon-envelope" href="${fn:escapeXml(requestUrl)}"></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>