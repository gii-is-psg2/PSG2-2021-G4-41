<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="adoptions">
	<div>
	<table class="table table-striped">
        <tr>
            <th>Nombre de la mascota</th>
            <td><c:out value="${request.pet.name}"/></td>
        </tr>
        <tr>
            <th>Tipo</th>
            <td><c:out value="${request.pet.type}"/></td>
        </tr>
        <tr>
            <th>Edad (Años)</th>
            <td><c:out value="${request.pet.getAge()}"/></td>
        </tr>
    </table>
	</div>

    <div>
    <h2>Adoptions Applications</h2>

    <table id="applicationsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nuevo Nombre de Owner</th>
            <th>Localización (Ciudad)</th>
            <th>Descripción</th>
            <th>Fecha</th>
            <th>Aprobación</th>
     
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${applications}" var="application">
            <tr>
                <td>
                    <c:out value="${application.futureOwner.firstName} ${application.futureOwner.lastName}"/>
                </td>
                <td>
                 	<c:out value="${application.futureOwner.city}"/>
                </td>
				<td>
                 	<c:out value="${application.description}"/>
                </td>
                <td>
                 	<c:out value="${application.timestamp}"/>
                </td>
                <td>
                    <spring:url value="/adoptions/requests/{requestId}/applications/{applicationId}/confirm" var="confirm">
						<spring:param name="requestId" value="${request.id}" />
                        <spring:param name="applicationId" value="${application.id}" />
					</spring:url> 
                    <a class="glyphicon glyphicon-ok" href="${fn:escapeXml(confirm)}"></a>              
                  </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>	
    </div>
</petclinic:layout>