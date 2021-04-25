<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="causes">
    <h2>Causes</h2>

    <table id="causesTable" class="table table-striped table-cause">
        <thead>
        <tr>
            <th style="width: 150px;">Name</th>
            <th style="width: 150px;">Target</th>
            <th style="width: 150px;">Amount reached until now</th>
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
                    <a href="${fn:escapeXml(causeUrl)}">Cause details</a>
                </td>
                <td>
                    <spring:url value="/causes/{causeId}/newDonation" var="causeUrl">
                        <spring:param name="causeId" value="${cause.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(causeUrl)}">Donate for this cause</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <div>
    	<a href="/causes/new" class="btn btn-default">Create a cause</a>
    </div>
</petclinic:layout>
