<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="adoptions">
	<table class="table-buttons">
        <tr>
            <td>
                <a class="btn btn-default" href='<spring:url value="/adoptions/requests/new" htmlEscape="true"/>'>New Adoption Request</a>
        </tr>
    </table>

    <br/>
    <br/>
    
    <div>
    <h2>Adoptions Requests</h2>

    <table id="requestsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre de la mascota</th>
            <th>Tipo</th>
            <th>Edad (Años)</th>
            <th>Localización (Ciudad)</th>
            <th>Owner</th>
            <th>Aplicar</th>
     
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requests}" var="request">
            <tr>
                <td>
                    <c:out value="${request.pet.name}"/>
                </td>
				<td>
                 <c:out value="${request.pet.type}"/>
                </td>
                <td>
                    <c:out value="${request.pet.getAge()}"/><%-- ${request.pet.birthDate} --%>
                </td>
                <td>
                    <c:out value="${request.pet.owner.city}"/>
                </td>
                <td>
                    <c:out value="${request.pet.owner.firstName} ${request.pet.owner.lastName}"/>
                </td>
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
    <br/>
    <br/>
    <br/>	
    </div>

    <div>
    <h2>Adoption Applications para mis Mascotas</h2>

    <table id="applicationsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre de la Mascota</th>
            <th>Tipo</th>
            <th>Edad (Años)</th>
            <%--
            <th>Number of Applications</th>
            --%>
            <th>Applications List</th>
     
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${myRequests}" var="myRequest">
            <tr>
                <td>
                    <c:out value="${myRequest.pet.name}"/>
                </td>
				<td>
                 <c:out value="${myRequest.pet.type}"/>
                </td>
                <td>
                    <c:out value="${myRequest.pet.getAge()}"/><%-- ${request.pet.birthDate} --%>
                </td>
                <%--
                <td>
                	<c:if test="${applications.size() == 0}">none</c:if>
                	<c:if test="${applications.size() != 0}">
                		<c:out value="${applications.size()}"/>
                	</c:if>
                </td>
                --%>
                <td>                
                    <spring:url value="/adoptions/requests/{myRequestId}/applications" var="myRequestUrl">
						<spring:param name="myRequestId" value="${myRequest.id}" />
					</spring:url> 
                    <a class="glyphicon glyphicon-th-list" href="${fn:escapeXml(myRequestUrl)}"></a>                
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
</petclinic:layout>