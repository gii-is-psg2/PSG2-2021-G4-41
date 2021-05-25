<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">
    <h2>Causas</h2>

    <table id="causesTable" class="table table-striped table-cause">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">Objetivo</th>
            <th style="width: 150px;">Alcanzado</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${causes}" var="cause">
            <tr>
                
                <td>
                    <c:out value="${cause.name}"/>
                </td>
                <td>
                    <c:out value="${cause.target}"/>
                </td>
                <td>
                    <c:out value="${cause.donated}"/>
                
                </td>
                <td>
                    <spring:url value="/causes/{causeId}" var="causeUrl">
                        <spring:param name="causeId" value="${cause.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(causeUrl)}">Detalles de la causa</a>
                </td>
                <c:if test="${cause.open == 'true'}">
                <td>
                    <spring:url value="/causes/{causeId}/newDonation" var="causeUrl">
                        <spring:param name="causeId" value="${cause.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(causeUrl)}">Dona por esta causa</a>
                </td>
                </c:if>
                <td>
                <c:if test="${cause.open == 'false'}">
                	<p>Causa cerrada</p>
                </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <div>
    	<a href="/causes/new" class="btn btn-default">Create a cause</a>
    </div>
</petclinic:layout>
